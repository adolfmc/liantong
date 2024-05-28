package com.huawei.hms.hatool;

/* renamed from: com.huawei.hms.hatool.l1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5003l1 {

    /* renamed from: a */
    private C5024s0 f11454a;

    /* renamed from: b */
    private C5024s0 f11455b;

    /* renamed from: c */
    private C5024s0 f11456c;

    /* renamed from: d */
    private C5024s0 f11457d;

    public C5003l1(String str) {
    }

    /* renamed from: a */
    public C5024s0 m14618a() {
        return this.f11456c;
    }

    /* renamed from: a */
    public C5024s0 m14616a(String str) {
        if (str.equals("oper")) {
            return m14613c();
        }
        if (str.equals("maint")) {
            return m14615b();
        }
        if (str.equals("diffprivacy")) {
            return m14618a();
        }
        if (str.equals("preins")) {
            return m14612d();
        }
        C5029v.m14451f("hmsSdk", "HiAnalyticsInstData.getConfig(type): wrong type: " + str);
        return null;
    }

    /* renamed from: a */
    public void m14617a(C5024s0 c5024s0) {
        this.f11454a = c5024s0;
    }

    /* renamed from: b */
    public C5024s0 m14615b() {
        return this.f11454a;
    }

    /* renamed from: b */
    public void m14614b(C5024s0 c5024s0) {
        this.f11455b = c5024s0;
    }

    /* renamed from: c */
    public C5024s0 m14613c() {
        return this.f11455b;
    }

    /* renamed from: d */
    public C5024s0 m14612d() {
        return this.f11457d;
    }
}
