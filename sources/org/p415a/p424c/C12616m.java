package org.p415a.p424c;

import java.io.IOException;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12616m extends AbstractC12607d implements InterfaceC12606c {

    /* renamed from: a */
    C12620q f25601a;

    /* renamed from: b */
    C12620q f25602b;

    /* renamed from: c */
    C12620q f25603c;

    public C12616m(C12592b c12592b) {
        this.f25601a = new C12620q(c12592b);
        this.f25602b = new C12620q(c12592b);
        this.f25603c = new C12620q(c12592b);
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        c12608e.m1564a(this.f25601a);
        c12608e.m1564a(this.f25602b);
        c12608e.m1564a(this.f25603c);
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public byte[] mo1536a() {
        try {
            return super.mo1536a();
        } catch (IOException unused) {
            return null;
        }
    }

    /* renamed from: b */
    public BigInteger m1546b() {
        return this.f25601a.m1543b();
    }

    /* renamed from: c */
    public BigInteger m1545c() {
        return this.f25602b.m1543b();
    }

    /* renamed from: d */
    public BigInteger m1544d() {
        return this.f25603c.m1543b();
    }
}
