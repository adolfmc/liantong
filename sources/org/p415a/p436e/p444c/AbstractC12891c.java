package org.p415a.p436e.p444c;

import java.math.BigInteger;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12891c {
    /* renamed from: a */
    public static int m711a(int i, int i2, int[] iArr) {
        long j = (iArr[0] & 4294967295L) + (i2 & 4294967295L);
        iArr[0] = (int) j;
        long j2 = (j >>> 32) + (4294967295L & iArr[1]) + 1;
        iArr[1] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return m688b(i, iArr, 2);
    }

    /* renamed from: a */
    public static int m710a(int i, int i2, int[] iArr, int i3) {
        long j = (i2 & 4294967295L) + (4294967295L & iArr[i3]);
        iArr[i3] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return m688b(i, iArr, i3 + 1);
    }

    /* renamed from: a */
    public static int m709a(int i, int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j = i2 & 4294967295L;
        long j2 = 0;
        int i5 = 0;
        do {
            int i6 = i4 + i5;
            long j3 = j2 + ((iArr[i3 + i5] & 4294967295L) * j) + (iArr2[i6] & 4294967295L);
            iArr2[i6] = (int) j3;
            j2 = j3 >>> 32;
            i5++;
        } while (i5 < i);
        return (int) j2;
    }

    /* renamed from: a */
    public static int m708a(int i, int i2, int[] iArr, int i3, int[] iArr2, int[] iArr3, int i4) {
        long j = i2 & 4294967295L;
        long j2 = i3 & 4294967295L;
        long j3 = 0;
        int i5 = 0;
        do {
            int i6 = i4 + i5;
            long j4 = j3 + ((iArr[i5] & 4294967295L) * j) + ((iArr2[i5] & 4294967295L) * j2) + (iArr3[i6] & 4294967295L);
            iArr3[i6] = (int) j4;
            j3 = j4 >>> 32;
            i5++;
        } while (i5 < i);
        return (int) j3;
    }

    /* renamed from: a */
    public static int m705a(int i, int[] iArr, int i2) {
        while (i2 < i) {
            int i3 = iArr[i2] - 1;
            iArr[i2] = i3;
            if (i3 != -1) {
                return 0;
            }
            i2++;
        }
        return -1;
    }

    /* renamed from: a */
    public static int m704a(int i, int[] iArr, int i2, int i3) {
        while (i3 < i) {
            int i4 = i2 + i3;
            int i5 = iArr[i4] + 1;
            iArr[i4] = i5;
            if (i5 != 0) {
                return 0;
            }
            i3++;
        }
        return 1;
    }

    /* renamed from: a */
    public static int m703a(int i, int[] iArr, int i2, int i3, int i4, int[] iArr2, int i5) {
        while (true) {
            i--;
            if (i < 0) {
                return i4 << (-i3);
            }
            int i6 = iArr[i2 + i];
            iArr2[i5 + i] = (i4 << (-i3)) | (i6 >>> i3);
            i4 = i6;
        }
    }

    /* renamed from: a */
    public static int m702a(int i, int[] iArr, int i2, int i3, int[] iArr2) {
        int i4 = 0;
        while (i4 < i) {
            int i5 = iArr[i4];
            iArr2[i4] = (i3 >>> (-i2)) | (i5 << i2);
            i4++;
            i3 = i5;
        }
        return i3 >>> (-i2);
    }

    /* renamed from: a */
    public static int m701a(int i, int[] iArr, int i2, int i3, int[] iArr2, int i4) {
        int i5 = 0;
        while (i5 < i) {
            int i6 = iArr[i2 + i5];
            iArr2[i4 + i5] = (i3 >>> 31) | (i6 << 1);
            i5++;
            i3 = i6;
        }
        return i3 >>> 31;
    }

    /* renamed from: a */
    public static int m700a(int i, int[] iArr, int i2, int[] iArr2) {
        int i3 = 0;
        while (i3 < i) {
            int i4 = iArr[i3];
            iArr2[i3] = (i2 >>> 31) | (i4 << 1);
            i3++;
            i2 = i4;
        }
        return i2 >>> 31;
    }

    /* renamed from: a */
    public static int m699a(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 + i4;
            long j2 = j + (iArr[i2 + i4] & 4294967295L) + (4294967295L & iArr2[i5]);
            iArr2[i5] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    /* renamed from: a */
    public static int m698a(int i, int[] iArr, int[] iArr2) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = j + (iArr[i2] & 4294967295L) + (4294967295L & iArr2[i2]);
            iArr2[i2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    /* renamed from: a */
    public static int m697a(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = j + (iArr[i2] & 4294967295L) + (4294967295L & iArr2[i2]);
            iArr3[i2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    /* renamed from: a */
    public static int m692a(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= iArr.length) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    /* renamed from: a */
    public static long m695a(int i, long[] jArr, int i2, int i3, long j) {
        int i4 = 0;
        while (i4 < i) {
            int i5 = i2 + i4;
            long j2 = jArr[i5];
            jArr[i5] = (j >>> (-i3)) | (j2 << i3);
            i4++;
            j = j2;
        }
        return j >>> (-i3);
    }

    /* renamed from: a */
    public static long m694a(int i, long[] jArr, int i2, int i3, long j, long[] jArr2, int i4) {
        int i5 = 0;
        while (i5 < i) {
            long j2 = jArr[i2 + i5];
            jArr2[i4 + i5] = (j >>> (-i3)) | (j2 << i3);
            i5++;
            j = j2;
        }
        return j >>> (-i3);
    }

    /* renamed from: a */
    public static long m693a(int i, long[] jArr, int i2, long j, long[] jArr2, int i3) {
        int i4 = 0;
        while (i4 < i) {
            long j2 = jArr[i2 + i4];
            jArr2[i3 + i4] = (j >>> 63) | (j2 << 1);
            i4++;
            j = j2;
        }
        return j >>> 63;
    }

    /* renamed from: a */
    public static void m696a(int i, long[] jArr) {
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = 0;
        }
    }

    /* renamed from: a */
    public static int[] m712a(int i) {
        return new int[i];
    }

    /* renamed from: a */
    public static int[] m707a(int i, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > i) {
            throw new IllegalArgumentException();
        }
        int[] m712a = m712a((i + 31) >> 5);
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            m712a[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i2++;
        }
        return m712a;
    }

    /* renamed from: a */
    public static int[] m706a(int i, int[] iArr) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    /* renamed from: b */
    public static int m690b(int i, int i2, int[] iArr) {
        long j = (i2 & 4294967295L) + (4294967295L & iArr[0]);
        iArr[0] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return m688b(i, iArr, 1);
    }

    /* renamed from: b */
    public static int m689b(int i, int[] iArr) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2] - 1;
            iArr[i2] = i3;
            if (i3 != -1) {
                return 0;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public static int m688b(int i, int[] iArr, int i2) {
        while (i2 < i) {
            int i3 = iArr[i2] + 1;
            iArr[i2] = i3;
            if (i3 != 0) {
                return 0;
            }
            i2++;
        }
        return 1;
    }

    /* renamed from: b */
    public static int m687b(int i, int[] iArr, int i2, int i3) {
        while (true) {
            i--;
            if (i < 0) {
                return i3 << (-i2);
            }
            int i4 = iArr[i];
            iArr[i] = (i3 << (-i2)) | (i4 >>> i2);
            i3 = i4;
        }
    }

    /* renamed from: b */
    public static int m686b(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 + i4;
            long j2 = j + ((iArr2[i5] & 4294967295L) - (4294967295L & iArr[i2 + i4]));
            iArr2[i5] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    /* renamed from: b */
    public static int m684b(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = j + (iArr[i2] & 4294967295L) + (iArr2[i2] & 4294967295L) + (4294967295L & iArr3[i2]);
            iArr3[i2] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    /* renamed from: b */
    public static boolean m685b(int i, int[] iArr, int[] iArr2) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static long[] m691b(int i) {
        return new long[i];
    }

    /* renamed from: c */
    public static int m683c(int i, int i2, int[] iArr) {
        long j = (iArr[0] & 4294967295L) - (i2 & 4294967295L);
        iArr[0] = (int) j;
        long j2 = (j >> 32) + ((4294967295L & iArr[1]) - 1);
        iArr[1] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return m705a(i, iArr, 2);
    }

    /* renamed from: c */
    public static int m682c(int i, int[] iArr) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2] + 1;
            iArr[i2] = i3;
            if (i3 != 0) {
                return 0;
            }
        }
        return 1;
    }

    /* renamed from: c */
    public static int m681c(int i, int[] iArr, int i2) {
        while (true) {
            i--;
            if (i < 0) {
                return i2 << 31;
            }
            int i3 = iArr[i];
            iArr[i] = (i2 << 31) | (i3 >>> 1);
            i2 = i3;
        }
    }

    /* renamed from: c */
    public static int m680c(int i, int[] iArr, int i2, int i3) {
        int i4 = 0;
        while (i4 < i) {
            int i5 = iArr[i4];
            iArr[i4] = (i3 >>> (-i2)) | (i5 << i2);
            i4++;
            i3 = i5;
        }
        return i3 >>> (-i2);
    }

    /* renamed from: c */
    public static int m678c(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = j + ((iArr[i2] & 4294967295L) - (4294967295L & iArr2[i2]));
            iArr3[i2] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    /* renamed from: c */
    public static boolean m679c(int i, int[] iArr, int[] iArr2) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            int i3 = iArr[i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                return false;
            }
            if (i3 > i4) {
                return true;
            }
        }
        return true;
    }

    /* renamed from: d */
    public static int m677d(int i, int i2, int[] iArr) {
        long j = (iArr[0] & 4294967295L) - (4294967295L & i2);
        iArr[0] = (int) j;
        if ((j >> 32) == 0) {
            return 0;
        }
        return m705a(i, iArr, 1);
    }

    /* renamed from: d */
    public static int m675d(int i, int[] iArr, int i2) {
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            int i3 = iArr[i];
            iArr[i] = i2;
            i2 = i3;
        }
    }

    /* renamed from: d */
    public static int m674d(int i, int[] iArr, int[] iArr2) {
        int i2 = 0;
        while (i2 < i) {
            int i3 = iArr[i2] + 1;
            iArr2[i2] = i3;
            i2++;
            if (i3 != 0) {
                while (i2 < i) {
                    iArr2[i2] = iArr[i2];
                    i2++;
                }
                return 0;
            }
        }
        return 1;
    }

    /* renamed from: d */
    public static boolean m676d(int i, int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < i; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    public static int m672e(int i, int[] iArr, int[] iArr2) {
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = j + ((iArr2[i2] & 4294967295L) - (4294967295L & iArr[i2]));
            iArr2[i2] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    /* renamed from: e */
    public static boolean m673e(int i, int[] iArr) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: f */
    public static BigInteger m671f(int i, int[] iArr) {
        byte[] bArr = new byte[i << 2];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                AbstractC12971e.m400a(i3, bArr, ((i - 1) - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    /* renamed from: g */
    public static void m670g(int i, int[] iArr) {
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = 0;
        }
    }
}
