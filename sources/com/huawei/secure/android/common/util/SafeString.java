package com.huawei.secure.android.common.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SafeString {

    /* renamed from: a */
    private static final String f12145a = "SafeString";

    /* renamed from: b */
    private static final String f12146b = "";

    public static String replace(String str, CharSequence charSequence, CharSequence charSequence2) {
        if (str != null && charSequence != null && charSequence2 != null) {
            try {
                return str.replace(charSequence, charSequence2);
            } catch (Exception e) {
                String str2 = f12145a;
                Log.e(str2, "replace: " + e.getMessage());
            }
        }
        return str;
    }

    public static String substring(String str, int i) {
        if (str == null || str.length() < i || i < 0) {
            return "";
        }
        try {
            return str.substring(i);
        } catch (Exception e) {
            String str2 = f12145a;
            Log.e(str2, "substring exception: " + e.getMessage());
            return "";
        }
    }

    public static String substring(String str, int i, int i2) {
        if (str == null || i < 0 || i2 > str.length() || i2 < i) {
            return "";
        }
        try {
            return str.substring(i, i2);
        } catch (Exception e) {
            String str2 = f12145a;
            Log.e(str2, "substring: " + e.getMessage());
            return "";
        }
    }
}
