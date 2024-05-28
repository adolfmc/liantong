package org.bouncycastle.crypto.params;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECKeyParameters extends AsymmetricKeyParameter {
    private final ECDomainParameters parameters;

    /* JADX INFO: Access modifiers changed from: protected */
    public ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        if (eCDomainParameters == null) {
            throw new NullPointerException("'parameters' cannot be null");
        }
        this.parameters = eCDomainParameters;
    }

    public ECDomainParameters getParameters() {
        return this.parameters;
    }
}
