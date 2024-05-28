package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0235p0;

/* renamed from: a.a.a.a.a.e.a.b.o2.q */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0217q extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f348v3;

    /* renamed from: w3 */
    public C0213o f349w3;

    /* renamed from: x3 */
    public AbstractC0338u f350x3;

    public C0217q(C0213o c0213o) {
        this(c0213o, null);
    }

    /* renamed from: a */
    public static C0217q m23928a(Object obj) {
        if (obj instanceof C0217q) {
            return (C0217q) obj;
        }
        if (obj != null) {
            return new C0217q(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f348v3);
        c0140e.m24170a(this.f349w3);
        AbstractC0338u abstractC0338u = this.f350x3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0235p0(false, 1, abstractC0338u));
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public C0213o m23927i() {
        return this.f349w3;
    }

    /* renamed from: j */
    public AbstractC0338u m23926j() {
        return this.f350x3;
    }

    /* renamed from: k */
    public C0166k m23925k() {
        return this.f348v3;
    }

    public C0217q(C0213o c0213o, AbstractC0338u abstractC0338u) {
        this.f348v3 = new C0166k(abstractC0338u == null ? 0L : 2L);
        this.f349w3 = c0213o;
        this.f350x3 = abstractC0338u;
    }

    public C0217q(AbstractC0263s abstractC0263s) {
        this.f348v3 = C0151g1.m24147a(abstractC0263s.mo23751a(0));
        this.f349w3 = C0213o.m23945a(abstractC0263s.mo23751a(1));
        if (abstractC0263s.mo23745o() == 3) {
            this.f350x3 = AbstractC0338u.m23581a((Object) abstractC0263s.mo23751a(2));
        }
    }
}
