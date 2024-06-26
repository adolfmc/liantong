package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.p464ec.ECAlgorithms;
import org.bouncycastle.math.p464ec.ECConstants;
import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECDHBasicAgreement implements BasicAgreement {
    private ECPrivateKeyParameters key;

    @Override // org.bouncycastle.crypto.BasicAgreement
    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) cipherParameters;
        ECDomainParameters parameters = this.key.getParameters();
        if (parameters.equals(eCPublicKeyParameters.getParameters())) {
            BigInteger d = this.key.getD();
            ECPoint cleanPoint = ECAlgorithms.cleanPoint(parameters.getCurve(), eCPublicKeyParameters.getQ());
            if (cleanPoint.isInfinity()) {
                throw new IllegalStateException("Infinity is not a valid public key for ECDH");
            }
            BigInteger h = parameters.getH();
            if (!h.equals(ECConstants.ONE)) {
                d = parameters.getHInv().multiply(d).mod(parameters.getN());
                cleanPoint = ECAlgorithms.referenceMultiply(cleanPoint, h);
            }
            ECPoint normalize = cleanPoint.multiply(d).normalize();
            if (normalize.isInfinity()) {
                throw new IllegalStateException("Infinity is not a valid agreement value for ECDH");
            }
            return normalize.getAffineXCoord().toBigInteger();
        }
        throw new IllegalStateException("ECDH public key has wrong domain parameters");
    }

    @Override // org.bouncycastle.crypto.BasicAgreement
    public int getFieldSize() {
        return (this.key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    @Override // org.bouncycastle.crypto.BasicAgreement
    public void init(CipherParameters cipherParameters) {
        this.key = (ECPrivateKeyParameters) cipherParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("ECDH", this.key));
    }
}
