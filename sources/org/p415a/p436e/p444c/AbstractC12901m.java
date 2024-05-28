package org.p415a.p436e.p444c;

import java.math.BigInteger;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12901m {
    /* renamed from: a */
    public static boolean m513a(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 9; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m512a(long[] jArr, long[] jArr2) {
        for (int i = 8; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static long[] m515a() {
        return new long[9];
    }

    /* renamed from: a */
    public static long[] m514a(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 576) {
            throw new IllegalArgumentException();
        }
        long[] m515a = m515a();
        int i = 0;
        while (bigInteger.signum() != 0) {
            m515a[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i++;
        }
        return m515a;
    }

    /* renamed from: b */
    public static boolean m510b(long[] jArr) {
        for (int i = 0; i < 9; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static long[] m511b() {
        return new long[18];
    }

    /* renamed from: c */
    public static BigInteger m509c(long[] jArr) {
        byte[] bArr = new byte[72];
        for (int i = 0; i < 9; i++) {
            long j = jArr[i];
            if (j != 0) {
                AbstractC12971e.m399a(j, bArr, (8 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
