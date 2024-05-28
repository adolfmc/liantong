package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPlusKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SPHINCSPlusParameters parameters;
    private SecureRandom random;

    private byte[] sec_rand(int i) {
        byte[] bArr = new byte[i];
        this.random.nextBytes(bArr);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        C13372SK c13372sk;
        byte[] sec_rand;
        SPHINCSPlusEngine engine = this.parameters.getEngine();
        if (engine instanceof SPHINCSPlusEngine.HarakaSEngine) {
            byte[] sec_rand2 = sec_rand(engine.f27250N * 3);
            byte[] bArr = new byte[engine.f27250N];
            byte[] bArr2 = new byte[engine.f27250N];
            sec_rand = new byte[engine.f27250N];
            System.arraycopy(sec_rand2, 0, bArr, 0, engine.f27250N);
            System.arraycopy(sec_rand2, engine.f27250N, bArr2, 0, engine.f27250N);
            System.arraycopy(sec_rand2, engine.f27250N << 1, sec_rand, 0, engine.f27250N);
            c13372sk = new C13372SK(bArr, bArr2);
        } else {
            c13372sk = new C13372SK(sec_rand(engine.f27250N), sec_rand(engine.f27250N));
            sec_rand = sec_rand(engine.f27250N);
        }
        engine.init(sec_rand);
        C13371PK c13371pk = new C13371PK(sec_rand, new C13370HT(engine, c13372sk.seed, sec_rand).htPubKey);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new SPHINCSPlusPublicKeyParameters(this.parameters, c13371pk), (AsymmetricKeyParameter) new SPHINCSPlusPrivateKeyParameters(this.parameters, c13372sk, c13371pk));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.parameters = ((SPHINCSPlusKeyGenerationParameters) keyGenerationParameters).getParameters();
    }
}
