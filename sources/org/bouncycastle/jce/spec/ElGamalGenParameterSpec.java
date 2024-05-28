package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ElGamalGenParameterSpec implements AlgorithmParameterSpec {
    private int primeSize;

    public ElGamalGenParameterSpec(int i) {
        this.primeSize = i;
    }

    public int getPrimeSize() {
        return this.primeSize;
    }
}
