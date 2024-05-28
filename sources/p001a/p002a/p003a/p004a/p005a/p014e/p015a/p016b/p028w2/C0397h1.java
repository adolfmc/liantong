package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0125a0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;

/* renamed from: a.a.a.a.a.e.a.b.w2.h1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0397h1 {

    /* renamed from: a */
    public C0360v1 f1086a = new C0360v1(true, 0, new C0166k(0));

    /* renamed from: b */
    public C0166k f1087b;

    /* renamed from: c */
    public C0377b f1088c;

    /* renamed from: d */
    public C0364d f1089d;

    /* renamed from: e */
    public C0391f1 f1090e;

    /* renamed from: f */
    public C0391f1 f1091f;

    /* renamed from: g */
    public C0364d f1092g;

    /* renamed from: h */
    public C0445y0 f1093h;

    /* renamed from: a */
    public void m23363a(C0166k c0166k) {
        this.f1087b = c0166k;
    }

    /* renamed from: b */
    public void m23355b(C0391f1 c0391f1) {
        this.f1090e = c0391f1;
    }

    /* renamed from: a */
    public void m23361a(C0377b c0377b) {
        this.f1088c = c0377b;
    }

    /* renamed from: b */
    public void m23357b(C0125a0 c0125a0) {
        this.f1090e = new C0391f1(c0125a0);
    }

    /* renamed from: a */
    public void m23359a(C0430s1 c0430s1) {
        this.f1089d = C0364d.m23537a(c0430s1.mo23015d());
    }

    /* renamed from: b */
    public void m23354b(C0430s1 c0430s1) {
        this.f1092g = C0364d.m23537a(c0430s1.mo23015d());
    }

    /* renamed from: a */
    public void m23362a(C0364d c0364d) {
        this.f1089d = c0364d;
    }

    /* renamed from: b */
    public void m23356b(C0364d c0364d) {
        this.f1092g = c0364d;
    }

    /* renamed from: a */
    public void m23360a(C0391f1 c0391f1) {
        this.f1091f = c0391f1;
    }

    /* renamed from: a */
    public void m23364a(C0125a0 c0125a0) {
        this.f1091f = new C0391f1(c0125a0);
    }

    /* renamed from: a */
    public void m23358a(C0445y0 c0445y0) {
        this.f1093h = c0445y0;
    }

    /* renamed from: a */
    public C0376a1 m23365a() {
        if (this.f1087b != null && this.f1088c != null && this.f1089d != null && this.f1090e != null && this.f1091f != null && this.f1092g != null && this.f1093h != null) {
            C0140e c0140e = new C0140e();
            c0140e.m24170a(this.f1087b);
            c0140e.m24170a(this.f1088c);
            c0140e.m24170a(this.f1089d);
            C0140e c0140e2 = new C0140e();
            c0140e2.m24170a(this.f1090e);
            c0140e2.m24170a(this.f1091f);
            c0140e.m24170a(new C0184o1(c0140e2));
            c0140e.m24170a(this.f1092g);
            c0140e.m24170a(this.f1093h);
            return C0376a1.m23474a(new C0184o1(c0140e));
        }
        throw new IllegalStateException("not all mandatory fields set in V1 TBScertificate generator");
    }
}
