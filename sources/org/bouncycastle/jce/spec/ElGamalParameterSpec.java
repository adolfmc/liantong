package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: g */
    private BigInteger f26919g;

    /* renamed from: p */
    private BigInteger f26920p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f26920p = bigInteger;
        this.f26919g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f26919g;
    }

    public BigInteger getP() {
        return this.f26920p;
    }
}
