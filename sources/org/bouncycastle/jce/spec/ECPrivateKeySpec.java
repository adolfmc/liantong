package org.bouncycastle.jce.spec;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECPrivateKeySpec extends ECKeySpec {

    /* renamed from: d */
    private BigInteger f26917d;

    public ECPrivateKeySpec(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f26917d = bigInteger;
    }

    public BigInteger getD() {
        return this.f26917d;
    }
}
