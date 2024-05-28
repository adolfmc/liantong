package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ISO18033KDFParameters implements DerivationParameters {
    byte[] seed;

    public ISO18033KDFParameters(byte[] bArr) {
        this.seed = bArr;
    }

    public byte[] getSeed() {
        return this.seed;
    }
}
