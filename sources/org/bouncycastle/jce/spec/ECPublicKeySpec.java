package org.bouncycastle.jce.spec;

import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECPublicKeySpec extends ECKeySpec {

    /* renamed from: q */
    private ECPoint f26918q;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f26918q = eCPoint.getCurve() != null ? eCPoint.normalize() : eCPoint;
    }

    public ECPoint getQ() {
        return this.f26918q;
    }
}
