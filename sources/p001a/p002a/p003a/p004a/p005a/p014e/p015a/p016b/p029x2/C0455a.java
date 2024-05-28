package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.x2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0455a extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f1486v3;

    /* renamed from: w3 */
    public C0166k f1487w3;

    /* renamed from: x3 */
    public C0166k f1488x3;

    /* renamed from: y3 */
    public C0166k f1489y3;

    /* renamed from: z3 */
    public C0457c f1490z3;

    public C0455a(C0166k c0166k, C0166k c0166k2, C0166k c0166k3, C0166k c0166k4, C0457c c0457c) {
        if (c0166k == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (c0166k2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (c0166k3 != null) {
            this.f1486v3 = c0166k;
            this.f1487w3 = c0166k2;
            this.f1488x3 = c0166k3;
            this.f1489y3 = c0166k4;
            this.f1490z3 = c0457c;
            return;
        }
        throw new IllegalArgumentException("'q' cannot be null");
    }

    /* renamed from: a */
    public static C0455a m23063a(AbstractC0494y abstractC0494y, boolean z) {
        return m23062a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1486v3);
        c0140e.m24170a(this.f1487w3);
        c0140e.m24170a(this.f1488x3);
        C0166k c0166k = this.f1489y3;
        if (c0166k != null) {
            c0140e.m24170a(c0166k);
        }
        C0457c c0457c = this.f1490z3;
        if (c0457c != null) {
            c0140e.m24170a(c0457c);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0166k m23060i() {
        return this.f1487w3;
    }

    /* renamed from: j */
    public C0166k m23059j() {
        return this.f1489y3;
    }

    /* renamed from: k */
    public C0166k m23058k() {
        return this.f1486v3;
    }

    /* renamed from: l */
    public C0166k m23057l() {
        return this.f1488x3;
    }

    /* renamed from: m */
    public C0457c m23056m() {
        return this.f1490z3;
    }

    /* renamed from: a */
    public static C0455a m23062a(Object obj) {
        if (obj != null && !(obj instanceof C0455a)) {
            if (obj instanceof AbstractC0263s) {
                return new C0455a((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid DHDomainParameters: " + obj.getClass().getName());
        }
        return (C0455a) obj;
    }

    /* renamed from: a */
    public static InterfaceC0136d m23061a(Enumeration enumeration) {
        if (enumeration.hasMoreElements()) {
            return (InterfaceC0136d) enumeration.nextElement();
        }
        return null;
    }

    public C0455a(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 3 && abstractC0263s.mo23745o() <= 5) {
            Enumeration mo23747m = abstractC0263s.mo23747m();
            this.f1486v3 = C0151g1.m24147a(mo23747m.nextElement());
            this.f1487w3 = C0151g1.m24147a(mo23747m.nextElement());
            this.f1488x3 = C0151g1.m24147a(mo23747m.nextElement());
            InterfaceC0136d m23061a = m23061a(mo23747m);
            if (m23061a != null && (m23061a instanceof C0166k)) {
                this.f1489y3 = C0151g1.m24147a(m23061a);
                m23061a = m23061a(mo23747m);
            }
            if (m23061a != null) {
                this.f1490z3 = C0457c.m23051a(m23061a.mo23015d());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
