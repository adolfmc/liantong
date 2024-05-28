package org.bouncycastle.jcajce.spec;

import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KEMGenerateSpec implements AlgorithmParameterSpec {
    private final String keyAlgorithmName;
    private final PublicKey publicKey;

    public KEMGenerateSpec(PublicKey publicKey, String str) {
        this.publicKey = publicKey;
        this.keyAlgorithmName = str;
    }

    public String getKeyAlgorithmName() {
        return this.keyAlgorithmName;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
