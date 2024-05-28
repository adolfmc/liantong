package com.bytedance.android.live.base.api.outer;

import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILiveView {
    View getView();

    void openLive();

    void release();

    void setLiveStatusListener(ILiveStatusListener iLiveStatusListener);

    void setMute(boolean z);

    void show();

    void stream();
}
