package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0153h;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0260r1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.i1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0400i1 {

    /* renamed from: b */
    public C0390f0 f1105b;

    /* renamed from: c */
    public C0380c f1106c;

    /* renamed from: d */
    public C0377b f1107d;

    /* renamed from: e */
    public C0166k f1108e;

    /* renamed from: g */
    public C0359v0 f1110g;

    /* renamed from: h */
    public C0446z f1111h;

    /* renamed from: i */
    public C0153h f1112i;

    /* renamed from: j */
    public C0153h f1113j;

    /* renamed from: a */
    public C0166k f1104a = new C0166k(1);

    /* renamed from: f */
    public C0140e f1109f = new C0140e();

    /* renamed from: a */
    public void m23330a(C0390f0 c0390f0) {
        this.f1105b = c0390f0;
    }

    /* renamed from: b */
    public void m23326b(C0153h c0153h) {
        this.f1112i = c0153h;
    }

    /* renamed from: a */
    public void m23327a(String str, InterfaceC0136d interfaceC0136d) {
        this.f1109f.m24170a(new C0386e(new C0178n(str), new C0260r1(interfaceC0136d)));
    }

    /* renamed from: a */
    public void m23331a(C0386e c0386e) {
        this.f1109f.m24170a(c0386e);
    }

    /* renamed from: a */
    public void m23335a(C0166k c0166k) {
        this.f1108e = c0166k;
    }

    /* renamed from: a */
    public void m23333a(C0377b c0377b) {
        this.f1107d = c0377b;
    }

    /* renamed from: a */
    public void m23332a(C0380c c0380c) {
        this.f1106c = c0380c;
    }

    /* renamed from: a */
    public void m23336a(C0153h c0153h) {
        this.f1113j = c0153h;
    }

    /* renamed from: a */
    public void m23334a(C0359v0 c0359v0) {
        this.f1110g = c0359v0;
    }

    /* renamed from: a */
    public void m23329a(C0424q1 c0424q1) {
        this.f1111h = C0446z.m23093a(c0424q1.mo23015d());
    }

    /* renamed from: a */
    public void m23328a(C0446z c0446z) {
        this.f1111h = c0446z;
    }

    /* renamed from: a */
    public C0392g m23337a() {
        if (this.f1108e != null && this.f1107d != null && this.f1106c != null && this.f1112i != null && this.f1113j != null && this.f1105b != null && this.f1109f != null) {
            C0140e c0140e = new C0140e();
            c0140e.m24170a(this.f1104a);
            c0140e.m24170a(this.f1105b);
            c0140e.m24170a(this.f1106c);
            c0140e.m24170a(this.f1107d);
            c0140e.m24170a(this.f1108e);
            c0140e.m24170a(new C0383d(this.f1112i, this.f1113j));
            c0140e.m24170a(new C0184o1(this.f1109f));
            C0359v0 c0359v0 = this.f1110g;
            if (c0359v0 != null) {
                c0140e.m24170a(c0359v0);
            }
            C0446z c0446z = this.f1111h;
            if (c0446z != null) {
                c0140e.m24170a(c0446z);
            }
            return C0392g.m23389a(new C0184o1(c0140e));
        }
        throw new IllegalStateException("not all mandatory fields set in V2 AttributeCertificateInfo generator");
    }
}
