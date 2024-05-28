package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0447z0;

/* renamed from: a.a.a.a.a.e.a.b.w2.p */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0419p extends AbstractC0174m {

    /* renamed from: v3 */
    public C0447z0 f1232v3;

    /* renamed from: w3 */
    public C0377b f1233w3;

    /* renamed from: x3 */
    public C0359v0 f1234x3;

    public C0419p(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 3) {
            this.f1232v3 = C0447z0.m23084a(abstractC0263s.mo23751a(0));
            this.f1233w3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
            this.f1234x3 = C0359v0.m23557a(abstractC0263s.mo23751a(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for CertificateList");
    }

    /* renamed from: a */
    public static C0419p m23223a(AbstractC0494y abstractC0494y, boolean z) {
        return m23222a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1232v3);
        c0140e.m24170a(this.f1233w3);
        c0140e.m24170a(this.f1234x3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0364d m23221i() {
        return this.f1232v3.m23082j();
    }

    /* renamed from: j */
    public C0391f1 m23220j() {
        return this.f1232v3.m23081k();
    }

    /* renamed from: k */
    public Enumeration m23219k() {
        return this.f1232v3.m23080l();
    }

    /* renamed from: l */
    public C0447z0.C0449b[] m23218l() {
        return this.f1232v3.m23079m();
    }

    /* renamed from: m */
    public C0359v0 m23217m() {
        return this.f1234x3;
    }

    /* renamed from: n */
    public C0377b m23216n() {
        return this.f1233w3;
    }

    /* renamed from: o */
    public C0447z0 m23215o() {
        return this.f1232v3;
    }

    /* renamed from: p */
    public C0391f1 m23214p() {
        return this.f1232v3.m23077o();
    }

    /* renamed from: q */
    public int m23213q() {
        return this.f1232v3.m23075q();
    }

    /* renamed from: a */
    public static C0419p m23222a(Object obj) {
        if (obj instanceof C0419p) {
            return (C0419p) obj;
        }
        if (obj != null) {
            return new C0419p(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
