package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: pk */
    private CramerShoupPublicKeyParameters f26777pk;

    /* renamed from: x1 */
    private BigInteger f26778x1;

    /* renamed from: x2 */
    private BigInteger f26779x2;

    /* renamed from: y1 */
    private BigInteger f26780y1;

    /* renamed from: y2 */
    private BigInteger f26781y2;

    /* renamed from: z */
    private BigInteger f26782z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.f26778x1 = bigInteger;
        this.f26779x2 = bigInteger2;
        this.f26780y1 = bigInteger3;
        this.f26781y2 = bigInteger4;
        this.f26782z = bigInteger5;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPrivateKeyParameters) {
            CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
            return cramerShoupPrivateKeyParameters.getX1().equals(this.f26778x1) && cramerShoupPrivateKeyParameters.getX2().equals(this.f26779x2) && cramerShoupPrivateKeyParameters.getY1().equals(this.f26780y1) && cramerShoupPrivateKeyParameters.getY2().equals(this.f26781y2) && cramerShoupPrivateKeyParameters.getZ().equals(this.f26782z) && super.equals(obj);
        }
        return false;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f26777pk;
    }

    public BigInteger getX1() {
        return this.f26778x1;
    }

    public BigInteger getX2() {
        return this.f26779x2;
    }

    public BigInteger getY1() {
        return this.f26780y1;
    }

    public BigInteger getY2() {
        return this.f26781y2;
    }

    public BigInteger getZ() {
        return this.f26782z;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((((this.f26778x1.hashCode() ^ this.f26779x2.hashCode()) ^ this.f26780y1.hashCode()) ^ this.f26781y2.hashCode()) ^ this.f26782z.hashCode()) ^ super.hashCode();
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.f26777pk = cramerShoupPublicKeyParameters;
    }
}
