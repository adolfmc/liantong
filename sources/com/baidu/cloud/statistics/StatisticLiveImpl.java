package com.baidu.cloud.statistics;

import android.content.Context;
import com.baidu.uaq.agent.android.AgentConfig;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.customtransmission.APMAgent;
import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.customtransmission.APMUploadHandler;
import com.baidu.uaq.agent.android.customtransmission.MergeBlockCallBack;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p059a.p060a.C0707b;
import p001a.p058b.p059a.p060a.RunnableC0702a;
import p001a.p058b.p059a.p060a.p061a.C0704b;
import p001a.p058b.p059a.p060a.p061a.C0706d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StatisticLiveImpl implements IStatisticLive {
    public static final String TAG = "LiveStatistics";
    public static StatisticLiveImpl apmEventHandle = null;
    public static String sCuid = "";
    public APMAgent apmAgent;
    public final APMUploadHandler apmUploadHandler;
    public C0704b commData;
    public long mliveStartTime;
    public long connectStartTime = 0;
    public ExecutorService threadRunner = null;
    public MergeBlockCallBack apmMergeCallBack = new C0707b(this);

    public StatisticLiveImpl(Context context) {
        this.commData = new C0704b(context, sCuid);
        this.apmAgent = UAQ.getInstance().setConfig(new AgentConfig.Builder().APIKey("6ab8e94e75b94316ae7cf4bfb6bd46e7").usePersistentUUID(true).reportCrashes(true).crashCollectorPath("/bdcmedia_sdk_crash").collectorPath("/bdcmedia_sdk_tracker").sourceType(1).thingsMonitor(true).build()).startAPM(context.getApplicationContext());
        APMUploadConfigure aPMUploadConfigure = new APMUploadConfigure("APMPerformanceConfigurationName", null, this.apmMergeCallBack);
        aPMUploadConfigure.setInterval4g(60);
        aPMUploadConfigure.setIntervalWifi(10);
        long j = 86400;
        aPMUploadConfigure.setMaxbytes4g(204800, j);
        aPMUploadConfigure.setMaxbyteswifi(0, j);
        aPMUploadConfigure.enableRetransmission(true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("Content-Encoding", "deflate");
        aPMUploadConfigure.setHeaderMap(hashMap);
        this.apmUploadHandler = this.apmAgent.addUploadConfigure(aPMUploadConfigure);
    }

    public static StatisticLiveImpl getInstance(Context context) {
        if (apmEventHandle == null) {
            apmEventHandle = new StatisticLiveImpl(context);
        }
        return apmEventHandle;
    }

    private void invokeAPM(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("TraceId", this.commData.f2107b);
            jSONObject2.put("CommData", this.commData.m22342b());
            jSONObject2.put("Event", jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("Trace", jSONObject2);
            RunnableC0702a runnableC0702a = new RunnableC0702a(this, !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
            if (this.threadRunner == null || this.threadRunner.isShutdown()) {
                this.threadRunner = Executors.newSingleThreadExecutor();
            }
            this.threadRunner.execute(runnableC0702a);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a(""), "LiveStatistics");
        }
    }

    public static void setCuid(String str) {
        sCuid = str;
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveConnected(long j, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "connect");
            this.connectStartTime = System.currentTimeMillis();
            jSONObject.put("StartTime", this.connectStartTime);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("connectDuration", j);
            jSONObject2.put("connectCount", i);
            jSONObject2.put("serviceIP", str);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveEnd(int i, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "stop");
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("StartTime", currentTimeMillis);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("errorCode", i);
            jSONObject2.put("Duration", currentTimeMillis - this.mliveStartTime);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveError(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "error");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("errorCode", i);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveMetadata(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "metadata");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("isSuccess", i);
            jSONObject2.put("error", i2);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveMute(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "audioSilent");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("isSilent", i);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLivePause(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "enterBackground");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("isBackground", z);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLivePushImage(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "pushImage");
            jSONObject.put("StartTime", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("hasImage", i);
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveConnected "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveStart() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "start");
            this.mliveStartTime = System.currentTimeMillis();
            jSONObject.put("StartTime", this.mliveStartTime);
            invokeAPM(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void onLiveUpdateInfo(int i, int i2, int i3, double d, double d2, float f, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Name", "streamInfo");
            this.connectStartTime = System.currentTimeMillis();
            jSONObject.put("StartTime", this.connectStartTime);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("videobitRate", i);
            jSONObject2.put("videobitRateMax", i2);
            jSONObject2.put("videobitRateMin", i3);
            jSONObject2.put("uploadSpeed", d);
            jSONObject2.put("uploadFPS", d2);
            jSONObject2.put("cacheRate", f);
            jSONObject2.put("localIP", C0706d.m22340a());
            jSONObject.put("EventData", jSONObject2);
            invokeAPM(jSONObject);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a("onLiveUpdateInfo "), "LiveStatistics");
        }
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void release() {
        this.apmAgent.stopAPM();
        apmEventHandle = null;
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void setLiveRelated(int i, int i2, int i3, int i4) {
        C0704b c0704b = this.commData;
        c0704b.f2108c = i;
        c0704b.f2109d = i2;
        c0704b.f2110e = i4;
        c0704b.f2111f = i3;
        c0704b.f2115j = null;
    }

    @Override // com.baidu.cloud.statistics.IStatisticLive
    public void updateSession(String str) {
        this.commData.m22343a(str);
    }
}
