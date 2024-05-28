package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0496y1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.C0293v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0378b0;

/* renamed from: a.a.a.a.a.e.a.b.p2.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0244h extends AbstractC0174m {

    /* renamed from: v3 */
    public final C0293v f443v3;

    /* renamed from: w3 */
    public final InterfaceC0136d f444w3;

    public C0244h(AbstractC0263s abstractC0263s) {
        this.f443v3 = C0293v.m23647a(abstractC0263s.mo23751a(0));
        if (abstractC0263s.mo23745o() > 1) {
            if (!(abstractC0263s.mo23751a(1) instanceof C0496y1)) {
                this.f444w3 = C0378b0.m23455a(abstractC0263s.mo23751a(1));
                return;
            } else {
                this.f444w3 = abstractC0263s.mo23751a(1);
                return;
            }
        }
        this.f444w3 = null;
    }

    /* renamed from: a */
    public static C0244h m23804a(Object obj) {
        if (obj instanceof C0244h) {
            return (C0244h) obj;
        }
        if (obj != null) {
            return new C0244h(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f443v3);
        InterfaceC0136d interfaceC0136d = this.f444w3;
        if (interfaceC0136d != null) {
            c0140e.m24170a(interfaceC0136d);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public InterfaceC0136d m23803i() {
        return this.f444w3;
    }

    /* renamed from: j */
    public C0293v m23802j() {
        return this.f443v3;
    }

    /* renamed from: k */
    public boolean m23801k() {
        return this.f444w3 != null;
    }

    /* renamed from: l */
    public boolean m23800l() {
        return this.f444w3 instanceof C0496y1;
    }

    public C0244h(C0293v c0293v) {
        this.f443v3 = c0293v;
        this.f444w3 = null;
    }

    public C0244h(C0293v c0293v, C0496y1 c0496y1) {
        this.f443v3 = c0293v;
        this.f444w3 = c0496y1;
    }

    public C0244h(C0293v c0293v, C0378b0 c0378b0) {
        this.f443v3 = c0293v;
        this.f444w3 = c0378b0;
    }
}
