package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ii */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11465ii extends AbstractC11467ik {

    /* renamed from: a */
    private int f23337a;

    /* renamed from: a */
    private C11446hs f23338a;

    public C11465ii(int i) {
        this.f23338a = new C11446hs(i);
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public int mo2982a(byte[] bArr, int i, int i2) {
        byte[] m3063a = this.f23338a.m3063a();
        if (i2 > this.f23338a.m3064a() - this.f23337a) {
            i2 = this.f23338a.m3064a() - this.f23337a;
        }
        if (i2 > 0) {
            System.arraycopy(m3063a, this.f23337a, bArr, i, i2);
            this.f23337a += i2;
        }
        return i2;
    }

    @Override // com.xiaomi.push.AbstractC11467ik
    /* renamed from: a */
    public void mo2981a(byte[] bArr, int i, int i2) {
        this.f23338a.write(bArr, i, i2);
    }

    /* renamed from: a */
    public int m2987a() {
        return this.f23338a.size();
    }
}
