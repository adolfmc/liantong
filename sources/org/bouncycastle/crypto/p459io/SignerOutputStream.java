package org.bouncycastle.crypto.p459io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.Signer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.io.SignerOutputStream */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SignerOutputStream extends OutputStream {
    protected Signer signer;

    public SignerOutputStream(Signer signer) {
        this.signer = signer;
    }

    public Signer getSigner() {
        return this.signer;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.signer.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.signer.update(bArr, i, i2);
    }
}
