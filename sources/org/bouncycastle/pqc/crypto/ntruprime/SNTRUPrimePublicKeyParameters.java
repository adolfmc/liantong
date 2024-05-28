package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SNTRUPrimePublicKeyParameters extends SNTRUPrimeKeyParameters {
    private final byte[] encH;

    public SNTRUPrimePublicKeyParameters(SNTRUPrimeParameters sNTRUPrimeParameters, byte[] bArr) {
        super(false, sNTRUPrimeParameters);
        this.encH = Arrays.clone(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getEncH() {
        return this.encH;
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.encH);
    }
}
