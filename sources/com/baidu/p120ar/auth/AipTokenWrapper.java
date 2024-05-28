package com.baidu.p120ar.auth;

import android.content.Context;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.utils.ReflectionUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.AipTokenWrapper */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AipTokenWrapper {
    private static final String AIP_AUTH_CLASS = "com.baidu.aip.auth.Auth";
    private static int sSupportTag;

    AipTokenWrapper() {
    }

    public static boolean isSupportAip() {
        if (sSupportTag == 0) {
            try {
                Class.forName("com.baidu.aip.auth.Auth");
                LibLoader.require("aip-native-auth");
                sSupportTag = 1;
            } catch (Throwable unused) {
                sSupportTag = -1;
            }
        }
        return sSupportTag == 1;
    }

    public static String getToken(Context context) {
        Object newInstance = ReflectionUtils.getNewInstance("com.baidu.aip.auth.Auth");
        return newInstance != null ? (String) ReflectionUtils.invokeMethod(newInstance, "com.baidu.aip.auth.Auth", "getToken", context, Context.class) : "";
    }
}
