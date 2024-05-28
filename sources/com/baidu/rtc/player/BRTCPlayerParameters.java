package com.baidu.rtc.player;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BRTCPlayerParameters {
    public static final int AAC = 2;
    private static final int DEFAULT_STATS_INTERVAL_MS = 1000;
    private static final int DEFAULT_STREAMING_INTERRUPT_INTERVAL_MS = 6000;
    public static final int OPUS = 1;
    private long mUserId;
    public int mAudioDecodeFormat = 2;
    public double mVolume = 1.0d;
    public boolean mEnableDebug = false;
    private String mPullUrl = "https://rtc2.exp.bcelive.com/brtc/v3/pullstream";
    private String mSoLaterLoadUrl = "";
    private String mCpuType = "armeabi-v7a";
    private boolean mIsEnableSoLaterLoad = false;
    private boolean enableEncrypt = false;
    private boolean mAutoPlay = false;
    private int mStreamingInterruptDetectIntervalMs = 6000;
    private int mStatsReportIntervalMs = 1000;
    private int mAudioBufferUnderLoadLevel = 20;
    private int mAudioBufferMaintainableLevel = 50;
    private int mBufferingDetectIntervalMs = 200;

    public void setAudioDecodeFormat(int i) {
        this.mAudioDecodeFormat = i;
    }

    public void setVolume(double d) {
        this.mVolume = d;
    }

    public void setEnableDebug(boolean z) {
        this.mEnableDebug = z;
    }

    public void setPullUrl(String str) {
        this.mPullUrl = str;
    }

    public void setSoLaterLoadUrl(String str) {
        this.mSoLaterLoadUrl = str;
    }

    public void setCpuType(String str) {
        this.mCpuType = str;
    }

    public void enableSoLaterLoad(boolean z) {
        this.mIsEnableSoLaterLoad = z;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public void setStreamingInterruptDetectIntervalMs(int i) {
        this.mStreamingInterruptDetectIntervalMs = i;
    }

    public void setStatsReportIntervalMs(int i) {
        this.mStatsReportIntervalMs = i;
    }

    public void setAudioBufferUnderLoadLevel(int i) {
        this.mAudioBufferUnderLoadLevel = i;
    }

    public void setAudioBufferMaintainableLevel(int i) {
        this.mAudioBufferMaintainableLevel = i;
    }

    public void setBufferingDetectIntervalMs(int i) {
        this.mBufferingDetectIntervalMs = i;
    }

    public void setEnableEncrypt(boolean z) {
        this.enableEncrypt = z;
    }

    public int getAudioDecodeFormat() {
        return this.mAudioDecodeFormat;
    }

    public double getVolume() {
        return this.mVolume;
    }

    public boolean getEnableDebug() {
        return this.mEnableDebug;
    }

    public String getPullUrl() {
        return this.mPullUrl;
    }

    public String getSoLaterLoadUrl() {
        return this.mSoLaterLoadUrl;
    }

    public boolean isEnableSoLaterLoad() {
        return this.mIsEnableSoLaterLoad;
    }

    public String getCpuType() {
        return this.mCpuType;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public int getStreamingInterruptDetectInterval() {
        return this.mStreamingInterruptDetectIntervalMs;
    }

    public int getStatsReportIntervalMs() {
        return this.mStatsReportIntervalMs;
    }

    public int getBufferingDetectIntervalMs() {
        return this.mBufferingDetectIntervalMs;
    }

    public int getAudioBufferUnderLoadLevel() {
        return this.mAudioBufferUnderLoadLevel;
    }

    public int getAudioBufferMaintainableLevel() {
        return this.mAudioBufferMaintainableLevel;
    }

    public boolean isEnableEncrypt() {
        return this.enableEncrypt;
    }

    public void setAutoPlay(boolean z) {
        this.mAutoPlay = z;
    }

    public boolean isAutoPlay() {
        return this.mAutoPlay;
    }

    public String toString() {
        return "BRTCPlayerParameters{mAudioDecodeFormat=" + this.mAudioDecodeFormat + ", mVolume=" + this.mVolume + ", mEnableDebug=" + this.mEnableDebug + ", mPullUrl='" + this.mPullUrl + "', mSoLaterLoadUrl='" + this.mSoLaterLoadUrl + "', mCpuType='" + this.mCpuType + "', mIsEnableSoLaterLoad=" + this.mIsEnableSoLaterLoad + ", mUserId=" + this.mUserId + ", enableEncrypt=" + this.enableEncrypt + ", mAutoPlay=" + this.mAutoPlay + ", mStreamingInterruptDetectIntervalMs=" + this.mStreamingInterruptDetectIntervalMs + ", mStatsReportIntervalMs=" + this.mStatsReportIntervalMs + ", mAudioBufferUnderLoadLevel=" + this.mAudioBufferUnderLoadLevel + ", mAudioBufferMaintainableLevel=" + this.mAudioBufferMaintainableLevel + ", mBufferingDetectIntervalMs=" + this.mBufferingDetectIntervalMs + '}';
    }
}
