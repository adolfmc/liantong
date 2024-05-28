package org.p415a.p418b;

import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.aj */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12419aj extends AbstractC12573w {
    public C12419aj() {
    }

    public C12419aj(C12555g c12555g) {
        super(c12555g, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12573w, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.mo1620b(49);
        c12567r.mo1620b(128);
        Enumeration b = m1606b();
        while (b.hasMoreElements()) {
            c12567r.mo1625a((InterfaceC12554f) b.nextElement());
        }
        c12567r.mo1620b(0);
        c12567r.mo1620b(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        Enumeration b = m1606b();
        int i = 0;
        while (b.hasMoreElements()) {
            i += ((InterfaceC12554f) b.nextElement()).mo1617h().mo1618c();
        }
        return i + 2 + 2;
    }
}
