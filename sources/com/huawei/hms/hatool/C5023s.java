package com.huawei.hms.hatool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.huawei.hms.hatool.s */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5023s {

    /* renamed from: b */
    static Map<String, C5003l1> f11489b = new HashMap();

    /* renamed from: c */
    private static C5023s f11490c;

    /* renamed from: a */
    private C4986g1 f11491a = new C4986g1();

    private C5023s() {
    }

    /* renamed from: c */
    public static C5023s m14511c() {
        if (f11490c == null) {
            m14510d();
        }
        return f11490c;
    }

    /* renamed from: d */
    private static synchronized void m14510d() {
        synchronized (C5023s.class) {
            if (f11490c == null) {
                f11490c = new C5023s();
            }
        }
    }

    /* renamed from: a */
    public C5003l1 m14514a(String str) {
        return f11489b.get(str);
    }

    /* renamed from: a */
    public Set<String> m14515a() {
        return f11489b.keySet();
    }

    /* renamed from: a */
    public void m14513a(String str, C5003l1 c5003l1) {
        f11489b.put(str, c5003l1);
    }

    /* renamed from: b */
    public C4986g1 m14512b() {
        return this.f11491a;
    }
}
