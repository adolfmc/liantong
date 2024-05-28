package com.baidu.p122b.p125c.p126a;

import java.security.InvalidKeyException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2397d extends AbstractC2399f {

    /* renamed from: a */
    protected byte[] f4210a;

    /* renamed from: e */
    private byte[] f4211e;

    /* renamed from: f */
    private byte[] f4212f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2397d(C2395b c2395b) {
        super(c2395b);
        this.f4212f = null;
        this.f4211e = new byte[this.f4224c];
        this.f4210a = new byte[this.f4224c];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p122b.p125c.p126a.AbstractC2399f
    /* renamed from: a */
    public void mo20273a() {
        System.arraycopy(this.f4225d, 0, this.f4210a, 0, this.f4224c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p122b.p125c.p126a.AbstractC2399f
    /* renamed from: a */
    public void mo20272a(boolean z, String str, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr2.length != this.f4224c) {
            throw new InvalidKeyException("Internal error");
        }
        this.f4225d = bArr2;
        mo20273a();
        this.f4223b.m20286a(z, str, bArr);
    }

    @Override // com.baidu.p122b.p125c.p126a.AbstractC2399f
    /* renamed from: a */
    void mo20271a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2 + i;
        while (i < i4) {
            for (int i5 = 0; i5 < this.f4224c; i5++) {
                this.f4211e[i5] = (byte) (bArr[i5 + i] ^ this.f4210a[i5]);
            }
            this.f4223b.m20284a(this.f4211e, 0, bArr2, i3);
            System.arraycopy(bArr2, i3, this.f4210a, 0, this.f4224c);
            i += this.f4224c;
            i3 += this.f4224c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p122b.p125c.p126a.AbstractC2399f
    /* renamed from: b */
    public void mo20270b() {
        if (this.f4212f == null) {
            this.f4212f = new byte[this.f4224c];
        }
        System.arraycopy(this.f4210a, 0, this.f4212f, 0, this.f4224c);
    }

    @Override // com.baidu.p122b.p125c.p126a.AbstractC2399f
    /* renamed from: b */
    void mo20269b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2 + i;
        byte[] bArr3 = (bArr != bArr2 || i < i3 || i - i3 >= this.f4224c) ? null : (byte[]) bArr.clone();
        while (i < i4) {
            this.f4223b.m20282b(bArr, i, this.f4211e, 0);
            for (int i5 = 0; i5 < this.f4224c; i5++) {
                bArr2[i5 + i3] = (byte) (this.f4211e[i5] ^ this.f4210a[i5]);
            }
            if (bArr3 == null) {
                System.arraycopy(bArr, i, this.f4210a, 0, this.f4224c);
            } else {
                System.arraycopy(bArr3, i, this.f4210a, 0, this.f4224c);
            }
            i += this.f4224c;
            i3 += this.f4224c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.p122b.p125c.p126a.AbstractC2399f
    /* renamed from: c */
    public void mo20268c() {
        System.arraycopy(this.f4212f, 0, this.f4210a, 0, this.f4224c);
    }
}
