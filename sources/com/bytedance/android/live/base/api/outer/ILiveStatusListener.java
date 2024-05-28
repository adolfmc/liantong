package com.bytedance.android.live.base.api.outer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILiveStatusListener {
    void onError(String str);

    void onFirstFrame();

    void onLiveStatusChange(boolean z);

    void onPrepare();

    void onVideoSizeChanged(int i, int i2);
}
