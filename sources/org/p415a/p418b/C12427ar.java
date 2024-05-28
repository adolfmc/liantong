package org.p415a.p418b;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ar */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12427ar extends AbstractC12570t {

    /* renamed from: a */
    private C12563o f25130a;

    /* renamed from: b */
    private C12560l f25131b;

    /* renamed from: c */
    private AbstractC12570t f25132c;

    /* renamed from: d */
    private int f25133d;

    /* renamed from: e */
    private AbstractC12570t f25134e;

    public C12427ar(C12555g c12555g) {
        int i = 0;
        AbstractC12570t m1725a = m1725a(c12555g, 0);
        if (m1725a instanceof C12563o) {
            this.f25130a = (C12563o) m1725a;
            m1725a = m1725a(c12555g, 1);
            i = 1;
        }
        if (m1725a instanceof C12560l) {
            this.f25131b = (C12560l) m1725a;
            i++;
            m1725a = m1725a(c12555g, i);
        }
        if (!(m1725a instanceof AbstractC12576z)) {
            this.f25132c = m1725a;
            i++;
            m1725a = m1725a(c12555g, i);
        }
        if (c12555g.m1661a() != i + 1) {
            throw new IllegalArgumentException("input vector too large");
        }
        if (!(m1725a instanceof AbstractC12576z)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
        AbstractC12576z abstractC12576z = (AbstractC12576z) m1725a;
        m1726a(abstractC12576z.m1595b());
        this.f25134e = abstractC12576z.m1594d();
    }

    /* renamed from: a */
    private AbstractC12570t m1725a(C12555g c12555g, int i) {
        if (c12555g.m1661a() > i) {
            return c12555g.m1660a(i).mo1617h();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    /* renamed from: a */
    private void m1726a(int i) {
        if (i >= 0 && i <= 2) {
            this.f25133d = i;
            return;
        }
        throw new IllegalArgumentException("invalid encoding value: " + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12563o c12563o = this.f25130a;
        if (c12563o != null) {
            byteArrayOutputStream.write(c12563o.m1644a("DER"));
        }
        C12560l c12560l = this.f25131b;
        if (c12560l != null) {
            byteArrayOutputStream.write(c12560l.m1644a("DER"));
        }
        AbstractC12570t abstractC12570t = this.f25132c;
        if (abstractC12570t != null) {
            byteArrayOutputStream.write(abstractC12570t.m1644a("DER"));
        }
        byteArrayOutputStream.write(new C12449bi(true, this.f25133d, this.f25134e).m1644a("DER"));
        c12567r.m1627a(32, 8, byteArrayOutputStream.toByteArray());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return true;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        AbstractC12570t abstractC12570t2;
        C12560l c12560l;
        C12563o c12563o;
        if (abstractC12570t instanceof C12427ar) {
            if (this == abstractC12570t) {
                return true;
            }
            C12427ar c12427ar = (C12427ar) abstractC12570t;
            C12563o c12563o2 = this.f25130a;
            if (c12563o2 == null || ((c12563o = c12427ar.f25130a) != null && c12563o.equals(c12563o2))) {
                C12560l c12560l2 = this.f25131b;
                if (c12560l2 == null || ((c12560l = c12427ar.f25131b) != null && c12560l.equals(c12560l2))) {
                    AbstractC12570t abstractC12570t3 = this.f25132c;
                    if (abstractC12570t3 == null || ((abstractC12570t2 = c12427ar.f25132c) != null && abstractC12570t2.equals(abstractC12570t3))) {
                        return this.f25134e.equals(c12427ar.f25134e);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return m1643i().length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        C12563o c12563o = this.f25130a;
        int hashCode = c12563o != null ? c12563o.hashCode() : 0;
        C12560l c12560l = this.f25131b;
        if (c12560l != null) {
            hashCode ^= c12560l.hashCode();
        }
        AbstractC12570t abstractC12570t = this.f25132c;
        if (abstractC12570t != null) {
            hashCode ^= abstractC12570t.hashCode();
        }
        return hashCode ^ this.f25134e.hashCode();
    }
}
