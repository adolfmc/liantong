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

/* renamed from: a.a.a.a.a.e.a.b.w2.s */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0428s extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f1312v3;

    /* renamed from: w3 */
    public C0166k f1313w3;

    /* renamed from: x3 */
    public C0166k f1314x3;

    public C0428s(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f1312v3 = new C0166k(bigInteger);
        this.f1313w3 = new C0166k(bigInteger2);
        this.f1314x3 = new C0166k(bigInteger3);
    }

    /* renamed from: a */
    public static C0428s m23180a(AbstractC0494y abstractC0494y, boolean z) {
        return m23179a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1312v3);
        c0140e.m24170a(this.f1313w3);
        c0140e.m24170a(this.f1314x3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23178i() {
        return this.f1314x3.m24146m();
    }

    /* renamed from: j */
    public BigInteger m23177j() {
        return this.f1312v3.m24146m();
    }

    /* renamed from: k */
    public BigInteger m23176k() {
        return this.f1313w3.m24146m();
    }

    /* renamed from: a */
    public static C0428s m23179a(Object obj) {
        if (obj instanceof C0428s) {
            return (C0428s) obj;
        }
        if (obj != null) {
            return new C0428s(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0428s(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 3) {
            Enumeration mo23747m = abstractC0263s.mo23747m();
            this.f1312v3 = C0151g1.m24147a(mo23747m.nextElement());
            this.f1313w3 = C0151g1.m24147a(mo23747m.nextElement());
            this.f1314x3 = C0151g1.m24147a(mo23747m.nextElement());
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
