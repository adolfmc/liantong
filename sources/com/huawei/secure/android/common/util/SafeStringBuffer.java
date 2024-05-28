package com.huawei.secure.android.common.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SafeStringBuffer {

    /* renamed from: a */
    private static final String f12147a = "SafeStringBuffer";

    /* renamed from: b */
    private static final String f12148b = "";

    public static String substring(StringBuffer stringBuffer, int i) {
        if (stringBuffer == null || stringBuffer.length() < i || i < 0) {
            return "";
        }
        try {
            return stringBuffer.substring(i);
        } catch (Exception e) {
            String str = f12147a;
            Log.e(str, "substring exception: " + e.getMessage());
            return "";
        }
    }

    public static String substring(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null || i < 0 || i2 > stringBuffer.length() || i2 < i) {
            return "";
        }
        try {
            return stringBuffer.substring(i, i2);
        } catch (Exception e) {
            String str = f12147a;
            Log.e(str, "substring: " + e.getMessage());
            return "";
        }
    }
}
