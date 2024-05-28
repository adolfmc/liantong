package com.baidu.p120ar.audio;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.IAudio */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAudio {
    void releaseAudio();

    void setVolumeListener(VolumeListener volumeListener);

    boolean setupAudio(AudioParams audioParams, AudioCallback audioCallback);

    void startAudio();

    void stopAudio();
}
