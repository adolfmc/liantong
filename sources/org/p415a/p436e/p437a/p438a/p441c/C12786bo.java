package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12786bo {
    /* renamed from: a */
    protected static void m1133a(long j, long j2, long[] jArr, int i) {
        long[] jArr2 = {0, j2, jArr2[1] << 1, jArr2[2] ^ j2, jArr2[2] << 1, jArr2[4] ^ j2, jArr2[3] << 1, jArr2[6] ^ j2};
        int i2 = (int) j;
        long j3 = (jArr2[(i2 >>> 3) & 7] << 3) ^ jArr2[i2 & 7];
        long j4 = 0;
        int i3 = 36;
        do {
            int i4 = (int) (j >>> i3);
            long j5 = (((jArr2[i4 & 7] ^ (jArr2[(i4 >>> 3) & 7] << 3)) ^ (jArr2[(i4 >>> 6) & 7] << 6)) ^ (jArr2[(i4 >>> 9) & 7] << 9)) ^ (jArr2[(i4 >>> 12) & 7] << 12);
            j3 ^= j5 << i3;
            j4 ^= j5 >>> (-i3);
            i3 -= 15;
        } while (i3 > 0);
        jArr[i] = jArr[i] ^ (562949953421311L & j3);
        int i5 = i + 1;
        jArr[i5] = jArr[i5] ^ ((j3 >>> 49) ^ (j4 << 15));
    }

    /* renamed from: a */
    protected static void m1131a(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        jArr[0] = j ^ (j2 << 49);
        jArr[1] = (j2 >>> 15) ^ (j3 << 34);
        jArr[2] = (j3 >>> 30) ^ (j4 << 19);
        jArr[3] = ((j4 >>> 45) ^ (j5 << 4)) ^ (j6 << 53);
        jArr[4] = ((j5 >>> 60) ^ (j7 << 38)) ^ (j6 >>> 11);
        jArr[5] = (j7 >>> 26) ^ (j8 << 23);
        jArr[6] = j8 >>> 41;
        jArr[7] = 0;
    }

    /* renamed from: a */
    public static void m1130a(long[] jArr, int i) {
        int i2 = i + 3;
        long j = jArr[i2];
        long j2 = j >>> 1;
        jArr[i] = jArr[i] ^ ((j2 << 15) ^ j2);
        int i3 = i + 1;
        jArr[i3] = (j2 >>> 49) ^ jArr[i3];
        jArr[i2] = j & 1;
    }

    /* renamed from: a */
    public static void m1129a(long[] jArr, int i, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1116h(jArr, m539d);
        while (true) {
            m1124c(m539d, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1116h(jArr2, m539d);
        }
    }

    /* renamed from: a */
    public static void m1128a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    /* renamed from: a */
    public static void m1127a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    /* renamed from: a */
    public static long[] m1132a(BigInteger bigInteger) {
        long[] m553b = AbstractC12896h.m553b(bigInteger);
        m1130a(m553b, 0);
        return m553b;
    }

    /* renamed from: b */
    public static void m1126b(long[] jArr, long[] jArr2) {
        if (AbstractC12896h.m547b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m554b = AbstractC12896h.m554b();
        long[] m554b2 = AbstractC12896h.m554b();
        m1120e(jArr, m554b);
        m1129a(m554b, 1, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b2, 1, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b, 3, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b, 6, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b, 12, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b, 24, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b, 48, m554b2);
        m1123c(m554b, m554b2, m554b);
        m1129a(m554b, 96, m554b2);
        m1123c(m554b, m554b2, jArr2);
    }

    /* renamed from: b */
    public static void m1125b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr2[6] ^ jArr[6];
    }

    /* renamed from: c */
    public static void m1124c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = j5 ^ (j7 >>> 50);
        long j9 = (j4 ^ ((j7 >>> 1) ^ (j7 << 14))) ^ (j6 >>> 50);
        long j10 = j ^ (j8 << 63);
        long j11 = (j2 ^ (j6 << 63)) ^ ((j8 >>> 1) ^ (j8 << 14));
        long j12 = ((j3 ^ (j7 << 63)) ^ ((j6 >>> 1) ^ (j6 << 14))) ^ (j8 >>> 50);
        long j13 = j9 >>> 1;
        jArr2[0] = (j10 ^ j13) ^ (j13 << 15);
        jArr2[1] = (j13 >>> 49) ^ j11;
        jArr2[2] = j12;
        jArr2[3] = 1 & j9;
    }

    /* renamed from: c */
    public static void m1123c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m539d = AbstractC12896h.m539d();
        m1119e(jArr, jArr2, m539d);
        m1124c(m539d, jArr3);
    }

    /* renamed from: d */
    public static void m1122d(long[] jArr, long[] jArr2) {
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        long j = (m721a & 4294967295L) | (m721a2 << 32);
        long j2 = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long j3 = (m721a3 & 4294967295L) ^ (jArr[3] << 32);
        long j4 = m721a3 >>> 32;
        jArr2[0] = j ^ (j2 << 8);
        jArr2[1] = ((j3 ^ (j4 << 8)) ^ (j2 >>> 56)) ^ (j2 << 33);
        jArr2[2] = (j2 >>> 31) ^ ((j4 >>> 56) ^ (j4 << 33));
        jArr2[3] = j4 >>> 31;
    }

    /* renamed from: d */
    public static void m1121d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m539d = AbstractC12896h.m539d();
        m1119e(jArr, jArr2, m539d);
        m1125b(jArr3, m539d, jArr3);
    }

    /* renamed from: e */
    public static void m1120e(long[] jArr, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1116h(jArr, m539d);
        m1124c(m539d, jArr2);
    }

    /* renamed from: e */
    protected static void m1119e(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        m1117g(jArr, jArr4);
        m1117g(jArr2, jArr5);
        m1133a(jArr4[0], jArr5[0], jArr3, 0);
        m1133a(jArr4[1], jArr5[1], jArr3, 1);
        m1133a(jArr4[2], jArr5[2], jArr3, 2);
        m1133a(jArr4[3], jArr5[3], jArr3, 3);
        for (int i = 5; i > 0; i--) {
            jArr3[i] = jArr3[i] ^ jArr3[i - 1];
        }
        m1133a(jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        m1133a(jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i2 = 7; i2 > 1; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 2];
        }
        long j = jArr4[0] ^ jArr4[2];
        long j2 = jArr4[1] ^ jArr4[3];
        long j3 = jArr5[0] ^ jArr5[2];
        long j4 = jArr5[1] ^ jArr5[3];
        m1133a(j ^ j2, j3 ^ j4, jArr3, 3);
        long[] jArr6 = new long[3];
        m1133a(j, j3, jArr6, 0);
        m1133a(j2, j4, jArr6, 1);
        long j5 = jArr6[0];
        long j6 = jArr6[1];
        long j7 = jArr6[2];
        jArr3[2] = jArr3[2] ^ j5;
        jArr3[3] = (j5 ^ j6) ^ jArr3[3];
        jArr3[4] = jArr3[4] ^ (j7 ^ j6);
        jArr3[5] = jArr3[5] ^ j7;
        m1131a(jArr3);
    }

    /* renamed from: f */
    public static void m1118f(long[] jArr, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1116h(jArr, m539d);
        m1125b(jArr2, m539d, jArr2);
    }

    /* renamed from: g */
    protected static void m1117g(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = j & 562949953421311L;
        jArr2[1] = ((j >>> 49) ^ (j2 << 15)) & 562949953421311L;
        jArr2[2] = ((j2 >>> 34) ^ (j3 << 30)) & 562949953421311L;
        jArr2[3] = (j3 >>> 19) ^ (j4 << 45);
    }

    /* renamed from: h */
    protected static void m1116h(long[] jArr, long[] jArr2) {
        C12889a.m720a(jArr[0], jArr2, 0);
        C12889a.m720a(jArr[1], jArr2, 2);
        C12889a.m720a(jArr[2], jArr2, 4);
        jArr2[6] = jArr[3] & 1;
    }
}
