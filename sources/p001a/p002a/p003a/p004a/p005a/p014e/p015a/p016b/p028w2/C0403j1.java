package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.j1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0403j1 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0381c0 f1139v3;

    /* renamed from: w3 */
    public C0396h0 f1140w3;

    /* renamed from: x3 */
    public C0414n0 f1141x3;

    public C0403j1(C0381c0 c0381c0) {
        this(c0381c0, null, null);
    }

    /* renamed from: a */
    public static C0403j1 m23318a(AbstractC0494y abstractC0494y, boolean z) {
        return m23317a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0381c0 c0381c0 = this.f1139v3;
        if (c0381c0 != null) {
            c0140e.m24170a(c0381c0);
        }
        C0396h0 c0396h0 = this.f1140w3;
        if (c0396h0 != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0396h0));
        }
        C0414n0 c0414n0 = this.f1141x3;
        if (c0414n0 != null) {
            c0140e.m24170a(new C0360v1(false, 1, c0414n0));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0396h0 m23316i() {
        return this.f1140w3;
    }

    /* renamed from: j */
    public C0381c0 m23315j() {
        return this.f1139v3;
    }

    /* renamed from: k */
    public C0414n0 m23314k() {
        return this.f1141x3;
    }

    public C0403j1(C0381c0 c0381c0, C0396h0 c0396h0) {
        this(c0381c0, c0396h0, null);
    }

    /* renamed from: a */
    public static C0403j1 m23317a(Object obj) {
        if (obj instanceof C0403j1) {
            return (C0403j1) obj;
        }
        if (obj != null) {
            return new C0403j1(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0403j1(C0381c0 c0381c0, C0414n0 c0414n0) {
        this(c0381c0, null, c0414n0);
    }

    public C0403j1(C0381c0 c0381c0, C0396h0 c0396h0, C0414n0 c0414n0) {
        this.f1139v3 = c0381c0;
        this.f1140w3 = c0396h0;
        this.f1141x3 = c0414n0;
    }

    public C0403j1(AbstractC0263s abstractC0263s) {
        int i;
        if (abstractC0263s.mo23745o() <= 3) {
            if (abstractC0263s.mo23751a(0) instanceof AbstractC0494y) {
                i = 0;
            } else {
                this.f1139v3 = C0381c0.m23427a(abstractC0263s.mo23751a(0));
                i = 1;
            }
            while (i != abstractC0263s.mo23745o()) {
                AbstractC0494y m23008a = AbstractC0494y.m23008a(abstractC0263s.mo23751a(i));
                if (m23008a.mo22994f() == 0) {
                    this.f1140w3 = C0396h0.m23370a(m23008a, false);
                } else if (m23008a.mo22994f() == 1) {
                    this.f1141x3 = C0414n0.m23257a(m23008a, false);
                } else {
                    throw new IllegalArgumentException("Bad tag number: " + m23008a.mo22994f());
                }
                i++;
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
