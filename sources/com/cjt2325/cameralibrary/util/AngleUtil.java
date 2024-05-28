package com.cjt2325.cameralibrary.util;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AngleUtil {
    public static int getSensorAngle(float f, float f2) {
        return Math.abs(f) > Math.abs(f2) ? f > 4.0f ? SubsamplingScaleImageView.ORIENTATION_270 : f < -4.0f ? 90 : 0 : (f2 <= 7.0f && f2 < -7.0f) ? 180 : 0;
    }
}
