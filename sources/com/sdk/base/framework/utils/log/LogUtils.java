package com.sdk.base.framework.utils.log;

import android.util.Log;
import com.sdk.base.module.manager.SDKManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LogUtils {
    public static final String CSTR_TIMES_END = "ms\n";
    public static final String CSTR_TIMES_START = "\n时间差=";
    private static boolean DEBUG = false;
    private static final int RETURN_NOLOG = -1;
    private static long lastTimeMillis;

    /* renamed from: d */
    public static int m8187d(String str, String str2, Boolean bool) {
        if (str2 == null) {
            str2 = "";
        }
        if (bool.booleanValue()) {
            return Log.d(str, str2);
        }
        return -1;
    }

    public static int d_yl(String str, String str2, int i) {
        if (DEBUG) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - lastTimeMillis;
            String str3 = "时间戳=" + currentTimeMillis;
            int i2 = (j > 500 ? 1 : (j == 500 ? 0 : -1));
            if (i2 > 0) {
                str3 = str3 + "\n■★■★■★■★■★■★■★■★■★■\n";
            }
            String str4 = (str3 + "\n时间差=" + j + "ms\n") + str2;
            if (i2 > 0) {
                str4 = str4 + "\n\n■★■★■★■★■★■★■★■★■★■\n";
            }
            LiveDataBus3.get();
            LiveDataBus3.post(str4);
            lastTimeMillis = currentTimeMillis;
            if (i >= 2 || !SDKManager.isDebug()) {
                return -1;
            }
            Log.d("CUCC_OATH", str + str4);
            return -1;
        }
        return -1;
    }

    /* renamed from: e */
    public static int m8186e(String str, String str2, Boolean bool) {
        if (str2 == null) {
            str2 = "";
        }
        if (bool.booleanValue()) {
            return Log.e(str, str2);
        }
        return -1;
    }

    /* renamed from: i */
    public static int m8185i(String str, String str2, Boolean bool) {
        if (str2 == null) {
            str2 = "";
        }
        if (bool.booleanValue()) {
            return Log.i(str, str2);
        }
        return -1;
    }

    public static boolean isDEBUG() {
        return DEBUG;
    }

    public static void setDEBUG(boolean z) {
        DEBUG = z;
    }

    /* renamed from: w */
    public static int m8184w(String str, String str2, Boolean bool) {
        if (str2 == null) {
            str2 = "";
        }
        if (bool.booleanValue()) {
            return Log.w(str, str2);
        }
        return -1;
    }
}
