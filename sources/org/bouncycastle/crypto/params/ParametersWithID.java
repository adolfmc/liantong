package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ParametersWithID implements CipherParameters {

    /* renamed from: id */
    private byte[] f26829id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f26829id = bArr;
    }

    public byte[] getID() {
        return this.f26829id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
