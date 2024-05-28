package org.bouncycastle.pqc.crypto.cmce;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private CMCEKeyGenerationParameters cmceParams;

    /* renamed from: m */
    private int f27115m;

    /* renamed from: n */
    private int f27116n;
    private SecureRandom random;

    /* renamed from: t */
    private int f27117t;

    private AsymmetricCipherKeyPair genKeyPair() {
        CMCEEngine engine = this.cmceParams.getParameters().getEngine();
        byte[] bArr = new byte[engine.getPrivateKeySize()];
        byte[] bArr2 = new byte[engine.getPublicKeySize()];
        engine.kem_keypair(bArr2, bArr, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new CMCEPublicKeyParameters(this.cmceParams.getParameters(), bArr2), (AsymmetricKeyParameter) new CMCEPrivateKeyParameters(this.cmceParams.getParameters(), bArr));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.cmceParams = (CMCEKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.f27115m = this.cmceParams.getParameters().getM();
        this.f27116n = this.cmceParams.getParameters().getN();
        this.f27117t = this.cmceParams.getParameters().getT();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
