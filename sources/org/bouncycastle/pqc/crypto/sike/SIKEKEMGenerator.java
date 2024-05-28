package org.bouncycastle.pqc.crypto.sike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SIKEKEMGenerator implements EncapsulatedSecretGenerator {

    /* renamed from: sr */
    private final SecureRandom f27243sr;

    public SIKEKEMGenerator(SecureRandom secureRandom) {
        this.f27243sr = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("SIKEKEM", 0, asymmetricKeyParameter, CryptoServicePurpose.ENCRYPTION));
        return generateEncapsulated(asymmetricKeyParameter, ((SIKEPublicKeyParameters) asymmetricKeyParameter).getParameters().getEngine().getDefaultSessionKeySize());
    }

    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter, int i) {
        System.err.println("WARNING: the SIKE algorithm is only for research purposes, insecure");
        SIKEPublicKeyParameters sIKEPublicKeyParameters = (SIKEPublicKeyParameters) asymmetricKeyParameter;
        SIKEEngine engine = sIKEPublicKeyParameters.getParameters().getEngine();
        byte[] bArr = new byte[engine.getCipherTextSize()];
        byte[] bArr2 = new byte[i / 8];
        engine.crypto_kem_enc(bArr, bArr2, sIKEPublicKeyParameters.getPublicKey(), this.f27243sr);
        return new SecretWithEncapsulationImpl(bArr2, bArr);
    }
}
