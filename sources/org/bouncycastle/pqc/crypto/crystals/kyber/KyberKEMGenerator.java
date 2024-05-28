package org.bouncycastle.pqc.crypto.crystals.kyber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberKEMGenerator implements EncapsulatedSecretGenerator {

    /* renamed from: sr */
    private final SecureRandom f27129sr;

    public KyberKEMGenerator(SecureRandom secureRandom) {
        this.f27129sr = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        KyberPublicKeyParameters kyberPublicKeyParameters = (KyberPublicKeyParameters) asymmetricKeyParameter;
        KyberEngine engine = kyberPublicKeyParameters.getParameters().getEngine();
        engine.init(this.f27129sr);
        byte[][] kemEncrypt = engine.kemEncrypt(kyberPublicKeyParameters.getPublicKey());
        return new SecretWithEncapsulationImpl(kemEncrypt[0], kemEncrypt[1]);
    }
}
