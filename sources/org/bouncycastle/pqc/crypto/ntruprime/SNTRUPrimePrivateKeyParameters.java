package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SNTRUPrimePrivateKeyParameters extends SNTRUPrimeKeyParameters {

    /* renamed from: f */
    private final byte[] f27230f;
    private final byte[] ginv;
    private final byte[] hash;

    /* renamed from: pk */
    private final byte[] f27231pk;
    private final byte[] rho;

    public SNTRUPrimePrivateKeyParameters(SNTRUPrimeParameters sNTRUPrimeParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        super(true, sNTRUPrimeParameters);
        this.f27230f = Arrays.clone(bArr);
        this.ginv = Arrays.clone(bArr2);
        this.f27231pk = Arrays.clone(bArr3);
        this.rho = Arrays.clone(bArr4);
        this.hash = Arrays.clone(bArr5);
    }

    public byte[] getEncoded() {
        byte[] bArr = new byte[getParameters().getPrivateKeyBytes()];
        byte[] bArr2 = this.f27230f;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        byte[] bArr3 = this.ginv;
        System.arraycopy(bArr3, 0, bArr, this.f27230f.length, bArr3.length);
        byte[] bArr4 = this.f27231pk;
        System.arraycopy(bArr4, 0, bArr, this.f27230f.length + this.ginv.length, bArr4.length);
        byte[] bArr5 = this.rho;
        System.arraycopy(bArr5, 0, bArr, this.f27230f.length + this.ginv.length + this.f27231pk.length, bArr5.length);
        byte[] bArr6 = this.hash;
        System.arraycopy(bArr6, 0, bArr, this.f27230f.length + this.ginv.length + this.f27231pk.length + this.rho.length, bArr6.length);
        return bArr;
    }

    public byte[] getF() {
        return Arrays.clone(this.f27230f);
    }

    public byte[] getGinv() {
        return Arrays.clone(this.ginv);
    }

    public byte[] getHash() {
        return Arrays.clone(this.hash);
    }

    public byte[] getPk() {
        return Arrays.clone(this.f27231pk);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }
}
