package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12897i;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ce */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12803ce {

    /* renamed from: a */
    private static final long[] f26006a = {878416384462358536L, 3513665537849438403L, -9076969306111048948L, 585610922974906400L, 34087042};

    /* renamed from: a */
    protected static void m1079a(long j, long j2, long[] jArr, int i) {
        long[] jArr2 = {0, j2, jArr2[1] << 1, jArr2[2] ^ j2, jArr2[2] << 1, jArr2[4] ^ j2, jArr2[3] << 1, jArr2[6] ^ j2};
        long j3 = jArr2[((int) j) & 7];
        long j4 = 0;
        int i2 = 48;
        do {
            int i3 = (int) (j >>> i2);
            long j5 = (jArr2[i3 & 7] ^ (jArr2[(i3 >>> 3) & 7] << 3)) ^ (jArr2[(i3 >>> 6) & 7] << 6);
            j3 ^= j5 << i2;
            j4 ^= j5 >>> (-i2);
            i2 -= 9;
        } while (i2 > 0);
        jArr[i] = 144115188075855871L & j3;
        jArr[i + 1] = (((((j & 72198606942111744L) & ((j2 << 7) >> 63)) >>> 8) ^ j4) << 7) ^ (j3 >>> 57);
    }

    /* renamed from: a */
    protected static void m1077a(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = jArr[9];
        jArr[0] = j ^ (j2 << 57);
        jArr[1] = (j2 >>> 7) ^ (j3 << 50);
        jArr[2] = (j3 >>> 14) ^ (j4 << 43);
        jArr[3] = (j4 >>> 21) ^ (j5 << 36);
        jArr[4] = (j5 >>> 28) ^ (j6 << 29);
        jArr[5] = (j6 >>> 35) ^ (j7 << 22);
        jArr[6] = (j7 >>> 42) ^ (j8 << 15);
        jArr[7] = (j8 >>> 49) ^ (j9 << 8);
        jArr[8] = (j9 >>> 56) ^ (j10 << 1);
        jArr[9] = j10 >>> 63;
    }

    /* renamed from: a */
    public static void m1076a(long[] jArr, int i) {
        int i2 = i + 4;
        long j = jArr[i2];
        long j2 = j >>> 27;
        jArr[i] = ((j2 << 12) ^ (((j2 << 5) ^ j2) ^ (j2 << 7))) ^ jArr[i];
        jArr[i2] = j & 134217727;
    }

    /* renamed from: a */
    public static void m1075a(long[] jArr, int i, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(9);
        m1062h(jArr, m691b);
        while (true) {
            m1070c(m691b, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1062h(jArr2, m691b);
        }
    }

    /* renamed from: a */
    public static void m1074a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
    }

    /* renamed from: a */
    public static void m1073a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr2[4] ^ jArr[4];
    }

    /* renamed from: a */
    public static long[] m1078a(BigInteger bigInteger) {
        long[] m532a = AbstractC12897i.m532a(bigInteger);
        m1076a(m532a, 0);
        return m532a;
    }

    /* renamed from: b */
    public static void m1072b(long[] jArr, long[] jArr2) {
        if (AbstractC12897i.m528b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m533a = AbstractC12897i.m533a();
        long[] m533a2 = AbstractC12897i.m533a();
        m1066e(jArr, m533a);
        m1069c(m533a, jArr, m533a);
        m1075a(m533a, 2, m533a2);
        m1069c(m533a2, m533a, m533a2);
        m1075a(m533a2, 4, m533a);
        m1069c(m533a, m533a2, m533a);
        m1075a(m533a, 8, m533a2);
        m1069c(m533a2, m533a, m533a2);
        m1066e(m533a2, m533a2);
        m1069c(m533a2, jArr, m533a2);
        m1075a(m533a2, 17, m533a);
        m1069c(m533a, m533a2, m533a);
        m1066e(m533a, m533a);
        m1069c(m533a, jArr, m533a);
        m1075a(m533a, 35, m533a2);
        m1069c(m533a2, m533a, m533a2);
        m1075a(m533a2, 70, m533a);
        m1069c(m533a, m533a2, m533a);
        m1066e(m533a, m533a);
        m1069c(m533a, jArr, m533a);
        m1075a(m533a, 141, m533a2);
        m1069c(m533a2, m533a, m533a2);
        m1066e(m533a2, jArr2);
    }

    /* renamed from: b */
    public static void m1071b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr[7] ^ jArr2[7];
        jArr3[8] = jArr2[8] ^ jArr[8];
    }

    /* renamed from: c */
    public static void m1070c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = j5 ^ ((((j9 >>> 27) ^ (j9 >>> 22)) ^ (j9 >>> 20)) ^ (j9 >>> 15));
        long j11 = j ^ ((((j6 << 37) ^ (j6 << 42)) ^ (j6 << 44)) ^ (j6 << 49));
        long j12 = (j2 ^ ((((j7 << 37) ^ (j7 << 42)) ^ (j7 << 44)) ^ (j7 << 49))) ^ ((((j6 >>> 27) ^ (j6 >>> 22)) ^ (j6 >>> 20)) ^ (j6 >>> 15));
        long j13 = j10 >>> 27;
        jArr2[0] = (((j11 ^ j13) ^ (j13 << 5)) ^ (j13 << 7)) ^ (j13 << 12);
        jArr2[1] = j12;
        jArr2[2] = (j3 ^ ((((j8 << 37) ^ (j8 << 42)) ^ (j8 << 44)) ^ (j8 << 49))) ^ ((((j7 >>> 27) ^ (j7 >>> 22)) ^ (j7 >>> 20)) ^ (j7 >>> 15));
        jArr2[3] = (j4 ^ ((((j9 << 37) ^ (j9 << 42)) ^ (j9 << 44)) ^ (j9 << 49))) ^ ((((j8 >>> 27) ^ (j8 >>> 22)) ^ (j8 >>> 20)) ^ (j8 >>> 15));
        jArr2[4] = 134217727 & j10;
    }

    /* renamed from: c */
    public static void m1069c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m529b = AbstractC12897i.m529b();
        m1065e(jArr, jArr2, m529b);
        m1070c(m529b, jArr3);
    }

    /* renamed from: d */
    public static void m1068d(long[] jArr, long[] jArr2) {
        long[] m533a = AbstractC12897i.m533a();
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        long j = (m721a & 4294967295L) | (m721a2 << 32);
        m533a[0] = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long m721a4 = C12889a.m721a(jArr[3]);
        m533a[1] = (m721a3 >>> 32) | ((-4294967296L) & m721a4);
        long m721a5 = C12889a.m721a(jArr[4]);
        m533a[2] = m721a5 >>> 32;
        m1069c(m533a, f26006a, jArr2);
        jArr2[0] = jArr2[0] ^ j;
        jArr2[1] = jArr2[1] ^ ((m721a3 & 4294967295L) | (m721a4 << 32));
        jArr2[2] = jArr2[2] ^ (4294967295L & m721a5);
    }

    /* renamed from: d */
    public static void m1067d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m529b = AbstractC12897i.m529b();
        m1065e(jArr, jArr2, m529b);
        m1071b(jArr3, m529b, jArr3);
    }

    /* renamed from: e */
    public static void m1066e(long[] jArr, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(9);
        m1062h(jArr, m691b);
        m1070c(m691b, jArr2);
    }

    /* renamed from: e */
    protected static void m1065e(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[5];
        long[] jArr5 = new long[5];
        m1063g(jArr, jArr4);
        m1063g(jArr2, jArr5);
        long[] jArr6 = new long[26];
        m1079a(jArr4[0], jArr5[0], jArr6, 0);
        m1079a(jArr4[1], jArr5[1], jArr6, 2);
        m1079a(jArr4[2], jArr5[2], jArr6, 4);
        m1079a(jArr4[3], jArr5[3], jArr6, 6);
        m1079a(jArr4[4], jArr5[4], jArr6, 8);
        long j = jArr4[0] ^ jArr4[1];
        long j2 = jArr5[0] ^ jArr5[1];
        long j3 = jArr4[0] ^ jArr4[2];
        long j4 = jArr5[0] ^ jArr5[2];
        long j5 = jArr4[2] ^ jArr4[4];
        long j6 = jArr5[2] ^ jArr5[4];
        long j7 = jArr4[3] ^ jArr4[4];
        long j8 = jArr5[3] ^ jArr5[4];
        m1079a(j3 ^ jArr4[3], j4 ^ jArr5[3], jArr6, 18);
        m1079a(j5 ^ jArr4[1], j6 ^ jArr5[1], jArr6, 20);
        long j9 = j ^ j7;
        long j10 = j2 ^ j8;
        m1079a(j9, j10, jArr6, 22);
        m1079a(j9 ^ jArr4[2], jArr5[2] ^ j10, jArr6, 24);
        m1079a(j, j2, jArr6, 10);
        m1079a(j3, j4, jArr6, 12);
        m1079a(j5, j6, jArr6, 14);
        m1079a(j7, j8, jArr6, 16);
        jArr3[0] = jArr6[0];
        jArr3[9] = jArr6[9];
        long j11 = jArr6[0] ^ jArr6[1];
        long j12 = jArr6[2] ^ j11;
        long j13 = jArr6[10] ^ j12;
        jArr3[1] = j13;
        long j14 = jArr6[3] ^ jArr6[4];
        long j15 = j12 ^ (j14 ^ (jArr6[11] ^ jArr6[12]));
        jArr3[2] = j15;
        long j16 = jArr6[5] ^ jArr6[6];
        long j17 = ((j11 ^ j14) ^ j16) ^ jArr6[8];
        long j18 = jArr6[13] ^ jArr6[14];
        jArr3[3] = (j17 ^ j18) ^ ((jArr6[18] ^ jArr6[22]) ^ jArr6[24]);
        long j19 = (jArr6[7] ^ jArr6[8]) ^ jArr6[9];
        long j20 = j19 ^ jArr6[17];
        jArr3[8] = j20;
        long j21 = (j19 ^ j16) ^ (jArr6[15] ^ jArr6[16]);
        jArr3[7] = j21;
        long j22 = j13 ^ j21;
        long j23 = (jArr6[19] ^ jArr6[20]) ^ (jArr6[25] ^ jArr6[24]);
        jArr3[4] = (j23 ^ (jArr6[18] ^ jArr6[23])) ^ j22;
        jArr3[5] = ((j15 ^ j20) ^ j23) ^ (jArr6[21] ^ jArr6[22]);
        jArr3[6] = ((((jArr6[9] ^ (j17 ^ jArr6[0])) ^ j18) ^ jArr6[21]) ^ jArr6[23]) ^ jArr6[25];
        m1077a(jArr3);
    }

    /* renamed from: f */
    public static void m1064f(long[] jArr, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(9);
        m1062h(jArr, m691b);
        m1071b(jArr2, m691b, jArr2);
    }

    /* renamed from: g */
    protected static void m1063g(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        jArr2[0] = j & 144115188075855871L;
        jArr2[1] = ((j >>> 57) ^ (j2 << 7)) & 144115188075855871L;
        jArr2[2] = ((j2 >>> 50) ^ (j3 << 14)) & 144115188075855871L;
        jArr2[3] = ((j3 >>> 43) ^ (j4 << 21)) & 144115188075855871L;
        jArr2[4] = (j4 >>> 36) ^ (j5 << 28);
    }

    /* renamed from: h */
    protected static void m1062h(long[] jArr, long[] jArr2) {
        for (int i = 0; i < 4; i++) {
            C12889a.m720a(jArr[i], jArr2, i << 1);
        }
        jArr2[8] = C12889a.m718c((int) jArr[4]);
    }
}
