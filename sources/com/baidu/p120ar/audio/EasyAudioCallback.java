package com.baidu.p120ar.audio;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.EasyAudioCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface EasyAudioCallback {
    void onAudioFrameAvailable(ByteBuffer byteBuffer, int i, long j);

    void onAudioStart(boolean z, AudioParams audioParams);

    void onAudioStop(boolean z);
}
