package com.billy.android.swipe.calculator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ScaledCalculator implements SwipeDistanceCalculator {
    private float mScale;

    public ScaledCalculator(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("scale must be positive");
        }
        this.mScale = f;
    }

    @Override // com.billy.android.swipe.calculator.SwipeDistanceCalculator
    public int calculateSwipeDistance(int i, float f) {
        return (int) (i * this.mScale);
    }

    @Override // com.billy.android.swipe.calculator.SwipeDistanceCalculator
    public int calculateSwipeOpenDistance(int i) {
        return (int) (i / this.mScale);
    }
}
