package com.heytap.mcssdk.p203a;

import java.math.BigInteger;
import org.apache.commons.codec.binary.StringUtils;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* renamed from: com.heytap.mcssdk.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4712a extends AbstractC4713b {

    /* renamed from: m */
    private static final int f10551m = 6;

    /* renamed from: n */
    private static final int f10552n = 3;

    /* renamed from: o */
    private static final int f10553o = 4;

    /* renamed from: s */
    private static final int f10557s = 63;

    /* renamed from: t */
    private final byte[] f10558t;

    /* renamed from: u */
    private final byte[] f10559u;

    /* renamed from: v */
    private final byte[] f10560v;

    /* renamed from: w */
    private final int f10561w;

    /* renamed from: x */
    private final int f10562x;

    /* renamed from: y */
    private int f10563y;

    /* renamed from: a */
    static final byte[] f10550a = {13, 10};

    /* renamed from: p */
    private static final byte[] f10554p = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 43, 47};

    /* renamed from: q */
    private static final byte[] f10555q = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 45, 95};

    /* renamed from: r */
    private static final byte[] f10556r = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, C0548c.f1784h, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public C4712a() {
        this(0);
    }

    public C4712a(int i) {
        this(i, f10550a);
    }

    public C4712a(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C4712a(int r5, byte[] r6, boolean r7) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L5
            r1 = r0
            goto L6
        L5:
            int r1 = r6.length
        L6:
            r2 = 4
            r3 = 3
            r4.<init>(r3, r2, r5, r1)
            byte[] r1 = com.heytap.mcssdk.p203a.C4712a.f10556r
            r4.f10559u = r1
            r1 = 0
            if (r6 == 0) goto L4a
            boolean r3 = r4.m15579l(r6)
            if (r3 != 0) goto L2a
            if (r5 <= 0) goto L4a
            int r5 = r6.length
            int r5 = r5 + r2
            r4.f10562x = r5
            int r5 = r6.length
            byte[] r5 = new byte[r5]
            r4.f10560v = r5
            byte[] r5 = r4.f10560v
            int r1 = r6.length
            java.lang.System.arraycopy(r6, r0, r5, r0, r1)
            goto L4e
        L2a:
            java.lang.String r5 = org.apache.commons.codec.binary.StringUtils.newStringUtf8(r6)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "lineSeparator must not contain base64 characters: ["
            r7.append(r0)
            r7.append(r5)
            java.lang.String r5 = "]"
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r6.<init>(r5)
            throw r6
        L4a:
            r4.f10562x = r2
            r4.f10560v = r1
        L4e:
            int r5 = r4.f10562x
            int r5 = r5 + (-1)
            r4.f10561w = r5
            if (r7 == 0) goto L59
            byte[] r5 = com.heytap.mcssdk.p203a.C4712a.f10555q
            goto L5b
        L59:
            byte[] r5 = com.heytap.mcssdk.p203a.C4712a.f10554p
        L5b:
            r4.f10558t = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.mcssdk.p203a.C4712a.<init>(int, byte[], boolean):void");
    }

    public C4712a(boolean z) {
        this(76, f10550a, z);
    }

    /* renamed from: a */
    public static boolean m15612a(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = f10556r;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m15611a(String str) {
        return m15603b(StringUtils.getBytesUtf8(str));
    }

    /* renamed from: a */
    public static boolean m15609a(byte[] bArr) {
        return m15603b(bArr);
    }

    /* renamed from: a */
    public static byte[] m15610a(BigInteger bigInteger) {
        if (bigInteger != null) {
            return m15608a(m15604b(bigInteger), false);
        }
        throw new NullPointerException("encodeInteger called with null parameter");
    }

    /* renamed from: a */
    public static byte[] m15608a(byte[] bArr, boolean z) {
        return m15607a(bArr, z, false);
    }

    /* renamed from: a */
    public static byte[] m15607a(byte[] bArr, boolean z, boolean z2) {
        return m15606a(bArr, z, z2, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static byte[] m15606a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        C4712a c4712a = z ? new C4712a(z2) : new C4712a(0, f10550a, z2);
        long m = c4712a.m15578m(bArr);
        if (m <= i) {
            return c4712a.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + m + ") than the specified maximum size of " + i);
    }

    /* renamed from: b */
    public static boolean m15603b(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!m15612a(bArr[i]) && !m15587c(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static byte[] m15605b(String str) {
        return new C4712a().m15586c(str);
    }

    /* renamed from: b */
    static byte[] m15604b(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 == 0 || (bigInteger.bitLength() / 8) + 1 != bitLength / 8) {
            int i = 0;
            int length = byteArray.length;
            if (bigInteger.bitLength() % 8 == 0) {
                length--;
                i = 1;
            }
            int i2 = bitLength / 8;
            int i3 = i2 - length;
            byte[] bArr = new byte[i2];
            System.arraycopy(byteArray, i, bArr, i3, length);
            return bArr;
        }
        return byteArray;
    }

    /* renamed from: c */
    public static byte[] m15602c(byte[] bArr) {
        return m15608a(bArr, false);
    }

    /* renamed from: d */
    public static String m15601d(byte[] bArr) {
        return StringUtils.newStringUtf8(m15608a(bArr, false));
    }

    /* renamed from: e */
    public static byte[] m15600e(byte[] bArr) {
        return m15607a(bArr, false, true);
    }

    /* renamed from: f */
    public static String m15599f(byte[] bArr) {
        return StringUtils.newStringUtf8(m15607a(bArr, false, true));
    }

    /* renamed from: g */
    public static byte[] m15598g(byte[] bArr) {
        return m15608a(bArr, true);
    }

    /* renamed from: h */
    public static byte[] m15597h(byte[] bArr) {
        return new C4712a().decode(bArr);
    }

    /* renamed from: i */
    public static BigInteger m15596i(byte[] bArr) {
        return new BigInteger(1, m15597h(bArr));
    }

    @Override // com.heytap.mcssdk.p203a.AbstractC4713b
    /* renamed from: a */
    void mo15593a(byte[] bArr, int i, int i2) {
        if (this.f10574j) {
            return;
        }
        if (i2 >= 0) {
            int i3 = i;
            int i4 = 0;
            while (i4 < i2) {
                m15594a(this.f10562x);
                this.f10576l = (this.f10576l + 1) % 3;
                int i5 = i3 + 1;
                int i6 = bArr[i3];
                if (i6 < 0) {
                    i6 += 256;
                }
                this.f10563y = (this.f10563y << 8) + i6;
                if (this.f10576l == 0) {
                    byte[] bArr2 = this.f10572h;
                    int i7 = this.f10573i;
                    this.f10573i = i7 + 1;
                    bArr2[i7] = this.f10558t[(this.f10563y >> 18) & 63];
                    byte[] bArr3 = this.f10572h;
                    int i8 = this.f10573i;
                    this.f10573i = i8 + 1;
                    bArr3[i8] = this.f10558t[(this.f10563y >> 12) & 63];
                    byte[] bArr4 = this.f10572h;
                    int i9 = this.f10573i;
                    this.f10573i = i9 + 1;
                    bArr4[i9] = this.f10558t[(this.f10563y >> 6) & 63];
                    byte[] bArr5 = this.f10572h;
                    int i10 = this.f10573i;
                    this.f10573i = i10 + 1;
                    bArr5[i10] = this.f10558t[this.f10563y & 63];
                    this.f10575k += 4;
                    if (this.f10571g > 0 && this.f10571g <= this.f10575k) {
                        System.arraycopy(this.f10560v, 0, this.f10572h, this.f10573i, this.f10560v.length);
                        this.f10573i += this.f10560v.length;
                        this.f10575k = 0;
                    }
                }
                i4++;
                i3 = i5;
            }
            return;
        }
        this.f10574j = true;
        if (this.f10576l == 0 && this.f10571g == 0) {
            return;
        }
        m15594a(this.f10562x);
        int i11 = this.f10573i;
        switch (this.f10576l) {
            case 1:
                byte[] bArr6 = this.f10572h;
                int i12 = this.f10573i;
                this.f10573i = i12 + 1;
                bArr6[i12] = this.f10558t[(this.f10563y >> 2) & 63];
                byte[] bArr7 = this.f10572h;
                int i13 = this.f10573i;
                this.f10573i = i13 + 1;
                byte[] bArr8 = this.f10558t;
                bArr7[i13] = bArr8[(this.f10563y << 4) & 63];
                if (bArr8 == f10554p) {
                    byte[] bArr9 = this.f10572h;
                    int i14 = this.f10573i;
                    this.f10573i = i14 + 1;
                    bArr9[i14] = 61;
                    byte[] bArr10 = this.f10572h;
                    int i15 = this.f10573i;
                    this.f10573i = i15 + 1;
                    bArr10[i15] = 61;
                    break;
                }
                break;
            case 2:
                byte[] bArr11 = this.f10572h;
                int i16 = this.f10573i;
                this.f10573i = i16 + 1;
                bArr11[i16] = this.f10558t[(this.f10563y >> 10) & 63];
                byte[] bArr12 = this.f10572h;
                int i17 = this.f10573i;
                this.f10573i = i17 + 1;
                bArr12[i17] = this.f10558t[(this.f10563y >> 4) & 63];
                byte[] bArr13 = this.f10572h;
                int i18 = this.f10573i;
                this.f10573i = i18 + 1;
                byte[] bArr14 = this.f10558t;
                bArr13[i18] = bArr14[(this.f10563y << 2) & 63];
                if (bArr14 == f10554p) {
                    byte[] bArr15 = this.f10572h;
                    int i19 = this.f10573i;
                    this.f10573i = i19 + 1;
                    bArr15[i19] = 61;
                    break;
                }
                break;
        }
        this.f10575k += this.f10573i - i11;
        if (this.f10571g <= 0 || this.f10575k <= 0) {
            return;
        }
        System.arraycopy(this.f10560v, 0, this.f10572h, this.f10573i, this.f10560v.length);
        this.f10573i += this.f10560v.length;
    }

    /* renamed from: a */
    public boolean m15613a() {
        return this.f10558t == f10555q;
    }

    @Override // com.heytap.mcssdk.p203a.AbstractC4713b
    /* renamed from: b */
    void mo15590b(byte[] bArr, int i, int i2) {
        byte b;
        if (this.f10574j) {
            return;
        }
        if (i2 < 0) {
            this.f10574j = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            m15594a(this.f10561w);
            int i4 = i + 1;
            byte b2 = bArr[i];
            if (b2 == 61) {
                this.f10574j = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = f10556r;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    this.f10576l = (this.f10576l + 1) % 4;
                    this.f10563y = (this.f10563y << 6) + b;
                    if (this.f10576l == 0) {
                        byte[] bArr3 = this.f10572h;
                        int i5 = this.f10573i;
                        this.f10573i = i5 + 1;
                        bArr3[i5] = (byte) ((this.f10563y >> 16) & 255);
                        byte[] bArr4 = this.f10572h;
                        int i6 = this.f10573i;
                        this.f10573i = i6 + 1;
                        bArr4[i6] = (byte) ((this.f10563y >> 8) & 255);
                        byte[] bArr5 = this.f10572h;
                        int i7 = this.f10573i;
                        this.f10573i = i7 + 1;
                        bArr5[i7] = (byte) (this.f10563y & 255);
                    }
                }
            }
            i3++;
            i = i4;
        }
        if (!this.f10574j || this.f10576l == 0) {
            return;
        }
        m15594a(this.f10561w);
        switch (this.f10576l) {
            case 2:
                this.f10563y >>= 4;
                byte[] bArr6 = this.f10572h;
                int i8 = this.f10573i;
                this.f10573i = i8 + 1;
                bArr6[i8] = (byte) (this.f10563y & 255);
                return;
            case 3:
                this.f10563y >>= 2;
                byte[] bArr7 = this.f10572h;
                int i9 = this.f10573i;
                this.f10573i = i9 + 1;
                bArr7[i9] = (byte) ((this.f10563y >> 8) & 255);
                byte[] bArr8 = this.f10572h;
                int i10 = this.f10573i;
                this.f10573i = i10 + 1;
                bArr8[i10] = (byte) (this.f10563y & 255);
                return;
            default:
                return;
        }
    }

    @Override // com.heytap.mcssdk.p203a.AbstractC4713b
    /* renamed from: b */
    protected boolean mo15591b(byte b) {
        if (b >= 0) {
            byte[] bArr = this.f10559u;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
