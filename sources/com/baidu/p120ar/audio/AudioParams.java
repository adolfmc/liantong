package com.baidu.p120ar.audio;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.AudioParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioParams {
    public static final float DEFAULT_AMPLIFY_VOLUME = 1.0f;
    public static final int DEFAULT_AUDIO_BUFFER_SIZE = 32768;
    public static final int DEFAULT_AUDIO_FORMAT = 2;
    public static final int DEFAULT_AUDIO_SOURCE = 1;
    public static final int DEFAULT_BUFFER_FRAME_COUNT = 32;
    public static final int DEFAULT_CHANNEL_CONFIG = 16;
    public static final int DEFAULT_FRAME_SIZE = 1024;
    public static final int DEFAULT_SAMPLE_RATE = 16000;
    public static final int SAMPLES_PER_FRAME = 1024;
    private int mAudioSource = 1;
    private int mSampleRate = 16000;
    private int mChannelConfig = 16;
    private int mAudioFormat = 2;
    private int mFrameSize = 1024;
    private int mFrameBufferCount = 32;
    private int mAudioBufferSize = 32768;
    private float mAmplifyVolume = 1.0f;

    public int getAudioSource() {
        return this.mAudioSource;
    }

    public void setAudioSource(int i) {
        this.mAudioSource = i;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public void setSampleRate(int i) {
        this.mSampleRate = i;
    }

    public int getChannelConfig() {
        return this.mChannelConfig;
    }

    public void setChannelConfig(int i) {
        this.mChannelConfig = i;
    }

    public int getAudioFormat() {
        return this.mAudioFormat;
    }

    public void setAudioFormat(int i) {
        this.mAudioFormat = i;
    }

    public int getFrameSize() {
        return this.mFrameSize;
    }

    public void setFrameSize(int i) {
        this.mFrameSize = i;
    }

    public int getFrameBufferCount() {
        return this.mFrameBufferCount;
    }

    public void setFrameBufferCount(int i) {
        this.mFrameBufferCount = i;
    }

    public int getAudioBufferSize() {
        return this.mAudioBufferSize;
    }

    public void setAudioBufferSize(int i) {
        this.mAudioBufferSize = i;
    }

    public float getAmplifyVolume() {
        return this.mAmplifyVolume;
    }

    public void setAmplifyVolume(float f) {
        this.mAmplifyVolume = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof AudioParams)) {
            AudioParams audioParams = (AudioParams) obj;
            return this.mAudioSource == audioParams.mAudioSource && this.mSampleRate == audioParams.getSampleRate() && this.mChannelConfig == audioParams.getChannelConfig() && this.mAudioFormat == audioParams.getAudioFormat() && this.mFrameSize == audioParams.getFrameSize() && this.mAmplifyVolume == audioParams.getAmplifyVolume();
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((this.mAudioSource + 31) * 31) + this.mSampleRate) * 31) + this.mChannelConfig) * 31) + this.mAudioFormat) * 31) + this.mFrameSize) * 31) + ((int) this.mAmplifyVolume);
    }
}
