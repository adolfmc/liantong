package com.bytedance.sdk.openadsdk.api;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.sdk.openadsdk.api.mb */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3972mb {

    /* renamed from: mb */
    private static boolean f9492mb = false;

    /* renamed from: ox */
    private static int f9493ox = 4;

    /* renamed from: mb */
    public static void m16550mb(int i) {
        f9493ox = i;
    }

    /* renamed from: mb */
    public static void m16551mb() {
        f9492mb = true;
        m16550mb(3);
    }

    /* renamed from: mb */
    public static void m16548mb(String str, String str2) {
        if (f9492mb && str2 != null && f9493ox <= 2) {
            Log.v(str, str2);
        }
    }

    /* renamed from: ox */
    public static void m16544ox(String str, String str2) {
        if (f9492mb && str2 != null && f9493ox <= 3) {
            Log.d(str, str2);
        }
    }

    /* renamed from: b */
    public static void m16554b(String str, String str2) {
        if (f9492mb && str2 != null && f9493ox <= 4) {
            Log.i(str, str2);
        }
    }

    /* renamed from: mb */
    public static void m16549mb(String str) {
        if (f9492mb) {
            m16552hj("TTLogger", str);
        }
    }

    /* renamed from: hj */
    public static void m16552hj(String str, String str2) {
        if (f9492mb && str2 != null && f9493ox <= 5) {
            Log.w(str, str2);
        }
    }

    /* renamed from: mb */
    public static void m16547mb(String str, String str2, Throwable th) {
        if (f9492mb) {
            if (!(str2 == null && th == null) && f9493ox <= 5) {
                Log.w(str, str2, th);
            }
        }
    }

    /* renamed from: mb */
    public static void m16546mb(String str, Object... objArr) {
        if (f9492mb && objArr != null && f9493ox <= 5) {
            Log.v(str, m16545mb(objArr));
        }
    }

    /* renamed from: h */
    public static void m16553h(String str, String str2) {
        if (f9492mb && str2 != null && f9493ox <= 6) {
            Log.e(str, str2);
        }
    }

    /* renamed from: ox */
    public static void m16543ox(String str, String str2, Throwable th) {
        if (f9492mb) {
            if (!(str2 == null && th == null) && f9493ox <= 6) {
                Log.e(str, str2, th);
            }
        }
    }

    /* renamed from: mb */
    private static String m16545mb(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            if (obj != null) {
                sb.append(obj.toString());
            } else {
                sb.append(" null ");
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}
