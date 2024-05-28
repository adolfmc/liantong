package com.megvii.lv5;

import android.animation.ValueAnimator;
import com.megvii.lv5.sdk.view.EggView;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.g3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5454g3 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    public final /* synthetic */ EggView f12689a;

    public C5454g3(EggView eggView) {
        this.f12689a = eggView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f12689a.f13551G = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f12689a.invalidate();
    }
}
