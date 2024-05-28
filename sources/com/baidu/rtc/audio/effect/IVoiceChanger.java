package com.baidu.rtc.audio.effect;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IVoiceChanger {
    void finish();

    int getBytes(byte[] bArr);

    int getChannels();

    long getOutputBufferSize();

    float getPitchSemi();

    float getRate();

    int getSamplingRate();

    float getTempo();

    int getTrackId();

    void initVoiceChanger(int i, int i2, int i3);

    void initVoiceChanger(int i, int i2, int i3, int i4, float f, float f2);

    void putBytes(byte[] bArr);

    void release();

    void setBytesWidth(int i);

    void setChannels(int i);

    void setPitchSemi(float f);

    void setRate(float f);

    void setRateChange(float f);

    void setSamplingRate(int i);

    void setTempo(float f);

    void setTempoChange(float f);

    void setVoiceChangeType(int i);
}
