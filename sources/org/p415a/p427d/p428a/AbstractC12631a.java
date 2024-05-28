package org.p415a.p427d.p428a;

import org.p415a.p427d.InterfaceC12726k;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12631a implements InterfaceC12726k {

    /* renamed from: a */
    private final byte[] f25635a = new byte[4];

    /* renamed from: b */
    private int f25636b = 0;

    /* renamed from: c */
    private long f25637c;

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public void mo1313a(byte b) {
        byte[] bArr = this.f25635a;
        int i = this.f25636b;
        this.f25636b = i + 1;
        bArr[i] = b;
        if (this.f25636b == bArr.length) {
            mo1485b(bArr, 0);
            this.f25636b = 0;
        }
        this.f25637c++;
    }

    /* renamed from: a */
    protected abstract void mo1488a(long j);

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public void mo1311a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int max = Math.max(0, i2);
        if (this.f25636b != 0) {
            int i4 = 0;
            while (true) {
                if (i4 >= max) {
                    i3 = i4;
                    break;
                }
                byte[] bArr2 = this.f25635a;
                int i5 = this.f25636b;
                this.f25636b = i5 + 1;
                int i6 = i4 + 1;
                bArr2[i5] = bArr[i4 + i];
                if (this.f25636b == 4) {
                    mo1485b(bArr2, 0);
                    this.f25636b = 0;
                    i3 = i6;
                    break;
                }
                i4 = i6;
            }
        }
        int i7 = ((max - i3) & (-4)) + i3;
        while (i3 < i7) {
            mo1485b(bArr, i + i3);
            i3 += 4;
        }
        while (i3 < max) {
            byte[] bArr3 = this.f25635a;
            int i8 = this.f25636b;
            this.f25636b = i8 + 1;
            bArr3[i8] = bArr[i3 + i];
            i3++;
        }
        this.f25637c += max;
    }

    /* renamed from: b */
    public void m1527b() {
        long j = this.f25637c << 3;
        byte b = Byte.MIN_VALUE;
        while (true) {
            mo1313a(b);
            if (this.f25636b == 0) {
                mo1488a(j);
                mo1482d();
                return;
            }
            b = 0;
        }
    }

    /* renamed from: b */
    protected abstract void mo1485b(byte[] bArr, int i);

    /* renamed from: c */
    public void mo1484c() {
        this.f25637c = 0L;
        this.f25636b = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f25635a;
            if (i >= bArr.length) {
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }

    /* renamed from: d */
    protected abstract void mo1482d();
}
