package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C0669a {
    /* renamed from: a */
    public static boolean m22478a(boolean[] zArr, boolean[] zArr2) {
        if (zArr == zArr2) {
            return true;
        }
        if (zArr == null || zArr2 == null || zArr.length != zArr2.length) {
            return false;
        }
        for (int i = 0; i != zArr.length; i++) {
            if (zArr[i] != zArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static void m22469b(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = i;
        }
    }

    /* renamed from: c */
    public static boolean m22466c(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    /* renamed from: b */
    public static int m22472b(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ bArr[length];
        }
    }

    /* renamed from: a */
    public static boolean m22493a(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i = 0; i != cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static int m22470b(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ iArr[length];
        }
    }

    /* renamed from: a */
    public static boolean m22499a(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i != bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static int m22467b(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        int length = sArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ (sArr[length] & 255);
        }
    }

    /* renamed from: a */
    public static boolean m22489a(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i = 0; i != iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static int m22468b(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return 0;
        }
        int length = bigIntegerArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ bigIntegerArr[length].hashCode();
        }
    }

    /* renamed from: b */
    public static byte[] m22471b(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            if (bArr2 != null) {
                return m22503a(bArr2);
            }
            return m22503a(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* renamed from: a */
    public static boolean m22485a(long[] jArr, long[] jArr2) {
        if (jArr == jArr2) {
            return true;
        }
        if (jArr == null || jArr2 == null || jArr.length != jArr2.length) {
            return false;
        }
        for (int i = 0; i != jArr.length; i++) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m22481a(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        if (bigIntegerArr == bigIntegerArr2) {
            return true;
        }
        if (bigIntegerArr == null || bigIntegerArr2 == null || bigIntegerArr.length != bigIntegerArr2.length) {
            return false;
        }
        for (int i = 0; i != bigIntegerArr.length; i++) {
            if (!bigIntegerArr[i].equals(bigIntegerArr2[i])) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static void m22502a(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = b;
        }
    }

    /* renamed from: a */
    public static void m22495a(char[] cArr, char c) {
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = c;
        }
    }

    /* renamed from: a */
    public static void m22486a(long[] jArr, long j) {
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = j;
        }
    }

    /* renamed from: a */
    public static void m22479a(short[] sArr, short s) {
        for (int i = 0; i < sArr.length; i++) {
            sArr[i] = s;
        }
    }

    /* renamed from: a */
    public static int m22496a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ cArr[length];
        }
    }

    /* renamed from: a */
    public static int m22476a(int[][] iArr) {
        int i = 0;
        for (int i2 = 0; i2 != iArr.length; i2++) {
            i = (i * 257) + m22470b(iArr[i2]);
        }
        return i;
    }

    /* renamed from: a */
    public static int m22473a(short[][][] sArr) {
        int i = 0;
        for (int i2 = 0; i2 != sArr.length; i2++) {
            i = (i * 257) + m22475a(sArr[i2]);
        }
        return i;
    }

    /* renamed from: a */
    public static int m22475a(short[][] sArr) {
        int i = 0;
        for (int i2 = 0; i2 != sArr.length; i2++) {
            i = (i * 257) + m22467b(sArr[i2]);
        }
        return i;
    }

    /* renamed from: a */
    public static byte[] m22503a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* renamed from: a */
    public static byte[][] m22477a(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[][] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = m22503a(bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: a */
    public static byte[][][] m22474a(byte[][][] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[][][] bArr2 = new byte[length][];
        for (int i = 0; i != length; i++) {
            bArr2[i] = m22477a(bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: a */
    public static int[] m22492a(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    /* renamed from: a */
    public static short[] m22480a(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        short[] sArr2 = new short[sArr.length];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    /* renamed from: a */
    public static BigInteger[] m22484a(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[bigIntegerArr.length];
        System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        return bigIntegerArr2;
    }

    /* renamed from: a */
    public static byte[] m22501a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i < bArr.length) {
            System.arraycopy(bArr, 0, bArr2, 0, i);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        return bArr2;
    }

    /* renamed from: a */
    public static char[] m22494a(char[] cArr, int i) {
        char[] cArr2 = new char[i];
        if (i < cArr.length) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        } else {
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        }
        return cArr2;
    }

    /* renamed from: a */
    public static int[] m22491a(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        if (i < iArr.length) {
            System.arraycopy(iArr, 0, iArr2, 0, i);
        } else {
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        }
        return iArr2;
    }

    /* renamed from: a */
    public static long[] m22488a(long[] jArr, int i) {
        long[] jArr2 = new long[i];
        if (i < jArr.length) {
            System.arraycopy(jArr, 0, jArr2, 0, i);
        } else {
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        }
        return jArr2;
    }

    /* renamed from: a */
    public static BigInteger[] m22483a(BigInteger[] bigIntegerArr, int i) {
        BigInteger[] bigIntegerArr2 = new BigInteger[i];
        if (i < bigIntegerArr.length) {
            System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, i);
        } else {
            System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, bigIntegerArr.length);
        }
        return bigIntegerArr2;
    }

    /* renamed from: a */
    public static byte[] m22500a(byte[] bArr, int i, int i2) {
        int m22504a = m22504a(i, i2);
        byte[] bArr2 = new byte[m22504a];
        if (bArr.length - i < m22504a) {
            System.arraycopy(bArr, i, bArr2, 0, bArr.length - i);
        } else {
            System.arraycopy(bArr, i, bArr2, 0, m22504a);
        }
        return bArr2;
    }

    /* renamed from: a */
    public static int[] m22490a(int[] iArr, int i, int i2) {
        int m22504a = m22504a(i, i2);
        int[] iArr2 = new int[m22504a];
        if (iArr.length - i < m22504a) {
            System.arraycopy(iArr, i, iArr2, 0, iArr.length - i);
        } else {
            System.arraycopy(iArr, i, iArr2, 0, m22504a);
        }
        return iArr2;
    }

    /* renamed from: a */
    public static long[] m22487a(long[] jArr, int i, int i2) {
        int m22504a = m22504a(i, i2);
        long[] jArr2 = new long[m22504a];
        if (jArr.length - i < m22504a) {
            System.arraycopy(jArr, i, jArr2, 0, jArr.length - i);
        } else {
            System.arraycopy(jArr, i, jArr2, 0, m22504a);
        }
        return jArr2;
    }

    /* renamed from: a */
    public static BigInteger[] m22482a(BigInteger[] bigIntegerArr, int i, int i2) {
        int m22504a = m22504a(i, i2);
        BigInteger[] bigIntegerArr2 = new BigInteger[m22504a];
        if (bigIntegerArr.length - i < m22504a) {
            System.arraycopy(bigIntegerArr, i, bigIntegerArr2, 0, bigIntegerArr.length - i);
        } else {
            System.arraycopy(bigIntegerArr, i, bigIntegerArr2, 0, m22504a);
        }
        return bigIntegerArr2;
    }

    /* renamed from: a */
    public static int m22504a(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            return i3;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(" > ");
        stringBuffer.append(i2);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    /* renamed from: a */
    public static byte[] m22498a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            if (bArr2 == null) {
                return m22471b(bArr, bArr3);
            }
            return m22471b(bArr, bArr2);
        }
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    /* renamed from: a */
    public static byte[] m22497a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr != null && bArr2 != null && bArr3 != null && bArr4 != null) {
            byte[] bArr5 = new byte[bArr.length + bArr2.length + bArr3.length + bArr4.length];
            System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
            System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
            System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
            return bArr5;
        } else if (bArr4 == null) {
            return m22498a(bArr, bArr2, bArr3);
        } else {
            if (bArr3 == null) {
                return m22498a(bArr, bArr2, bArr4);
            }
            if (bArr2 == null) {
                return m22498a(bArr, bArr3, bArr4);
            }
            return m22498a(bArr2, bArr3, bArr4);
        }
    }
}
