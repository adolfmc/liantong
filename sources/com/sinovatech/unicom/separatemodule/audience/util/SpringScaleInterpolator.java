package com.sinovatech.unicom.separatemodule.audience.util;

import android.view.animation.Interpolator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SpringScaleInterpolator implements Interpolator {
    private float factor;

    public SpringScaleInterpolator(float f) {
        this.factor = f;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        double pow = Math.pow(2.0d, (-10.0f) * f);
        float f2 = this.factor;
        return (float) ((pow * Math.sin(((f - (f2 / 4.0f)) * 6.283185307179586d) / f2)) + 1.0d);
    }
}
