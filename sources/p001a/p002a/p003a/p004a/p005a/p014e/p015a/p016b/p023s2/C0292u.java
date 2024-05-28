package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;

/* renamed from: a.a.a.a.a.e.a.b.s2.u */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0292u extends AbstractC0174m implements InterfaceC0291t {

    /* renamed from: v3 */
    public C0278g f765v3;

    /* renamed from: w3 */
    public C0285n f766w3;

    public C0292u(AbstractC0263s abstractC0263s) {
        this.f766w3 = null;
        if (((C0166k) abstractC0263s.mo23751a(0)).m24145n().intValue() == 3) {
            this.f765v3 = C0278g.m23694a(abstractC0263s.mo23751a(1));
            if (abstractC0263s.mo23745o() == 3) {
                this.f766w3 = C0285n.m23671a(abstractC0263s.mo23751a(2));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("wrong version for PFX PDU");
    }

    /* renamed from: a */
    public static C0292u m23651a(Object obj) {
        if (obj instanceof C0292u) {
            return (C0292u) obj;
        }
        if (obj != null) {
            return new C0292u(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(3L));
        c0140e.m24170a(this.f765v3);
        C0285n c0285n = this.f766w3;
        if (c0285n != null) {
            c0140e.m24170a(c0285n);
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public C0278g m23650i() {
        return this.f765v3;
    }

    /* renamed from: j */
    public C0285n m23649j() {
        return this.f766w3;
    }

    public C0292u(C0278g c0278g, C0285n c0285n) {
        this.f766w3 = null;
        this.f765v3 = c0278g;
        this.f766w3 = c0285n;
    }
}
