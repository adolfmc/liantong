package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DualECPoints {
    private final int cofactor;

    /* renamed from: p */
    private final ECPoint f26846p;

    /* renamed from: q */
    private final ECPoint f26847q;
    private final int securityStrength;

    public DualECPoints(int i, ECPoint eCPoint, ECPoint eCPoint2, int i2) {
        if (!eCPoint.getCurve().equals(eCPoint2.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.securityStrength = i;
        this.f26846p = eCPoint;
        this.f26847q = eCPoint2;
        this.cofactor = i2;
    }

    private static int log2(int i) {
        int i2 = 0;
        while (true) {
            i >>= 1;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public int getCofactor() {
        return this.cofactor;
    }

    public int getMaxOutlen() {
        return ((this.f26846p.getCurve().getFieldSize() - (log2(this.cofactor) + 13)) / 8) * 8;
    }

    public ECPoint getP() {
        return this.f26846p;
    }

    public ECPoint getQ() {
        return this.f26847q;
    }

    public int getSecurityStrength() {
        return this.securityStrength;
    }

    public int getSeedLen() {
        return this.f26846p.getCurve().getFieldSize();
    }
}
