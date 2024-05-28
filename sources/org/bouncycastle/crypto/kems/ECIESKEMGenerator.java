package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECMultiplier;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.p464ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECIESKEMGenerator implements EncapsulatedSecretGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;
    private DerivationFunction kdf;
    private final int keySize;
    private SecureRandom rnd;

    public ECIESKEMGenerator(int i, DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.keySize = i;
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECIESKEMGenerator(int i, DerivationFunction derivationFunction, SecureRandom secureRandom, boolean z, boolean z2, boolean z3) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.keySize = i;
        this.CofactorMode = z;
        if (z) {
            this.OldCofactorMode = false;
        } else {
            this.OldCofactorMode = z2;
        }
        this.SingleHashMode = z3;
    }

    private ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] deriveKey(boolean z, DerivationFunction derivationFunction, int i, byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        if (z) {
            bArr3 = bArr2;
        } else {
            bArr3 = Arrays.concatenate(bArr, bArr2);
            Arrays.fill(bArr2, (byte) 0);
        }
        try {
            derivationFunction.init(new KDFParameters(bArr3, null));
            byte[] bArr4 = new byte[i];
            derivationFunction.generateBytes(bArr4, 0, bArr4.length);
            return bArr4;
        } finally {
            Arrays.fill(bArr3, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        if (asymmetricKeyParameter instanceof ECKeyParameters) {
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) asymmetricKeyParameter;
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("ECIESKem", ConstraintUtils.bitsOfSecurityFor(eCPublicKeyParameters.getParameters().getCurve()), asymmetricKeyParameter, CryptoServicePurpose.ENCRYPTION));
            ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
            ECCurve curve = parameters.getCurve();
            BigInteger n = parameters.getN();
            BigInteger h = parameters.getH();
            BigInteger createRandomInRange = BigIntegers.createRandomInRange(ONE, n, this.rnd);
            ECPoint[] eCPointArr = {createBasePointMultiplier().multiply(parameters.getG(), createRandomInRange), eCPublicKeyParameters.getQ().multiply(this.OldCofactorMode ? createRandomInRange.multiply(h).mod(n) : createRandomInRange)};
            curve.normalizeAll(eCPointArr);
            ECPoint eCPoint = eCPointArr[0];
            ECPoint eCPoint2 = eCPointArr[1];
            byte[] encoded = eCPoint.getEncoded(false);
            byte[] bArr = new byte[encoded.length];
            System.arraycopy(encoded, 0, bArr, 0, encoded.length);
            return new SecretWithEncapsulationImpl(deriveKey(this.SingleHashMode, this.kdf, this.keySize, encoded, eCPoint2.getAffineXCoord().getEncoded()), bArr);
        }
        throw new IllegalArgumentException("EC key required");
    }
}
