package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12894f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12837t {

    /* renamed from: a */
    static final int[] f26044a = {-1, -1, -2, -1, -1, -1};

    /* renamed from: b */
    static final int[] f26045b = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f26046c = {-1, -1, -3, -1, -2, -1, 1, 0, 2};

    /* renamed from: a */
    public static void m985a(int i, int[] iArr) {
        long j;
        if (i != 0) {
            long j2 = i & 4294967295L;
            long j3 = (iArr[0] & 4294967295L) + j2 + 0;
            iArr[0] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = j4 + (4294967295L & iArr[2]) + j2;
            iArr[2] = (int) j6;
            j = j6 >> 32;
        } else {
            j = 0;
        }
        if ((j == 0 || AbstractC12891c.m688b(6, iArr, 3) == 0) && !(iArr[5] == -1 && AbstractC12894f.m605b(iArr, f26044a))) {
            return;
        }
        m983a(iArr);
    }

    /* renamed from: a */
    private static void m983a(int[] iArr) {
        long j = (iArr[0] & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + (4294967295L & iArr[2]) + 1;
        iArr[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            AbstractC12891c.m688b(6, iArr, 3);
        }
    }

    /* renamed from: a */
    public static void m982a(int[] iArr, int i, int[] iArr2) {
        int[] m602c = AbstractC12894f.m602c();
        AbstractC12894f.m598c(iArr, m602c);
        while (true) {
            m976c(m602c, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12894f.m598c(iArr2, m602c);
        }
    }

    /* renamed from: a */
    public static void m981a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(6, iArr, iArr2) != 0 || (iArr2[5] == -1 && AbstractC12894f.m605b(iArr2, f26044a))) {
            m983a(iArr2);
        }
    }

    /* renamed from: a */
    public static void m980a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12894f.m613a(iArr, iArr2, iArr3) != 0 || (iArr3[5] == -1 && AbstractC12894f.m605b(iArr3, f26044a))) {
            m983a(iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m984a(BigInteger bigInteger) {
        int[] m620a = AbstractC12894f.m620a(bigInteger);
        if (m620a[5] == -1 && AbstractC12894f.m605b(m620a, f26044a)) {
            AbstractC12894f.m593d(f26044a, m620a);
        }
        return m620a;
    }

    /* renamed from: b */
    private static void m979b(int[] iArr) {
        long j = (iArr[0] & 4294967295L) - 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (iArr[1] & 4294967295L);
            iArr[1] = (int) j3;
            j2 = j3 >> 32;
        }
        long j4 = j2 + ((4294967295L & iArr[2]) - 1);
        iArr[2] = (int) j4;
        if ((j4 >> 32) != 0) {
            AbstractC12891c.m705a(6, iArr, 3);
        }
    }

    /* renamed from: b */
    public static void m978b(int[] iArr, int[] iArr2) {
        if (AbstractC12894f.m608b(iArr)) {
            AbstractC12894f.m594d(iArr2);
        } else {
            AbstractC12894f.m591e(f26044a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m977b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m602c = AbstractC12894f.m602c();
        AbstractC12894f.m597c(iArr, iArr2, m602c);
        m976c(m602c, iArr3);
    }

    /* renamed from: c */
    public static void m976c(int[] iArr, int[] iArr2) {
        long j = iArr[6] & 4294967295L;
        long j2 = iArr[7] & 4294967295L;
        long j3 = (iArr[10] & 4294967295L) + j;
        long j4 = (iArr[11] & 4294967295L) + j2;
        long j5 = (iArr[0] & 4294967295L) + j3 + 0;
        int i = (int) j5;
        long j6 = (j5 >> 32) + (iArr[1] & 4294967295L) + j4;
        iArr2[1] = (int) j6;
        long j7 = j3 + (iArr[8] & 4294967295L);
        long j8 = j4 + (iArr[9] & 4294967295L);
        long j9 = (j6 >> 32) + (iArr[2] & 4294967295L) + j7;
        long j10 = j9 & 4294967295L;
        long j11 = (j9 >> 32) + (iArr[3] & 4294967295L) + j8;
        iArr2[3] = (int) j11;
        long j12 = (j11 >> 32) + (iArr[4] & 4294967295L) + (j7 - j);
        iArr2[4] = (int) j12;
        long j13 = (j12 >> 32) + (iArr[5] & 4294967295L) + (j8 - j2);
        iArr2[5] = (int) j13;
        long j14 = j13 >> 32;
        long j15 = j10 + j14;
        long j16 = j14 + (i & 4294967295L);
        iArr2[0] = (int) j16;
        long j17 = j16 >> 32;
        if (j17 != 0) {
            long j18 = j17 + (4294967295L & iArr2[1]);
            iArr2[1] = (int) j18;
            j15 += j18 >> 32;
        }
        iArr2[2] = (int) j15;
        if (((j15 >> 32) == 0 || AbstractC12891c.m688b(6, iArr2, 3) == 0) && !(iArr2[5] == -1 && AbstractC12894f.m605b(iArr2, f26044a))) {
            return;
        }
        m983a(iArr2);
    }

    /* renamed from: c */
    public static void m975c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12894f.m592d(iArr, iArr2, iArr3) != 0 || (iArr3[11] == -1 && AbstractC12891c.m679c(12, iArr3, f26045b))) {
            int[] iArr4 = f26046c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(12, iArr3, f26046c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m974d(int[] iArr, int[] iArr2) {
        int[] m602c = AbstractC12894f.m602c();
        AbstractC12894f.m598c(iArr, m602c);
        m976c(m602c, iArr2);
    }

    /* renamed from: d */
    public static void m973d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12894f.m591e(iArr, iArr2, iArr3) != 0) {
            m979b(iArr3);
        }
    }

    /* renamed from: e */
    public static void m972e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(6, iArr, 0, iArr2) != 0 || (iArr2[5] == -1 && AbstractC12894f.m605b(iArr2, f26044a))) {
            m983a(iArr2);
        }
    }
}
