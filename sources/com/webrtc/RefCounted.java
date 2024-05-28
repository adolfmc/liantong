package com.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface RefCounted {
    @CalledByNative
    void release();

    @CalledByNative
    void retain();
}
