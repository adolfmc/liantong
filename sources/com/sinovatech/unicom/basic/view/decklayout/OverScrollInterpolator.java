package com.sinovatech.unicom.basic.view.decklayout;

import android.view.animation.Interpolator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OverScrollInterpolator implements Interpolator {
    public static int INTERPOLATOR_DECELERATE = 1;
    public static int INTERPOLATOR_VISCOUS_FLUID = 0;
    private static final float VISCOUS_FLUID_NORMALIZE = 1.0f / viscousFluid(1.0f);
    private static final float VISCOUS_FLUID_OFFSET = 1.0f - (VISCOUS_FLUID_NORMALIZE * viscousFluid(1.0f));
    private static final float VISCOUS_FLUID_SCALE = 8.0f;
    private int type;

    public OverScrollInterpolator(int i) {
        this.type = i;
    }

    private static float viscousFluid(float f) {
        float f2 = f * VISCOUS_FLUID_SCALE;
        if (f2 < 1.0f) {
            return f2 - (1.0f - ((float) Math.exp(-f2)));
        }
        return ((1.0f - ((float) Math.exp(1.0f - f2))) * 0.63212055f) + 0.36787945f;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (this.type == INTERPOLATOR_DECELERATE) {
            float f2 = 1.0f - f;
            return 1.0f - (f2 * f2);
        }
        float viscousFluid = VISCOUS_FLUID_NORMALIZE * viscousFluid(f);
        return viscousFluid > 0.0f ? viscousFluid + VISCOUS_FLUID_OFFSET : viscousFluid;
    }
}
