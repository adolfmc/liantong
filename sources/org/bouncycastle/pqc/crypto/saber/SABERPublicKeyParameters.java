package org.bouncycastle.pqc.crypto.saber;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SABERPublicKeyParameters extends SABERKeyParameters {
    private final byte[] publicKey;

    public SABERPublicKeyParameters(SABERParameters sABERParameters, byte[] bArr) {
        super(false, sABERParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.publicKey);
    }
}
