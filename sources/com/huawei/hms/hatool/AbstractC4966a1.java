package com.huawei.hms.hatool;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.a1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC4966a1 {
    /* renamed from: a */
    public static void m14817a(String str, String str2, long j) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            m14810h.m14507a(j);
        }
    }

    /* renamed from: a */
    public static boolean m14818a(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14509a();
        }
        return true;
    }

    /* renamed from: b */
    public static int m14816b(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14496d();
        }
        return 7;
    }

    /* renamed from: c */
    public static boolean m14815c(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14488g();
        }
        return true;
    }

    /* renamed from: d */
    public static String m14814d(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14490f() : "";
    }

    /* renamed from: e */
    public static boolean m14813e(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14485i();
        }
        return false;
    }

    /* renamed from: f */
    public static String m14812f(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14486h() : "";
    }

    /* renamed from: g */
    public static String m14811g(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14480n() : "";
    }

    /* renamed from: h */
    private static C5024s0 m14810h(String str, String str2) {
        C5003l1 m14514a = C5023s.m14511c().m14514a(str);
        if (m14514a != null) {
            if ("alltype".equals(str2)) {
                C5024s0 m14616a = m14514a.m14616a("oper");
                return m14616a == null ? m14514a.m14616a("maint") : m14616a;
            }
            return m14514a.m14616a(str2);
        }
        return null;
    }

    /* renamed from: i */
    public static Map<String, String> m14809i(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14483k();
        }
        return null;
    }

    /* renamed from: j */
    public static long m14808j(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14482l();
        }
        return 0L;
    }

    /* renamed from: k */
    public static int m14807k(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        if (m14810h != null) {
            return m14810h.m14503b();
        }
        return 10;
    }

    /* renamed from: l */
    public static String m14806l(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14479o() : "";
    }

    /* renamed from: m */
    public static String m14805m(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14477q() : "";
    }

    /* renamed from: n */
    public static String m14804n(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14481m() : "";
    }

    /* renamed from: o */
    public static String m14803o(String str, String str2) {
        C5024s0 m14810h = m14810h(str, str2);
        return m14810h != null ? m14810h.m14478p() : "";
    }
}
