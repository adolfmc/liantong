package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LMSHSSParameterSpec implements AlgorithmParameterSpec {
    private final LMSParameterSpec[] specs;

    public LMSHSSParameterSpec(LMSParameterSpec[] lMSParameterSpecArr) {
        this.specs = (LMSParameterSpec[]) lMSParameterSpecArr.clone();
    }

    public LMSParameterSpec[] getLMSSpecs() {
        return (LMSParameterSpec[]) this.specs.clone();
    }
}
