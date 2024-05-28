package org.bouncycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;
import org.bouncycastle.pqc.math.ntru.Polynomial;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRUKEMGenerator implements EncapsulatedSecretGenerator {
    private final SecureRandom random;

    public NTRUKEMGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        NTRUPublicKeyParameters nTRUPublicKeyParameters = (NTRUPublicKeyParameters) asymmetricKeyParameter;
        NTRUParameterSet nTRUParameterSet = nTRUPublicKeyParameters.getParameters().parameterSet;
        NTRUSampling nTRUSampling = new NTRUSampling(nTRUParameterSet);
        NTRUOWCPA ntruowcpa = new NTRUOWCPA(nTRUParameterSet);
        byte[] bArr = new byte[nTRUParameterSet.owcpaMsgBytes()];
        byte[] bArr2 = new byte[nTRUParameterSet.sampleRmBytes()];
        this.random.nextBytes(bArr2);
        PolynomialPair sampleRm = nTRUSampling.sampleRm(bArr2);
        Polynomial m248r = sampleRm.m248r();
        Polynomial m249m = sampleRm.m249m();
        byte[] s3ToBytes = m248r.s3ToBytes(nTRUParameterSet.owcpaMsgBytes());
        System.arraycopy(s3ToBytes, 0, bArr, 0, s3ToBytes.length);
        byte[] s3ToBytes2 = m249m.s3ToBytes(bArr.length - nTRUParameterSet.packTrinaryBytes());
        System.arraycopy(s3ToBytes2, 0, bArr, nTRUParameterSet.packTrinaryBytes(), s3ToBytes2.length);
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[sHA3Digest.getDigestSize()];
        sHA3Digest.doFinal(bArr3, 0);
        m248r.z3ToZq();
        byte[] encrypt = ntruowcpa.encrypt(m248r, m249m, nTRUPublicKeyParameters.publicKey);
        byte[] copyOfRange = Arrays.copyOfRange(bArr3, 0, nTRUParameterSet.sharedKeyBytes());
        Arrays.clear(bArr3);
        return new SecretWithEncapsulationImpl(copyOfRange, encrypt);
    }
}
