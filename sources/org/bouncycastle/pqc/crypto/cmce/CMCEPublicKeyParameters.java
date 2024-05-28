package org.bouncycastle.pqc.crypto.cmce;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CMCEPublicKeyParameters extends CMCEKeyParameters {
    private final byte[] publicKey;

    public CMCEPublicKeyParameters(CMCEParameters cMCEParameters, byte[] bArr) {
        super(false, cMCEParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.publicKey);
    }
}
