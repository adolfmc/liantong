package com.alipay.apmobilesecuritysdk.p108e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p106c.C1961a;
import com.alipay.security.mobile.module.p110a.C2081a;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.e.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1977i {

    /* renamed from: a */
    private static String f3496a = "";

    /* renamed from: b */
    private static String f3497b = "";

    /* renamed from: c */
    private static String f3498c = "";

    /* renamed from: d */
    private static String f3499d = "";

    /* renamed from: e */
    private static String f3500e = "";

    /* renamed from: f */
    private static Map<String, String> f3501f = new HashMap();

    /* renamed from: a */
    public static synchronized String m20982a(String str) {
        synchronized (C1977i.class) {
            String str2 = "apdidTokenCache" + str;
            if (f3501f.containsKey(str2)) {
                String str3 = f3501f.get(str2);
                if (C2081a.m20573b(str3)) {
                    return str3;
                }
            }
            return "";
        }
    }

    /* renamed from: a */
    public static synchronized void m20986a() {
        synchronized (C1977i.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m20984a(C1970b c1970b) {
        synchronized (C1977i.class) {
            if (c1970b != null) {
                f3496a = c1970b.f3482a;
                f3497b = c1970b.f3483b;
                f3498c = c1970b.f3484c;
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m20983a(C1971c c1971c) {
        synchronized (C1977i.class) {
            if (c1971c != null) {
                f3496a = c1971c.f3485a;
                f3497b = c1971c.f3486b;
                f3499d = c1971c.f3488d;
                f3500e = c1971c.f3489e;
                f3498c = c1971c.f3487c;
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m20981a(String str, String str2) {
        synchronized (C1977i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f3501f.containsKey(str3)) {
                f3501f.remove(str3);
            }
            f3501f.put(str3, str2);
        }
    }

    /* renamed from: a */
    public static synchronized boolean m20985a(Context context, String str) {
        boolean z;
        synchronized (C1977i.class) {
            long j = 86400000;
            try {
                long m21003a = C1976h.m21003a(context);
                if (m21003a >= 0) {
                    j = m21003a;
                }
            } catch (Throwable unused) {
            }
            try {
            } catch (Throwable th) {
                C1961a.m21046a(th);
            }
            z = Math.abs(System.currentTimeMillis() - C1976h.m20987h(context, str)) < j;
        }
        return z;
    }

    /* renamed from: b */
    public static synchronized String m20980b() {
        String str;
        synchronized (C1977i.class) {
            str = f3496a;
        }
        return str;
    }

    /* renamed from: b */
    public static void m20979b(String str) {
        f3496a = str;
    }

    /* renamed from: c */
    public static synchronized String m20978c() {
        String str;
        synchronized (C1977i.class) {
            str = f3497b;
        }
        return str;
    }

    /* renamed from: c */
    public static void m20977c(String str) {
        f3497b = str;
    }

    /* renamed from: d */
    public static synchronized String m20976d() {
        String str;
        synchronized (C1977i.class) {
            str = f3499d;
        }
        return str;
    }

    /* renamed from: d */
    public static void m20975d(String str) {
        f3498c = str;
    }

    /* renamed from: e */
    public static synchronized String m20974e() {
        String str;
        synchronized (C1977i.class) {
            str = f3500e;
        }
        return str;
    }

    /* renamed from: e */
    public static void m20973e(String str) {
        f3499d = str;
    }

    /* renamed from: f */
    public static synchronized String m20972f() {
        String str;
        synchronized (C1977i.class) {
            str = f3498c;
        }
        return str;
    }

    /* renamed from: f */
    public static void m20971f(String str) {
        f3500e = str;
    }

    /* renamed from: g */
    public static synchronized C1971c m20970g() {
        C1971c c1971c;
        synchronized (C1977i.class) {
            c1971c = new C1971c(f3496a, f3497b, f3498c, f3499d, f3500e);
        }
        return c1971c;
    }

    /* renamed from: h */
    public static void m20969h() {
        f3501f.clear();
        f3496a = "";
        f3497b = "";
        f3499d = "";
        f3500e = "";
        f3498c = "";
    }
}
