package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RSABlindingParameters implements CipherParameters {
    private BigInteger blindingFactor;
    private RSAKeyParameters publicKey;

    public RSABlindingParameters(RSAKeyParameters rSAKeyParameters, BigInteger bigInteger) {
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("RSA parameters should be for a public key");
        }
        this.publicKey = rSAKeyParameters;
        this.blindingFactor = bigInteger;
    }

    public BigInteger getBlindingFactor() {
        return this.blindingFactor;
    }

    public RSAKeyParameters getPublicKey() {
        return this.publicKey;
    }
}
