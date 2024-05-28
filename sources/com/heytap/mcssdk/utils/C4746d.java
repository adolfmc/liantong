package com.heytap.mcssdk.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.utils.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4746d {

    /* renamed from: a */
    public static final String f10706a = "mcssdk---";

    /* renamed from: b */
    private static String f10707b = "MCS";

    /* renamed from: c */
    private static boolean f10708c = false;

    /* renamed from: d */
    private static boolean f10709d = false;

    /* renamed from: e */
    private static boolean f10710e = true;

    /* renamed from: f */
    private static boolean f10711f = true;

    /* renamed from: g */
    private static boolean f10712g = true;

    /* renamed from: h */
    private static String f10713h = "-->";

    /* renamed from: i */
    private static boolean f10714i = true;

    /* renamed from: a */
    public static String m15501a() {
        return f10707b;
    }

    /* renamed from: a */
    public static void m15500a(Exception exc) {
        if (!f10712g || exc == null) {
            return;
        }
        Log.e("mcssdk---", exc.getMessage());
    }

    /* renamed from: a */
    public static void m15499a(String str) {
        if (f10708c && f10714i) {
            Log.v("mcssdk---", f10707b + f10713h + str);
        }
    }

    /* renamed from: a */
    public static void m15498a(String str, String str2) {
        if (f10708c && f10714i) {
            Log.v(str, f10707b + f10713h + str2);
        }
    }

    /* renamed from: a */
    public static void m15497a(String str, Throwable th) {
        if (f10712g) {
            Log.e(str, th.toString());
        }
    }

    /* renamed from: a */
    public static void m15496a(boolean z) {
        f10708c = z;
    }

    /* renamed from: b */
    public static void m15494b(String str) {
        if (f10710e && f10714i) {
            Log.d("mcssdk---", f10707b + f10713h + str);
        }
    }

    /* renamed from: b */
    public static void m15493b(String str, String str2) {
        if (f10710e && f10714i) {
            Log.d(str, f10707b + f10713h + str2);
        }
    }

    /* renamed from: b */
    public static void m15492b(boolean z) {
        f10710e = z;
    }

    /* renamed from: b */
    public static boolean m15495b() {
        return f10708c;
    }

    /* renamed from: c */
    public static void m15490c(String str) {
        if (f10709d && f10714i) {
            Log.i("mcssdk---", f10707b + f10713h + str);
        }
    }

    /* renamed from: c */
    public static void m15489c(String str, String str2) {
        if (f10709d && f10714i) {
            Log.i(str, f10707b + f10713h + str2);
        }
    }

    /* renamed from: c */
    public static void m15488c(boolean z) {
        f10709d = z;
    }

    /* renamed from: c */
    public static boolean m15491c() {
        return f10710e;
    }

    /* renamed from: d */
    public static void m15486d(String str) {
        if (f10711f && f10714i) {
            Log.w("mcssdk---", f10707b + f10713h + str);
        }
    }

    /* renamed from: d */
    public static void m15485d(String str, String str2) {
        if (f10711f && f10714i) {
            Log.w(str, f10707b + f10713h + str2);
        }
    }

    /* renamed from: d */
    public static void m15484d(boolean z) {
        f10711f = z;
    }

    /* renamed from: d */
    public static boolean m15487d() {
        return f10709d;
    }

    /* renamed from: e */
    public static void m15482e(String str) {
        if (f10712g && f10714i) {
            Log.e("mcssdk---", f10707b + f10713h + str);
        }
    }

    /* renamed from: e */
    public static void m15481e(String str, String str2) {
        if (f10712g && f10714i) {
            Log.e(str, f10707b + f10713h + str2);
        }
    }

    /* renamed from: e */
    public static void m15480e(boolean z) {
        f10712g = z;
    }

    /* renamed from: e */
    public static boolean m15483e() {
        return f10711f;
    }

    /* renamed from: f */
    public static void m15478f(String str) {
        f10707b = str;
    }

    /* renamed from: f */
    public static void m15477f(boolean z) {
        f10714i = z;
        boolean z2 = f10714i;
        f10708c = z2;
        f10710e = z2;
        f10709d = z2;
        f10711f = z2;
        f10712g = z2;
    }

    /* renamed from: f */
    public static boolean m15479f() {
        return f10712g;
    }

    /* renamed from: g */
    public static void m15475g(String str) {
        f10713h = str;
    }

    /* renamed from: g */
    public static boolean m15476g() {
        return f10714i;
    }

    /* renamed from: h */
    public static String m15474h() {
        return f10713h;
    }
}
