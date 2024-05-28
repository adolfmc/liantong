package com.baidu.p120ar.audio;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.IEasyAudio */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IEasyAudio {
    void release();

    void removeVolumeListener(VolumeListener volumeListener);

    void setVolumeListener(VolumeListener volumeListener);

    void startAudio(AudioParams audioParams, EasyAudioCallback easyAudioCallback);

    void stopAudio(EasyAudioCallback easyAudioCallback);
}
