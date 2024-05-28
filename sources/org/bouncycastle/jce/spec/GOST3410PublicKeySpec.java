package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410PublicKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f26930a;

    /* renamed from: p */
    private BigInteger f26931p;

    /* renamed from: q */
    private BigInteger f26932q;

    /* renamed from: y */
    private BigInteger f26933y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f26933y = bigInteger;
        this.f26931p = bigInteger2;
        this.f26932q = bigInteger3;
        this.f26930a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f26930a;
    }

    public BigInteger getP() {
        return this.f26931p;
    }

    public BigInteger getQ() {
        return this.f26932q;
    }

    public BigInteger getY() {
        return this.f26933y;
    }
}
