package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0148g;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0495y0;

/* renamed from: a.a.a.a.a.e.a.b.w2.n0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0414n0 extends AbstractC0174m {

    /* renamed from: A3 */
    public static final int f1215A3 = 1;

    /* renamed from: B3 */
    public static final int f1216B3 = 2;

    /* renamed from: z3 */
    public static final int f1217z3 = 0;

    /* renamed from: v3 */
    public C0148g f1218v3;

    /* renamed from: w3 */
    public C0178n f1219w3;

    /* renamed from: x3 */
    public C0377b f1220x3;

    /* renamed from: y3 */
    public C0359v0 f1221y3;

    public C0414n0(int i, C0178n c0178n, C0377b c0377b, byte[] bArr) {
        this.f1218v3 = new C0148g(i);
        if (i == 2) {
            this.f1219w3 = c0178n;
        }
        this.f1220x3 = c0377b;
        this.f1221y3 = new C0359v0(bArr);
    }

    /* renamed from: a */
    public static C0414n0 m23256a(Object obj) {
        if (obj instanceof C0414n0) {
            return (C0414n0) obj;
        }
        if (obj != null) {
            return new C0414n0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1218v3);
        C0178n c0178n = this.f1219w3;
        if (c0178n != null) {
            c0140e.m24170a(c0178n);
        }
        c0140e.m24170a(this.f1220x3);
        c0140e.m24170a(this.f1221y3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23255i() {
        return this.f1220x3;
    }

    /* renamed from: j */
    public C0148g m23254j() {
        return this.f1218v3;
    }

    /* renamed from: k */
    public C0359v0 m23253k() {
        return this.f1221y3;
    }

    /* renamed from: l */
    public C0178n m23252l() {
        return this.f1219w3;
    }

    /* renamed from: a */
    public static C0414n0 m23257a(AbstractC0494y abstractC0494y, boolean z) {
        return m23256a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    public C0414n0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() <= 4 && abstractC0263s.mo23745o() >= 3) {
            int i = 0;
            this.f1218v3 = C0495y0.m23000a(abstractC0263s.mo23751a(0));
            if (abstractC0263s.mo23745o() == 4) {
                this.f1219w3 = C0164j1.m24118a(abstractC0263s.mo23751a(1));
                i = 1;
            }
            this.f1220x3 = C0377b.m23460a(abstractC0263s.mo23751a(i + 1));
            this.f1221y3 = C0359v0.m23557a(abstractC0263s.mo23751a(i + 2));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
