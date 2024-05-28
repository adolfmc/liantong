package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.w2.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0416o extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f1226v3;

    /* renamed from: w3 */
    public C0376a1 f1227w3;

    /* renamed from: x3 */
    public C0377b f1228x3;

    /* renamed from: y3 */
    public C0359v0 f1229y3;

    public C0416o(AbstractC0263s abstractC0263s) {
        this.f1226v3 = abstractC0263s;
        if (abstractC0263s.mo23745o() == 3) {
            this.f1227w3 = C0376a1.m23474a(abstractC0263s.mo23751a(0));
            this.f1228x3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
            this.f1229y3 = C0359v0.m23557a(abstractC0263s.mo23751a(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for a certificate");
    }

    /* renamed from: a */
    public static C0416o m23239a(AbstractC0494y abstractC0494y, boolean z) {
        return m23238a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1226v3;
    }

    /* renamed from: i */
    public C0391f1 m23237i() {
        return this.f1227w3.m23473i();
    }

    /* renamed from: j */
    public C0364d m23236j() {
        return this.f1227w3.m23471k();
    }

    /* renamed from: k */
    public C0166k m23235k() {
        return this.f1227w3.m23469m();
    }

    /* renamed from: l */
    public C0359v0 m23234l() {
        return this.f1229y3;
    }

    /* renamed from: m */
    public C0377b m23233m() {
        return this.f1228x3;
    }

    /* renamed from: n */
    public C0391f1 m23232n() {
        return this.f1227w3.m23467o();
    }

    /* renamed from: o */
    public C0364d m23231o() {
        return this.f1227w3.m23466p();
    }

    /* renamed from: p */
    public C0445y0 m23230p() {
        return this.f1227w3.m23465q();
    }

    /* renamed from: q */
    public C0376a1 m23229q() {
        return this.f1227w3;
    }

    /* renamed from: r */
    public C0166k m23228r() {
        return this.f1227w3.m23463s();
    }

    /* renamed from: s */
    public int m23227s() {
        return this.f1227w3.m23462t();
    }

    /* renamed from: a */
    public static C0416o m23238a(Object obj) {
        if (obj instanceof C0416o) {
            return (C0416o) obj;
        }
        if (obj != null) {
            return new C0416o(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
