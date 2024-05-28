package com.megvii.lv5;

import android.animation.ValueAnimator;
import com.megvii.lv5.sdk.detect.action.ActionLivenessActivity;
import com.megvii.lv5.sdk.view.LoadingCoverView;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.b0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5385b0 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    public final /* synthetic */ float f12382a;

    /* renamed from: b */
    public final /* synthetic */ float f12383b;

    /* renamed from: c */
    public final /* synthetic */ ActionLivenessActivity f12384c;

    public C5385b0(ActionLivenessActivity actionLivenessActivity, float f, float f2) {
        this.f12384c = actionLivenessActivity;
        this.f12382a = f;
        this.f12383b = f2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        LoadingCoverView loadingCoverView = this.f12384c.f13321p;
        float f = this.f12382a;
        loadingCoverView.setProgress((f + (floatValue * (this.f12383b - f))) * 100.0f);
    }
}
