package org.bouncycastle.pqc.crypto.picnic;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PicnicPublicKeyParameters extends PicnicKeyParameters {
    private final byte[] publicKey;

    public PicnicPublicKeyParameters(PicnicParameters picnicParameters, byte[] bArr) {
        super(false, picnicParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.publicKey);
    }
}
