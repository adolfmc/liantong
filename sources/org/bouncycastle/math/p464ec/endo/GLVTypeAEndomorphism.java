package org.bouncycastle.math.p464ec.endo;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECPointMap;
import org.bouncycastle.math.p464ec.ScaleYNegateXPointMap;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.endo.GLVTypeAEndomorphism */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GLVTypeAEndomorphism implements GLVEndomorphism {
    protected final GLVTypeAParameters parameters;
    protected final ECPointMap pointMap;

    public GLVTypeAEndomorphism(ECCurve eCCurve, GLVTypeAParameters gLVTypeAParameters) {
        this.parameters = gLVTypeAParameters;
        this.pointMap = new ScaleYNegateXPointMap(eCCurve.fromBigInteger(gLVTypeAParameters.getI()));
    }

    @Override // org.bouncycastle.math.p464ec.endo.GLVEndomorphism
    public BigInteger[] decomposeScalar(BigInteger bigInteger) {
        return EndoUtil.decomposeScalar(this.parameters.getSplitParams(), bigInteger);
    }

    @Override // org.bouncycastle.math.p464ec.endo.ECEndomorphism
    public ECPointMap getPointMap() {
        return this.pointMap;
    }

    @Override // org.bouncycastle.math.p464ec.endo.ECEndomorphism
    public boolean hasEfficientPointMap() {
        return true;
    }
}
