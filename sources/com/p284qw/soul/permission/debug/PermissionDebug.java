package com.p284qw.soul.permission.debug;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.debug.PermissionDebug */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PermissionDebug {
    private static boolean isDebug;

    public static void setDebug(boolean z) {
        isDebug = z;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    /* renamed from: d */
    public static void m8226d(String str, String str2) {
        if (isDebug) {
            Log.d(str, str2);
        }
    }

    /* renamed from: w */
    public static void m8222w(String str, String str2) {
        if (isDebug) {
            Log.w(str, str2);
        }
    }

    /* renamed from: e */
    public static void m8225e(String str, String str2) {
        if (isDebug) {
            Log.e(str, str2);
        }
    }

    /* renamed from: v */
    public static void m8223v(String str, String str2) {
        if (isDebug) {
            Log.v(str, str2);
        }
    }

    /* renamed from: i */
    public static void m8224i(String str, String str2) {
        if (isDebug) {
            Log.i(str, str2);
        }
    }
}
