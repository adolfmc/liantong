package com.megvii.lv5;

import android.graphics.drawable.AnimationDrawable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.j2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5483j2 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ AnimationDrawable f12821a;

    /* renamed from: b */
    public final /* synthetic */ float f12822b;

    /* renamed from: c */
    public final /* synthetic */ C5496l2 f12823c;

    public RunnableC5483j2(C5496l2 c5496l2, AnimationDrawable animationDrawable, float f) {
        this.f12823c = c5496l2;
        this.f12821a = animationDrawable;
        this.f12822b = f;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f12821a != null) {
            AnimationDrawable animationDrawable = this.f12823c.f12861a;
            if (animationDrawable != null) {
                animationDrawable.stop();
            }
            this.f12823c.f12863c.setY(this.f12822b);
            this.f12823c.f12863c.setBackgroundDrawable(this.f12821a);
            this.f12823c.f12863c.setVisibility(0);
            C5496l2 c5496l2 = this.f12823c;
            c5496l2.f12861a = (AnimationDrawable) c5496l2.f12863c.getBackground();
            this.f12823c.f12861a.start();
        }
    }
}
