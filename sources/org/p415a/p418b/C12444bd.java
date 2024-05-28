package org.p415a.p418b;

import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bd */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12444bd extends AbstractC12571u {

    /* renamed from: b */
    private int f25216b;

    public C12444bd() {
        this.f25216b = -1;
    }

    public C12444bd(C12555g c12555g) {
        super(c12555g);
        this.f25216b = -1;
    }

    /* renamed from: j */
    private int m1712j() {
        if (this.f25216b < 0) {
            int i = 0;
            Enumeration d = mo1613d();
            while (d.hasMoreElements()) {
                i += ((InterfaceC12554f) d.nextElement()).mo1617h().mo1592f().mo1618c();
            }
            this.f25216b = i;
        }
        return this.f25216b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12571u, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        C12567r mo1630a = c12567r.mo1630a();
        int m1712j = m1712j();
        c12567r.mo1620b(48);
        c12567r.m1629a(m1712j);
        Enumeration d = mo1613d();
        while (d.hasMoreElements()) {
            mo1630a.mo1625a((InterfaceC12554f) d.nextElement());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int m1712j = m1712j();
        return C12466bz.m1694a(m1712j) + 1 + m1712j;
    }
}
