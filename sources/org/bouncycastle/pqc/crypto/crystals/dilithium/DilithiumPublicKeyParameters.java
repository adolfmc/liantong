package org.bouncycastle.pqc.crypto.crystals.dilithium;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DilithiumPublicKeyParameters extends DilithiumKeyParameters {
    final byte[] rho;

    /* renamed from: t1 */
    final byte[] f27128t1;

    public DilithiumPublicKeyParameters(DilithiumParameters dilithiumParameters, byte[] bArr) {
        super(false, dilithiumParameters);
        this.rho = Arrays.copyOfRange(bArr, 0, 32);
        this.f27128t1 = Arrays.copyOfRange(bArr, 32, bArr.length);
    }

    public DilithiumPublicKeyParameters(DilithiumParameters dilithiumParameters, byte[] bArr, byte[] bArr2) {
        super(false, dilithiumParameters);
        this.rho = Arrays.clone(bArr);
        this.f27128t1 = Arrays.clone(bArr2);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.rho, this.f27128t1);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getT1() {
        return Arrays.clone(this.f27128t1);
    }
}
