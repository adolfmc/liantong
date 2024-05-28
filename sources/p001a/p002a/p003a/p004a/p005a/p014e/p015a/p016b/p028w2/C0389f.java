package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;

/* renamed from: a.a.a.a.a.e.a.b.w2.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0389f extends AbstractC0174m {

    /* renamed from: v3 */
    public C0392g f1055v3;

    /* renamed from: w3 */
    public C0377b f1056w3;

    /* renamed from: x3 */
    public C0359v0 f1057x3;

    public C0389f(C0392g c0392g, C0377b c0377b, C0359v0 c0359v0) {
        this.f1055v3 = c0392g;
        this.f1056w3 = c0377b;
        this.f1057x3 = c0359v0;
    }

    /* renamed from: a */
    public static C0389f m23403a(Object obj) {
        if (obj instanceof C0389f) {
            return (C0389f) obj;
        }
        if (obj != null) {
            return new C0389f(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1055v3);
        c0140e.m24170a(this.f1056w3);
        c0140e.m24170a(this.f1057x3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0392g m23402i() {
        return this.f1055v3;
    }

    /* renamed from: j */
    public C0377b m23401j() {
        return this.f1056w3;
    }

    /* renamed from: k */
    public C0359v0 m23400k() {
        return this.f1057x3;
    }

    public C0389f(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 3) {
            this.f1055v3 = C0392g.m23389a(abstractC0263s.mo23751a(0));
            this.f1056w3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
            this.f1057x3 = C0359v0.m23557a(abstractC0263s.mo23751a(2));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
