package com.megvii.lv5;

import android.animation.ValueAnimator;
import com.megvii.lv5.sdk.view.EggView;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.f3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5439f3 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    public final /* synthetic */ EggView f12599a;

    public C5439f3(EggView eggView) {
        this.f12599a = eggView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f12599a.f13548D = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f12599a.invalidate();
    }
}
