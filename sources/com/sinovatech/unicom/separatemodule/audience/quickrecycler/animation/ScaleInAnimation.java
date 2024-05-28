package com.sinovatech.unicom.separatemodule.audience.quickrecycler.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScaleInAnimation implements BaseAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5f;
    private final float mFrom;

    public ScaleInAnimation() {
        this(0.5f);
    }

    public ScaleInAnimation(float f) {
        this.mFrom = f;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new ObjectAnimator[]{ObjectAnimator.ofFloat(view, "scaleX", this.mFrom, 1.0f), ObjectAnimator.ofFloat(view, "scaleY", this.mFrom, 1.0f)};
    }
}
