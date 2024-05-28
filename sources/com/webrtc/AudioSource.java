package com.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AudioSource extends MediaSource {
    public AudioSource(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeAudioSource() {
        return getNativeMediaSource();
    }
}
