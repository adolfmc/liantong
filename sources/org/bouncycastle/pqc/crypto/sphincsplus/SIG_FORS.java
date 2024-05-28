package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SIG_FORS {
    final byte[][] authPath;

    /* renamed from: sk */
    final byte[] f27245sk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SIG_FORS(byte[] bArr, byte[][] bArr2) {
        this.authPath = bArr2;
        this.f27245sk = bArr;
    }

    public byte[][] getAuthPath() {
        return this.authPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getSK() {
        return this.f27245sk;
    }
}
