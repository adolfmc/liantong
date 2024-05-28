package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.g1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0394g1 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0411m0 f1080v3;

    /* renamed from: w3 */
    public C0434u f1081w3;

    public C0394g1(C0411m0 c0411m0, C0434u c0434u) {
        this.f1080v3 = c0411m0;
        this.f1081w3 = c0434u;
    }

    /* renamed from: a */
    public static C0394g1 m23375a(Object obj) {
        if (obj instanceof C0394g1) {
            return (C0394g1) obj;
        }
        if (obj != null) {
            return new C0394g1(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0411m0 c0411m0 = this.f1080v3;
        if (c0411m0 != null) {
            c0140e.m24170a(c0411m0);
        }
        C0434u c0434u = this.f1081w3;
        if (c0434u != null) {
            c0140e.m24170a(c0434u);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0434u m23374i() {
        return this.f1081w3;
    }

    /* renamed from: j */
    public C0411m0 m23373j() {
        return this.f1080v3;
    }

    public C0394g1(C0411m0 c0411m0, String str) {
        this(c0411m0, new C0434u(str));
    }

    public C0394g1(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            this.f1080v3 = C0411m0.m23263a(abstractC0263s.mo23751a(0));
            this.f1081w3 = C0434u.m23144a(abstractC0263s.mo23751a(1));
        } else if (abstractC0263s.mo23745o() == 1) {
            if (abstractC0263s.mo23751a(0).mo23015d() instanceof AbstractC0263s) {
                this.f1080v3 = C0411m0.m23263a(abstractC0263s.mo23751a(0));
            } else {
                this.f1081w3 = C0434u.m23144a(abstractC0263s.mo23751a(0));
            }
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
        }
    }
}
