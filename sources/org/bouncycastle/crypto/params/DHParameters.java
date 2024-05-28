package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Properties;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;

    /* renamed from: g */
    private BigInteger f26786g;

    /* renamed from: j */
    private BigInteger f26787j;

    /* renamed from: l */
    private int f26788l;

    /* renamed from: m */
    private int f26789m;

    /* renamed from: p */
    private BigInteger f26790p;

    /* renamed from: q */
    private BigInteger f26791q;
    private DHValidationParameters validation;

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this(bigInteger, bigInteger2, bigInteger3, getDefaultMParam(i), i, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, i, i2, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        if (i2 != 0) {
            if (i2 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (i > bigInteger.bitLength() && !Properties.isOverrideSet("org.bouncycastle.dh.allow_unsafe_p_value")) {
            throw new IllegalArgumentException("unsafe p value so small specific l required");
        }
        this.f26786g = bigInteger2;
        this.f26790p = bigInteger;
        this.f26791q = bigInteger3;
        this.f26789m = i;
        this.f26788l = i2;
        this.f26787j = bigInteger4;
        this.validation = dHValidationParameters;
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        this(bigInteger, bigInteger2, bigInteger3, C0567f.f1819h, 0, bigInteger4, dHValidationParameters);
    }

    private static int getDefaultMParam(int i) {
        return (i != 0 && i < 160) ? i : C0567f.f1819h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DHParameters) {
            DHParameters dHParameters = (DHParameters) obj;
            if (getQ() != null) {
                if (!getQ().equals(dHParameters.getQ())) {
                    return false;
                }
            } else if (dHParameters.getQ() != null) {
                return false;
            }
            return dHParameters.getP().equals(this.f26790p) && dHParameters.getG().equals(this.f26786g);
        }
        return false;
    }

    public BigInteger getG() {
        return this.f26786g;
    }

    public BigInteger getJ() {
        return this.f26787j;
    }

    public int getL() {
        return this.f26788l;
    }

    public int getM() {
        return this.f26789m;
    }

    public BigInteger getP() {
        return this.f26790p;
    }

    public BigInteger getQ() {
        return this.f26791q;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) ^ (getQ() != null ? getQ().hashCode() : 0);
    }
}
