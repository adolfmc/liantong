package org.bouncycastle.pqc.crypto.falcon;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FalconKeyParameters extends AsymmetricKeyParameter {
    private final FalconParameters params;

    public FalconKeyParameters(boolean z, FalconParameters falconParameters) {
        super(z);
        this.params = falconParameters;
    }

    public FalconParameters getParameters() {
        return this.params;
    }
}
