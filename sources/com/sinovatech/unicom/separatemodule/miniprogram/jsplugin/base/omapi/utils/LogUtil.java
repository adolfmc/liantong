package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils;

import android.text.TextUtils;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LogUtil {
    private static final String DEFAULT_TAG = "LogUtil_Omapi";
    public static final int LEVEL_LOG_D = 2;
    public static final int LEVEL_LOG_E = 3;
    public static final int LEVEL_LOG_I = 1;
    public static final int LEVEL_LOG_V = 4;
    public static final int LEVEL_LOG_W = 5;
    private static boolean isLog = true;

    private LogUtil() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static boolean isLog() {
        return isLog;
    }

    public static void setLog(boolean z) {
        isLog = z;
    }

    /* renamed from: d */
    public static void m7989d(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, str2);
    }

    /* renamed from: d */
    public static void m7990d(String str) {
        m7989d("LogUtil_Omapi", str);
    }

    /* renamed from: v */
    public static void m7983v(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.v(str, str2);
    }

    /* renamed from: v */
    public static void m7984v(String str) {
        m7983v("LogUtil_Omapi", str);
    }

    /* renamed from: w */
    public static void m7981w(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, str2);
    }

    /* renamed from: w */
    public static void m7982w(String str) {
        m7981w("LogUtil_Omapi", str);
    }

    /* renamed from: i */
    public static void m7985i(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
    }

    /* renamed from: i */
    public static void m7986i(String str) {
        m7985i("LogUtil_Omapi", str);
    }

    /* renamed from: e */
    public static void m7987e(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, str2);
    }

    /* renamed from: e */
    public static void m7988e(String str) {
        m7987e("LogUtil_Omapi", str);
    }

    public static void dTAGLongInfo(String str, String str2) {
        String substring;
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        String trim = str2.trim();
        int i = 0;
        while (i < trim.length()) {
            int i2 = i + 3500;
            if (trim.length() <= i2) {
                substring = trim.substring(i);
            } else {
                substring = trim.substring(i, i2);
            }
            m7989d(str, substring.trim());
            i = i2;
        }
    }

    public static void dLongInfo(String str) {
        dTAGLongInfo("LogUtil_Omapi", str);
    }
}
