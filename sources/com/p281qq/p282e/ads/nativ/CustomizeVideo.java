package com.p281qq.p282e.ads.nativ;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.nativ.CustomizeVideo */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface CustomizeVideo {
    String getVideoUrl();

    void reportVideoCompleted();

    void reportVideoError(long j, int i, int i2);

    void reportVideoPause(long j);

    void reportVideoPreload();

    void reportVideoResume(long j);

    void reportVideoStart();
}
