package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0512f;

/* renamed from: a.a.a.a.a.e.a.b.w2.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0398i extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0182o f1094v3;

    /* renamed from: w3 */
    public C0381c0 f1095w3;

    /* renamed from: x3 */
    public C0166k f1096x3;

    public C0398i(AbstractC0263s abstractC0263s) {
        this.f1094v3 = null;
        this.f1095w3 = null;
        this.f1096x3 = null;
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            AbstractC0494y m23008a = AbstractC0494y.m23008a(mo23747m.nextElement());
            int mo22994f = m23008a.mo22994f();
            if (mo22994f == 0) {
                this.f1094v3 = AbstractC0182o.m24090a(m23008a, false);
            } else if (mo22994f == 1) {
                this.f1095w3 = C0381c0.m23428a(m23008a, false);
            } else if (mo22994f == 2) {
                this.f1096x3 = C0151g1.m24148a(m23008a, false);
            } else {
                throw new IllegalArgumentException("illegal tag");
            }
        }
    }

    /* renamed from: a */
    public static C0398i m23352a(AbstractC0494y abstractC0494y, boolean z) {
        return m23351a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        AbstractC0182o abstractC0182o = this.f1094v3;
        if (abstractC0182o != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0182o));
        }
        C0381c0 c0381c0 = this.f1095w3;
        if (c0381c0 != null) {
            c0140e.m24170a(new C0360v1(false, 1, c0381c0));
        }
        C0166k c0166k = this.f1096x3;
        if (c0166k != null) {
            c0140e.m24170a(new C0360v1(false, 2, c0166k));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0381c0 m23350i() {
        return this.f1095w3;
    }

    /* renamed from: j */
    public BigInteger m23349j() {
        C0166k c0166k = this.f1096x3;
        if (c0166k != null) {
            return c0166k.m24145n();
        }
        return null;
    }

    /* renamed from: k */
    public byte[] m23348k() {
        AbstractC0182o abstractC0182o = this.f1094v3;
        if (abstractC0182o != null) {
            return abstractC0182o.mo24088m();
        }
        return null;
    }

    public String toString() {
        return "AuthorityKeyIdentifier: KeyID(" + this.f1094v3.mo24088m() + ")";
    }

    /* renamed from: a */
    public static C0398i m23351a(Object obj) {
        if (obj instanceof C0398i) {
            return (C0398i) obj;
        }
        if (obj != null) {
            return new C0398i(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    /* renamed from: a */
    public static C0398i m23353a(C0446z c0446z) {
        return m23351a(c0446z.m23090b(C0444y.f1451Q3));
    }

    public C0398i(C0445y0 c0445y0) {
        this.f1094v3 = null;
        this.f1095w3 = null;
        this.f1096x3 = null;
        C0512f c0512f = new C0512f();
        byte[] bArr = new byte[c0512f.mo22743c()];
        byte[] m23554m = c0445y0.m23098l().m23554m();
        c0512f.mo22745a(m23554m, 0, m23554m.length);
        c0512f.mo22746a(bArr, 0);
        this.f1094v3 = new C0168k1(bArr);
    }

    public C0398i(C0445y0 c0445y0, C0381c0 c0381c0, BigInteger bigInteger) {
        this.f1094v3 = null;
        this.f1095w3 = null;
        this.f1096x3 = null;
        C0512f c0512f = new C0512f();
        byte[] bArr = new byte[c0512f.mo22743c()];
        byte[] m23554m = c0445y0.m23098l().m23554m();
        c0512f.mo22745a(m23554m, 0, m23554m.length);
        c0512f.mo22746a(bArr, 0);
        this.f1094v3 = new C0168k1(bArr);
        this.f1095w3 = C0381c0.m23427a(c0381c0.mo23015d());
        this.f1096x3 = new C0166k(bigInteger);
    }

    public C0398i(C0381c0 c0381c0, BigInteger bigInteger) {
        this.f1094v3 = null;
        this.f1095w3 = null;
        this.f1096x3 = null;
        this.f1094v3 = null;
        this.f1095w3 = C0381c0.m23427a(c0381c0.mo23015d());
        this.f1096x3 = new C0166k(bigInteger);
    }

    public C0398i(byte[] bArr) {
        this.f1094v3 = null;
        this.f1095w3 = null;
        this.f1096x3 = null;
        this.f1094v3 = new C0168k1(bArr);
        this.f1095w3 = null;
        this.f1096x3 = null;
    }

    public C0398i(byte[] bArr, C0381c0 c0381c0, BigInteger bigInteger) {
        this.f1094v3 = null;
        this.f1095w3 = null;
        this.f1096x3 = null;
        this.f1094v3 = new C0168k1(bArr);
        this.f1095w3 = C0381c0.m23427a(c0381c0.mo23015d());
        this.f1096x3 = new C0166k(bigInteger);
    }
}
