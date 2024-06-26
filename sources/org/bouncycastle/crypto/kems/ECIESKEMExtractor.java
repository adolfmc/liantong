package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECIESKEMExtractor implements EncapsulatedSecretExtractor {
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;
    private final ECPrivateKeyParameters decKey;
    private DerivationFunction kdf;
    private int keyLen;

    public ECIESKEMExtractor(ECPrivateKeyParameters eCPrivateKeyParameters, int i, DerivationFunction derivationFunction) {
        this.decKey = eCPrivateKeyParameters;
        this.keyLen = i;
        this.kdf = derivationFunction;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECIESKEMExtractor(ECPrivateKeyParameters eCPrivateKeyParameters, int i, DerivationFunction derivationFunction, boolean z, boolean z2, boolean z3) {
        this.decKey = eCPrivateKeyParameters;
        this.keyLen = i;
        this.kdf = derivationFunction;
        this.CofactorMode = z;
        if (z) {
            this.OldCofactorMode = false;
        } else {
            this.OldCofactorMode = z2;
        }
        this.SingleHashMode = z3;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("ECIESKem", ConstraintUtils.bitsOfSecurityFor(this.decKey.getParameters().getCurve()), eCPrivateKeyParameters, CryptoServicePurpose.DECRYPTION));
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        ECPrivateKeyParameters eCPrivateKeyParameters = this.decKey;
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        ECCurve curve = parameters.getCurve();
        BigInteger n = parameters.getN();
        BigInteger h = parameters.getH();
        ECPoint decodePoint = curve.decodePoint(bArr);
        if (this.CofactorMode || this.OldCofactorMode) {
            decodePoint = decodePoint.multiply(h);
        }
        BigInteger d = eCPrivateKeyParameters.getD();
        if (this.CofactorMode) {
            d = d.multiply(parameters.getHInv()).mod(n);
        }
        return ECIESKEMGenerator.deriveKey(this.SingleHashMode, this.kdf, this.keyLen, bArr, decodePoint.multiply(d).normalize().getAffineXCoord().getEncoded());
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return ((this.decKey.getParameters().getCurve().getFieldSize() / 8) * 2) + 1;
    }
}
