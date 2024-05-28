package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.a0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0268a0 extends AbstractC0174m {

    /* renamed from: v3 */
    public BigInteger f563v3;

    /* renamed from: w3 */
    public BigInteger f564w3;

    public C0268a0(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f563v3 = bigInteger;
        this.f564w3 = bigInteger2;
    }

    /* renamed from: a */
    public static C0268a0 m23739a(AbstractC0494y abstractC0494y, boolean z) {
        return m23738a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(m23737i()));
        c0140e.m24170a(new C0166k(m23736j()));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23737i() {
        return this.f563v3;
    }

    /* renamed from: j */
    public BigInteger m23736j() {
        return this.f564w3;
    }

    /* renamed from: a */
    public static C0268a0 m23738a(Object obj) {
        if (obj instanceof C0268a0) {
            return (C0268a0) obj;
        }
        if (obj != null) {
            return new C0268a0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0268a0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            Enumeration mo23747m = abstractC0263s.mo23747m();
            this.f563v3 = C0151g1.m24147a(mo23747m.nextElement()).m24146m();
            this.f564w3 = C0151g1.m24147a(mo23747m.nextElement()).m24146m();
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
