package com.bytedance.android.live.base.api;

import android.view.View;
import com.bytedance.android.live.base.api.callback.EmptyCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILiveOuterPreviewCoverView {
    void cancelAutoEnterGuide(boolean z);

    void onShow();

    void release();

    void setCustomBottomView(View view);

    void setOnDislikeCallback(EmptyCallback emptyCallback);

    void stream(String str);
}
