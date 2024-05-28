package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0179n0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0235p0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.o2.n0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0212n0 extends AbstractC0174m {

    /* renamed from: D3 */
    public static final C0166k f324D3 = new C0166k(1);

    /* renamed from: E3 */
    public static final C0166k f325E3 = new C0166k(3);

    /* renamed from: F3 */
    public static final C0166k f326F3 = new C0166k(4);

    /* renamed from: G3 */
    public static final C0166k f327G3 = new C0166k(5);

    /* renamed from: A3 */
    public AbstractC0338u f328A3;

    /* renamed from: B3 */
    public boolean f329B3;

    /* renamed from: C3 */
    public boolean f330C3;

    /* renamed from: v3 */
    public C0166k f331v3;

    /* renamed from: w3 */
    public AbstractC0338u f332w3;

    /* renamed from: x3 */
    public C0207l f333x3;

    /* renamed from: y3 */
    public AbstractC0338u f334y3;

    /* renamed from: z3 */
    public AbstractC0338u f335z3;

    public C0212n0(AbstractC0338u abstractC0338u, C0207l c0207l, AbstractC0338u abstractC0338u2, AbstractC0338u abstractC0338u3, AbstractC0338u abstractC0338u4) {
        this.f331v3 = m23954a(c0207l.m23971j(), abstractC0338u2, abstractC0338u3, abstractC0338u4);
        this.f332w3 = abstractC0338u;
        this.f333x3 = c0207l;
        this.f334y3 = abstractC0338u2;
        this.f335z3 = abstractC0338u3;
        this.f328A3 = abstractC0338u4;
        this.f330C3 = abstractC0338u3 instanceof C0179n0;
        this.f329B3 = abstractC0338u2 instanceof C0179n0;
    }

    /* renamed from: a */
    public static C0212n0 m23952a(Object obj) {
        if (obj instanceof C0212n0) {
            return (C0212n0) obj;
        }
        if (obj != null) {
            return new C0212n0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f331v3);
        c0140e.m24170a(this.f332w3);
        c0140e.m24170a(this.f333x3);
        AbstractC0338u abstractC0338u = this.f334y3;
        if (abstractC0338u != null) {
            if (this.f329B3) {
                c0140e.m24170a(new C0235p0(false, 0, abstractC0338u));
            } else {
                c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
            }
        }
        AbstractC0338u abstractC0338u2 = this.f335z3;
        if (abstractC0338u2 != null) {
            if (this.f330C3) {
                c0140e.m24170a(new C0235p0(false, 1, abstractC0338u2));
            } else {
                c0140e.m24170a(new C0360v1(false, 1, abstractC0338u2));
            }
        }
        c0140e.m24170a(this.f328A3);
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m23951i() {
        return this.f335z3;
    }

    /* renamed from: j */
    public AbstractC0338u m23950j() {
        return this.f334y3;
    }

    /* renamed from: k */
    public AbstractC0338u m23949k() {
        return this.f332w3;
    }

    /* renamed from: l */
    public C0207l m23948l() {
        return this.f333x3;
    }

    /* renamed from: m */
    public AbstractC0338u m23947m() {
        return this.f328A3;
    }

    /* renamed from: n */
    public C0166k m23946n() {
        return this.f331v3;
    }

    /* renamed from: a */
    private C0166k m23954a(C0178n c0178n, AbstractC0338u abstractC0338u, AbstractC0338u abstractC0338u2, AbstractC0338u abstractC0338u3) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (abstractC0338u != null) {
            Enumeration m23578m = abstractC0338u.m23578m();
            z = false;
            z2 = false;
            z3 = false;
            while (m23578m.hasMoreElements()) {
                Object nextElement = m23578m.nextElement();
                if (nextElement instanceof AbstractC0494y) {
                    AbstractC0494y m23008a = AbstractC0494y.m23008a(nextElement);
                    if (m23008a.mo22994f() == 1) {
                        z2 = true;
                    } else if (m23008a.mo22994f() == 2) {
                        z3 = true;
                    } else if (m23008a.mo22994f() == 3) {
                        z = true;
                    }
                }
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        if (z) {
            return new C0166k(5L);
        }
        if (abstractC0338u2 != null) {
            Enumeration m23578m2 = abstractC0338u2.m23578m();
            while (m23578m2.hasMoreElements()) {
                if (m23578m2.nextElement() instanceof AbstractC0494y) {
                    z4 = true;
                }
            }
        }
        if (z4) {
            return f327G3;
        }
        if (z3) {
            return f326F3;
        }
        if (z2) {
            return f325E3;
        }
        if (m23953a(abstractC0338u3)) {
            return f325E3;
        }
        if (!InterfaceC0201i.f288a.equals(c0178n)) {
            return f325E3;
        }
        return f324D3;
    }

    public C0212n0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f331v3 = C0151g1.m24147a(mo23747m.nextElement());
        this.f332w3 = (AbstractC0338u) mo23747m.nextElement();
        this.f333x3 = C0207l.m23973a(mo23747m.nextElement());
        while (mo23747m.hasMoreElements()) {
            AbstractC0258r abstractC0258r = (AbstractC0258r) mo23747m.nextElement();
            if (abstractC0258r instanceof AbstractC0494y) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0258r;
                int mo22994f = abstractC0494y.mo22994f();
                if (mo22994f == 0) {
                    this.f329B3 = abstractC0494y instanceof C0235p0;
                    this.f334y3 = AbstractC0338u.m23582a(abstractC0494y, false);
                } else if (mo22994f == 1) {
                    this.f330C3 = abstractC0494y instanceof C0235p0;
                    this.f335z3 = AbstractC0338u.m23582a(abstractC0494y, false);
                } else {
                    throw new IllegalArgumentException("unknown tag value " + abstractC0494y.mo22994f());
                }
            } else {
                this.f328A3 = (AbstractC0338u) abstractC0258r;
            }
        }
    }

    /* renamed from: a */
    private boolean m23953a(AbstractC0338u abstractC0338u) {
        Enumeration m23578m = abstractC0338u.m23578m();
        while (m23578m.hasMoreElements()) {
            if (C0218q0.m23924a(m23578m.nextElement()).m23917o().m24145n().intValue() == 3) {
                return true;
            }
        }
        return false;
    }
}
