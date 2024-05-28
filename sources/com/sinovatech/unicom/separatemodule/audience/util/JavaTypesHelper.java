package com.sinovatech.unicom.separatemodule.audience.util;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class JavaTypesHelper {
    public static int toInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static long toLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j;
        }
    }

    public static float toFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception unused) {
            return f;
        }
    }

    public static double toDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return d;
        }
    }

    public static boolean toBoolean(String str, boolean z) {
        if (str == null) {
            return z;
        }
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception unused) {
            return z;
        }
    }
}
