package org.bouncycastle.pqc.crypto.hqc;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQCKEMGenerator implements EncapsulatedSecretGenerator {

    /* renamed from: sr */
    private final SecureRandom f27175sr;

    public HQCKEMGenerator(SecureRandom secureRandom) {
        this.f27175sr = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        HQCPublicKeyParameters hQCPublicKeyParameters = (HQCPublicKeyParameters) asymmetricKeyParameter;
        HQCEngine engine = hQCPublicKeyParameters.getParameters().getEngine();
        byte[] bArr = new byte[hQCPublicKeyParameters.getParameters().getSHA512_BYTES()];
        byte[] bArr2 = new byte[hQCPublicKeyParameters.getParameters().getN_BYTES()];
        byte[] bArr3 = new byte[hQCPublicKeyParameters.getParameters().getN1N2_BYTES()];
        byte[] bArr4 = new byte[hQCPublicKeyParameters.getParameters().getSHA512_BYTES()];
        byte[] publicKey = hQCPublicKeyParameters.getPublicKey();
        byte[] bArr5 = new byte[48];
        this.f27175sr.nextBytes(bArr5);
        engine.encaps(bArr2, bArr3, bArr, bArr4, publicKey, bArr5);
        return new SecretWithEncapsulationImpl(Arrays.copyOfRange(bArr, 0, hQCPublicKeyParameters.getParameters().getK()), Arrays.concatenate(Arrays.concatenate(bArr2, bArr3), bArr4));
    }
}
