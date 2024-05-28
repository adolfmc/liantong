package org.bouncycastle.jce.spec;

import java.security.spec.KeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECKeySpec implements KeySpec {
    private ECParameterSpec spec;

    /* JADX INFO: Access modifiers changed from: protected */
    public ECKeySpec(ECParameterSpec eCParameterSpec) {
        this.spec = eCParameterSpec;
    }

    public ECParameterSpec getParams() {
        return this.spec;
    }
}
