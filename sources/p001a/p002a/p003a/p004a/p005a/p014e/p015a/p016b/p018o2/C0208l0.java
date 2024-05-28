package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0138d1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.o2.l0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0208l0 extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0182o f313v3;

    /* renamed from: w3 */
    public C0138d1 f314w3;

    /* renamed from: x3 */
    public C0194e0 f315x3;

    public C0208l0(AbstractC0182o abstractC0182o, C0138d1 c0138d1, C0194e0 c0194e0) {
        this.f313v3 = abstractC0182o;
        this.f314w3 = c0138d1;
        this.f315x3 = c0194e0;
    }

    /* renamed from: a */
    public static C0208l0 m23970a(AbstractC0494y abstractC0494y, boolean z) {
        return m23969a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f313v3);
        C0138d1 c0138d1 = this.f314w3;
        if (c0138d1 != null) {
            c0140e.m24170a(c0138d1);
        }
        C0194e0 c0194e0 = this.f315x3;
        if (c0194e0 != null) {
            c0140e.m24170a(c0194e0);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0138d1 m23968i() {
        return this.f314w3;
    }

    /* renamed from: j */
    public C0194e0 m23967j() {
        return this.f315x3;
    }

    /* renamed from: k */
    public AbstractC0182o m23966k() {
        return this.f313v3;
    }

    /* renamed from: a */
    public static C0208l0 m23969a(Object obj) {
        if (obj != null && !(obj instanceof C0208l0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0208l0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid RecipientKeyIdentifier: " + obj.getClass().getName());
        }
        return (C0208l0) obj;
    }

    public C0208l0(byte[] bArr, C0138d1 c0138d1, C0194e0 c0194e0) {
        this.f313v3 = new C0168k1(bArr);
        this.f314w3 = c0138d1;
        this.f315x3 = c0194e0;
    }

    public C0208l0(byte[] bArr) {
        this(bArr, (C0138d1) null, (C0194e0) null);
    }

    public C0208l0(AbstractC0263s abstractC0263s) {
        this.f313v3 = AbstractC0182o.m24089a(abstractC0263s.mo23751a(0));
        int mo23745o = abstractC0263s.mo23745o();
        if (mo23745o != 1) {
            if (mo23745o != 2) {
                if (mo23745o == 3) {
                    this.f314w3 = (C0138d1) abstractC0263s.mo23751a(1);
                    this.f315x3 = C0194e0.m24032a(abstractC0263s.mo23751a(2));
                    return;
                }
                throw new IllegalArgumentException("Invalid RecipientKeyIdentifier");
            } else if (abstractC0263s.mo23751a(1) instanceof C0138d1) {
                this.f314w3 = (C0138d1) abstractC0263s.mo23751a(1);
            } else {
                this.f315x3 = C0194e0.m24032a(abstractC0263s.mo23751a(2));
            }
        }
    }
}
