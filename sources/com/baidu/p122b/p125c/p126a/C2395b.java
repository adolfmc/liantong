package com.baidu.p122b.p125c.p126a;

import java.lang.reflect.Array;
import java.security.InvalidKeyException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2395b implements InterfaceC2394a {

    /* renamed from: g */
    private static int[] f4186g;

    /* renamed from: h */
    private static int[] f4187h;

    /* renamed from: i */
    private static final byte[] f4188i = new byte[256];

    /* renamed from: j */
    private static final byte[] f4189j = new byte[256];

    /* renamed from: k */
    private static final int[] f4190k = new int[256];

    /* renamed from: l */
    private static final int[] f4191l = new int[256];

    /* renamed from: m */
    private static final int[] f4192m = new int[256];

    /* renamed from: n */
    private static final int[] f4193n = new int[256];

    /* renamed from: o */
    private static final int[] f4194o = new int[256];

    /* renamed from: p */
    private static final int[] f4195p = new int[256];

    /* renamed from: q */
    private static final int[] f4196q = new int[256];

    /* renamed from: r */
    private static final int[] f4197r = new int[256];

    /* renamed from: s */
    private static final int[] f4198s = new int[256];

    /* renamed from: t */
    private static final int[] f4199t = new int[256];

    /* renamed from: u */
    private static final int[] f4200u = new int[256];

    /* renamed from: v */
    private static final int[] f4201v = new int[256];

    /* renamed from: w */
    private static final byte[] f4202w = new byte[30];

    /* renamed from: b */
    private boolean f4203b = false;

    /* renamed from: c */
    private boolean f4204c = false;

    /* renamed from: d */
    private Object[] f4205d = null;

    /* renamed from: e */
    private int[] f4206e = null;

    /* renamed from: f */
    private int f4207f = 0;

    static {
        f4186g = new int[256];
        f4187h = new int[256];
        f4186g[0] = 1;
        for (int i = 1; i < 256; i++) {
            int[] iArr = f4186g;
            int i2 = i - 1;
            int i3 = iArr[i2] ^ (iArr[i2] << 1);
            if ((i3 & 256) != 0) {
                i3 ^= 283;
            }
            f4186g[i] = i3;
        }
        for (int i4 = 1; i4 < 255; i4++) {
            f4187h[f4186g[i4]] = i4;
        }
        byte[][] bArr = {new byte[]{1, 1, 1, 1, 1, 0, 0, 0}, new byte[]{0, 1, 1, 1, 1, 1, 0, 0}, new byte[]{0, 0, 1, 1, 1, 1, 1, 0}, new byte[]{0, 0, 0, 1, 1, 1, 1, 1}, new byte[]{1, 0, 0, 0, 1, 1, 1, 1}, new byte[]{1, 1, 0, 0, 0, 1, 1, 1}, new byte[]{1, 1, 1, 0, 0, 0, 1, 1}, new byte[]{1, 1, 1, 1, 0, 0, 0, 1}};
        byte[] bArr2 = {0, 1, 1, 0, 0, 0, 1, 1};
        byte[][] bArr3 = (byte[][]) Array.newInstance(byte.class, 256, 8);
        bArr3[1][7] = 1;
        for (int i5 = 2; i5 < 256; i5++) {
            int i6 = f4186g[255 - f4187h[i5]];
            for (int i7 = 0; i7 < 8; i7++) {
                bArr3[i5][i7] = (byte) ((i6 >>> (7 - i7)) & 1);
            }
        }
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, 256, 8);
        for (int i8 = 0; i8 < 256; i8++) {
            for (int i9 = 0; i9 < 8; i9++) {
                bArr4[i8][i9] = bArr2[i9];
                for (int i10 = 0; i10 < 8; i10++) {
                    byte[] bArr5 = bArr4[i8];
                    bArr5[i9] = (byte) (bArr5[i9] ^ (bArr[i9][i10] * bArr3[i8][i10]));
                }
            }
        }
        for (int i11 = 0; i11 < 256; i11++) {
            f4188i[i11] = (byte) (bArr4[i11][0] << 7);
            for (int i12 = 1; i12 < 8; i12++) {
                byte[] bArr6 = f4188i;
                bArr6[i11] = (byte) (bArr6[i11] ^ (bArr4[i11][i12] << (7 - i12)));
            }
            f4189j[f4188i[i11] & 255] = (byte) i11;
        }
        byte[][] bArr7 = {new byte[]{2, 1, 1, 3}, new byte[]{3, 2, 1, 1}, new byte[]{1, 3, 2, 1}, new byte[]{1, 1, 3, 2}};
        byte[][] bArr8 = (byte[][]) Array.newInstance(byte.class, 4, 8);
        for (int i13 = 0; i13 < 4; i13++) {
            for (int i14 = 0; i14 < 4; i14++) {
                bArr8[i13][i14] = bArr7[i13][i14];
            }
            bArr8[i13][i13 + 4] = 1;
        }
        byte[][] bArr9 = (byte[][]) Array.newInstance(byte.class, 4, 4);
        for (int i15 = 0; i15 < 4; i15++) {
            byte b = bArr8[i15][i15];
            if (b == 0) {
                int i16 = i15 + 1;
                while (bArr8[i16][i15] == 0 && i16 < 4) {
                    i16++;
                }
                if (i16 == 4) {
                    throw new RuntimeException("G matrix is not invertible");
                }
                for (int i17 = 0; i17 < 8; i17++) {
                    byte b2 = bArr8[i15][i17];
                    bArr8[i15][i17] = bArr8[i16][i17];
                    bArr8[i16][i17] = b2;
                }
                b = bArr8[i15][i15];
            }
            for (int i18 = 0; i18 < 8; i18++) {
                if (bArr8[i15][i18] != 0) {
                    byte[] bArr10 = bArr8[i15];
                    int[] iArr2 = f4186g;
                    int[] iArr3 = f4187h;
                    bArr10[i18] = (byte) iArr2[((iArr3[bArr8[i15][i18] & 255] + 255) - iArr3[b & 255]) % 255];
                }
            }
            for (int i19 = 0; i19 < 4; i19++) {
                if (i15 != i19) {
                    for (int i20 = i15 + 1; i20 < 8; i20++) {
                        byte[] bArr11 = bArr8[i19];
                        bArr11[i20] = (byte) (bArr11[i20] ^ m20289a(bArr8[i15][i20], bArr8[i19][i15]));
                    }
                    bArr8[i19][i15] = 0;
                }
            }
        }
        for (int i21 = 0; i21 < 4; i21++) {
            for (int i22 = 0; i22 < 4; i22++) {
                bArr9[i21][i22] = bArr8[i21][i22 + 4];
            }
        }
        for (int i23 = 0; i23 < 256; i23++) {
            byte b3 = f4188i[i23];
            f4190k[i23] = m20288a(b3, bArr7[0]);
            f4191l[i23] = m20288a(b3, bArr7[1]);
            f4192m[i23] = m20288a(b3, bArr7[2]);
            f4193n[i23] = m20288a(b3, bArr7[3]);
            byte b4 = f4189j[i23];
            f4194o[i23] = m20288a(b4, bArr9[0]);
            f4195p[i23] = m20288a(b4, bArr9[1]);
            f4196q[i23] = m20288a(b4, bArr9[2]);
            f4197r[i23] = m20288a(b4, bArr9[3]);
            f4198s[i23] = m20288a(i23, bArr9[0]);
            f4199t[i23] = m20288a(i23, bArr9[1]);
            f4200u[i23] = m20288a(i23, bArr9[2]);
            f4201v[i23] = m20288a(i23, bArr9[3]);
        }
        f4202w[0] = 1;
        int i24 = 1;
        for (int i25 = 1; i25 < 30; i25++) {
            i24 = m20289a(2, i24);
            f4202w[i25] = (byte) i24;
        }
        f4187h = null;
        f4186g = null;
    }

    /* renamed from: a */
    private static final int m20289a(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int[] iArr = f4186g;
        int[] iArr2 = f4187h;
        return iArr[(iArr2[i & 255] + iArr2[i2 & 255]) % 255];
    }

    /* renamed from: a */
    private static final int m20288a(int i, byte[] bArr) {
        if (i == 0) {
            return 0;
        }
        int[] iArr = f4187h;
        int i2 = iArr[i & 255];
        return ((bArr[0] != 0 ? f4186g[(iArr[bArr[0] & 255] + i2) % 255] & 255 : 0) << 24) | ((bArr[1] != 0 ? f4186g[(f4187h[bArr[1] & 255] + i2) % 255] & 255 : 0) << 16) | ((bArr[2] != 0 ? f4186g[(f4187h[bArr[2] & 255] + i2) % 255] & 255 : 0) << 8) | (bArr[3] != 0 ? f4186g[(i2 + f4187h[bArr[3] & 255]) % 255] & 255 : 0);
    }

    /* renamed from: a */
    private void m20287a(boolean z) {
        int[][] iArr = (int[][]) this.f4205d[z ? 1 : 0];
        int length = iArr.length;
        this.f4206e = new int[length * 4];
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                this.f4206e[(i * 4) + i2] = iArr[i][i2];
            }
        }
        if (z) {
            int[] iArr2 = this.f4206e;
            int i3 = iArr2[iArr2.length - 4];
            int i4 = iArr2[iArr2.length - 3];
            int i5 = iArr2[iArr2.length - 2];
            int i6 = iArr2[iArr2.length - 1];
            for (int length2 = iArr2.length - 1; length2 > 3; length2--) {
                int[] iArr3 = this.f4206e;
                iArr3[length2] = iArr3[length2 - 4];
            }
            int[] iArr4 = this.f4206e;
            iArr4[0] = i3;
            iArr4[1] = i4;
            iArr4[2] = i5;
            iArr4[3] = i6;
        }
        this.f4203b = length >= 13;
        this.f4204c = length == 15;
        this.f4207f = (length - 1) * 4;
    }

    /* renamed from: a */
    static final boolean m20290a(int i) {
        for (int i2 = 0; i2 < f4185a.length; i2++) {
            if (i == f4185a[i2]) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static Object[] m20285a(byte[] bArr) {
        int i;
        if (bArr != null) {
            if (!m20290a(bArr.length)) {
                throw new InvalidKeyException("Invalid AES key length: " + bArr.length + " bytes");
            }
            int m20283b = m20283b(bArr.length);
            int i2 = m20283b + 1;
            int i3 = i2 * 4;
            int[][] iArr = (int[][]) Array.newInstance(int.class, i2, 4);
            int[][] iArr2 = (int[][]) Array.newInstance(int.class, i2, 4);
            int length = bArr.length / 4;
            int[] iArr3 = new int[length];
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i5 < length) {
                iArr3[i5] = ((bArr[i6 + 2] & 255) << 8) | (bArr[i6] << 24) | ((bArr[i6 + 1] & 255) << 16) | (bArr[i6 + 3] & 255);
                i5++;
                i6 += 4;
            }
            int i7 = 0;
            int i8 = 0;
            while (i7 < length && i8 < i3) {
                int i9 = i8 / 4;
                int i10 = i8 % 4;
                iArr[i9][i10] = iArr3[i7];
                iArr2[m20283b - i9][i10] = iArr3[i7];
                i7++;
                i8++;
            }
            int i11 = 0;
            while (i8 < i3) {
                int i12 = iArr3[length - 1];
                int i13 = iArr3[i4];
                byte[] bArr2 = f4188i;
                int i14 = ((((bArr2[(i12 >>> 8) & 255] & 255) << 16) ^ (bArr2[(i12 >>> 16) & 255] << 24)) ^ ((bArr2[i12 & 255] & 255) << 8)) ^ (bArr2[i12 >>> 24] & 255);
                int i15 = i11 + 1;
                iArr3[i4] = ((f4202w[i11] << 24) ^ i14) ^ i13;
                if (length != 8) {
                    int i16 = i4;
                    int i17 = 1;
                    while (i17 < length) {
                        iArr3[i17] = iArr3[i17] ^ iArr3[i16];
                        i17++;
                        i16++;
                    }
                } else {
                    int i18 = i4;
                    int i19 = 1;
                    while (true) {
                        i = length / 2;
                        if (i19 >= i) {
                            break;
                        }
                        iArr3[i19] = iArr3[i19] ^ iArr3[i18];
                        i19++;
                        i18++;
                    }
                    int i20 = iArr3[i - 1];
                    int i21 = iArr3[i];
                    byte[] bArr3 = f4188i;
                    iArr3[i] = ((bArr3[i20 >>> 24] << 24) ^ ((((bArr3[(i20 >>> 8) & 255] & 255) << 8) ^ (bArr3[i20 & 255] & 255)) ^ ((bArr3[(i20 >>> 16) & 255] & 255) << 16))) ^ i21;
                    int i22 = i + 1;
                    while (i22 < length) {
                        iArr3[i22] = iArr3[i22] ^ iArr3[i];
                        i22++;
                        i++;
                    }
                }
                int i23 = 0;
                while (i23 < length && i8 < i3) {
                    int i24 = i8 / 4;
                    int i25 = i8 % 4;
                    iArr[i24][i25] = iArr3[i23];
                    iArr2[m20283b - i24][i25] = iArr3[i23];
                    i23++;
                    i8++;
                }
                i11 = i15;
                i4 = 0;
            }
            for (int i26 = 1; i26 < m20283b; i26++) {
                for (int i27 = 0; i27 < 4; i27++) {
                    int i28 = iArr2[i26][i27];
                    iArr2[i26][i27] = f4201v[i28 & 255] ^ ((f4198s[(i28 >>> 24) & 255] ^ f4199t[(i28 >>> 16) & 255]) ^ f4200u[(i28 >>> 8) & 255]);
                }
            }
            return new Object[]{iArr, iArr2};
        }
        throw new InvalidKeyException("Empty key");
    }

    /* renamed from: b */
    private static int m20283b(int i) {
        return (i >> 2) + 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m20291a() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20286a(boolean z, String str, byte[] bArr) {
        if (m20290a(bArr.length)) {
            this.f4205d = m20285a(bArr);
            m20287a(z);
            return;
        }
        throw new InvalidKeyException("Invalid AES key length: " + bArr.length + " bytes");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20284a(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = i4 + 1;
        int i6 = ((bArr[i3] & 255) << 16) | (bArr[i] << 24) | ((bArr[i4] & 255) << 8);
        int i7 = i5 + 1;
        int i8 = i6 | (bArr[i5] & 255);
        int[] iArr = this.f4206e;
        int i9 = i8 ^ iArr[0];
        int i10 = i7 + 1;
        int i11 = i10 + 1;
        int i12 = (bArr[i7] << 24) | ((bArr[i10] & 255) << 16);
        int i13 = i11 + 1;
        int i14 = i12 | ((bArr[i11] & 255) << 8);
        int i15 = i13 + 1;
        int i16 = (i14 | (bArr[i13] & 255)) ^ iArr[1];
        int i17 = i15 + 1;
        int i18 = i17 + 1;
        int i19 = ((bArr[i17] & 255) << 16) | (bArr[i15] << 24);
        int i20 = i18 + 1;
        int i21 = i19 | ((bArr[i18] & 255) << 8);
        int i22 = i20 + 1;
        int i23 = (i21 | (bArr[i20] & 255)) ^ iArr[2];
        int i24 = i22 + 1;
        int i25 = i24 + 1;
        int i26 = iArr[3];
        int i27 = i26 ^ (((((bArr[i24] & 255) << 16) | (bArr[i22] << 24)) | ((bArr[i25] & 255) << 8)) | (bArr[i25 + 1] & 255));
        int i28 = 4;
        while (i28 < this.f4207f) {
            int[] iArr2 = f4190k;
            int i29 = iArr2[i9 >>> 24];
            int[] iArr3 = f4191l;
            int i30 = i29 ^ iArr3[(i16 >>> 16) & 255];
            int[] iArr4 = f4192m;
            int i31 = i30 ^ iArr4[(i23 >>> 8) & 255];
            int[] iArr5 = f4193n;
            int i32 = i31 ^ iArr5[i27 & 255];
            int[] iArr6 = this.f4206e;
            int i33 = i28 + 1;
            int i34 = iArr6[i28] ^ i32;
            int i35 = i33 + 1;
            int i36 = (((iArr2[i16 >>> 24] ^ iArr3[(i23 >>> 16) & 255]) ^ iArr4[(i27 >>> 8) & 255]) ^ iArr5[i9 & 255]) ^ iArr6[i33];
            int i37 = ((iArr2[i23 >>> 24] ^ iArr3[(i27 >>> 16) & 255]) ^ iArr4[(i9 >>> 8) & 255]) ^ iArr5[i16 & 255];
            int i38 = i35 + 1;
            int i39 = (((iArr3[(i9 >>> 16) & 255] ^ iArr2[i27 >>> 24]) ^ iArr4[(i16 >>> 8) & 255]) ^ iArr5[i23 & 255]) ^ iArr6[i38];
            i16 = i36;
            i23 = i37 ^ iArr6[i35];
            i27 = i39;
            i9 = i34;
            i28 = i38 + 1;
        }
        int[] iArr7 = this.f4206e;
        int i40 = i28 + 1;
        int i41 = iArr7[i28];
        int i42 = i2 + 1;
        byte[] bArr3 = f4188i;
        bArr2[i2] = (byte) (bArr3[i9 >>> 24] ^ (i41 >>> 24));
        int i43 = i42 + 1;
        bArr2[i42] = (byte) (bArr3[(i16 >>> 16) & 255] ^ (i41 >>> 16));
        int i44 = i43 + 1;
        bArr2[i43] = (byte) (bArr3[(i23 >>> 8) & 255] ^ (i41 >>> 8));
        int i45 = i44 + 1;
        bArr2[i44] = (byte) (i41 ^ bArr3[i27 & 255]);
        int i46 = i40 + 1;
        int i47 = iArr7[i40];
        int i48 = i45 + 1;
        bArr2[i45] = (byte) (bArr3[i16 >>> 24] ^ (i47 >>> 24));
        int i49 = i48 + 1;
        bArr2[i48] = (byte) (bArr3[(i23 >>> 16) & 255] ^ (i47 >>> 16));
        int i50 = i49 + 1;
        bArr2[i49] = (byte) (bArr3[(i27 >>> 8) & 255] ^ (i47 >>> 8));
        int i51 = i50 + 1;
        bArr2[i50] = (byte) (i47 ^ bArr3[i9 & 255]);
        int i52 = i46 + 1;
        int i53 = iArr7[i46];
        int i54 = i51 + 1;
        bArr2[i51] = (byte) (bArr3[i23 >>> 24] ^ (i53 >>> 24));
        int i55 = i54 + 1;
        bArr2[i54] = (byte) (bArr3[(i27 >>> 16) & 255] ^ (i53 >>> 16));
        int i56 = i55 + 1;
        bArr2[i55] = (byte) (bArr3[(i9 >>> 8) & 255] ^ (i53 >>> 8));
        int i57 = i56 + 1;
        bArr2[i56] = (byte) (i53 ^ bArr3[i16 & 255]);
        int i58 = iArr7[i52];
        int i59 = i57 + 1;
        bArr2[i57] = (byte) (bArr3[i27 >>> 24] ^ (i58 >>> 24));
        int i60 = i59 + 1;
        bArr2[i59] = (byte) (bArr3[(i9 >>> 16) & 255] ^ (i58 >>> 16));
        bArr2[i60] = (byte) (bArr3[(i16 >>> 8) & 255] ^ (i58 >>> 8));
        bArr2[i60 + 1] = (byte) (bArr3[i23 & 255] ^ i58);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m20282b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i + 1;
        int i9 = i8 + 1;
        int i10 = i9 + 1;
        int i11 = ((bArr[i8] & 255) << 16) | (bArr[i] << 24) | ((bArr[i9] & 255) << 8);
        int i12 = i10 + 1;
        int i13 = i11 | (bArr[i10] & 255);
        int[] iArr = this.f4206e;
        int i14 = i13 ^ iArr[4];
        int i15 = i12 + 1;
        int i16 = i15 + 1;
        int i17 = (bArr[i12] << 24) | ((bArr[i15] & 255) << 16);
        int i18 = i16 + 1;
        int i19 = i17 | ((bArr[i16] & 255) << 8);
        int i20 = i18 + 1;
        int i21 = (i19 | (bArr[i18] & 255)) ^ iArr[5];
        int i22 = i20 + 1;
        int i23 = i22 + 1;
        int i24 = ((bArr[i22] & 255) << 16) | (bArr[i20] << 24);
        int i25 = i23 + 1;
        int i26 = i24 | ((bArr[i23] & 255) << 8);
        int i27 = i25 + 1;
        int i28 = (i26 | (bArr[i25] & 255)) ^ iArr[6];
        int i29 = i27 + 1;
        int i30 = i29 + 1;
        int i31 = (((((bArr[i29] & 255) << 16) | (bArr[i27] << 24)) | ((bArr[i30] & 255) << 8)) | (bArr[i30 + 1] & 255)) ^ iArr[7];
        if (this.f4203b) {
            int[] iArr2 = f4194o;
            int i32 = iArr2[i14 >>> 24];
            int[] iArr3 = f4195p;
            int i33 = i32 ^ iArr3[(i31 >>> 16) & 255];
            int[] iArr4 = f4196q;
            int i34 = i33 ^ iArr4[(i28 >>> 8) & 255];
            int[] iArr5 = f4197r;
            int i35 = (i34 ^ iArr5[i21 & 255]) ^ iArr[8];
            int i36 = (((iArr3[(i14 >>> 16) & 255] ^ iArr2[i21 >>> 24]) ^ iArr4[(i31 >>> 8) & 255]) ^ iArr5[i28 & 255]) ^ iArr[9];
            int i37 = (((iArr3[(i21 >>> 16) & 255] ^ iArr2[i28 >>> 24]) ^ iArr4[(i14 >>> 8) & 255]) ^ iArr5[i31 & 255]) ^ iArr[10];
            int i38 = iArr2[i31 >>> 24];
            int i39 = (iArr5[i14 & 255] ^ (iArr4[(i21 >>> 8) & 255] ^ (iArr3[(i28 >>> 16) & 255] ^ i38))) ^ iArr[11];
            i4 = iArr[12] ^ (((iArr2[i35 >>> 24] ^ iArr3[(i39 >>> 16) & 255]) ^ iArr4[(i37 >>> 8) & 255]) ^ iArr5[i36 & 255]);
            i7 = (((iArr2[i36 >>> 24] ^ iArr3[(i35 >>> 16) & 255]) ^ iArr4[(i39 >>> 8) & 255]) ^ iArr5[i37 & 255]) ^ iArr[13];
            i6 = (((iArr2[i37 >>> 24] ^ iArr3[(i36 >>> 16) & 255]) ^ iArr4[(i35 >>> 8) & 255]) ^ iArr5[i39 & 255]) ^ iArr[14];
            i5 = (((iArr2[i39 >>> 24] ^ iArr3[(i37 >>> 16) & 255]) ^ iArr4[(i36 >>> 8) & 255]) ^ iArr5[i35 & 255]) ^ iArr[15];
            if (this.f4204c) {
                int i40 = (((iArr2[i4 >>> 24] ^ iArr3[(i5 >>> 16) & 255]) ^ iArr4[(i6 >>> 8) & 255]) ^ iArr5[i7 & 255]) ^ iArr[16];
                int i41 = iArr[17] ^ (((iArr2[i7 >>> 24] ^ iArr3[(i4 >>> 16) & 255]) ^ iArr4[(i5 >>> 8) & 255]) ^ iArr5[i6 & 255]);
                int i42 = (((iArr2[i6 >>> 24] ^ iArr3[(i7 >>> 16) & 255]) ^ iArr4[(i4 >>> 8) & 255]) ^ iArr5[i5 & 255]) ^ iArr[18];
                int i43 = (((iArr2[i5 >>> 24] ^ iArr3[(i6 >>> 16) & 255]) ^ iArr4[(i7 >>> 8) & 255]) ^ iArr5[i4 & 255]) ^ iArr[19];
                i4 = iArr[20] ^ (((iArr2[i40 >>> 24] ^ iArr3[(i43 >>> 16) & 255]) ^ iArr4[(i42 >>> 8) & 255]) ^ iArr5[i41 & 255]);
                i7 = (((iArr2[i41 >>> 24] ^ iArr3[(i40 >>> 16) & 255]) ^ iArr4[(i43 >>> 8) & 255]) ^ iArr5[i42 & 255]) ^ iArr[21];
                i6 = (((iArr2[i42 >>> 24] ^ iArr3[(i41 >>> 16) & 255]) ^ iArr4[(i40 >>> 8) & 255]) ^ iArr5[i43 & 255]) ^ iArr[22];
                i5 = (((iArr2[i43 >>> 24] ^ iArr3[(i42 >>> 16) & 255]) ^ iArr4[(i41 >>> 8) & 255]) ^ iArr5[i40 & 255]) ^ iArr[23];
                i3 = 24;
            } else {
                i3 = 16;
            }
        } else {
            i3 = 8;
            i4 = i14;
            i5 = i31;
            i6 = i28;
            i7 = i21;
        }
        int[] iArr6 = f4194o;
        int i44 = iArr6[i4 >>> 24];
        int[] iArr7 = f4195p;
        int i45 = i44 ^ iArr7[(i5 >>> 16) & 255];
        int[] iArr8 = f4196q;
        int i46 = i45 ^ iArr8[(i6 >>> 8) & 255];
        int[] iArr9 = f4197r;
        int i47 = i46 ^ iArr9[i7 & 255];
        int[] iArr10 = this.f4206e;
        int i48 = i3 + 1;
        int i49 = iArr10[i3] ^ i47;
        int i50 = i48 + 1;
        int i51 = (((iArr6[i7 >>> 24] ^ iArr7[(i4 >>> 16) & 255]) ^ iArr8[(i5 >>> 8) & 255]) ^ iArr9[i6 & 255]) ^ iArr10[i48];
        int i52 = i50 + 1;
        int i53 = (((iArr6[i6 >>> 24] ^ iArr7[(i7 >>> 16) & 255]) ^ iArr8[(i4 >>> 8) & 255]) ^ iArr9[i5 & 255]) ^ iArr10[i50];
        int i54 = ((iArr6[i5 >>> 24] ^ iArr7[(i6 >>> 16) & 255]) ^ iArr8[(i7 >>> 8) & 255]) ^ iArr9[i4 & 255];
        int i55 = i52 + 1;
        int i56 = i54 ^ iArr10[i52];
        int i57 = i55 + 1;
        int i58 = iArr10[i55] ^ (((iArr6[i49 >>> 24] ^ iArr7[(i56 >>> 16) & 255]) ^ iArr8[(i53 >>> 8) & 255]) ^ iArr9[i51 & 255]);
        int i59 = i57 + 1;
        int i60 = (((iArr6[i51 >>> 24] ^ iArr7[(i49 >>> 16) & 255]) ^ iArr8[(i56 >>> 8) & 255]) ^ iArr9[i53 & 255]) ^ iArr10[i57];
        int i61 = i59 + 1;
        int i62 = (((iArr6[i53 >>> 24] ^ iArr7[(i51 >>> 16) & 255]) ^ iArr8[(i49 >>> 8) & 255]) ^ iArr9[i56 & 255]) ^ iArr10[i59];
        int i63 = ((iArr6[i56 >>> 24] ^ iArr7[(i53 >>> 16) & 255]) ^ iArr8[(i51 >>> 8) & 255]) ^ iArr9[i49 & 255];
        int i64 = i61 + 1;
        int i65 = i63 ^ iArr10[i61];
        int i66 = i64 + 1;
        int i67 = iArr10[i64] ^ (((iArr6[i58 >>> 24] ^ iArr7[(i65 >>> 16) & 255]) ^ iArr8[(i62 >>> 8) & 255]) ^ iArr9[i60 & 255]);
        int i68 = i66 + 1;
        int i69 = (((iArr6[i60 >>> 24] ^ iArr7[(i58 >>> 16) & 255]) ^ iArr8[(i65 >>> 8) & 255]) ^ iArr9[i62 & 255]) ^ iArr10[i66];
        int i70 = i68 + 1;
        int i71 = (((iArr6[i62 >>> 24] ^ iArr7[(i60 >>> 16) & 255]) ^ iArr8[(i58 >>> 8) & 255]) ^ iArr9[i65 & 255]) ^ iArr10[i68];
        int i72 = ((iArr6[i65 >>> 24] ^ iArr7[(i62 >>> 16) & 255]) ^ iArr8[(i60 >>> 8) & 255]) ^ iArr9[i58 & 255];
        int i73 = i70 + 1;
        int i74 = i72 ^ iArr10[i70];
        int i75 = i73 + 1;
        int i76 = iArr10[i73] ^ (((iArr6[i67 >>> 24] ^ iArr7[(i74 >>> 16) & 255]) ^ iArr8[(i71 >>> 8) & 255]) ^ iArr9[i69 & 255]);
        int i77 = i75 + 1;
        int i78 = (((iArr6[i69 >>> 24] ^ iArr7[(i67 >>> 16) & 255]) ^ iArr8[(i74 >>> 8) & 255]) ^ iArr9[i71 & 255]) ^ iArr10[i75];
        int i79 = i77 + 1;
        int i80 = (((iArr6[i71 >>> 24] ^ iArr7[(i69 >>> 16) & 255]) ^ iArr8[(i67 >>> 8) & 255]) ^ iArr9[i74 & 255]) ^ iArr10[i77];
        int i81 = ((iArr6[i74 >>> 24] ^ iArr7[(i71 >>> 16) & 255]) ^ iArr8[(i69 >>> 8) & 255]) ^ iArr9[i67 & 255];
        int i82 = i79 + 1;
        int i83 = i81 ^ iArr10[i79];
        int i84 = i82 + 1;
        int i85 = iArr10[i82] ^ (((iArr6[i76 >>> 24] ^ iArr7[(i83 >>> 16) & 255]) ^ iArr8[(i80 >>> 8) & 255]) ^ iArr9[i78 & 255]);
        int i86 = i84 + 1;
        int i87 = (((iArr6[i78 >>> 24] ^ iArr7[(i76 >>> 16) & 255]) ^ iArr8[(i83 >>> 8) & 255]) ^ iArr9[i80 & 255]) ^ iArr10[i84];
        int i88 = i86 + 1;
        int i89 = (((iArr6[i80 >>> 24] ^ iArr7[(i78 >>> 16) & 255]) ^ iArr8[(i76 >>> 8) & 255]) ^ iArr9[i83 & 255]) ^ iArr10[i86];
        int i90 = ((iArr6[i83 >>> 24] ^ iArr7[(i80 >>> 16) & 255]) ^ iArr8[(i78 >>> 8) & 255]) ^ iArr9[i76 & 255];
        int i91 = i88 + 1;
        int i92 = i90 ^ iArr10[i88];
        int i93 = i91 + 1;
        int i94 = iArr10[i91] ^ (((iArr6[i85 >>> 24] ^ iArr7[(i92 >>> 16) & 255]) ^ iArr8[(i89 >>> 8) & 255]) ^ iArr9[i87 & 255]);
        int i95 = i93 + 1;
        int i96 = (((iArr6[i87 >>> 24] ^ iArr7[(i85 >>> 16) & 255]) ^ iArr8[(i92 >>> 8) & 255]) ^ iArr9[i89 & 255]) ^ iArr10[i93];
        int i97 = i95 + 1;
        int i98 = (((iArr6[i89 >>> 24] ^ iArr7[(i87 >>> 16) & 255]) ^ iArr8[(i85 >>> 8) & 255]) ^ iArr9[i92 & 255]) ^ iArr10[i95];
        int i99 = ((iArr6[i92 >>> 24] ^ iArr7[(i89 >>> 16) & 255]) ^ iArr8[(i87 >>> 8) & 255]) ^ iArr9[i85 & 255];
        int i100 = i97 + 1;
        int i101 = i99 ^ iArr10[i97];
        int i102 = i100 + 1;
        int i103 = iArr10[i100] ^ (((iArr6[i94 >>> 24] ^ iArr7[(i101 >>> 16) & 255]) ^ iArr8[(i98 >>> 8) & 255]) ^ iArr9[i96 & 255]);
        int i104 = i102 + 1;
        int i105 = (((iArr6[i96 >>> 24] ^ iArr7[(i94 >>> 16) & 255]) ^ iArr8[(i101 >>> 8) & 255]) ^ iArr9[i98 & 255]) ^ iArr10[i102];
        int i106 = i104 + 1;
        int i107 = (((iArr6[i98 >>> 24] ^ iArr7[(i96 >>> 16) & 255]) ^ iArr8[(i94 >>> 8) & 255]) ^ iArr9[i101 & 255]) ^ iArr10[i104];
        int i108 = ((iArr6[i101 >>> 24] ^ iArr7[(i98 >>> 16) & 255]) ^ iArr8[(i96 >>> 8) & 255]) ^ iArr9[i94 & 255];
        int i109 = i106 + 1;
        int i110 = i108 ^ iArr10[i106];
        int i111 = i109 + 1;
        int i112 = iArr10[i109] ^ (((iArr6[i103 >>> 24] ^ iArr7[(i110 >>> 16) & 255]) ^ iArr8[(i107 >>> 8) & 255]) ^ iArr9[i105 & 255]);
        int i113 = i111 + 1;
        int i114 = (((iArr6[i105 >>> 24] ^ iArr7[(i103 >>> 16) & 255]) ^ iArr8[(i110 >>> 8) & 255]) ^ iArr9[i107 & 255]) ^ iArr10[i111];
        int i115 = i113 + 1;
        int i116 = (((iArr6[i107 >>> 24] ^ iArr7[(i105 >>> 16) & 255]) ^ iArr8[(i103 >>> 8) & 255]) ^ iArr9[i110 & 255]) ^ iArr10[i113];
        int i117 = ((iArr6[i110 >>> 24] ^ iArr7[(i107 >>> 16) & 255]) ^ iArr8[(i105 >>> 8) & 255]) ^ iArr9[i103 & 255];
        int i118 = i115 + 1;
        int i119 = i117 ^ iArr10[i115];
        int i120 = i118 + 1;
        int i121 = iArr10[i118] ^ (((iArr6[i112 >>> 24] ^ iArr7[(i119 >>> 16) & 255]) ^ iArr8[(i116 >>> 8) & 255]) ^ iArr9[i114 & 255]);
        int i122 = i120 + 1;
        int i123 = (((iArr6[i114 >>> 24] ^ iArr7[(i112 >>> 16) & 255]) ^ iArr8[(i119 >>> 8) & 255]) ^ iArr9[i116 & 255]) ^ iArr10[i120];
        int i124 = (((iArr6[i116 >>> 24] ^ iArr7[(i114 >>> 16) & 255]) ^ iArr8[(i112 >>> 8) & 255]) ^ iArr9[i119 & 255]) ^ iArr10[i122];
        int i125 = (((iArr6[i119 >>> 24] ^ iArr7[(i116 >>> 16) & 255]) ^ iArr8[(i114 >>> 8) & 255]) ^ iArr9[i112 & 255]) ^ iArr10[i122 + 1];
        int i126 = iArr10[0];
        int i127 = i2 + 1;
        byte[] bArr3 = f4189j;
        bArr2[i2] = (byte) (bArr3[i121 >>> 24] ^ (i126 >>> 24));
        int i128 = i127 + 1;
        bArr2[i127] = (byte) (bArr3[(i125 >>> 16) & 255] ^ (i126 >>> 16));
        int i129 = i128 + 1;
        bArr2[i128] = (byte) (bArr3[(i124 >>> 8) & 255] ^ (i126 >>> 8));
        int i130 = i129 + 1;
        bArr2[i129] = (byte) (i126 ^ bArr3[i123 & 255]);
        int i131 = iArr10[1];
        int i132 = i130 + 1;
        bArr2[i130] = (byte) (bArr3[i123 >>> 24] ^ (i131 >>> 24));
        int i133 = i132 + 1;
        bArr2[i132] = (byte) (bArr3[(i121 >>> 16) & 255] ^ (i131 >>> 16));
        int i134 = i133 + 1;
        bArr2[i133] = (byte) (bArr3[(i125 >>> 8) & 255] ^ (i131 >>> 8));
        int i135 = i134 + 1;
        bArr2[i134] = (byte) (i131 ^ bArr3[i124 & 255]);
        int i136 = iArr10[2];
        int i137 = i135 + 1;
        bArr2[i135] = (byte) (bArr3[i124 >>> 24] ^ (i136 >>> 24));
        int i138 = i137 + 1;
        bArr2[i137] = (byte) (bArr3[(i123 >>> 16) & 255] ^ (i136 >>> 16));
        int i139 = i138 + 1;
        bArr2[i138] = (byte) (bArr3[(i121 >>> 8) & 255] ^ (i136 >>> 8));
        int i140 = i139 + 1;
        bArr2[i139] = (byte) (i136 ^ bArr3[i125 & 255]);
        int i141 = iArr10[3];
        int i142 = i140 + 1;
        bArr2[i140] = (byte) (bArr3[i125 >>> 24] ^ (i141 >>> 24));
        int i143 = i142 + 1;
        bArr2[i142] = (byte) (bArr3[(i124 >>> 16) & 255] ^ (i141 >>> 16));
        bArr2[i143] = (byte) (bArr3[(i123 >>> 8) & 255] ^ (i141 >>> 8));
        bArr2[i143 + 1] = (byte) (bArr3[i121 & 255] ^ i141);
    }
}
