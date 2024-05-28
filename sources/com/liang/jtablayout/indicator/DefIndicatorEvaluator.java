package com.liang.jtablayout.indicator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DefIndicatorEvaluator extends IndicatorTypeEvaluator {
    @Override // android.animation.TypeEvaluator
    public IndicatorPoint evaluate(float f, IndicatorPoint indicatorPoint, IndicatorPoint indicatorPoint2) {
        float f2 = indicatorPoint.left + ((indicatorPoint2.left - indicatorPoint.left) * f);
        float f3 = indicatorPoint.right + (f * (indicatorPoint2.right - indicatorPoint.right));
        IndicatorPoint indicatorPoint3 = new IndicatorPoint();
        indicatorPoint3.left = f2;
        indicatorPoint3.right = f3;
        return indicatorPoint3;
    }
}
