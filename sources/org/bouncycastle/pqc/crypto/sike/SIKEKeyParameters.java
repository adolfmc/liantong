package org.bouncycastle.pqc.crypto.sike;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SIKEKeyParameters extends AsymmetricKeyParameter {
    private SIKEParameters params;

    public SIKEKeyParameters(boolean z, SIKEParameters sIKEParameters) {
        super(z);
        this.params = sIKEParameters;
    }

    public SIKEParameters getParameters() {
        return this.params;
    }
}
