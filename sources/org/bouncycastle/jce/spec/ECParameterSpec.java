package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: G */
    private ECPoint f26914G;
    private ECCurve curve;

    /* renamed from: h */
    private BigInteger f26915h;

    /* renamed from: n */
    private BigInteger f26916n;
    private byte[] seed;

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this.curve = eCCurve;
        this.f26914G = eCPoint.normalize();
        this.f26916n = bigInteger;
        this.f26915h = BigInteger.valueOf(1L);
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this.curve = eCCurve;
        this.f26914G = eCPoint.normalize();
        this.f26916n = bigInteger;
        this.f26915h = bigInteger2;
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f26914G = eCPoint.normalize();
        this.f26916n = bigInteger;
        this.f26915h = bigInteger2;
        this.seed = bArr;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECParameterSpec) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
            return getCurve().equals(eCParameterSpec.getCurve()) && getG().equals(eCParameterSpec.getG());
        }
        return false;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f26914G;
    }

    public BigInteger getH() {
        return this.f26915h;
    }

    public BigInteger getN() {
        return this.f26916n;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public int hashCode() {
        return getCurve().hashCode() ^ getG().hashCode();
    }
}
