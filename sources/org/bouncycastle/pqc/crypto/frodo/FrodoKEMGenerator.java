package org.bouncycastle.pqc.crypto.frodo;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FrodoKEMGenerator implements EncapsulatedSecretGenerator {

    /* renamed from: sr */
    private final SecureRandom f27158sr;

    public FrodoKEMGenerator(SecureRandom secureRandom) {
        this.f27158sr = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        FrodoPublicKeyParameters frodoPublicKeyParameters = (FrodoPublicKeyParameters) asymmetricKeyParameter;
        FrodoEngine engine = frodoPublicKeyParameters.getParameters().getEngine();
        byte[] bArr = new byte[engine.getCipherTextSize()];
        byte[] bArr2 = new byte[engine.getSessionKeySize()];
        engine.kem_enc(bArr, bArr2, frodoPublicKeyParameters.getPublicKey(), this.f27158sr);
        return new SecretWithEncapsulationImpl(bArr2, bArr);
    }
}
