package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PKIXCertPathValidatorSpi_8 extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi_8() {
        this(false);
    }

    public PKIXCertPathValidatorSpi_8(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        if (x509Certificate instanceof BCX509Certificate) {
            RuntimeException runtimeException = null;
            try {
                if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() != null) {
                    return;
                }
            } catch (RuntimeException e) {
                runtimeException = e;
            }
            throw new AnnotatedException("unable to process TBSCertificate", runtimeException);
        }
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e2) {
            throw new AnnotatedException(e2.getMessage());
        } catch (CertificateEncodingException e3) {
            throw new AnnotatedException("unable to process TBSCertificate", e3);
        }
    }

    @Override // java.security.cert.CertPathValidatorSpi
    public PKIXCertPathChecker engineGetRevocationChecker() {
        return new ProvRevocationChecker(this.helper);
    }

    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        int i;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        boolean z;
        HashSet hashSet2;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (!(certPathParameters instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        } else {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Date validityDate = CertPathValidatorUtilities.getValidityDate(pKIXExtendedParameters, new Date());
        Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
        try {
            TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (findTrustAnchor == null) {
                i = 1;
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e) {
                    e = e;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - i);
                }
            }
            checkCertificate(findTrustAnchor.getTrustedCert());
            PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
            ArrayList arrayList2 = new ArrayList();
            PKIXCertRevocationChecker pKIXCertRevocationChecker = null;
            for (PKIXCertPathChecker pKIXCertPathChecker : build.getCertPathCheckers()) {
                pKIXCertPathChecker.init(false);
                if (!(pKIXCertPathChecker instanceof PKIXRevocationChecker)) {
                    arrayList2.add(pKIXCertPathChecker);
                } else if (pKIXCertRevocationChecker != null) {
                    throw new CertPathValidatorException("only one PKIXRevocationChecker allowed");
                } else {
                    pKIXCertRevocationChecker = pKIXCertPathChecker instanceof PKIXCertRevocationChecker ? (PKIXCertRevocationChecker) pKIXCertPathChecker : new WrappedRevocationChecker(pKIXCertPathChecker);
                }
            }
            PKIXCertRevocationChecker provRevocationChecker = (build.isRevocationEnabled() && pKIXCertRevocationChecker == null) ? new ProvRevocationChecker(this.helper) : pKIXCertRevocationChecker;
            int i5 = size + 1;
            ArrayList[] arrayListArr = new ArrayList[i5];
            for (int i6 = 0; i6 < arrayListArr.length; i6++) {
                arrayListArr[i6] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add("2.5.29.32.0");
            PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), "2.5.29.32.0", false);
            arrayListArr[0].add(pKIXPolicyNode);
            PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i7 = build.isExplicitPolicyRequired() ? 0 : i5;
            int i8 = build.isAnyPolicyInhibited() ? 0 : i5;
            if (build.isPolicyMappingInhibited()) {
                i5 = 0;
            }
            X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
            try {
                if (trustedCert != null) {
                    ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    ca = PrincipalUtils.getCA(findTrustAnchor);
                    cAPublicKey = findTrustAnchor.getCAPublicKey();
                }
                try {
                    AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                    algorithmIdentifier.getAlgorithm();
                    algorithmIdentifier.getParameters();
                    if (build.getTargetConstraints() == null || build.getTargetConstraints().match((Certificate) ((X509Certificate) certificates.get(0)))) {
                        boolean z2 = true;
                        int i9 = i5;
                        int i10 = size;
                        X509Certificate x509Certificate = null;
                        int i11 = i8;
                        int size2 = certificates.size() - 1;
                        int i12 = i7;
                        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                        while (size2 >= 0) {
                            int i13 = size - size2;
                            X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                            boolean z3 = size2 == certificates.size() + (-1) ? z2 : false;
                            try {
                                checkCertificate(x509Certificate2);
                                List<? extends Certificate> list2 = certificates;
                                int i14 = i12;
                                PKIXExtendedParameters pKIXExtendedParameters2 = build;
                                PKIXExtendedParameters pKIXExtendedParameters3 = build;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator2 = pKIXNameConstraintValidator;
                                int i15 = size2;
                                ArrayList[] arrayListArr2 = arrayListArr;
                                Date date = validityDate;
                                ArrayList arrayList3 = arrayList2;
                                boolean z4 = z3;
                                TrustAnchor trustAnchor = findTrustAnchor;
                                RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters2, validityDate, provRevocationChecker, i15, cAPublicKey, z4, ca, trustedCert);
                                RFC3280CertPathUtilities.processCertBC(certPath, i15, pKIXNameConstraintValidator2, this.isForCRLCheck);
                                PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, i15, RFC3280CertPathUtilities.processCertD(certPath, i15, hashSet4, pKIXPolicyNode2, arrayListArr2, i11, this.isForCRLCheck));
                                RFC3280CertPathUtilities.processCertF(certPath, i15, processCertE, i14);
                                if (i13 != size) {
                                    if (x509Certificate2 != null) {
                                        z = true;
                                        if (x509Certificate2.getVersion() == 1) {
                                            if (i13 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                                throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i15);
                                            }
                                            i2 = i11;
                                            i3 = i9;
                                            i4 = i10;
                                            arrayListArr = arrayListArr2;
                                            arrayList = arrayList3;
                                        }
                                    } else {
                                        z = true;
                                    }
                                    RFC3280CertPathUtilities.prepareNextCertA(certPath, i15);
                                    int i16 = i9;
                                    arrayListArr = arrayListArr2;
                                    PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i15, arrayListArr, processCertE, i16);
                                    RFC3280CertPathUtilities.prepareNextCertG(certPath, i15, pKIXNameConstraintValidator2);
                                    int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i15, i14);
                                    int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i15, i16);
                                    int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i15, i11);
                                    int prepareNextCertI1 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i15, prepareNextCertH1);
                                    int prepareNextCertI2 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i15, prepareNextCertH2);
                                    i2 = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i15, prepareNextCertH3);
                                    RFC3280CertPathUtilities.prepareNextCertK(certPath, i15);
                                    int prepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, i15, RFC3280CertPathUtilities.prepareNextCertL(certPath, i15, i10));
                                    RFC3280CertPathUtilities.prepareNextCertN(certPath, i15);
                                    Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        hashSet2 = new HashSet(criticalExtensionOIDs);
                                        hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                        hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                        hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                        hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                        hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                        hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    } else {
                                        hashSet2 = new HashSet();
                                    }
                                    arrayList = arrayList3;
                                    RFC3280CertPathUtilities.prepareNextCertO(certPath, i15, hashSet2, arrayList);
                                    ca = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                    try {
                                        cAPublicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i15, this.helper);
                                        AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                                        algorithmIdentifier2.getAlgorithm();
                                        algorithmIdentifier2.getParameters();
                                        pKIXPolicyNode2 = prepareCertB;
                                        i9 = prepareNextCertI2;
                                        i12 = prepareNextCertI1;
                                        i10 = prepareNextCertM;
                                        trustedCert = x509Certificate2;
                                        size2 = i15 - 1;
                                        i11 = i2;
                                        arrayList2 = arrayList;
                                        x509Certificate = x509Certificate2;
                                        findTrustAnchor = trustAnchor;
                                        certificates = list2;
                                        validityDate = date;
                                        z2 = z;
                                        pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                        build = pKIXExtendedParameters3;
                                    } catch (CertPathValidatorException e2) {
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath, i15);
                                    }
                                } else {
                                    i2 = i11;
                                    i3 = i9;
                                    i4 = i10;
                                    arrayListArr = arrayListArr2;
                                    arrayList = arrayList3;
                                    z = true;
                                }
                                pKIXPolicyNode2 = processCertE;
                                i9 = i3;
                                i10 = i4;
                                i12 = i14;
                                size2 = i15 - 1;
                                i11 = i2;
                                arrayList2 = arrayList;
                                x509Certificate = x509Certificate2;
                                findTrustAnchor = trustAnchor;
                                certificates = list2;
                                validityDate = date;
                                z2 = z;
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                build = pKIXExtendedParameters3;
                            } catch (AnnotatedException e3) {
                                throw new CertPathValidatorException(e3.getMessage(), e3.getUnderlyingException(), certPath, size2);
                            }
                        }
                        PKIXExtendedParameters pKIXExtendedParameters4 = build;
                        ArrayList arrayList4 = arrayList2;
                        TrustAnchor trustAnchor2 = findTrustAnchor;
                        int i17 = size2;
                        int i18 = i17 + 1;
                        int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i18, RFC3280CertPathUtilities.wrapupCertA(i12, x509Certificate));
                        Set<String> criticalExtensionOIDs2 = x509Certificate.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                            hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                            hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                            hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                            hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                            hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                            hashSet.remove(Extension.extendedKeyUsage.getId());
                        } else {
                            hashSet = new HashSet();
                        }
                        RFC3280CertPathUtilities.wrapupCertF(certPath, i18, arrayList4, hashSet);
                        X509Certificate x509Certificate3 = x509Certificate;
                        PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters4, initialPolicies, i18, arrayListArr, pKIXPolicyNode2, hashSet4);
                        if (wrapupCertB > 0 || wrapupCertG != null) {
                            return new PKIXCertPathValidatorResult(trustAnchor2, wrapupCertG, x509Certificate3.getPublicKey());
                        }
                        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i17);
                    }
                    throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                } catch (CertPathValidatorException e4) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e4, certPath, -1);
                }
            } catch (RuntimeException e5) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e5, certPath, -1);
            }
        } catch (AnnotatedException e6) {
            e = e6;
            i = 1;
            list = certificates;
        }
    }
}
