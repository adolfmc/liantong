package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FalconPrivateKeyParameters extends FalconKeyParameters {

    /* renamed from: F */
    private final byte[] f27138F;

    /* renamed from: f */
    private final byte[] f27139f;

    /* renamed from: g */
    private final byte[] f27140g;

    /* renamed from: pk */
    private final byte[] f27141pk;

    public FalconPrivateKeyParameters(FalconParameters falconParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        super(true, falconParameters);
        this.f27139f = Arrays.clone(bArr);
        this.f27140g = Arrays.clone(bArr2);
        this.f27138F = Arrays.clone(bArr3);
        this.f27141pk = Arrays.clone(bArr4);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.f27139f, this.f27140g, this.f27138F);
    }

    public byte[] getG() {
        return Arrays.clone(this.f27140g);
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.f27141pk);
    }

    public byte[] getSpolyF() {
        return Arrays.clone(this.f27138F);
    }

    public byte[] getSpolyf() {
        return Arrays.clone(this.f27139f);
    }
}
