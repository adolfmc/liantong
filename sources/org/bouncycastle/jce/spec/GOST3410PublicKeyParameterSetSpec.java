package org.bouncycastle.jce.spec;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a */
    private BigInteger f26927a;

    /* renamed from: p */
    private BigInteger f26928p;

    /* renamed from: q */
    private BigInteger f26929q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f26928p = bigInteger;
        this.f26929q = bigInteger2;
        this.f26927a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410PublicKeyParameterSetSpec) {
            GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
            return this.f26927a.equals(gOST3410PublicKeyParameterSetSpec.f26927a) && this.f26928p.equals(gOST3410PublicKeyParameterSetSpec.f26928p) && this.f26929q.equals(gOST3410PublicKeyParameterSetSpec.f26929q);
        }
        return false;
    }

    public BigInteger getA() {
        return this.f26927a;
    }

    public BigInteger getP() {
        return this.f26928p;
    }

    public BigInteger getQ() {
        return this.f26929q;
    }

    public int hashCode() {
        return (this.f26927a.hashCode() ^ this.f26928p.hashCode()) ^ this.f26929q.hashCode();
    }
}
