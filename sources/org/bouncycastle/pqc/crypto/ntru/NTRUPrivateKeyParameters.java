package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRUPrivateKeyParameters extends NTRUKeyParameters {
    final byte[] privateKey;

    public NTRUPrivateKeyParameters(NTRUParameters nTRUParameters, byte[] bArr) {
        super(true, nTRUParameters);
        this.privateKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPrivateKey();
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.privateKey);
    }
}
