package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.z */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0297z extends AbstractC0174m {

    /* renamed from: A3 */
    public BigInteger f788A3;

    /* renamed from: B3 */
    public BigInteger f789B3;

    /* renamed from: C3 */
    public BigInteger f790C3;

    /* renamed from: D3 */
    public BigInteger f791D3;

    /* renamed from: E3 */
    public AbstractC0263s f792E3;

    /* renamed from: v3 */
    public int f793v3;

    /* renamed from: w3 */
    public BigInteger f794w3;

    /* renamed from: x3 */
    public BigInteger f795x3;

    /* renamed from: y3 */
    public BigInteger f796y3;

    /* renamed from: z3 */
    public BigInteger f797z3;

    public C0297z(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this.f792E3 = null;
        this.f793v3 = 0;
        this.f794w3 = bigInteger;
        this.f795x3 = bigInteger2;
        this.f796y3 = bigInteger3;
        this.f797z3 = bigInteger4;
        this.f788A3 = bigInteger5;
        this.f789B3 = bigInteger6;
        this.f790C3 = bigInteger7;
        this.f791D3 = bigInteger8;
    }

    /* renamed from: a */
    public static C0297z m23623a(AbstractC0494y abstractC0494y, boolean z) {
        return m23622a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(this.f793v3));
        c0140e.m24170a(new C0166k(m23618l()));
        c0140e.m24170a(new C0166k(m23614p()));
        c0140e.m24170a(new C0166k(m23615o()));
        c0140e.m24170a(new C0166k(m23617m()));
        c0140e.m24170a(new C0166k(m23616n()));
        c0140e.m24170a(new C0166k(m23620j()));
        c0140e.m24170a(new C0166k(m23619k()));
        c0140e.m24170a(new C0166k(m23621i()));
        AbstractC0263s abstractC0263s = this.f792E3;
        if (abstractC0263s != null) {
            c0140e.m24170a(abstractC0263s);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23621i() {
        return this.f791D3;
    }

    /* renamed from: j */
    public BigInteger m23620j() {
        return this.f789B3;
    }

    /* renamed from: k */
    public BigInteger m23619k() {
        return this.f790C3;
    }

    /* renamed from: l */
    public BigInteger m23618l() {
        return this.f794w3;
    }

    /* renamed from: m */
    public BigInteger m23617m() {
        return this.f797z3;
    }

    /* renamed from: n */
    public BigInteger m23616n() {
        return this.f788A3;
    }

    /* renamed from: o */
    public BigInteger m23615o() {
        return this.f796y3;
    }

    /* renamed from: p */
    public BigInteger m23614p() {
        return this.f795x3;
    }

    /* renamed from: q */
    public int m23613q() {
        return this.f793v3;
    }

    /* renamed from: a */
    public static C0297z m23622a(Object obj) {
        if (obj instanceof C0297z) {
            return (C0297z) obj;
        }
        if (obj instanceof AbstractC0263s) {
            return new C0297z((AbstractC0263s) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public C0297z(AbstractC0263s abstractC0263s) {
        this.f792E3 = null;
        Enumeration mo23747m = abstractC0263s.mo23747m();
        BigInteger m24145n = ((C0166k) mo23747m.nextElement()).m24145n();
        if (m24145n.intValue() != 0 && m24145n.intValue() != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        this.f793v3 = m24145n.intValue();
        this.f794w3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f795x3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f796y3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f797z3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f788A3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f789B3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f790C3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f791D3 = ((C0166k) mo23747m.nextElement()).m24145n();
        if (mo23747m.hasMoreElements()) {
            this.f792E3 = (AbstractC0263s) mo23747m.nextElement();
        }
    }
}
