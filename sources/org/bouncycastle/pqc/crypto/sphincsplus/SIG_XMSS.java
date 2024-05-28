package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SIG_XMSS {
    final byte[][] auth;
    final byte[] sig;

    public SIG_XMSS(byte[] bArr, byte[][] bArr2) {
        this.sig = bArr;
        this.auth = bArr2;
    }

    public byte[] getWOTSSig() {
        return this.sig;
    }

    public byte[][] getXMSSAUTH() {
        return this.auth;
    }
}
