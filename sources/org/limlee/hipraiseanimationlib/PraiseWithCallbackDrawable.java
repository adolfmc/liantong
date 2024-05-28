package org.limlee.hipraiseanimationlib;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PraiseWithCallbackDrawable extends PraiseDrawable implements OnDrawCallback {
    private OnDrawCallback mOnDrawCallback;

    public PraiseWithCallbackDrawable(@NonNull Bitmap bitmap, float f, float f2, long j, long j2, long j3, float f3, OnDrawCallback onDrawCallback) {
        super(bitmap, f, f2, j, j2, j3, f3);
        this.mOnDrawCallback = onDrawCallback;
    }

    @Override // org.limlee.hipraiseanimationlib.OnDrawCallback
    public void onFinish() {
        OnDrawCallback onDrawCallback = this.mOnDrawCallback;
        if (onDrawCallback != null) {
            onDrawCallback.onFinish();
        }
    }
}
