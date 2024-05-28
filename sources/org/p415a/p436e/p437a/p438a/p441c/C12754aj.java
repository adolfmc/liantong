package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12896h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.aj */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12754aj {

    /* renamed from: a */
    static final int[] f25965a = {-1, -1, -1, 0, 0, 0, 1, -1};

    /* renamed from: b */
    static final int[] f25966b = {1, 0, 0, -2, -1, -1, -2, 1, -2, 1, -2, 1, 1, -2, 2, -2};

    /* renamed from: a */
    public static void m1238a(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = i & 4294967295L;
            long j3 = (iArr[0] & 4294967295L) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j5;
                long j6 = (j5 >> 32) + (iArr[2] & 4294967295L);
                iArr[2] = (int) j6;
                j4 = j6 >> 32;
            }
            long j7 = j4 + ((iArr[3] & 4294967295L) - j2);
            iArr[3] = (int) j7;
            long j8 = j7 >> 32;
            if (j8 != 0) {
                long j9 = j8 + (iArr[4] & 4294967295L);
                iArr[4] = (int) j9;
                long j10 = (j9 >> 32) + (iArr[5] & 4294967295L);
                iArr[5] = (int) j10;
                j8 = j10 >> 32;
            }
            long j11 = j8 + ((iArr[6] & 4294967295L) - j2);
            iArr[6] = (int) j11;
            long j12 = (j11 >> 32) + (4294967295L & iArr[7]) + j2;
            iArr[7] = (int) j12;
            j = j12 >> 32;
        } else {
            j = 0;
        }
        if (j != 0 || (iArr[7] == -1 && AbstractC12896h.m542c(iArr, f25965a))) {
            m1236a(iArr);
        }
    }

    /* renamed from: a */
    private static void m1236a(int[] iArr) {
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
        long j5 = j2 + ((iArr[3] & 4294967295L) - 1);
        iArr[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (iArr[4] & 4294967295L);
            iArr[4] = (int) j7;
            long j8 = (j7 >> 32) + (iArr[5] & 4294967295L);
            iArr[5] = (int) j8;
            j6 = j8 >> 32;
        }
        long j9 = j6 + ((iArr[6] & 4294967295L) - 1);
        iArr[6] = (int) j9;
        iArr[7] = (int) ((j9 >> 32) + (4294967295L & iArr[7]) + 1);
    }

    /* renamed from: a */
    public static void m1235a(int[] iArr, int i, int[] iArr2) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(iArr, m546c);
        while (true) {
            m1229c(m546c, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12896h.m537d(iArr2, m546c);
        }
    }

    /* renamed from: a */
    public static void m1234a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(8, iArr, iArr2) != 0 || (iArr2[7] == -1 && AbstractC12896h.m542c(iArr2, f25965a))) {
            m1236a(iArr2);
        }
    }

    /* renamed from: a */
    public static void m1233a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m557a(iArr, iArr2, iArr3) != 0 || (iArr3[7] == -1 && AbstractC12896h.m542c(iArr3, f25965a))) {
            m1236a(iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1237a(BigInteger bigInteger) {
        int[] m564a = AbstractC12896h.m564a(bigInteger);
        if (m564a[7] == -1 && AbstractC12896h.m542c(m564a, f25965a)) {
            AbstractC12896h.m535e(f25965a, m564a);
        }
        return m564a;
    }

    /* renamed from: b */
    private static void m1232b(int[] iArr) {
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
        long j5 = j2 + (iArr[3] & 4294967295L) + 1;
        iArr[3] = (int) j5;
        long j6 = j5 >> 32;
        if (j6 != 0) {
            long j7 = j6 + (iArr[4] & 4294967295L);
            iArr[4] = (int) j7;
            long j8 = (j7 >> 32) + (iArr[5] & 4294967295L);
            iArr[5] = (int) j8;
            j6 = j8 >> 32;
        }
        long j9 = j6 + (iArr[6] & 4294967295L) + 1;
        iArr[6] = (int) j9;
        iArr[7] = (int) ((j9 >> 32) + ((4294967295L & iArr[7]) - 1));
    }

    /* renamed from: b */
    public static void m1231b(int[] iArr, int[] iArr2) {
        if (AbstractC12896h.m552b(iArr)) {
            AbstractC12896h.m538d(iArr2);
        } else {
            AbstractC12896h.m534e(f25965a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1230b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m541c(iArr, iArr2, m546c);
        m1229c(m546c, iArr3);
    }

    /* renamed from: c */
    public static void m1229c(int[] iArr, int[] iArr2) {
        long j = iArr[9] & 4294967295L;
        long j2 = iArr[10] & 4294967295L;
        long j3 = iArr[11] & 4294967295L;
        long j4 = iArr[12] & 4294967295L;
        long j5 = iArr[13] & 4294967295L;
        long j6 = iArr[14] & 4294967295L;
        long j7 = iArr[15] & 4294967295L;
        long j8 = (iArr[8] & 4294967295L) - 6;
        long j9 = j8 + j;
        long j10 = j + j2;
        long j11 = (j2 + j3) - j7;
        long j12 = j3 + j4;
        long j13 = j4 + j5;
        long j14 = j5 + j6;
        long j15 = j6 + j7;
        long j16 = j14 - j9;
        long j17 = (((iArr[0] & 4294967295L) - j12) - j16) + 0;
        iArr2[0] = (int) j17;
        long j18 = (j17 >> 32) + ((((iArr[1] & 4294967295L) + j10) - j13) - j15);
        iArr2[1] = (int) j18;
        long j19 = (j18 >> 32) + (((iArr[2] & 4294967295L) + j11) - j14);
        iArr2[2] = (int) j19;
        long j20 = (j19 >> 32) + ((((iArr[3] & 4294967295L) + (j12 << 1)) + j16) - j15);
        iArr2[3] = (int) j20;
        long j21 = (j20 >> 32) + ((((iArr[4] & 4294967295L) + (j13 << 1)) + j6) - j10);
        iArr2[4] = (int) j21;
        long j22 = (j21 >> 32) + (((iArr[5] & 4294967295L) + (j14 << 1)) - j11);
        iArr2[5] = (int) j22;
        long j23 = (j22 >> 32) + (iArr[6] & 4294967295L) + (j15 << 1) + j16;
        iArr2[6] = (int) j23;
        long j24 = (j23 >> 32) + (((((iArr[7] & 4294967295L) + (j7 << 1)) + j8) - j11) - j13);
        iArr2[7] = (int) j24;
        m1238a((int) ((j24 >> 32) + 6), iArr2);
    }

    /* renamed from: c */
    public static void m1228c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m536d(iArr, iArr2, iArr3) != 0 || ((iArr3[15] >>> 1) >= Integer.MAX_VALUE && AbstractC12891c.m679c(16, iArr3, f25966b))) {
            AbstractC12891c.m672e(16, f25966b, iArr3);
        }
    }

    /* renamed from: d */
    public static void m1227d(int[] iArr, int[] iArr2) {
        int[] m546c = AbstractC12896h.m546c();
        AbstractC12896h.m537d(iArr, m546c);
        m1229c(m546c, iArr2);
    }

    /* renamed from: d */
    public static void m1226d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12896h.m534e(iArr, iArr2, iArr3) != 0) {
            m1232b(iArr3);
        }
    }

    /* renamed from: e */
    public static void m1225e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(8, iArr, 0, iArr2) != 0 || (iArr2[7] == -1 && AbstractC12896h.m542c(iArr2, f25965a))) {
            m1236a(iArr2);
        }
    }
}
