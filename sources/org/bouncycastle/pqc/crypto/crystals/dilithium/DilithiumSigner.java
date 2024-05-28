package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumSigner implements MessageSigner {
    private DilithiumPrivateKeyParameters privKey;
    private DilithiumPublicKeyParameters pubKey;
    private SecureRandom random;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return this.privKey.getParameters().getEngine(this.random).sign(bArr, bArr.length, this.privKey.rho, this.privKey.f27122k, this.privKey.f27127tr, this.privKey.f27125t0, this.privKey.f27123s1, this.privKey.f27124s2);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (!z) {
            this.pubKey = (DilithiumPublicKeyParameters) cipherParameters;
            return;
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.privKey = (DilithiumPrivateKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.privKey = (DilithiumPrivateKeyParameters) cipherParameters;
            secureRandom = null;
        }
        this.random = secureRandom;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return this.pubKey.getParameters().getEngine(this.random).signOpen(bArr, bArr2, bArr2.length, this.pubKey.rho, this.pubKey.f27128t1);
    }
}
