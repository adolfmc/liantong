package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ECDHUPrivateParameters;
import org.bouncycastle.crypto.params.ECDHUPublicParameters;
import org.bouncycastle.util.BigIntegers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECDHCUnifiedAgreement {
    private ECDHUPrivateParameters privParams;

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        ECDHUPublicParameters eCDHUPublicParameters = (ECDHUPublicParameters) cipherParameters;
        ECDHCBasicAgreement eCDHCBasicAgreement = new ECDHCBasicAgreement();
        ECDHCBasicAgreement eCDHCBasicAgreement2 = new ECDHCBasicAgreement();
        eCDHCBasicAgreement.init(this.privParams.getStaticPrivateKey());
        BigInteger calculateAgreement = eCDHCBasicAgreement.calculateAgreement(eCDHUPublicParameters.getStaticPublicKey());
        eCDHCBasicAgreement2.init(this.privParams.getEphemeralPrivateKey());
        BigInteger calculateAgreement2 = eCDHCBasicAgreement2.calculateAgreement(eCDHUPublicParameters.getEphemeralPublicKey());
        int fieldSize = getFieldSize();
        byte[] bArr = new byte[fieldSize * 2];
        BigIntegers.asUnsignedByteArray(calculateAgreement2, bArr, 0, fieldSize);
        BigIntegers.asUnsignedByteArray(calculateAgreement, bArr, fieldSize, fieldSize);
        return bArr;
    }

    public int getFieldSize() {
        return (this.privParams.getStaticPrivateKey().getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public void init(CipherParameters cipherParameters) {
        this.privParams = (ECDHUPrivateParameters) cipherParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("ECCDHU", this.privParams.getStaticPrivateKey()));
    }
}
