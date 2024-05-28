package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    /* renamed from: dP */
    private BigInteger f26831dP;

    /* renamed from: dQ */
    private BigInteger f26832dQ;

    /* renamed from: e */
    private BigInteger f26833e;

    /* renamed from: p */
    private BigInteger f26834p;

    /* renamed from: q */
    private BigInteger f26835q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this(bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5, bigInteger6, bigInteger7, bigInteger8, false);
    }

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8, boolean z) {
        super(true, bigInteger, bigInteger3, z);
        this.f26833e = bigInteger2;
        this.f26834p = bigInteger4;
        this.f26835q = bigInteger5;
        this.f26831dP = bigInteger6;
        this.f26832dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.f26831dP;
    }

    public BigInteger getDQ() {
        return this.f26832dQ;
    }

    public BigInteger getP() {
        return this.f26834p;
    }

    public BigInteger getPublicExponent() {
        return this.f26833e;
    }

    public BigInteger getQ() {
        return this.f26835q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
