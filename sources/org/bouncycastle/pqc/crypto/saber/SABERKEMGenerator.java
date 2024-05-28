package org.bouncycastle.pqc.crypto.saber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SABERKEMGenerator implements EncapsulatedSecretGenerator {

    /* renamed from: sr */
    private final SecureRandom f27235sr;

    public SABERKEMGenerator(SecureRandom secureRandom) {
        this.f27235sr = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        SABERPublicKeyParameters sABERPublicKeyParameters = (SABERPublicKeyParameters) asymmetricKeyParameter;
        SABEREngine engine = sABERPublicKeyParameters.getParameters().getEngine();
        byte[] bArr = new byte[engine.getCipherTextSize()];
        byte[] bArr2 = new byte[engine.getSessionKeySize()];
        engine.crypto_kem_enc(bArr, bArr2, sABERPublicKeyParameters.getPublicKey(), this.f27235sr);
        return new SecretWithEncapsulationImpl(bArr2, bArr);
    }
}
