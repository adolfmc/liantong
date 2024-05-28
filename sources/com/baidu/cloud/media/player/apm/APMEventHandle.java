package com.baidu.cloud.media.player.apm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.uaq.agent.android.AgentConfig;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.customtransmission.APMAgent;
import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.customtransmission.APMUploadHandler;
import com.baidu.uaq.agent.android.customtransmission.MergeBlockCallBack;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class APMEventHandle {
    private static final String APM_UAQ_CLASS = "com.baidu.uaq.agent.android.UAQ";
    private static final String APM_UAQ_LIVE_METHOD = "onLiveEvent";
    private static final String EVENT_BUFFER_MID_END = "bufferingEnd";
    private static final String EVENT_BUFFER_MID_START = "bufferingStart";
    private static final String EVENT_DEALLOC = "dealloc";
    private static final String EVENT_DOMAIN_IP_FOUND = "domainIPFound";
    private static final String EVENT_ENV_INFO = "info";
    private static final String EVENT_FIRST_BUFFER_END = "firstBufferingEnd";
    private static final String EVENT_FIRST_FRAME_RENDERED = "firstFrameRendered";
    private static final String EVENT_FRAME_CHARSING_END = "frameChasingEnd";
    private static final String EVENT_FRAME_CHARSING_START = "frameChasingStart";
    private static final String EVENT_INIT = "init";
    private static final String EVENT_NETWORK_CHANGED = "network";
    private static final String EVENT_NETWORK_SPEED_REPORT = "networkSpeed";
    private static final String EVENT_PLAYER_CRASH = "crash";
    private static final String EVENT_PLAYER_CREATED = "playerCreated";
    private static final String EVENT_PLAY_COUNT = "keepPlaying";
    private static final String EVENT_PLAY_END_APM = "playEnd";
    private static final String EVENT_PLAY_FAIL = "error";
    private static final String EVENT_UPDATE_CDN = "updateCdn";
    private static final String EVENT_USER_PAUSE = "pause";
    private static final String EVENT_USER_PLAY = "play";
    private static final String EVENT_USER_PLAY_END = "end";
    private static final String EVENT_USER_SEEK = "seek";
    private static final String TAG = "APMEventHandle";
    private static APMEventHandle apmEventHandle = null;
    private static String sCuid = "";
    private static boolean sUAUploadEnable;
    private APMAgent apmAgent;
    private final APMUploadHandler apmUploadHandler;
    private APMCommData commData;
    private APMUploadHandler ownUploadHandler;
    private long bufferStartTime = 0;
    private boolean isFrameChasing = false;
    private long frameCharsingStart = 0;
    private ExecutorService threadRunner = null;
    private MergeBlockCallBack ownMergeCallBack = new MergeBlockCallBack() { // from class: com.baidu.cloud.media.player.apm.APMEventHandle.3
        @Override // com.baidu.uaq.agent.android.customtransmission.MergeBlockCallBack
        public String executeMerge(ArrayList<String> arrayList) {
            JSONArray jSONArray = new JSONArray();
            try {
                JSONObject envJson = APMEventHandle.this.commData.toEnvJson();
                envJson.put("time", System.currentTimeMillis());
                envJson.put("type", "info");
                jSONArray.put(envJson);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    Object nextValue = new JSONTokener(it.next()).nextValue();
                    if (nextValue instanceof JSONObject) {
                        jSONArray.put(nextValue);
                    } else if (nextValue instanceof JSONArray) {
                        JSONArray jSONArray2 = (JSONArray) nextValue;
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            jSONArray.put(jSONArray2.getJSONObject(i));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
        }
    };
    private MergeBlockCallBack apmMergeCallBack = new MergeBlockCallBack() { // from class: com.baidu.cloud.media.player.apm.APMEventHandle.4
        @Override // com.baidu.uaq.agent.android.customtransmission.MergeBlockCallBack
        public String executeMerge(ArrayList<String> arrayList) {
            JSONArray jSONArray = new JSONArray();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("CommData", APMEventHandle.this.commData.toJson());
                jSONObject.put("eventName", "info");
                jSONObject.put("eventInfo", APMEventHandle.this.commData.toEnvJson());
                jSONArray.put(jSONObject);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    Object nextValue = new JSONTokener(it.next()).nextValue();
                    if (nextValue instanceof JSONObject) {
                        jSONArray.put(nextValue);
                    } else if (nextValue instanceof JSONArray) {
                        JSONArray jSONArray2 = (JSONArray) nextValue;
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            jSONArray.put(jSONArray2.getJSONObject(i));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
        }
    };

    private APMEventHandle(Context context) {
        this.commData = new APMCommData(context, sCuid);
        CrashHandler.getInstance().init(context);
        this.apmAgent = UAQ.getInstance().setConfig(new AgentConfig.Builder().APIKey("6ab8e94e75b94316ae7cf4bfb6bd46e7").usePersistentUUID(true).reportCrashes(true).thingsMonitor(true).build()).startAPM(context.getApplicationContext());
        APMUploadConfigure aPMUploadConfigure = new APMUploadConfigure("APMPerformanceConfigurationName", null, this.apmMergeCallBack);
        long j = 60;
        aPMUploadConfigure.setInterval4g(j);
        long j2 = 15;
        aPMUploadConfigure.setIntervalWifi(j2);
        long j3 = 204800;
        long j4 = 86400;
        aPMUploadConfigure.setMaxbytes4g(j3, j4);
        long j5 = 0;
        aPMUploadConfigure.setMaxbyteswifi(j5, j4);
        aPMUploadConfigure.enableRetransmission(true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("Content-Encoding", "deflate");
        aPMUploadConfigure.setHeaderMap(hashMap);
        this.apmUploadHandler = this.apmAgent.addUploadConfigure(aPMUploadConfigure);
        if (sUAUploadEnable) {
            APMUploadConfigure aPMUploadConfigure2 = new APMUploadConfigure("userOperation", "https://drm.media.baidubce.com:8888/v2/sdk/player", this.ownMergeCallBack);
            aPMUploadConfigure2.setInterval4g(j);
            aPMUploadConfigure2.setIntervalWifi(j2);
            aPMUploadConfigure2.setMaxbytes4g(j3, j4);
            aPMUploadConfigure2.setMaxbyteswifi(j5, j4);
            aPMUploadConfigure2.enableRetransmission(true);
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("Content-Type", "application/json");
            hashMap2.put("Content-Encoding", "gzip");
            aPMUploadConfigure2.setHeaderMap(hashMap2);
            this.ownUploadHandler = this.apmAgent.addUploadConfigure(aPMUploadConfigure2);
        }
    }

    public static APMEventHandle getInstance(Context context) {
        if (apmEventHandle == null) {
            apmEventHandle = new APMEventHandle(context);
        }
        return apmEventHandle;
    }

    public static void setCuid(String str) {
        sCuid = str;
    }

    public static void setUAUploadEnable(boolean z) {
        sUAUploadEnable = z;
    }

    public void release() {
        try {
            invokeStat("dealloc", new JSONObject());
        } catch (Exception e) {
            Log.d("APMEventHandle", "release " + e.getMessage());
        }
        this.apmAgent.stopAPM();
        apmEventHandle = null;
    }

    public void updateSession(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("videoUrl", str);
            invokeStat("init", jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "release " + e.getMessage());
        }
        this.commData.updateSession(str);
    }

    public void setPlayerRelated(String str, String str2, String str3) {
        this.commData.setPlayerRelated(str, str2, str3);
    }

    public void onPlayerCreated() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "playerCreated");
            jSONObject.put("StartTime", System.currentTimeMillis());
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDnsParsed(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "domainIPFound");
            jSONObject.put("StartTime", System.currentTimeMillis());
            jSONObject.put("Duration", i);
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onHttpConnected(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "httpConnected");
            jSONObject.put("StartTime", System.currentTimeMillis());
            jSONObject.put("Duration", j);
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFirstFrameRendered(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "firstFrameRendered");
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("StartTime", currentTimeMillis);
            jSONObject.put("Duration", currentTimeMillis - j);
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFrameChasingStart() {
        this.isFrameChasing = true;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "frameChasingStart");
            this.frameCharsingStart = System.currentTimeMillis();
            jSONObject.put("StartTime", this.frameCharsingStart);
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFrameCharingEnd() {
        this.isFrameChasing = false;
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.frameCharsingStart <= 0) {
                Log.d("APMEventHandle", "onFrameCharingEnd error: need invoke onFrameChasingStart first");
                return;
            }
            jSONObject.put("Name", "frameChasingEnd");
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("StartTime", currentTimeMillis);
            jSONObject.put("Duration", currentTimeMillis - this.frameCharsingStart);
            this.frameCharsingStart = 0L;
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFirstBufferEnd(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "firstBufferingEnd");
            jSONObject.put("StartTime", System.currentTimeMillis());
            jSONObject.put("Duration", j);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onFirstBufferEnd " + e.getMessage());
        }
    }

    public void onBufferingStart() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "bufferingStart");
            this.bufferStartTime = System.currentTimeMillis();
            jSONObject.put("StartTime", this.bufferStartTime);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("isFrameChasing", this.isFrameChasing);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onBufferingStart " + e.getMessage());
        }
    }

    public void onBufferingEnd() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.bufferStartTime <= 0) {
                Log.d("APMEventHandle", "onBufferingEnd error: need invoke onBufferingStart first");
                return;
            }
            jSONObject.put("Name", "bufferingEnd");
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("StartTime", currentTimeMillis);
            jSONObject.put("Duration", currentTimeMillis - this.bufferStartTime);
            this.bufferStartTime = 0L;
            invokeAPM(jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onBufferingEnd " + e.getMessage());
        }
    }

    public void onNetworkSpeedReport(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "networkSpeed");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("speed", i);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onNetworkSpeedReport " + e.getMessage());
        }
    }

    public void onUserPlay(JSONObject jSONObject) {
        try {
            invokeStat("play", jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onUserPlay " + e.getMessage());
        }
    }

    public void onUserPlayEnd(JSONObject jSONObject) {
        try {
            invokeStat("end", jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onUserPlayEnd " + e.getMessage());
        }
    }

    public void onUserPause(float f) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("position", f);
            invokeStat("pause", jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onUserPause " + e.getMessage());
        }
    }

    public void onUserSeek(JSONObject jSONObject) {
        try {
            invokeStat("seek", jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onUserSeek " + e.getMessage());
        }
    }

    public void onPlayCount() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "keepPlaying");
            jSONObject.put("StartTime", System.currentTimeMillis());
            invokeAPM(jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onPlayCount " + e.getMessage());
        }
    }

    public void onUpdateCdn(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "updateCdn");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("cdnIp", str);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onUpdateCdn " + e.getMessage());
        }
    }

    public void onUpdateMetadata(String str) {
        this.commData.updateplyId(str);
    }

    public void onPlayEnd(int i, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("error", i);
            jSONObject.put("errorInfo", i2);
            jSONObject2.put("Name", "playEnd");
            jSONObject2.put("StartTime", System.currentTimeMillis());
            jSONObject2.put("Code", i3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("detail", "what=" + i + ";extra=" + i2);
            jSONObject2.put("EventData", jSONObject3);
            invokeStat("error", jSONObject);
            invokeAPM(jSONObject2);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onPlayFail " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPlayerCrash(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashInfo", str);
            invokeStat("crash", jSONObject);
        } catch (Exception e) {
            Log.d("APMEventHandle", "onPlayerCrash " + e.getMessage());
        }
    }

    private void invokeStat(String str, JSONObject jSONObject) throws JSONException {
        if (!sUAUploadEnable || this.ownUploadHandler == null) {
            return;
        }
        jSONObject.put("type", str);
        jSONObject.put("time", System.currentTimeMillis());
        if (!TextUtils.isEmpty(this.commData.getVvid())) {
            jSONObject.put("session", this.commData.getVvid());
        }
        final String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        Runnable runnable = new Runnable() { // from class: com.baidu.cloud.media.player.apm.APMEventHandle.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    APMEventHandle.this.apmAgent.addLogWithHandler(APMEventHandle.this.ownUploadHandler, jSONObject2);
                } catch (Exception e) {
                    Log.d("APMEventHandle", "" + e.getMessage());
                }
            }
        };
        ExecutorService executorService = this.threadRunner;
        if (executorService == null || executorService.isShutdown()) {
            this.threadRunner = Executors.newSingleThreadExecutor();
        }
        this.threadRunner.execute(runnable);
    }

    private void invokeAPM(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("TraceId", this.commData.getVvid());
            jSONObject2.put("CommData", this.commData.toJson());
            jSONObject2.put("Event", jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("Trace", jSONObject2);
            final String jSONObject4 = !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3);
            Runnable runnable = new Runnable() { // from class: com.baidu.cloud.media.player.apm.APMEventHandle.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        APMEventHandle.this.apmAgent.addLogWithHandler(APMEventHandle.this.apmUploadHandler, jSONObject4);
                    } catch (Exception e) {
                        Log.d("APMEventHandle", "" + e.getMessage());
                    }
                }
            };
            if (this.threadRunner == null || this.threadRunner.isShutdown()) {
                this.threadRunner = Executors.newSingleThreadExecutor();
            }
            this.threadRunner.execute(runnable);
        } catch (Exception e) {
            Log.d("APMEventHandle", "" + e.getMessage());
        }
    }
}
