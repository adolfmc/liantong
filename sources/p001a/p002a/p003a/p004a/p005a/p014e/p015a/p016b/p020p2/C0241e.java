package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0445y0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0446z;

/* renamed from: a.a.a.a.a.e.a.b.p2.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0241e extends AbstractC0174m {

    /* renamed from: A3 */
    public C0247k f421A3;

    /* renamed from: B3 */
    public C0364d f422B3;

    /* renamed from: C3 */
    public C0445y0 f423C3;

    /* renamed from: D3 */
    public C0359v0 f424D3;

    /* renamed from: E3 */
    public C0359v0 f425E3;

    /* renamed from: F3 */
    public C0446z f426F3;

    /* renamed from: v3 */
    public AbstractC0263s f427v3;

    /* renamed from: w3 */
    public C0166k f428w3;

    /* renamed from: x3 */
    public C0166k f429x3;

    /* renamed from: y3 */
    public C0377b f430y3;

    /* renamed from: z3 */
    public C0364d f431z3;

    public C0241e(AbstractC0263s abstractC0263s) {
        this.f427v3 = abstractC0263s;
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) mo23747m.nextElement();
            switch (abstractC0494y.mo22994f()) {
                case 0:
                    this.f428w3 = C0151g1.m24148a(abstractC0494y, false);
                    break;
                case 1:
                    this.f429x3 = C0151g1.m24148a(abstractC0494y, false);
                    break;
                case 2:
                    this.f430y3 = C0377b.m23461a(abstractC0494y, false);
                    break;
                case 3:
                    this.f431z3 = C0364d.m23538a(abstractC0494y, true);
                    break;
                case 4:
                    this.f421A3 = C0247k.m23788a(AbstractC0263s.m23750a(abstractC0494y, false));
                    break;
                case 5:
                    this.f422B3 = C0364d.m23538a(abstractC0494y, true);
                    break;
                case 6:
                    this.f423C3 = C0445y0.m23103a(abstractC0494y, false);
                    break;
                case 7:
                    this.f424D3 = C0359v0.m23558a(abstractC0494y, false);
                    break;
                case 8:
                    this.f425E3 = C0359v0.m23558a(abstractC0494y, false);
                    break;
                case 9:
                    this.f426F3 = C0446z.m23094a(abstractC0494y, false);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag: " + abstractC0494y.mo22994f());
            }
        }
    }

    /* renamed from: a */
    public static C0241e m23830a(Object obj) {
        if (obj instanceof C0241e) {
            return (C0241e) obj;
        }
        if (obj != null) {
            return new C0241e(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f427v3;
    }

    /* renamed from: i */
    public C0446z m23829i() {
        return this.f426F3;
    }

    /* renamed from: j */
    public C0364d m23828j() {
        return this.f431z3;
    }

    /* renamed from: k */
    public C0359v0 m23827k() {
        return this.f424D3;
    }

    /* renamed from: l */
    public C0445y0 m23826l() {
        return this.f423C3;
    }

    /* renamed from: m */
    public C0166k m23825m() {
        return this.f429x3;
    }

    /* renamed from: n */
    public C0377b m23824n() {
        return this.f430y3;
    }

    /* renamed from: o */
    public C0364d m23823o() {
        return this.f422B3;
    }

    /* renamed from: p */
    public C0359v0 m23822p() {
        return this.f425E3;
    }

    /* renamed from: q */
    public C0247k m23821q() {
        return this.f421A3;
    }

    /* renamed from: r */
    public int m23820r() {
        return this.f428w3.m24145n().intValue();
    }
}
