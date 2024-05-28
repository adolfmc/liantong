package com.baidu.rtc.logreport;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.rtc.PeerConnectionClient;
import com.baidu.rtc.config.Constraints;
import com.baidu.rtc.utils.CommonUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.webrtc.Logging;
import com.webrtc.StatsReport;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcReportHandle {
    private static final int ANTI_WEAK_REPORT_INTERVAL = 2000;
    public static final int COMMUNICATION_REPORT_INTERVAL = 2000;
    private static final String DEFAULT_REPORT_ENV = "online";
    private static final int DEVICEINFO_REPORT_INTERVAL = 300000;
    public static final int INTERNAL_STATES_INTERVAL = 200;
    private static final int SLI_REPORT_INTERVAL = 5000;
    private static final String TAG = "RtcReportHandle";
    private static RtcReportHandle instance = null;
    private static boolean mIsEnableErrorInfoMonitor = false;
    private static boolean mIsEnableInternalStatesMonitor = true;
    private static boolean mIsEnablePullQualityMonitor = true;
    private static boolean mIsEnablePushQualityMonitor;
    private String mAppId;
    private WeakReference<Context> mContext;
    private final CpuMonitor mCpuMonitor;
    private final ErrorInfoReport mErrorInfoReport;
    private String mFeedId;
    private String mHandleId;
    private Handler mHandler;
    private String mRoomId;
    private final RtcLogReport mRtcLogReport;
    private String mSessionId;
    boolean mIsDeviceInfoReporting = false;
    String mQualityMonitorEnv = "online";
    private ConcurrentHashMap<BigInteger, HUDStatistics> mHUDStatisticsMap = new ConcurrentHashMap<>();
    private BigInteger mPublisherHandle = new BigInteger("0");
    private long mUserId = -1;
    private boolean mEnableMultistream = true;
    private Runnable reportDeviceInfoRun = new Runnable() { // from class: com.baidu.rtc.logreport.RtcReportHandle.1
        @Override // java.lang.Runnable
        public void run() {
            if (RtcReportHandle.this.mRtcLogReport != null && (RtcReportHandle.mIsEnablePushQualityMonitor || RtcReportHandle.mIsEnablePullQualityMonitor)) {
                RtcReportHandle.this.reportDeviceInfo();
            }
            RtcReportHandle.this.mHandler.postDelayed(RtcReportHandle.this.reportDeviceInfoRun, 300000L);
        }
    };
    private final HandlerThread mHandlerThread = new HandlerThread("" + getClass().getSimpleName());

    private void reportAntiWeakInfo() {
    }

    public static synchronized RtcReportHandle getInstance(Context context) {
        RtcReportHandle rtcReportHandle;
        synchronized (RtcReportHandle.class) {
            if (instance == null) {
                instance = new RtcReportHandle(context);
            }
            rtcReportHandle = instance;
        }
        return rtcReportHandle;
    }

    private RtcReportHandle(Context context) {
        this.mContext = null;
        this.mContext = new WeakReference<>(context);
        this.mCpuMonitor = new CpuMonitor(context);
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mRtcLogReport = RtcLogReport.getInstance();
        this.mErrorInfoReport = ErrorInfoReport.getInstance();
        this.mErrorInfoReport.setVersion(Constraints.sdkVersion());
    }

    public void setEnableMultistream(boolean z) {
        this.mEnableMultistream = z;
    }

    public void startPeerPullReport(BigInteger bigInteger, PeerConnectionClient peerConnectionClient) {
        if (peerConnectionClient == null || bigInteger == null) {
            return;
        }
        if (mIsEnablePullQualityMonitor) {
            if (this.mHUDStatisticsMap.get(bigInteger) != null) {
                peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
                this.mHUDStatisticsMap.remove(bigInteger);
            }
            peerConnectionClient.enableStatsEvents(mIsEnablePullQualityMonitor, 2000, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
            peerConnectionClient.enableStatsEvents(mIsEnablePullQualityMonitor, 5000, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
            peerConnectionClient.enableStatsEvents(mIsEnablePullQualityMonitor, 2000, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
        }
        if (mIsEnableInternalStatesMonitor) {
            peerConnectionClient.enableStatsEvents(true, 200, bigInteger, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
        }
        HUDStatistics hUDStatistics = new HUDStatistics();
        this.mHUDStatisticsMap.put(bigInteger, hUDStatistics);
        hUDStatistics.setRequestSubscribeTime(System.currentTimeMillis());
    }

    public void stopPeerReport(BigInteger bigInteger, PeerConnectionClient peerConnectionClient) {
        if (peerConnectionClient == null || bigInteger == null || this.mHUDStatisticsMap.get(bigInteger) == null) {
            return;
        }
        this.mHUDStatisticsMap.remove(bigInteger);
        if (peerConnectionClient != null) {
            if (mIsEnablePullQualityMonitor) {
                peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT);
                peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_SLI_EVENT);
                peerConnectionClient.enableStatsEvents(false, 0, bigInteger, PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT);
            }
            if (mIsEnableInternalStatesMonitor) {
                peerConnectionClient.enableStatsEvents(true, 200, bigInteger, PeerConnectionClient.StatsEventsType.GET_AUDIOLEVEL_EVENT);
            }
        }
    }

    public HUDStatistics updateStatsData(StatsReport[] statsReportArr, BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        HUDStatistics hUDStatistics = this.mHUDStatisticsMap.get(bigInteger);
        if (hUDStatistics != null) {
            hUDStatistics.updateEncoderStatistics(statsReportArr);
        }
        return hUDStatistics;
    }

    public void onPeerStatisticsReport(PeerConnectionClient.StatsEventsType statsEventsType) {
        if ((mIsEnablePushQualityMonitor || mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
            reportCommunicationQualityInfo();
        } else if ((mIsEnablePushQualityMonitor || mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_SLI_EVENT) {
            reportSLIStuckInfo();
        } else if ((mIsEnablePushQualityMonitor || mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT) {
            reportAntiWeakInfo();
        }
    }

    public void onPeerStatisticsReport(StatsReport[] statsReportArr, BigInteger bigInteger, PeerConnectionClient.StatsEventsType statsEventsType) {
        if (bigInteger == null) {
            return;
        }
        HUDStatistics hUDStatistics = this.mHUDStatisticsMap.get(bigInteger);
        if (hUDStatistics != null) {
            hUDStatistics.updateEncoderStatistics(statsReportArr);
        }
        if ((mIsEnablePushQualityMonitor || mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_QUALITY_MONITOR_EVENT) {
            reportCommunicationQualityInfo();
        } else if ((mIsEnablePushQualityMonitor || mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_SLI_EVENT) {
            reportSLIStuckInfo();
        } else if ((mIsEnablePushQualityMonitor || mIsEnablePullQualityMonitor) && statsEventsType == PeerConnectionClient.StatsEventsType.GET_ANTI_WEAK_EVENT) {
            reportAntiWeakInfo();
        }
    }

    public void startDeviceInfoReport() {
        Handler handler = this.mHandler;
        if (handler == null || this.mIsDeviceInfoReporting) {
            return;
        }
        this.mIsDeviceInfoReporting = true;
        handler.post(this.reportDeviceInfoRun);
    }

    public void updateRoomInfo(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mAppId = str;
        this.mRoomId = str2;
        if (!TextUtils.isEmpty(str3)) {
            try {
                this.mUserId = Long.valueOf(str3).longValue();
            } catch (NumberFormatException e) {
                this.mUserId = -1L;
                Logging.m5304e("RtcReportHandle", "UserID must be number format " + e.getMessage());
            }
        }
        this.mFeedId = str4;
        this.mHandleId = str5;
        this.mSessionId = str6;
        this.mErrorInfoReport.setAppId(this.mAppId);
        this.mErrorInfoReport.setEnv("online");
        this.mErrorInfoReport.setRoomId(CommonUtils.strToLong(this.mRoomId));
        this.mErrorInfoReport.setUserId(this.mUserId);
        this.mErrorInfoReport.setSessionId(CommonUtils.strToLong(this.mSessionId));
        Logging.m5305d("RtcReportHandle", "update room info : userId = " + this.mUserId + " handleId =" + this.mHandleId);
    }

    public static void enableMonitor(boolean z, boolean z2, boolean z3, boolean z4) {
        mIsEnableInternalStatesMonitor = z;
        mIsEnablePullQualityMonitor = z2;
        mIsEnableErrorInfoMonitor = z3;
        mIsEnablePushQualityMonitor = z4;
    }

    public String getStreamingStatesInfo(BigInteger bigInteger, int i) {
        HUDStatistics hUDStatistics;
        return (bigInteger == null || (hUDStatistics = this.mHUDStatisticsMap.get(bigInteger)) == null) ? "null" : hUDStatistics.statsString(i);
    }

    public HUDStatistics getStreamingStats(BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        return this.mHUDStatisticsMap.get(bigInteger);
    }

    public boolean streamingValidityDetect(BigInteger bigInteger) {
        if (bigInteger == null) {
            return false;
        }
        HUDStatistics hUDStatistics = this.mHUDStatisticsMap.get(bigInteger);
        if (hUDStatistics != null) {
            if ((hUDStatistics.hasVideo() && hUDStatistics.getVideoRecvBitrateTracker().getBytesDelta() == 0) || (hUDStatistics.hasAudio() && hUDStatistics.getAudioRecvBitrateTracker().getBytesDelta() == 0)) {
                Logging.m5305d("RtcReportHandle", "No streaming date received in current period, hasVideo: " + hUDStatistics.hasVideo() + " hasAudio: " + hUDStatistics.hasAudio());
                return false;
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportDeviceInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdkVersion", Constraints.sdkVersion());
            jSONObject2.put("networkType", RtcLogReport.getNetworkType(this.mContext.get()));
            jSONObject2.put("device", RtcLogReport.getDeviceModel());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("deviceInfo", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            jSONObject.put("roomId", this.mRoomId);
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject3);
        } catch (JSONException e) {
            Logging.m5304e("RtcReportHandle", "Caught error on reportDeviceInfo: " + e);
        }
        reportData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 0);
    }

    private void reportCommunicationQualityInfo() {
        boolean z;
        HUDStatistics hUDStatistics;
        HashMap hashMap = new HashMap();
        HUDStatistics hUDStatistics2 = this.mHUDStatisticsMap.get(this.mPublisherHandle);
        if (hUDStatistics2 == null) {
            z = false;
        } else {
            hUDStatistics2.getStatsSendInfo(hashMap);
            z = true;
        }
        if (hashMap.size() == 0) {
            z = false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("duration", 2);
            JSONObject jSONObject3 = new JSONObject();
            if (this.mCpuMonitor != null) {
                jSONObject3.put("sysCpuUsage", this.mCpuMonitor.getFrequencyScaleAverage());
            }
            jSONObject3.put("appCpuUsage", 0);
            jSONObject2.put("resourceUsageInfo", jSONObject3);
            if (mIsEnablePushQualityMonitor) {
                JSONObject jSONObject4 = new JSONObject();
                if (z) {
                    jSONObject4.put("bitrate", hashMap.get("bitrate_s"));
                    jSONObject4.put("abps", hashMap.get("bitrate_audio_s"));
                    jSONObject4.put("audioEnergy", hashMap.get("audio_energy_s"));
                    jSONObject4.put("audioLevel", hashMap.get("audio_input_level_s"));
                    jSONObject4.put("packetloss", hashMap.get("packetloss_s"));
                    jSONObject4.put("cfps", hashMap.get("fps_s"));
                    jSONObject4.put("fps", hashMap.get("fps_i"));
                    if (hUDStatistics2 == null) {
                        jSONObject4.put("resolution", "");
                    } else {
                        jSONObject4.put("resolution", hUDStatistics2.getSendResolution());
                    }
                } else {
                    jSONObject4.put("bitrate", 0);
                    jSONObject4.put("packetloss", 0);
                    jSONObject4.put("fps", 0);
                    jSONObject4.put("resolution", "");
                }
                jSONObject2.put("senderQualityInfo", jSONObject4);
            }
            if (mIsEnablePullQualityMonitor) {
                JSONArray jSONArray = new JSONArray();
                for (BigInteger bigInteger : this.mHUDStatisticsMap.keySet()) {
                    if (bigInteger != this.mPublisherHandle && (hUDStatistics = this.mHUDStatisticsMap.get(bigInteger)) != null) {
                        HashMap hashMap2 = new HashMap();
                        hUDStatistics.getStatsRecvInfo(hashMap2);
                        if (hashMap2.size() != 0) {
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("feedId", this.mFeedId);
                            jSONObject5.put("bitrate", hashMap2.get("bitrate_r"));
                            jSONObject5.put("abps", hashMap2.get("bitrate_audio_r"));
                            jSONObject5.put("audioEnergy", hashMap2.get("audio_energy_r"));
                            jSONObject5.put("audioLevel", hashMap2.get("audio_input_level_r"));
                            jSONObject5.put("packetloss", hashMap2.get("packetloss_r"));
                            jSONObject5.put("fps", hashMap2.get("fps_r"));
                            jSONObject5.put("resolution", hUDStatistics.getRecvResolution());
                            jSONArray.put(jSONObject5);
                        }
                    }
                }
                jSONObject2.put("receiverQualityInfoList", jSONArray);
            }
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("communicationQualityInfo", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            jSONObject.put("roomId", this.mRoomId);
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject6);
        } catch (JSONException e) {
            Logging.m5304e("RtcReportHandle", "Caught error on reportCommunicationQualityInfo: " + e);
        }
        reportData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 2);
    }

    public void onFfDelayChange(BigInteger bigInteger) {
        HUDStatistics hUDStatistics;
        if (!mIsEnablePullQualityMonitor || (hUDStatistics = this.mHUDStatisticsMap.get(bigInteger)) == null) {
            return;
        }
        hUDStatistics.setFirstFrameTime(System.currentTimeMillis());
        reportSLIFfDelay(this.mUserId, hUDStatistics.getFirstFrameTime() - hUDStatistics.getRequestSubscribeTime());
    }

    public void reportFirstFrameConsume(long j) {
        if (mIsEnablePullQualityMonitor) {
            reportSLIFfDelay(this.mUserId, j);
        }
    }

    public void reportSLIFfDelay(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject2.put("feedId", j);
            jSONObject4.put("duration", j2);
            jSONObject2.put("ffDelay", jSONObject4);
            jSONObject3.put("sli", jSONObject2);
            jSONObject.put("env", this.mQualityMonitorEnv);
            jSONObject.put("appId", this.mAppId);
            jSONObject.put("roomId", this.mRoomId);
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("userId", this.mUserId);
            jSONObject.put("message", jSONObject3);
        } catch (JSONException e) {
            Logging.m5304e("RtcReportHandle", "Caught error on reportDeviceInfo: " + e);
        }
        Logging.m5305d("RtcReportHandle", "report first frame rendered time :" + j2);
        reportData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 3);
    }

    private void reportSLIStuckInfo() {
        HUDStatistics hUDStatistics;
        if (mIsEnablePullQualityMonitor) {
            for (BigInteger bigInteger : this.mHUDStatisticsMap.keySet()) {
                if (bigInteger != this.mPublisherHandle && (hUDStatistics = this.mHUDStatisticsMap.get(bigInteger)) != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("feedId", this.mFeedId);
                        JSONArray jSONArray = new JSONArray();
                        JSONArray jSONArray2 = new JSONArray();
                        HashMap hashMap = new HashMap();
                        hUDStatistics.getSlIStuckData(hashMap);
                        ArrayList<Long> arrayList = hashMap.get("aStuck");
                        if (arrayList.size() == 0) {
                            jSONArray.put(0);
                        } else {
                            for (int i = 0; i < arrayList.size(); i++) {
                                jSONArray.put(arrayList.get(i));
                            }
                        }
                        ArrayList<Long> arrayList2 = hashMap.get("vStuck");
                        if (arrayList2.size() == 0) {
                            jSONArray2.put(0);
                        } else {
                            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                                jSONArray2.put(arrayList2.get(i2));
                            }
                        }
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("durations", jSONArray);
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("durations", jSONArray2);
                        jSONObject3.put("astuck", jSONObject4);
                        jSONObject3.put("vstuck", jSONObject5);
                        hUDStatistics.clearSLIStuckData();
                        JSONObject jSONObject6 = new JSONObject();
                        if (hUDStatistics.getEndTOEndTime() > 0) {
                            jSONObject6.put("duration", hUDStatistics.getEndTOEndTime());
                            jSONObject3.put("e2eDelay", jSONObject6);
                        }
                        jSONObject2.put("sli", jSONObject3);
                        jSONObject.put("env", this.mQualityMonitorEnv);
                        jSONObject.put("appId", this.mAppId);
                        jSONObject.put("roomId", this.mRoomId);
                        jSONObject.put("timestamp", System.currentTimeMillis());
                        jSONObject.put("userId", this.mUserId);
                        jSONObject.put("message", jSONObject2);
                    } catch (JSONException e) {
                        Logging.m5304e("RtcReportHandle", "Caught error on reportDeviceInfo: " + e);
                    }
                    reportData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 3);
                }
            }
        }
    }

    private void reportData(String str, int i) {
        RtcLogReport rtcLogReport = this.mRtcLogReport;
        if (rtcLogReport != null) {
            rtcLogReport.report(str, i);
        }
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioStuckEvent implements SLIReportInterface {
        public AudioStuckEvent() {
        }

        @Override // com.baidu.rtc.logreport.SLIReportInterface
        public void onStuckData(long j, long j2) {
            if (RtcReportHandle.mIsEnablePullQualityMonitor) {
                for (Map.Entry entry : RtcReportHandle.this.mHUDStatisticsMap.entrySet()) {
                    if (entry.getKey() != RtcReportHandle.this.mPublisherHandle && entry.getValue() != null) {
                        ((HUDStatistics) entry.getValue()).addAudioStuckData(j, j2);
                    }
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class VideoStuckEvent implements SLIReportInterface {
        private BigInteger handleId;

        public VideoStuckEvent(BigInteger bigInteger) {
            this.handleId = bigInteger;
        }

        @Override // com.baidu.rtc.logreport.SLIReportInterface
        public void onStuckData(long j, long j2) {
            HUDStatistics hUDStatistics;
            if (!RtcReportHandle.mIsEnablePullQualityMonitor || this.handleId == null || (hUDStatistics = (HUDStatistics) RtcReportHandle.this.mHUDStatisticsMap.get(this.handleId)) == null) {
                return;
            }
            hUDStatistics.addVideoStuckData(j, j2);
        }
    }

    public void reportError(int i, String str) {
        ErrorInfoReport errorInfoReport = this.mErrorInfoReport;
        if (errorInfoReport != null) {
            errorInfoReport.reportErrorInfo(i, str, CommonUtils.strToLong(this.mFeedId), CommonUtils.strToLong(this.mHandleId));
        }
    }

    public void release() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.reportDeviceInfoRun);
        }
        instance = null;
    }
}
