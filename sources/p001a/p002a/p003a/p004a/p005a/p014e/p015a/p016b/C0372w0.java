package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.w0 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0372w0 extends AbstractC0258r {

    /* renamed from: w3 */
    public static final byte[] f988w3 = {-1};

    /* renamed from: x3 */
    public static final byte[] f989x3 = {0};

    /* renamed from: y3 */
    public static final C0128b f990y3 = new C0128b(false);

    /* renamed from: z3 */
    public static final C0128b f991z3 = new C0128b(true);

    /* renamed from: v3 */
    public byte[] f992v3;

    public C0372w0(byte[] bArr) {
        if (bArr.length == 1) {
            if (bArr[0] == 0) {
                this.f992v3 = f989x3;
                return;
            } else if (bArr[0] == 255) {
                this.f992v3 = f988w3;
                return;
            } else {
                this.f992v3 = C0669a.m22503a(bArr);
                return;
            }
        }
        throw new IllegalArgumentException("byte value should have 1 byte in it");
    }

    /* renamed from: a */
    public static C0128b m23487a(Object obj) {
        if (obj != null && !(obj instanceof C0128b)) {
            if (obj instanceof C0372w0) {
                return ((C0372w0) obj).m23484m() ? f991z3 : f990y3;
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0128b) obj;
    }

    /* renamed from: b */
    public static C0128b m23485b(byte[] bArr) {
        if (bArr.length == 1) {
            if (bArr[0] == 0) {
                return f990y3;
            }
            if (bArr[0] == 255) {
                return f991z3;
            }
            return new C0128b(bArr);
        }
        throw new IllegalArgumentException("byte value should have 1 byte in it");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return this.f992v3[0];
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return 3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public boolean m23484m() {
        return this.f992v3[0] != 0;
    }

    public String toString() {
        return this.f992v3[0] != 0 ? "TRUE" : "FALSE";
    }

    /* renamed from: a */
    public static C0128b m23486a(boolean z) {
        return z ? f991z3 : f990y3;
    }

    /* renamed from: a */
    public static C0128b m23489a(int i) {
        return i != 0 ? f991z3 : f990y3;
    }

    /* renamed from: a */
    public static C0128b m23488a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0372w0)) {
            return m23485b(((AbstractC0182o) m23004m).mo24088m());
        }
        return m23487a((Object) m23004m);
    }

    public C0372w0(boolean z) {
        this.f992v3 = z ? f988w3 : f989x3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(1, this.f992v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        return abstractC0258r != null && (abstractC0258r instanceof C0372w0) && this.f992v3[0] == ((C0372w0) abstractC0258r).f992v3[0];
    }
}
