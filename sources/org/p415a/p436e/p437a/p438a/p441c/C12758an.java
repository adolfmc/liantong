package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12898j;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.an */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12758an {

    /* renamed from: a */
    static final int[] f25971a = {-1, 0, 0, -1, -2, -1, -1, -1, -1, -1, -1, -1};

    /* renamed from: b */
    static final int[] f25972b = {1, -2, 0, 2, 0, -2, 0, 2, 1, 0, 0, 0, -2, 1, 0, -2, -3, -1, -1, -1, -1, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f25973c = {-1, 1, -1, -3, -1, 1, -1, -3, -2, -1, -1, -1, 1, -2, -1, 1, 2};

    /* renamed from: a */
    public static void m1224a(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = i & 4294967295L;
            long j3 = (iArr[0] & 4294967295L) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = (j3 >> 32) + ((iArr[1] & 4294967295L) - j2);
            iArr[1] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (iArr[2] & 4294967295L);
                iArr[2] = (int) j6;
                j5 = j6 >> 32;
            }
            long j7 = j5 + (iArr[3] & 4294967295L) + j2;
            iArr[3] = (int) j7;
            long j8 = (j7 >> 32) + (4294967295L & iArr[4]) + j2;
            iArr[4] = (int) j8;
            j = j8 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || AbstractC12891c.m688b(12, iArr, 5) == 0) && !(iArr[11] == -1 && AbstractC12891c.m679c(12, iArr, f25971a))) {
            return;
        }
        m1222a(iArr);
    }

    /* renamed from: a */
    private static void m1222a(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = (j >> 32) + ((iArr[1] & 4294967295L) - 1);
        iArr[1] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[2] & 4294967295L);
            iArr[2] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + (iArr[3] & 4294967295L) + 1;
        iArr[3] = (int) j5;
        long j6 = (j5 >> 32) + (4294967295L & iArr[4]) + 1;
        iArr[4] = (int) j6;
        if ((j6 >> 32) != 0) {
            AbstractC12891c.m688b(12, iArr, 5);
        }
    }

    /* renamed from: a */
    public static void m1221a(int[] iArr, int i, int[] iArr2) {
        int[] m712a = AbstractC12891c.m712a(24);
        AbstractC12898j.m526a(iArr, m712a);
        while (true) {
            m1215c(m712a, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12898j.m526a(iArr2, m712a);
        }
    }

    /* renamed from: a */
    public static void m1220a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(12, iArr, iArr2) != 0 || (iArr2[11] == -1 && AbstractC12891c.m679c(12, iArr2, f25971a))) {
            m1222a(iArr2);
        }
    }

    /* renamed from: a */
    public static void m1219a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12891c.m697a(12, iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && AbstractC12891c.m679c(12, iArr3, f25971a))) {
            m1222a(iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1223a(BigInteger bigInteger) {
        int[] m707a = AbstractC12891c.m707a(384, bigInteger);
        if (m707a[11] == -1 && AbstractC12891c.m679c(12, m707a, f25971a)) {
            AbstractC12891c.m672e(12, f25971a, m707a);
        }
        return m707a;
    }

    /* renamed from: b */
    private static void m1218b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = (j >> 32) + (iArr[1] & 4294967295L) + 1;
        iArr[1] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (iArr[2] & 4294967295L);
            iArr[2] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + ((iArr[3] & 4294967295L) - 1);
        iArr[3] = (int) j5;
        long j6 = (j5 >> 32) + ((4294967295L & iArr[4]) - 1);
        iArr[4] = (int) j6;
        if ((j6 >> 32) != 0) {
            AbstractC12891c.m705a(12, iArr, 5);
        }
    }

    /* renamed from: b */
    public static void m1217b(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m673e(12, iArr)) {
            AbstractC12891c.m670g(12, iArr2);
        } else {
            AbstractC12891c.m678c(12, f25971a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1216b(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12891c.m697a(24, iArr, iArr2, iArr3) != 0 || (iArr3[23] == -1 && AbstractC12891c.m679c(24, iArr3, f25972b))) {
            int[] iArr4 = f25973c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(24, iArr3, f25973c.length);
            }
        }
    }

    /* renamed from: c */
    public static void m1215c(int[] iArr, int[] iArr2) {
        long j = iArr[16] & 4294967295L;
        long j2 = iArr[17] & 4294967295L;
        long j3 = iArr[18] & 4294967295L;
        long j4 = iArr[19] & 4294967295L;
        long j5 = iArr[20] & 4294967295L;
        long j6 = iArr[21] & 4294967295L;
        long j7 = iArr[22] & 4294967295L;
        long j8 = iArr[23] & 4294967295L;
        long j9 = ((iArr[12] & 4294967295L) + j5) - 1;
        long j10 = (iArr[13] & 4294967295L) + j7;
        long j11 = (iArr[14] & 4294967295L) + j7 + j8;
        long j12 = (iArr[15] & 4294967295L) + j8;
        long j13 = j2 + j6;
        long j14 = j6 - j8;
        long j15 = j7 - j8;
        long j16 = j9 + j14;
        long j17 = (iArr[0] & 4294967295L) + j16 + 0;
        iArr2[0] = (int) j17;
        long j18 = (j17 >> 32) + (((iArr[1] & 4294967295L) + j8) - j9) + j10;
        iArr2[1] = (int) j18;
        long j19 = (j18 >> 32) + (((iArr[2] & 4294967295L) - j6) - j10) + j11;
        iArr2[2] = (int) j19;
        long j20 = (j19 >> 32) + ((iArr[3] & 4294967295L) - j11) + j12 + j16;
        iArr2[3] = (int) j20;
        long j21 = (j20 >> 32) + (((((iArr[4] & 4294967295L) + j) + j6) + j10) - j12) + j16;
        iArr2[4] = (int) j21;
        long j22 = (j21 >> 32) + ((iArr[5] & 4294967295L) - j) + j10 + j11 + j13;
        iArr2[5] = (int) j22;
        long j23 = (j22 >> 32) + (((iArr[6] & 4294967295L) + j3) - j2) + j11 + j12;
        iArr2[6] = (int) j23;
        long j24 = (j23 >> 32) + ((((iArr[7] & 4294967295L) + j) + j4) - j3) + j12;
        iArr2[7] = (int) j24;
        long j25 = (j24 >> 32) + (((((iArr[8] & 4294967295L) + j) + j2) + j5) - j4);
        iArr2[8] = (int) j25;
        long j26 = (j25 >> 32) + (((iArr[9] & 4294967295L) + j3) - j5) + j13;
        iArr2[9] = (int) j26;
        long j27 = (j26 >> 32) + ((((iArr[10] & 4294967295L) + j3) + j4) - j14) + j15;
        iArr2[10] = (int) j27;
        long j28 = (j27 >> 32) + ((((iArr[11] & 4294967295L) + j4) + j5) - j15);
        iArr2[11] = (int) j28;
        m1224a((int) ((j28 >> 32) + 1), iArr2);
    }

    /* renamed from: c */
    public static void m1214c(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m712a = AbstractC12891c.m712a(24);
        AbstractC12898j.m525a(iArr, iArr2, m712a);
        m1215c(m712a, iArr3);
    }

    /* renamed from: d */
    public static void m1213d(int[] iArr, int[] iArr2) {
        int[] m712a = AbstractC12891c.m712a(24);
        AbstractC12898j.m526a(iArr, m712a);
        m1215c(m712a, iArr2);
    }

    /* renamed from: d */
    public static void m1212d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12891c.m678c(12, iArr, iArr2, iArr3) != 0) {
            m1218b(iArr3);
        }
    }

    /* renamed from: e */
    public static void m1211e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(12, iArr, 0, iArr2) != 0 || (iArr2[11] == -1 && AbstractC12891c.m679c(12, iArr2, f25971a))) {
            m1222a(iArr2);
        }
    }
}
