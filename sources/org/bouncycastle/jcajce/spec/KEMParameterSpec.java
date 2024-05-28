package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KEMParameterSpec implements AlgorithmParameterSpec {
    private final String keyAlgorithmName;
    private final int keySizeInBits;

    public KEMParameterSpec(String str) {
        this(str, -1);
    }

    public KEMParameterSpec(String str, int i) {
        this.keyAlgorithmName = str;
        this.keySizeInBits = i;
    }

    public String getKeyAlgorithmName() {
        return this.keyAlgorithmName;
    }

    public int getKeySizeInBits() {
        return this.keySizeInBits;
    }
}
