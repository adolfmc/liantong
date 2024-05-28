package com.baidu.rtc.record;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class MediaEncodeParams {
    private static final int AAC_SAMPLES_PER_FRAME = 1024;
    private static final int AUDIO_BIT_RATE = 65536;
    private static final int AUDIO_CHANNEL = 1;
    private static final int AUDIO_FRAME_SIZE = 10240;
    private static final String AUDIO_MIME_TYPE = "audio/mp4a-latm";
    private static final int AUDIO_SAMPLE_RATE = 48000;
    private static final int OUTPUT_FORMAT = 0;
    private static final long OUTPUT_TOTAL_MS = 0;
    private static final int VIDEO_BIT_RATE = 2097152;
    private static final int VIDEO_FRAME_RATE = 30;
    private static final int VIDEO_HEIGHT = 1280;
    private static final int VIDEO_I_FRAME_INTERVAL = 5;
    private static final String VIDEO_MIME_TYPE = "video/avc";
    private static final int VIDEO_WIDTH = 720;
    private int mOutputFormat = 0;
    private long mOutputTotalMs = 0;
    private boolean mVideoIncluded = true;
    private int mVideoWidth = 720;
    private int mVideoHeight = 1280;
    private String mVideoCodec = "video/avc";
    private int mVideoBitrate = 2097152;
    private int mVideoFrameRate = 30;
    private int mVideoIFrameInterval = 5;
    private boolean mAudioIncluded = true;
    private String mAudioCodec = "audio/mp4a-latm";
    private int mAudioChannel = 1;
    private int mAudioBitrate = 65536;
    private int mAudioSampleRate = 48000;
    private int mAudioFrameSize = 10240;

    public int getOutputFormat() {
        return this.mOutputFormat;
    }

    public void setOutputFormat(int i) {
        this.mOutputFormat = i;
    }

    public long getOutputTotalMs() {
        return this.mOutputTotalMs;
    }

    public void setOutputTotalMs(long j) {
        this.mOutputTotalMs = j;
    }

    public boolean isVideoIncluded() {
        return this.mVideoIncluded;
    }

    public void setVideoIncluded(boolean z) {
        this.mVideoIncluded = z;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public void setVideoWidth(int i) {
        this.mVideoWidth = i;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public void setVideoHeight(int i) {
        this.mVideoHeight = i;
    }

    public String getVideoCodec() {
        return this.mVideoCodec;
    }

    public void setVideoCodec(String str) {
        this.mVideoCodec = str;
    }

    public int getVideoBitrate() {
        return this.mVideoBitrate;
    }

    public void setVideoBitrate(int i) {
        this.mVideoBitrate = i;
    }

    public int getVideoFrameRate() {
        return this.mVideoFrameRate;
    }

    public void setVideoFrameRate(int i) {
        this.mVideoFrameRate = i;
    }

    public int getVideoIFrameInterval() {
        return this.mVideoIFrameInterval;
    }

    public void setVideoIFrameInterval(int i) {
        this.mVideoIFrameInterval = i;
    }

    public boolean isAudioIncluded() {
        return this.mAudioIncluded;
    }

    public void setAudioIncluded(boolean z) {
        this.mAudioIncluded = z;
    }

    public String getAudioCodec() {
        return this.mAudioCodec;
    }

    public void setAudioCodec(String str) {
        this.mAudioCodec = str;
    }

    public int getAudioChannel() {
        return this.mAudioChannel;
    }

    public void setAudioChannel(int i) {
        this.mAudioChannel = i;
    }

    public int getAudioBitrate() {
        return this.mAudioBitrate;
    }

    public void setAudioBitrate(int i) {
        this.mAudioBitrate = i;
    }

    public int getAudioSampleRate() {
        return this.mAudioSampleRate;
    }

    public void setAudioSampleRate(int i) {
        this.mAudioSampleRate = i;
    }

    public int getAudioFrameSize() {
        return this.mAudioFrameSize;
    }

    public void setAudioFrameSize(int i) {
        this.mAudioFrameSize = i;
    }
}
