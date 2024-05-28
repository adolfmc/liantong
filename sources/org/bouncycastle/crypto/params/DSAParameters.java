package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DSAParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f26796g;

    /* renamed from: p */
    private BigInteger f26797p;

    /* renamed from: q */
    private BigInteger f26798q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f26796g = bigInteger3;
        this.f26797p = bigInteger;
        this.f26798q = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f26796g = bigInteger3;
        this.f26797p = bigInteger;
        this.f26798q = bigInteger2;
        this.validation = dSAValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAParameters) {
            DSAParameters dSAParameters = (DSAParameters) obj;
            return dSAParameters.getP().equals(this.f26797p) && dSAParameters.getQ().equals(this.f26798q) && dSAParameters.getG().equals(this.f26796g);
        }
        return false;
    }

    public BigInteger getG() {
        return this.f26796g;
    }

    public BigInteger getP() {
        return this.f26797p;
    }

    public BigInteger getQ() {
        return this.f26798q;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
