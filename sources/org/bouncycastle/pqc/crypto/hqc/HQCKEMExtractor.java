package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQCKEMExtractor implements EncapsulatedSecretExtractor {
    private HQCEngine engine;
    private HQCKeyParameters key;

    public HQCKEMExtractor(HQCPrivateKeyParameters hQCPrivateKeyParameters) {
        this.key = hQCPrivateKeyParameters;
        initCipher(this.key.getParameters());
    }

    private void initCipher(HQCParameters hQCParameters) {
        this.engine = hQCParameters.getEngine();
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        byte[] bArr2 = new byte[this.engine.getSessionKeySize()];
        this.engine.decaps(bArr2, bArr, ((HQCPrivateKeyParameters) this.key).getPrivateKey());
        return Arrays.copyOfRange(bArr2, 0, this.key.getParameters().getK());
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.key.getParameters().getN_BYTES() + this.key.getParameters().getN1N2_BYTES() + 64;
    }
}
