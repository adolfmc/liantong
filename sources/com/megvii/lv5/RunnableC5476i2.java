package com.megvii.lv5;

import android.graphics.drawable.AnimationDrawable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.i2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5476i2 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ AnimationDrawable f12805a;

    /* renamed from: b */
    public final /* synthetic */ C5496l2 f12806b;

    public RunnableC5476i2(C5496l2 c5496l2, AnimationDrawable animationDrawable) {
        this.f12806b = c5496l2;
        this.f12805a = animationDrawable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f12805a != null) {
            AnimationDrawable animationDrawable = this.f12806b.f12861a;
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
            this.f12806b.f12863c.setBackgroundDrawable(this.f12805a);
            this.f12806b.f12863c.setVisibility(0);
            C5496l2 c5496l2 = this.f12806b;
            c5496l2.f12861a = (AnimationDrawable) c5496l2.f12863c.getBackground();
            this.f12806b.f12861a.start();
        }
    }
}
