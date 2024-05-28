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

/* renamed from: a.a.a.a.a.e.a.b.o2.f0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0196f0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f271v3;

    /* renamed from: w3 */
    public InterfaceC0136d f272w3;

    public C0196f0(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f271v3 = c0178n;
        this.f272w3 = interfaceC0136d;
    }

    /* renamed from: a */
    public static C0196f0 m24017a(AbstractC0494y abstractC0494y, boolean z) {
        return m24016a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f271v3);
        c0140e.m24170a(this.f272w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m24015i() {
        return this.f271v3;
    }

    /* renamed from: j */
    public InterfaceC0136d m24014j() {
        return this.f272w3;
    }

    /* renamed from: a */
    public static C0196f0 m24016a(Object obj) {
        if (obj instanceof C0196f0) {
            return (C0196f0) obj;
        }
        if (obj != null) {
            return new C0196f0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0196f0(AbstractC0263s abstractC0263s) {
        this.f271v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
        this.f272w3 = abstractC0263s.mo23751a(1);
    }
}
