package com.megvii.lv5;

import android.graphics.drawable.AnimationDrawable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.k2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5489k2 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C5496l2 f12841a;

    public RunnableC5489k2(C5496l2 c5496l2) {
        this.f12841a = c5496l2;
    }

    @Override // java.lang.Runnable
    public void run() {
        AnimationDrawable animationDrawable = this.f12841a.f12861a;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }
}
