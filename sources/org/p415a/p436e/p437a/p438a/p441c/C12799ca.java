package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ca */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12799ca {
    /* renamed from: a */
    protected static void m1097a(long j, long j2, long[] jArr, int i) {
        long[] jArr2 = {0, j2, jArr2[1] << 1, jArr2[2] ^ j2, jArr2[2] << 1, jArr2[4] ^ j2, jArr2[3] << 1, jArr2[6] ^ j2};
        int i2 = (int) j;
        long j3 = (jArr2[(i2 >>> 3) & 7] << 3) ^ jArr2[i2 & 7];
        long j4 = 0;
        int i3 = 54;
        do {
            int i4 = (int) (j >>> i3);
            long j5 = jArr2[i4 & 7] ^ (jArr2[(i4 >>> 3) & 7] << 3);
            j3 ^= j5 << i3;
            j4 ^= j5 >>> (-i3);
            i3 -= 6;
        } while (i3 > 0);
        jArr[i] = jArr[i] ^ (1152921504606846975L & j3);
        int i5 = i + 1;
        jArr[i5] = ((((((j & 585610922974906400L) & ((j2 << 4) >> 63)) >>> 5) ^ j4) << 4) ^ (j3 >>> 60)) ^ jArr[i5];
    }

    /* renamed from: a */
    protected static void m1095a(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        jArr[0] = j ^ (j2 << 60);
        jArr[1] = (j2 >>> 4) ^ (j3 << 56);
        jArr[2] = (j3 >>> 8) ^ (j4 << 52);
        jArr[3] = (j4 >>> 12) ^ (j5 << 48);
        jArr[4] = (j5 >>> 16) ^ (j6 << 44);
        jArr[5] = (j6 >>> 20) ^ (j7 << 40);
        jArr[6] = (j7 >>> 24) ^ (j8 << 36);
        jArr[7] = j8 >>> 28;
    }

    /* renamed from: a */
    public static void m1094a(long[] jArr, int i) {
        int i2 = i + 3;
        long j = jArr[i2];
        long j2 = j >>> 47;
        jArr[i] = jArr[i] ^ j2;
        int i3 = i + 2;
        jArr[i3] = (j2 << 30) ^ jArr[i3];
        jArr[i2] = j & 140737488355327L;
    }

    /* renamed from: a */
    public static void m1093a(long[] jArr, int i, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1080h(jArr, m539d);
        while (true) {
            m1088c(m539d, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1080h(jArr2, m539d);
        }
    }

    /* renamed from: a */
    public static void m1092a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    /* renamed from: a */
    public static void m1091a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    /* renamed from: a */
    public static long[] m1096a(BigInteger bigInteger) {
        long[] m553b = AbstractC12896h.m553b(bigInteger);
        m1094a(m553b, 0);
        return m553b;
    }

    /* renamed from: b */
    public static void m1090b(long[] jArr, long[] jArr2) {
        if (AbstractC12896h.m547b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m554b = AbstractC12896h.m554b();
        long[] m554b2 = AbstractC12896h.m554b();
        m1084e(jArr, m554b);
        m1087c(m554b, jArr, m554b);
        m1084e(m554b, m554b);
        m1087c(m554b, jArr, m554b);
        m1093a(m554b, 3, m554b2);
        m1087c(m554b2, m554b, m554b2);
        m1084e(m554b2, m554b2);
        m1087c(m554b2, jArr, m554b2);
        m1093a(m554b2, 7, m554b);
        m1087c(m554b, m554b2, m554b);
        m1093a(m554b, 14, m554b2);
        m1087c(m554b2, m554b, m554b2);
        m1084e(m554b2, m554b2);
        m1087c(m554b2, jArr, m554b2);
        m1093a(m554b2, 29, m554b);
        m1087c(m554b, m554b2, m554b);
        m1084e(m554b, m554b);
        m1087c(m554b, jArr, m554b);
        m1093a(m554b, 59, m554b2);
        m1087c(m554b2, m554b, m554b2);
        m1084e(m554b2, m554b2);
        m1087c(m554b2, jArr, m554b2);
        m1093a(m554b2, 119, m554b);
        m1087c(m554b, m554b2, m554b);
        m1084e(m554b, jArr2);
    }

    /* renamed from: b */
    public static void m1089b(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr2[7] ^ jArr[7];
    }

    /* renamed from: c */
    public static void m1088c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = j7 ^ (j8 >>> 17);
        long j10 = (j6 ^ (j8 << 47)) ^ (j9 >>> 17);
        long j11 = ((j5 ^ (j8 >>> 47)) ^ (j9 << 47)) ^ (j10 >>> 17);
        long j12 = j ^ (j11 << 17);
        long j13 = (j2 ^ (j10 << 17)) ^ (j11 >>> 47);
        long j14 = (((j4 ^ (j8 << 17)) ^ (j9 >>> 47)) ^ (j10 << 47)) ^ (j11 >>> 17);
        long j15 = j14 >>> 47;
        jArr2[0] = j12 ^ j15;
        jArr2[1] = j13;
        long j16 = j15 << 30;
        jArr2[2] = j16 ^ (((j3 ^ (j9 << 17)) ^ (j10 >>> 47)) ^ (j11 << 47));
        jArr2[3] = 140737488355327L & j14;
    }

    /* renamed from: c */
    public static void m1087c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m539d = AbstractC12896h.m539d();
        m1083e(jArr, jArr2, m539d);
        m1088c(m539d, jArr3);
    }

    /* renamed from: d */
    public static void m1086d(long[] jArr, long[] jArr2) {
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        long j = (m721a & 4294967295L) | (m721a2 << 32);
        long j2 = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long m721a4 = C12889a.m721a(jArr[3]);
        long j3 = (m721a3 & 4294967295L) | (m721a4 << 32);
        long j4 = (m721a4 & (-4294967296L)) | (m721a3 >>> 32);
        long j5 = j4 >>> 49;
        long j6 = (j2 >>> 49) | (j4 << 15);
        long j7 = j4 ^ (j2 << 15);
        long[] m539d = AbstractC12896h.m539d();
        int[] iArr = {39, 120};
        int i = 0;
        while (i < iArr.length) {
            int i2 = iArr[i] >>> 6;
            int[] iArr2 = iArr;
            int i3 = iArr[i] & 63;
            m539d[i2] = m539d[i2] ^ (j2 << i3);
            int i4 = i2 + 1;
            long j8 = j3;
            int i5 = -i3;
            m539d[i4] = m539d[i4] ^ ((j7 << i3) | (j2 >>> i5));
            int i6 = i2 + 2;
            m539d[i6] = m539d[i6] ^ ((j6 << i3) | (j7 >>> i5));
            int i7 = i2 + 3;
            m539d[i7] = m539d[i7] ^ ((j5 << i3) | (j6 >>> i5));
            int i8 = i2 + 4;
            m539d[i8] = m539d[i8] ^ (j5 >>> i5);
            i++;
            iArr = iArr2;
            j3 = j8;
        }
        m1088c(m539d, jArr2);
        jArr2[0] = jArr2[0] ^ j;
        jArr2[1] = jArr2[1] ^ j3;
    }

    /* renamed from: d */
    public static void m1085d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m539d = AbstractC12896h.m539d();
        m1083e(jArr, jArr2, m539d);
        m1089b(jArr3, m539d, jArr3);
    }

    /* renamed from: e */
    public static void m1084e(long[] jArr, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1080h(jArr, m539d);
        m1088c(m539d, jArr2);
    }

    /* renamed from: e */
    protected static void m1083e(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        m1081g(jArr, jArr4);
        m1081g(jArr2, jArr5);
        m1097a(jArr4[0], jArr5[0], jArr3, 0);
        m1097a(jArr4[1], jArr5[1], jArr3, 1);
        m1097a(jArr4[2], jArr5[2], jArr3, 2);
        m1097a(jArr4[3], jArr5[3], jArr3, 3);
        for (int i = 5; i > 0; i--) {
            jArr3[i] = jArr3[i] ^ jArr3[i - 1];
        }
        m1097a(jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        m1097a(jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i2 = 7; i2 > 1; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 2];
        }
        long j = jArr4[0] ^ jArr4[2];
        long j2 = jArr4[1] ^ jArr4[3];
        long j3 = jArr5[0] ^ jArr5[2];
        long j4 = jArr5[1] ^ jArr5[3];
        m1097a(j ^ j2, j3 ^ j4, jArr3, 3);
        long[] jArr6 = new long[3];
        m1097a(j, j3, jArr6, 0);
        m1097a(j2, j4, jArr6, 1);
        long j5 = jArr6[0];
        long j6 = jArr6[1];
        long j7 = jArr6[2];
        jArr3[2] = jArr3[2] ^ j5;
        jArr3[3] = (j5 ^ j6) ^ jArr3[3];
        jArr3[4] = jArr3[4] ^ (j7 ^ j6);
        jArr3[5] = jArr3[5] ^ j7;
        m1095a(jArr3);
    }

    /* renamed from: f */
    public static void m1082f(long[] jArr, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1080h(jArr, m539d);
        m1089b(jArr2, m539d, jArr2);
    }

    /* renamed from: g */
    protected static void m1081g(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = j & 1152921504606846975L;
        jArr2[1] = ((j >>> 60) ^ (j2 << 4)) & 1152921504606846975L;
        jArr2[2] = ((j2 >>> 56) ^ (j3 << 8)) & 1152921504606846975L;
        jArr2[3] = (j3 >>> 52) ^ (j4 << 12);
    }

    /* renamed from: h */
    protected static void m1080h(long[] jArr, long[] jArr2) {
        C12889a.m720a(jArr[0], jArr2, 0);
        C12889a.m720a(jArr[1], jArr2, 2);
        C12889a.m720a(jArr[2], jArr2, 4);
        long j = jArr[3];
        jArr2[6] = C12889a.m718c((int) j);
        jArr2[7] = C12889a.m719b((int) (j >>> 32)) & 4294967295L;
    }
}
