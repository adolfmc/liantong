package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0419p;

/* renamed from: a.a.a.a.a.e.a.b.o2.s0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0222s0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0207l f368v3;

    /* renamed from: w3 */
    public C0419p f369w3;

    public C0222s0(C0207l c0207l) {
        this.f368v3 = c0207l;
    }

    /* renamed from: a */
    public static C0222s0 m23899a(Object obj) {
        if (obj instanceof C0222s0) {
            return (C0222s0) obj;
        }
        if (obj != null) {
            return new C0222s0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f368v3);
        C0419p c0419p = this.f369w3;
        if (c0419p != null) {
            c0140e.m24170a(c0419p);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0419p m23898i() {
        return this.f369w3;
    }

    /* renamed from: j */
    public C0419p m23897j() {
        return this.f369w3;
    }

    /* renamed from: k */
    public C0207l m23896k() {
        return this.f368v3;
    }

    public C0222s0(AbstractC0263s abstractC0263s) {
        this.f368v3 = C0207l.m23973a(abstractC0263s.mo23751a(0));
        if (abstractC0263s.mo23745o() == 2) {
            this.f369w3 = C0419p.m23222a(abstractC0263s.mo23751a(1));
        }
    }
}
