package com.sinovatech.unicom.separatemodule.audience.quickrecycler.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SlideInBottomAnimation implements BaseAnimation {
    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0.0f)};
    }
}
