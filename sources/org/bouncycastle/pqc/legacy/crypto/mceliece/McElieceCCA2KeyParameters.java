package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class McElieceCCA2KeyParameters extends AsymmetricKeyParameter {
    private String params;

    public McElieceCCA2KeyParameters(boolean z, String str) {
        super(z);
        this.params = str;
    }

    public String getDigest() {
        return this.params;
    }
}
