package com.baidu.rtc.logreport;

import android.text.TextUtils;
import com.webrtc.Logging;
import com.webrtc.StatsReport;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HUDStatistics {
    public static final int FLAG_STATES_STREAMING_ALL = 63;
    public static final int FLAG_STATES_STREAMING_AV = 15;
    public static final int FLAG_STATES_STREAMING_BWE = 32;
    public static final int FLAG_STATES_STREAMING_CONNECTION = 16;
    public static final int FLAG_STATES_STREAMING_FIRST_FRAME = 128;
    public static final int FLAG_STATES_STREAMING_RECV = 3;
    public static final int FLAG_STATES_STREAMING_RECV_AUDIO = 1;
    public static final int FLAG_STATES_STREAMING_RECV_VIDEO = 2;
    public static final int FLAG_STATES_STREAMING_SEND = 12;
    public static final int FLAG_STATES_STREAMING_SEND_AUDIO = 4;
    public static final int FLAG_STATES_STREAMING_SEND_VIDEO = 8;
    private static final String TAG = "WebSocketChannel";
    private static int mLastCpuCoreCount = -1;
    private boolean enableMultistream;
    private long firstFrameTime;
    public String mActualEncBitrate;
    private double mActualEncBitrateValue;
    public String mAudioCurrentDelay;
    public double mAudioEnergy;
    public String mAudioExpandRate;
    public int mAudioInputLevel;
    public int mAudioJitterBufferMs;
    public String mAudioRecvBitrate;
    public String mAudioRecvCodec;
    public double mAudioRecvEnergy;
    public int mAudioRecvLevel;
    public String mAudioSendBitrate;
    public String mAudioSendCodec;
    public String mAvailableRevBW;
    private double mAvailableRevBWValue;
    public String mAvailableSendBW;
    private double mAvailableSendBWValue;
    public String mBucketDelay;
    private double mBucketDelayValue;
    public String mConnRecvBitrate;
    public String mConnRtt;
    public String mConnSendBitrate;
    public String mEndToEndTime;
    public BigInteger mHandleId;
    public boolean mIsPublisher;
    public String mLocalCandType;
    public String mNacksRecv;
    private long mNacksRecvPerMil;
    public String mNacksSent;
    public int mOldFrameEncoded;
    public int mOldVideoQPSum;
    public String mPlisRecv;
    private long mPlisRecvPerMil;
    public String mPlisSent;
    public String mRemoteCandType;
    public String mRemoteIp;
    public String mRetransmitBitrate;
    private double mRetransmitBitrateValue;
    public String mTargetEncBitrate;
    private double mTargetEncBitrateValue;
    public String mTransPortType;
    public String mTransmitBitrate;
    private double mTransmitBitrateValue;
    public String mVideoDecodFps;
    public String mVideoDecodeMs;
    public String mVideoEncodeMs;
    public String mVideoInputFps;
    public String mVideoInputHeight;
    public String mVideoInputWidth;
    public String mVideoOutputFps;
    public String mVideoPacketRecv;
    public String mVideoPacketSend;
    public String mVideoRecvBitrate;
    public String mVideoRecvFps;
    public String mVideoRecvHeight;
    public int mVideoRecvPacketLostRatio;
    public String mVideoRecvWidth;
    public String mVideoSendBitrate;
    public String mVideoSendCodec;
    public String mVideoSendFps;
    public String mVideoSendHeight;
    public String mVideoSendPacketLost;
    public String mVideoSendWidth;
    private long requestSubscribeTime;
    public String mVideoRecvPacketLost = "0";
    public String mVideoRecvFrameFrozenPercent = "0";
    public String mVideoRecvFrameRenderDelay = "0";
    private ArrayList<Long> audioStuckList = new ArrayList<>();
    private ArrayList<Long> videoStuckList = new ArrayList<>();
    private long mVideoTotalStuckMs = 0;
    private long mAudioTotalStuckMs = 0;
    public ArrayList<String> ssrcs = new ArrayList<>();
    private int[] deltaResult = new int[RtcRenderStep.RTC_RENDER_STEP_MAX.ordinal()];
    private long[] deltaStateMs = new long[RtcRenderStep.RTC_RENDER_STEP_MAX.ordinal()];
    private String[] deltaStateTips = {"start", "sdkd", "setupd", "wssd", "coming", "subscribe", "remote", "local", "webrtcup", "ffplay"};
    public int mVideoQPSum = 0;
    public int mFrameEncoded = 0;
    public int mWifiRtt = 0;
    public int mWanRtt = 0;
    RTCBitrateTracker mAudioRecvBitrateTracker = new RTCBitrateTracker();
    RTCBitrateTracker mAudioSendBitrateTracker = new RTCBitrateTracker();
    RTCBitrateTracker mConnSendBitrateTradker = new RTCBitrateTracker();
    RTCBitrateTracker mConnRecvBitrateTracker = new RTCBitrateTracker();
    RTCBitrateTracker mVideoRecvBitrateTracker = new RTCBitrateTracker();
    RTCBitrateTracker mVideoSendBitrateTracker = new RTCBitrateTracker();
    private boolean mHasVideo = false;
    private boolean mHasAudio = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcRenderStep {
        RTC_RENDER_STEP_START,
        RTC_RENDER_STEP_SDK,
        RTC_RENDER_STEP_SETUPD,
        RTC_RENDER_STEP_WEBSOCKET_OPENED,
        RTC_RENDER_STEP_USER_JOINED,
        RTC_RENDER_STEP_SUBSCRIBE,
        RTC_RENDER_STEP_SET_REMOTE_DESCRIPTION,
        RTC_RENDER_STEP_SET_LOCAL_DESCRIPTION,
        RTC_RENDER_STEP_RWEBRTCUP,
        RTC_RENDER_STEP_FIRST_FRAME,
        RTC_RENDER_STEP_MAX
    }

    public HUDStatistics() {
        for (int i = 0; i < RtcRenderStep.RTC_RENDER_STEP_MAX.ordinal(); i++) {
            this.deltaStateMs[i] = 0;
            this.deltaResult[i] = -1;
        }
    }

    public RTCBitrateTracker getVideoRecvBitrateTracker() {
        return this.mVideoRecvBitrateTracker;
    }

    public RTCBitrateTracker getAudioRecvBitrateTracker() {
        return this.mAudioRecvBitrateTracker;
    }

    public boolean hasVideo() {
        return this.mHasVideo;
    }

    public boolean hasAudio() {
        return this.mHasAudio;
    }

    public void setEnableMultistream(boolean z) {
        this.enableMultistream = z;
    }

    private void statsFF(StringBuilder sb, int i) {
        if (i <= RtcRenderStep.RTC_RENDER_STEP_START.ordinal() || i >= RtcRenderStep.RTC_RENDER_STEP_MAX.ordinal() || this.deltaResult[i] == -1) {
            return;
        }
        sb.append(this.deltaStateTips[i] + ":");
        int[] iArr = this.deltaResult;
        if (iArr[i] == 0) {
            sb.append(this.deltaStateMs[i]);
            if (i > RtcRenderStep.RTC_RENDER_STEP_START.ordinal() + 1) {
                sb.append("(");
                long[] jArr = this.deltaStateMs;
                sb.append(jArr[i] - jArr[i - 1]);
                sb.append(")");
            }
            sb.append("ms ");
        } else if (iArr[i] == 1) {
            sb.append("start... ");
        } else if (iArr[i] == 2) {
            sb.append("fail... ");
        }
    }

    public void setDeltaStateMs(int i, int i2, long j) {
        String str;
        if (i <= RtcRenderStep.RTC_RENDER_STEP_START.ordinal() || i >= RtcRenderStep.RTC_RENDER_STEP_MAX.ordinal()) {
            return;
        }
        this.deltaResult[i] = i2;
        this.deltaStateMs[i] = j;
        String str2 = "delta:" + this.deltaStateTips[i] + "@" + j;
        if (i > RtcRenderStep.RTC_RENDER_STEP_START.ordinal() + 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append("(");
            long[] jArr = this.deltaStateMs;
            sb.append(jArr[i] - jArr[i - 1]);
            sb.append("ms)");
            str = sb.toString();
        } else {
            str = str2 + "ms";
        }
        Logging.m5305d("WebSocketChannel", str);
    }

    public String statsString(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 16) != 0) {
            sb.append("Conn: (rtt)");
            sb.append(this.mConnRtt);
            sb.append(" ms | remoteIp ->");
            sb.append(this.mRemoteIp);
            sb.append(" | cand");
            sb.append(this.mLocalCandType);
            sb.append("->");
            sb.append(this.mRemoteCandType);
            sb.append("/");
            sb.append(this.mTransPortType);
            sb.append("  |  (sendBr)");
            sb.append(this.mConnSendBitrate);
            sb.append("  |  (recvBr)");
            sb.append(this.mConnRecvBitrate);
            sb.append("\n");
        }
        if ((i & 32) != 0) {
            sb.append("BWE: (AEncBr)");
            sb.append(this.mActualEncBitrate);
            sb.append("/(TEncBr)");
            sb.append(this.mTargetEncBitrate);
            sb.append(" | (sendRate) ");
            sb.append(this.mVideoSendBitrate);
            sb.append("/(BWE)");
            sb.append(this.mAvailableSendBW);
            sb.append(" | (TransBr) ");
            sb.append(this.mTransmitBitrate);
            sb.append(" | (RetransBr) ");
            sb.append(this.mRetransmitBitrate);
            sb.append(" | (BktDelay) ");
            sb.append(this.mBucketDelay);
            sb.append("ms");
            sb.append("\n");
        }
        if ((i & 8) != 0) {
            int calculateAvgQP = calculateAvgQP();
            sb.append("VideoCap: (input)");
            sb.append(this.mVideoInputWidth);
            sb.append("x");
            sb.append(this.mVideoInputHeight);
            sb.append("@");
            sb.append(this.mVideoInputFps);
            sb.append("fps | (send) ");
            sb.append(this.mVideoSendWidth);
            sb.append("X");
            sb.append(this.mVideoSendHeight);
            sb.append("@");
            sb.append(this.mVideoSendFps);
            sb.append("fps\n");
            sb.append("VideoEnc: ");
            sb.append(this.mVideoSendCodec);
            sb.append(" | (encMs)");
            sb.append(this.mVideoEncodeMs);
            sb.append("ms | (lost)");
            sb.append(this.mVideoSendPacketLost);
            sb.append(" / (sendPacks)");
            sb.append(this.mVideoPacketSend);
            sb.append(" | (nacksRecv)");
            sb.append(this.mNacksRecv);
            sb.append(" | (plisRecv)");
            sb.append(this.mPlisRecv);
            sb.append("\n");
            sb.append("AvgQP (past ");
            sb.append(this.mFrameEncoded);
            sb.append(" encoded frames) = ");
            sb.append(calculateAvgQP);
            sb.append("\n");
        }
        if ((i & 2) != 0) {
            if (this.enableMultistream) {
                sb.append("UserId: (recv)");
                sb.append(this.mHandleId);
                sb.append("\n");
            } else {
                sb.append("HandleId: (recv)");
                sb.append(this.mHandleId);
                sb.append("\n");
            }
            sb.append("VideoRecv: (recv)");
            sb.append(this.mVideoRecvWidth);
            sb.append("X");
            sb.append(this.mVideoRecvHeight);
            sb.append("@");
            sb.append(this.mVideoRecvFps);
            sb.append(" frozen:");
            sb.append((Integer.parseInt(this.mVideoRecvFrameFrozenPercent) * 1.0f) / 100.0f);
            sb.append("%");
            sb.append(" rdelay:");
            sb.append(this.mVideoRecvFrameRenderDelay);
            sb.append("ms");
            sb.append(" fps  |  (deocded) ");
            sb.append(this.mVideoDecodFps);
            sb.append("  |  (loss)");
            sb.append(this.mVideoRecvPacketLost);
            sb.append("  / ");
            sb.append(this.mVideoPacketRecv);
            sb.append("  / ");
            sb.append(this.mVideoRecvPacketLostRatio);
            sb.append(" perMil |  (output)");
            sb.append(this.mVideoOutputFps);
            sb.append("fps  |  (recvBr)");
            sb.append(this.mVideoRecvBitrate);
            sb.append("/ (recvBWE)");
            sb.append(this.mAvailableRevBW);
            sb.append("  | (decMs)");
            sb.append(this.mVideoDecodeMs);
            sb.append("ms | (nacksSent)");
            sb.append(this.mNacksSent);
            sb.append(" | (plisSent)");
            sb.append(this.mPlisSent);
            sb.append(" | (e2e)");
            sb.append(this.mEndToEndTime);
            sb.append("ms");
            sb.append("\n");
        }
        if ((i & 4) != 0) {
            sb.append("AudioOutput: ");
            sb.append(this.mAudioSendBitrate);
            sb.append(" | ");
            sb.append(this.mAudioSendCodec);
            sb.append("\n");
        }
        if ((i & 1) != 0) {
            sb.append("AudioRecv: (recvBr)");
            sb.append(this.mAudioRecvBitrate);
            sb.append(" | ");
            sb.append(this.mAudioRecvCodec);
            sb.append(" | (delay)");
            sb.append(this.mAudioCurrentDelay);
            sb.append("ms | (expandrate)");
            sb.append(this.mAudioExpandRate);
            sb.append("| (Jittbuf)");
            sb.append(this.mAudioJitterBufferMs);
            sb.append("ms");
            sb.append("\n");
        }
        if ((i & 128) != 0) {
            sb.append("\n");
            int ordinal = RtcRenderStep.RTC_RENDER_STEP_START.ordinal();
            while (true) {
                ordinal++;
                if (ordinal >= RtcRenderStep.RTC_RENDER_STEP_MAX.ordinal()) {
                    break;
                }
                statsFF(sb, ordinal);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void getStatsSendInfo(Map<String, Integer> map) {
        map.put("audio_energy_s", Integer.valueOf((int) (this.mAudioEnergy * 1000.0d)));
        map.put("audio_input_level_s", Integer.valueOf(this.mAudioInputLevel));
        map.put("bitrate_audio_s", Integer.valueOf(RTCBitrateTracker.bitrateToString(this.mAudioSendBitrate) / 1000));
        String str = this.mVideoSendBitrate;
        if (str == null || this.mVideoSendFps == null || this.mVideoInputFps == null || this.mVideoSendHeight == null || map == null) {
            Logging.m5304e("HUDStatistics", "getStatsSendInfo null");
            return;
        }
        map.put("bitrate_s", Integer.valueOf(RTCBitrateTracker.bitrateToString(str) / 1000));
        map.put("bitrate_audio_s", Integer.valueOf(RTCBitrateTracker.bitrateToString(this.mAudioSendBitrate) / 1000));
        map.put("audio_energy_s", Integer.valueOf((int) (this.mAudioEnergy * 1000.0d)));
        map.put("audio_input_level_s", Integer.valueOf(this.mAudioInputLevel));
        try {
            Long l = new Long(0L);
            if (Long.parseLong(this.mVideoPacketSend) != 0) {
                l = Long.valueOf((Long.parseLong(this.mVideoSendPacketLost) * 1000) / Long.parseLong(this.mVideoPacketSend));
            }
            map.put("packetlost", Integer.valueOf(Integer.parseInt(this.mVideoSendPacketLost)));
            map.put("packesend", Integer.valueOf(Integer.parseInt(this.mVideoPacketSend)));
            map.put("packetloss_s", Integer.valueOf(l.intValue()));
            map.put("fps_s", Integer.valueOf(this.mVideoSendFps));
            map.put("fps_i", Integer.valueOf(this.mVideoInputFps));
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistics", "getStatsSendInfo: " + e);
        }
    }

    public String getSendResolution() {
        return this.mVideoSendWidth + "*" + this.mVideoSendHeight;
    }

    public String getRecvResolution() {
        return this.mVideoRecvWidth + "*" + this.mVideoRecvHeight;
    }

    public void getStatsRecvInfo(Map<String, Integer> map) {
        if (this.mVideoRecvBitrate == null || this.mVideoRecvPacketLost == null || this.mVideoRecvFps == null || map == null) {
            Logging.m5304e("HUDStatistics", "getStatsRecvInfo null");
            return;
        }
        try {
            new Long(0L);
            if (Long.parseLong(this.mVideoPacketRecv) != 0) {
                this.mVideoRecvPacketLostRatio = Long.valueOf((Long.parseLong(this.mVideoRecvPacketLost) * 1000) / Long.parseLong(this.mVideoPacketRecv)).intValue();
            }
            map.put("bitrate_r", Integer.valueOf(RTCBitrateTracker.bitrateToString(this.mVideoRecvBitrate) / 1000));
            map.put("bitrate_audio_r", Integer.valueOf(RTCBitrateTracker.bitrateToString(this.mAudioRecvBitrate) / 1000));
            map.put("audio_energy_r", Integer.valueOf((int) (this.mAudioRecvEnergy * 1000.0d)));
            map.put("audio_input_level_r", Integer.valueOf(this.mAudioRecvLevel));
            map.put("packetlost", Integer.valueOf(Integer.parseInt(this.mVideoRecvPacketLost)));
            map.put("packetloss_r", Integer.valueOf(this.mVideoRecvPacketLostRatio));
            map.put("fps_r", Integer.valueOf(this.mVideoRecvFps));
            long currentTimeMillis = System.currentTimeMillis() - this.requestSubscribeTime;
            int i = (currentTimeMillis > 0L ? 1 : (currentTimeMillis == 0L ? 0 : -1));
            map.put("vstuck", Integer.valueOf((int) ((this.mVideoTotalStuckMs * 100) / (i > 0 ? currentTimeMillis : 1L))));
            long j = this.mAudioTotalStuckMs * 100;
            if (i <= 0) {
                currentTimeMillis = 1;
            }
            map.put("astuck", Integer.valueOf((int) (j / currentTimeMillis)));
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistics", "getStatsRecvInfo: " + e);
        }
    }

    public JSONObject getSentBweInfoJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("estBw", this.mAvailableSendBWValue);
            jSONObject.put("sentTargetBitRate", this.mTargetEncBitrateValue);
            long j = 0;
            jSONObject.put("sentRtt", TextUtils.isEmpty(this.mConnRtt) ? 0L : Long.parseLong(this.mConnRtt));
            jSONObject.put("audioBps", this.mAudioSendBitrateTracker == null ? 0.0d : this.mAudioSendBitrateTracker.getBitrate().doubleValue());
            jSONObject.put("encBitrate", this.mActualEncBitrateValue);
            jSONObject.put("retransBitrate", this.mRetransmitBitrateValue);
            jSONObject.put("transBitrate", this.mTransmitBitrateValue);
            jSONObject.put("nackRecv", TextUtils.isEmpty(this.mNacksRecv) ? 0L : Long.parseLong(this.mNacksRecv));
            if (!TextUtils.isEmpty(this.mPlisRecv)) {
                j = Long.parseLong(this.mPlisRecv);
            }
            jSONObject.put("plisRecv", j);
            jSONObject.put("bucketDelay", this.mBucketDelayValue);
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistics", "getSentInfoJson : " + e);
        } catch (JSONException e2) {
            Logging.m5304e("HUDStatistics", "getSentInfoJson : " + e2);
        }
        return jSONObject;
    }

    public JSONObject getRecvBweInfoJson(BigInteger bigInteger) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("feedId", bigInteger);
            jSONObject.put("downlinkDelay", 0);
            jSONObject.put("framingDelay", 0);
            jSONObject.put("estBwRecv", this.mAvailableRevBWValue);
        } catch (JSONException e) {
            Logging.m5304e("HUDStatistics", "getStatsRecvInfo: " + e);
        }
        return jSONObject;
    }

    public JSONObject getSysInfoJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("gatewayRtt", this.mWifiRtt);
            jSONObject.put("wanRtt", this.mWanRtt);
        } catch (JSONException e) {
            Logging.m5304e("HUDStatistics", "getSysInfoJson: " + e);
        }
        return jSONObject;
    }

    public void getSlIStuckData(Map<String, ArrayList<Long>> map) {
        map.put("aStuck", this.audioStuckList);
        map.put("vStuck", this.videoStuckList);
    }

    public void clearSLIStuckData() {
        this.audioStuckList.clear();
        this.videoStuckList.clear();
    }

    public int getEndTOEndTime() {
        try {
            if (this.mEndToEndTime != null && this.mEndToEndTime != "") {
                return Integer.valueOf(this.mEndToEndTime).intValue();
            }
            return -1;
        } catch (NumberFormatException unused) {
            Logging.m5304e("HUDStatistics", "getEndTOEndTime error format :" + this.mEndToEndTime);
            return -1;
        }
    }

    public void setRequestSubscribeTime(long j) {
        this.requestSubscribeTime = j;
    }

    public long getRequestSubscribeTime() {
        return this.requestSubscribeTime;
    }

    public void setFirstFrameTime(long j) {
        this.firstFrameTime = j;
    }

    public long getFirstFrameTime() {
        return this.firstFrameTime;
    }

    public void addVideoStuckData(long j, long j2) {
        long j3 = j2 - j;
        this.videoStuckList.add(Long.valueOf(j3));
        this.mVideoTotalStuckMs += j3;
    }

    public void addAudioStuckData(long j, long j2) {
        long j3 = j2 - j;
        this.audioStuckList.add(Long.valueOf(j3));
        this.mAudioTotalStuckMs += j3;
    }

    private int calculateAvgQP() {
        int i = this.mFrameEncoded - this.mOldFrameEncoded;
        int i2 = this.mVideoQPSum - this.mOldVideoQPSum;
        if (i != 0) {
            return i2 / i;
        }
        return 0;
    }

    private Map<String, String> getReportMap(StatsReport statsReport) {
        StatsReport.Value[] valueArr;
        HashMap hashMap = new HashMap();
        for (StatsReport.Value value : statsReport.values) {
            hashMap.put(value.name, value.value);
        }
        return hashMap;
    }

    private void parseVideoSendStatsReport(Map<String, String> map) {
        this.mVideoEncodeMs = map.get("googAvgEncodeMs");
        this.mVideoInputFps = map.get("googFrameRateInput");
        this.mVideoInputHeight = map.get("googFrameHeightInput");
        this.mVideoInputWidth = map.get("googFrameWidthInput");
        this.mVideoSendFps = map.get("googFrameRateSent");
        this.mNacksRecv = map.get("googNacksReceived");
        this.mPlisRecv = map.get("googPlisReceived");
        this.mVideoSendCodec = map.get("googCodecName");
        this.mVideoSendWidth = map.get("googFrameWidthSent");
        this.mVideoSendHeight = map.get("googFrameHeightSent");
        this.mVideoSendPacketLost = map.get("packetsLost");
        this.mVideoPacketSend = map.get("packetsSent");
        try {
            String str = map.get("bytesSent");
            if (str != null) {
                this.mVideoSendBitrateTracker.updataBitrateWidhCurrentByteCount(Integer.parseInt(str));
            }
            this.mVideoSendBitrate = this.mVideoSendBitrateTracker.bitRateString();
            this.mOldVideoQPSum = this.mVideoQPSum;
            String str2 = map.get("qpSum");
            if (str2 != null) {
                this.mVideoQPSum = Integer.parseInt(str2);
            }
            this.mOldFrameEncoded = this.mFrameEncoded;
            String str3 = map.get("framesEncoded");
            if (str3 != null) {
                this.mFrameEncoded = Integer.parseInt(str3);
            }
            if (Long.parseLong(this.mVideoPacketSend) != 0) {
                long parseLong = Long.parseLong(this.mVideoPacketSend);
                this.mNacksRecvPerMil = (Long.parseLong(this.mNacksRecv) * 1000) / parseLong;
                this.mPlisRecvPerMil = (Long.parseLong(this.mPlisRecv) * 1000) / parseLong;
            }
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistic", "parseVideoSendStatsReport: " + e);
        }
    }

    private void parseBweStatsReport(Map<String, String> map) {
        try {
            this.mTargetEncBitrateValue = Double.parseDouble(map.get("googTargetEncBitrate"));
            this.mTargetEncBitrate = RTCBitrateTracker.bitrateStringForBitrate(this.mTargetEncBitrateValue);
            this.mActualEncBitrateValue = Double.parseDouble(map.get("googActualEncBitrate"));
            this.mActualEncBitrate = RTCBitrateTracker.bitrateStringForBitrate(this.mActualEncBitrateValue);
            this.mAvailableSendBWValue = Double.parseDouble(map.get("googAvailableSendBandwidth"));
            this.mAvailableSendBW = RTCBitrateTracker.bitrateStringForBitrate(this.mAvailableSendBWValue);
            this.mAvailableRevBWValue = Double.parseDouble(map.get("googAvailableReceiveBandwidth"));
            this.mAvailableRevBW = RTCBitrateTracker.bitrateStringForBitrate(this.mAvailableRevBWValue);
            this.mRetransmitBitrateValue = Double.parseDouble(map.get("googRetransmitBitrate"));
            this.mRetransmitBitrate = RTCBitrateTracker.bitrateStringForBitrate(this.mRetransmitBitrateValue);
            this.mTransmitBitrateValue = Double.parseDouble(map.get("googTransmitBitrate"));
            this.mTransmitBitrate = RTCBitrateTracker.bitrateStringForBitrate(this.mTransmitBitrateValue);
            this.mBucketDelay = map.get("googBucketDelay");
            this.mBucketDelayValue = Double.parseDouble(this.mBucketDelay);
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistics", "parseBweStatsReport: " + e);
        }
    }

    private void parseConnectionStatsReport(Map<String, String> map) {
        String str;
        String str2 = map.get("googActiveConnection");
        if (str2 == null || !str2.equals("true")) {
            return;
        }
        try {
            this.mConnRecvBitrateTracker.updataBitrateWidhCurrentByteCount(Long.parseLong(map.get("bytesReceived")));
            this.mConnRecvBitrate = this.mConnRecvBitrateTracker.bitRateString();
            this.mConnSendBitrateTradker.updataBitrateWidhCurrentByteCount(Long.parseLong(map.get("bytesSent")));
            this.mConnSendBitrate = this.mConnSendBitrateTradker.bitRateString();
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistics", "parseConnectionStatsReport" + e);
        }
        this.mConnRtt = map.get("googRtt");
        this.mLocalCandType = map.get("googLocalCandidateType");
        this.mRemoteCandType = map.get("googRemoteCandidateType");
        this.mTransPortType = map.get("googTransportType");
        if (map.containsKey("googLocalAddress") && (str = map.get("googLocalAddress").split(":")[0]) != null) {
            ErrorInfoReport.getInstance().setClientIp(str);
        }
        if (map.containsKey("googRemoteAddress")) {
            this.mRemoteIp = map.get("googRemoteAddress").split(":")[0];
            if (this.mRemoteIp != null) {
                ErrorInfoReport.getInstance().setRemoteIp(this.mRemoteIp);
            }
        }
    }

    private void parseAudioSendStatsReport(Map<String, String> map) {
        try {
            this.mAudioSendBitrateTracker.updataBitrateWidhCurrentByteCount(Long.parseLong(map.get("bytesSent")));
            this.mAudioSendBitrate = this.mAudioSendBitrateTracker.bitRateString();
            if (map.containsKey("totalAudioEnergy")) {
                this.mAudioEnergy = Double.parseDouble(map.get("totalAudioEnergy"));
            }
            if (map.containsKey("audioInputLevel")) {
                this.mAudioInputLevel = Integer.parseInt(map.get("audioInputLevel"));
            }
        } catch (NumberFormatException | Exception unused) {
        }
        this.mAudioSendCodec = map.get("googCodecName");
    }

    private void parseVideoRecvStatsReport(Map<String, String> map) {
        this.mVideoDecodeMs = map.get("googDecodeMs");
        this.mVideoDecodFps = map.get("googFrameRateDecoded");
        this.mVideoOutputFps = map.get("googFrameRateOutput");
        this.mVideoRecvFps = map.get("googFrameRateReceived");
        this.mNacksSent = map.get("googNacksSent");
        this.mPlisSent = map.get("googPlisSent");
        this.mVideoRecvBitrateTracker.updataBitrateWidhCurrentByteCount(Long.parseLong(map.get("bytesReceived")));
        this.mVideoRecvBitrate = this.mVideoRecvBitrateTracker.bitRateString();
        this.mVideoRecvHeight = map.get("googFrameHeightReceived");
        this.mVideoRecvWidth = map.get("googFrameWidthReceived");
        if (map.containsKey("googEndToEndTime")) {
            this.mEndToEndTime = map.get("googEndToEndTime");
        }
        if (map.containsKey("googVideoFrameFrozenPercent")) {
            this.mVideoRecvFrameFrozenPercent = map.get("googVideoFrameFrozenPercent");
            if (this.mVideoRecvFrameFrozenPercent == null) {
                this.mVideoRecvFrameFrozenPercent = "0";
            }
        }
        if (map.containsKey("googVideoFrameRenderDelay")) {
            this.mVideoRecvFrameRenderDelay = map.get("googVideoFrameRenderDelay");
            if (this.mVideoRecvFrameRenderDelay == null) {
                this.mVideoRecvFrameRenderDelay = "0";
            }
        }
        this.mVideoRecvPacketLost = map.get("packetsLost");
        this.mVideoPacketRecv = map.get("packetsReceived");
    }

    private void parseAudioRecvStatsReport(Map<String, String> map) {
        try {
            this.mAudioRecvBitrateTracker.updataBitrateWidhCurrentByteCount(Integer.parseInt(map.get("bytesReceived")));
            this.mAudioRecvBitrate = this.mAudioRecvBitrateTracker.bitRateString();
            String str = map.get("googJitterBufferMs");
            if (!TextUtils.isEmpty(str)) {
                this.mAudioJitterBufferMs = Integer.valueOf(str).intValue();
            }
            if (map.containsKey("totalAudioEnergy")) {
                this.mAudioRecvEnergy = Double.parseDouble(map.get("totalAudioEnergy"));
            }
            if (map.containsKey("audioOutputLevel")) {
                this.mAudioRecvLevel = Integer.parseInt(map.get("audioOutputLevel"));
            }
        } catch (NumberFormatException e) {
            Logging.m5304e("HUDStatistic", "parseAudioRecvStatsReport" + e);
        }
        this.mAudioCurrentDelay = map.get("googCurrentDelayMs");
        this.mAudioRecvCodec = map.get("googCodecName");
        this.mAudioExpandRate = map.get("googSpeechExpandRate");
    }

    public void updateEncoderStatistics(StatsReport[] statsReportArr) {
        Map<String, String> reportMap;
        for (StatsReport statsReport : statsReportArr) {
            if (statsReport.type.equals("ssrc") && statsReport.f21266id.contains("ssrc") && statsReport.f21266id.contains("send")) {
                Map<String, String> reportMap2 = getReportMap(statsReport);
                String str = reportMap2.get("googTrackId");
                if (str != null && str.contains("ARDAMSv0")) {
                    parseVideoSendStatsReport(reportMap2);
                } else if (str != null && str.contains("ARDAMSa0")) {
                    parseAudioSendStatsReport(reportMap2);
                }
            } else if (statsReport.type.equals("ssrc") && statsReport.f21266id.contains("ssrc") && statsReport.f21266id.contains("recv")) {
                Map<String, String> reportMap3 = getReportMap(statsReport);
                if (this.enableMultistream) {
                    if (this.ssrcs.contains(reportMap3.get("ssrc"))) {
                        if (reportMap3.get("googFrameWidthReceived") != null) {
                            parseVideoRecvStatsReport(reportMap3);
                        }
                        if (reportMap3.get("audioOutputLevel") != null) {
                            parseAudioRecvStatsReport(reportMap3);
                        }
                        if (reportMap3.get("googFrameWidthReceived") != null) {
                            parseVideoRecvStatsReport(reportMap3);
                            this.mHasVideo = true;
                        }
                        if (reportMap3.get("audioOutputLevel") != null) {
                            parseAudioRecvStatsReport(reportMap3);
                            this.mHasAudio = true;
                        }
                    }
                } else {
                    if (reportMap3.get("googFrameWidthReceived") != null) {
                        parseVideoRecvStatsReport(reportMap3);
                    }
                    if (reportMap3.get("audioOutputLevel") != null) {
                        parseAudioRecvStatsReport(reportMap3);
                    }
                }
            } else if (statsReport.f21266id.equals("bweforvideo")) {
                Map<String, String> reportMap4 = getReportMap(statsReport);
                if (reportMap4.size() > 3) {
                    parseBweStatsReport(reportMap4);
                }
            } else if (statsReport.type.equals("googCandidatePair") && (reportMap = getReportMap(statsReport)) != null) {
                parseConnectionStatsReport(reportMap);
            }
        }
    }

    public void setHandlerId(BigInteger bigInteger) {
        this.mHandleId = bigInteger;
    }

    public void setPublisher(boolean z) {
        this.mIsPublisher = z;
    }

    public boolean isPublisher() {
        return this.mIsPublisher;
    }
}
