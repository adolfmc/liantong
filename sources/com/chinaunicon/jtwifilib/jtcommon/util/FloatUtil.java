package com.chinaunicon.jtwifilib.jtcommon.util;

import java.text.DecimalFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FloatUtil {
    public static float changeFloatTwo(float f) {
        return Float.valueOf(new DecimalFormat(".00").format(f)).floatValue();
    }
}
