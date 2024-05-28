package com.bytedance.applog;

import org.json.JSONObject;

/* renamed from: com.bytedance.applog.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3605j extends AbstractC3579f {

    /* renamed from: f */
    public final String f8519f;

    public C3605j(C3591h c3591h, String str) {
        super(c3591h);
        this.f8519f = str;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: c */
    public boolean mo17162c() {
        C3607j1.m17259a((JSONObject) null, this.f8519f);
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: d */
    public String mo17161d() {
        return "RangersEventVerify";
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: e */
    public long[] mo17160e() {
        return new long[]{1000};
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: g */
    public boolean mo17159g() {
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: h */
    public long mo17158h() {
        return 1000L;
    }
}
