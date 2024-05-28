package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ParametersWithUKM implements CipherParameters {
    private CipherParameters parameters;
    private byte[] ukm;

    public ParametersWithUKM(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithUKM(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.ukm = new byte[i2];
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i, this.ukm, 0, i2);
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public byte[] getUKM() {
        return this.ukm;
    }
}
