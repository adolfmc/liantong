package com.megvii.lv5;

import android.animation.ValueAnimator;
import com.megvii.lv5.sdk.view.LoadingCoverView;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.h3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5465h3 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    public final /* synthetic */ LoadingCoverView f12722a;

    public C5465h3(LoadingCoverView loadingCoverView) {
        this.f12722a = loadingCoverView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f12722a.f13679o = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        LoadingCoverView loadingCoverView = this.f12722a;
        loadingCoverView.f13680p = LoadingCoverView.EnumC5610a.PROGRESSING;
        loadingCoverView.postInvalidate();
    }
}
