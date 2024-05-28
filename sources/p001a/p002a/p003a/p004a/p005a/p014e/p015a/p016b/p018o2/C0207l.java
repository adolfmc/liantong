package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0235p0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.l */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0207l extends AbstractC0174m implements InterfaceC0201i {

    /* renamed from: v3 */
    public C0178n f311v3;

    /* renamed from: w3 */
    public InterfaceC0136d f312w3;

    public C0207l(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 1 && abstractC0263s.mo23745o() <= 2) {
            this.f311v3 = (C0178n) abstractC0263s.mo23751a(0);
            if (abstractC0263s.mo23745o() > 1) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0263s.mo23751a(1);
                if (abstractC0494y.m23002o() && abstractC0494y.mo22994f() == 0) {
                    this.f312w3 = abstractC0494y.m23004m();
                    return;
                }
                throw new IllegalArgumentException("Bad tag for 'content'");
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0207l m23973a(Object obj) {
        if (obj instanceof C0207l) {
            return (C0207l) obj;
        }
        if (obj != null) {
            return new C0207l(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f311v3);
        InterfaceC0136d interfaceC0136d = this.f312w3;
        if (interfaceC0136d != null) {
            c0140e.m24170a(new C0235p0(0, interfaceC0136d));
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public InterfaceC0136d m23972i() {
        return this.f312w3;
    }

    /* renamed from: j */
    public C0178n m23971j() {
        return this.f311v3;
    }

    /* renamed from: a */
    public static C0207l m23974a(AbstractC0494y abstractC0494y, boolean z) {
        return m23973a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    public C0207l(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f311v3 = c0178n;
        this.f312w3 = interfaceC0136d;
    }
}
