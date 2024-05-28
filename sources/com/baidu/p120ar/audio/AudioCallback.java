package com.baidu.p120ar.audio;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.AudioCallback */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface AudioCallback {
    void onAudioFrameAvailable(ByteBuffer byteBuffer, int i, long j);

    void onAudioRelease();

    void onAudioSetup(boolean z);

    void onAudioStart(boolean z);

    void onAudioStop(boolean z);
}
