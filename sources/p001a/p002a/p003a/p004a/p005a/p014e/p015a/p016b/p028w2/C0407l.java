package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;

/* renamed from: a.a.a.a.a.e.a.b.w2.l */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0407l extends AbstractC0174m {

    /* renamed from: v3 */
    public BigInteger f1161v3;

    public C0407l(BigInteger bigInteger) {
        this.f1161v3 = bigInteger;
    }

    /* renamed from: a */
    public static C0407l m23288a(Object obj) {
        if (obj instanceof C0407l) {
            return (C0407l) obj;
        }
        if (obj != null) {
            return new C0407l(C0151g1.m24147a(obj).m24145n());
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0166k(this.f1161v3);
    }

    /* renamed from: i */
    public BigInteger m23287i() {
        return this.f1161v3;
    }

    public String toString() {
        return "CRLNumber: " + m23287i();
    }
}
