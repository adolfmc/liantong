package com.bytedance.pangle.p180g;

import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.g.r */
/* loaded from: E:\10762272_dexfile_execute.dex */
class C3863r extends X509Certificate {

    /* renamed from: a */
    private final X509Certificate f9214a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3863r(X509Certificate x509Certificate) {
        this.f9214a = x509Certificate;
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getCriticalExtensionOIDs() {
        return this.f9214a.getCriticalExtensionOIDs();
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        return this.f9214a.getExtensionValue(str);
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getNonCriticalExtensionOIDs() {
        return this.f9214a.getNonCriticalExtensionOIDs();
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        return this.f9214a.hasUnsupportedCriticalExtension();
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity() {
        this.f9214a.checkValidity();
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity(Date date) {
        this.f9214a.checkValidity(date);
    }

    @Override // java.security.cert.X509Certificate
    public int getVersion() {
        return this.f9214a.getVersion();
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        return this.f9214a.getSerialNumber();
    }

    @Override // java.security.cert.X509Certificate
    public Principal getIssuerDN() {
        return this.f9214a.getIssuerDN();
    }

    @Override // java.security.cert.X509Certificate
    public Principal getSubjectDN() {
        return this.f9214a.getSubjectDN();
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotBefore() {
        return this.f9214a.getNotBefore();
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotAfter() {
        return this.f9214a.getNotAfter();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() {
        return this.f9214a.getTBSCertificate();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSignature() {
        return this.f9214a.getSignature();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgName() {
        return this.f9214a.getSigAlgName();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgOID() {
        return this.f9214a.getSigAlgOID();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        return this.f9214a.getSigAlgParams();
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getIssuerUniqueID() {
        return this.f9214a.getIssuerUniqueID();
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getSubjectUniqueID() {
        return this.f9214a.getSubjectUniqueID();
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        return this.f9214a.getKeyUsage();
    }

    @Override // java.security.cert.X509Certificate
    public int getBasicConstraints() {
        return this.f9214a.getBasicConstraints();
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() {
        return this.f9214a.getEncoded();
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey) {
        this.f9214a.verify(publicKey);
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey, String str) {
        this.f9214a.verify(publicKey, str);
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        return this.f9214a.toString();
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        return this.f9214a.getPublicKey();
    }
}
