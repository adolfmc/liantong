package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberPublicKeyParameters extends KyberKeyParameters {
    final byte[] rho;

    /* renamed from: t */
    final byte[] f27133t;

    public KyberPublicKeyParameters(KyberParameters kyberParameters, byte[] bArr) {
        super(false, kyberParameters);
        this.f27133t = Arrays.copyOfRange(bArr, 0, bArr.length - 32);
        this.rho = Arrays.copyOfRange(bArr, bArr.length - 32, bArr.length);
    }

    public KyberPublicKeyParameters(KyberParameters kyberParameters, byte[] bArr, byte[] bArr2) {
        super(false, kyberParameters);
        this.f27133t = Arrays.clone(bArr);
        this.rho = Arrays.clone(bArr2);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.f27133t, this.rho);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getT() {
        return Arrays.clone(this.f27133t);
    }
}
