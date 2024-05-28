package p001a.p002a.p003a.p004a.p005a.p008d.p012g;

import p001a.p002a.p003a.p004a.p005a.p008d.C0112e;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.d.g.a */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C0118a {

    /* renamed from: a */
    public static final byte[] f130a = {115, Byte.MIN_VALUE, 22, 111, 73, 20, -78, -71, 23, 36, 66, -41, -38, -118, 6, 0, -87, 111, 48, -68, 22, 49, 56, -86, -29, -115, -18, 77, -80, -5, 14, 78};

    /* renamed from: b */
    public static int[] f131b = new int[64];

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            f131b[i2] = 2043430169;
            i2++;
        }
        for (i = 16; i < 64; i++) {
            f131b[i] = 2055708042;
        }
    }

    /* renamed from: a */
    public static int m24244a(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: a */
    public static byte[] m24240a(byte[] bArr, byte[] bArr2) {
        return m24239a(m24238a(m24228c(bArr), m24228c(bArr2)));
    }

    /* renamed from: b */
    public static int m24236b(int i, int i2) {
        return (i >> (32 - i2)) | (i << i2);
    }

    /* renamed from: b */
    public static int m24235b(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: b */
    public static int[][] m24231b(int[] iArr) {
        int[] iArr2 = new int[68];
        int[] iArr3 = new int[64];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[i];
        }
        for (int i2 = 16; i2 < 68; i2++) {
            iArr2[i2] = (m24237b((iArr2[i2 - 16] ^ iArr2[i2 - 9]) ^ m24245a(iArr2[i2 - 3], 15)) ^ m24245a(iArr2[i2 - 13], 7)) ^ iArr2[i2 - 6];
        }
        for (int i3 = 0; i3 < 64; i3++) {
            iArr3[i3] = iArr2[i3] ^ iArr2[i3 + 4];
        }
        return new int[][]{iArr2, iArr3};
    }

    /* renamed from: c */
    public static int m24229c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: c */
    public static int[] m24228c(byte[] bArr) {
        int[] iArr = new int[bArr.length / 4];
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < bArr.length; i += 4) {
            System.arraycopy(bArr, i, bArr2, 0, 4);
            iArr[i / 4] = m24233b(bArr2);
        }
        return iArr;
    }

    /* renamed from: d */
    public static int m24226d(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: a */
    public static byte[] m24239a(int[] iArr) {
        byte[] bArr = new byte[iArr.length * 4];
        for (int i = 0; i < iArr.length; i++) {
            System.arraycopy(m24230c(iArr[i]), 0, bArr, i * 4, 4);
        }
        return bArr;
    }

    /* renamed from: c */
    public static byte[] m24230c(int i) {
        return m24242a(C0112e.m24288b(i));
    }

    /* renamed from: c */
    public static byte[] m24227c(byte[] bArr, int i) {
        int length = 448 - (((bArr.length * 8) + 1) % 512);
        if (length < 0) {
            length = 960 - (((bArr.length * 8) + 1) % 512);
        }
        int i2 = (length + 1) / 8;
        byte[] bArr2 = new byte[i2];
        bArr2[0] = Byte.MIN_VALUE;
        long length2 = (bArr.length * 8) + (i * 512);
        byte[] bArr3 = new byte[bArr.length + i2 + 8];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        int length3 = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr3, length3, i2);
        int i3 = length3 + i2;
        byte[] m24242a = m24242a(C0112e.m24298a(length2));
        System.arraycopy(m24242a, 0, bArr3, i3, m24242a.length);
        return bArr3;
    }

    /* renamed from: a */
    public static int[] m24238a(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int[][] m24231b = m24231b(iArr2);
        int[] iArr3 = m24231b[0];
        int[] iArr4 = m24231b[1];
        int i9 = i;
        int i10 = 0;
        while (i10 < 64) {
            int m24245a = m24245a(m24245a(i9, 12) + i5 + m24245a(f131b[i10], i10), 7);
            int m24243a = m24243a(i9, i2, i3, i10) + i4;
            int m24234b = m24234b(i5, i6, i7, i10) + i8 + m24245a + iArr3[i10];
            int m24245a2 = m24245a(i2, 9);
            int m24245a3 = m24245a(i6, 19);
            i10++;
            i6 = i5;
            i5 = m24246a(m24234b);
            i8 = i7;
            i7 = m24245a3;
            int i11 = i3;
            i3 = m24245a2;
            i2 = i9;
            i9 = m24243a + (m24245a(i9, 12) ^ m24245a) + iArr4[i10];
            i4 = i11;
        }
        return new int[]{iArr[0] ^ i9, iArr[1] ^ i2, iArr[2] ^ i3, iArr[3] ^ i4, iArr[4] ^ i5, iArr[5] ^ i6, iArr[6] ^ i7, iArr[7] ^ i8};
    }

    /* renamed from: b */
    public static int m24233b(byte[] bArr) {
        return C0112e.m24280c(m24242a(bArr));
    }

    /* renamed from: b */
    public static int m24234b(int i, int i2, int i3, int i4) {
        if (i4 >= 0 && i4 <= 15) {
            return m24229c(i, i2, i3);
        }
        return m24226d(i, i2, i3);
    }

    /* renamed from: b */
    public static int m24237b(int i) {
        return m24245a(i, 23) ^ (m24245a(i, 15) ^ i);
    }

    /* renamed from: b */
    public static byte[] m24232b(byte[] bArr, int i) {
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, i, bArr2, 0, bArr.length - i);
        System.arraycopy(bArr, 0, bArr2, bArr.length - i, i);
        return bArr2;
    }

    /* renamed from: a */
    public static int m24243a(int i, int i2, int i3, int i4) {
        if (i4 >= 0 && i4 <= 15) {
            return m24244a(i, i2, i3);
        }
        return m24235b(i, i2, i3);
    }

    /* renamed from: a */
    public static int m24246a(int i) {
        m24236b(i, 9);
        int m24245a = m24245a(i, 9);
        m24236b(i, 17);
        return (i ^ m24245a) ^ m24245a(i, 17);
    }

    /* renamed from: a */
    public static byte[] m24242a(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[(length - i) - 1];
        }
        return bArr2;
    }

    /* renamed from: a */
    public static int m24245a(int i, int i2) {
        int i3 = i2 % 32;
        byte[] m24230c = m24230c(i);
        int i4 = i3 / 8;
        int i5 = i3 % 8;
        if (i4 > 0) {
            m24230c = m24232b(m24230c, i4);
        }
        if (i5 > 0) {
            m24230c = m24241a(m24230c, i5);
        }
        return m24233b(m24230c);
    }

    /* renamed from: a */
    public static byte[] m24241a(byte[] bArr, int i) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            bArr2[i2] = (byte) (((byte) ((bArr[i2] & 255) << i)) | ((byte) ((bArr[i3 % length] & 255) >> (8 - i))));
            i2 = i3;
        }
        return bArr2;
    }
}
