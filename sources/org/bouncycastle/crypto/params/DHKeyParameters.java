package org.bouncycastle.crypto.params;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DHKeyParameters extends AsymmetricKeyParameter {
    private DHParameters params;

    /* JADX INFO: Access modifiers changed from: protected */
    public DHKeyParameters(boolean z, DHParameters dHParameters) {
        super(z);
        this.params = dHParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DHKeyParameters) {
            DHKeyParameters dHKeyParameters = (DHKeyParameters) obj;
            DHParameters dHParameters = this.params;
            return dHParameters == null ? dHKeyParameters.getParameters() == null : dHParameters.equals(dHKeyParameters.getParameters());
        }
        return false;
    }

    public DHParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        int i = !isPrivate();
        DHParameters dHParameters = this.params;
        return dHParameters != null ? i ^ dHParameters.hashCode() : i;
    }
}
