package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12893e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12825h {

    /* renamed from: a */
    static final int[] f26023a = {Integer.MAX_VALUE, -1, -1, -1, -1};

    /* renamed from: b */
    static final int[] f26024b = {1, 1073741825, 0, 0, 0, -2, -2, -1, -1, -1};

    /* renamed from: c */
    private static final int[] f26025c = {-1, -1073741826, -1, -1, -1, 1, 1};

    /* renamed from: a */
    public static void m1021a(int i, int[] iArr) {
        if ((i == 0 || AbstractC12893e.m635b(-2147483647, i, iArr, 0) == 0) && !(iArr[4] == -1 && AbstractC12893e.m633b(iArr, f26023a))) {
            return;
        }
        AbstractC12891c.m690b(5, -2147483647, iArr);
    }

    /* renamed from: a */
    public static void m1019a(int[] iArr, int i, int[] iArr2) {
        int[] m636b = AbstractC12893e.m636b();
        AbstractC12893e.m630c(iArr, m636b);
        while (true) {
            m1014c(m636b, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12893e.m630c(iArr2, m636b);
        }
    }

    /* renamed from: a */
    public static void m1018a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(5, iArr, iArr2) != 0 || (iArr2[4] == -1 && AbstractC12893e.m633b(iArr2, f26023a))) {
            AbstractC12891c.m690b(5, -2147483647, iArr2);
        }
    }

    /* renamed from: a */
    public static void m1017a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12893e.m637a(iArr, iArr2, iArr3) != 0 || (iArr3[4] == -1 && AbstractC12893e.m633b(iArr3, f26023a))) {
            AbstractC12891c.m690b(5, -2147483647, iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1020a(BigInteger bigInteger) {
        int[] m641a = AbstractC12893e.m641a(bigInteger);
        if (m641a[4] == -1 && AbstractC12893e.m633b(m641a, f26023a)) {
            AbstractC12893e.m627d(f26023a, m641a);
        }
        return m641a;
    }

    /* renamed from: b */
    public static void m1016b(int[] iArr, int[] iArr2) {
        if (AbstractC12893e.m634b(iArr)) {
            AbstractC12893e.m628d(iArr2);
        } else {
            AbstractC12893e.m625e(f26023a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1015b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m636b = AbstractC12893e.m636b();
        AbstractC12893e.m629c(iArr, iArr2, m636b);
        m1014c(m636b, iArr3);
    }

    /* renamed from: c */
    public static void m1014c(int[] iArr, int[] iArr2) {
        long j = iArr[5] & 4294967295L;
        long j2 = iArr[6] & 4294967295L;
        long j3 = iArr[7] & 4294967295L;
        long j4 = iArr[8] & 4294967295L;
        long j5 = iArr[9] & 4294967295L;
        long j6 = (iArr[0] & 4294967295L) + j + (j << 31) + 0;
        iArr2[0] = (int) j6;
        long j7 = (j6 >>> 32) + (iArr[1] & 4294967295L) + j2 + (j2 << 31);
        iArr2[1] = (int) j7;
        long j8 = (j7 >>> 32) + (iArr[2] & 4294967295L) + j3 + (j3 << 31);
        iArr2[2] = (int) j8;
        long j9 = (j8 >>> 32) + (iArr[3] & 4294967295L) + j4 + (j4 << 31);
        iArr2[3] = (int) j9;
        long j10 = (j9 >>> 32) + (4294967295L & iArr[4]) + j5 + (j5 << 31);
        iArr2[4] = (int) j10;
        m1021a((int) (j10 >>> 32), iArr2);
    }

    /* renamed from: c */
    public static void m1013c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12893e.m626d(iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && AbstractC12891c.m679c(10, iArr3, f26024b))) {
            int[] iArr4 = f26025c;
            if (AbstractC12891c.m698a(iArr4.length, iArr4, iArr3) != 0) {
                AbstractC12891c.m688b(10, iArr3, f26025c.length);
            }
        }
    }

    /* renamed from: d */
    public static void m1012d(int[] iArr, int[] iArr2) {
        int[] m636b = AbstractC12893e.m636b();
        AbstractC12893e.m630c(iArr, m636b);
        m1014c(m636b, iArr2);
    }

    /* renamed from: d */
    public static void m1011d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12893e.m625e(iArr, iArr2, iArr3) != 0) {
            AbstractC12891c.m677d(5, -2147483647, iArr3);
        }
    }

    /* renamed from: e */
    public static void m1010e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(5, iArr, 0, iArr2) != 0 || (iArr2[4] == -1 && AbstractC12893e.m633b(iArr2, f26023a))) {
            AbstractC12891c.m690b(5, -2147483647, iArr2);
        }
    }
}
