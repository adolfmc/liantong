package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;

/* renamed from: a.a.a.a.a.e.a.b.z0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0498z0 extends AbstractC0258r {

    /* renamed from: v3 */
    public C0178n f1612v3;

    /* renamed from: w3 */
    public C0166k f1613w3;

    /* renamed from: x3 */
    public AbstractC0258r f1614x3;

    /* renamed from: y3 */
    public int f1615y3;

    /* renamed from: z3 */
    public AbstractC0258r f1616z3;

    public C0498z0(C0140e c0140e) {
        int i = 0;
        AbstractC0258r m22992a = m22992a(c0140e, 0);
        if (m22992a instanceof C0178n) {
            this.f1612v3 = (C0178n) m22992a;
            m22992a = m22992a(c0140e, 1);
            i = 1;
        }
        if (m22992a instanceof C0166k) {
            this.f1613w3 = (C0166k) m22992a;
            i++;
            m22992a = m22992a(c0140e, i);
        }
        if (!(m22992a instanceof C0360v1)) {
            this.f1614x3 = m22992a;
            i++;
            m22992a = m22992a(c0140e, i);
        }
        if (c0140e.m24172a() == i + 1) {
            if (m22992a instanceof C0360v1) {
                C0360v1 c0360v1 = (C0360v1) m22992a;
                m22993a(c0360v1.mo22994f());
                this.f1616z3 = c0360v1.m23004m();
                return;
            }
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
        throw new IllegalArgumentException("input vector too large");
    }

    /* renamed from: a */
    private AbstractC0258r m22992a(C0140e c0140e, int i) {
        if (c0140e.m24172a() > i) {
            return c0140e.m24171a(i).mo23015d();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    /* renamed from: b */
    private void m22989b(AbstractC0258r abstractC0258r) {
        this.f1614x3 = abstractC0258r;
    }

    /* renamed from: c */
    private void m22988c(AbstractC0258r abstractC0258r) {
        this.f1616z3 = abstractC0258r;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        C0178n c0178n = this.f1612v3;
        int hashCode = c0178n != null ? c0178n.hashCode() : 0;
        C0166k c0166k = this.f1613w3;
        if (c0166k != null) {
            hashCode ^= c0166k.hashCode();
        }
        AbstractC0258r abstractC0258r = this.f1614x3;
        if (abstractC0258r != null) {
            hashCode ^= abstractC0258r.hashCode();
        }
        return hashCode ^ this.f1616z3.hashCode();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return m24101g().length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return true;
    }

    /* renamed from: m */
    public AbstractC0258r m22987m() {
        return this.f1614x3;
    }

    /* renamed from: n */
    public C0178n m22986n() {
        return this.f1612v3;
    }

    /* renamed from: o */
    public int m22985o() {
        return this.f1615y3;
    }

    /* renamed from: p */
    public AbstractC0258r m22984p() {
        return this.f1616z3;
    }

    /* renamed from: q */
    public C0166k m22983q() {
        return this.f1613w3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C0178n c0178n = this.f1612v3;
        if (c0178n != null) {
            byteArrayOutputStream.write(c0178n.m24102a("DER"));
        }
        C0166k c0166k = this.f1613w3;
        if (c0166k != null) {
            byteArrayOutputStream.write(c0166k.m24102a("DER"));
        }
        AbstractC0258r abstractC0258r = this.f1614x3;
        if (abstractC0258r != null) {
            byteArrayOutputStream.write(abstractC0258r.m24102a("DER"));
        }
        byteArrayOutputStream.write(new C0360v1(true, this.f1615y3, this.f1616z3).m24102a("DER"));
        c0252q.m23774a(32, 8, byteArrayOutputStream.toByteArray());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        AbstractC0258r abstractC0258r2;
        C0166k c0166k;
        C0178n c0178n;
        if (abstractC0258r instanceof C0498z0) {
            if (this == abstractC0258r) {
                return true;
            }
            C0498z0 c0498z0 = (C0498z0) abstractC0258r;
            C0178n c0178n2 = this.f1612v3;
            if (c0178n2 == null || ((c0178n = c0498z0.f1612v3) != null && c0178n.equals(c0178n2))) {
                C0166k c0166k2 = this.f1613w3;
                if (c0166k2 == null || ((c0166k = c0498z0.f1613w3) != null && c0166k.equals(c0166k2))) {
                    AbstractC0258r abstractC0258r3 = this.f1614x3;
                    if (abstractC0258r3 == null || ((abstractC0258r2 = c0498z0.f1614x3) != null && abstractC0258r2.equals(abstractC0258r3))) {
                        return this.f1616z3.equals(c0498z0.f1616z3);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public C0498z0(C0178n c0178n, C0166k c0166k, AbstractC0258r abstractC0258r, C0360v1 c0360v1) {
        this(c0178n, c0166k, abstractC0258r, c0360v1.mo22994f(), c0360v1.mo23015d());
    }

    public C0498z0(C0178n c0178n, C0166k c0166k, AbstractC0258r abstractC0258r, int i, AbstractC0258r abstractC0258r2) {
        m22990a(c0178n);
        m22991a(c0166k);
        m22989b(abstractC0258r);
        m22993a(i);
        m22988c(abstractC0258r2.mo23015d());
    }

    /* renamed from: a */
    private void m22990a(C0178n c0178n) {
        this.f1612v3 = c0178n;
    }

    /* renamed from: a */
    private void m22993a(int i) {
        if (i >= 0 && i <= 2) {
            this.f1615y3 = i;
            return;
        }
        throw new IllegalArgumentException("invalid encoding value: " + i);
    }

    /* renamed from: a */
    private void m22991a(C0166k c0166k) {
        this.f1613w3 = c0166k;
    }
}
