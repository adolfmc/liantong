package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.e0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0194e0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f260v3;

    /* renamed from: w3 */
    public InterfaceC0136d f261w3;

    public C0194e0(AbstractC0263s abstractC0263s) {
        this.f260v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f261w3 = abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0194e0 m24032a(Object obj) {
        if (obj != null && !(obj instanceof C0194e0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0194e0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0194e0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f260v3);
        c0140e.m24170a(this.f261w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public InterfaceC0136d m24031i() {
        return this.f261w3;
    }

    /* renamed from: j */
    public C0178n m24030j() {
        return this.f260v3;
    }

    public C0194e0(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f260v3 = c0178n;
        this.f261w3 = interfaceC0136d;
    }
}
