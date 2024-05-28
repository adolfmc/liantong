package org.bouncycastle.crypto.p458ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.p464ec.ECAlgorithms;
import org.bouncycastle.math.p464ec.ECMultiplier;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.p464ec.FixedPointCombMultiplier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.ec.ECNewRandomnessTransform */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECNewRandomnessTransform implements ECPairFactorTransform {
    private ECPublicKeyParameters key;
    private BigInteger lastK;
    private SecureRandom random;

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.p458ec.ECPairFactorTransform
    public BigInteger getTransformValue() {
        return this.lastK;
    }

    @Override // org.bouncycastle.crypto.p458ec.ECPairTransform
    public void init(CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            if (!(parametersWithRandom.getParameters() instanceof ECPublicKeyParameters)) {
                throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
            }
            this.key = (ECPublicKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else if (!(cipherParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
        } else {
            this.key = (ECPublicKeyParameters) cipherParameters;
            secureRandom = CryptoServicesRegistrar.getSecureRandom();
        }
        this.random = secureRandom;
    }

    @Override // org.bouncycastle.crypto.p458ec.ECPairTransform
    public ECPair transform(ECPair eCPair) {
        ECPublicKeyParameters eCPublicKeyParameters = this.key;
        if (eCPublicKeyParameters != null) {
            ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
            BigInteger n = parameters.getN();
            ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
            BigInteger generateK = ECUtil.generateK(n, this.random);
            ECPoint[] eCPointArr = {createBasePointMultiplier.multiply(parameters.getG(), generateK).add(ECAlgorithms.cleanPoint(parameters.getCurve(), eCPair.getX())), this.key.getQ().multiply(generateK).add(ECAlgorithms.cleanPoint(parameters.getCurve(), eCPair.getY()))};
            parameters.getCurve().normalizeAll(eCPointArr);
            this.lastK = generateK;
            return new ECPair(eCPointArr[0], eCPointArr[1]);
        }
        throw new IllegalStateException("ECNewRandomnessTransform not initialised");
    }
}
