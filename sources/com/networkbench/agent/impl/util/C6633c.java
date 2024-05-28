package com.networkbench.agent.impl.util;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6633c {

    /* renamed from: a */
    public static boolean f17085a = false;

    /* renamed from: b */
    public static String f17086b = "appmonitor";

    private C6633c() {
    }

    /* renamed from: a */
    public static void m9131a(String str) {
        f17086b = str;
    }

    /* renamed from: a */
    public static int m9133a(Object obj) {
        if (!f17085a || obj == null) {
            return -1;
        }
        return Log.i(f17086b, obj.toString());
    }

    /* renamed from: b */
    public static int m9124b(String str) {
        if (!f17085a || str == null) {
            return -1;
        }
        return Log.i(f17086b, str);
    }

    /* renamed from: c */
    public static int m9119c(String str) {
        if (!f17085a || str == null) {
            return -1;
        }
        return Log.d(f17086b, str);
    }

    /* renamed from: d */
    public static int m9114d(String str) {
        if (!f17085a || str == null) {
            return -1;
        }
        return Log.v(f17086b, str);
    }

    /* renamed from: a */
    public static int m9130a(String str, String str2) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.v(str, str2);
    }

    /* renamed from: a */
    public static void m9127a(Throwable th) {
        Log.d(f17086b, th.getMessage(), th);
    }

    /* renamed from: b */
    public static int m9123b(String str, String str2) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.d(str, str2);
    }

    /* renamed from: c */
    public static int m9118c(String str, String str2) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.i(str, str2);
    }

    /* renamed from: d */
    public static int m9113d(String str, String str2) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.w(str, str2);
    }

    /* renamed from: e */
    public static int m9109e(String str, String str2) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.e(str, str2);
    }

    /* renamed from: a */
    public static int m9128a(String str, Object... objArr) {
        if (f17085a) {
            return Log.v(str, m9126a(objArr));
        }
        return -1;
    }

    /* renamed from: b */
    public static int m9121b(String str, Object... objArr) {
        if (f17085a) {
            return Log.d(str, m9126a(objArr));
        }
        return -1;
    }

    /* renamed from: c */
    public static int m9116c(String str, Object... objArr) {
        if (f17085a) {
            return Log.i(str, m9126a(objArr));
        }
        return -1;
    }

    /* renamed from: d */
    public static int m9111d(String str, Object... objArr) {
        if (f17085a) {
            return Log.w(str, m9126a(objArr));
        }
        return -1;
    }

    /* renamed from: e */
    public static int m9107e(String str, Object... objArr) {
        if (f17085a) {
            return Log.e(str, m9126a(objArr));
        }
        return -1;
    }

    /* renamed from: a */
    private static String m9126a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            if (objArr != null && obj != null) {
                sb.append(obj.toString());
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static int m9129a(String str, String str2, Throwable th) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.v(str, str2, th);
    }

    /* renamed from: b */
    public static int m9122b(String str, String str2, Throwable th) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.d(str, str2, th);
    }

    /* renamed from: c */
    public static int m9117c(String str, String str2, Throwable th) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.i(str, str2, th);
    }

    /* renamed from: d */
    public static int m9112d(String str, String str2, Throwable th) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.w(str, str2, th);
    }

    /* renamed from: e */
    public static int m9108e(String str, String str2, Throwable th) {
        if (!f17085a || str2 == null) {
            return -1;
        }
        return Log.e(str, str2, th);
    }

    /* renamed from: a */
    public static int m9132a(Object obj, String str) {
        if (f17085a) {
            return Log.v(obj.getClass().getSimpleName(), str);
        }
        return -1;
    }

    /* renamed from: b */
    public static int m9125b(Object obj, String str) {
        if (f17085a) {
            return Log.d(obj.getClass().getSimpleName(), str);
        }
        return -1;
    }

    /* renamed from: c */
    public static int m9120c(Object obj, String str) {
        if (f17085a) {
            return Log.i(obj.getClass().getSimpleName(), str);
        }
        return -1;
    }

    /* renamed from: d */
    public static int m9115d(Object obj, String str) {
        if (f17085a) {
            return Log.w(obj.getClass().getSimpleName(), str);
        }
        return -1;
    }

    /* renamed from: e */
    public static int m9110e(Object obj, String str) {
        if (f17085a) {
            return Log.e(obj.getClass().getSimpleName(), str);
        }
        return -1;
    }
}
