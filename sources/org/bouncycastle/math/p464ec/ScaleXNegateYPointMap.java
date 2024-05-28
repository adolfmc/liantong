package org.bouncycastle.math.p464ec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.ScaleXNegateYPointMap */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ScaleXNegateYPointMap implements ECPointMap {
    protected final ECFieldElement scale;

    public ScaleXNegateYPointMap(ECFieldElement eCFieldElement) {
        this.scale = eCFieldElement;
    }

    @Override // org.bouncycastle.math.p464ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleXNegateY(this.scale);
    }
}
