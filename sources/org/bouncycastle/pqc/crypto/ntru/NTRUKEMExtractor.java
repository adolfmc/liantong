package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRUKEMExtractor implements EncapsulatedSecretExtractor {
    private final NTRUPrivateKeyParameters ntruPrivateKey;
    private final NTRUParameters params;

    public NTRUKEMExtractor(NTRUPrivateKeyParameters nTRUPrivateKeyParameters) {
        this.params = nTRUPrivateKeyParameters.getParameters();
        this.ntruPrivateKey = nTRUPrivateKeyParameters;
    }

    private void cmov(byte[] bArr, byte[] bArr2, byte b) {
        byte b2 = (byte) ((~b) + 1);
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ ((bArr2[i] ^ bArr[i]) & b2));
        }
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        NTRUParameterSet nTRUParameterSet = this.params.parameterSet;
        byte[] bArr2 = this.ntruPrivateKey.privateKey;
        byte[] bArr3 = new byte[nTRUParameterSet.prfKeyBytes() + nTRUParameterSet.ntruCiphertextBytes()];
        OWCPADecryptResult decrypt = new NTRUOWCPA(nTRUParameterSet).decrypt(bArr, this.ntruPrivateKey.privateKey);
        byte[] bArr4 = decrypt.f27220rm;
        int i = decrypt.fail;
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        byte[] bArr5 = new byte[sHA3Digest.getDigestSize()];
        sHA3Digest.update(bArr4, 0, bArr4.length);
        sHA3Digest.doFinal(bArr5, 0);
        for (int i2 = 0; i2 < nTRUParameterSet.prfKeyBytes(); i2++) {
            bArr3[i2] = bArr2[nTRUParameterSet.owcpaSecretKeyBytes() + i2];
        }
        for (int i3 = 0; i3 < nTRUParameterSet.ntruCiphertextBytes(); i3++) {
            bArr3[nTRUParameterSet.prfKeyBytes() + i3] = bArr[i3];
        }
        sHA3Digest.reset();
        sHA3Digest.update(bArr3, 0, bArr3.length);
        sHA3Digest.doFinal(bArr4, 0);
        cmov(bArr5, bArr4, (byte) i);
        byte[] copyOfRange = Arrays.copyOfRange(bArr5, 0, nTRUParameterSet.sharedKeyBytes());
        Arrays.clear(bArr5);
        return copyOfRange;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.params.parameterSet.ntruCiphertextBytes();
    }
}
