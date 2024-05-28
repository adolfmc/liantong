package com.baidu.feedcv.soundchanger;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StkOp implements AudioProcessor {
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final int VOICE_CHANGER_TYPE_DASHU = 2;
    public static final int VOICE_CHANGER_TYPE_FEIZHAI = 4;
    public static final int VOICE_CHANGER_TYPE_LUOLI = 1;
    public static final int VOICE_CHANGER_TYPE_ZHENGTAI = 3;
    private int bytesPerSample;
    private int channels;
    private float pitchSemi;
    private float rate = 1.0f;
    private int samplingRate;
    private float tempo;
    private int track;
    private int voiceChangerType;

    static {
        System.loadLibrary("soundtouch");
    }

    public StkOp(int i, int i2, int i3, int i4, float f, float f2) {
        this.channels = i2;
        this.samplingRate = i3;
        this.bytesPerSample = i4;
        this.tempo = f;
        this.pitchSemi = f2;
        this.track = i;
        setup(i, i2, i3, i4, f, f2);
    }

    private static native synchronized void clearBytes(int i);

    private static native synchronized void finish(int i, int i2);

    private static native synchronized int getBytes(int i, byte[] bArr, int i2);

    private static native synchronized long getOutputBufferSize(int i);

    private static native synchronized void putBytes(int i, byte[] bArr, int i2, int i3);

    private static native synchronized void setPitchSemi(int i, float f);

    private static native synchronized void setRate(int i, float f);

    private static native synchronized void setRateChange(int i, float f);

    private static native synchronized void setSpeech(int i, boolean z);

    private static native synchronized void setTempo(int i, float f);

    private static native synchronized void setTempoChange(int i, float f);

    private static native synchronized void setup(int i, int i2, int i3, int i4, float f, float f2);

    public void clearBuffer() {
        clearBytes(this.track);
    }

    public void finish() {
        finish(this.track, 2048);
    }

    public int getBytes(byte[] bArr) {
        return getBytes(this.track, bArr, bArr.length);
    }

    public int getBytesPerSample() {
        return this.bytesPerSample;
    }

    public int getChannels() {
        return this.channels;
    }

    public long getOutputBufferSize() {
        return getOutputBufferSize(this.track);
    }

    public float getPitchSemi() {
        return this.pitchSemi;
    }

    public float getRate() {
        return this.rate;
    }

    public int getSamplingRate() {
        return this.samplingRate;
    }

    public float getTempo() {
        return this.tempo;
    }

    public int getTrackId() {
        return this.track;
    }

    public void putBytes(byte[] bArr) {
        putBytes(this.track, bArr, bArr.length, this.voiceChangerType);
    }

    public void setBytesPerSample(int i) {
        this.bytesPerSample = i;
    }

    public void setChannels(int i) {
        this.channels = i;
    }

    public void setParams(Map<String, String> map) {
        if (map.containsKey("SampleRate")) {
            setSamplingRate(Integer.parseInt(map.get("SampleRate")));
        }
        if (map.containsKey("Channels")) {
            setChannels(Integer.parseInt(map.get("Channels")));
        }
        if (map.containsKey("bytePerSample")) {
            setBytesPerSample(Integer.parseInt(map.get("bytePerSample")));
        }
    }

    public void setPitchSemi(float f) {
        this.pitchSemi = f;
        setPitchSemi(this.track, f);
    }

    public void setRate(float f) {
        this.rate = f;
        setRate(this.track, f);
    }

    public void setRateChange(float f) {
        this.rate = f;
        setRateChange(this.track, f);
    }

    public void setSamplingRate(int i) {
        this.samplingRate = i;
    }

    public void setSpeech(boolean z) {
        setSpeech(this.track, z);
    }

    public void setTempo(float f) {
        this.tempo = f;
        setTempo(this.track, f);
    }

    public void setTempoChange(float f) {
        if (f >= -50.0f && f <= 100.0f) {
            this.tempo = (0.01f * f) + 1.0f;
            setTempoChange(this.track, f);
            return;
        }
        throw new SoundStreamRuntimeException("Tempo percentage must be between -50 and 100");
    }

    public void setVoiceChangeType(int i) {
        this.voiceChangerType = i;
    }
}
