package com.alipay.sdk.encrypt;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.encrypt.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2011a {

    /* renamed from: a */
    private static final int f3754a = 128;

    /* renamed from: b */
    private static final int f3755b = 64;

    /* renamed from: c */
    private static final int f3756c = 24;

    /* renamed from: d */
    private static final int f3757d = 8;

    /* renamed from: e */
    private static final int f3758e = 16;

    /* renamed from: f */
    private static final int f3759f = 4;

    /* renamed from: g */
    private static final int f3760g = -128;

    /* renamed from: h */
    private static final char f3761h = '=';

    /* renamed from: i */
    private static final byte[] f3762i = new byte[128];

    /* renamed from: j */
    private static final char[] f3763j = new char[64];

    /* renamed from: a */
    private static boolean m20844a(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    /* renamed from: b */
    private static boolean m20840b(char c) {
        return c == '=';
    }

    static {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 128; i4++) {
            f3762i[i4] = -1;
        }
        for (int i5 = 90; i5 >= 65; i5--) {
            f3762i[i5] = (byte) (i5 - 65);
        }
        int i6 = 122;
        while (true) {
            i = 26;
            if (i6 < 97) {
                break;
            }
            f3762i[i6] = (byte) ((i6 - 97) + 26);
            i6--;
        }
        int i7 = 57;
        while (true) {
            i2 = 52;
            if (i7 < 48) {
                break;
            }
            f3762i[i7] = (byte) ((i7 - 48) + 52);
            i7--;
        }
        byte[] bArr = f3762i;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i8 = 0; i8 <= 25; i8++) {
            f3763j[i8] = (char) (i8 + 65);
        }
        int i9 = 0;
        while (i <= 51) {
            f3763j[i] = (char) (i9 + 97);
            i++;
            i9++;
        }
        while (i2 <= 61) {
            f3763j[i2] = (char) (i3 + 48);
            i2++;
            i3++;
        }
        char[] cArr = f3763j;
        cArr[62] = '+';
        cArr[63] = '/';
    }

    /* renamed from: c */
    private static boolean m20839c(char c) {
        return c < 128 && f3762i[c] != -1;
    }

    /* renamed from: a */
    public static String m20842a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2) {
            int i6 = i4 + 1;
            byte b = bArr[i4];
            int i7 = i6 + 1;
            byte b2 = bArr[i6];
            int i8 = i7 + 1;
            byte b3 = bArr[i7];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            byte b6 = (byte) ((b & Byte.MIN_VALUE) == 0 ? b >> 2 : (b >> 2) ^ 192);
            byte b7 = (byte) ((b2 & Byte.MIN_VALUE) == 0 ? b2 >> 4 : (b2 >> 4) ^ 240);
            int i9 = (b3 & Byte.MIN_VALUE) == 0 ? b3 >> 6 : (b3 >> 6) ^ 252;
            int i10 = i5 + 1;
            char[] cArr2 = f3763j;
            cArr[i5] = cArr2[b6];
            int i11 = i10 + 1;
            cArr[i10] = cArr2[(b5 << 4) | b7];
            int i12 = i11 + 1;
            cArr[i11] = cArr2[(b4 << 2) | ((byte) i9)];
            cArr[i12] = cArr2[b3 & 63];
            i3++;
            i5 = i12 + 1;
            i4 = i8;
        }
        if (i == 8) {
            byte b8 = bArr[i4];
            byte b9 = (byte) (b8 & 3);
            int i13 = (b8 & Byte.MIN_VALUE) == 0 ? b8 >> 2 : (b8 >> 2) ^ 192;
            int i14 = i5 + 1;
            char[] cArr3 = f3763j;
            cArr[i5] = cArr3[(byte) i13];
            int i15 = i14 + 1;
            cArr[i14] = cArr3[b9 << 4];
            cArr[i15] = '=';
            cArr[i15 + 1] = '=';
        } else if (i == 16) {
            byte b10 = bArr[i4];
            byte b11 = bArr[i4 + 1];
            byte b12 = (byte) (b11 & 15);
            byte b13 = (byte) (b10 & 3);
            byte b14 = (byte) ((b10 & Byte.MIN_VALUE) == 0 ? b10 >> 2 : (b10 >> 2) ^ 192);
            int i16 = (b11 & Byte.MIN_VALUE) == 0 ? b11 >> 4 : (b11 >> 4) ^ 240;
            int i17 = i5 + 1;
            char[] cArr4 = f3763j;
            cArr[i5] = cArr4[b14];
            int i18 = i17 + 1;
            cArr[i17] = cArr4[((byte) i16) | (b13 << 4)];
            cArr[i18] = cArr4[b12 << 2];
            cArr[i18 + 1] = '=';
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static byte[] m20843a(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int m20841a = m20841a(charArray);
        if (m20841a % 4 != 0) {
            return null;
        }
        int i = m20841a / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i * 3];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i - 1) {
            int i5 = i3 + 1;
            char c = charArray[i3];
            if (m20839c(c)) {
                int i6 = i5 + 1;
                char c2 = charArray[i5];
                if (m20839c(c2)) {
                    int i7 = i6 + 1;
                    char c3 = charArray[i6];
                    if (m20839c(c3)) {
                        int i8 = i7 + 1;
                        char c4 = charArray[i7];
                        if (m20839c(c4)) {
                            byte[] bArr2 = f3762i;
                            byte b = bArr2[c];
                            byte b2 = bArr2[c2];
                            byte b3 = bArr2[c3];
                            byte b4 = bArr2[c4];
                            int i9 = i4 + 1;
                            bArr[i4] = (byte) ((b << 2) | (b2 >> 4));
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
                            i4 = i10 + 1;
                            bArr[i10] = (byte) ((b3 << 6) | b4);
                            i2++;
                            i3 = i8;
                        }
                    }
                }
            }
            return null;
        }
        int i11 = i3 + 1;
        char c5 = charArray[i3];
        if (m20839c(c5)) {
            int i12 = i11 + 1;
            char c6 = charArray[i11];
            if (m20839c(c6)) {
                byte[] bArr3 = f3762i;
                byte b5 = bArr3[c5];
                byte b6 = bArr3[c6];
                int i13 = i12 + 1;
                char c7 = charArray[i12];
                char c8 = charArray[i13];
                if (!m20839c(c7) || !m20839c(c8)) {
                    if (m20840b(c7) && m20840b(c8)) {
                        if ((b6 & 15) != 0) {
                            return null;
                        }
                        int i14 = i2 * 3;
                        byte[] bArr4 = new byte[i14 + 1];
                        System.arraycopy(bArr, 0, bArr4, 0, i14);
                        bArr4[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                        return bArr4;
                    } else if (m20840b(c7) || !m20840b(c8)) {
                        return null;
                    } else {
                        byte b7 = f3762i[c7];
                        if ((b7 & 3) != 0) {
                            return null;
                        }
                        int i15 = i2 * 3;
                        byte[] bArr5 = new byte[i15 + 2];
                        System.arraycopy(bArr, 0, bArr5, 0, i15);
                        bArr5[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                        bArr5[i4 + 1] = (byte) (((b7 >> 2) & 15) | ((b6 & 15) << 4));
                        return bArr5;
                    }
                }
                byte[] bArr6 = f3762i;
                byte b8 = bArr6[c7];
                byte b9 = bArr6[c8];
                int i16 = i4 + 1;
                bArr[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                bArr[i16] = (byte) (((b6 & 15) << 4) | ((b8 >> 2) & 15));
                bArr[i16 + 1] = (byte) (b9 | (b8 << 6));
                return bArr;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static int m20841a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!m20844a(cArr[i2])) {
                cArr[i] = cArr[i2];
                i++;
            }
        }
        return i;
    }
}
