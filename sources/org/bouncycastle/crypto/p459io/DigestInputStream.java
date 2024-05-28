package org.bouncycastle.crypto.p459io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.Digest;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.io.DigestInputStream */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DigestInputStream extends FilterInputStream {
    protected Digest digest;

    public DigestInputStream(InputStream inputStream, Digest digest) {
        super(inputStream);
        this.digest = digest;
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = this.in.read();
        if (read >= 0) {
            this.digest.update((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read > 0) {
            this.digest.update(bArr, i, read);
        }
        return read;
    }
}
