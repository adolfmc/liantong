package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LMSHSSKeyGenParameterSpec implements AlgorithmParameterSpec {
    private final LMSKeyGenParameterSpec[] specs;

    public LMSHSSKeyGenParameterSpec(LMSKeyGenParameterSpec... lMSKeyGenParameterSpecArr) {
        if (lMSKeyGenParameterSpecArr.length == 0) {
            throw new IllegalArgumentException("at least one LMSKeyGenParameterSpec required");
        }
        this.specs = (LMSKeyGenParameterSpec[]) lMSKeyGenParameterSpecArr.clone();
    }

    public LMSKeyGenParameterSpec[] getLMSSpecs() {
        return (LMSKeyGenParameterSpec[]) this.specs.clone();
    }
}
