package org.p415a.p436e.p443b;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12883c {

    /* renamed from: a */
    static final InterfaceC12882b f26142a = new C12888h(BigInteger.valueOf(2));

    /* renamed from: b */
    static final InterfaceC12882b f26143b = new C12888h(BigInteger.valueOf(3));

    /* renamed from: a */
    public static InterfaceC12882b m729a(BigInteger bigInteger) {
        int bitLength = bigInteger.bitLength();
        if (bigInteger.signum() <= 0 || bitLength < 2) {
            throw new IllegalArgumentException("'characteristic' must be >= 2");
        }
        if (bitLength < 3) {
            switch (bigInteger.intValue()) {
                case 2:
                    return f26142a;
                case 3:
                    return f26143b;
            }
        }
        return new C12888h(bigInteger);
    }

    /* renamed from: a */
    public static InterfaceC12887g m728a(int[] iArr) {
        if (iArr[0] == 0) {
            for (int i = 1; i < iArr.length; i++) {
                if (iArr[i] <= iArr[i - 1]) {
                    throw new IllegalArgumentException("Polynomial exponents must be montonically increasing");
                }
            }
            return new C12885e(f26142a, new C12884d(iArr));
        }
        throw new IllegalArgumentException("Irreducible polynomials in GF(2) must have constant term");
    }
}
