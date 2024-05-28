package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class NTRUKeyParameters extends AsymmetricKeyParameter {
    private final NTRUParameters params;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NTRUKeyParameters(boolean z, NTRUParameters nTRUParameters) {
        super(z);
        this.params = nTRUParameters;
    }

    public NTRUParameters getParameters() {
        return this.params;
    }
}
