package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EasyL {
    private static boolean sEnabled;

    public static void setEnabled(boolean z) {
        sEnabled = z;
    }

    /* renamed from: v */
    public static void m7992v(String str, String str2) {
        if (sEnabled) {
            Log.v(str, str2);
        }
    }

    /* renamed from: d */
    public static void m7996d(String str, String str2) {
        if (sEnabled) {
            Log.d(str, str2);
        }
    }

    /* renamed from: i */
    public static void m7993i(String str, String str2) {
        if (sEnabled) {
            Log.i(str, str2);
        }
    }

    /* renamed from: w */
    public static void m7991w(String str, String str2) {
        if (sEnabled) {
            Log.w(str, str2);
        }
    }

    /* renamed from: e */
    public static void m7994e(String str, String str2) {
        if (sEnabled) {
            Log.e(str, str2);
        }
    }

    /* renamed from: d */
    public static void m7995d(String str, Object... objArr) {
        if (sEnabled) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : objArr) {
                sb.append(obj);
                sb.append(" ");
            }
            Log.d(str, sb.toString());
        }
    }
}
