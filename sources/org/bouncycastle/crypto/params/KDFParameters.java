package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KDFParameters implements DerivationParameters {

    /* renamed from: iv */
    byte[] f26826iv;
    byte[] shared;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.shared = bArr;
        this.f26826iv = bArr2;
    }

    public byte[] getIV() {
        return this.f26826iv;
    }

    public byte[] getSharedSecret() {
        return this.shared;
    }
}
