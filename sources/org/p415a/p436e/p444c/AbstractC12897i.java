package org.p415a.p436e.p444c;

import java.math.BigInteger;
import org.p415a.p448g.AbstractC12971e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12897i {
    /* renamed from: a */
    public static boolean m531a(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 5; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m530a(long[] jArr, long[] jArr2) {
        for (int i = 4; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static long[] m533a() {
        return new long[5];
    }

    /* renamed from: a */
    public static long[] m532a(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 320) {
            throw new IllegalArgumentException();
        }
        long[] m533a = m533a();
        int i = 0;
        while (bigInteger.signum() != 0) {
            m533a[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i++;
        }
        return m533a;
    }

    /* renamed from: b */
    public static boolean m528b(long[] jArr) {
        for (int i = 0; i < 5; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static long[] m529b() {
        return new long[10];
    }

    /* renamed from: c */
    public static BigInteger m527c(long[] jArr) {
        byte[] bArr = new byte[40];
        for (int i = 0; i < 5; i++) {
            long j = jArr[i];
            if (j != 0) {
                AbstractC12971e.m399a(j, bArr, (4 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
