package org.bouncycastle.pqc.crypto.sike;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SIKEPrivateKeyParameters extends SIKEKeyParameters {
    private byte[] privateKey;

    public SIKEPrivateKeyParameters(SIKEParameters sIKEParameters, byte[] bArr) {
        super(true, sIKEParameters);
        this.privateKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.privateKey);
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.privateKey);
    }
}
