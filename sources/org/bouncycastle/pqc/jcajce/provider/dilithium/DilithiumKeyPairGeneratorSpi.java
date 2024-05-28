package org.bouncycastle.pqc.jcajce.provider.dilithium;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.DilithiumParameterSpec;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters = new HashMap();
    DilithiumKeyPairGenerator engine;
    boolean initialised;
    DilithiumKeyGenerationParameters param;
    SecureRandom random;

    static {
        parameters.put(DilithiumParameterSpec.dilithium2.getName(), DilithiumParameters.dilithium2);
        parameters.put(DilithiumParameterSpec.dilithium3.getName(), DilithiumParameters.dilithium3);
        parameters.put(DilithiumParameterSpec.dilithium5.getName(), DilithiumParameters.dilithium5);
        parameters.put(DilithiumParameterSpec.dilithium2_aes.getName(), DilithiumParameters.dilithium2_aes);
        parameters.put(DilithiumParameterSpec.dilithium3_aes.getName(), DilithiumParameters.dilithium3_aes);
        parameters.put(DilithiumParameterSpec.dilithium5_aes.getName(), DilithiumParameters.dilithium5_aes);
    }

    public DilithiumKeyPairGeneratorSpi() {
        super("Dilithium");
        this.engine = new DilithiumKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) {
        return algorithmParameterSpec instanceof DilithiumParameterSpec ? ((DilithiumParameterSpec) algorithmParameterSpec).getName() : Strings.toLowerCase(SpecUtil.getNameFrom(algorithmParameterSpec));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            this.param = new DilithiumKeyGenerationParameters(this.random, DilithiumParameters.dilithium3);
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCDilithiumPublicKey((DilithiumPublicKeyParameters) generateKeyPair.getPublic()), new BCDilithiumPrivateKey((DilithiumPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (getNameFromParams(algorithmParameterSpec) != null) {
            this.param = new DilithiumKeyGenerationParameters(secureRandom, (DilithiumParameters) parameters.get(getNameFromParams(algorithmParameterSpec)));
            this.engine.init(this.param);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("invalid ParameterSpec: " + algorithmParameterSpec);
    }
}
