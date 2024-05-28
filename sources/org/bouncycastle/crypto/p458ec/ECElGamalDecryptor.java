package org.bouncycastle.crypto.p458ec;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.p464ec.ECAlgorithms;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.ec.ECElGamalDecryptor */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECElGamalDecryptor implements ECDecryptor {
    private ECPrivateKeyParameters key;

    @Override // org.bouncycastle.crypto.p458ec.ECDecryptor
    public ECPoint decrypt(ECPair eCPair) {
        ECPrivateKeyParameters eCPrivateKeyParameters = this.key;
        if (eCPrivateKeyParameters != null) {
            ECCurve curve = eCPrivateKeyParameters.getParameters().getCurve();
            return ECAlgorithms.cleanPoint(curve, eCPair.getY()).subtract(ECAlgorithms.cleanPoint(curve, eCPair.getX()).multiply(this.key.getD())).normalize();
        }
        throw new IllegalStateException("ECElGamalDecryptor not initialised");
    }

    @Override // org.bouncycastle.crypto.p458ec.ECDecryptor
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
        }
        this.key = (ECPrivateKeyParameters) cipherParameters;
    }
}
