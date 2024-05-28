package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p046q0.AbstractC0632a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f;

/* renamed from: a.a.a.a.a.e.a.c.d0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0513g extends AbstractC0507a {

    /* renamed from: o */
    public static final int f1681o = 32;

    /* renamed from: p */
    public static final int[] f1682p = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* renamed from: e */
    public int f1683e;

    /* renamed from: f */
    public int f1684f;

    /* renamed from: g */
    public int f1685g;

    /* renamed from: h */
    public int f1686h;

    /* renamed from: i */
    public int f1687i;

    /* renamed from: j */
    public int f1688j;

    /* renamed from: k */
    public int f1689k;

    /* renamed from: l */
    public int f1690l;

    /* renamed from: m */
    public int[] f1691m;

    /* renamed from: n */
    public int f1692n;

    public C0513g() {
        this.f1691m = new int[64];
        mo22744b();
    }

    /* renamed from: a */
    private int m22939a(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    /* renamed from: a */
    private int m22938a(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    /* renamed from: a */
    private void m22936a(C0513g c0513g) {
        super.m22964a((AbstractC0507a) c0513g);
        this.f1683e = c0513g.f1683e;
        this.f1684f = c0513g.f1684f;
        this.f1685g = c0513g.f1685g;
        this.f1686h = c0513g.f1686h;
        this.f1687i = c0513g.f1687i;
        this.f1688j = c0513g.f1688j;
        this.f1689k = c0513g.f1689k;
        this.f1690l = c0513g.f1690l;
        int[] iArr = c0513g.f1691m;
        System.arraycopy(iArr, 0, this.f1691m, 0, iArr.length);
        this.f1692n = c0513g.f1692n;
    }

    /* renamed from: b */
    private int m22935b(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    /* renamed from: b */
    private int m22934b(int i, int i2, int i3) {
        return ((i & i3) ^ (i & i2)) ^ (i2 & i3);
    }

    /* renamed from: c */
    private int m22932c(int i) {
        return (i >>> 3) ^ (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14)));
    }

    /* renamed from: d */
    private int m22931d(int i) {
        return (i >>> 10) ^ (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13)));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public String mo22748a() {
        return "SHA-256";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: b */
    public void mo22933b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f1691m;
        int i5 = this.f1692n;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.f1692n = i6;
        if (i6 == 16) {
            mo22930g();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: c */
    public int mo22743c() {
        return 32;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f
    /* renamed from: e */
    public InterfaceC0674f mo22453e() {
        return new C0513g(this);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: g */
    public void mo22930g() {
        for (int i = 16; i <= 63; i++) {
            int[] iArr = this.f1691m;
            int m22931d = m22931d(iArr[i - 2]);
            int[] iArr2 = this.f1691m;
            iArr[i] = m22931d + iArr2[i - 7] + m22932c(iArr2[i - 15]) + this.f1691m[i - 16];
        }
        int i2 = this.f1683e;
        int i3 = this.f1684f;
        int i4 = this.f1685g;
        int i5 = this.f1686h;
        int i6 = this.f1687i;
        int i7 = this.f1688j;
        int i8 = this.f1689k;
        int i9 = this.f1690l;
        int i10 = 0;
        int i11 = i8;
        int i12 = i7;
        int i13 = i6;
        int i14 = i5;
        int i15 = i4;
        int i16 = i3;
        int i17 = i2;
        for (int i18 = 0; i18 < 8; i18++) {
            int m22935b = i9 + m22935b(i13) + m22938a(i13, i12, i11) + f1682p[i10] + this.f1691m[i10];
            int i19 = i14 + m22935b;
            int m22939a = m22935b + m22939a(i17) + m22934b(i17, i16, i15);
            int i20 = i10 + 1;
            int m22935b2 = i11 + m22935b(i19) + m22938a(i19, i13, i12) + f1682p[i20] + this.f1691m[i20];
            int i21 = i15 + m22935b2;
            int m22939a2 = m22935b2 + m22939a(m22939a) + m22934b(m22939a, i17, i16);
            int i22 = i20 + 1;
            int m22935b3 = i12 + m22935b(i21) + m22938a(i21, i19, i13) + f1682p[i22] + this.f1691m[i22];
            int i23 = i16 + m22935b3;
            int m22939a3 = m22935b3 + m22939a(m22939a2) + m22934b(m22939a2, m22939a, i17);
            int i24 = i22 + 1;
            int m22935b4 = i13 + m22935b(i23) + m22938a(i23, i21, i19) + f1682p[i24] + this.f1691m[i24];
            int i25 = i17 + m22935b4;
            int m22939a4 = m22935b4 + m22939a(m22939a3) + m22934b(m22939a3, m22939a2, m22939a);
            int i26 = i24 + 1;
            int m22935b5 = i19 + m22935b(i25) + m22938a(i25, i23, i21) + f1682p[i26] + this.f1691m[i26];
            i9 = m22939a + m22935b5;
            i14 = m22935b5 + m22939a(m22939a4) + m22934b(m22939a4, m22939a3, m22939a2);
            int i27 = i26 + 1;
            int m22935b6 = i21 + m22935b(i9) + m22938a(i9, i25, i23) + f1682p[i27] + this.f1691m[i27];
            i11 = m22939a2 + m22935b6;
            i15 = m22935b6 + m22939a(i14) + m22934b(i14, m22939a4, m22939a3);
            int i28 = i27 + 1;
            int m22935b7 = i23 + m22935b(i11) + m22938a(i11, i9, i25) + f1682p[i28] + this.f1691m[i28];
            i12 = m22939a3 + m22935b7;
            i16 = m22935b7 + m22939a(i15) + m22934b(i15, i14, m22939a4);
            int i29 = i28 + 1;
            int m22935b8 = i25 + m22935b(i12) + m22938a(i12, i11, i9) + f1682p[i29] + this.f1691m[i29];
            i13 = m22939a4 + m22935b8;
            i17 = m22935b8 + m22939a(i16) + m22934b(i16, i15, i14);
            i10 = i29 + 1;
        }
        this.f1683e += i17;
        this.f1684f += i16;
        this.f1685g += i15;
        this.f1686h += i14;
        this.f1687i += i13;
        this.f1688j += i12;
        this.f1689k += i11;
        this.f1690l += i9;
        this.f1692n = 0;
        for (int i30 = 0; i30 < 16; i30++) {
            this.f1691m[i30] = 0;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        super.mo22744b();
        this.f1683e = 1779033703;
        this.f1684f = -1150833019;
        this.f1685g = 1013904242;
        this.f1686h = -1521486534;
        this.f1687i = 1359893119;
        this.f1688j = -1694144372;
        this.f1689k = 528734635;
        this.f1690l = 1541459225;
        this.f1692n = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f1691m;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    public C0513g(C0513g c0513g) {
        super(c0513g);
        this.f1691m = new int[64];
        m22936a(c0513g);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: a */
    public void mo22937a(long j) {
        if (this.f1692n > 14) {
            mo22930g();
        }
        int[] iArr = this.f1691m;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public int mo22746a(byte[] bArr, int i) {
        m22963f();
        AbstractC0632a.m22694a(this.f1683e, bArr, i);
        AbstractC0632a.m22694a(this.f1684f, bArr, i + 4);
        AbstractC0632a.m22694a(this.f1685g, bArr, i + 8);
        AbstractC0632a.m22694a(this.f1686h, bArr, i + 12);
        AbstractC0632a.m22694a(this.f1687i, bArr, i + 16);
        AbstractC0632a.m22694a(this.f1688j, bArr, i + 20);
        AbstractC0632a.m22694a(this.f1689k, bArr, i + 24);
        AbstractC0632a.m22694a(this.f1690l, bArr, i + 28);
        mo22744b();
        return 32;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f
    /* renamed from: a */
    public void mo22454a(InterfaceC0674f interfaceC0674f) {
        m22936a((C0513g) interfaceC0674f);
    }
}
