package org.p415a.p418b;

import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bq */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12457bq extends AbstractC12573w {

    /* renamed from: a */
    private int f25228a;

    public C12457bq() {
        this.f25228a = -1;
    }

    public C12457bq(C12555g c12555g) {
        super(c12555g, false);
        this.f25228a = -1;
    }

    /* renamed from: k */
    private int m1704k() {
        if (this.f25228a < 0) {
            int i = 0;
            Enumeration b = m1606b();
            while (b.hasMoreElements()) {
                i += ((InterfaceC12554f) b.nextElement()).mo1617h().mo1591g().mo1618c();
            }
            this.f25228a = i;
        }
        return this.f25228a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12573w, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        C12567r mo1621b = c12567r.mo1621b();
        int m1704k = m1704k();
        c12567r.mo1620b(49);
        c12567r.m1629a(m1704k);
        Enumeration b = m1606b();
        while (b.hasMoreElements()) {
            mo1621b.mo1625a((InterfaceC12554f) b.nextElement());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        int m1704k = m1704k();
        return C12466bz.m1694a(m1704k) + 1 + m1704k;
    }
}
