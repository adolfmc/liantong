package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ParametersWithSalt implements CipherParameters {
    private CipherParameters parameters;
    private byte[] salt;

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithSalt(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.salt = new byte[i2];
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i, this.salt, 0, i2);
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public byte[] getSalt() {
        return this.salt;
    }
}
