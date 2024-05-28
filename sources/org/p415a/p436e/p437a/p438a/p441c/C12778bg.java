package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12894f;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bg */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12778bg {

    /* renamed from: a */
    private static final long[] f25991a = {-5270498306774157648L, 5270498306774195053L, 19634136210L};

    /* renamed from: a */
    protected static void m1150a(long j, long j2, long[] jArr, int i) {
        long[] jArr2 = {0, j2, jArr2[1] << 1, jArr2[2] ^ j2, jArr2[2] << 1, jArr2[4] ^ j2, jArr2[3] << 1, jArr2[6] ^ j2};
        long j3 = jArr2[((int) j) & 3];
        long j4 = 0;
        int i2 = 47;
        do {
            int i3 = (int) (j >>> i2);
            long j5 = (jArr2[i3 & 7] ^ (jArr2[(i3 >>> 3) & 7] << 3)) ^ (jArr2[(i3 >>> 6) & 7] << 6);
            j3 ^= j5 << i2;
            j4 ^= j5 >>> (-i2);
            i2 -= 9;
        } while (i2 > 0);
        jArr[i] = 36028797018963967L & j3;
        jArr[i + 1] = (j3 >>> 55) ^ (j4 << 9);
    }

    /* renamed from: a */
    protected static void m1148a(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        jArr[0] = j ^ (j2 << 55);
        jArr[1] = (j2 >>> 9) ^ (j3 << 46);
        jArr[2] = (j3 >>> 18) ^ (j4 << 37);
        jArr[3] = (j4 >>> 27) ^ (j5 << 28);
        jArr[4] = (j5 >>> 36) ^ (j6 << 19);
        jArr[5] = j6 >>> 45;
    }

    /* renamed from: a */
    public static void m1147a(long[] jArr, int i) {
        int i2 = i + 2;
        long j = jArr[i2];
        long j2 = j >>> 35;
        jArr[i] = ((j2 << 7) ^ (((j2 << 3) ^ j2) ^ (j2 << 6))) ^ jArr[i];
        jArr[i2] = j & 34359738367L;
    }

    /* renamed from: a */
    public static void m1146a(long[] jArr, int i, long[] jArr2) {
        long[] m595d = AbstractC12894f.m595d();
        m1134g(jArr, m595d);
        while (true) {
            m1141c(m595d, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1134g(jArr2, m595d);
        }
    }

    /* renamed from: a */
    public static void m1145a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    /* renamed from: a */
    public static void m1144a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr2[2] ^ jArr[2];
    }

    /* renamed from: a */
    public static long[] m1149a(BigInteger bigInteger) {
        long[] m609b = AbstractC12894f.m609b(bigInteger);
        m1147a(m609b, 0);
        return m609b;
    }

    /* renamed from: b */
    public static void m1143b(long[] jArr, long[] jArr2) {
        if (AbstractC12894f.m603b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m610b = AbstractC12894f.m610b();
        long[] m610b2 = AbstractC12894f.m610b();
        m1137e(jArr, m610b);
        m1146a(m610b, 1, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b2, 1, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b, 3, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b2, 3, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b, 9, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b2, 9, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b, 27, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b2, 27, m610b2);
        m1140c(m610b, m610b2, m610b);
        m1146a(m610b, 81, m610b2);
        m1140c(m610b, m610b2, jArr2);
    }

    /* renamed from: b */
    public static void m1142b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr2[5] ^ jArr[5];
    }

    /* renamed from: c */
    public static void m1141c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = j4 ^ ((((j6 >>> 35) ^ (j6 >>> 32)) ^ (j6 >>> 29)) ^ (j6 >>> 28));
        long j8 = j2 ^ ((((j5 << 29) ^ (j5 << 32)) ^ (j5 << 35)) ^ (j5 << 36));
        long j9 = (j3 ^ ((((j6 << 29) ^ (j6 << 32)) ^ (j6 << 35)) ^ (j6 << 36))) ^ ((j5 >>> 28) ^ (((j5 >>> 35) ^ (j5 >>> 32)) ^ (j5 >>> 29)));
        long j10 = j ^ ((((j7 << 29) ^ (j7 << 32)) ^ (j7 << 35)) ^ (j7 << 36));
        long j11 = j8 ^ ((j7 >>> 28) ^ (((j7 >>> 35) ^ (j7 >>> 32)) ^ (j7 >>> 29)));
        long j12 = j9 >>> 35;
        jArr2[0] = (((j10 ^ j12) ^ (j12 << 3)) ^ (j12 << 6)) ^ (j12 << 7);
        jArr2[1] = j11;
        jArr2[2] = 34359738367L & j9;
    }

    /* renamed from: c */
    public static void m1140c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m595d = AbstractC12894f.m595d();
        m1136e(jArr, jArr2, m595d);
        m1141c(m595d, jArr3);
    }

    /* renamed from: d */
    public static void m1139d(long[] jArr, long[] jArr2) {
        long[] m610b = AbstractC12894f.m610b();
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        m610b[0] = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long j = m721a3 & 4294967295L;
        m610b[1] = m721a3 >>> 32;
        m1140c(m610b, f25991a, jArr2);
        jArr2[0] = jArr2[0] ^ ((m721a & 4294967295L) | (m721a2 << 32));
        jArr2[1] = jArr2[1] ^ j;
    }

    /* renamed from: d */
    public static void m1138d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m595d = AbstractC12894f.m595d();
        m1136e(jArr, jArr2, m595d);
        m1142b(jArr3, m595d, jArr3);
    }

    /* renamed from: e */
    public static void m1137e(long[] jArr, long[] jArr2) {
        long[] m595d = AbstractC12894f.m595d();
        m1134g(jArr, m595d);
        m1141c(m595d, jArr2);
    }

    /* renamed from: e */
    protected static void m1136e(long[] jArr, long[] jArr2, long[] jArr3) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = (jArr[2] << 18) ^ (j2 >>> 46);
        long j4 = ((j2 << 9) ^ (j >>> 55)) & 36028797018963967L;
        long j5 = j & 36028797018963967L;
        long j6 = jArr2[0];
        long j7 = jArr2[1];
        long j8 = (j7 >>> 46) ^ (jArr2[2] << 18);
        long j9 = ((j6 >>> 55) ^ (j7 << 9)) & 36028797018963967L;
        long j10 = j6 & 36028797018963967L;
        long[] jArr4 = new long[10];
        m1150a(j5, j10, jArr4, 0);
        m1150a(j3, j8, jArr4, 2);
        long j11 = (j5 ^ j4) ^ j3;
        long j12 = (j10 ^ j9) ^ j8;
        m1150a(j11, j12, jArr4, 4);
        long j13 = (j4 << 1) ^ (j3 << 2);
        long j14 = (j9 << 1) ^ (j8 << 2);
        m1150a(j5 ^ j13, j10 ^ j14, jArr4, 6);
        m1150a(j11 ^ j13, j12 ^ j14, jArr4, 8);
        long j15 = jArr4[6] ^ jArr4[8];
        long j16 = jArr4[7] ^ jArr4[9];
        long j17 = (j15 << 1) ^ jArr4[6];
        long j18 = (j15 ^ (j16 << 1)) ^ jArr4[7];
        long j19 = jArr4[0];
        long j20 = (jArr4[1] ^ jArr4[0]) ^ jArr4[4];
        long j21 = jArr4[1] ^ jArr4[5];
        long j22 = ((j17 ^ j19) ^ (jArr4[2] << 4)) ^ (jArr4[2] << 1);
        long j23 = (((j18 ^ j20) ^ (jArr4[3] << 4)) ^ (jArr4[3] << 1)) ^ (j22 >>> 55);
        long j24 = (j21 ^ j16) ^ (j23 >>> 55);
        long j25 = j23 & 36028797018963967L;
        long j26 = ((j22 & 36028797018963967L) >>> 1) ^ ((j25 & 1) << 54);
        long j27 = j26 ^ (j26 << 1);
        long j28 = j27 ^ (j27 << 2);
        long j29 = j28 ^ (j28 << 4);
        long j30 = j29 ^ (j29 << 8);
        long j31 = j30 ^ (j30 << 16);
        long j32 = (j31 ^ (j31 << 32)) & 36028797018963967L;
        long j33 = ((j25 >>> 1) ^ ((j24 & 1) << 54)) ^ (j32 >>> 54);
        long j34 = j33 ^ (j33 << 1);
        long j35 = j34 ^ (j34 << 2);
        long j36 = j35 ^ (j35 << 4);
        long j37 = j36 ^ (j36 << 8);
        long j38 = j37 ^ (j37 << 16);
        long j39 = (j38 ^ (j38 << 32)) & 36028797018963967L;
        long j40 = (j24 >>> 1) ^ (j39 >>> 54);
        long j41 = j40 ^ (j40 << 1);
        long j42 = j41 ^ (j41 << 2);
        long j43 = j42 ^ (j42 << 4);
        long j44 = j43 ^ (j43 << 8);
        long j45 = j44 ^ (j44 << 16);
        long j46 = j45 ^ (j45 << 32);
        jArr3[0] = j19;
        jArr3[1] = (j20 ^ j32) ^ jArr4[2];
        jArr3[2] = ((j21 ^ j39) ^ j32) ^ jArr4[3];
        jArr3[3] = j46 ^ j39;
        jArr3[4] = jArr4[2] ^ j46;
        jArr3[5] = jArr4[3];
        m1148a(jArr3);
    }

    /* renamed from: f */
    public static void m1135f(long[] jArr, long[] jArr2) {
        long[] m595d = AbstractC12894f.m595d();
        m1134g(jArr, m595d);
        m1142b(jArr2, m595d, jArr2);
    }

    /* renamed from: g */
    protected static void m1134g(long[] jArr, long[] jArr2) {
        C12889a.m720a(jArr[0], jArr2, 0);
        C12889a.m720a(jArr[1], jArr2, 2);
        long j = jArr[2];
        jArr2[4] = C12889a.m718c((int) j);
        jArr2[5] = C12889a.m722a((int) (j >>> 32)) & 4294967295L;
    }
}
