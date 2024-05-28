package com.baidu.p166vi;

import android.media.MediaPlayer;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.AudioFilePlayer */
/* loaded from: E:\567196_dexfile_execute.dex */
public class AudioFilePlayer {

    /* renamed from: a */
    private MediaPlayer f8173a = new MediaPlayer();

    private AudioFilePlayer() {
    }

    private native boolean onErrorOccured(long j, int i);

    private native void onPlayCompleted(long j);
}
