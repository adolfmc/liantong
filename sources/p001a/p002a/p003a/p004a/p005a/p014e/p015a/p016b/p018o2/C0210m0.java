package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.o2.m0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0210m0 extends AbstractC0174m {

    /* renamed from: v3 */
    public final C0207l f318v3;

    /* renamed from: w3 */
    public final C0207l f319w3;

    public C0210m0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23751a(0) instanceof AbstractC0494y) {
            this.f318v3 = C0207l.m23974a(AbstractC0494y.m23008a(abstractC0263s.mo23751a(0)), true);
            this.f319w3 = C0207l.m23973a(abstractC0263s.mo23751a(1));
            return;
        }
        this.f318v3 = null;
        this.f319w3 = C0207l.m23973a(abstractC0263s.mo23751a(0));
    }

    /* renamed from: a */
    public static C0210m0 m23963a(Object obj) {
        if (obj instanceof C0210m0) {
            return (C0210m0) obj;
        }
        if (obj != null) {
            return new C0210m0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0207l c0207l = this.f318v3;
        if (c0207l != null) {
            c0140e.m24170a(new C0360v1(true, 0, c0207l));
        }
        c0140e.m24170a(this.f319w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0207l m23962i() {
        return this.f318v3;
    }

    /* renamed from: j */
    public C0207l m23961j() {
        return this.f319w3;
    }

    public C0210m0(C0207l c0207l) {
        this.f318v3 = null;
        this.f319w3 = c0207l;
    }

    public C0210m0(C0207l c0207l, C0207l c0207l2) {
        this.f318v3 = c0207l;
        this.f319w3 = c0207l2;
    }
}
