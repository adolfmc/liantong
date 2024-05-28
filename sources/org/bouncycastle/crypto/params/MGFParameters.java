package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MGFParameters implements DerivationParameters {
    byte[] seed;

    public MGFParameters(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public MGFParameters(byte[] bArr, int i, int i2) {
        this.seed = new byte[i2];
        System.arraycopy(bArr, i, this.seed, 0, i2);
    }

    public byte[] getSeed() {
        return this.seed;
    }
}
