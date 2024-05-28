package com.baidu.rtc;

import android.text.TextUtils;
import com.baidu.rtc.logreport.HUDStatistics;
import com.baidu.rtc.logreport.RTCBitrateTracker;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCCommStatesReport {
    int mDebugStatesFlag;
    BigInteger mPlayTransactionId;
    private String mRemoteHandleId;
    private String mRoomId;
    private HUDStatistics mStates;
    private String mUserId;

    public RTCCommStatesReport(BigInteger bigInteger, String str, String str2, String str3, HUDStatistics hUDStatistics) {
        this.mPlayTransactionId = null;
        this.mDebugStatesFlag = 63;
        this.mStates = hUDStatistics;
        this.mPlayTransactionId = bigInteger;
        this.mRoomId = str;
        this.mUserId = str2;
        this.mRemoteHandleId = str3;
    }

    public RTCCommStatesReport(HUDStatistics hUDStatistics) {
        this.mPlayTransactionId = null;
        this.mDebugStatesFlag = 63;
        this.mStates = hUDStatistics;
    }

    public void setPlayTransactionId(BigInteger bigInteger) {
        this.mPlayTransactionId = bigInteger;
    }

    public void setRemoteHandleId(String str) {
        this.mRemoteHandleId = str;
    }

    public void setRoomId(String str) {
        this.mRoomId = str;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public void setDebugFlag(int i) {
        this.mDebugStatesFlag = i;
    }

    public void updateStates(HUDStatistics hUDStatistics) {
        this.mStates = hUDStatistics;
    }

    public String getRoomId() {
        return this.mRoomId;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public int getConnRecvBitrate() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null) {
            return -1;
        }
        return RTCBitrateTracker.bitrateToString(hUDStatistics.mConnRecvBitrate);
    }

    public int getVideoPacketLostRatioPerMil() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null) {
            return 0;
        }
        return hUDStatistics.mVideoRecvPacketLostRatio;
    }

    public int getAudioRecvBitrate() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null) {
            return -1;
        }
        return RTCBitrateTracker.bitrateToString(hUDStatistics.mAudioRecvBitrate);
    }

    public int getVideoOutputFps() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null || TextUtils.isEmpty(hUDStatistics.mVideoOutputFps)) {
            return -1;
        }
        return Integer.valueOf(this.mStates.mVideoOutputFps).intValue();
    }

    public int getVideoRecvBitrate() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null) {
            return -1;
        }
        return RTCBitrateTracker.bitrateToString(hUDStatistics.mVideoRecvBitrate);
    }

    public int getVideoRecvPacketLost() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null || TextUtils.isEmpty(hUDStatistics.mVideoRecvPacketLost)) {
            return -1;
        }
        return Integer.valueOf(this.mStates.mVideoRecvPacketLost).intValue();
    }

    public String getRemoteAddr() {
        HUDStatistics hUDStatistics = this.mStates;
        return hUDStatistics == null ? "" : hUDStatistics.mRemoteIp;
    }

    public int getAudioJitterBufferMs() {
        HUDStatistics hUDStatistics = this.mStates;
        if (hUDStatistics == null) {
            return 0;
        }
        return hUDStatistics.mAudioJitterBufferMs;
    }

    private String formatPlayInfo() {
        if (this.mPlayTransactionId == null || this.mRoomId == null || this.mUserId == null || this.mRemoteHandleId == null) {
            return "";
        }
        return "PlayInfo: (transactionId)" + this.mPlayTransactionId + " | (handleId)" + this.mRemoteHandleId + " | (room)" + this.mRoomId + " | (user)" + this.mUserId + "\n";
    }

    public String toString() {
        String formatPlayInfo = formatPlayInfo();
        HUDStatistics hUDStatistics = this.mStates;
        String statsString = hUDStatistics != null ? hUDStatistics.statsString(this.mDebugStatesFlag) : "";
        return formatPlayInfo + statsString;
    }
}
