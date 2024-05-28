package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0235p0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0280i extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f608v3;

    /* renamed from: w3 */
    public C0178n f609w3;

    /* renamed from: x3 */
    public AbstractC0258r f610x3;

    public C0280i(AbstractC0263s abstractC0263s) {
        if (((C0166k) abstractC0263s.mo23751a(0)).m24145n().intValue() == 0) {
            this.f608v3 = AbstractC0263s.m23749a(abstractC0263s.mo23751a(1));
            return;
        }
        throw new IllegalArgumentException("sequence not version 0");
    }

    /* renamed from: a */
    public static C0280i m23687a(Object obj) {
        if (obj instanceof C0280i) {
            return (C0280i) obj;
        }
        if (obj != null) {
            return new C0280i(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(0L));
        c0140e.m24170a(this.f608v3);
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23686i() {
        if (this.f608v3.mo23745o() == 3) {
            return AbstractC0182o.m24090a(AbstractC0494y.m23008a(this.f608v3.mo23751a(2)), false);
        }
        return null;
    }

    /* renamed from: j */
    public C0178n m23685j() {
        return C0164j1.m24118a(this.f608v3.mo23751a(0));
    }

    /* renamed from: k */
    public C0377b m23684k() {
        return C0377b.m23460a(this.f608v3.mo23751a(1));
    }

    public C0280i(C0178n c0178n, C0377b c0377b, InterfaceC0136d interfaceC0136d) {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(c0178n);
        c0140e.m24170a(c0377b.mo23015d());
        c0140e.m24170a(new C0235p0(false, 0, interfaceC0136d));
        this.f608v3 = new C0167k0(c0140e);
    }
}
