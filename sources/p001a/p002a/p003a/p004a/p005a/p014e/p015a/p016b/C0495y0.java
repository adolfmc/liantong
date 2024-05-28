package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.y0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0495y0 extends AbstractC0258r {

    /* renamed from: w3 */
    public static C0148g[] f1609w3 = new C0148g[12];

    /* renamed from: v3 */
    public byte[] f1610v3;

    public C0495y0(int i) {
        this.f1610v3 = BigInteger.valueOf(i).toByteArray();
    }

    /* renamed from: a */
    public static C0148g m23000a(Object obj) {
        if (obj != null && !(obj instanceof C0148g)) {
            if (obj instanceof C0495y0) {
                return new C0148g(((C0495y0) obj).m22998m());
            }
            if (obj instanceof byte[]) {
                try {
                    return (C0148g) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0148g) obj;
    }

    /* renamed from: b */
    public static C0148g m22999b(byte[] bArr) {
        if (bArr.length > 1) {
            return new C0148g(C0669a.m22503a(bArr));
        }
        if (bArr.length != 0) {
            int i = bArr[0] & 255;
            C0148g[] c0148gArr = f1609w3;
            if (i >= c0148gArr.length) {
                return new C0148g(C0669a.m22503a(bArr));
            }
            C0148g c0148g = c0148gArr[i];
            if (c0148g == null) {
                C0148g c0148g2 = new C0148g(C0669a.m22503a(bArr));
                c0148gArr[i] = c0148g2;
                return c0148g2;
            }
            return c0148g;
        }
        throw new IllegalArgumentException("ENUMERATED has zero length");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(this.f1610v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f1610v3.length) + 1 + this.f1610v3.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public BigInteger m22998m() {
        return new BigInteger(this.f1610v3);
    }

    public C0495y0(BigInteger bigInteger) {
        this.f1610v3 = bigInteger.toByteArray();
    }

    public C0495y0(byte[] bArr) {
        this.f1610v3 = bArr;
    }

    /* renamed from: a */
    public static C0495y0 m23001a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0495y0)) {
            return m22999b(((AbstractC0182o) m23004m).mo24088m());
        }
        return m23000a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(10, this.f1610v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0495y0) {
            return C0669a.m22499a(this.f1610v3, ((C0495y0) abstractC0258r).f1610v3);
        }
        return false;
    }
}
