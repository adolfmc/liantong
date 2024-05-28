package org.p415a.p418b;

import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ah */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12417ah extends AbstractC12571u {
    public C12417ah() {
    }

    public C12417ah(C12555g c12555g) {
        super(c12555g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12571u, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.mo1620b(48);
        c12567r.mo1620b(128);
        Enumeration d = mo1613d();
        while (d.hasMoreElements()) {
            c12567r.mo1625a((InterfaceC12554f) d.nextElement());
        }
        c12567r.mo1620b(0);
        c12567r.mo1620b(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        Enumeration d = mo1613d();
        int i = 0;
        while (d.hasMoreElements()) {
            i += ((InterfaceC12554f) d.nextElement()).mo1617h().mo1618c();
        }
        return i + 2 + 2;
    }
}
