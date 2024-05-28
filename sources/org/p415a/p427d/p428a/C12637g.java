package org.p415a.p427d.p428a;

import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12637g extends AbstractC12631a {

    /* renamed from: a */
    static final int[] f25680a = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* renamed from: b */
    private int f25681b;

    /* renamed from: c */
    private int f25682c;

    /* renamed from: d */
    private int f25683d;

    /* renamed from: e */
    private int f25684e;

    /* renamed from: f */
    private int f25685f;

    /* renamed from: g */
    private int f25686g;

    /* renamed from: h */
    private int f25687h;

    /* renamed from: i */
    private int f25688i;

    /* renamed from: j */
    private int[] f25689j = new int[64];

    /* renamed from: k */
    private int f25690k;

    public C12637g() {
        mo1484c();
    }

    /* renamed from: a */
    private int m1496a(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    /* renamed from: a */
    private int m1495a(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    /* renamed from: b */
    private int m1494b(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    /* renamed from: b */
    private int m1493b(int i, int i2, int i3) {
        return ((i & i3) ^ (i & i2)) ^ (i2 & i3);
    }

    /* renamed from: c */
    private int m1492c(int i) {
        return (i >>> 3) ^ (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14)));
    }

    /* renamed from: d */
    private int m1491d(int i) {
        return (i >>> 10) ^ (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13)));
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1314a() {
        return 28;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1312a(byte[] bArr, int i) {
        m1527b();
        AbstractC12971e.m400a(this.f25681b, bArr, i);
        AbstractC12971e.m400a(this.f25682c, bArr, i + 4);
        AbstractC12971e.m400a(this.f25683d, bArr, i + 8);
        AbstractC12971e.m400a(this.f25684e, bArr, i + 12);
        AbstractC12971e.m400a(this.f25685f, bArr, i + 16);
        AbstractC12971e.m400a(this.f25686g, bArr, i + 20);
        AbstractC12971e.m400a(this.f25687h, bArr, i + 24);
        mo1484c();
        return 28;
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: a */
    protected void mo1488a(long j) {
        if (this.f25690k > 14) {
            mo1482d();
        }
        int[] iArr = this.f25689j;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: b */
    protected void mo1485b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f25689j;
        int i5 = this.f25690k;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.f25690k = i6;
        if (i6 == 16) {
            mo1482d();
        }
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: c */
    public void mo1484c() {
        super.mo1484c();
        this.f25681b = -1056596264;
        this.f25682c = 914150663;
        this.f25683d = 812702999;
        this.f25684e = -150054599;
        this.f25685f = -4191439;
        this.f25686g = 1750603025;
        this.f25687h = 1694076839;
        this.f25688i = -1090891868;
        this.f25690k = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f25689j;
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
        for (int i = 16; i <= 63; i++) {
            int[] iArr = this.f25689j;
            int m1491d = m1491d(iArr[i - 2]);
            int[] iArr2 = this.f25689j;
            iArr[i] = m1491d + iArr2[i - 7] + m1492c(iArr2[i - 15]) + this.f25689j[i - 16];
        }
        int i2 = this.f25681b;
        int i3 = this.f25682c;
        int i4 = this.f25683d;
        int i5 = this.f25684e;
        int i6 = this.f25685f;
        int i7 = this.f25686g;
        int i8 = this.f25687h;
        int i9 = this.f25688i;
        int i10 = 0;
        int i11 = i8;
        int i12 = i7;
        int i13 = i6;
        int i14 = i5;
        int i15 = i4;
        int i16 = i3;
        int i17 = i2;
        for (int i18 = 0; i18 < 8; i18++) {
            int m1494b = i9 + m1494b(i13) + m1495a(i13, i12, i11) + f25680a[i10] + this.f25689j[i10];
            int i19 = i14 + m1494b;
            int m1496a = m1494b + m1496a(i17) + m1493b(i17, i16, i15);
            int i20 = i10 + 1;
            int m1494b2 = i11 + m1494b(i19) + m1495a(i19, i13, i12) + f25680a[i20] + this.f25689j[i20];
            int i21 = i15 + m1494b2;
            int m1496a2 = m1494b2 + m1496a(m1496a) + m1493b(m1496a, i17, i16);
            int i22 = i20 + 1;
            int m1494b3 = i12 + m1494b(i21) + m1495a(i21, i19, i13) + f25680a[i22] + this.f25689j[i22];
            int i23 = i16 + m1494b3;
            int m1496a3 = m1494b3 + m1496a(m1496a2) + m1493b(m1496a2, m1496a, i17);
            int i24 = i22 + 1;
            int m1494b4 = i13 + m1494b(i23) + m1495a(i23, i21, i19) + f25680a[i24] + this.f25689j[i24];
            int i25 = i17 + m1494b4;
            int m1496a4 = m1494b4 + m1496a(m1496a3) + m1493b(m1496a3, m1496a2, m1496a);
            int i26 = i24 + 1;
            int m1494b5 = i19 + m1494b(i25) + m1495a(i25, i23, i21) + f25680a[i26] + this.f25689j[i26];
            i9 = m1496a + m1494b5;
            i14 = m1494b5 + m1496a(m1496a4) + m1493b(m1496a4, m1496a3, m1496a2);
            int i27 = i26 + 1;
            int m1494b6 = i21 + m1494b(i9) + m1495a(i9, i25, i23) + f25680a[i27] + this.f25689j[i27];
            i11 = m1496a2 + m1494b6;
            i15 = m1494b6 + m1496a(i14) + m1493b(i14, m1496a4, m1496a3);
            int i28 = i27 + 1;
            int m1494b7 = i23 + m1494b(i11) + m1495a(i11, i9, i25) + f25680a[i28] + this.f25689j[i28];
            i12 = m1496a3 + m1494b7;
            i16 = m1494b7 + m1496a(i15) + m1493b(i15, i14, m1496a4);
            int i29 = i28 + 1;
            int m1494b8 = i25 + m1494b(i12) + m1495a(i12, i11, i9) + f25680a[i29] + this.f25689j[i29];
            i13 = m1496a4 + m1494b8;
            i17 = m1494b8 + m1496a(i16) + m1493b(i16, i15, i14);
            i10 = i29 + 1;
        }
        this.f25681b += i17;
        this.f25682c += i16;
        this.f25683d += i15;
        this.f25684e += i14;
        this.f25685f += i13;
        this.f25686g += i12;
        this.f25687h += i11;
        this.f25688i += i9;
        this.f25690k = 0;
        for (int i30 = 0; i30 < 16; i30++) {
            this.f25689j[i30] = 0;
        }
    }
}
