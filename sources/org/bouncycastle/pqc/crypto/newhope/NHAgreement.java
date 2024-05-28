package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NHAgreement {
    private NHPrivateKeyParameters privKey;

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        byte[] bArr = new byte[32];
        NewHope.sharedA(bArr, this.privKey.secData, ((NHPublicKeyParameters) cipherParameters).pubData);
        return bArr;
    }

    public void init(CipherParameters cipherParameters) {
        this.privKey = (NHPrivateKeyParameters) cipherParameters;
    }
}
