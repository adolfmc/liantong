package org.p415a.p424c;

import java.io.IOException;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12612i extends AbstractC12607d implements InterfaceC12606c {

    /* renamed from: a */
    C12620q f25592a;

    /* renamed from: b */
    C12620q f25593b;

    /* renamed from: c */
    C12620q f25594c;

    /* renamed from: d */
    C12620q f25595d;

    public C12612i(C12592b c12592b) {
        this.f25592a = new C12620q(c12592b);
        this.f25593b = new C12620q(c12592b);
        this.f25594c = new C12620q(c12592b);
        this.f25595d = new C12620q(c12592b);
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        c12608e.m1564a(this.f25592a);
        c12608e.m1564a(this.f25593b);
        c12608e.m1564a(this.f25594c);
        c12608e.m1564a(this.f25595d);
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
    public BigInteger m1557b() {
        return this.f25594c.m1543b();
    }

    /* renamed from: c */
    public BigInteger m1556c() {
        return this.f25592a.m1543b();
    }

    /* renamed from: d */
    public BigInteger m1555d() {
        return this.f25593b.m1543b();
    }

    /* renamed from: e */
    public BigInteger m1554e() {
        return this.f25595d.m1543b();
    }
}
