package com.chinaunicon.jtwifilib.core.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtL {
    private static final String TAG = "装维SDK";
    public static boolean isDebug = true;

    private JtL() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /* renamed from: i */
    public static void m16340i(String str) {
        if (isDebug) {
            Log.i("装维SDK", str);
        }
    }

    /* renamed from: d */
    public static void m16344d(String str) {
        if (isDebug) {
            Log.d("装维SDK", str);
        }
    }

    /* renamed from: e */
    public static void m16342e(String str) {
        if (isDebug) {
            Log.e("装维SDK", str);
        }
    }

    /* renamed from: v */
    public static void m16338v(String str) {
        if (isDebug) {
            Log.v("装维SDK", str);
        }
    }

    /* renamed from: i */
    public static void m16339i(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }

    /* renamed from: d */
    public static void m16343d(String str, String str2) {
        if (isDebug) {
            Log.d(str, str2);
        }
    }

    /* renamed from: e */
    public static void m16341e(String str, String str2) {
        if (isDebug) {
            Log.e(str, str2);
        }
    }

    /* renamed from: v */
    public static void m16337v(String str, String str2) {
        if (isDebug) {
            Log.v(str, str2);
        }
    }
}
