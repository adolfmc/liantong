package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FalconPublicKeyParameters extends FalconKeyParameters {

    /* renamed from: H */
    private byte[] f27142H;

    public FalconPublicKeyParameters(FalconParameters falconParameters, byte[] bArr) {
        super(false, falconParameters);
        this.f27142H = Arrays.clone(bArr);
    }

    public byte[] getH() {
        return Arrays.clone(this.f27142H);
    }
}
