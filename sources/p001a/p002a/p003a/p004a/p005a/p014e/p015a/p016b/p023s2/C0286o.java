package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.o */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0286o extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f621v3;

    /* renamed from: w3 */
    public AbstractC0182o f622w3;

    public C0286o(byte[] bArr, int i) {
        if (bArr.length == 8) {
            this.f622w3 = new C0168k1(bArr);
            this.f621v3 = new C0166k(i);
            return;
        }
        throw new IllegalArgumentException("salt length must be 8");
    }

    /* renamed from: a */
    public static C0286o m23667a(Object obj) {
        if (obj instanceof C0286o) {
            return (C0286o) obj;
        }
        if (obj != null) {
            return new C0286o(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f622w3);
        c0140e.m24170a(this.f621v3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23666i() {
        return this.f621v3.m24145n();
    }

    /* renamed from: j */
    public byte[] m23665j() {
        return this.f622w3.mo24088m();
    }

    public C0286o(AbstractC0263s abstractC0263s) {
        this.f622w3 = (AbstractC0182o) abstractC0263s.mo23751a(0);
        this.f621v3 = (C0166k) abstractC0263s.mo23751a(1);
    }
}
