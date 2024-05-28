package com.huawei.hms.hatool;

import java.util.ArrayList;
import java.util.UUID;

/* renamed from: com.huawei.hms.hatool.i1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4992i1 {

    /* renamed from: a */
    private String f11417a;

    /* renamed from: b */
    private String f11418b;

    /* renamed from: c */
    private String f11419c;

    /* renamed from: d */
    private String f11420d;

    /* renamed from: e */
    private long f11421e;

    public C4992i1(String str, String str2, String str3, String str4, long j) {
        this.f11417a = str;
        this.f11418b = str2;
        this.f11419c = str3;
        this.f11420d = str4;
        this.f11421e = j;
    }

    /* renamed from: a */
    public void m14674a() {
        C5029v.m14455c("StreamEventHandler", "Begin to handle stream events...");
        C4971b1 c4971b1 = new C4971b1();
        c4971b1.m14789b(this.f11419c);
        c4971b1.m14785d(this.f11418b);
        c4971b1.m14792a(this.f11420d);
        c4971b1.m14787c(String.valueOf(this.f11421e));
        if ("oper".equals(this.f11418b) && AbstractC5038z.m14412i(this.f11417a, "oper")) {
            C5016p0 m14426a = C5036y.m14428a().m14426a(this.f11417a, this.f11421e);
            String m14560a = m14426a.m14560a();
            Boolean valueOf = Boolean.valueOf(m14426a.m14556b());
            c4971b1.m14783f(m14560a);
            c4971b1.m14784e(String.valueOf(valueOf));
        }
        String replace = UUID.randomUUID().toString().replace("-", "");
        ArrayList arrayList = new ArrayList();
        arrayList.add(c4971b1);
        new C5002l0(this.f11417a, this.f11418b, AbstractC5020q0.m14530g(), arrayList, replace).m14624a();
    }
}
