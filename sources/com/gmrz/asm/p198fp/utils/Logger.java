package com.gmrz.asm.p198fp.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.utils.Logger */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Logger {
    private static final String TAG = "DemoSDK";
    public static boolean isDebug = true;

    private Logger() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /* renamed from: i */
    public static void m15750i(String str) {
        if (isDebug) {
            Log.i("DemoSDK", str);
        }
    }

    /* renamed from: d */
    public static void m15754d(String str) {
        if (isDebug) {
            Log.d("DemoSDK", str);
        }
    }

    /* renamed from: e */
    public static void m15752e(String str) {
        if (isDebug) {
            Log.e("DemoSDK", str);
        }
    }

    /* renamed from: v */
    public static void m15748v(String str) {
        if (isDebug) {
            Log.v("DemoSDK", str);
        }
    }

    /* renamed from: i */
    public static void m15749i(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    /* renamed from: d */
    public static void m15753d(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    /* renamed from: e */
    public static void m15751e(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    /* renamed from: v */
    public static void m15747v(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }
}
