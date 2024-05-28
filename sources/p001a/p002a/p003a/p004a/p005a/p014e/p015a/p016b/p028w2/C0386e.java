package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0386e extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f1048v3;

    /* renamed from: w3 */
    public AbstractC0338u f1049w3;

    public C0386e(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            this.f1048v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
            this.f1049w3 = AbstractC0338u.m23581a((Object) abstractC0263s.mo23751a(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0386e m23414a(Object obj) {
        if (obj instanceof C0386e) {
            return (C0386e) obj;
        }
        if (obj != null) {
            return new C0386e(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1048v3);
        c0140e.m24170a(this.f1049w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23413i() {
        return new C0178n(this.f1048v3.m24113n());
    }

    /* renamed from: j */
    public AbstractC0338u m23412j() {
        return this.f1049w3;
    }

    /* renamed from: k */
    public InterfaceC0136d[] m23411k() {
        return this.f1049w3.m23574q();
    }

    public C0386e(C0178n c0178n, AbstractC0338u abstractC0338u) {
        this.f1048v3 = c0178n;
        this.f1049w3 = abstractC0338u;
    }
}
