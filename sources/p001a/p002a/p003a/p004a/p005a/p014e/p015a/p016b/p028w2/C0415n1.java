package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.InterfaceC0291t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.w2.n1 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0415n1 extends AbstractC0174m implements InterfaceC0291t, InterfaceC0439v1 {

    /* renamed from: v3 */
    public AbstractC0263s f1222v3;

    /* renamed from: w3 */
    public C0379b1 f1223w3;

    /* renamed from: x3 */
    public C0377b f1224x3;

    /* renamed from: y3 */
    public C0359v0 f1225y3;

    public C0415n1(AbstractC0263s abstractC0263s) {
        this.f1222v3 = abstractC0263s;
        if (abstractC0263s.mo23745o() == 3) {
            this.f1223w3 = C0379b1.m23445a(abstractC0263s.mo23751a(0));
            this.f1224x3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
            this.f1225y3 = C0359v0.m23557a(abstractC0263s.mo23751a(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for a certificate");
    }

    /* renamed from: a */
    public static C0415n1 m23251a(AbstractC0494y abstractC0494y, boolean z) {
        return m23250a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1222v3;
    }

    /* renamed from: i */
    public C0391f1 m23249i() {
        return this.f1223w3.m23444i();
    }

    /* renamed from: j */
    public C0364d m23248j() {
        return this.f1223w3.m23442k();
    }

    /* renamed from: k */
    public C0166k m23247k() {
        return this.f1223w3.m23440m();
    }

    /* renamed from: l */
    public C0359v0 m23246l() {
        return this.f1225y3;
    }

    /* renamed from: m */
    public C0377b m23245m() {
        return this.f1224x3;
    }

    /* renamed from: n */
    public C0391f1 m23244n() {
        return this.f1223w3.m23438o();
    }

    /* renamed from: o */
    public C0364d m23243o() {
        return this.f1223w3.m23437p();
    }

    /* renamed from: p */
    public C0445y0 m23242p() {
        return this.f1223w3.m23436q();
    }

    /* renamed from: q */
    public C0379b1 m23241q() {
        return this.f1223w3;
    }

    /* renamed from: r */
    public int m23240r() {
        return this.f1223w3.m23434s();
    }

    /* renamed from: a */
    public static C0415n1 m23250a(Object obj) {
        if (obj instanceof C0415n1) {
            return (C0415n1) obj;
        }
        if (obj != null) {
            return new C0415n1(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
