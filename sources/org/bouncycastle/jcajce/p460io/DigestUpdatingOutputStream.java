package org.bouncycastle.jcajce.p460io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.jcajce.io.DigestUpdatingOutputStream */
/* loaded from: E:\9227576_dexfile_execute.dex */
class DigestUpdatingOutputStream extends OutputStream {
    private MessageDigest digest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestUpdatingOutputStream(MessageDigest messageDigest) {
        this.digest = messageDigest;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.digest.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.digest.update(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.digest.update(bArr, i, i2);
    }
}
