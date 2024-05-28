package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

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

/* renamed from: a.a.a.a.a.e.a.b.w2.t0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0432t0 extends AbstractC0174m {

    /* renamed from: v3 */
    public BigInteger f1370v3;

    /* renamed from: w3 */
    public BigInteger f1371w3;

    public C0432t0(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f1370v3 = bigInteger;
        this.f1371w3 = bigInteger2;
    }

    /* renamed from: a */
    public static C0432t0 m23152a(AbstractC0494y abstractC0494y, boolean z) {
        return m23151a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(m23150i()));
        c0140e.m24170a(new C0166k(m23149j()));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23150i() {
        return this.f1370v3;
    }

    /* renamed from: j */
    public BigInteger m23149j() {
        return this.f1371w3;
    }

    /* renamed from: a */
    public static C0432t0 m23151a(Object obj) {
        if (obj != null && !(obj instanceof C0432t0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0432t0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid RSAPublicKeyStructure: " + obj.getClass().getName());
        }
        return (C0432t0) obj;
    }

    public C0432t0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            Enumeration mo23747m = abstractC0263s.mo23747m();
            this.f1370v3 = C0151g1.m24147a(mo23747m.nextElement()).m24146m();
            this.f1371w3 = C0151g1.m24147a(mo23747m.nextElement()).m24146m();
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
