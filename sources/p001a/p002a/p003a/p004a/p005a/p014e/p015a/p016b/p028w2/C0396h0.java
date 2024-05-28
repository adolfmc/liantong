package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;

/* renamed from: a.a.a.a.a.e.a.b.w2.h0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0396h0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0381c0 f1083v3;

    /* renamed from: w3 */
    public C0166k f1084w3;

    /* renamed from: x3 */
    public C0359v0 f1085x3;

    public C0396h0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() != 2 && abstractC0263s.mo23745o() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
        }
        this.f1083v3 = C0381c0.m23427a(abstractC0263s.mo23751a(0));
        this.f1084w3 = C0151g1.m24147a(abstractC0263s.mo23751a(1));
        if (abstractC0263s.mo23745o() == 3) {
            this.f1085x3 = C0359v0.m23557a(abstractC0263s.mo23751a(2));
        }
    }

    /* renamed from: a */
    public static C0396h0 m23369a(Object obj) {
        if (obj instanceof C0396h0) {
            return (C0396h0) obj;
        }
        if (obj != null) {
            return new C0396h0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1083v3);
        c0140e.m24170a(this.f1084w3);
        C0359v0 c0359v0 = this.f1085x3;
        if (c0359v0 != null) {
            c0140e.m24170a(c0359v0);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0381c0 m23368i() {
        return this.f1083v3;
    }

    /* renamed from: j */
    public C0359v0 m23367j() {
        return this.f1085x3;
    }

    /* renamed from: k */
    public C0166k m23366k() {
        return this.f1084w3;
    }

    /* renamed from: a */
    public static C0396h0 m23370a(AbstractC0494y abstractC0494y, boolean z) {
        return m23369a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    public C0396h0(C0381c0 c0381c0, BigInteger bigInteger) {
        this(c0381c0, new C0166k(bigInteger));
    }

    public C0396h0(C0381c0 c0381c0, C0166k c0166k) {
        this.f1083v3 = c0381c0;
        this.f1084w3 = c0166k;
    }
}
