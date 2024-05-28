package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NTRUPublicKeyParameters extends NTRUKeyParameters {
    final byte[] publicKey;

    public NTRUPublicKeyParameters(NTRUParameters nTRUParameters, byte[] bArr) {
        super(false, nTRUParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.publicKey);
    }
}
