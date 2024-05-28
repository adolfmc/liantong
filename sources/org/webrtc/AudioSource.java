package org.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AudioSource extends MediaSource {
    public AudioSource(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeAudioSource() {
        return getNativeMediaSource();
    }
}
