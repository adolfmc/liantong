package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.r */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0219r extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f358v3;

    /* renamed from: w3 */
    public C0190c0 f359w3;

    /* renamed from: x3 */
    public AbstractC0338u f360x3;

    /* renamed from: y3 */
    public C0213o f361y3;

    /* renamed from: z3 */
    public AbstractC0338u f362z3;

    public C0219r(C0190c0 c0190c0, AbstractC0338u abstractC0338u, C0213o c0213o, AbstractC0338u abstractC0338u2) {
        this.f358v3 = new C0166k(m23916a(c0190c0, abstractC0338u, abstractC0338u2));
        this.f359w3 = c0190c0;
        this.f360x3 = abstractC0338u;
        this.f361y3 = c0213o;
        this.f362z3 = abstractC0338u2;
    }

    /* renamed from: a */
    public static C0219r m23915a(AbstractC0494y abstractC0494y, boolean z) {
        return m23914a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f358v3);
        C0190c0 c0190c0 = this.f359w3;
        if (c0190c0 != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0190c0));
        }
        c0140e.m24170a(this.f360x3);
        c0140e.m24170a(this.f361y3);
        AbstractC0338u abstractC0338u = this.f362z3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 1, abstractC0338u));
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public C0213o m23913i() {
        return this.f361y3;
    }

    /* renamed from: j */
    public C0190c0 m23912j() {
        return this.f359w3;
    }

    /* renamed from: k */
    public AbstractC0338u m23911k() {
        return this.f360x3;
    }

    /* renamed from: l */
    public AbstractC0338u m23910l() {
        return this.f362z3;
    }

    /* renamed from: m */
    public C0166k m23909m() {
        return this.f358v3;
    }

    /* renamed from: a */
    public static C0219r m23914a(Object obj) {
        if (obj instanceof C0219r) {
            return (C0219r) obj;
        }
        if (obj != null) {
            return new C0219r(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0219r(C0190c0 c0190c0, AbstractC0338u abstractC0338u, C0213o c0213o, C0189c c0189c) {
        this.f358v3 = new C0166k(m23916a(c0190c0, abstractC0338u, AbstractC0338u.m23581a((Object) c0189c)));
        this.f359w3 = c0190c0;
        this.f360x3 = abstractC0338u;
        this.f361y3 = c0213o;
        this.f362z3 = AbstractC0338u.m23581a((Object) c0189c);
    }

    /* renamed from: a */
    public static int m23916a(C0190c0 c0190c0, AbstractC0338u abstractC0338u, AbstractC0338u abstractC0338u2) {
        if (c0190c0 == null && abstractC0338u2 == null) {
            Enumeration m23578m = abstractC0338u.m23578m();
            while (m23578m.hasMoreElements()) {
                if (C0206k0.m23978a(m23578m.nextElement()).m23976j().m24145n().intValue() != 0) {
                    return 2;
                }
            }
            return 0;
        }
        return 2;
    }

    public C0219r(AbstractC0263s abstractC0263s) {
        InterfaceC0136d interfaceC0136d;
        int i;
        this.f358v3 = (C0166k) abstractC0263s.mo23751a(0);
        InterfaceC0136d mo23751a = abstractC0263s.mo23751a(1);
        if (mo23751a instanceof AbstractC0494y) {
            this.f359w3 = C0190c0.m24056a((AbstractC0494y) mo23751a, false);
            i = 3;
            interfaceC0136d = abstractC0263s.mo23751a(2);
        } else {
            interfaceC0136d = mo23751a;
            i = 2;
        }
        this.f360x3 = AbstractC0338u.m23581a((Object) interfaceC0136d);
        int i2 = i + 1;
        this.f361y3 = C0213o.m23945a(abstractC0263s.mo23751a(i));
        if (abstractC0263s.mo23745o() > i2) {
            this.f362z3 = AbstractC0338u.m23582a((AbstractC0494y) abstractC0263s.mo23751a(i2), false);
        }
    }
}
