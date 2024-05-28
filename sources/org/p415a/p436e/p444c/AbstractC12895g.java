package org.p415a.p436e.p444c;

import java.math.BigInteger;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12895g {
    /* renamed from: a */
    public static int m589a(int i, int i2, int[] iArr, int i3) {
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
        return AbstractC12891c.m704a(7, iArr, i3, 3);
    }

    /* renamed from: a */
    public static int m588a(int i, long j, int[] iArr, int i2) {
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
        return AbstractC12891c.m704a(7, iArr, i2, 4);
    }

    /* renamed from: a */
    public static int m584a(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= 7) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    /* renamed from: a */
    public static int m582a(int[] iArr, int[] iArr2, int[] iArr3) {
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
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L);
        iArr3[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    /* renamed from: a */
    public static long m587a(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
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
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (iArr2[i3 + 4] & 4294967295L);
        iArr3[i4 + 4] = (int) j12;
        long j13 = iArr[i2 + 5] & 4294967295L;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (iArr2[i3 + 5] & 4294967295L);
        iArr3[i4 + 5] = (int) j14;
        long j15 = iArr[i2 + 6] & 4294967295L;
        long j16 = (j14 >>> 32) + (j * j15) + j13 + (4294967295L & iArr2[i3 + 6]);
        iArr3[i4 + 6] = (int) j16;
        return (j16 >>> 32) + j15;
    }

    /* renamed from: a */
    public static void m583a(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
    }

    /* renamed from: a */
    public static boolean m585a(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 7; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static int[] m590a() {
        return new int[7];
    }

    /* renamed from: a */
    public static int[] m586a(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 224) {
            throw new IllegalArgumentException();
        }
        int[] m590a = m590a();
        int i = 0;
        while (bigInteger.signum() != 0) {
            m590a[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i++;
        }
        return m590a;
    }

    /* renamed from: b */
    public static int m578b(int[] iArr, int[] iArr2, int[] iArr3) {
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
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (iArr3[5] & 4294967295L);
        iArr3[5] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[6] & 4294967295L) + (iArr2[6] & 4294967295L) + (iArr3[6] & 4294967295L);
        iArr3[6] = (int) j7;
        return (int) (j7 >>> 32);
    }

    /* renamed from: b */
    public static boolean m580b(int[] iArr) {
        for (int i = 0; i < 7; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m579b(int[] iArr, int[] iArr2) {
        for (int i = 6; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static int[] m581b() {
        return new int[14];
    }

    /* renamed from: c */
    public static BigInteger m577c(int[] iArr) {
        byte[] bArr = new byte[28];
        for (int i = 0; i < 7; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                AbstractC12971e.m400a(i2, bArr, (6 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    /* renamed from: c */
    public static void m575c(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        long j = iArr2[0] & 4294967295L;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr2[5] & 4294967295L;
        long j7 = iArr2[6] & 4294967295L;
        long j8 = iArr[0] & 4294967295L;
        long j9 = (j8 * j) + 0;
        iArr3[0] = (int) j9;
        long j10 = (j9 >>> 32) + (j8 * j2);
        iArr3[1] = (int) j10;
        long j11 = (j10 >>> 32) + (j8 * j3);
        iArr3[2] = (int) j11;
        long j12 = (j11 >>> 32) + (j8 * j4);
        iArr3[3] = (int) j12;
        long j13 = (j12 >>> 32) + (j8 * j5);
        iArr3[4] = (int) j13;
        long j14 = (j13 >>> 32) + (j8 * j6);
        iArr3[5] = (int) j14;
        long j15 = (j14 >>> 32) + (j8 * j7);
        iArr3[6] = (int) j15;
        iArr3[7] = (int) (j15 >>> 32);
        int i7 = 1;
        for (int i8 = 7; i7 < i8; i8 = 7) {
            long j16 = iArr[i7] & 4294967295L;
            long j17 = (j16 * j) + (iArr3[i] & 4294967295L) + 0;
            iArr3[i7 + 0] = (int) j17;
            int i9 = i7 + 1;
            long j18 = j2;
            long j19 = (j17 >>> 32) + (j16 * j2) + (iArr3[i9] & 4294967295L);
            iArr3[i9] = (int) j19;
            int i10 = i7;
            long j20 = (j19 >>> 32) + (j16 * j3) + (iArr3[i2] & 4294967295L);
            iArr3[i7 + 2] = (int) j20;
            long j21 = (j20 >>> 32) + (j16 * j4) + (iArr3[i3] & 4294967295L);
            iArr3[i10 + 3] = (int) j21;
            long j22 = (j21 >>> 32) + (j16 * j5) + (iArr3[i4] & 4294967295L);
            iArr3[i10 + 4] = (int) j22;
            long j23 = (j22 >>> 32) + (j16 * j6) + (iArr3[i5] & 4294967295L);
            iArr3[i10 + 5] = (int) j23;
            long j24 = (j23 >>> 32) + (j16 * j7) + (iArr3[i6] & 4294967295L);
            iArr3[i10 + 6] = (int) j24;
            iArr3[i10 + 7] = (int) (j24 >>> 32);
            i7 = i9;
            j = j;
            j2 = j18;
        }
    }

    /* renamed from: c */
    public static boolean m576c(int[] iArr, int[] iArr2) {
        for (int i = 6; i >= 0; i--) {
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

    /* renamed from: d */
    public static int m572d(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j = 4294967295L;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = iArr2[6] & 4294967295L;
        long j9 = 0;
        int i8 = 0;
        while (i8 < 7) {
            long j10 = j8;
            long j11 = iArr[i8] & j;
            long j12 = j7;
            long j13 = (j11 * j2) + (iArr3[i] & j) + 0;
            iArr3[i8 + 0] = (int) j13;
            int i9 = i8 + 1;
            long j14 = j3;
            long j15 = (j13 >>> 32) + (j11 * j3) + (iArr3[i9] & j);
            iArr3[i9] = (int) j15;
            long j16 = (j15 >>> 32) + (j11 * j4) + (iArr3[i2] & j);
            iArr3[i8 + 2] = (int) j16;
            long j17 = (j16 >>> 32) + (j11 * j5) + (iArr3[i3] & j);
            iArr3[i8 + 3] = (int) j17;
            long j18 = (j17 >>> 32) + (j11 * j6) + (iArr3[i4] & j);
            iArr3[i8 + 4] = (int) j18;
            long j19 = (j18 >>> 32) + (j11 * j12) + (iArr3[i5] & j);
            iArr3[i8 + 5] = (int) j19;
            long j20 = (j19 >>> 32) + (j11 * j10) + (iArr3[i6] & j);
            iArr3[i8 + 6] = (int) j20;
            long j21 = (j20 >>> 32) + j9 + (iArr3[i7] & j);
            iArr3[i8 + 7] = (int) j21;
            j9 = j21 >>> 32;
            i8 = i9;
            j8 = j10;
            j7 = j12;
            j3 = j14;
            j = 4294967295L;
        }
        return (int) j9;
    }

    /* renamed from: d */
    public static void m574d(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
    }

    /* renamed from: d */
    public static void m573d(int[] iArr, int[] iArr2) {
        long j = iArr[0] & 4294967295L;
        int i = 0;
        int i2 = 14;
        int i3 = 6;
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
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long j16 = (j12 & 4294967295L) + (j15 >>> 32) + (j13 * j6);
                long j17 = (j14 & 4294967295L) + (j16 >>> 32) + (j13 * j9);
                long j18 = (iArr2[6] & 4294967295L) + (j14 >>> 32) + (j17 >>> 32);
                long j19 = iArr[4] & 4294967295L;
                long j20 = (iArr2[7] & 4294967295L) + (j18 >>> 32);
                long j21 = j18 & 4294967295L;
                long j22 = (iArr2[8] & 4294967295L) + (j20 >>> 32);
                long j23 = (j16 & 4294967295L) + (j19 * j);
                int i11 = (int) j23;
                iArr2[4] = i10 | (i11 << 1);
                int i12 = i11 >>> 31;
                long j24 = (j17 & 4294967295L) + (j23 >>> 32) + (j19 * j6);
                long j25 = j21 + (j24 >>> 32) + (j19 * j9);
                long j26 = (j20 & 4294967295L) + (j25 >>> 32) + (j19 * j13);
                long j27 = j22 + (j26 >>> 32);
                long j28 = iArr[5] & 4294967295L;
                long j29 = (iArr2[9] & 4294967295L) + (j27 >>> 32);
                long j30 = (j24 & 4294967295L) + (j28 * j);
                int i13 = (int) j30;
                iArr2[5] = i12 | (i13 << 1);
                int i14 = i13 >>> 31;
                long j31 = (j25 & 4294967295L) + (j30 >>> 32) + (j28 * j6);
                long j32 = (j26 & 4294967295L) + (j31 >>> 32) + (j28 * j9);
                long j33 = (j27 & 4294967295L) + (j32 >>> 32) + (j28 * j13);
                long j34 = (j29 & 4294967295L) + (j33 >>> 32) + (j28 * j19);
                long j35 = (iArr2[10] & 4294967295L) + (j29 >>> 32) + (j34 >>> 32);
                long j36 = j34 & 4294967295L;
                long j37 = iArr[6] & 4294967295L;
                long j38 = (iArr2[11] & 4294967295L) + (j35 >>> 32);
                long j39 = 4294967295L & j38;
                long j40 = (j31 & 4294967295L) + (j37 * j);
                int i15 = (int) j40;
                iArr2[6] = i14 | (i15 << 1);
                int i16 = i15 >>> 31;
                long j41 = (j32 & 4294967295L) + (j40 >>> 32) + (j6 * j37);
                long j42 = (j33 & 4294967295L) + (j41 >>> 32) + (j37 * j9);
                long j43 = j36 + (j42 >>> 32) + (j37 * j13);
                long j44 = (j35 & 4294967295L) + (j43 >>> 32) + (j37 * j19);
                long j45 = j39 + (j44 >>> 32) + (j37 * j28);
                long j46 = (iArr2[12] & 4294967295L) + (j38 >>> 32) + (j45 >>> 32);
                int i17 = (int) j41;
                iArr2[7] = i16 | (i17 << 1);
                int i18 = (int) j42;
                iArr2[8] = (i17 >>> 31) | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) j43;
                iArr2[9] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j44;
                iArr2[10] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) j45;
                iArr2[11] = i23 | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j46;
                iArr2[12] = i25 | (i26 << 1);
                iArr2[13] = ((iArr2[13] + ((int) (j46 >>> 32))) << 1) | (i26 >>> 31);
                return;
            }
            i3 = i4;
            i = i6;
        }
    }

    /* renamed from: e */
    public static int m571e(int[] iArr, int[] iArr2) {
        long j = ((iArr2[0] & 4294967295L) - (iArr[0] & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[1] & 4294967295L) - (iArr[1] & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[2] & 4294967295L) - (iArr[2] & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[3] & 4294967295L) - (iArr[3] & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[4] & 4294967295L) - (iArr[4] & 4294967295L));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[5] & 4294967295L) - (iArr[5] & 4294967295L));
        iArr2[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr2[6] & 4294967295L) - (4294967295L & iArr[6]));
        iArr2[6] = (int) j7;
        return (int) (j7 >> 32);
    }

    /* renamed from: e */
    public static int m570e(int[] iArr, int[] iArr2, int[] iArr3) {
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
        long j6 = (j5 >> 32) + ((iArr[5] & 4294967295L) - (iArr2[5] & 4294967295L));
        iArr3[5] = (int) j6;
        long j7 = (j6 >> 32) + ((iArr[6] & 4294967295L) - (iArr2[6] & 4294967295L));
        iArr3[6] = (int) j7;
        return (int) (j7 >> 32);
    }
}
