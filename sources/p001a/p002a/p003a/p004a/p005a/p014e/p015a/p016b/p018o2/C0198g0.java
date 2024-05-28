package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.g0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0198g0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f277v3;

    /* renamed from: w3 */
    public InterfaceC0136d f278w3;

    public C0198g0(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f277v3 = c0178n;
        this.f278w3 = interfaceC0136d;
    }

    /* renamed from: a */
    public static C0198g0 m24004a(AbstractC0494y abstractC0494y, boolean z) {
        return m24003a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f277v3);
        c0140e.m24170a(this.f278w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public InterfaceC0136d m24002i() {
        return this.f278w3;
    }

    /* renamed from: j */
    public C0178n m24001j() {
        return this.f277v3;
    }

    /* renamed from: a */
    public static C0198g0 m24003a(Object obj) {
        if (obj instanceof C0198g0) {
            return (C0198g0) obj;
        }
        if (obj != null) {
            return new C0198g0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0198g0(AbstractC0263s abstractC0263s) {
        this.f277v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
        this.f278w3 = abstractC0263s.mo23751a(1);
    }
}
