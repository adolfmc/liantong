package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RC5Parameters implements CipherParameters {
    private byte[] key;
    private int rounds;

    public RC5Parameters(byte[] bArr, int i) {
        if (bArr.length > 255) {
            throw new IllegalArgumentException("RC5 key length can be no greater than 255");
        }
        this.key = new byte[bArr.length];
        this.rounds = i;
        System.arraycopy(bArr, 0, this.key, 0, bArr.length);
    }

    public byte[] getKey() {
        return this.key;
    }

    public int getRounds() {
        return this.rounds;
    }
}
