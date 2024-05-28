package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HQCPrivateKeyParameters extends HQCKeyParameters {

    /* renamed from: sk */
    private byte[] f27189sk;

    public HQCPrivateKeyParameters(HQCParameters hQCParameters, byte[] bArr) {
        super(true, hQCParameters);
        this.f27189sk = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.f27189sk);
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.f27189sk);
    }
}
