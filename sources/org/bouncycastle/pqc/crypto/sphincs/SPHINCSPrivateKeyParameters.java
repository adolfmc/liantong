package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPrivateKeyParameters extends SPHINCSKeyParameters {
    private final byte[] keyData;

    public SPHINCSPrivateKeyParameters(byte[] bArr) {
        super(true, null);
        this.keyData = Arrays.clone(bArr);
    }

    public SPHINCSPrivateKeyParameters(byte[] bArr, String str) {
        super(true, str);
        this.keyData = Arrays.clone(bArr);
    }

    public byte[] getKeyData() {
        return Arrays.clone(this.keyData);
    }
}
