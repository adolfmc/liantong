package org.bouncycastle.x509;

import java.security.cert.CertificateEncodingException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class ExtCertificateEncodingException extends CertificateEncodingException {
    Throwable cause;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
