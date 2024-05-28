package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.o2.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0191d extends AbstractC0174m {

    /* renamed from: A3 */
    public AbstractC0182o f247A3;

    /* renamed from: B3 */
    public AbstractC0338u f248B3;

    /* renamed from: v3 */
    public C0166k f249v3;

    /* renamed from: w3 */
    public C0190c0 f250w3;

    /* renamed from: x3 */
    public AbstractC0338u f251x3;

    /* renamed from: y3 */
    public C0213o f252y3;

    /* renamed from: z3 */
    public AbstractC0338u f253z3;

    public C0191d(C0190c0 c0190c0, AbstractC0338u abstractC0338u, C0213o c0213o, AbstractC0338u abstractC0338u2, AbstractC0182o abstractC0182o, AbstractC0338u abstractC0338u3) {
        this.f249v3 = new C0166k(0L);
        this.f250w3 = c0190c0;
        this.f251x3 = abstractC0338u;
        this.f252y3 = c0213o;
        this.f253z3 = abstractC0338u2;
        this.f247A3 = abstractC0182o;
        this.f248B3 = abstractC0338u3;
    }

    /* renamed from: a */
    public static C0191d m24052a(AbstractC0494y abstractC0494y, boolean z) {
        return m24051a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f249v3);
        C0190c0 c0190c0 = this.f250w3;
        if (c0190c0 != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0190c0));
        }
        c0140e.m24170a(this.f251x3);
        c0140e.m24170a(this.f252y3);
        AbstractC0338u abstractC0338u = this.f253z3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 1, abstractC0338u));
        }
        c0140e.m24170a(this.f247A3);
        AbstractC0338u abstractC0338u2 = this.f248B3;
        if (abstractC0338u2 != null) {
            c0140e.m24170a(new C0360v1(false, 2, abstractC0338u2));
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m24050i() {
        return this.f253z3;
    }

    /* renamed from: j */
    public C0213o m24049j() {
        return this.f252y3;
    }

    /* renamed from: k */
    public AbstractC0182o m24048k() {
        return this.f247A3;
    }

    /* renamed from: l */
    public C0190c0 m24047l() {
        return this.f250w3;
    }

    /* renamed from: m */
    public AbstractC0338u m24046m() {
        return this.f251x3;
    }

    /* renamed from: n */
    public AbstractC0338u m24045n() {
        return this.f248B3;
    }

    /* renamed from: o */
    public C0166k m24044o() {
        return this.f249v3;
    }

    /* renamed from: a */
    public static C0191d m24051a(Object obj) {
        if (obj != null && !(obj instanceof C0191d)) {
            if (obj instanceof AbstractC0263s) {
                return new C0191d((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid AuthEnvelopedData: " + obj.getClass().getName());
        }
        return (C0191d) obj;
    }

    public C0191d(AbstractC0263s abstractC0263s) {
        AbstractC0258r abstractC0258r;
        int i;
        int i2;
        AbstractC0258r abstractC0258r2;
        this.f249v3 = (C0166k) abstractC0263s.mo23751a(0).mo23015d();
        AbstractC0258r mo23015d = abstractC0263s.mo23751a(1).mo23015d();
        if (mo23015d instanceof AbstractC0494y) {
            this.f250w3 = C0190c0.m24056a((AbstractC0494y) mo23015d, false);
            i = 3;
            abstractC0258r = abstractC0263s.mo23751a(2).mo23015d();
        } else {
            abstractC0258r = mo23015d;
            i = 2;
        }
        this.f251x3 = AbstractC0338u.m23581a((Object) abstractC0258r);
        int i3 = i + 1;
        this.f252y3 = C0213o.m23945a(abstractC0263s.mo23751a(i).mo23015d());
        int i4 = i3 + 1;
        AbstractC0258r mo23015d2 = abstractC0263s.mo23751a(i3).mo23015d();
        if (mo23015d2 instanceof AbstractC0494y) {
            this.f253z3 = AbstractC0338u.m23582a((AbstractC0494y) mo23015d2, false);
            i2 = i4 + 1;
            abstractC0258r2 = abstractC0263s.mo23751a(i4).mo23015d();
        } else {
            i2 = i4;
            abstractC0258r2 = mo23015d2;
        }
        this.f247A3 = AbstractC0182o.m24089a((Object) abstractC0258r2);
        if (abstractC0263s.mo23745o() > i2) {
            this.f248B3 = AbstractC0338u.m23582a((AbstractC0494y) abstractC0263s.mo23751a(i2).mo23015d(), false);
        }
    }
}
