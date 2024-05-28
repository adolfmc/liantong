package com.huawei.secure.android.common.util;

import android.text.TextUtils;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SafeStringBuilder {

    /* renamed from: a */
    private static final String f12149a = "SafeStringBuilder";

    /* renamed from: b */
    private static final String f12150b = "";

    public static String substring(StringBuilder sb, int i) {
        if (TextUtils.isEmpty(sb) || sb.length() < i || i < 0) {
            return "";
        }
        try {
            return sb.substring(i);
        } catch (Exception e) {
            String str = f12149a;
            Log.e(str, "substring exception: " + e.getMessage());
            return "";
        }
    }

    public static String substring(StringBuilder sb, int i, int i2) {
        if (TextUtils.isEmpty(sb) || i < 0 || i2 > sb.length() || i2 < i) {
            return "";
        }
        try {
            return sb.substring(i, i2);
        } catch (Exception e) {
            String str = f12149a;
            Log.e(str, "substring: " + e.getMessage());
            return "";
        }
    }
}
