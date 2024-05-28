package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0430s1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0445y0;

/* renamed from: a.a.a.a.a.e.a.b.s2.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0277f extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f598v3;

    /* renamed from: w3 */
    public C0364d f599w3;

    /* renamed from: x3 */
    public C0445y0 f600x3;

    /* renamed from: y3 */
    public AbstractC0338u f601y3;

    public C0277f(C0364d c0364d, C0445y0 c0445y0, AbstractC0338u abstractC0338u) {
        this.f598v3 = new C0166k(0L);
        this.f601y3 = null;
        this.f599w3 = c0364d;
        this.f600x3 = c0445y0;
        this.f601y3 = abstractC0338u;
        if (c0364d == null || c0445y0 == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }

    /* renamed from: a */
    public static C0277f m23699a(Object obj) {
        if (obj instanceof C0277f) {
            return (C0277f) obj;
        }
        if (obj != null) {
            return new C0277f(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f598v3);
        c0140e.m24170a(this.f599w3);
        c0140e.m24170a(this.f600x3);
        AbstractC0338u abstractC0338u = this.f601y3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m23698i() {
        return this.f601y3;
    }

    /* renamed from: j */
    public C0364d m23697j() {
        return this.f599w3;
    }

    /* renamed from: k */
    public C0445y0 m23696k() {
        return this.f600x3;
    }

    /* renamed from: l */
    public C0166k m23695l() {
        return this.f598v3;
    }

    public C0277f(C0430s1 c0430s1, C0445y0 c0445y0, AbstractC0338u abstractC0338u) {
        this.f598v3 = new C0166k(0L);
        this.f601y3 = null;
        this.f599w3 = C0364d.m23537a(c0430s1.mo23015d());
        this.f600x3 = c0445y0;
        this.f601y3 = abstractC0338u;
        if (c0430s1 == null || this.f598v3 == null || c0445y0 == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }

    public C0277f(AbstractC0263s abstractC0263s) {
        this.f598v3 = new C0166k(0L);
        this.f601y3 = null;
        this.f598v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f599w3 = C0364d.m23537a(abstractC0263s.mo23751a(1));
        this.f600x3 = C0445y0.m23102a(abstractC0263s.mo23751a(2));
        if (abstractC0263s.mo23745o() > 3) {
            this.f601y3 = AbstractC0338u.m23582a((AbstractC0494y) ((C0360v1) abstractC0263s.mo23751a(3)), false);
        }
        if (this.f599w3 == null || this.f598v3 == null || this.f600x3 == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }
}
