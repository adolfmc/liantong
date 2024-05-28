package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.s2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0267a extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f561v3;

    /* renamed from: w3 */
    public AbstractC0338u f562w3;

    public C0267a(AbstractC0263s abstractC0263s) {
        this.f561v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f562w3 = (AbstractC0338u) abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0267a m23743a(Object obj) {
        if (obj != null && !(obj instanceof C0267a)) {
            if (obj instanceof AbstractC0263s) {
                return new C0267a((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0267a) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f561v3);
        c0140e.m24170a(this.f562w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23742i() {
        return this.f561v3;
    }

    /* renamed from: j */
    public AbstractC0338u m23741j() {
        return this.f562w3;
    }

    /* renamed from: k */
    public InterfaceC0136d[] m23740k() {
        return this.f562w3.m23574q();
    }

    public C0267a(C0178n c0178n, AbstractC0338u abstractC0338u) {
        this.f561v3 = c0178n;
        this.f562w3 = abstractC0338u;
    }
}
