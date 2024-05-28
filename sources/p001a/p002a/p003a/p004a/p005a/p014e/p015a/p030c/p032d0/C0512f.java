package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p046q0.AbstractC0632a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f;

/* renamed from: a.a.a.a.a.e.a.c.d0.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0512f extends AbstractC0507a {

    /* renamed from: l */
    public static final int f1669l = 20;

    /* renamed from: m */
    public static final int f1670m = 1518500249;

    /* renamed from: n */
    public static final int f1671n = 1859775393;

    /* renamed from: o */
    public static final int f1672o = -1894007588;

    /* renamed from: p */
    public static final int f1673p = -899497514;

    /* renamed from: e */
    public int f1674e;

    /* renamed from: f */
    public int f1675f;

    /* renamed from: g */
    public int f1676g;

    /* renamed from: h */
    public int f1677h;

    /* renamed from: i */
    public int f1678i;

    /* renamed from: j */
    public int[] f1679j;

    /* renamed from: k */
    public int f1680k;

    public C0512f() {
        this.f1679j = new int[80];
        mo22744b();
    }

    /* renamed from: a */
    private int m22943a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m22942a(C0512f c0512f) {
        this.f1674e = c0512f.f1674e;
        this.f1675f = c0512f.f1675f;
        this.f1676g = c0512f.f1676g;
        this.f1677h = c0512f.f1677h;
        this.f1678i = c0512f.f1678i;
        int[] iArr = c0512f.f1679j;
        System.arraycopy(iArr, 0, this.f1679j, 0, iArr.length);
        this.f1680k = c0512f.f1680k;
    }

    /* renamed from: b */
    private int m22941b(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: c */
    private int m22940c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public String mo22748a() {
        return "SHA-1";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: b */
    public void mo22933b(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.f1679j;
        int i5 = this.f1680k;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.f1680k = i6;
        if (i6 == 16) {
            mo22930g();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: c */
    public int mo22743c() {
        return 20;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f
    /* renamed from: e */
    public InterfaceC0674f mo22453e() {
        return new C0512f(this);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: g */
    public void mo22930g() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f1679j;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.f1674e;
        int i4 = this.f1675f;
        int i5 = this.f1676g;
        int i6 = this.f1677h;
        int i7 = this.f1678i;
        int i8 = i4;
        int i9 = 0;
        int i10 = i3;
        int i11 = 0;
        while (i11 < 4) {
            int i12 = i9 + 1;
            int m22943a = i7 + ((i10 << 5) | (i10 >>> 27)) + m22943a(i8, i5, i6) + this.f1679j[i9] + f1670m;
            int i13 = (i8 >>> 2) | (i8 << 30);
            int i14 = i12 + 1;
            int m22943a2 = i6 + ((m22943a << 5) | (m22943a >>> 27)) + m22943a(i10, i13, i5) + this.f1679j[i12] + f1670m;
            int i15 = (i10 >>> 2) | (i10 << 30);
            int i16 = i14 + 1;
            int m22943a3 = i5 + ((m22943a2 << 5) | (m22943a2 >>> 27)) + m22943a(m22943a, i15, i13) + this.f1679j[i14] + f1670m;
            i7 = (m22943a >>> 2) | (m22943a << 30);
            int i17 = i16 + 1;
            i8 = i13 + ((m22943a3 << 5) | (m22943a3 >>> 27)) + m22943a(m22943a2, i7, i15) + this.f1679j[i16] + f1670m;
            i6 = (m22943a2 >>> 2) | (m22943a2 << 30);
            i10 = i15 + ((i8 << 5) | (i8 >>> 27)) + m22943a(m22943a3, i6, i7) + this.f1679j[i17] + f1670m;
            i5 = (m22943a3 >>> 2) | (m22943a3 << 30);
            i11++;
            i9 = i17 + 1;
        }
        int i18 = 0;
        while (i18 < 4) {
            int i19 = i9 + 1;
            int m22940c = i7 + ((i10 << 5) | (i10 >>> 27)) + m22940c(i8, i5, i6) + this.f1679j[i9] + f1671n;
            int i20 = (i8 >>> 2) | (i8 << 30);
            int i21 = i19 + 1;
            int m22940c2 = i6 + ((m22940c << 5) | (m22940c >>> 27)) + m22940c(i10, i20, i5) + this.f1679j[i19] + f1671n;
            int i22 = (i10 >>> 2) | (i10 << 30);
            int i23 = i21 + 1;
            int m22940c3 = i5 + ((m22940c2 << 5) | (m22940c2 >>> 27)) + m22940c(m22940c, i22, i20) + this.f1679j[i21] + f1671n;
            i7 = (m22940c >>> 2) | (m22940c << 30);
            int i24 = i23 + 1;
            i8 = i20 + ((m22940c3 << 5) | (m22940c3 >>> 27)) + m22940c(m22940c2, i7, i22) + this.f1679j[i23] + f1671n;
            i6 = (m22940c2 >>> 2) | (m22940c2 << 30);
            i10 = i22 + ((i8 << 5) | (i8 >>> 27)) + m22940c(m22940c3, i6, i7) + this.f1679j[i24] + f1671n;
            i5 = (m22940c3 >>> 2) | (m22940c3 << 30);
            i18++;
            i9 = i24 + 1;
        }
        int i25 = 0;
        while (i25 < 4) {
            int i26 = i9 + 1;
            int m22941b = i7 + ((i10 << 5) | (i10 >>> 27)) + m22941b(i8, i5, i6) + this.f1679j[i9] + f1672o;
            int i27 = (i8 >>> 2) | (i8 << 30);
            int i28 = i26 + 1;
            int m22941b2 = i6 + ((m22941b << 5) | (m22941b >>> 27)) + m22941b(i10, i27, i5) + this.f1679j[i26] + f1672o;
            int i29 = (i10 >>> 2) | (i10 << 30);
            int i30 = i28 + 1;
            int m22941b3 = i5 + ((m22941b2 << 5) | (m22941b2 >>> 27)) + m22941b(m22941b, i29, i27) + this.f1679j[i28] + f1672o;
            i7 = (m22941b >>> 2) | (m22941b << 30);
            int i31 = i30 + 1;
            i8 = i27 + ((m22941b3 << 5) | (m22941b3 >>> 27)) + m22941b(m22941b2, i7, i29) + this.f1679j[i30] + f1672o;
            i6 = (m22941b2 >>> 2) | (m22941b2 << 30);
            i10 = i29 + ((i8 << 5) | (i8 >>> 27)) + m22941b(m22941b3, i6, i7) + this.f1679j[i31] + f1672o;
            i5 = (m22941b3 >>> 2) | (m22941b3 << 30);
            i25++;
            i9 = i31 + 1;
        }
        int i32 = 0;
        while (i32 <= 3) {
            int i33 = i9 + 1;
            int m22940c4 = i7 + ((i10 << 5) | (i10 >>> 27)) + m22940c(i8, i5, i6) + this.f1679j[i9] + f1673p;
            int i34 = (i8 >>> 2) | (i8 << 30);
            int i35 = i33 + 1;
            int m22940c5 = i6 + ((m22940c4 << 5) | (m22940c4 >>> 27)) + m22940c(i10, i34, i5) + this.f1679j[i33] + f1673p;
            int i36 = (i10 >>> 2) | (i10 << 30);
            int i37 = i35 + 1;
            int m22940c6 = i5 + ((m22940c5 << 5) | (m22940c5 >>> 27)) + m22940c(m22940c4, i36, i34) + this.f1679j[i35] + f1673p;
            i7 = (m22940c4 >>> 2) | (m22940c4 << 30);
            int i38 = i37 + 1;
            i8 = i34 + ((m22940c6 << 5) | (m22940c6 >>> 27)) + m22940c(m22940c5, i7, i36) + this.f1679j[i37] + f1673p;
            i6 = (m22940c5 >>> 2) | (m22940c5 << 30);
            i10 = i36 + ((i8 << 5) | (i8 >>> 27)) + m22940c(m22940c6, i6, i7) + this.f1679j[i38] + f1673p;
            i5 = (m22940c6 >>> 2) | (m22940c6 << 30);
            i32++;
            i9 = i38 + 1;
        }
        this.f1674e += i10;
        this.f1675f += i8;
        this.f1676g += i5;
        this.f1677h += i6;
        this.f1678i += i7;
        this.f1680k = 0;
        for (int i39 = 0; i39 < 16; i39++) {
            this.f1679j[i39] = 0;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: a */
    public void mo22937a(long j) {
        if (this.f1680k > 14) {
            mo22930g();
        }
        int[] iArr = this.f1679j;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        super.mo22744b();
        this.f1674e = 1732584193;
        this.f1675f = -271733879;
        this.f1676g = -1732584194;
        this.f1677h = 271733878;
        this.f1678i = -1009589776;
        this.f1680k = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f1679j;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    public C0512f(C0512f c0512f) {
        super(c0512f);
        this.f1679j = new int[80];
        m22942a(c0512f);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public int mo22746a(byte[] bArr, int i) {
        m22963f();
        AbstractC0632a.m22694a(this.f1674e, bArr, i);
        AbstractC0632a.m22694a(this.f1675f, bArr, i + 4);
        AbstractC0632a.m22694a(this.f1676g, bArr, i + 8);
        AbstractC0632a.m22694a(this.f1677h, bArr, i + 12);
        AbstractC0632a.m22694a(this.f1678i, bArr, i + 16);
        mo22744b();
        return 20;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f
    /* renamed from: a */
    public void mo22454a(InterfaceC0674f interfaceC0674f) {
        C0512f c0512f = (C0512f) interfaceC0674f;
        super.m22964a((AbstractC0507a) c0512f);
        m22942a(c0512f);
    }
}
