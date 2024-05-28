package org.p415a.p427d.p428a;

import org.p415a.p448g.AbstractC12971e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0512f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12636f extends AbstractC12631a {

    /* renamed from: a */
    private int f25673a;

    /* renamed from: b */
    private int f25674b;

    /* renamed from: c */
    private int f25675c;

    /* renamed from: d */
    private int f25676d;

    /* renamed from: e */
    private int f25677e;

    /* renamed from: f */
    private int[] f25678f = new int[80];

    /* renamed from: g */
    private int f25679g;

    public C12636f() {
        mo1484c();
    }

    /* renamed from: a */
    private int m1499a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: b */
    private int m1498b(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: c */
    private int m1497c(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1314a() {
        return 20;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1312a(byte[] bArr, int i) {
        m1527b();
        AbstractC12971e.m400a(this.f25673a, bArr, i);
        AbstractC12971e.m400a(this.f25674b, bArr, i + 4);
        AbstractC12971e.m400a(this.f25675c, bArr, i + 8);
        AbstractC12971e.m400a(this.f25676d, bArr, i + 12);
        AbstractC12971e.m400a(this.f25677e, bArr, i + 16);
        mo1484c();
        return 20;
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: a */
    protected void mo1488a(long j) {
        if (this.f25679g > 14) {
            mo1482d();
        }
        int[] iArr = this.f25678f;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: b */
    protected void mo1485b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f25678f;
        int i5 = this.f25679g;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.f25679g = i6;
        if (i6 == 16) {
            mo1482d();
        }
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: c */
    public void mo1484c() {
        super.mo1484c();
        this.f25673a = 1732584193;
        this.f25674b = -271733879;
        this.f25675c = -1732584194;
        this.f25676d = 271733878;
        this.f25677e = -1009589776;
        this.f25679g = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f25678f;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: d */
    protected void mo1482d() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f25678f;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.f25673a;
        int i4 = this.f25674b;
        int i5 = this.f25675c;
        int i6 = this.f25676d;
        int i7 = this.f25677e;
        int i8 = i6;
        int i9 = 0;
        int i10 = i5;
        int i11 = i4;
        int i12 = i3;
        int i13 = 0;
        while (i13 < 4) {
            int i14 = i9 + 1;
            int m1499a = i7 + ((i12 << 5) | (i12 >>> 27)) + m1499a(i11, i10, i8) + this.f25678f[i9] + C0512f.f1670m;
            int i15 = (i11 >>> 2) | (i11 << 30);
            int i16 = i14 + 1;
            int m1499a2 = i8 + ((m1499a << 5) | (m1499a >>> 27)) + m1499a(i12, i15, i10) + this.f25678f[i14] + C0512f.f1670m;
            int i17 = (i12 >>> 2) | (i12 << 30);
            int i18 = i16 + 1;
            int m1499a3 = i10 + ((m1499a2 << 5) | (m1499a2 >>> 27)) + m1499a(m1499a, i17, i15) + this.f25678f[i16] + C0512f.f1670m;
            i7 = (m1499a >>> 2) | (m1499a << 30);
            int i19 = i18 + 1;
            i11 = i15 + ((m1499a3 << 5) | (m1499a3 >>> 27)) + m1499a(m1499a2, i7, i17) + this.f25678f[i18] + C0512f.f1670m;
            i8 = (m1499a2 >>> 2) | (m1499a2 << 30);
            i12 = i17 + ((i11 << 5) | (i11 >>> 27)) + m1499a(m1499a3, i8, i7) + this.f25678f[i19] + C0512f.f1670m;
            i10 = (m1499a3 >>> 2) | (m1499a3 << 30);
            i13++;
            i9 = i19 + 1;
        }
        int i20 = 0;
        while (i20 < 4) {
            int i21 = i9 + 1;
            int m1498b = i7 + ((i12 << 5) | (i12 >>> 27)) + m1498b(i11, i10, i8) + this.f25678f[i9] + C0512f.f1671n;
            int i22 = (i11 >>> 2) | (i11 << 30);
            int i23 = i21 + 1;
            int m1498b2 = i8 + ((m1498b << 5) | (m1498b >>> 27)) + m1498b(i12, i22, i10) + this.f25678f[i21] + C0512f.f1671n;
            int i24 = (i12 >>> 2) | (i12 << 30);
            int i25 = i23 + 1;
            int m1498b3 = i10 + ((m1498b2 << 5) | (m1498b2 >>> 27)) + m1498b(m1498b, i24, i22) + this.f25678f[i23] + C0512f.f1671n;
            i7 = (m1498b >>> 2) | (m1498b << 30);
            int i26 = i25 + 1;
            i11 = i22 + ((m1498b3 << 5) | (m1498b3 >>> 27)) + m1498b(m1498b2, i7, i24) + this.f25678f[i25] + C0512f.f1671n;
            i8 = (m1498b2 >>> 2) | (m1498b2 << 30);
            i12 = i24 + ((i11 << 5) | (i11 >>> 27)) + m1498b(m1498b3, i8, i7) + this.f25678f[i26] + C0512f.f1671n;
            i10 = (m1498b3 >>> 2) | (m1498b3 << 30);
            i20++;
            i9 = i26 + 1;
        }
        int i27 = 0;
        while (i27 < 4) {
            int i28 = i9 + 1;
            int m1497c = i7 + ((i12 << 5) | (i12 >>> 27)) + m1497c(i11, i10, i8) + this.f25678f[i9] + C0512f.f1672o;
            int i29 = (i11 >>> 2) | (i11 << 30);
            int i30 = i28 + 1;
            int m1497c2 = i8 + ((m1497c << 5) | (m1497c >>> 27)) + m1497c(i12, i29, i10) + this.f25678f[i28] + C0512f.f1672o;
            int i31 = (i12 >>> 2) | (i12 << 30);
            int i32 = i30 + 1;
            int m1497c3 = i10 + ((m1497c2 << 5) | (m1497c2 >>> 27)) + m1497c(m1497c, i31, i29) + this.f25678f[i30] + C0512f.f1672o;
            i7 = (m1497c >>> 2) | (m1497c << 30);
            int i33 = i32 + 1;
            i11 = i29 + ((m1497c3 << 5) | (m1497c3 >>> 27)) + m1497c(m1497c2, i7, i31) + this.f25678f[i32] + C0512f.f1672o;
            i8 = (m1497c2 >>> 2) | (m1497c2 << 30);
            i12 = i31 + ((i11 << 5) | (i11 >>> 27)) + m1497c(m1497c3, i8, i7) + this.f25678f[i33] + C0512f.f1672o;
            i10 = (m1497c3 >>> 2) | (m1497c3 << 30);
            i27++;
            i9 = i33 + 1;
        }
        int i34 = 0;
        while (i34 <= 3) {
            int i35 = i9 + 1;
            int m1498b4 = i7 + ((i12 << 5) | (i12 >>> 27)) + m1498b(i11, i10, i8) + this.f25678f[i9] + C0512f.f1673p;
            int i36 = (i11 >>> 2) | (i11 << 30);
            int i37 = i35 + 1;
            int m1498b5 = i8 + ((m1498b4 << 5) | (m1498b4 >>> 27)) + m1498b(i12, i36, i10) + this.f25678f[i35] + C0512f.f1673p;
            int i38 = (i12 >>> 2) | (i12 << 30);
            int i39 = i37 + 1;
            int m1498b6 = i10 + ((m1498b5 << 5) | (m1498b5 >>> 27)) + m1498b(m1498b4, i38, i36) + this.f25678f[i37] + C0512f.f1673p;
            i7 = (m1498b4 >>> 2) | (m1498b4 << 30);
            int i40 = i39 + 1;
            i11 = i36 + ((m1498b6 << 5) | (m1498b6 >>> 27)) + m1498b(m1498b5, i7, i38) + this.f25678f[i39] + C0512f.f1673p;
            i8 = (m1498b5 >>> 2) | (m1498b5 << 30);
            i12 = i38 + ((i11 << 5) | (i11 >>> 27)) + m1498b(m1498b6, i8, i7) + this.f25678f[i40] + C0512f.f1673p;
            i10 = (m1498b6 >>> 2) | (m1498b6 << 30);
            i34++;
            i9 = i40 + 1;
        }
        this.f25673a += i12;
        this.f25674b += i11;
        this.f25675c += i10;
        this.f25676d += i8;
        this.f25677e += i7;
        this.f25679g = 0;
        for (int i41 = 0; i41 < 16; i41++) {
            this.f25678f[i41] = 0;
        }
    }
}
