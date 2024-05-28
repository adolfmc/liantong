package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f26923a;

    /* renamed from: p */
    private BigInteger f26924p;

    /* renamed from: q */
    private BigInteger f26925q;

    /* renamed from: x */
    private BigInteger f26926x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f26926x = bigInteger;
        this.f26924p = bigInteger2;
        this.f26925q = bigInteger3;
        this.f26923a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f26923a;
    }

    public BigInteger getP() {
        return this.f26924p;
    }

    public BigInteger getQ() {
        return this.f26925q;
    }

    public BigInteger getX() {
        return this.f26926x;
    }
}
