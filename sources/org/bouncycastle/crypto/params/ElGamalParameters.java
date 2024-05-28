package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ElGamalParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f26806g;

    /* renamed from: l */
    private int f26807l;

    /* renamed from: p */
    private BigInteger f26808p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f26806g = bigInteger2;
        this.f26808p = bigInteger;
        this.f26807l = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ElGamalParameters) {
            ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
            return elGamalParameters.getP().equals(this.f26808p) && elGamalParameters.getG().equals(this.f26806g) && elGamalParameters.getL() == this.f26807l;
        }
        return false;
    }

    public BigInteger getG() {
        return this.f26806g;
    }

    public int getL() {
        return this.f26807l;
    }

    public BigInteger getP() {
        return this.f26808p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f26807l;
    }
}
