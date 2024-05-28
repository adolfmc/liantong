package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0392g extends AbstractC0174m {

    /* renamed from: A3 */
    public C0383d f1065A3;

    /* renamed from: B3 */
    public AbstractC0263s f1066B3;

    /* renamed from: C3 */
    public C0359v0 f1067C3;

    /* renamed from: D3 */
    public C0446z f1068D3;

    /* renamed from: v3 */
    public C0166k f1069v3;

    /* renamed from: w3 */
    public C0390f0 f1070w3;

    /* renamed from: x3 */
    public C0380c f1071x3;

    /* renamed from: y3 */
    public C0377b f1072y3;

    /* renamed from: z3 */
    public C0166k f1073z3;

    public C0392g(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 7 && abstractC0263s.mo23745o() <= 9) {
            this.f1069v3 = C0151g1.m24147a(abstractC0263s.mo23751a(0));
            this.f1070w3 = C0390f0.m23399a(abstractC0263s.mo23751a(1));
            this.f1071x3 = C0380c.m23431a(abstractC0263s.mo23751a(2));
            this.f1072y3 = C0377b.m23460a(abstractC0263s.mo23751a(3));
            this.f1073z3 = C0151g1.m24147a(abstractC0263s.mo23751a(4));
            this.f1065A3 = C0383d.m23422a(abstractC0263s.mo23751a(5));
            this.f1066B3 = AbstractC0263s.m23749a(abstractC0263s.mo23751a(6));
            for (int i = 7; i < abstractC0263s.mo23745o(); i++) {
                InterfaceC0136d mo23751a = abstractC0263s.mo23751a(i);
                if (mo23751a instanceof C0359v0) {
                    this.f1067C3 = C0359v0.m23557a(abstractC0263s.mo23751a(i));
                } else if ((mo23751a instanceof AbstractC0263s) || (mo23751a instanceof C0446z)) {
                    this.f1068D3 = C0446z.m23093a(abstractC0263s.mo23751a(i));
                }
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0392g m23390a(AbstractC0494y abstractC0494y, boolean z) {
        return m23389a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1069v3);
        c0140e.m24170a(this.f1070w3);
        c0140e.m24170a(this.f1071x3);
        c0140e.m24170a(this.f1072y3);
        c0140e.m24170a(this.f1073z3);
        c0140e.m24170a(this.f1065A3);
        c0140e.m24170a(this.f1066B3);
        C0359v0 c0359v0 = this.f1067C3;
        if (c0359v0 != null) {
            c0140e.m24170a(c0359v0);
        }
        C0446z c0446z = this.f1068D3;
        if (c0446z != null) {
            c0140e.m24170a(c0446z);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0383d m23388i() {
        return this.f1065A3;
    }

    /* renamed from: j */
    public AbstractC0263s m23387j() {
        return this.f1066B3;
    }

    /* renamed from: k */
    public C0446z m23386k() {
        return this.f1068D3;
    }

    /* renamed from: l */
    public C0390f0 m23385l() {
        return this.f1070w3;
    }

    /* renamed from: m */
    public C0380c m23384m() {
        return this.f1071x3;
    }

    /* renamed from: n */
    public C0359v0 m23383n() {
        return this.f1067C3;
    }

    /* renamed from: o */
    public C0166k m23382o() {
        return this.f1073z3;
    }

    /* renamed from: p */
    public C0377b m23381p() {
        return this.f1072y3;
    }

    /* renamed from: q */
    public C0166k m23380q() {
        return this.f1069v3;
    }

    /* renamed from: a */
    public static C0392g m23389a(Object obj) {
        if (obj instanceof C0392g) {
            return (C0392g) obj;
        }
        if (obj != null) {
            return new C0392g(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
