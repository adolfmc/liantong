package com.nineoldandroids.animation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class IntEvaluator implements TypeEvaluator<Integer> {
    @Override // com.nineoldandroids.animation.TypeEvaluator
    public Integer evaluate(float f, Integer num, Integer num2) {
        int intValue = num.intValue();
        return Integer.valueOf((int) (intValue + (f * (num2.intValue() - intValue))));
    }
}
