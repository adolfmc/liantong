package com.bytedance.applog;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* renamed from: com.bytedance.applog.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3586g extends AbstractC3579f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3586g(C3591h c3591h) {
        super(c3591h);
        long j = c3591h.f8465d.f8900e.getLong("app_log_last_config_time", 0L);
        this.f8442d = j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0096, code lost:
        if (r0[1].length() == 16) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0376 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01c4  */
    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo17162c() {
        /*
            Method dump skipped, instructions count: 890
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3586g.mo17162c():boolean");
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: d */
    public String mo17161d() {
        return "configer";
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: e */
    public long[] mo17160e() {
        return C3612k.f8531g;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: g */
    public boolean mo17159g() {
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: h */
    public long mo17158h() {
        return this.f8439a.f8465d.f8900e.getLong("fetch_interval", 21600000L);
    }
}
