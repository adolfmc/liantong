package org.bouncycastle.pqc.crypto.hqc;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQCKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private int N_BYTE;
    private int delta;
    private HQCKeyGenerationParameters hqcKeyGenerationParameters;

    /* renamed from: k */
    private int f27176k;

    /* renamed from: n */
    private int f27177n;
    private SecureRandom random;

    /* renamed from: w */
    private int f27178w;

    /* renamed from: we */
    private int f27179we;

    /* renamed from: wr */
    private int f27180wr;

    private AsymmetricCipherKeyPair genKeyPair(byte[] bArr) {
        HQCEngine engine = this.hqcKeyGenerationParameters.getParameters().getEngine();
        int i = this.N_BYTE;
        byte[] bArr2 = new byte[i + 40];
        byte[] bArr3 = new byte[i + 80];
        engine.genKeyPair(bArr2, bArr3, bArr);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new HQCPublicKeyParameters(this.hqcKeyGenerationParameters.getParameters(), bArr2), (AsymmetricKeyParameter) new HQCPrivateKeyParameters(this.hqcKeyGenerationParameters.getParameters(), bArr3));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[] bArr = new byte[48];
        this.random.nextBytes(bArr);
        return genKeyPair(bArr);
    }

    public AsymmetricCipherKeyPair generateKeyPairWithSeed(byte[] bArr) {
        return genKeyPair(bArr);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.hqcKeyGenerationParameters = (HQCKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.f27177n = this.hqcKeyGenerationParameters.getParameters().getN();
        this.f27176k = this.hqcKeyGenerationParameters.getParameters().getK();
        this.delta = this.hqcKeyGenerationParameters.getParameters().getDelta();
        this.f27178w = this.hqcKeyGenerationParameters.getParameters().getW();
        this.f27180wr = this.hqcKeyGenerationParameters.getParameters().getWr();
        this.f27179we = this.hqcKeyGenerationParameters.getParameters().getWe();
        this.N_BYTE = (this.f27177n + 7) / 8;
    }
}
