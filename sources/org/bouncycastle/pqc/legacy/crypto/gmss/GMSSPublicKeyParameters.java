package org.bouncycastle.pqc.legacy.crypto.gmss;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GMSSPublicKeyParameters extends GMSSKeyParameters {
    private byte[] gmssPublicKey;

    public GMSSPublicKeyParameters(byte[] bArr, GMSSParameters gMSSParameters) {
        super(false, gMSSParameters);
        this.gmssPublicKey = bArr;
    }

    public byte[] getPublicKey() {
        return this.gmssPublicKey;
    }
}
