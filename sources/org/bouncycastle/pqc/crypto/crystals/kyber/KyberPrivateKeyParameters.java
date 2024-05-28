package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberPrivateKeyParameters extends KyberKeyParameters {
    final byte[] hpk;
    final byte[] nonce;
    final byte[] rho;

    /* renamed from: s */
    final byte[] f27131s;

    /* renamed from: t */
    final byte[] f27132t;

    public KyberPrivateKeyParameters(KyberParameters kyberParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        super(true, kyberParameters);
        this.f27131s = Arrays.clone(bArr);
        this.hpk = Arrays.clone(bArr2);
        this.nonce = Arrays.clone(bArr3);
        this.f27132t = Arrays.clone(bArr4);
        this.rho = Arrays.clone(bArr5);
    }

    public byte[] getEncoded() {
        return getPrivateKey();
    }

    public byte[] getHPK() {
        return Arrays.clone(this.hpk);
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    public byte[] getPrivateKey() {
        return Arrays.concatenate(this.f27131s, getPublicKey(), this.hpk, this.nonce);
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.f27132t, this.rho);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS() {
        return Arrays.clone(this.f27131s);
    }

    public byte[] getT() {
        return Arrays.clone(this.f27132t);
    }
}
