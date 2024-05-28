package org.bouncycastle.math.p464ec.endo;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.endo.GLVTypeBParameters */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GLVTypeBParameters {
    protected final BigInteger beta;
    protected final BigInteger lambda;
    protected final ScalarSplitParameters splitParams;

    public GLVTypeBParameters(BigInteger bigInteger, BigInteger bigInteger2, ScalarSplitParameters scalarSplitParameters) {
        this.beta = bigInteger;
        this.lambda = bigInteger2;
        this.splitParams = scalarSplitParameters;
    }

    public BigInteger getBeta() {
        return this.beta;
    }

    public BigInteger getLambda() {
        return this.lambda;
    }

    public ScalarSplitParameters getSplitParams() {
        return this.splitParams;
    }
}
