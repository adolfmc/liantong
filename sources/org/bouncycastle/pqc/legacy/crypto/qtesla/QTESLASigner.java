package org.bouncycastle.pqc.legacy.crypto.qtesla;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class QTESLASigner implements MessageSigner {
    private QTESLAPrivateKeyParameters privateKey;
    private QTESLAPublicKeyParameters publicKey;
    private SecureRandom secureRandom;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] bArr2 = new byte[QTESLASecurityCategory.getSignatureSize(this.privateKey.getSecurityCategory())];
        switch (this.privateKey.getSecurityCategory()) {
            case 5:
                QTesla1p.generateSignature(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
                break;
            case 6:
                QTesla3p.generateSignature(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
                break;
            default:
                throw new IllegalArgumentException("unknown security category: " + this.privateKey.getSecurityCategory());
        }
        return bArr2;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        int securityCategory;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.secureRandom = parametersWithRandom.getRandom();
                this.privateKey = (QTESLAPrivateKeyParameters) parametersWithRandom.getParameters();
            } else {
                this.secureRandom = CryptoServicesRegistrar.getSecureRandom();
                this.privateKey = (QTESLAPrivateKeyParameters) cipherParameters;
            }
            this.publicKey = null;
            securityCategory = this.privateKey.getSecurityCategory();
        } else {
            this.privateKey = null;
            this.publicKey = (QTESLAPublicKeyParameters) cipherParameters;
            securityCategory = this.publicKey.getSecurityCategory();
        }
        QTESLASecurityCategory.validate(securityCategory);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        int verifying;
        switch (this.publicKey.getSecurityCategory()) {
            case 5:
                verifying = QTesla1p.verifying(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
                break;
            case 6:
                verifying = QTesla3p.verifying(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
                break;
            default:
                throw new IllegalArgumentException("unknown security category: " + this.publicKey.getSecurityCategory());
        }
        return verifying == 0;
    }
}
