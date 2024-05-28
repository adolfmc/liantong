package com.huawei.hms.hatool;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.huawei.hms.hatool.y */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5036y {

    /* renamed from: b */
    private static C5036y f11525b;

    /* renamed from: a */
    private volatile Map<String, C5016p0> f11526a = new HashMap();

    private C5036y() {
    }

    /* renamed from: a */
    private C5016p0 m14427a(String str) {
        if (!this.f11526a.containsKey(str)) {
            this.f11526a.put(str, new C5016p0());
        }
        return this.f11526a.get(str);
    }

    /* renamed from: a */
    public static C5036y m14428a() {
        if (f11525b == null) {
            m14425b();
        }
        return f11525b;
    }

    /* renamed from: b */
    private static synchronized void m14425b() {
        synchronized (C5036y.class) {
            if (f11525b == null) {
                f11525b = new C5036y();
            }
        }
    }

    /* renamed from: a */
    public C5016p0 m14426a(String str, long j) {
        C5016p0 m14427a = m14427a(str);
        m14427a.m14559a(j);
        return m14427a;
    }
}
