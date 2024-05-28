package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Memoable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CramerShoupParameters implements CipherParameters {

    /* renamed from: H */
    private Digest f26773H;

    /* renamed from: g1 */
    private BigInteger f26774g1;

    /* renamed from: g2 */
    private BigInteger f26775g2;

    /* renamed from: p */
    private BigInteger f26776p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f26776p = bigInteger;
        this.f26774g1 = bigInteger2;
        this.f26775g2 = bigInteger3;
        this.f26773H = (Digest) ((Memoable) digest).copy();
        this.f26773H.reset();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupParameters) {
            CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
            return cramerShoupParameters.getP().equals(this.f26776p) && cramerShoupParameters.getG1().equals(this.f26774g1) && cramerShoupParameters.getG2().equals(this.f26775g2);
        }
        return false;
    }

    public BigInteger getG1() {
        return this.f26774g1;
    }

    public BigInteger getG2() {
        return this.f26775g2;
    }

    public Digest getH() {
        return (Digest) ((Memoable) this.f26773H).copy();
    }

    public BigInteger getP() {
        return this.f26776p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }
}
