package org.bouncycastle.math.p464ec;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.AbstractECMultiplier */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class AbstractECMultiplier implements ECMultiplier {
    protected ECPoint checkResult(ECPoint eCPoint) {
        return ECAlgorithms.implCheckResult(eCPoint);
    }

    @Override // org.bouncycastle.math.p464ec.ECMultiplier
    public ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger) {
        int signum = bigInteger.signum();
        if (signum == 0 || eCPoint.isInfinity()) {
            return eCPoint.getCurve().getInfinity();
        }
        ECPoint multiplyPositive = multiplyPositive(eCPoint, bigInteger.abs());
        if (signum <= 0) {
            multiplyPositive = multiplyPositive.negate();
        }
        return checkResult(multiplyPositive);
    }

    protected abstract ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger);
}
