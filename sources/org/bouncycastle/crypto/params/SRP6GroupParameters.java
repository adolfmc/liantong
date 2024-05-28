package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SRP6GroupParameters {

    /* renamed from: N */
    private BigInteger f26836N;

    /* renamed from: g */
    private BigInteger f26837g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f26836N = bigInteger;
        this.f26837g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f26837g;
    }

    public BigInteger getN() {
        return this.f26836N;
    }
}
