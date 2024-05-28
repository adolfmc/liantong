package org.limlee.hipraiseanimationlib;

import android.graphics.Bitmap;
import org.limlee.hipraiseanimationlib.base.IDrawable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HiPraiseWithCallback extends HiPraise {
    private OnDrawCallback mOnDrawCallback;

    public HiPraiseWithCallback(Bitmap bitmap, OnDrawCallback onDrawCallback) {
        super(bitmap);
        this.mOnDrawCallback = onDrawCallback;
    }

    @Override // org.limlee.hipraiseanimationlib.HiPraise, org.limlee.hipraiseanimationlib.base.IPraise
    public IDrawable toDrawable() {
        return new PraiseWithCallbackDrawable(this.bitmap, this.scale, this.alpha, this.duration, this.startDelay, this.delayAplhaTime, 0.45f, this.mOnDrawCallback);
    }
}
