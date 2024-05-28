package org.bouncycastle.crypto.params;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CramerShoupKeyParameters extends AsymmetricKeyParameter {
    private CramerShoupParameters params;

    /* JADX INFO: Access modifiers changed from: protected */
    public CramerShoupKeyParameters(boolean z, CramerShoupParameters cramerShoupParameters) {
        super(z);
        this.params = cramerShoupParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupKeyParameters) {
            CramerShoupKeyParameters cramerShoupKeyParameters = (CramerShoupKeyParameters) obj;
            CramerShoupParameters cramerShoupParameters = this.params;
            return cramerShoupParameters == null ? cramerShoupKeyParameters.getParameters() == null : cramerShoupParameters.equals(cramerShoupKeyParameters.getParameters());
        }
        return false;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        int i = !isPrivate();
        CramerShoupParameters cramerShoupParameters = this.params;
        return cramerShoupParameters != null ? i ^ cramerShoupParameters.hashCode() : i;
    }
}
