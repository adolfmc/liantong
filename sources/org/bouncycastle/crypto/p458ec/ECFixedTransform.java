package org.bouncycastle.crypto.p458ec;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.p464ec.ECAlgorithms;
import org.bouncycastle.math.p464ec.ECMultiplier;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.p464ec.FixedPointCombMultiplier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.ec.ECFixedTransform */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECFixedTransform implements ECPairFactorTransform {

    /* renamed from: k */
    private BigInteger f26550k;
    private ECPublicKeyParameters key;

    public ECFixedTransform(BigInteger bigInteger) {
        this.f26550k = bigInteger;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.p458ec.ECPairFactorTransform
    public BigInteger getTransformValue() {
        return this.f26550k;
    }

    @Override // org.bouncycastle.crypto.p458ec.ECPairTransform
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for fixed transform.");
        }
        this.key = (ECPublicKeyParameters) cipherParameters;
    }

    @Override // org.bouncycastle.crypto.p458ec.ECPairTransform
    public ECPair transform(ECPair eCPair) {
        ECPublicKeyParameters eCPublicKeyParameters = this.key;
        if (eCPublicKeyParameters != null) {
            ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
            BigInteger n = parameters.getN();
            ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
            BigInteger mod = this.f26550k.mod(n);
            ECPoint[] eCPointArr = {createBasePointMultiplier.multiply(parameters.getG(), mod).add(ECAlgorithms.cleanPoint(parameters.getCurve(), eCPair.getX())), this.key.getQ().multiply(mod).add(ECAlgorithms.cleanPoint(parameters.getCurve(), eCPair.getY()))};
            parameters.getCurve().normalizeAll(eCPointArr);
            return new ECPair(eCPointArr[0], eCPointArr[1]);
        }
        throw new IllegalStateException("ECFixedTransform not initialised");
    }
}
