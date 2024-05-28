package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.u0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0340u0 extends AbstractC0258r implements InterfaceC0452x {

    /* renamed from: v3 */
    public char[] f880v3;

    public C0340u0(byte[] bArr) {
        int length = bArr.length / 2;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            int i2 = i * 2;
            cArr[i] = (char) ((bArr[i2 + 1] & 255) | (bArr[i2] << 8));
        }
        this.f880v3 = cArr;
    }

    /* renamed from: a */
    public static C0340u0 m23572a(Object obj) {
        if (obj != null && !(obj instanceof C0340u0)) {
            if (obj instanceof byte[]) {
                try {
                    return (C0340u0) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0340u0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x
    /* renamed from: e */
    public String mo22978e() {
        return new String(this.f880v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22496a(this.f880v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f880v3.length * 2) + 1 + (this.f880v3.length * 2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    public String toString() {
        return mo22978e();
    }

    public C0340u0(char[] cArr) {
        this.f880v3 = cArr;
    }

    public C0340u0(String str) {
        this.f880v3 = str.toCharArray();
    }

    /* renamed from: a */
    public static C0340u0 m23573a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0340u0)) {
            return new C0340u0(AbstractC0182o.m24089a((Object) m23004m).mo24088m());
        }
        return m23572a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0340u0) {
            return C0669a.m22493a(this.f880v3, ((C0340u0) abstractC0258r).f880v3);
        }
        return false;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.mo23763a(30);
        c0252q.m23767b(this.f880v3.length * 2);
        int i = 0;
        while (true) {
            char[] cArr = this.f880v3;
            if (i == cArr.length) {
                return;
            }
            char c = cArr[i];
            c0252q.mo23763a((byte) (c >> '\b'));
            c0252q.mo23763a((byte) c);
            i++;
        }
    }
}
