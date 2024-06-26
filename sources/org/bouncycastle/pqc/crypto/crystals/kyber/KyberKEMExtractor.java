package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberKEMExtractor implements EncapsulatedSecretExtractor {
    private KyberEngine engine;
    private KyberPrivateKeyParameters key;

    public KyberKEMExtractor(KyberPrivateKeyParameters kyberPrivateKeyParameters) {
        this.key = kyberPrivateKeyParameters;
        initCipher(kyberPrivateKeyParameters);
    }

    private void initCipher(AsymmetricKeyParameter asymmetricKeyParameter) {
        this.engine = ((KyberPrivateKeyParameters) asymmetricKeyParameter).getParameters().getEngine();
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        return this.engine.kemDecrypt(bArr, this.key.getPrivateKey());
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.engine.getCryptoCipherTextBytes();
    }
}
