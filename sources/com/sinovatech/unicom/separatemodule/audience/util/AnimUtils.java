package com.sinovatech.unicom.separatemodule.audience.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AnimUtils {
    public static ScaleAnimation scaleAnim(long j, float f, float f2, long j2) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setStartOffset(j2);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        scaleAnimation.setDuration(j);
        return scaleAnimation;
    }

    public static RotateAnimation rotateAnim(long j, int i, float f) {
        RotateAnimation rotateAnimation = new RotateAnimation(i, f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(j);
        return rotateAnimation;
    }

    public static TranslateAnimation translationAnim(long j, float f, float f2, float f3, float f4, long j2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(f, f2, f3, f4);
        translateAnimation.setDuration(j);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setStartOffset(j2);
        return translateAnimation;
    }

    public static AlphaAnimation alphaAnim(float f, float f2, long j, long j2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(j);
        alphaAnimation.setStartOffset(j2);
        return alphaAnimation;
    }
}
