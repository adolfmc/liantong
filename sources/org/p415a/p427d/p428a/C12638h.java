package org.p415a.p427d.p428a;

import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12638h extends AbstractC12631a {

    /* renamed from: a */
    static final int[] f25691a = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* renamed from: b */
    private int f25692b;

    /* renamed from: c */
    private int f25693c;

    /* renamed from: d */
    private int f25694d;

    /* renamed from: e */
    private int f25695e;

    /* renamed from: f */
    private int f25696f;

    /* renamed from: g */
    private int f25697g;

    /* renamed from: h */
    private int f25698h;

    /* renamed from: i */
    private int f25699i;

    /* renamed from: j */
    private int[] f25700j = new int[64];

    /* renamed from: k */
    private int f25701k;

    public C12638h() {
        mo1484c();
    }

    /* renamed from: a */
    private int m1490a(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    /* renamed from: a */
    private int m1489a(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    /* renamed from: b */
    private int m1487b(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    /* renamed from: b */
    private int m1486b(int i, int i2, int i3) {
        return ((i & i3) ^ (i & i2)) ^ (i2 & i3);
    }

    /* renamed from: c */
    private int m1483c(int i) {
        return (i >>> 3) ^ (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14)));
    }

    /* renamed from: d */
    private int m1481d(int i) {
        return (i >>> 10) ^ (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13)));
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1314a() {
        return 32;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1312a(byte[] bArr, int i) {
        m1527b();
        AbstractC12971e.m400a(this.f25692b, bArr, i);
        AbstractC12971e.m400a(this.f25693c, bArr, i + 4);
        AbstractC12971e.m400a(this.f25694d, bArr, i + 8);
        AbstractC12971e.m400a(this.f25695e, bArr, i + 12);
        AbstractC12971e.m400a(this.f25696f, bArr, i + 16);
        AbstractC12971e.m400a(this.f25697g, bArr, i + 20);
        AbstractC12971e.m400a(this.f25698h, bArr, i + 24);
        AbstractC12971e.m400a(this.f25699i, bArr, i + 28);
        mo1484c();
        return 32;
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: a */
    protected void mo1488a(long j) {
        if (this.f25701k > 14) {
            mo1482d();
        }
        int[] iArr = this.f25700j;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: b */
    protected void mo1485b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f25700j;
        int i5 = this.f25701k;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.f25701k = i6;
        if (i6 == 16) {
            mo1482d();
        }
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: c */
    public void mo1484c() {
        super.mo1484c();
        this.f25692b = 1779033703;
        this.f25693c = -1150833019;
        this.f25694d = 1013904242;
        this.f25695e = -1521486534;
        this.f25696f = 1359893119;
        this.f25697g = -1694144372;
        this.f25698h = 528734635;
        this.f25699i = 1541459225;
        this.f25701k = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f25700j;
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
            int[] iArr = this.f25700j;
            int m1481d = m1481d(iArr[i - 2]);
            int[] iArr2 = this.f25700j;
            iArr[i] = m1481d + iArr2[i - 7] + m1483c(iArr2[i - 15]) + this.f25700j[i - 16];
        }
        int i2 = this.f25692b;
        int i3 = this.f25693c;
        int i4 = this.f25694d;
        int i5 = this.f25695e;
        int i6 = this.f25696f;
        int i7 = this.f25697g;
        int i8 = this.f25698h;
        int i9 = this.f25699i;
        int i10 = 0;
        int i11 = i8;
        int i12 = i7;
        int i13 = i6;
        int i14 = i5;
        int i15 = i4;
        int i16 = i3;
        int i17 = i2;
        for (int i18 = 0; i18 < 8; i18++) {
            int m1487b = i9 + m1487b(i13) + m1489a(i13, i12, i11) + f25691a[i10] + this.f25700j[i10];
            int i19 = i14 + m1487b;
            int m1490a = m1487b + m1490a(i17) + m1486b(i17, i16, i15);
            int i20 = i10 + 1;
            int m1487b2 = i11 + m1487b(i19) + m1489a(i19, i13, i12) + f25691a[i20] + this.f25700j[i20];
            int i21 = i15 + m1487b2;
            int m1490a2 = m1487b2 + m1490a(m1490a) + m1486b(m1490a, i17, i16);
            int i22 = i20 + 1;
            int m1487b3 = i12 + m1487b(i21) + m1489a(i21, i19, i13) + f25691a[i22] + this.f25700j[i22];
            int i23 = i16 + m1487b3;
            int m1490a3 = m1487b3 + m1490a(m1490a2) + m1486b(m1490a2, m1490a, i17);
            int i24 = i22 + 1;
            int m1487b4 = i13 + m1487b(i23) + m1489a(i23, i21, i19) + f25691a[i24] + this.f25700j[i24];
            int i25 = i17 + m1487b4;
            int m1490a4 = m1487b4 + m1490a(m1490a3) + m1486b(m1490a3, m1490a2, m1490a);
            int i26 = i24 + 1;
            int m1487b5 = i19 + m1487b(i25) + m1489a(i25, i23, i21) + f25691a[i26] + this.f25700j[i26];
            i9 = m1490a + m1487b5;
            i14 = m1487b5 + m1490a(m1490a4) + m1486b(m1490a4, m1490a3, m1490a2);
            int i27 = i26 + 1;
            int m1487b6 = i21 + m1487b(i9) + m1489a(i9, i25, i23) + f25691a[i27] + this.f25700j[i27];
            i11 = m1490a2 + m1487b6;
            i15 = m1487b6 + m1490a(i14) + m1486b(i14, m1490a4, m1490a3);
            int i28 = i27 + 1;
            int m1487b7 = i23 + m1487b(i11) + m1489a(i11, i9, i25) + f25691a[i28] + this.f25700j[i28];
            i12 = m1490a3 + m1487b7;
            i16 = m1487b7 + m1490a(i15) + m1486b(i15, i14, m1490a4);
            int i29 = i28 + 1;
            int m1487b8 = i25 + m1487b(i12) + m1489a(i12, i11, i9) + f25691a[i29] + this.f25700j[i29];
            i13 = m1490a4 + m1487b8;
            i17 = m1487b8 + m1490a(i16) + m1486b(i16, i15, i14);
            i10 = i29 + 1;
        }
        this.f25692b += i17;
        this.f25693c += i16;
        this.f25694d += i15;
        this.f25695e += i14;
        this.f25696f += i13;
        this.f25697g += i12;
        this.f25698h += i11;
        this.f25699i += i9;
        this.f25701k = 0;
        for (int i30 = 0; i30 < 16; i30++) {
            this.f25700j[i30] = 0;
        }
    }
}
