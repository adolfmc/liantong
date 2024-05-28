package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;

/* renamed from: a.a.a.a.a.e.a.b.x2.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0457c extends AbstractC0174m {

    /* renamed from: v3 */
    public C0359v0 f1492v3;

    /* renamed from: w3 */
    public C0166k f1493w3;

    public C0457c(C0359v0 c0359v0, C0166k c0166k) {
        if (c0359v0 == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        }
        if (c0166k != null) {
            this.f1492v3 = c0359v0;
            this.f1493w3 = c0166k;
            return;
        }
        throw new IllegalArgumentException("'pgenCounter' cannot be null");
    }

    /* renamed from: a */
    public static C0457c m23052a(AbstractC0494y abstractC0494y, boolean z) {
        return m23051a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1492v3);
        c0140e.m24170a(this.f1493w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0166k m23050i() {
        return this.f1493w3;
    }

    /* renamed from: j */
    public C0359v0 m23049j() {
        return this.f1492v3;
    }

    /* renamed from: a */
    public static C0457c m23051a(Object obj) {
        if (obj != null && !(obj instanceof C0455a)) {
            if (obj instanceof AbstractC0263s) {
                return new C0457c((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid DHValidationParms: " + obj.getClass().getName());
        }
        return (C0457c) obj;
    }

    public C0457c(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            this.f1492v3 = C0359v0.m23557a(abstractC0263s.mo23751a(0));
            this.f1493w3 = C0151g1.m24147a(abstractC0263s.mo23751a(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
