package org.bouncycastle.crypto;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Commitment {
    private final byte[] commitment;
    private final byte[] secret;

    public Commitment(byte[] bArr, byte[] bArr2) {
        this.secret = bArr;
        this.commitment = bArr2;
    }

    public byte[] getCommitment() {
        return this.commitment;
    }

    public byte[] getSecret() {
        return this.secret;
    }
}
