package org.p415a.p418b;

import java.util.Enumeration;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bw */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12463bw extends AbstractC12571u {

    /* renamed from: b */
    private byte[] f25239b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12463bw(byte[] bArr) {
        this.f25239b = bArr;
    }

    /* renamed from: j */
    private void m1699j() {
        C12462bv c12462bv = new C12462bv(this.f25239b);
        while (c12462bv.hasMoreElements()) {
            this.f25515a.addElement(c12462bv.nextElement());
        }
        this.f25239b = null;
    }

    @Override // org.p415a.p418b.AbstractC12571u
    /* renamed from: a */
    public synchronized InterfaceC12554f mo1616a(int i) {
        if (this.f25239b != null) {
            m1699j();
        }
        return super.mo1616a(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12571u, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        byte[] bArr = this.f25239b;
        if (bArr != null) {
            c12567r.m1626a(48, bArr);
        } else {
            super.mo1591g().mo1597a(c12567r);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        byte[] bArr = this.f25239b;
        return bArr != null ? C12466bz.m1694a(bArr.length) + 1 + this.f25239b.length : super.mo1591g().mo1618c();
    }

    @Override // org.p415a.p418b.AbstractC12571u
    /* renamed from: d */
    public synchronized Enumeration mo1613d() {
        if (this.f25239b == null) {
            return super.mo1613d();
        }
        return new C12462bv(this.f25239b);
    }

    @Override // org.p415a.p418b.AbstractC12571u
    /* renamed from: e */
    public synchronized int mo1612e() {
        if (this.f25239b != null) {
            m1699j();
        }
        return super.mo1612e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12571u, org.p415a.p418b.AbstractC12570t
    /* renamed from: f */
    public AbstractC12570t mo1592f() {
        if (this.f25239b != null) {
            m1699j();
        }
        return super.mo1592f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12571u, org.p415a.p418b.AbstractC12570t
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        if (this.f25239b != null) {
            m1699j();
        }
        return super.mo1591g();
    }
}
