package org.p415a.p424c;

import java.io.IOException;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.y */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12628y extends AbstractC12607d implements InterfaceC12606c {

    /* renamed from: a */
    C12620q f25628a;

    /* renamed from: b */
    C12620q f25629b;

    public C12628y(C12592b c12592b) {
        this.f25628a = new C12620q(c12592b);
        this.f25629b = new C12620q(c12592b);
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        c12608e.m1564a(this.f25628a);
        c12608e.m1564a(this.f25629b);
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
    public BigInteger m1535b() {
        return this.f25629b.m1543b();
    }

    /* renamed from: c */
    public BigInteger m1534c() {
        return this.f25628a.m1543b();
    }
}
