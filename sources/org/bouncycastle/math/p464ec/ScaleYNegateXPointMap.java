package org.bouncycastle.math.p464ec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.ScaleYNegateXPointMap */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ScaleYNegateXPointMap implements ECPointMap {
    protected final ECFieldElement scale;

    public ScaleYNegateXPointMap(ECFieldElement eCFieldElement) {
        this.scale = eCFieldElement;
    }

    @Override // org.bouncycastle.math.p464ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleYNegateX(this.scale);
    }
}
