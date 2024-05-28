package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UserKeyingMaterialSpec implements AlgorithmParameterSpec {
    private final byte[] salt;
    private final byte[] userKeyingMaterial;

    public UserKeyingMaterialSpec(byte[] bArr) {
        this(bArr, null);
    }

    public UserKeyingMaterialSpec(byte[] bArr, byte[] bArr2) {
        this.userKeyingMaterial = Arrays.clone(bArr);
        this.salt = Arrays.clone(bArr2);
    }

    public byte[] getSalt() {
        return Arrays.clone(this.salt);
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.userKeyingMaterial);
    }
}
