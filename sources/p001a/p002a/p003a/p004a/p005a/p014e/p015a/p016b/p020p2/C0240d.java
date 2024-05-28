package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.p2.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0240d extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f418v3;

    /* renamed from: w3 */
    public C0241e f419w3;

    /* renamed from: x3 */
    public C0243g f420x3;

    public C0240d(AbstractC0263s abstractC0263s) {
        this.f418v3 = new C0166k(C0151g1.m24147a(abstractC0263s.mo23751a(0)).m24145n());
        this.f419w3 = C0241e.m23830a(abstractC0263s.mo23751a(1));
        if (abstractC0263s.mo23745o() > 2) {
            this.f420x3 = C0243g.m23806a(abstractC0263s.mo23751a(2));
        }
    }

    /* renamed from: a */
    public static C0240d m23834a(Object obj) {
        if (obj instanceof C0240d) {
            return (C0240d) obj;
        }
        if (obj != null) {
            return new C0240d(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f418v3);
        c0140e.m24170a(this.f419w3);
        C0243g c0243g = this.f420x3;
        if (c0243g != null) {
            c0140e.m24170a(c0243g);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0166k m23833i() {
        return this.f418v3;
    }

    /* renamed from: j */
    public C0241e m23832j() {
        return this.f419w3;
    }

    /* renamed from: k */
    public C0243g m23831k() {
        return this.f420x3;
    }

    public C0240d(int i, C0241e c0241e, C0243g c0243g) {
        this(new C0166k(i), c0241e, c0243g);
    }

    public C0240d(C0166k c0166k, C0241e c0241e, C0243g c0243g) {
        this.f418v3 = c0166k;
        this.f419w3 = c0241e;
        this.f420x3 = c0243g;
    }
}
