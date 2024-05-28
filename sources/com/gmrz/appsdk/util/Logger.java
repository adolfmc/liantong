package com.gmrz.appsdk.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Logger {
    public static boolean isDebug = true;

    /* renamed from: d */
    public static void m15758d(String str, String str2) {
        if (isDebug) {
            Log.d(str, str2);
        }
    }

    /* renamed from: e */
    public static void m15757e(String str, String str2) {
        if (isDebug) {
            Log.e(str, str2);
        }
    }

    /* renamed from: i */
    public static void m15756i(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    /* renamed from: v */
    public static void m15755v(String str, String str2) {
        if (isDebug) {
            Log.v(str, str2);
        }
    }

    public static void wtf(String str, String str2) {
        if (isDebug) {
            Log.wtf(str, str2);
        }
    }
}
