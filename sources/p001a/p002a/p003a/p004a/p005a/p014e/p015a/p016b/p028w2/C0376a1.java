package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;

/* renamed from: a.a.a.a.a.e.a.b.w2.a1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0376a1 extends AbstractC0174m {

    /* renamed from: A3 */
    public C0391f1 f999A3;

    /* renamed from: B3 */
    public C0391f1 f1000B3;

    /* renamed from: C3 */
    public C0364d f1001C3;

    /* renamed from: D3 */
    public C0445y0 f1002D3;

    /* renamed from: E3 */
    public C0359v0 f1003E3;

    /* renamed from: F3 */
    public C0359v0 f1004F3;

    /* renamed from: G3 */
    public C0446z f1005G3;

    /* renamed from: v3 */
    public AbstractC0263s f1006v3;

    /* renamed from: w3 */
    public C0166k f1007w3;

    /* renamed from: x3 */
    public C0166k f1008x3;

    /* renamed from: y3 */
    public C0377b f1009y3;

    /* renamed from: z3 */
    public C0364d f1010z3;

    public C0376a1(AbstractC0263s abstractC0263s) {
        int i;
        this.f1006v3 = abstractC0263s;
        if (abstractC0263s.mo23751a(0) instanceof C0360v1) {
            this.f1007w3 = C0151g1.m24148a((AbstractC0494y) abstractC0263s.mo23751a(0), true);
            i = 0;
        } else {
            this.f1007w3 = new C0166k(0L);
            i = -1;
        }
        this.f1008x3 = C0151g1.m24147a(abstractC0263s.mo23751a(i + 1));
        this.f1009y3 = C0377b.m23460a(abstractC0263s.mo23751a(i + 2));
        this.f1010z3 = C0364d.m23537a(abstractC0263s.mo23751a(i + 3));
        AbstractC0263s abstractC0263s2 = (AbstractC0263s) abstractC0263s.mo23751a(i + 4);
        this.f999A3 = C0391f1.m23393a(abstractC0263s2.mo23751a(0));
        this.f1000B3 = C0391f1.m23393a(abstractC0263s2.mo23751a(1));
        this.f1001C3 = C0364d.m23537a(abstractC0263s.mo23751a(i + 5));
        int i2 = i + 6;
        this.f1002D3 = C0445y0.m23102a(abstractC0263s.mo23751a(i2));
        for (int mo23745o = (abstractC0263s.mo23745o() - i2) - 1; mo23745o > 0; mo23745o--) {
            C0360v1 c0360v1 = (C0360v1) abstractC0263s.mo23751a(i2 + mo23745o);
            int mo22994f = c0360v1.mo22994f();
            if (mo22994f == 1) {
                this.f1003E3 = C0359v0.m23558a((AbstractC0494y) c0360v1, false);
            } else if (mo22994f == 2) {
                this.f1004F3 = C0359v0.m23558a((AbstractC0494y) c0360v1, false);
            } else if (mo22994f == 3) {
                this.f1005G3 = C0446z.m23093a(AbstractC0263s.m23750a((AbstractC0494y) c0360v1, true));
            }
        }
    }

    /* renamed from: a */
    public static C0376a1 m23475a(AbstractC0494y abstractC0494y, boolean z) {
        return m23474a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1006v3;
    }

    /* renamed from: i */
    public C0391f1 m23473i() {
        return this.f1000B3;
    }

    /* renamed from: j */
    public C0446z m23472j() {
        return this.f1005G3;
    }

    /* renamed from: k */
    public C0364d m23471k() {
        return this.f1010z3;
    }

    /* renamed from: l */
    public C0359v0 m23470l() {
        return this.f1003E3;
    }

    /* renamed from: m */
    public C0166k m23469m() {
        return this.f1008x3;
    }

    /* renamed from: n */
    public C0377b m23468n() {
        return this.f1009y3;
    }

    /* renamed from: o */
    public C0391f1 m23467o() {
        return this.f999A3;
    }

    /* renamed from: p */
    public C0364d m23466p() {
        return this.f1001C3;
    }

    /* renamed from: q */
    public C0445y0 m23465q() {
        return this.f1002D3;
    }

    /* renamed from: r */
    public C0359v0 m23464r() {
        return this.f1004F3;
    }

    /* renamed from: s */
    public C0166k m23463s() {
        return this.f1007w3;
    }

    /* renamed from: t */
    public int m23462t() {
        return this.f1007w3.m24145n().intValue() + 1;
    }

    /* renamed from: a */
    public static C0376a1 m23474a(Object obj) {
        if (obj instanceof C0376a1) {
            return (C0376a1) obj;
        }
        if (obj != null) {
            return new C0376a1(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
