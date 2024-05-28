package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12896h;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.bu */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12792bu {
    /* renamed from: a */
    protected static void m1115a(long j, long j2, long[] jArr, int i) {
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
        jArr[i] = jArr[i] ^ (576460752303423487L & j3);
        int i5 = i + 1;
        jArr[i5] = jArr[i5] ^ ((j3 >>> 59) ^ (j4 << 5));
    }

    /* renamed from: a */
    protected static void m1113a(long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        jArr[0] = j ^ (j2 << 59);
        jArr[1] = (j2 >>> 5) ^ (j3 << 54);
        jArr[2] = (j3 >>> 10) ^ (j4 << 49);
        jArr[3] = (j4 >>> 15) ^ (j5 << 44);
        jArr[4] = (j5 >>> 20) ^ (j6 << 39);
        jArr[5] = (j6 >>> 25) ^ (j7 << 34);
        jArr[6] = (j7 >>> 30) ^ (j8 << 29);
        jArr[7] = j8 >>> 35;
    }

    /* renamed from: a */
    public static void m1112a(long[] jArr, int i) {
        int i2 = i + 3;
        long j = jArr[i2];
        long j2 = j >>> 41;
        jArr[i] = jArr[i] ^ j2;
        int i3 = i + 1;
        jArr[i3] = (j2 << 10) ^ jArr[i3];
        jArr[i2] = j & 2199023255551L;
    }

    /* renamed from: a */
    public static void m1111a(long[] jArr, int i, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1098h(jArr, m539d);
        while (true) {
            m1106c(m539d, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1098h(jArr2, m539d);
        }
    }

    /* renamed from: a */
    public static void m1110a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    /* renamed from: a */
    public static void m1109a(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr2[3] ^ jArr[3];
    }

    /* renamed from: a */
    public static long[] m1114a(BigInteger bigInteger) {
        long[] m553b = AbstractC12896h.m553b(bigInteger);
        m1112a(m553b, 0);
        return m553b;
    }

    /* renamed from: b */
    public static void m1108b(long[] jArr, long[] jArr2) {
        if (AbstractC12896h.m547b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m554b = AbstractC12896h.m554b();
        long[] m554b2 = AbstractC12896h.m554b();
        m1104d(jArr, m554b);
        m1105c(m554b, jArr, m554b);
        m1104d(m554b, m554b);
        m1105c(m554b, jArr, m554b);
        m1111a(m554b, 3, m554b2);
        m1105c(m554b2, m554b, m554b2);
        m1104d(m554b2, m554b2);
        m1105c(m554b2, jArr, m554b2);
        m1111a(m554b2, 7, m554b);
        m1105c(m554b, m554b2, m554b);
        m1111a(m554b, 14, m554b2);
        m1105c(m554b2, m554b, m554b2);
        m1104d(m554b2, m554b2);
        m1105c(m554b2, jArr, m554b2);
        m1111a(m554b2, 29, m554b);
        m1105c(m554b, m554b2, m554b);
        m1111a(m554b, 58, m554b2);
        m1105c(m554b2, m554b, m554b2);
        m1111a(m554b2, 116, m554b);
        m1105c(m554b, m554b2, m554b);
        m1104d(m554b, jArr2);
    }

    /* renamed from: b */
    public static void m1107b(long[] jArr, long[] jArr2, long[] jArr3) {
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
    public static void m1106c(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = j6 ^ (j8 >>> 31);
        long j10 = (j5 ^ ((j8 >>> 41) ^ (j8 << 33))) ^ (j7 >>> 31);
        long j11 = ((j4 ^ (j8 << 23)) ^ ((j7 >>> 41) ^ (j7 << 33))) ^ (j9 >>> 31);
        long j12 = j ^ (j10 << 23);
        long j13 = ((j3 ^ (j7 << 23)) ^ ((j9 >>> 41) ^ (j9 << 33))) ^ (j10 >>> 31);
        long j14 = j11 >>> 41;
        jArr2[0] = j12 ^ j14;
        long j15 = j14 << 10;
        jArr2[1] = j15 ^ ((j2 ^ (j9 << 23)) ^ ((j10 >>> 41) ^ (j10 << 33)));
        jArr2[2] = j13;
        jArr2[3] = 2199023255551L & j11;
    }

    /* renamed from: c */
    public static void m1105c(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m539d = AbstractC12896h.m539d();
        m1101e(jArr, jArr2, m539d);
        m1106c(m539d, jArr3);
    }

    /* renamed from: d */
    public static void m1104d(long[] jArr, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1098h(jArr, m539d);
        m1106c(m539d, jArr2);
    }

    /* renamed from: d */
    public static void m1103d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m539d = AbstractC12896h.m539d();
        m1101e(jArr, jArr2, m539d);
        m1107b(jArr3, m539d, jArr3);
    }

    /* renamed from: e */
    public static void m1102e(long[] jArr, long[] jArr2) {
        long[] m539d = AbstractC12896h.m539d();
        m1098h(jArr, m539d);
        m1107b(jArr2, m539d, jArr2);
    }

    /* renamed from: e */
    protected static void m1101e(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        m1099g(jArr, jArr4);
        m1099g(jArr2, jArr5);
        m1115a(jArr4[0], jArr5[0], jArr3, 0);
        m1115a(jArr4[1], jArr5[1], jArr3, 1);
        m1115a(jArr4[2], jArr5[2], jArr3, 2);
        m1115a(jArr4[3], jArr5[3], jArr3, 3);
        for (int i = 5; i > 0; i--) {
            jArr3[i] = jArr3[i] ^ jArr3[i - 1];
        }
        m1115a(jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        m1115a(jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i2 = 7; i2 > 1; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 2];
        }
        long j = jArr4[0] ^ jArr4[2];
        long j2 = jArr4[1] ^ jArr4[3];
        long j3 = jArr5[0] ^ jArr5[2];
        long j4 = jArr5[1] ^ jArr5[3];
        m1115a(j ^ j2, j3 ^ j4, jArr3, 3);
        long[] jArr6 = new long[3];
        m1115a(j, j3, jArr6, 0);
        m1115a(j2, j4, jArr6, 1);
        long j5 = jArr6[0];
        long j6 = jArr6[1];
        long j7 = jArr6[2];
        jArr3[2] = jArr3[2] ^ j5;
        jArr3[3] = (j5 ^ j6) ^ jArr3[3];
        jArr3[4] = jArr3[4] ^ (j7 ^ j6);
        jArr3[5] = jArr3[5] ^ j7;
        m1113a(jArr3);
    }

    /* renamed from: f */
    public static void m1100f(long[] jArr, long[] jArr2) {
        long m721a = C12889a.m721a(jArr[0]);
        long m721a2 = C12889a.m721a(jArr[1]);
        long j = (m721a & 4294967295L) | (m721a2 << 32);
        long j2 = (m721a >>> 32) | (m721a2 & (-4294967296L));
        long m721a3 = C12889a.m721a(jArr[2]);
        long m721a4 = C12889a.m721a(jArr[3]);
        long j3 = (4294967295L & m721a3) | (m721a4 << 32);
        long j4 = (m721a3 >>> 32) | (m721a4 & (-4294967296L));
        long j5 = j4 >>> 27;
        long j6 = j4 ^ ((j2 >>> 27) | (j4 << 37));
        long j7 = j2 ^ (j2 << 37);
        long[] m539d = AbstractC12896h.m539d();
        int[] iArr = {32, 117, 191};
        int i = 0;
        while (i < iArr.length) {
            int i2 = iArr[i] >>> 6;
            int i3 = iArr[i] & 63;
            m539d[i2] = m539d[i2] ^ (j7 << i3);
            int i4 = i2 + 1;
            int[] iArr2 = iArr;
            int i5 = -i3;
            m539d[i4] = m539d[i4] ^ ((j6 << i3) | (j7 >>> i5));
            int i6 = i2 + 2;
            m539d[i6] = m539d[i6] ^ ((j5 << i3) | (j6 >>> i5));
            int i7 = i2 + 3;
            m539d[i7] = m539d[i7] ^ (j5 >>> i5);
            i++;
            iArr = iArr2;
        }
        m1106c(m539d, jArr2);
        jArr2[0] = jArr2[0] ^ j;
        jArr2[1] = jArr2[1] ^ j3;
    }

    /* renamed from: g */
    protected static void m1099g(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = j & 576460752303423487L;
        jArr2[1] = ((j >>> 59) ^ (j2 << 5)) & 576460752303423487L;
        jArr2[2] = ((j2 >>> 54) ^ (j3 << 10)) & 576460752303423487L;
        jArr2[3] = (j3 >>> 49) ^ (j4 << 15);
    }

    /* renamed from: h */
    protected static void m1098h(long[] jArr, long[] jArr2) {
        C12889a.m720a(jArr[0], jArr2, 0);
        C12889a.m720a(jArr[1], jArr2, 2);
        C12889a.m720a(jArr[2], jArr2, 4);
        long j = jArr[3];
        jArr2[6] = C12889a.m718c((int) j);
        jArr2[7] = C12889a.m719b((int) (j >>> 32)) & 4294967295L;
    }
}
