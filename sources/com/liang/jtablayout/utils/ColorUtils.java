package com.liang.jtablayout.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ColorUtils {
    public static int getColorFrom(int i, int i2, float f) {
        int red = Color.red(i);
        int blue = Color.blue(i);
        int green = Color.green(i);
        return Color.argb(255, (int) (red + ((Color.red(i2) - red) * f) + 0.5d), (int) (green + ((Color.green(i2) - green) * f) + 0.5d), (int) (blue + ((Color.blue(i2) - blue) * f) + 0.5d));
    }

    public static ColorStateList createColorStateList(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{16842913}, new int[0]}, new int[]{i2, i});
    }
}
