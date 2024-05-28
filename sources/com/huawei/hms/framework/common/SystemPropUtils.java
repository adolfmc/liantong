package com.huawei.hms.framework.common;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SystemPropUtils {
    private static final String TAG = "SystemPropUtils";

    public static String getProperty(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            Logger.m15045w(TAG, "reflect class for method has exception.");
            return str4;
        }
        try {
            Class<?> cls = Class.forName(str3);
            return (String) cls.getMethod(str, String.class, String.class).invoke(cls, str2, str4);
        } catch (Exception e) {
            Logger.m15051e(TAG, "getProperty catch exception: ", e);
            return str4;
        }
    }
}
