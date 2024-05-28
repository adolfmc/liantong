package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.f0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0390f0 extends AbstractC0174m {

    /* renamed from: A3 */
    public static final int f1058A3 = 1;

    /* renamed from: z3 */
    public static final int f1059z3 = 0;

    /* renamed from: v3 */
    public C0396h0 f1060v3;

    /* renamed from: w3 */
    public C0381c0 f1061w3;

    /* renamed from: x3 */
    public C0414n0 f1062x3;

    /* renamed from: y3 */
    public int f1063y3;

    public C0390f0(AbstractC0494y abstractC0494y) {
        this.f1063y3 = 1;
        int mo22994f = abstractC0494y.mo22994f();
        if (mo22994f == 0) {
            this.f1060v3 = C0396h0.m23370a(abstractC0494y, false);
        } else if (mo22994f == 1) {
            this.f1061w3 = C0381c0.m23428a(abstractC0494y, false);
        } else {
            throw new IllegalArgumentException("unknown tag in Holder");
        }
        this.f1063y3 = 0;
    }

    /* renamed from: a */
    public static C0390f0 m23399a(Object obj) {
        if (obj instanceof C0390f0) {
            return (C0390f0) obj;
        }
        if (obj instanceof AbstractC0494y) {
            return new C0390f0(AbstractC0494y.m23008a(obj));
        }
        if (obj != null) {
            return new C0390f0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        if (this.f1063y3 == 1) {
            C0140e c0140e = new C0140e();
            C0396h0 c0396h0 = this.f1060v3;
            if (c0396h0 != null) {
                c0140e.m24170a(new C0360v1(false, 0, c0396h0));
            }
            C0381c0 c0381c0 = this.f1061w3;
            if (c0381c0 != null) {
                c0140e.m24170a(new C0360v1(false, 1, c0381c0));
            }
            C0414n0 c0414n0 = this.f1062x3;
            if (c0414n0 != null) {
                c0140e.m24170a(new C0360v1(false, 2, c0414n0));
            }
            return new C0184o1(c0140e);
        }
        C0381c0 c0381c02 = this.f1061w3;
        if (c0381c02 != null) {
            return new C0360v1(false, 1, c0381c02);
        }
        return new C0360v1(false, 0, this.f1060v3);
    }

    /* renamed from: i */
    public C0396h0 m23398i() {
        return this.f1060v3;
    }

    /* renamed from: j */
    public C0381c0 m23397j() {
        return this.f1061w3;
    }

    /* renamed from: k */
    public C0414n0 m23396k() {
        return this.f1062x3;
    }

    /* renamed from: l */
    public int m23395l() {
        return this.f1063y3;
    }

    public C0390f0(AbstractC0263s abstractC0263s) {
        this.f1063y3 = 1;
        if (abstractC0263s.mo23745o() <= 3) {
            for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
                AbstractC0494y m23008a = AbstractC0494y.m23008a(abstractC0263s.mo23751a(i));
                int mo22994f = m23008a.mo22994f();
                if (mo22994f == 0) {
                    this.f1060v3 = C0396h0.m23370a(m23008a, false);
                } else if (mo22994f == 1) {
                    this.f1061w3 = C0381c0.m23428a(m23008a, false);
                } else if (mo22994f == 2) {
                    this.f1062x3 = C0414n0.m23257a(m23008a, false);
                } else {
                    throw new IllegalArgumentException("unknown tag in Holder");
                }
            }
            this.f1063y3 = 1;
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    public C0390f0(C0396h0 c0396h0) {
        this(c0396h0, 1);
    }

    public C0390f0(C0396h0 c0396h0, int i) {
        this.f1063y3 = 1;
        this.f1060v3 = c0396h0;
        this.f1063y3 = i;
    }

    public C0390f0(C0381c0 c0381c0) {
        this(c0381c0, 1);
    }

    public C0390f0(C0381c0 c0381c0, int i) {
        this.f1063y3 = 1;
        this.f1061w3 = c0381c0;
        this.f1063y3 = i;
    }

    public C0390f0(C0414n0 c0414n0) {
        this.f1063y3 = 1;
        this.f1062x3 = c0414n0;
    }
}
