package com.bytedance.applog;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* renamed from: com.bytedance.applog.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3618l extends AbstractC3579f {

    /* renamed from: g */
    public static final long[] f8541g = {10000};

    /* renamed from: f */
    public C3627m0 f8542f;

    public C3618l(C3591h c3591h) {
        super(c3591h);
        this.f8542f = new C3627m0("sender_", c3591h.f8465d);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0080  */
    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo17162c() {
        /*
            Method dump skipped, instructions count: 796
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3618l.mo17162c():boolean");
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: d */
    public String mo17161d() {
        return "sender";
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: e */
    public long[] mo17160e() {
        return f8541g;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: g */
    public boolean mo17159g() {
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: h */
    public long mo17158h() {
        return this.f8439a.f8465d.f8900e.getLong("batch_event_interval", 30000L);
    }
}
