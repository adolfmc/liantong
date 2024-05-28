package com.baidu.feedcv.soundchanger;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VoiceChangerOp {
    public static final int VOICE_CHANGER_TYPE_DASHU = 2;
    public static final int VOICE_CHANGER_TYPE_FEIZHAI = 4;
    public static final int VOICE_CHANGER_TYPE_GAOGUAI = 10;
    public static final int VOICE_CHANGER_TYPE_GUANSHIYIN = 6;
    public static final int VOICE_CHANGER_TYPE_KTV = 5;
    public static final int VOICE_CHANGER_TYPE_LUOLI = 1;
    public static final int VOICE_CHANGER_TYPE_SHULAN = 9;
    public static final int VOICE_CHANGER_TYPE_SHUNLIU = 8;
    public static final int VOICE_CHANGER_TYPE_SINGINGREFINE = 7;
    public static final int VOICE_CHANGER_TYPE_ZHENGTAI = 3;
    private int bytesPerSample;
    private int channels;
    protected int currentType;
    private float pitchSemi;
    private float rate = 1.0f;
    private int samplingRate;
    protected SoundTouch soundTouch;
    protected StkOp stkOp;
    private float tempo;
    private int track;

    public VoiceChangerOp(int i, int i2, int i3, int i4, float f, float f2) {
        this.channels = i2;
        this.samplingRate = i3;
        this.bytesPerSample = i4;
        this.tempo = f;
        this.pitchSemi = f2;
        this.track = i;
        this.soundTouch = new SoundTouch(0, i2, i3, 2, f, f2);
        this.stkOp = new StkOp(0, i2, i3, 2, f, f2);
    }

    protected void finalize() {
        this.soundTouch.m19719a();
        this.stkOp.clearBuffer();
    }

    public void finish() {
        switch (this.currentType) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
                this.soundTouch.m19715b();
                return;
            case 5:
            case 6:
            case 7:
                this.stkOp.finish();
                return;
            default:
                return;
        }
    }

    public int getBytes(byte[] bArr) {
        switch (this.currentType) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
                return this.soundTouch.m19716a(bArr);
            case 5:
            case 6:
            case 7:
                return this.stkOp.getBytes(bArr);
            default:
                return 0;
        }
    }

    public int getChannels() {
        return this.soundTouch.m19711c();
    }

    public long getOutputBufferSize() {
        return this.soundTouch.m19708d();
    }

    public float getPitchSemi() {
        return this.soundTouch.m19705e();
    }

    public float getRate() {
        return this.soundTouch.m19703f();
    }

    public int getSamplingRate() {
        return this.soundTouch.m19702g();
    }

    public float getTempo() {
        return this.soundTouch.m19701h();
    }

    public int getTrackId() {
        return this.soundTouch.m19700i();
    }

    public void putBytes(byte[] bArr) {
        switch (this.currentType) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
                this.soundTouch.m19712b(bArr);
                return;
            case 5:
            case 6:
            case 7:
                this.stkOp.putBytes(bArr);
                return;
            default:
                return;
        }
    }

    public void release() {
        this.soundTouch.m19719a();
        this.stkOp.clearBuffer();
    }

    public void setBytesPerSample(int i) {
        this.soundTouch.m19717a(i);
    }

    public void setChannels(int i) {
        this.soundTouch.m19713b(i);
    }

    public void setParams(Map<String, String> map) {
        if (map.containsKey("SampleRate")) {
            this.soundTouch.m19709c(Integer.parseInt(map.get("SampleRate")));
        }
        if (map.containsKey("Channels")) {
            this.soundTouch.m19713b(Integer.parseInt(map.get("Channels")));
        }
        if (map.containsKey("bytePerSample")) {
            this.soundTouch.m19709c(Integer.parseInt(map.get("bytePerSample")));
        }
    }

    public void setPitchSemi(float f) {
        this.soundTouch.m19718a(f);
    }

    public void setRate(float f) {
        this.soundTouch.m19714b(f);
    }

    public void setRateChange(float f) {
        this.soundTouch.m19710c(f);
    }

    public void setSamplingRate(int i) {
        this.soundTouch.m19709c(i);
    }

    public void setTempo(float f) {
        this.soundTouch.m19707d(f);
    }

    public void setTempoChange(float f) {
        this.soundTouch.m19704e(f);
    }

    public void setVoiceChangeType(int i) {
        this.currentType = i;
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
                this.soundTouch.m19706d(i);
                return;
            case 5:
            case 6:
            case 7:
                this.stkOp.setVoiceChangeType(i);
                return;
            default:
                return;
        }
    }
}
