package p001a.p002a.p003a.p004a.p005a.p008d.p013h;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* renamed from: a.a.a.a.a.d.h.a */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0120a {

    /* renamed from: a */
    public static final int f139a = 1;

    /* renamed from: b */
    public static final int f140b = 0;

    /* renamed from: c */
    public static final byte[] f141c = {-42, -112, -23, -2, -52, -31, 61, -73, 22, -74, 20, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, 15, 75, 112, 86, -99, 53, 30, 36, 14, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, C0548c.f1784h, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, 52, 26, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, 13, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, -35, -68, Byte.MAX_VALUE, 17, -39, C0548c.f1785i, 65, 31, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, 18, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};

    /* renamed from: d */
    public static final int[] f142d = {-1548633402, 1453994832, 1736282519, -1301273892};

    /* renamed from: e */
    public static final int[] f143e = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    /* renamed from: a */
    private long m24210a(byte[] bArr, int i) {
        return (bArr[i + 3] & 255 & 4294967295L) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    /* renamed from: b */
    private long m24206b(long j) {
        byte[] bArr = new byte[4];
        m24213a(j, bArr, 0);
        long m24210a = m24210a(new byte[]{m24217a(bArr[0]), m24217a(bArr[1]), m24217a(bArr[2]), m24217a(bArr[3])}, 0);
        return (((m24215a(m24210a, 2) ^ m24210a) ^ m24215a(m24210a, 10)) ^ m24215a(m24210a, 18)) ^ m24215a(m24210a, 24);
    }

    /* renamed from: b */
    private long m24205b(long j, int i) {
        return (j & (-1)) << i;
    }

    /* renamed from: c */
    public void m24202c(C0122c c0122c, byte[] bArr) {
        if (c0122c != null) {
            if (bArr != null && bArr.length == 16) {
                c0122c.f147a = 1;
                m24208a(c0122c.f148b, bArr);
                return;
            }
            throw new Exception("key error!");
        }
        throw new Exception("ctx is null!");
    }

    /* renamed from: a */
    private void m24213a(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((j >> 24) & 255);
        bArr[i + 1] = (byte) ((j >> 16) & 255);
        bArr[i + 2] = (byte) ((j >> 8) & 255);
        bArr[i + 3] = (byte) (j & 255);
    }

    /* renamed from: a */
    private long m24215a(long j, int i) {
        return (j >> (32 - i)) | m24205b(j, i);
    }

    /* renamed from: a */
    private void m24209a(long[] jArr, int i) {
        long j = jArr[i];
        int i2 = 31 - i;
        jArr[i] = jArr[i2];
        jArr[i2] = j;
    }

    /* renamed from: a */
    private byte m24217a(byte b) {
        return f141c[b & 255];
    }

    /* renamed from: b */
    private byte[] m24203b(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        if (i == 1) {
            int length = 16 - (bArr.length % 16);
            byte[] bArr2 = new byte[bArr.length + length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            for (int i2 = 0; i2 < length; i2++) {
                bArr2[bArr.length + i2] = (byte) length;
            }
            return bArr2;
        }
        int i3 = bArr[bArr.length - 1];
        byte[] bArr3 = new byte[bArr.length - i3];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length - i3);
        return bArr3;
    }

    /* renamed from: a */
    private long m24214a(long j, long j2, long j3, long j4, long j5) {
        return j ^ m24206b(((j2 ^ j3) ^ j4) ^ j5);
    }

    /* renamed from: a */
    private long m24216a(long j) {
        byte[] bArr = new byte[4];
        m24213a(j, bArr, 0);
        long m24210a = m24210a(new byte[]{m24217a(bArr[0]), m24217a(bArr[1]), m24217a(bArr[2]), m24217a(bArr[3])}, 0);
        return m24215a(m24210a, 23) ^ (m24215a(m24210a, 13) ^ m24210a);
    }

    /* renamed from: a */
    private void m24208a(long[] jArr, byte[] bArr) {
        long[] jArr2 = new long[36];
        int i = 0;
        long[] jArr3 = {m24210a(bArr, 0), m24210a(bArr, 4), m24210a(bArr, 8), m24210a(bArr, 12)};
        long j = jArr3[0];
        int[] iArr = f142d;
        jArr2[0] = j ^ iArr[0];
        jArr2[1] = jArr3[1] ^ iArr[1];
        jArr2[2] = jArr3[2] ^ iArr[2];
        jArr2[3] = jArr3[3] ^ iArr[3];
        while (i < 32) {
            int i2 = i + 4;
            int i3 = i + 1;
            jArr2[i2] = jArr2[i] ^ m24216a(((jArr2[i3] ^ jArr2[i + 2]) ^ jArr2[i + 3]) ^ f143e[i]);
            jArr[i] = jArr2[i2];
            i = i3;
        }
    }

    /* renamed from: b */
    public void m24204b(C0122c c0122c, byte[] bArr) {
        if (c0122c != null) {
            if (bArr != null && bArr.length == 16) {
                c0122c.f147a = 0;
                m24208a(c0122c.f148b, bArr);
                for (int i = 0; i < 16; i++) {
                    m24209a(c0122c.f148b, i);
                }
                return;
            }
            throw new Exception("key error!");
        }
        throw new Exception("ctx is null!");
    }

    /* renamed from: a */
    private void m24207a(long[] jArr, byte[] bArr, byte[] bArr2) {
        long[] jArr2 = new long[36];
        jArr2[0] = m24210a(bArr, 0);
        jArr2[1] = m24210a(bArr, 4);
        jArr2[2] = m24210a(bArr, 8);
        jArr2[3] = m24210a(bArr, 12);
        int i = 0;
        while (i < 32) {
            int i2 = i + 1;
            jArr2[i + 4] = m24214a(jArr2[i], jArr2[i2], jArr2[i + 2], jArr2[i + 3], jArr[i]);
            i = i2;
        }
        m24213a(jArr2[35], bArr2, 0);
        m24213a(jArr2[34], bArr2, 4);
        m24213a(jArr2[33], bArr2, 8);
        m24213a(jArr2[32], bArr2, 12);
    }

    /* renamed from: a */
    public byte[] m24212a(C0122c c0122c, byte[] bArr) {
        if (bArr != null) {
            if (c0122c.f149c && c0122c.f147a == 1) {
                bArr = m24203b(bArr, 1);
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int length = bArr.length; length > 0; length -= 16) {
                byte[] bArr2 = new byte[16];
                byte[] bArr3 = new byte[16];
                byteArrayInputStream.read(bArr2);
                m24207a(c0122c.f148b, bArr2, bArr3);
                byteArrayOutputStream.write(bArr3);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (c0122c.f149c && c0122c.f147a == 0) {
                byteArray = m24203b(byteArray, 0);
            }
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
        }
        throw new Exception("input is null!");
    }

    /* renamed from: a */
    public byte[] m24211a(C0122c c0122c, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new Exception("iv error!");
        }
        if (bArr2 != null) {
            if (c0122c.f149c && c0122c.f147a == 1) {
                bArr2 = m24203b(bArr2, 1);
            }
            int length = bArr2.length;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (c0122c.f147a == 1) {
                while (length > 0) {
                    byte[] bArr3 = new byte[16];
                    byte[] bArr4 = new byte[16];
                    byte[] bArr5 = new byte[16];
                    byteArrayInputStream.read(bArr3);
                    for (int i = 0; i < 16; i++) {
                        bArr4[i] = (byte) (bArr3[i] ^ bArr[i]);
                    }
                    m24207a(c0122c.f148b, bArr4, bArr5);
                    System.arraycopy(bArr5, 0, bArr, 0, 16);
                    byteArrayOutputStream.write(bArr5);
                    length -= 16;
                }
            } else {
                byte[] bArr6 = new byte[16];
                while (length > 0) {
                    byte[] bArr7 = new byte[16];
                    byte[] bArr8 = new byte[16];
                    byte[] bArr9 = new byte[16];
                    byteArrayInputStream.read(bArr7);
                    System.arraycopy(bArr7, 0, bArr6, 0, 16);
                    m24207a(c0122c.f148b, bArr7, bArr8);
                    for (int i2 = 0; i2 < 16; i2++) {
                        bArr9[i2] = (byte) (bArr8[i2] ^ bArr[i2]);
                    }
                    System.arraycopy(bArr6, 0, bArr, 0, 16);
                    byteArrayOutputStream.write(bArr9);
                    length -= 16;
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (c0122c.f149c && c0122c.f147a == 0) {
                byteArray = m24203b(byteArray, 0);
            }
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
        }
        throw new Exception("input is null!");
    }
}
