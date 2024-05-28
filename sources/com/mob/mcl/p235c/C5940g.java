package com.mob.mcl.p235c;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.mob.mcl.c.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5940g {

    /* renamed from: a */
    private static C5940g f14612a;

    /* renamed from: b */
    private List<Map<String, Object>> f14613b;

    private C5940g() {
    }

    /* renamed from: a */
    public static C5940g m12022a() {
        if (f14612a == null) {
            synchronized (C5940g.class) {
                if (f14612a == null) {
                    f14612a = new C5940g();
                }
            }
        }
        return f14612a;
    }

    /* renamed from: b */
    public boolean m12020b() {
        List<Map<String, Object>> list = this.f14613b;
        return (list == null || list.isEmpty()) ? false : true;
    }

    /* renamed from: c */
    public List<Map<String, Object>> m12018c() {
        return this.f14613b;
    }

    /* renamed from: a */
    public void m12021a(Map<String, Object> map) {
        if (this.f14613b == null) {
            this.f14613b = new ArrayList();
        }
        this.f14613b.add(map);
    }

    /* renamed from: b */
    public void m12019b(Map<String, Object> map) {
        List<Map<String, Object>> list = this.f14613b;
        if (list == null || !list.contains(map)) {
            return;
        }
        this.f14613b.remove(map);
    }
}
