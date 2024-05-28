package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.o0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0417o0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f1230v3;

    /* renamed from: w3 */
    public AbstractC0263s f1231w3;

    public C0417o0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 1 && abstractC0263s.mo23745o() <= 2) {
            this.f1230v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
            if (abstractC0263s.mo23745o() > 1) {
                this.f1231w3 = AbstractC0263s.m23749a(abstractC0263s.mo23751a(1));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0417o0 m23226a(Object obj) {
        if (obj != null && !(obj instanceof C0417o0)) {
            return new C0417o0(AbstractC0263s.m23749a(obj));
        }
        return (C0417o0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1230v3);
        AbstractC0263s abstractC0263s = this.f1231w3;
        if (abstractC0263s != null) {
            c0140e.m24170a(abstractC0263s);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23225i() {
        return this.f1230v3;
    }

    /* renamed from: j */
    public AbstractC0263s m23224j() {
        return this.f1231w3;
    }

    public C0417o0(C0178n c0178n) {
        this.f1230v3 = c0178n;
    }

    public C0417o0(C0178n c0178n, AbstractC0263s abstractC0263s) {
        this.f1230v3 = c0178n;
        this.f1231w3 = abstractC0263s;
    }
}
