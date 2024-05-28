package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KyberKeyParameters extends AsymmetricKeyParameter {
    private KyberParameters params;

    public KyberKeyParameters(boolean z, KyberParameters kyberParameters) {
        super(z);
        this.params = kyberParameters;
    }

    public KyberParameters getParameters() {
        return this.params;
    }
}
