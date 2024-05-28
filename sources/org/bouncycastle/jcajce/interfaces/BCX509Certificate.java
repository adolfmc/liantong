package org.bouncycastle.jcajce.interfaces;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.TBSCertificate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface BCX509Certificate {
    X500Name getIssuerX500Name();

    X500Name getSubjectX500Name();

    TBSCertificate getTBSCertificateNative();
}
