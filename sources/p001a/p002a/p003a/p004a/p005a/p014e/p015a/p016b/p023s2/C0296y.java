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

/* renamed from: a.a.a.a.a.e.a.b.s2.y */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0296y extends AbstractC0174m {

    /* renamed from: A3 */
    public BigInteger f778A3;

    /* renamed from: B3 */
    public BigInteger f779B3;

    /* renamed from: C3 */
    public BigInteger f780C3;

    /* renamed from: D3 */
    public BigInteger f781D3;

    /* renamed from: E3 */
    public AbstractC0263s f782E3;

    /* renamed from: v3 */
    public BigInteger f783v3;

    /* renamed from: w3 */
    public BigInteger f784w3;

    /* renamed from: x3 */
    public BigInteger f785x3;

    /* renamed from: y3 */
    public BigInteger f786y3;

    /* renamed from: z3 */
    public BigInteger f787z3;

    public C0296y(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this.f782E3 = null;
        this.f783v3 = BigInteger.valueOf(0L);
        this.f784w3 = bigInteger;
        this.f785x3 = bigInteger2;
        this.f786y3 = bigInteger3;
        this.f787z3 = bigInteger4;
        this.f778A3 = bigInteger5;
        this.f779B3 = bigInteger6;
        this.f780C3 = bigInteger7;
        this.f781D3 = bigInteger8;
    }

    /* renamed from: a */
    public static C0296y m23634a(AbstractC0494y abstractC0494y, boolean z) {
        return m23633a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(this.f783v3));
        c0140e.m24170a(new C0166k(m23629l()));
        c0140e.m24170a(new C0166k(m23625p()));
        c0140e.m24170a(new C0166k(m23626o()));
        c0140e.m24170a(new C0166k(m23628m()));
        c0140e.m24170a(new C0166k(m23627n()));
        c0140e.m24170a(new C0166k(m23631j()));
        c0140e.m24170a(new C0166k(m23630k()));
        c0140e.m24170a(new C0166k(m23632i()));
        AbstractC0263s abstractC0263s = this.f782E3;
        if (abstractC0263s != null) {
            c0140e.m24170a(abstractC0263s);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23632i() {
        return this.f781D3;
    }

    /* renamed from: j */
    public BigInteger m23631j() {
        return this.f779B3;
    }

    /* renamed from: k */
    public BigInteger m23630k() {
        return this.f780C3;
    }

    /* renamed from: l */
    public BigInteger m23629l() {
        return this.f784w3;
    }

    /* renamed from: m */
    public BigInteger m23628m() {
        return this.f787z3;
    }

    /* renamed from: n */
    public BigInteger m23627n() {
        return this.f778A3;
    }

    /* renamed from: o */
    public BigInteger m23626o() {
        return this.f786y3;
    }

    /* renamed from: p */
    public BigInteger m23625p() {
        return this.f785x3;
    }

    /* renamed from: q */
    public BigInteger m23624q() {
        return this.f783v3;
    }

    /* renamed from: a */
    public static C0296y m23633a(Object obj) {
        if (obj instanceof C0296y) {
            return (C0296y) obj;
        }
        if (obj != null) {
            return new C0296y(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0296y(AbstractC0263s abstractC0263s) {
        this.f782E3 = null;
        Enumeration mo23747m = abstractC0263s.mo23747m();
        BigInteger m24145n = ((C0166k) mo23747m.nextElement()).m24145n();
        if (m24145n.intValue() != 0 && m24145n.intValue() != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        this.f783v3 = m24145n;
        this.f784w3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f785x3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f786y3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f787z3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f778A3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f779B3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f780C3 = ((C0166k) mo23747m.nextElement()).m24145n();
        this.f781D3 = ((C0166k) mo23747m.nextElement()).m24145n();
        if (mo23747m.hasMoreElements()) {
            this.f782E3 = (AbstractC0263s) mo23747m.nextElement();
        }
    }
}
