package org.p415a.p418b;

import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bp */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12456bp extends AbstractC12571u {

    /* renamed from: b */
    private int f25227b;

    public C12456bp() {
        this.f25227b = -1;
    }

    public C12456bp(C12555g c12555g) {
        super(c12555g);
        this.f25227b = -1;
    }

    /* renamed from: j */
    private int m1705j() {
        if (this.f25227b < 0) {
            int i = 0;
            Enumeration d = mo1613d();
            while (d.hasMoreElements()) {
                i += ((InterfaceC12554f) d.nextElement()).mo1617h().mo1591g().mo1618c();
            }
            this.f25227b = i;
        }
        return this.f25227b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12571u, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        C12567r mo1621b = c12567r.mo1621b();
        int m1705j = m1705j();
        c12567r.mo1620b(48);
        c12567r.m1629a(m1705j);
        Enumeration d = mo1613d();
        while (d.hasMoreElements()) {
            mo1621b.mo1625a((InterfaceC12554f) d.nextElement());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int m1705j = m1705j();
        return C12466bz.m1694a(m1705j) + 1 + m1705j;
    }
}
