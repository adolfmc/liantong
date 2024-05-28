package org.bouncycastle.crypto.agreement.kdf;

import org.bouncycastle.crypto.DerivationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GSKKDFParameters implements DerivationParameters {
    private final byte[] nonce;
    private final int startCounter;

    /* renamed from: z */
    private final byte[] f26382z;

    public GSKKDFParameters(byte[] bArr, int i) {
        this(bArr, i, null);
    }

    public GSKKDFParameters(byte[] bArr, int i, byte[] bArr2) {
        this.f26382z = bArr;
        this.startCounter = i;
        this.nonce = bArr2;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public int getStartCounter() {
        return this.startCounter;
    }

    public byte[] getZ() {
        return this.f26382z;
    }
}
