package com.baidu.minivideo.arface.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MathUtils {
    private float toNorValue(float f, float f2, float f3, float f4, float f5) {
        return f4 + (((f5 - f4) / (f3 - f2)) * (f - f2));
    }

    private float toNorValue(float f, float f2, float f3) {
        if (f <= f2) {
            return toNorValue(f, 0.0f, f2, 0.0f, f3);
        }
        return toNorValue(f, f2, 1.0f, f3, 1.0f);
    }
}
