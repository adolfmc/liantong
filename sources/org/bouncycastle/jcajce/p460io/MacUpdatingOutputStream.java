package org.bouncycastle.jcajce.p460io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.jcajce.io.MacUpdatingOutputStream */
/* loaded from: E:\9227576_dexfile_execute.dex */
class MacUpdatingOutputStream extends OutputStream {
    private Mac mac;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MacUpdatingOutputStream(Mac mac) {
        this.mac = mac;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.mac.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.mac.update(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.mac.update(bArr, i, i2);
    }
}
