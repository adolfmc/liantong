package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSAPublicKeyParameters extends DSAKeyParameters {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: y */
    private BigInteger f26800y;

    public DSAPublicKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(false, dSAParameters);
        this.f26800y = validate(bigInteger, dSAParameters);
    }

    private BigInteger validate(BigInteger bigInteger, DSAParameters dSAParameters) {
        if (dSAParameters != null) {
            if (TWO.compareTo(bigInteger) > 0 || dSAParameters.getP().subtract(TWO).compareTo(bigInteger) < 0 || !ONE.equals(bigInteger.modPow(dSAParameters.getQ(), dSAParameters.getP()))) {
                throw new IllegalArgumentException("y value does not appear to be in correct group");
            }
            return bigInteger;
        }
        return bigInteger;
    }

    public BigInteger getY() {
        return this.f26800y;
    }
}
