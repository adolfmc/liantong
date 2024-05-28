package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0135c2;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0143e2;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.s2.c0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0272c0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f577v3;

    /* renamed from: w3 */
    public InterfaceC0136d f578w3;

    /* renamed from: x3 */
    public AbstractC0338u f579x3;

    public C0272c0(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f577v3 = c0178n;
        this.f578w3 = interfaceC0136d;
        this.f579x3 = null;
    }

    /* renamed from: a */
    public static C0272c0 m23725a(Object obj) {
        if (obj instanceof C0272c0) {
            return (C0272c0) obj;
        }
        if (obj != null) {
            return new C0272c0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f577v3);
        c0140e.m24170a(new C0143e2(true, 0, this.f578w3));
        AbstractC0338u abstractC0338u = this.f579x3;
        if (abstractC0338u != null) {
            c0140e.m24170a(abstractC0338u);
        }
        return new C0135c2(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m23724i() {
        return this.f579x3;
    }

    /* renamed from: j */
    public C0178n m23723j() {
        return this.f577v3;
    }

    /* renamed from: k */
    public InterfaceC0136d m23722k() {
        return this.f578w3;
    }

    public C0272c0(C0178n c0178n, InterfaceC0136d interfaceC0136d, AbstractC0338u abstractC0338u) {
        this.f577v3 = c0178n;
        this.f578w3 = interfaceC0136d;
        this.f579x3 = abstractC0338u;
    }

    public C0272c0(AbstractC0263s abstractC0263s) {
        this.f577v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f578w3 = ((AbstractC0494y) abstractC0263s.mo23751a(1)).m23004m();
        if (abstractC0263s.mo23745o() == 3) {
            this.f579x3 = (AbstractC0338u) abstractC0263s.mo23751a(2);
        }
    }
}
