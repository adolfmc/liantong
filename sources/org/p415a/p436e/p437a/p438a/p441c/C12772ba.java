package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12894f;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ba */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12772ba {

    /* renamed from: a */
    private static final long[] f25987a = {2791191049453778211L, 2791191049453778402L, 6};

    /* renamed from: a */
    protected static void m1167a(long j, long j2, long[] jArr, int i) {
        long[] jArr2 = {0, j2, jArr2[1] << 1, jArr2[2] ^ j2, jArr2[2] << 1, jArr2[4] ^ j2, jArr2[3] << 1, jArr2[6] ^ j2};
        int i2 = (int) j;
        long j3 = (jArr2[(i2 >>> 6) & 7] << 6) ^ (jArr2[i2 & 7] ^ (jArr2[(i2 >>> 3) & 7] << 3));
        long j4 = 0;
        int i3 = 33;
        do {
            int i4 = (int) (j >>> i3);
            long j5 = ((jArr2[i4 & 7] ^ (jArr2[(i4 >>> 3) & 7] << 3)) ^ (jArr2[(i4 >>> 6) & 7] << 6)) ^ (jArr2[(i4 >>> 9) & 7] << 9);
            j3 ^= j5 << i3;
            j4 ^= j5 >>> (-i3);
            i3 -= 12;
        } while (i3 > 0);
        jArr[i] = 17592186044415L & j3;
        jArr[i + 1] = (j3 >>> 44) ^ (j4 << 20);
    }

    /* renamed from: a */
    protected static void m1165a(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        jArr[0] = j ^ (j2 << 44);
        jArr[1] = (j2 >>> 20) ^ (j3 << 24);
        jArr[2] = ((j3 >>> 40) ^ (j4 << 4)) ^ (j5 << 48);
        jArr[3] = ((j4 >>> 60) ^ (j6 << 28)) ^ (j5 >>> 16);
        jArr[4] = j6 >>> 36;
        jArr[5] = 0;
    }

    /* renamed from: a */
    public static void m1164a(long[] jArr, int i) {
        int i2 = i + 2;
        long j = jArr[i2];
        long j2 = j >>> 3;
        jArr[i] = jArr[i] ^ ((((j2 << 2) ^ j2) ^ (j2 << 3)) ^ (j2 << 8));
        int i3 = i + 1;
        jArr[i3] = (j2 >>> 56) ^ jArr[i3];
        jArr[i2] = j & 7;
    }

    /* renamed from: a */
    public static void m1163a(long[] jArr, int i, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(5);
        m1151g(jArr, m691b);
        while (true) {
            m1158c(m691b, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1151g(jArr2, m691b);
        }
    }

    /* renamed from: a */
    public static void m1162a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    /* renamed from: a */
    public static void m1161a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr2[2] ^ jArr[2];
    }

    /* renamed from: a */
    public static long[] m1166a(BigInteger bigInteger) {
        long[] m609b = AbstractC12894f.m609b(bigInteger);
        m1164a(m609b, 0);
        return m609b;
    }

    /* renamed from: b */
    public static void m1160b(long[] jArr, long[] jArr2) {
        if (AbstractC12894f.m603b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m610b = AbstractC12894f.m610b();
        long[] m610b2 = AbstractC12894f.m610b();
        m1154e(jArr, m610b);
        m1157c(m610b, jArr, m610b);
        m1163a(m610b, 2, m610b2);
        m1157c(m610b2, m610b, m610b2);
        m1163a(m610b2, 4, m610b);
        m1157c(m610b, m610b2, m610b);
        m1163a(m610b, 8, m610b2);
        m1157c(m610b2, m610b, m610b2);
        m1163a(m610b2, 16, m610b);
        m1157c(m610b, m610b2, m610b);
        m1163a(m610b, 32, m610b2);
        m1157c(m610b2, m610b, m610b2);
        m1154e(m610b2, m610b2);
        m1157c(m610b2, jArr, m610b2);
        m1163a(m610b2, 65, m610b);
        m1157c(m610b, m610b2, m610b);
        m1154e(m610b, jArr2);
    }

    /* renamed from: b */
    public static void m1159b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr2[4] ^ jArr[4];
    }

    /* renamed from: c */
    public static void m1158c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = j4 ^ (j5 >>> 59);
        long j7 = j ^ ((j6 << 61) ^ (j6 << 63));
        long j8 = (j2 ^ ((j5 << 61) ^ (j5 << 63))) ^ ((((j6 >>> 3) ^ (j6 >>> 1)) ^ j6) ^ (j6 << 5));
        long j9 = (j3 ^ ((((j5 >>> 3) ^ (j5 >>> 1)) ^ j5) ^ (j5 << 5))) ^ (j6 >>> 59);
        long j10 = j9 >>> 3;
        jArr2[0] = (((j7 ^ j10) ^ (j10 << 2)) ^ (j10 << 3)) ^ (j10 << 8);
        jArr2[1] = (j10 >>> 56) ^ j8;
        jArr2[2] = 7 & j9;
    }

    /* renamed from: c */
    public static void m1157c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m595d = AbstractC12894f.m595d();
        m1153e(jArr, jArr2, m595d);
        m1158c(m595d, jArr3);
    }

    /* renamed from: d */
    public static void m1156d(long[] jArr, long[] jArr2) {
        long[] m610b = AbstractC12894f.m610b();
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        m610b[0] = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long j = m721a3 & 4294967295L;
        m610b[1] = m721a3 >>> 32;
        m1157c(m610b, f25987a, jArr2);
        jArr2[0] = jArr2[0] ^ ((m721a & 4294967295L) | (m721a2 << 32));
        jArr2[1] = jArr2[1] ^ j;
    }

    /* renamed from: d */
    public static void m1155d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m595d = AbstractC12894f.m595d();
        m1153e(jArr, jArr2, m595d);
        m1159b(jArr3, m595d, jArr3);
    }

    /* renamed from: e */
    public static void m1154e(long[] jArr, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(5);
        m1151g(jArr, m691b);
        m1158c(m691b, jArr2);
    }

    /* renamed from: e */
    protected static void m1153e(long[] jArr, long[] jArr2, long[] jArr3) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = ((jArr[2] << 40) ^ (j2 >>> 24)) & 17592186044415L;
        long j4 = ((j2 << 20) ^ (j >>> 44)) & 17592186044415L;
        long j5 = j & 17592186044415L;
        long j6 = jArr2[0];
        long j7 = jArr2[1];
        long j8 = ((j7 >>> 24) ^ (jArr2[2] << 40)) & 17592186044415L;
        long j9 = ((j6 >>> 44) ^ (j7 << 20)) & 17592186044415L;
        long j10 = j6 & 17592186044415L;
        long[] jArr4 = new long[10];
        m1167a(j5, j10, jArr4, 0);
        m1167a(j3, j8, jArr4, 2);
        long j11 = (j5 ^ j4) ^ j3;
        long j12 = (j10 ^ j9) ^ j8;
        m1167a(j11, j12, jArr4, 4);
        long j13 = (j4 << 1) ^ (j3 << 2);
        long j14 = (j9 << 1) ^ (j8 << 2);
        m1167a(j5 ^ j13, j10 ^ j14, jArr4, 6);
        m1167a(j11 ^ j13, j12 ^ j14, jArr4, 8);
        long j15 = jArr4[6] ^ jArr4[8];
        long j16 = jArr4[7] ^ jArr4[9];
        long j17 = (j15 << 1) ^ jArr4[6];
        long j18 = (j15 ^ (j16 << 1)) ^ jArr4[7];
        long j19 = jArr4[0];
        long j20 = (jArr4[1] ^ jArr4[0]) ^ jArr4[4];
        long j21 = jArr4[1] ^ jArr4[5];
        long j22 = ((j17 ^ j19) ^ (jArr4[2] << 4)) ^ (jArr4[2] << 1);
        long j23 = (((j20 ^ j18) ^ (jArr4[3] << 4)) ^ (jArr4[3] << 1)) ^ (j22 >>> 44);
        long j24 = (j21 ^ j16) ^ (j23 >>> 44);
        long j25 = j23 & 17592186044415L;
        long j26 = ((j22 & 17592186044415L) >>> 1) ^ ((j25 & 1) << 43);
        long j27 = j26 ^ (j26 << 1);
        long j28 = j27 ^ (j27 << 2);
        long j29 = j28 ^ (j28 << 4);
        long j30 = j29 ^ (j29 << 8);
        long j31 = j30 ^ (j30 << 16);
        long j32 = (j31 ^ (j31 << 32)) & 17592186044415L;
        long j33 = ((j25 >>> 1) ^ ((j24 & 1) << 43)) ^ (j32 >>> 43);
        long j34 = j33 ^ (j33 << 1);
        long j35 = j34 ^ (j34 << 2);
        long j36 = j35 ^ (j35 << 4);
        long j37 = j36 ^ (j36 << 8);
        long j38 = j37 ^ (j37 << 16);
        long j39 = (j38 ^ (j38 << 32)) & 17592186044415L;
        long j40 = (j24 >>> 1) ^ (j39 >>> 43);
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
        m1165a(jArr3);
    }

    /* renamed from: f */
    public static void m1152f(long[] jArr, long[] jArr2) {
        long[] m691b = AbstractC12891c.m691b(5);
        m1151g(jArr, m691b);
        m1159b(jArr2, m691b, jArr2);
    }

    /* renamed from: g */
    protected static void m1151g(long[] jArr, long[] jArr2) {
        C12889a.m720a(jArr[0], jArr2, 0);
        C12889a.m720a(jArr[1], jArr2, 2);
        jArr2[4] = C12889a.m722a((int) jArr[2]) & 4294967295L;
    }
}
