package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a */
    private BigInteger f26811a;

    /* renamed from: p */
    private BigInteger f26812p;

    /* renamed from: q */
    private BigInteger f26813q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f26812p = bigInteger;
        this.f26813q = bigInteger2;
        this.f26811a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f26811a = bigInteger3;
        this.f26812p = bigInteger;
        this.f26813q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410Parameters) {
            GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
            return gOST3410Parameters.getP().equals(this.f26812p) && gOST3410Parameters.getQ().equals(this.f26813q) && gOST3410Parameters.getA().equals(this.f26811a);
        }
        return false;
    }

    public BigInteger getA() {
        return this.f26811a;
    }

    public BigInteger getP() {
        return this.f26812p;
    }

    public BigInteger getQ() {
        return this.f26813q;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f26812p.hashCode() ^ this.f26813q.hashCode()) ^ this.f26811a.hashCode();
    }
}
