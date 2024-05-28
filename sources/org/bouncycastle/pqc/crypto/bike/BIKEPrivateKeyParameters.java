package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BIKEPrivateKeyParameters extends BIKEKeyParameters {

    /* renamed from: h0 */
    private byte[] f27111h0;

    /* renamed from: h1 */
    private byte[] f27112h1;
    private byte[] sigma;

    public BIKEPrivateKeyParameters(BIKEParameters bIKEParameters, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        super(true, bIKEParameters);
        this.f27111h0 = Arrays.clone(bArr);
        this.f27112h1 = Arrays.clone(bArr2);
        this.sigma = Arrays.clone(bArr3);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(Arrays.concatenate(this.f27111h0, this.f27112h1), this.sigma);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getH0() {
        return this.f27111h0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getH1() {
        return this.f27112h1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getSigma() {
        return this.sigma;
    }
}
