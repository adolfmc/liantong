package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12901m;
import org.p415a.p436e.p444c.C12889a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.cq */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12815cq {

    /* renamed from: a */
    private static final long[] f26013a = {3161836309350906777L, -7642453882179322845L, -3821226941089661423L, 7312758566309945096L, -556661012383879292L, 8945041530681231562L, -4750851271514160027L, 6847946401097695794L, 541669439031730457L};

    /* renamed from: a */
    public static void m1041a(long[] jArr, int i) {
        int i2 = i + 8;
        long j = jArr[i2];
        long j2 = j >>> 59;
        jArr[i] = ((j2 << 10) ^ (((j2 << 2) ^ j2) ^ (j2 << 5))) ^ jArr[i];
        jArr[i2] = j & 576460752303423487L;
    }

    /* renamed from: a */
    public static void m1040a(long[] jArr, int i, long[] jArr2) {
        long[] m511b = AbstractC12901m.m511b();
        m1025g(jArr, m511b);
        while (true) {
            m1033c(m511b, jArr2);
            i--;
            if (i <= 0) {
                return;
            }
            m1025g(jArr2, m511b);
        }
    }

    /* renamed from: a */
    private static void m1039a(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3) {
        for (int i4 = 0; i4 < 9; i4++) {
            jArr3[i3 + i4] = jArr[i + i4] ^ jArr2[i2 + i4];
        }
    }

    /* renamed from: a */
    public static void m1038a(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        for (int i = 1; i < 9; i++) {
            jArr2[i] = jArr[i];
        }
    }

    /* renamed from: a */
    public static void m1037a(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 9; i++) {
            jArr3[i] = jArr[i] ^ jArr2[i];
        }
    }

    /* renamed from: a */
    public static long[] m1043a(BigInteger bigInteger) {
        long[] m514a = AbstractC12901m.m514a(bigInteger);
        m1041a(m514a, 0);
        return m514a;
    }

    /* renamed from: a */
    public static long[] m1042a(long[] jArr) {
        long[] jArr2 = new long[288];
        int i = 0;
        System.arraycopy(jArr, 0, jArr2, 9, 9);
        int i2 = 7;
        while (i2 > 0) {
            int i3 = i + 18;
            AbstractC12891c.m693a(9, jArr2, i3 >>> 1, 0L, jArr2, i3);
            m1041a(jArr2, i3);
            m1039a(jArr2, 9, jArr2, i3, jArr2, i3 + 9);
            i2--;
            i = i3;
        }
        AbstractC12891c.m694a(144, jArr2, 0, 4, 0L, jArr2, 144);
        return jArr2;
    }

    /* renamed from: b */
    private static void m1036b(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3) {
        for (int i4 = 0; i4 < 9; i4++) {
            int i5 = i3 + i4;
            jArr3[i5] = jArr3[i5] ^ (jArr[i + i4] ^ jArr2[i2 + i4]);
        }
    }

    /* renamed from: b */
    public static void m1035b(long[] jArr, long[] jArr2) {
        if (AbstractC12901m.m510b(jArr)) {
            throw new IllegalStateException();
        }
        long[] m515a = AbstractC12901m.m515a();
        long[] m515a2 = AbstractC12901m.m515a();
        long[] m515a3 = AbstractC12901m.m515a();
        m1029e(jArr, m515a3);
        m1029e(m515a3, m515a);
        m1029e(m515a, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a, 2, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1030d(m515a, m515a3, m515a);
        m1040a(m515a, 5, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a2, 5, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a, 15, m515a2);
        m1030d(m515a, m515a2, m515a3);
        m1040a(m515a3, 30, m515a);
        m1040a(m515a, 30, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a, 60, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a2, 60, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a, 180, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1040a(m515a2, 180, m515a2);
        m1030d(m515a, m515a2, m515a);
        m1030d(m515a, m515a3, jArr2);
    }

    /* renamed from: b */
    public static void m1034b(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 9; i++) {
            jArr3[i] = jArr3[i] ^ (jArr[i] ^ jArr2[i]);
        }
    }

    /* renamed from: c */
    public static void m1033c(long[] jArr, long[] jArr2) {
        long j = jArr[9];
        long j2 = jArr[17];
        long j3 = (((j ^ (j2 >>> 59)) ^ (j2 >>> 57)) ^ (j2 >>> 54)) ^ (j2 >>> 49);
        long j4 = (j2 << 15) ^ (((jArr[8] ^ (j2 << 5)) ^ (j2 << 7)) ^ (j2 << 10));
        for (int i = 16; i >= 10; i--) {
            long j5 = jArr[i];
            jArr2[i - 8] = (((j4 ^ (j5 >>> 59)) ^ (j5 >>> 57)) ^ (j5 >>> 54)) ^ (j5 >>> 49);
            j4 = (((jArr[i - 9] ^ (j5 << 5)) ^ (j5 << 7)) ^ (j5 << 10)) ^ (j5 << 15);
        }
        jArr2[1] = (((j4 ^ (j3 >>> 59)) ^ (j3 >>> 57)) ^ (j3 >>> 54)) ^ (j3 >>> 49);
        long j6 = (j3 << 15) ^ (((jArr[0] ^ (j3 << 5)) ^ (j3 << 7)) ^ (j3 << 10));
        long j7 = jArr2[8];
        long j8 = j7 >>> 59;
        jArr2[0] = (((j6 ^ j8) ^ (j8 << 2)) ^ (j8 << 5)) ^ (j8 << 10);
        jArr2[8] = 576460752303423487L & j7;
    }

    /* renamed from: c */
    public static void m1032c(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 18; i++) {
            jArr3[i] = jArr[i] ^ jArr2[i];
        }
    }

    /* renamed from: d */
    public static void m1031d(long[] jArr, long[] jArr2) {
        long[] m515a = AbstractC12901m.m515a();
        long[] m515a2 = AbstractC12901m.m515a();
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i + 1;
            long m721a = C12889a.m721a(jArr[i]);
            i = i3 + 1;
            long m721a2 = C12889a.m721a(jArr[i3]);
            m515a[i2] = (4294967295L & m721a) | (m721a2 << 32);
            m515a2[i2] = (m721a >>> 32) | ((-4294967296L) & m721a2);
        }
        long m721a3 = C12889a.m721a(jArr[i]);
        m515a[4] = 4294967295L & m721a3;
        m515a2[4] = m721a3 >>> 32;
        m1030d(m515a2, f26013a, jArr2);
        m1037a(jArr2, m515a, jArr2);
    }

    /* renamed from: d */
    public static void m1030d(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m511b = AbstractC12901m.m511b();
        m1023h(jArr, jArr2, m511b);
        m1033c(m511b, jArr3);
    }

    /* renamed from: e */
    public static void m1029e(long[] jArr, long[] jArr2) {
        long[] m511b = AbstractC12901m.m511b();
        m1025g(jArr, m511b);
        m1033c(m511b, jArr2);
    }

    /* renamed from: e */
    public static void m1028e(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m511b = AbstractC12901m.m511b();
        m1023h(jArr, jArr2, m511b);
        m1032c(jArr3, m511b, jArr3);
    }

    /* renamed from: f */
    public static void m1027f(long[] jArr, long[] jArr2) {
        long[] m511b = AbstractC12901m.m511b();
        m1025g(jArr, m511b);
        m1032c(jArr2, m511b, jArr2);
    }

    /* renamed from: f */
    public static void m1026f(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m511b = AbstractC12901m.m511b();
        m1022i(jArr, jArr2, m511b);
        m1033c(m511b, jArr3);
    }

    /* renamed from: g */
    protected static void m1025g(long[] jArr, long[] jArr2) {
        for (int i = 0; i < 9; i++) {
            C12889a.m720a(jArr[i], jArr2, i << 1);
        }
    }

    /* renamed from: g */
    public static void m1024g(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] m511b = AbstractC12901m.m511b();
        m1022i(jArr, jArr2, m511b);
        m1032c(jArr3, m511b, jArr3);
    }

    /* renamed from: h */
    protected static void m1023h(long[] jArr, long[] jArr2, long[] jArr3) {
        m1022i(jArr, m1042a(jArr2), jArr3);
    }

    /* renamed from: i */
    protected static void m1022i(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 56; i >= 0; i -= 8) {
            for (int i2 = 1; i2 < 9; i2 += 2) {
                int i3 = (int) (jArr[i2] >>> i);
                m1036b(jArr2, (i3 & 15) * 9, jArr2, (((i3 >>> 4) & 15) + 16) * 9, jArr3, i2 - 1);
            }
            AbstractC12891c.m695a(16, jArr3, 0, 8, 0L);
        }
        for (int i4 = 56; i4 >= 0; i4 -= 8) {
            for (int i5 = 0; i5 < 9; i5 += 2) {
                int i6 = (int) (jArr[i5] >>> i4);
                m1036b(jArr2, (i6 & 15) * 9, jArr2, (((i6 >>> 4) & 15) + 16) * 9, jArr3, i5);
            }
            if (i4 > 0) {
                AbstractC12891c.m695a(18, jArr3, 0, 8, 0L);
            }
        }
    }
}
