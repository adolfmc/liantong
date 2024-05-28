package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSTU4145KeyPairGenerator extends ECKeyPairGenerator {
    @Override // org.bouncycastle.crypto.generators.ECKeyPairGenerator, org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        AsymmetricCipherKeyPair generateKeyPair = super.generateKeyPair();
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) generateKeyPair.getPublic();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(eCPublicKeyParameters.getQ().negate(), eCPublicKeyParameters.getParameters()), (AsymmetricKeyParameter) ((ECPrivateKeyParameters) generateKeyPair.getPrivate()));
    }
}
