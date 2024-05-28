package org.p415a.p418b;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bi */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12449bi extends AbstractC12576z {

    /* renamed from: e */
    private static final byte[] f25221e = new byte[0];

    public C12449bi(boolean z, int i, InterfaceC12554f interfaceC12554f) {
        super(z, i, interfaceC12554f);
    }

    @Override // org.p415a.p418b.AbstractC12576z, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    void mo1597a(C12567r c12567r) {
        boolean z = this.f25522b;
        int i = C0567f.f1819h;
        if (z) {
            c12567r.m1627a(C0567f.f1819h, this.f25521a, f25221e);
            return;
        }
        AbstractC12570t mo1592f = this.f25524d.mo1617h().mo1592f();
        if (this.f25523c) {
            c12567r.m1628a(C0567f.f1819h, this.f25521a);
            c12567r.m1629a(mo1592f.mo1618c());
            c12567r.mo1625a((InterfaceC12554f) mo1592f);
            return;
        }
        if (!mo1592f.mo1611a()) {
            i = 128;
        }
        c12567r.m1628a(i, this.f25521a);
        c12567r.m1624a(mo1592f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        if (this.f25522b || this.f25523c) {
            return true;
        }
        return this.f25524d.mo1617h().mo1592f().mo1611a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int m1692b;
        if (this.f25522b) {
            return C12466bz.m1692b(this.f25521a) + 1;
        }
        int mo1618c = this.f25524d.mo1617h().mo1592f().mo1618c();
        if (this.f25523c) {
            m1692b = C12466bz.m1692b(this.f25521a) + C12466bz.m1694a(mo1618c);
        } else {
            mo1618c--;
            m1692b = C12466bz.m1692b(this.f25521a);
        }
        return m1692b + mo1618c;
    }
}
