package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12895g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ab */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12746ab {

    /* renamed from: a */
    static final int[] f25951a = {1, 0, 0, -1, -1, -1, -1};

    /* renamed from: b */
    static final int[] f25952b = {1, 0, 0, -2, -1, -1, 0, 2, 0, 0, -2, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f25953c = {-1, -1, -1, 1, 0, 0, -1, -3, -1, -1, 1};

    /* renamed from: a */
    public static void m1269a(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = i & 4294967295L;
            long j3 = ((iArr[0] & 4294967295L) - j2) + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j5;
                long j6 = (j5 >> 32) + (iArr[2] & 4294967295L);
                iArr[2] = (int) j6;
                j4 = j6 >> 32;
            }
            long j7 = j4 + (4294967295L & iArr[3]) + j2;
            iArr[3] = (int) j7;
            j = j7 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || AbstractC12891c.m688b(7, iArr, 4) == 0) && !(iArr[6] == -1 && AbstractC12895g.m576c(iArr, f25951a))) {
            return;
        }
        m1267a(iArr);
    }

    /* renamed from: a */
    private static void m1267a(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j3;
            long j4 = (j3 >> 32) + (iArr[2] & 4294967295L);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        long j5 = j2 + (4294967295L & iArr[3]) + 1;
        iArr[3] = (int) j5;
        if ((j5 >> 32) != 0) {
            AbstractC12891c.m688b(7, iArr, 4);
        }
    }

    /* renamed from: a */
    public static void m1266a(int[] iArr, int i, int[] iArr2) {
        int[] m581b = AbstractC12895g.m581b();
        AbstractC12895g.m573d(iArr, m581b);
        while (true) {
            m1260c(m581b, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12895g.m573d(iArr2, m581b);
        }
    }

    /* renamed from: a */
    public static void m1265a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(7, iArr, iArr2) != 0 || (iArr2[6] == -1 && AbstractC12895g.m576c(iArr2, f25951a))) {
            m1267a(iArr2);
        }
    }

    /* renamed from: a */
    public static void m1264a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12895g.m582a(iArr, iArr2, iArr3) != 0 || (iArr3[6] == -1 && AbstractC12895g.m576c(iArr3, f25951a))) {
            m1267a(iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1268a(BigInteger bigInteger) {
        int[] m586a = AbstractC12895g.m586a(bigInteger);
        if (m586a[6] == -1 && AbstractC12895g.m576c(m586a, f25951a)) {
            AbstractC12895g.m571e(f25951a, m586a);
        }
        return m586a;
    }

    /* renamed from: b */
    private static void m1263b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j3;
            long j4 = (j3 >> 32) + (iArr[2] & 4294967295L);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        long j5 = j2 + ((4294967295L & iArr[3]) - 1);
        iArr[3] = (int) j5;
        if ((j5 >> 32) != 0) {
            AbstractC12891c.m705a(7, iArr, 4);
        }
    }

    /* renamed from: b */
    public static void m1262b(int[] iArr, int[] iArr2) {
        if (AbstractC12895g.m580b(iArr)) {
            AbstractC12895g.m574d(iArr2);
        } else {
            AbstractC12895g.m570e(f25951a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1261b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m581b = AbstractC12895g.m581b();
        AbstractC12895g.m575c(iArr, iArr2, m581b);
        m1260c(m581b, iArr3);
    }

    /* renamed from: c */
    public static void m1260c(int[] iArr, int[] iArr2) {
        long j = iArr[10] & 4294967295L;
        long j2 = iArr[11] & 4294967295L;
        long j3 = iArr[12] & 4294967295L;
        long j4 = iArr[13] & 4294967295L;
        long j5 = ((iArr[7] & 4294967295L) + j2) - 1;
        long j6 = (iArr[8] & 4294967295L) + j3;
        long j7 = (iArr[9] & 4294967295L) + j4;
        long j8 = ((iArr[0] & 4294967295L) - j5) + 0;
        long j9 = j8 & 4294967295L;
        long j10 = (j8 >> 32) + ((iArr[1] & 4294967295L) - j6);
        iArr2[1] = (int) j10;
        long j11 = (j10 >> 32) + ((iArr[2] & 4294967295L) - j7);
        iArr2[2] = (int) j11;
        long j12 = (j11 >> 32) + (((iArr[3] & 4294967295L) + j5) - j);
        long j13 = j12 & 4294967295L;
        long j14 = (j12 >> 32) + (((iArr[4] & 4294967295L) + j6) - j2);
        iArr2[4] = (int) j14;
        long j15 = (j14 >> 32) + (((iArr[5] & 4294967295L) + j7) - j3);
        iArr2[5] = (int) j15;
        long j16 = (j15 >> 32) + (((iArr[6] & 4294967295L) + j) - j4);
        iArr2[6] = (int) j16;
        long j17 = (j16 >> 32) + 1;
        long j18 = j13 + j17;
        long j19 = j9 - j17;
        iArr2[0] = (int) j19;
        long j20 = j19 >> 32;
        if (j20 != 0) {
            long j21 = j20 + (iArr2[1] & 4294967295L);
            iArr2[1] = (int) j21;
            long j22 = (j21 >> 32) + (4294967295L & iArr2[2]);
            iArr2[2] = (int) j22;
            j18 += j22 >> 32;
        }
        iArr2[3] = (int) j18;
        if (((j18 >> 32) == 0 || AbstractC12891c.m688b(7, iArr2, 4) == 0) && !(iArr2[6] == -1 && AbstractC12895g.m576c(iArr2, f25951a))) {
            return;
        }
        m1267a(iArr2);
    }

    /* renamed from: c */
    public static void m1259c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12895g.m572d(iArr, iArr2, iArr3) != 0 || (iArr3[13] == -1 && AbstractC12891c.m679c(14, iArr3, f25952b))) {
            int[] iArr4 = f25953c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(14, iArr3, f25953c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m1258d(int[] iArr, int[] iArr2) {
        int[] m581b = AbstractC12895g.m581b();
        AbstractC12895g.m573d(iArr, m581b);
        m1260c(m581b, iArr2);
    }

    /* renamed from: d */
    public static void m1257d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12895g.m570e(iArr, iArr2, iArr3) != 0) {
            m1263b(iArr3);
        }
    }

    /* renamed from: e */
    public static void m1256e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(7, iArr, 0, iArr2) != 0 || (iArr2[6] == -1 && AbstractC12895g.m576c(iArr2, f25951a))) {
            m1267a(iArr2);
        }
    }
}
