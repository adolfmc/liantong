package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12899k;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ck */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12809ck {
    /* renamed from: a */
    protected static void m1060a(long[] jArr) {
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
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        jArr[0] = j ^ (j2 << 59);
        jArr[1] = (j2 >>> 5) ^ (j3 << 54);
        jArr[2] = (j3 >>> 10) ^ (j4 << 49);
        jArr[3] = (j4 >>> 15) ^ (j5 << 44);
        jArr[4] = (j5 >>> 20) ^ (j6 << 39);
        jArr[5] = (j6 >>> 25) ^ (j7 << 34);
        jArr[6] = (j7 >>> 30) ^ (j8 << 29);
        jArr[7] = (j8 >>> 35) ^ (j9 << 24);
        jArr[8] = (j9 >>> 40) ^ (j10 << 19);
        jArr[9] = (j10 >>> 45) ^ (j11 << 14);
        jArr[10] = (j11 >>> 50) ^ (j12 << 9);
        jArr[11] = ((j12 >>> 55) ^ (j13 << 4)) ^ (j14 << 63);
        jArr[12] = (j13 >>> 60) ^ (j14 >>> 1);
        jArr[13] = 0;
    }

    /* renamed from: a */
    public static void m1059a(long[] jArr, int i) {
        int i2 = i + 6;
        long j = jArr[i2];
        long j2 = j >>> 25;
        jArr[i] = jArr[i] ^ j2;
        int i3 = i + 1;
        jArr[i3] = (j2 << 23) ^ jArr[i3];
        jArr[i2] = j & 33554431;
    }

    /* renamed from: a */
    public static void m1058a(long[] jArr, int i, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(13);
        m1044h(jArr, m691b);
        while (true) {
            m1052c(m691b, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1044h(jArr2, m691b);
        }
    }

    /* renamed from: a */
    protected static void m1057a(long[] jArr, long j, long[] jArr2, int i) {
        long[] jArr3 = {0, j, jArr3[1] << 1, jArr3[2] ^ j, jArr3[2] << 1, jArr3[4] ^ j, jArr3[3] << 1, jArr3[6] ^ j};
        for (int i2 = 0; i2 < 7; i2++) {
            long j2 = jArr[i2];
            int i3 = (int) j2;
            long j3 = 0;
            long j4 = jArr3[i3 & 7] ^ (jArr3[(i3 >>> 3) & 7] << 3);
            int i4 = 54;
            do {
                int i5 = (int) (j2 >>> i4);
                long j5 = jArr3[i5 & 7] ^ (jArr3[(i5 >>> 3) & 7] << 3);
                j4 ^= j5 << i4;
                j3 ^= j5 >>> (-i4);
                i4 -= 6;
            } while (i4 > 0);
            int i6 = i + i2;
            jArr2[i6] = jArr2[i6] ^ (576460752303423487L & j4);
            int i7 = i6 + 1;
            jArr2[i7] = jArr2[i7] ^ ((j3 << 5) ^ (j4 >>> 59));
        }
    }

    /* renamed from: a */
    public static void m1056a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
        jArr2[5] = jArr[5];
        jArr2[6] = jArr[6];
    }

    /* renamed from: a */
    public static void m1055a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr2[6] ^ jArr[6];
    }

    /* renamed from: a */
    public static long[] m1061a(BigInteger bigInteger) {
        long[] m523a = AbstractC12899k.m523a(bigInteger);
        m1059a(m523a, 0);
        return m523a;
    }

    /* renamed from: b */
    public static void m1054b(long[] jArr, long[] jArr2) {
        if (AbstractC12899k.m519b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m524a = AbstractC12899k.m524a();
        long[] m524a2 = AbstractC12899k.m524a();
        long[] m524a3 = AbstractC12899k.m524a();
        m1048e(jArr, m524a);
        m1058a(m524a, 1, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a2, 1, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a, 3, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a, 6, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a, 12, m524a2);
        m1051c(m524a, m524a2, m524a3);
        m1058a(m524a3, 24, m524a);
        m1058a(m524a, 24, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a, 48, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a, 96, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1058a(m524a, 192, m524a2);
        m1051c(m524a, m524a2, m524a);
        m1051c(m524a, m524a3, jArr2);
    }

    /* renamed from: b */
    public static void m1053b(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 13; i++) {
            jArr3[i] = jArr[i] ^ jArr2[i];
        }
    }

    /* renamed from: c */
    public static void m1052c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[12];
        long j10 = j7 ^ ((j9 >>> 25) ^ (j9 << 62));
        long j11 = j8 ^ (j9 >>> 2);
        long j12 = jArr[11];
        long j13 = j5 ^ (j12 << 39);
        long j14 = (j6 ^ (j9 << 39)) ^ ((j12 >>> 25) ^ (j12 << 62));
        long j15 = j10 ^ (j12 >>> 2);
        long j16 = jArr[10];
        long j17 = j4 ^ (j16 << 39);
        long j18 = j13 ^ ((j16 >>> 25) ^ (j16 << 62));
        long j19 = j14 ^ (j16 >>> 2);
        long j20 = jArr[9];
        long j21 = j3 ^ (j20 << 39);
        long j22 = j17 ^ ((j20 >>> 25) ^ (j20 << 62));
        long j23 = j18 ^ (j20 >>> 2);
        long j24 = jArr[8];
        long j25 = j ^ (j11 << 39);
        long j26 = (j21 ^ ((j24 >>> 25) ^ (j24 << 62))) ^ (j11 >>> 2);
        long j27 = j15 >>> 25;
        jArr2[0] = j25 ^ j27;
        long j28 = j27 << 23;
        jArr2[1] = j28 ^ ((j2 ^ (j24 << 39)) ^ ((j11 >>> 25) ^ (j11 << 62)));
        jArr2[2] = j26;
        jArr2[3] = j22 ^ (j24 >>> 2);
        jArr2[4] = j23;
        jArr2[5] = j19;
        jArr2[6] = j15 & 33554431;
    }

    /* renamed from: c */
    public static void m1051c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m520b = AbstractC12899k.m520b();
        m1047e(jArr, jArr2, m520b);
        m1052c(m520b, jArr3);
    }

    /* renamed from: d */
    public static void m1050d(long[] jArr, long[] jArr2) {
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        long j = (m721a & 4294967295L) | (m721a2 << 32);
        long j2 = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long m721a4 = C12889a.m721a(jArr[3]);
        long j3 = (m721a3 & 4294967295L) | (m721a4 << 32);
        long j4 = (m721a3 >>> 32) | (m721a4 & (-4294967296L));
        long m721a5 = C12889a.m721a(jArr[4]);
        long m721a6 = C12889a.m721a(jArr[5]);
        long j5 = (m721a5 >>> 32) | (m721a6 & (-4294967296L));
        long m721a7 = C12889a.m721a(jArr[6]);
        long j6 = m721a7 & 4294967295L;
        long j7 = m721a7 >>> 32;
        jArr2[0] = j ^ (j2 << 44);
        jArr2[1] = (j3 ^ (j4 << 44)) ^ (j2 >>> 20);
        jArr2[2] = (((m721a5 & 4294967295L) | (m721a6 << 32)) ^ (j5 << 44)) ^ (j4 >>> 20);
        jArr2[3] = (((j7 << 44) ^ j6) ^ (j5 >>> 20)) ^ (j2 << 13);
        jArr2[4] = (j2 >>> 51) ^ ((j7 >>> 20) ^ (j4 << 13));
        jArr2[5] = (j5 << 13) ^ (j4 >>> 51);
        jArr2[6] = (j7 << 13) ^ (j5 >>> 51);
    }

    /* renamed from: d */
    public static void m1049d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m520b = AbstractC12899k.m520b();
        m1047e(jArr, jArr2, m520b);
        m1053b(jArr3, m520b, jArr3);
    }

    /* renamed from: e */
    public static void m1048e(long[] jArr, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(13);
        m1044h(jArr, m691b);
        m1052c(m691b, jArr2);
    }

    /* renamed from: e */
    protected static void m1047e(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[7];
        long[] jArr5 = new long[7];
        m1045g(jArr, jArr4);
        m1045g(jArr2, jArr5);
        for (int i = 0; i < 7; i++) {
            m1057a(jArr4, jArr5[i], jArr3, i);
        }
        m1060a(jArr3);
    }

    /* renamed from: f */
    public static void m1046f(long[] jArr, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(13);
        m1044h(jArr, m691b);
        m1053b(jArr2, m691b, jArr2);
    }

    /* renamed from: g */
    protected static void m1045g(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        jArr2[0] = j & 576460752303423487L;
        jArr2[1] = ((j >>> 59) ^ (j2 << 5)) & 576460752303423487L;
        jArr2[2] = ((j2 >>> 54) ^ (j3 << 10)) & 576460752303423487L;
        jArr2[3] = ((j3 >>> 49) ^ (j4 << 15)) & 576460752303423487L;
        jArr2[4] = ((j4 >>> 44) ^ (j5 << 20)) & 576460752303423487L;
        jArr2[5] = ((j5 >>> 39) ^ (j6 << 25)) & 576460752303423487L;
        jArr2[6] = (j6 >>> 34) ^ (j7 << 30);
    }

    /* renamed from: h */
    protected static void m1044h(long[] jArr, long[] jArr2) {
        for (int i = 0; i < 6; i++) {
            C12889a.m720a(jArr[i], jArr2, i << 1);
        }
        jArr2[12] = C12889a.m718c((int) jArr[6]);
    }
}
