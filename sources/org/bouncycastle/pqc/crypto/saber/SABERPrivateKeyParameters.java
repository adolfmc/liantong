package org.bouncycastle.pqc.crypto.saber;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SABERPrivateKeyParameters extends SABERKeyParameters {
    private byte[] privateKey;

    public SABERPrivateKeyParameters(SABERParameters sABERParameters, byte[] bArr) {
        super(true, sABERParameters);
        this.privateKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.privateKey);
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.privateKey);
    }
}
