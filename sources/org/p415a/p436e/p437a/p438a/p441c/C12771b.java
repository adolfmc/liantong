package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12892d;
import org.p415a.p436e.p444c.AbstractC12896h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12771b {

    /* renamed from: a */
    static final int[] f25984a = {-1, -1, -1, -3};

    /* renamed from: b */
    static final int[] f25985b = {1, 0, 0, 4, -2, -1, 3, -4};

    /* renamed from: c */
    private static final int[] f25986c = {-1, -1, -1, -5, 1, 0, -4, 3};

    /* renamed from: a */
    public static void m1181a(int i, int[] iArr) {
        while (i != 0) {
            long j = i & 4294967295L;
            long j2 = (iArr[0] & 4294967295L) + j;
            iArr[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (iArr[1] & 4294967295L);
                iArr[1] = (int) j4;
                long j5 = (j4 >> 32) + (iArr[2] & 4294967295L);
                iArr[2] = (int) j5;
                j3 = j5 >> 32;
            }
            long j6 = j3 + (4294967295L & iArr[3]) + (j << 1);
            iArr[3] = (int) j6;
            i = (int) (j6 >> 32);
        }
    }

    /* renamed from: a */
    private static void m1179a(int[] iArr) {
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
        iArr[3] = (int) (j2 + (4294967295L & iArr[3]) + 2);
    }

    /* renamed from: a */
    public static void m1178a(int[] iArr, int i, int[] iArr2) {
        int[] m655c = AbstractC12892d.m655c();
        AbstractC12892d.m653c(iArr, m655c);
        while (true) {
            m1172c(m655c, iArr2);
            i--;
            if (i <= 0) {
                return;
            }
            AbstractC12892d.m653c(iArr2, m655c);
        }
    }

    /* renamed from: a */
    public static void m1177a(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m674d(4, iArr, iArr2) != 0 || ((iArr2[3] >>> 1) >= 2147483646 && AbstractC12892d.m658b(iArr2, f25984a))) {
            m1179a(iArr2);
        }
    }

    /* renamed from: a */
    public static void m1176a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12892d.m664a(iArr, iArr2, iArr3) != 0 || ((iArr3[3] >>> 1) >= 2147483646 && AbstractC12892d.m658b(iArr3, f25984a))) {
            m1179a(iArr3);
        }
    }

    /* renamed from: a */
    public static int[] m1180a(BigInteger bigInteger) {
        int[] m668a = AbstractC12892d.m668a(bigInteger);
        if ((m668a[3] >>> 1) >= 2147483646 && AbstractC12892d.m658b(m668a, f25984a)) {
            AbstractC12892d.m648d(f25984a, m668a);
        }
        return m668a;
    }

    /* renamed from: b */
    private static void m1175b(int[] iArr) {
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
        iArr[3] = (int) (j2 + ((4294967295L & iArr[3]) - 2));
    }

    /* renamed from: b */
    public static void m1174b(int[] iArr, int[] iArr2) {
        if (AbstractC12892d.m659b(iArr)) {
            AbstractC12892d.m649d(iArr2);
        } else {
            AbstractC12892d.m646e(f25984a, iArr, iArr2);
        }
    }

    /* renamed from: b */
    public static void m1173b(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m655c = AbstractC12892d.m655c();
        AbstractC12892d.m652c(iArr, iArr2, m655c);
        m1172c(m655c, iArr3);
    }

    /* renamed from: c */
    public static void m1172c(int[] iArr, int[] iArr2) {
        long j = iArr[7] & 4294967295L;
        long j2 = (iArr[3] & 4294967295L) + j;
        long j3 = (iArr[6] & 4294967295L) + (j << 1);
        long j4 = (iArr[2] & 4294967295L) + j3;
        long j5 = (iArr[5] & 4294967295L) + (j3 << 1);
        long j6 = (iArr[1] & 4294967295L) + j5;
        long j7 = (iArr[4] & 4294967295L) + (j5 << 1);
        long j8 = (iArr[0] & 4294967295L) + j7;
        iArr2[0] = (int) j8;
        long j9 = j6 + (j8 >>> 32);
        iArr2[1] = (int) j9;
        long j10 = j4 + (j9 >>> 32);
        iArr2[2] = (int) j10;
        long j11 = j2 + (j7 << 1) + (j10 >>> 32);
        iArr2[3] = (int) j11;
        m1181a((int) (j11 >>> 32), iArr2);
    }

    /* renamed from: c */
    public static void m1171c(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12892d.m647d(iArr, iArr2, iArr3) != 0 || ((iArr3[7] >>> 1) >= 2147483646 && AbstractC12896h.m542c(iArr3, f25985b))) {
            int[] iArr4 = f25986c;
            AbstractC12891c.m698a(iArr4.length, iArr4, iArr3);
        }
    }

    /* renamed from: d */
    public static void m1170d(int[] iArr, int[] iArr2) {
        int[] m655c = AbstractC12892d.m655c();
        AbstractC12892d.m653c(iArr, m655c);
        m1172c(m655c, iArr2);
    }

    /* renamed from: d */
    public static void m1169d(int[] iArr, int[] iArr2, int[] iArr3) {
        if (AbstractC12892d.m646e(iArr, iArr2, iArr3) != 0) {
            m1175b(iArr3);
        }
    }

    /* renamed from: e */
    public static void m1168e(int[] iArr, int[] iArr2) {
        if (AbstractC12891c.m700a(4, iArr, 0, iArr2) != 0 || ((iArr2[3] >>> 1) >= 2147483646 && AbstractC12892d.m658b(iArr2, f25984a))) {
            m1179a(iArr2);
        }
    }
}
