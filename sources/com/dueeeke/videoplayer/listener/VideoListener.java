package com.dueeeke.videoplayer.listener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface VideoListener {
    void onComplete();

    void onError();

    void onInfo(int i, int i2);

    void onPrepared();
}
