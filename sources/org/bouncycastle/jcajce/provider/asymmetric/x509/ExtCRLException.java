package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.security.cert.CRLException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class ExtCRLException extends CRLException {
    Throwable cause;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtCRLException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
