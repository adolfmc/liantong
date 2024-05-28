package org.p415a.p436e.p444c;

import java.math.BigInteger;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12893e {
    /* renamed from: a */
    public static int m644a(int i, int i2, int[] iArr, int i3) {
        long j = i2 & 4294967295L;
        int i4 = i3 + 0;
        long j2 = ((i & 4294967295L) * j) + (iArr[i4] & 4294967295L) + 0;
        iArr[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >>> 32) + j + (iArr[i5] & 4294967295L);
        iArr[i5] = (int) j3;
        long j4 = j3 >>> 32;
        int i6 = i3 + 2;
        long j5 = j4 + (iArr[i6] & 4294967295L);
        iArr[i6] = (int) j5;
        if ((j5 >>> 32) == 0) {
            return 0;
        }
        return AbstractC12891c.m704a(5, iArr, i3, 3);
    }

    /* renamed from: a */
    public static int m643a(int i, long j, int[] iArr, int i2) {
        long j2 = i & 4294967295L;
        long j3 = j & 4294967295L;
        int i3 = i2 + 0;
        long j4 = (j2 * j3) + (iArr[i3] & 4294967295L) + 0;
        iArr[i3] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int i4 = i2 + 1;
        long j7 = (j4 >>> 32) + j6 + (iArr[i4] & 4294967295L);
        iArr[i4] = (int) j7;
        int i5 = i2 + 2;
        long j8 = (j7 >>> 32) + j5 + (iArr[i5] & 4294967295L);
        iArr[i5] = (int) j8;
        int i6 = i2 + 3;
        long j9 = (j8 >>> 32) + (4294967295L & iArr[i6]);
        iArr[i6] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return AbstractC12891c.m704a(5, iArr, i2, 4);
    }

    /* renamed from: a */
    public static int m639a(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= 5) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    /* renamed from: a */
    public static int m637a(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L);
        iArr3[4] = (int) j5;
        return (int) (j5 >>> 32);
    }

    /* renamed from: a */
    public static long m642a(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = i & 4294967295L;
        long j2 = iArr[i2 + 0] & 4294967295L;
        long j3 = (j * j2) + (iArr2[i3 + 0] & 4294967295L) + 0;
        iArr3[i4 + 0] = (int) j3;
        long j4 = iArr[i2 + 1] & 4294967295L;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (iArr2[i3 + 1] & 4294967295L);
        iArr3[i4 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = iArr[i2 + 2] & 4294967295L;
        long j8 = j6 + (j * j7) + j4 + (iArr2[i3 + 2] & 4294967295L);
        iArr3[i4 + 2] = (int) j8;
        long j9 = iArr[i2 + 3] & 4294967295L;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (iArr2[i3 + 3] & 4294967295L);
        iArr3[i4 + 3] = (int) j10;
        long j11 = iArr[i2 + 4] & 4294967295L;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (4294967295L & iArr2[i3 + 4]);
        iArr3[i4 + 4] = (int) j12;
        return (j12 >>> 32) + j11;
    }

    /* renamed from: a */
    public static boolean m640a(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m638a(int[] iArr, int[] iArr2) {
        for (int i = 4; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static int[] m645a() {
        return new int[5];
    }

    /* renamed from: a */
    public static int[] m641a(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 160) {
            throw new IllegalArgumentException();
        }
        int[] m645a = m645a();
        int i = 0;
        while (bigInteger.signum() != 0) {
            m645a[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i++;
        }
        return m645a;
    }

    /* renamed from: b */
    public static int m635b(int i, int i2, int[] iArr, int i3) {
        int i4 = i3 + 0;
        long j = ((i2 & 4294967295L) * (i & 4294967295L)) + (iArr[i4] & 4294967295L) + 0;
        iArr[i4] = (int) j;
        int i5 = i3 + 1;
        long j2 = (j >>> 32) + (4294967295L & iArr[i5]);
        iArr[i5] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return AbstractC12891c.m704a(5, iArr, i3, 2);
    }

    /* renamed from: b */
    public static int m632b(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + (iArr3[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (iArr3[1] & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (iArr3[2] & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (iArr3[3] & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (iArr3[4] & 4294967295L);
        iArr3[4] = (int) j5;
        return (int) (j5 >>> 32);
    }

    /* renamed from: b */
    public static boolean m634b(int[] iArr) {
        for (int i = 0; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m633b(int[] iArr, int[] iArr2) {
        for (int i = 4; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static int[] m636b() {
        return new int[10];
    }

    /* renamed from: c */
    public static BigInteger m631c(int[] iArr) {
        byte[] bArr = new byte[20];
        for (int i = 0; i < 5; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                AbstractC12971e.m400a(i2, bArr, (4 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    /* renamed from: c */
    public static void m630c(int[] iArr, int[] iArr2) {
        long j = iArr[0] & 4294967295L;
        int i = 0;
        int i2 = 10;
        int i3 = 4;
        while (true) {
            int i4 = i3 - 1;
            long j2 = iArr[i3] & 4294967295L;
            long j3 = j2 * j2;
            int i5 = i2 - 1;
            iArr2[i5] = (i << 31) | ((int) (j3 >>> 33));
            i2 = i5 - 1;
            iArr2[i2] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | ((i6 << 31) & 4294967295L);
                iArr2[0] = (int) j4;
                long j6 = iArr[1] & 4294967295L;
                long j7 = j5 + (j6 * j);
                int i7 = (int) j7;
                iArr2[1] = (i7 << 1) | (((int) (j4 >>> 32)) & 1);
                long j8 = (iArr2[2] & 4294967295L) + (j7 >>> 32);
                long j9 = iArr[2] & 4294967295L;
                long j10 = j8 + (j9 * j);
                int i8 = (int) j10;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long j11 = (iArr2[3] & 4294967295L) + (j10 >>> 32) + (j9 * j6);
                long j12 = (iArr2[4] & 4294967295L) + (j11 >>> 32);
                long j13 = iArr[3] & 4294967295L;
                long j14 = (iArr2[5] & 4294967295L) + (j12 >>> 32);
                long j15 = (j11 & 4294967295L) + (j13 * j);
                int i9 = (int) j15;
                iArr2[3] = (i9 << 1) | (i8 >>> 31);
                long j16 = (j12 & 4294967295L) + (j15 >>> 32) + (j13 * j6);
                long j17 = (j14 & 4294967295L) + (j16 >>> 32) + (j13 * j9);
                long j18 = (iArr2[6] & 4294967295L) + (j14 >>> 32) + (j17 >>> 32);
                long j19 = iArr[4] & 4294967295L;
                long j20 = (iArr2[7] & 4294967295L) + (j18 >>> 32);
                long j21 = (j16 & 4294967295L) + (j19 * j);
                int i10 = (int) j21;
                iArr2[4] = (i9 >>> 31) | (i10 << 1);
                long j22 = (j21 >>> 32) + (j6 * j19) + (j17 & 4294967295L);
                long j23 = (j18 & 4294967295L) + (j22 >>> 32) + (j19 * j9);
                long j24 = (j20 & 4294967295L) + (j23 >>> 32) + (j19 * j13);
                long j25 = (iArr2[8] & 4294967295L) + (j20 >>> 32) + (j24 >>> 32);
                int i11 = (int) j22;
                iArr2[5] = (i10 >>> 31) | (i11 << 1);
                int i12 = (int) j23;
                iArr2[6] = (i11 >>> 31) | (i12 << 1);
                int i13 = (int) j24;
                iArr2[7] = (i13 << 1) | (i12 >>> 31);
                int i14 = (int) j25;
                iArr2[8] = (i13 >>> 31) | (i14 << 1);
                iArr2[9] = (i14 >>> 31) | ((iArr2[9] + ((int) (j25 >>> 32))) << 1);
                return;
            }
            i3 = i4;
            i = i6;
        }
    }

    /* renamed from: c */
    public static void m629c(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr2[0] & 4294967295L;
        int i = 1;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr[0] & 4294967295L;
        long j7 = (j6 * j) + 0;
        iArr3[0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        iArr3[1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j3);
        iArr3[2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j4);
        iArr3[3] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j5);
        iArr3[4] = (int) j11;
        iArr3[5] = (int) (j11 >>> 32);
        for (int i2 = 5; i < i2; i2 = 5) {
            long j12 = iArr[i] & 4294967295L;
            int i3 = i + 0;
            long j13 = (j12 * j) + (iArr3[i3] & 4294967295L) + 0;
            iArr3[i3] = (int) j13;
            int i4 = i + 1;
            long j14 = j2;
            long j15 = (j13 >>> 32) + (j12 * j2) + (iArr3[i4] & 4294967295L);
            iArr3[i4] = (int) j15;
            int i5 = i + 2;
            long j16 = j5;
            long j17 = (j15 >>> 32) + (j12 * j3) + (iArr3[i5] & 4294967295L);
            iArr3[i5] = (int) j17;
            int i6 = i + 3;
            long j18 = (j17 >>> 32) + (j12 * j4) + (iArr3[i6] & 4294967295L);
            iArr3[i6] = (int) j18;
            int i7 = i + 4;
            long j19 = (j18 >>> 32) + (j12 * j16) + (iArr3[i7] & 4294967295L);
            iArr3[i7] = (int) j19;
            iArr3[i + 5] = (int) (j19 >>> 32);
            i = i4;
            j5 = j16;
            j = j;
            j2 = j14;
        }
    }

    /* renamed from: d */
    public static int m627d(int[] iArr, int[] iArr2) {
        long j = ((iArr2[0] & 4294967295L) - (iArr[0] & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[1] & 4294967295L) - (iArr[1] & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[2] & 4294967295L) - (iArr[2] & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[3] & 4294967295L) - (iArr[3] & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[4] & 4294967295L) - (4294967295L & iArr[4]));
        iArr2[4] = (int) j5;
        return (int) (j5 >> 32);
    }

    /* renamed from: d */
    public static int m626d(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        long j = 4294967295L;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = 0;
        while (i6 < 5) {
            long j8 = iArr[i6] & j;
            long j9 = (j8 * j2) + (iArr3[i] & j) + 0;
            iArr3[i6 + 0] = (int) j9;
            int i7 = i6 + 1;
            long j10 = j3;
            long j11 = (j9 >>> 32) + (j8 * j3) + (iArr3[i7] & 4294967295L);
            iArr3[i7] = (int) j11;
            long j12 = j4;
            long j13 = (j11 >>> 32) + (j8 * j4) + (iArr3[i2] & 4294967295L);
            iArr3[i6 + 2] = (int) j13;
            long j14 = (j13 >>> 32) + (j8 * j5) + (iArr3[i3] & 4294967295L);
            iArr3[i6 + 3] = (int) j14;
            long j15 = (j14 >>> 32) + (j8 * j6) + (iArr3[i4] & 4294967295L);
            iArr3[i6 + 4] = (int) j15;
            long j16 = (j15 >>> 32) + j7 + (iArr3[i5] & 4294967295L);
            iArr3[i6 + 5] = (int) j16;
            j7 = j16 >>> 32;
            i6 = i7;
            j = 4294967295L;
            j2 = j2;
            j4 = j12;
            j3 = j10;
        }
        return (int) j7;
    }

    /* renamed from: d */
    public static void m628d(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
    }

    /* renamed from: e */
    public static int m625e(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((iArr[1] & 4294967295L) - (iArr2[1] & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[2] & 4294967295L) - (iArr2[2] & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[3] & 4294967295L) - (iArr2[3] & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[4] & 4294967295L) - (iArr2[4] & 4294967295L));
        iArr3[4] = (int) j5;
        return (int) (j5 >> 32);
    }
}
