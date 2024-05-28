package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.e0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0387e0 extends AbstractC0174m {

    /* renamed from: y3 */
    public static final BigInteger f1050y3 = BigInteger.valueOf(0);

    /* renamed from: v3 */
    public C0378b0 f1051v3;

    /* renamed from: w3 */
    public C0166k f1052w3;

    /* renamed from: x3 */
    public C0166k f1053x3;

    public C0387e0(AbstractC0263s abstractC0263s) {
        this.f1051v3 = C0378b0.m23455a(abstractC0263s.mo23751a(0));
        int mo23745o = abstractC0263s.mo23745o();
        if (mo23745o != 1) {
            if (mo23745o == 2) {
                AbstractC0494y m23008a = AbstractC0494y.m23008a(abstractC0263s.mo23751a(1));
                int mo22994f = m23008a.mo22994f();
                if (mo22994f == 0) {
                    this.f1052w3 = C0151g1.m24148a(m23008a, false);
                } else if (mo22994f == 1) {
                    this.f1053x3 = C0151g1.m24148a(m23008a, false);
                } else {
                    throw new IllegalArgumentException("Bad tag number: " + m23008a.mo22994f());
                }
            } else if (mo23745o == 3) {
                AbstractC0494y m23008a2 = AbstractC0494y.m23008a(abstractC0263s.mo23751a(1));
                if (m23008a2.mo22994f() == 0) {
                    this.f1052w3 = C0151g1.m24148a(m23008a2, false);
                    AbstractC0494y m23008a3 = AbstractC0494y.m23008a(abstractC0263s.mo23751a(2));
                    if (m23008a3.mo22994f() == 1) {
                        this.f1053x3 = C0151g1.m24148a(m23008a3, false);
                        return;
                    }
                    throw new IllegalArgumentException("Bad tag number for 'maximum': " + m23008a3.mo22994f());
                }
                throw new IllegalArgumentException("Bad tag number for 'minimum': " + m23008a2.mo22994f());
            } else {
                throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
            }
        }
    }

    /* renamed from: a */
    public static C0387e0 m23410a(AbstractC0494y abstractC0494y, boolean z) {
        return new C0387e0(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1051v3);
        C0166k c0166k = this.f1052w3;
        if (c0166k != null && !c0166k.m24145n().equals(f1050y3)) {
            c0140e.m24170a(new C0360v1(false, 0, this.f1052w3));
        }
        C0166k c0166k2 = this.f1053x3;
        if (c0166k2 != null) {
            c0140e.m24170a(new C0360v1(false, 1, c0166k2));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0378b0 m23408i() {
        return this.f1051v3;
    }

    /* renamed from: j */
    public BigInteger m23407j() {
        C0166k c0166k = this.f1053x3;
        if (c0166k == null) {
            return null;
        }
        return c0166k.m24145n();
    }

    /* renamed from: k */
    public BigInteger m23406k() {
        C0166k c0166k = this.f1052w3;
        if (c0166k == null) {
            return f1050y3;
        }
        return c0166k.m24145n();
    }

    /* renamed from: a */
    public static C0387e0 m23409a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof C0387e0) {
            return (C0387e0) obj;
        }
        return new C0387e0(AbstractC0263s.m23749a(obj));
    }

    public C0387e0(C0378b0 c0378b0, BigInteger bigInteger, BigInteger bigInteger2) {
        this.f1051v3 = c0378b0;
        if (bigInteger2 != null) {
            this.f1053x3 = new C0166k(bigInteger2);
        }
        if (bigInteger == null) {
            this.f1052w3 = null;
        } else {
            this.f1052w3 = new C0166k(bigInteger);
        }
    }

    public C0387e0(C0378b0 c0378b0) {
        this(c0378b0, null, null);
    }
}
