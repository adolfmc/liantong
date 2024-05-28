package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0374a extends AbstractC0174m {

    /* renamed from: x3 */
    public static final C0178n f993x3 = new C0178n("1.3.6.1.5.5.7.48.2");

    /* renamed from: y3 */
    public static final C0178n f994y3 = new C0178n("1.3.6.1.5.5.7.48.1");

    /* renamed from: v3 */
    public C0178n f995v3;

    /* renamed from: w3 */
    public C0378b0 f996w3;

    public C0374a(AbstractC0263s abstractC0263s) {
        this.f995v3 = null;
        this.f996w3 = null;
        if (abstractC0263s.mo23745o() == 2) {
            this.f995v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
            this.f996w3 = C0378b0.m23455a(abstractC0263s.mo23751a(1));
            return;
        }
        throw new IllegalArgumentException("wrong number of elements in sequence");
    }

    /* renamed from: a */
    public static C0374a m23483a(Object obj) {
        if (obj instanceof C0374a) {
            return (C0374a) obj;
        }
        if (obj != null) {
            return new C0374a(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f995v3);
        c0140e.m24170a(this.f996w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0378b0 m23482i() {
        return this.f996w3;
    }

    /* renamed from: j */
    public C0178n m23481j() {
        return this.f995v3;
    }

    public String toString() {
        return "AccessDescription: Oid(" + this.f995v3.m24113n() + ")";
    }

    public C0374a(C0178n c0178n, C0378b0 c0378b0) {
        this.f995v3 = null;
        this.f996w3 = null;
        this.f995v3 = c0178n;
        this.f996w3 = c0378b0;
    }
}
