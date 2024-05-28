package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ij */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11466ij extends AbstractC11467ik {

    /* renamed from: a */
    private int f23339a;

    /* renamed from: a */
    private byte[] f23340a;

    /* renamed from: b */
    private int f23341b;

    /* renamed from: a */
    public void m2986a(byte[] bArr) {
        m2985b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public void m2985b(byte[] bArr, int i, int i2) {
        this.f23340a = bArr;
        this.f23339a = i;
        this.f23341b = i + i2;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public int mo2982a(byte[] bArr, int i, int i2) {
        int mo2979b = mo2979b();
        if (i2 > mo2979b) {
            i2 = mo2979b;
        }
        if (i2 > 0) {
            System.arraycopy(this.f23340a, this.f23339a, bArr, i, i2);
            mo2983a(i2);
        }
        return i2;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public void mo2981a(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public byte[] mo2984a() {
        return this.f23340a;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a_ */
    public int mo2980a_() {
        return this.f23339a;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: b */
    public int mo2979b() {
        return this.f23341b - this.f23339a;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public void mo2983a(int i) {
        this.f23339a += i;
    }
}
