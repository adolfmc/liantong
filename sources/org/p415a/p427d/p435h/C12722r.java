package org.p415a.p427d.p435h;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12722r extends C12705a {

    /* renamed from: b */
    private static final BigInteger f25920b = BigInteger.valueOf(1);

    /* renamed from: c */
    private BigInteger f25921c;

    /* renamed from: d */
    private BigInteger f25922d;

    public C12722r(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        super(z);
        if (!z && (bigInteger2.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA publicExponent is even");
        }
        this.f25921c = m1323a(bigInteger);
        this.f25922d = bigInteger2;
    }

    /* renamed from: a */
    private BigInteger m1323a(BigInteger bigInteger) {
        if ((bigInteger.intValue() & 1) != 0) {
            if (bigInteger.gcd(new BigInteger("1451887755777639901511587432083070202422614380984889313550570919659315177065956574359078912654149167643992684236991305777574330831666511589145701059710742276692757882915756220901998212975756543223550490431013061082131040808010565293748926901442915057819663730454818359472391642885328171302299245556663073719855")).equals(f25920b)) {
                return bigInteger;
            }
            throw new IllegalArgumentException("RSA modulus has a small prime factor");
        }
        throw new IllegalArgumentException("RSA modulus is even");
    }

    /* renamed from: b */
    public BigInteger m1322b() {
        return this.f25921c;
    }

    /* renamed from: c */
    public BigInteger m1321c() {
        return this.f25922d;
    }
}
