package org.limlee.hipraiseanimationlib;

import android.graphics.Bitmap;
import java.util.Random;
import org.limlee.hipraiseanimationlib.base.IDrawable;
import org.limlee.hipraiseanimationlib.base.IPraise;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HiPraise implements IPraise {
    protected Bitmap bitmap;
    public long delayAplhaTime;
    public long startDelay;
    public float scale = 1.0f;
    public float alpha = 1.0f;
    public long duration = Utils.rondomRange(2500, 2000);

    public HiPraise(Bitmap bitmap) {
        this.bitmap = bitmap;
        long j = 500;
        this.delayAplhaTime = (new Random().nextInt((int) this.duration) % ((this.duration - j) + 1)) + j;
    }

    @Override // org.limlee.hipraiseanimationlib.base.IPraise
    public IDrawable toDrawable() {
        return new PraiseDrawable(this.bitmap, this.scale, this.alpha, this.duration, this.startDelay, this.delayAplhaTime, 0.45f);
    }
}
