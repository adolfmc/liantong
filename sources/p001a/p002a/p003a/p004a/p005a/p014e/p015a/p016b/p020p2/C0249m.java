package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.p2.m */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0249m extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f459v3;

    /* renamed from: w3 */
    public AbstractC0263s f460w3;

    public C0249m(AbstractC0263s abstractC0263s) {
        this.f459v3 = C0151g1.m24147a(abstractC0263s.mo23751a(0));
        this.f460w3 = AbstractC0263s.m23749a(abstractC0263s.mo23751a(1));
    }

    /* renamed from: a */
    public static C0249m m23782a(Object obj) {
        if (obj instanceof C0249m) {
            return (C0249m) obj;
        }
        if (obj != null) {
            return new C0249m(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f459v3);
        c0140e.m24170a(this.f460w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0166k m23781i() {
        return this.f459v3;
    }

    /* renamed from: j */
    public C0250n[] m23780j() {
        AbstractC0263s abstractC0263s = this.f460w3;
        if (abstractC0263s == null) {
            return null;
        }
        int mo23745o = abstractC0263s.mo23745o();
        C0250n[] c0250nArr = new C0250n[mo23745o];
        for (int i = 0; i != mo23745o; i++) {
            c0250nArr[i] = C0250n.m23779a(this.f460w3.mo23751a(i));
        }
        return c0250nArr;
    }
}
