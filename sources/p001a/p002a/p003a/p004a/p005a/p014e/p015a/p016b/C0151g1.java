package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.g1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0151g1 extends AbstractC0258r {

    /* renamed from: v3 */
    public byte[] f184v3;

    public C0151g1(long j) {
        this.f184v3 = BigInteger.valueOf(j).toByteArray();
    }

    /* renamed from: a */
    public static C0166k m24147a(Object obj) {
        if (obj != null && !(obj instanceof C0166k)) {
            if (obj instanceof C0151g1) {
                return new C0166k(((C0151g1) obj).m24145n());
            }
            if (obj instanceof byte[]) {
                try {
                    return (C0166k) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0166k) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f184v3;
            if (i == bArr.length) {
                return i2;
            }
            i2 ^= (bArr[i] & 255) << (i % 4);
            i++;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f184v3.length) + 1 + this.f184v3.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public BigInteger m24146m() {
        return new BigInteger(1, this.f184v3);
    }

    /* renamed from: n */
    public BigInteger m24145n() {
        return new BigInteger(this.f184v3);
    }

    public String toString() {
        return m24145n().toString();
    }

    public C0151g1(BigInteger bigInteger) {
        this.f184v3 = bigInteger.toByteArray();
    }

    public C0151g1(byte[] bArr) {
        this.f184v3 = bArr;
    }

    /* renamed from: a */
    public static C0166k m24148a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0151g1)) {
            return new C0166k(AbstractC0182o.m24089a((Object) abstractC0494y.m23004m()).mo24088m());
        }
        return m24147a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(2, this.f184v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0151g1) {
            return C0669a.m22499a(this.f184v3, ((C0151g1) abstractC0258r).f184v3);
        }
        return false;
    }
}
