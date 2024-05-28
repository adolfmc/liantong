package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12892d;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.au */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12765au {
    /* renamed from: a */
    protected static void m1197a(long j, long j2, long[] jArr, int i) {
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
    public static void m1195a(long[] jArr, int i) {
        int i2 = i + 1;
        long j = jArr[i2];
        long j2 = j >>> 49;
        jArr[i] = (j2 ^ (j2 << 9)) ^ jArr[i];
        jArr[i2] = j & 562949953421311L;
    }

    /* renamed from: a */
    public static void m1194a(long[] jArr, int i, long[] jArr2) {
        long[] m650d = AbstractC12892d.m650d();
        m1182g(jArr, m650d);
        while (true) {
            m1189c(m650d, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1182g(jArr2, m650d);
        }
    }

    /* renamed from: a */
    public static void m1193a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
    }

    /* renamed from: a */
    public static void m1192a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr2[1] ^ jArr[1];
    }

    /* renamed from: a */
    public static long[] m1196a(BigInteger bigInteger) {
        long[] m660b = AbstractC12892d.m660b(bigInteger);
        m1195a(m660b, 0);
        return m660b;
    }

    /* renamed from: b */
    public static void m1191b(long[] jArr, long[] jArr2) {
        if (AbstractC12892d.m656b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m661b = AbstractC12892d.m661b();
        long[] m661b2 = AbstractC12892d.m661b();
        m1185e(jArr, m661b);
        m1188c(m661b, jArr, m661b);
        m1185e(m661b, m661b);
        m1188c(m661b, jArr, m661b);
        m1194a(m661b, 3, m661b2);
        m1188c(m661b2, m661b, m661b2);
        m1185e(m661b2, m661b2);
        m1188c(m661b2, jArr, m661b2);
        m1194a(m661b2, 7, m661b);
        m1188c(m661b, m661b2, m661b);
        m1194a(m661b, 14, m661b2);
        m1188c(m661b2, m661b, m661b2);
        m1194a(m661b2, 28, m661b);
        m1188c(m661b, m661b2, m661b);
        m1194a(m661b, 56, m661b2);
        m1188c(m661b2, m661b, m661b2);
        m1185e(m661b2, jArr2);
    }

    /* renamed from: b */
    public static void m1190b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    /* renamed from: c */
    public static void m1189c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = j3 ^ ((j4 >>> 40) ^ (j4 >>> 49));
        long j6 = j ^ ((j5 << 15) ^ (j5 << 24));
        long j7 = (j2 ^ ((j4 << 15) ^ (j4 << 24))) ^ ((j5 >>> 40) ^ (j5 >>> 49));
        long j8 = j7 >>> 49;
        jArr2[0] = (j6 ^ j8) ^ (j8 << 9);
        jArr2[1] = 562949953421311L & j7;
    }

    /* renamed from: c */
    public static void m1188c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m650d = AbstractC12892d.m650d();
        m1184e(jArr, jArr2, m650d);
        m1189c(m650d, jArr3);
    }

    /* renamed from: d */
    public static void m1187d(long[] jArr, long[] jArr2) {
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        long j = (m721a >>> 32) | (m721a2 & (-4294967296L));
        jArr2[0] = ((j << 57) ^ ((4294967295L & m721a) | (m721a2 << 32))) ^ (j << 5);
        jArr2[1] = (j >>> 59) ^ (j >>> 7);
    }

    /* renamed from: d */
    public static void m1186d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m650d = AbstractC12892d.m650d();
        m1184e(jArr, jArr2, m650d);
        m1190b(jArr3, m650d, jArr3);
    }

    /* renamed from: e */
    public static void m1185e(long[] jArr, long[] jArr2) {
        long[] m650d = AbstractC12892d.m650d();
        m1182g(jArr, m650d);
        m1189c(m650d, jArr2);
    }

    /* renamed from: e */
    protected static void m1184e(long[] jArr, long[] jArr2, long[] jArr3) {
        long j = jArr[0];
        long j2 = ((jArr[1] << 7) ^ (j >>> 57)) & 144115188075855871L;
        long j3 = j & 144115188075855871L;
        long j4 = jArr2[0];
        long j5 = ((jArr2[1] << 7) ^ (j4 >>> 57)) & 144115188075855871L;
        long j6 = 144115188075855871L & j4;
        long[] jArr4 = new long[6];
        m1197a(j3, j6, jArr4, 0);
        m1197a(j2, j5, jArr4, 2);
        m1197a(j3 ^ j2, j6 ^ j5, jArr4, 4);
        long j7 = jArr4[1] ^ jArr4[2];
        long j8 = jArr4[0];
        long j9 = jArr4[3];
        long j10 = (jArr4[4] ^ j8) ^ j7;
        long j11 = j7 ^ (jArr4[5] ^ j9);
        jArr3[0] = j8 ^ (j10 << 57);
        jArr3[1] = (j10 >>> 7) ^ (j11 << 50);
        jArr3[2] = (j11 >>> 14) ^ (j9 << 43);
        jArr3[3] = j9 >>> 21;
    }

    /* renamed from: f */
    public static void m1183f(long[] jArr, long[] jArr2) {
        long[] m650d = AbstractC12892d.m650d();
        m1182g(jArr, m650d);
        m1190b(jArr2, m650d, jArr2);
    }

    /* renamed from: g */
    protected static void m1182g(long[] jArr, long[] jArr2) {
        C12889a.m720a(jArr[0], jArr2, 0);
        C12889a.m720a(jArr[1], jArr2, 2);
    }
}
