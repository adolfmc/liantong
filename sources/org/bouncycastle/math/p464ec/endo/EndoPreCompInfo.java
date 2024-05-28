package org.bouncycastle.math.p464ec.endo;

import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.p464ec.PreCompInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.endo.EndoPreCompInfo */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EndoPreCompInfo implements PreCompInfo {
    protected ECEndomorphism endomorphism;
    protected ECPoint mappedPoint;

    public ECEndomorphism getEndomorphism() {
        return this.endomorphism;
    }

    public ECPoint getMappedPoint() {
        return this.mappedPoint;
    }

    public void setEndomorphism(ECEndomorphism eCEndomorphism) {
        this.endomorphism = eCEndomorphism;
    }

    public void setMappedPoint(ECPoint eCPoint) {
        this.mappedPoint = eCPoint;
    }
}
