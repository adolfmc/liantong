package org.bouncycastle.jcajce.spec;

import java.security.PrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KEMExtractSpec implements AlgorithmParameterSpec {
    private final byte[] encapsulation;
    private final String keyAlgorithmName;
    private final PrivateKey privateKey;

    public KEMExtractSpec(PrivateKey privateKey, byte[] bArr, String str) {
        this.privateKey = privateKey;
        this.encapsulation = Arrays.clone(bArr);
        this.keyAlgorithmName = str;
    }

    public byte[] getEncapsulation() {
        return Arrays.clone(this.encapsulation);
    }

    public String getKeyAlgorithmName() {
        return this.keyAlgorithmName;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
