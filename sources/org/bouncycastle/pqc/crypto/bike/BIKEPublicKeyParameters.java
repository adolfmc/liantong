package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKEPublicKeyParameters extends BIKEKeyParameters {
    byte[] publicKey;

    public BIKEPublicKeyParameters(BIKEParameters bIKEParameters, byte[] bArr) {
        super(false, bIKEParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.publicKey);
    }
}
