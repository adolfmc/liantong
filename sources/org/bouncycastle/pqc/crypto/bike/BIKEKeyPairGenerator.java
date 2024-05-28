package org.bouncycastle.pqc.crypto.bike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKEKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private int L_BYTE;
    private int R_BYTE;
    private BIKEKeyGenerationParameters bikeKeyGenerationParameters;

    /* renamed from: hw */
    private int f27102hw;

    /* renamed from: l */
    private int f27103l;
    private int nbIter;

    /* renamed from: r */
    private int f27104r;
    private SecureRandom random;

    /* renamed from: t */
    private int f27105t;
    private int tau;

    /* renamed from: w */
    private int f27106w;

    private AsymmetricCipherKeyPair genKeyPair() {
        BIKEEngine engine = this.bikeKeyGenerationParameters.getParameters().getEngine();
        int i = this.R_BYTE;
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[i];
        byte[] bArr3 = new byte[i];
        byte[] bArr4 = new byte[this.L_BYTE];
        engine.genKeyPair(bArr, bArr2, bArr4, bArr3, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new BIKEPublicKeyParameters(this.bikeKeyGenerationParameters.getParameters(), bArr3), (AsymmetricKeyParameter) new BIKEPrivateKeyParameters(this.bikeKeyGenerationParameters.getParameters(), bArr, bArr2, bArr4));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.bikeKeyGenerationParameters = (BIKEKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.f27104r = this.bikeKeyGenerationParameters.getParameters().getR();
        this.f27106w = this.bikeKeyGenerationParameters.getParameters().getW();
        this.f27103l = this.bikeKeyGenerationParameters.getParameters().getL();
        this.f27105t = this.bikeKeyGenerationParameters.getParameters().getT();
        this.nbIter = this.bikeKeyGenerationParameters.getParameters().getNbIter();
        this.tau = this.bikeKeyGenerationParameters.getParameters().getTau();
        this.f27102hw = this.f27106w / 2;
        this.L_BYTE = this.f27103l / 8;
        this.R_BYTE = (this.f27104r + 7) / 8;
    }
}
