package org.bouncycastle.jcajce;

import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface PKIXCertRevocationChecker {
    void check(Certificate certificate) throws CertPathValidatorException;

    void initialize(PKIXCertRevocationCheckerParameters pKIXCertRevocationCheckerParameters) throws CertPathValidatorException;

    void setParameter(String str, Object obj);
}
