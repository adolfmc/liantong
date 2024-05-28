package org.p415a.p436e.p444c;

import java.math.BigInteger;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12899k {
    /* renamed from: a */
    public static boolean m522a(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 7; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m521a(long[] jArr, long[] jArr2) {
        for (int i = 6; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static long[] m524a() {
        return new long[7];
    }

    /* renamed from: a */
    public static long[] m523a(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 448) {
            throw new IllegalArgumentException();
        }
        long[] m524a = m524a();
        int i = 0;
        while (bigInteger.signum() != 0) {
            m524a[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i++;
        }
        return m524a;
    }

    /* renamed from: b */
    public static boolean m519b(long[] jArr) {
        for (int i = 0; i < 7; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static long[] m520b() {
        return new long[14];
    }

    /* renamed from: c */
    public static BigInteger m518c(long[] jArr) {
        byte[] bArr = new byte[56];
        for (int i = 0; i < 7; i++) {
            long j = jArr[i];
            if (j != 0) {
                AbstractC12971e.m399a(j, bArr, (6 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
