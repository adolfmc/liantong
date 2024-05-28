package com.liang.jtablayout.indicator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TransitionIndicatorEvaluator extends IndicatorTypeEvaluator {
    @Override // android.animation.TypeEvaluator
    public IndicatorPoint evaluate(float f, IndicatorPoint indicatorPoint, IndicatorPoint indicatorPoint2) {
        float f2;
        float f3;
        if (indicatorPoint.left < indicatorPoint2.left) {
            f3 = f * 2.0f;
            f2 = f3 - 1.0f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (1.0f - f3 < 0.0f) {
                f3 = 1.0f;
            }
        } else {
            float f4 = f * 2.0f;
            float f5 = f4 - 1.0f;
            if (f5 < 0.0f) {
                f5 = 0.0f;
            }
            if (1.0f - f4 < 0.0f) {
                f3 = f5;
                f2 = 1.0f;
            } else {
                float f6 = f5;
                f2 = f4;
                f3 = f6;
            }
        }
        float f7 = indicatorPoint.left + (f2 * (indicatorPoint2.left - indicatorPoint.left));
        float f8 = indicatorPoint.right + (f3 * (indicatorPoint2.right - indicatorPoint.right));
        IndicatorPoint indicatorPoint3 = new IndicatorPoint();
        indicatorPoint3.left = f7;
        indicatorPoint3.right = f8;
        return indicatorPoint3;
    }
}
