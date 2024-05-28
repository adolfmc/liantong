package com.megvii.lv5;

import android.graphics.drawable.AnimationDrawable;
import com.megvii.lv5.sdk.C5559R;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.m2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5508m2 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C5496l2 f12886a;

    public RunnableC5508m2(C5496l2 c5496l2) {
        this.f12886a = c5496l2;
    }

    @Override // java.lang.Runnable
    public void run() {
        AnimationDrawable animationDrawable = this.f12886a.f12861a;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        C5496l2 c5496l2 = this.f12886a;
        if (c5496l2.f12864d == null) {
            int m12877b = C5667z2.m12879a(c5496l2.f12862b).m12877b(this.f12886a.f12862b.getResources().getString(C5559R.string.key_mouth_close));
            C5496l2 c5496l22 = this.f12886a;
            c5496l22.f12864d = c5496l22.f12862b.getResources().getDrawable(m12877b);
        }
        if (this.f12886a.f12863c.getVisibility() == 0) {
            C5496l2 c5496l23 = this.f12886a;
            c5496l23.f12863c.setBackgroundDrawable(c5496l23.f12864d);
        }
    }
}
