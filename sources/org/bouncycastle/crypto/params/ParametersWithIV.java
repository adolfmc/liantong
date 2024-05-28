package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ParametersWithIV implements CipherParameters {

    /* renamed from: iv */
    private byte[] f26830iv;
    private CipherParameters parameters;

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.f26830iv = new byte[i2];
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i, this.f26830iv, 0, i2);
    }

    public byte[] getIV() {
        return this.f26830iv;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
