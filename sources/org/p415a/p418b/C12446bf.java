package org.p415a.p418b;

import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bf */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12446bf extends AbstractC12573w {

    /* renamed from: a */
    private int f25218a;

    public C12446bf() {
        this.f25218a = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12446bf(C12555g c12555g, boolean z) {
        super(c12555g, z);
        this.f25218a = -1;
    }

    /* renamed from: k */
    private int m1711k() {
        if (this.f25218a < 0) {
            int i = 0;
            Enumeration b = m1606b();
            while (b.hasMoreElements()) {
                i += ((InterfaceC12554f) b.nextElement()).mo1617h().mo1592f().mo1618c();
            }
            this.f25218a = i;
        }
        return this.f25218a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12573w, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        C12567r mo1630a = c12567r.mo1630a();
        int m1711k = m1711k();
        c12567r.mo1620b(49);
        c12567r.m1629a(m1711k);
        Enumeration b = m1606b();
        while (b.hasMoreElements()) {
            mo1630a.mo1625a((InterfaceC12554f) b.nextElement());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int m1711k = m1711k();
        return C12466bz.m1694a(m1711k) + 1 + m1711k;
    }
}
