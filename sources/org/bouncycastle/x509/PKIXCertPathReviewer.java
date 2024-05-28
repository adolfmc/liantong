package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.util.Integers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected Date currentDate;
    protected List[] errors;
    private boolean initialized;

    /* renamed from: n */
    protected int f27379n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
            try {
                try {
                    pKIXCertPathChecker.init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                if (size == 0) {
                    criticalExtensionOIDs.remove(Extension.extendedKeyUsage.getId());
                }
                if (criticalExtensionOIDs.contains(QC_STATEMENT) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(QC_STATEMENT);
                }
                for (PKIXCertPathChecker pKIXCertPathChecker2 : certPathCheckers) {
                    try {
                        pKIXCertPathChecker2.check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it = criticalExtensionOIDs.iterator();
                    while (it.hasNext()) {
                        addError(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                int i = this.f27379n;
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i2));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i3 = 0; i3 != excludedSubtrees.length; i3++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i3]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.f27379n;
        int i2 = i;
        int i3 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            int i4 = this.f27379n;
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i2 <= 0) {
                    addError(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.pathLengthExtended"));
                }
                i2--;
                i3++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i2) {
                i2 = intValue;
            }
        }
        addNotification(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i3)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0236 A[Catch: CertPathReviewerException -> 0x05e8, TryCatch #3 {CertPathReviewerException -> 0x05e8, blocks: (B:18:0x0068, B:20:0x0076, B:21:0x0082, B:24:0x0090, B:25:0x0099, B:27:0x009f, B:29:0x00be, B:30:0x00c6, B:32:0x00cc, B:34:0x00d1, B:35:0x00e1, B:38:0x00e7, B:41:0x00f0, B:42:0x00f9, B:44:0x00ff, B:46:0x0109, B:49:0x0110, B:51:0x0114, B:91:0x0208, B:93:0x020c, B:94:0x0210, B:96:0x0216, B:98:0x0222, B:101:0x0229, B:102:0x022c, B:103:0x0230, B:105:0x0236, B:106:0x023f, B:108:0x0245, B:116:0x0260, B:117:0x026e, B:118:0x026f, B:120:0x0273, B:122:0x027d, B:123:0x0281, B:125:0x0287, B:127:0x02a9, B:129:0x02b5, B:130:0x02ba, B:131:0x02ca, B:132:0x02cb, B:133:0x02db, B:135:0x02de, B:136:0x02eb, B:138:0x02f1, B:140:0x0317, B:142:0x032f, B:141:0x0326, B:143:0x0336, B:144:0x033c, B:146:0x0342, B:148:0x034a, B:158:0x0376, B:152:0x0350, B:153:0x0360, B:155:0x0362, B:156:0x0373, B:160:0x037d, B:168:0x038f, B:170:0x0399, B:171:0x039d, B:173:0x03a3, B:174:0x03ad, B:176:0x03b1, B:179:0x03be, B:182:0x03cb, B:184:0x03d5, B:197:0x0417, B:189:0x03df, B:190:0x03ef, B:191:0x03f0, B:192:0x0400, B:194:0x0402, B:195:0x0412, B:55:0x0121, B:56:0x0125, B:58:0x012b, B:60:0x0143, B:62:0x014d, B:63:0x0150, B:65:0x0156, B:66:0x0165, B:68:0x016b, B:70:0x0175, B:74:0x0183, B:75:0x0189, B:77:0x018f, B:83:0x01aa, B:71:0x0179, B:73:0x017d, B:87:0x01ec, B:89:0x01f7, B:90:0x0207, B:199:0x0422, B:200:0x0432, B:201:0x0433, B:205:0x043d, B:207:0x0447, B:208:0x044c, B:210:0x0452, B:213:0x0460, B:219:0x0473, B:298:0x05c8, B:299:0x05d6, B:222:0x047e, B:223:0x048e, B:224:0x048f, B:226:0x0495, B:228:0x049d, B:230:0x04a3, B:231:0x04a9, B:233:0x04ac, B:234:0x04af, B:236:0x04b5, B:238:0x04c7, B:239:0x04cb, B:241:0x04d1, B:242:0x04d9, B:243:0x04dc, B:244:0x04df, B:245:0x04e3, B:247:0x04e9, B:249:0x04f9, B:251:0x04ff, B:252:0x0502, B:254:0x0508, B:256:0x0514, B:257:0x0518, B:258:0x051b, B:259:0x051e, B:260:0x052e, B:262:0x0532, B:263:0x0538, B:265:0x053b, B:266:0x053e, B:268:0x0544, B:270:0x0556, B:271:0x055a, B:273:0x0560, B:275:0x0572, B:276:0x0576, B:277:0x0579, B:278:0x057c, B:279:0x0580, B:281:0x0586, B:283:0x0596, B:285:0x059e, B:287:0x05a4, B:288:0x05a7, B:290:0x05ad, B:292:0x05b9, B:293:0x05bd, B:294:0x05c0, B:300:0x05d7, B:301:0x05e7), top: B:309:0x0068, inners: #1, #2, #5, #6, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:367:0x011b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0110 A[Catch: CertPathReviewerException -> 0x05e8, TryCatch #3 {CertPathReviewerException -> 0x05e8, blocks: (B:18:0x0068, B:20:0x0076, B:21:0x0082, B:24:0x0090, B:25:0x0099, B:27:0x009f, B:29:0x00be, B:30:0x00c6, B:32:0x00cc, B:34:0x00d1, B:35:0x00e1, B:38:0x00e7, B:41:0x00f0, B:42:0x00f9, B:44:0x00ff, B:46:0x0109, B:49:0x0110, B:51:0x0114, B:91:0x0208, B:93:0x020c, B:94:0x0210, B:96:0x0216, B:98:0x0222, B:101:0x0229, B:102:0x022c, B:103:0x0230, B:105:0x0236, B:106:0x023f, B:108:0x0245, B:116:0x0260, B:117:0x026e, B:118:0x026f, B:120:0x0273, B:122:0x027d, B:123:0x0281, B:125:0x0287, B:127:0x02a9, B:129:0x02b5, B:130:0x02ba, B:131:0x02ca, B:132:0x02cb, B:133:0x02db, B:135:0x02de, B:136:0x02eb, B:138:0x02f1, B:140:0x0317, B:142:0x032f, B:141:0x0326, B:143:0x0336, B:144:0x033c, B:146:0x0342, B:148:0x034a, B:158:0x0376, B:152:0x0350, B:153:0x0360, B:155:0x0362, B:156:0x0373, B:160:0x037d, B:168:0x038f, B:170:0x0399, B:171:0x039d, B:173:0x03a3, B:174:0x03ad, B:176:0x03b1, B:179:0x03be, B:182:0x03cb, B:184:0x03d5, B:197:0x0417, B:189:0x03df, B:190:0x03ef, B:191:0x03f0, B:192:0x0400, B:194:0x0402, B:195:0x0412, B:55:0x0121, B:56:0x0125, B:58:0x012b, B:60:0x0143, B:62:0x014d, B:63:0x0150, B:65:0x0156, B:66:0x0165, B:68:0x016b, B:70:0x0175, B:74:0x0183, B:75:0x0189, B:77:0x018f, B:83:0x01aa, B:71:0x0179, B:73:0x017d, B:87:0x01ec, B:89:0x01f7, B:90:0x0207, B:199:0x0422, B:200:0x0432, B:201:0x0433, B:205:0x043d, B:207:0x0447, B:208:0x044c, B:210:0x0452, B:213:0x0460, B:219:0x0473, B:298:0x05c8, B:299:0x05d6, B:222:0x047e, B:223:0x048e, B:224:0x048f, B:226:0x0495, B:228:0x049d, B:230:0x04a3, B:231:0x04a9, B:233:0x04ac, B:234:0x04af, B:236:0x04b5, B:238:0x04c7, B:239:0x04cb, B:241:0x04d1, B:242:0x04d9, B:243:0x04dc, B:244:0x04df, B:245:0x04e3, B:247:0x04e9, B:249:0x04f9, B:251:0x04ff, B:252:0x0502, B:254:0x0508, B:256:0x0514, B:257:0x0518, B:258:0x051b, B:259:0x051e, B:260:0x052e, B:262:0x0532, B:263:0x0538, B:265:0x053b, B:266:0x053e, B:268:0x0544, B:270:0x0556, B:271:0x055a, B:273:0x0560, B:275:0x0572, B:276:0x0576, B:277:0x0579, B:278:0x057c, B:279:0x0580, B:281:0x0586, B:283:0x0596, B:285:0x059e, B:287:0x05a4, B:288:0x05a7, B:290:0x05ad, B:292:0x05b9, B:293:0x05bd, B:294:0x05c0, B:300:0x05d7, B:301:0x05e7), top: B:309:0x0068, inners: #1, #2, #5, #6, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012b A[Catch: CertPathReviewerException -> 0x05e8, TRY_LEAVE, TryCatch #3 {CertPathReviewerException -> 0x05e8, blocks: (B:18:0x0068, B:20:0x0076, B:21:0x0082, B:24:0x0090, B:25:0x0099, B:27:0x009f, B:29:0x00be, B:30:0x00c6, B:32:0x00cc, B:34:0x00d1, B:35:0x00e1, B:38:0x00e7, B:41:0x00f0, B:42:0x00f9, B:44:0x00ff, B:46:0x0109, B:49:0x0110, B:51:0x0114, B:91:0x0208, B:93:0x020c, B:94:0x0210, B:96:0x0216, B:98:0x0222, B:101:0x0229, B:102:0x022c, B:103:0x0230, B:105:0x0236, B:106:0x023f, B:108:0x0245, B:116:0x0260, B:117:0x026e, B:118:0x026f, B:120:0x0273, B:122:0x027d, B:123:0x0281, B:125:0x0287, B:127:0x02a9, B:129:0x02b5, B:130:0x02ba, B:131:0x02ca, B:132:0x02cb, B:133:0x02db, B:135:0x02de, B:136:0x02eb, B:138:0x02f1, B:140:0x0317, B:142:0x032f, B:141:0x0326, B:143:0x0336, B:144:0x033c, B:146:0x0342, B:148:0x034a, B:158:0x0376, B:152:0x0350, B:153:0x0360, B:155:0x0362, B:156:0x0373, B:160:0x037d, B:168:0x038f, B:170:0x0399, B:171:0x039d, B:173:0x03a3, B:174:0x03ad, B:176:0x03b1, B:179:0x03be, B:182:0x03cb, B:184:0x03d5, B:197:0x0417, B:189:0x03df, B:190:0x03ef, B:191:0x03f0, B:192:0x0400, B:194:0x0402, B:195:0x0412, B:55:0x0121, B:56:0x0125, B:58:0x012b, B:60:0x0143, B:62:0x014d, B:63:0x0150, B:65:0x0156, B:66:0x0165, B:68:0x016b, B:70:0x0175, B:74:0x0183, B:75:0x0189, B:77:0x018f, B:83:0x01aa, B:71:0x0179, B:73:0x017d, B:87:0x01ec, B:89:0x01f7, B:90:0x0207, B:199:0x0422, B:200:0x0432, B:201:0x0433, B:205:0x043d, B:207:0x0447, B:208:0x044c, B:210:0x0452, B:213:0x0460, B:219:0x0473, B:298:0x05c8, B:299:0x05d6, B:222:0x047e, B:223:0x048e, B:224:0x048f, B:226:0x0495, B:228:0x049d, B:230:0x04a3, B:231:0x04a9, B:233:0x04ac, B:234:0x04af, B:236:0x04b5, B:238:0x04c7, B:239:0x04cb, B:241:0x04d1, B:242:0x04d9, B:243:0x04dc, B:244:0x04df, B:245:0x04e3, B:247:0x04e9, B:249:0x04f9, B:251:0x04ff, B:252:0x0502, B:254:0x0508, B:256:0x0514, B:257:0x0518, B:258:0x051b, B:259:0x051e, B:260:0x052e, B:262:0x0532, B:263:0x0538, B:265:0x053b, B:266:0x053e, B:268:0x0544, B:270:0x0556, B:271:0x055a, B:273:0x0560, B:275:0x0572, B:276:0x0576, B:277:0x0579, B:278:0x057c, B:279:0x0580, B:281:0x0586, B:283:0x0596, B:285:0x059e, B:287:0x05a4, B:288:0x05a7, B:290:0x05ad, B:292:0x05b9, B:293:0x05bd, B:294:0x05c0, B:300:0x05d7, B:301:0x05e7), top: B:309:0x0068, inners: #1, #2, #5, #6, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x020c A[Catch: CertPathReviewerException -> 0x05e8, TryCatch #3 {CertPathReviewerException -> 0x05e8, blocks: (B:18:0x0068, B:20:0x0076, B:21:0x0082, B:24:0x0090, B:25:0x0099, B:27:0x009f, B:29:0x00be, B:30:0x00c6, B:32:0x00cc, B:34:0x00d1, B:35:0x00e1, B:38:0x00e7, B:41:0x00f0, B:42:0x00f9, B:44:0x00ff, B:46:0x0109, B:49:0x0110, B:51:0x0114, B:91:0x0208, B:93:0x020c, B:94:0x0210, B:96:0x0216, B:98:0x0222, B:101:0x0229, B:102:0x022c, B:103:0x0230, B:105:0x0236, B:106:0x023f, B:108:0x0245, B:116:0x0260, B:117:0x026e, B:118:0x026f, B:120:0x0273, B:122:0x027d, B:123:0x0281, B:125:0x0287, B:127:0x02a9, B:129:0x02b5, B:130:0x02ba, B:131:0x02ca, B:132:0x02cb, B:133:0x02db, B:135:0x02de, B:136:0x02eb, B:138:0x02f1, B:140:0x0317, B:142:0x032f, B:141:0x0326, B:143:0x0336, B:144:0x033c, B:146:0x0342, B:148:0x034a, B:158:0x0376, B:152:0x0350, B:153:0x0360, B:155:0x0362, B:156:0x0373, B:160:0x037d, B:168:0x038f, B:170:0x0399, B:171:0x039d, B:173:0x03a3, B:174:0x03ad, B:176:0x03b1, B:179:0x03be, B:182:0x03cb, B:184:0x03d5, B:197:0x0417, B:189:0x03df, B:190:0x03ef, B:191:0x03f0, B:192:0x0400, B:194:0x0402, B:195:0x0412, B:55:0x0121, B:56:0x0125, B:58:0x012b, B:60:0x0143, B:62:0x014d, B:63:0x0150, B:65:0x0156, B:66:0x0165, B:68:0x016b, B:70:0x0175, B:74:0x0183, B:75:0x0189, B:77:0x018f, B:83:0x01aa, B:71:0x0179, B:73:0x017d, B:87:0x01ec, B:89:0x01f7, B:90:0x0207, B:199:0x0422, B:200:0x0432, B:201:0x0433, B:205:0x043d, B:207:0x0447, B:208:0x044c, B:210:0x0452, B:213:0x0460, B:219:0x0473, B:298:0x05c8, B:299:0x05d6, B:222:0x047e, B:223:0x048e, B:224:0x048f, B:226:0x0495, B:228:0x049d, B:230:0x04a3, B:231:0x04a9, B:233:0x04ac, B:234:0x04af, B:236:0x04b5, B:238:0x04c7, B:239:0x04cb, B:241:0x04d1, B:242:0x04d9, B:243:0x04dc, B:244:0x04df, B:245:0x04e3, B:247:0x04e9, B:249:0x04f9, B:251:0x04ff, B:252:0x0502, B:254:0x0508, B:256:0x0514, B:257:0x0518, B:258:0x051b, B:259:0x051e, B:260:0x052e, B:262:0x0532, B:263:0x0538, B:265:0x053b, B:266:0x053e, B:268:0x0544, B:270:0x0556, B:271:0x055a, B:273:0x0560, B:275:0x0572, B:276:0x0576, B:277:0x0579, B:278:0x057c, B:279:0x0580, B:281:0x0586, B:283:0x0596, B:285:0x059e, B:287:0x05a4, B:288:0x05a7, B:290:0x05ad, B:292:0x05b9, B:293:0x05bd, B:294:0x05c0, B:300:0x05d7, B:301:0x05e7), top: B:309:0x0068, inners: #1, #2, #5, #6, #7, #8, #9, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkPolicy() {
        /*
            Method dump skipped, instructions count: 1534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(2:88|89)|(6:(15:91|92|93|(11:95|96|(2:99|97)|100|101|(2:104|102)|105|106|107|108|109)|116|96|(1:97)|100|101|(1:102)|105|106|107|108|109)|105|106|107|108|109)|119|92|93|(0)|116|96|(1:97)|100|101|(1:102)) */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02d6, code lost:
        addError(new org.bouncycastle.i18n.ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.crlAuthInfoAccError"), r8);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02f5 A[LOOP:1: B:100:0x02ef->B:102:0x02f5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0323 A[LOOP:2: B:104:0x031d->B:106:0x0323, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02d1 A[Catch: AnnotatedException -> 0x02d6, TRY_LEAVE, TryCatch #17 {AnnotatedException -> 0x02d6, blocks: (B:93:0x02c9, B:95:0x02d1), top: B:176:0x02c9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkSignatures() {
        /*
            Method dump skipped, instructions count: 1097
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", "BC").generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        ErrorBundle errorBundle2;
        char c = 0;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            int i2 = 0;
            boolean z = false;
            while (i2 < aSN1Sequence.size()) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle2 = new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle2 = new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            if (monetaryValue.getCurrency().isAlphabetic()) {
                                Object[] objArr = new Object[3];
                                objArr[c] = monetaryValue.getCurrency().getAlphabetic();
                                objArr[1] = new TrustedInput(new Double(doubleValue));
                                objArr[2] = monetaryValue;
                                errorBundle = new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.QcLimitValueAlpha", objArr);
                            } else {
                                errorBundle = new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue});
                            }
                            addNotification(errorBundle, i);
                        } else {
                            addNotification(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                    i2++;
                    c = 0;
                }
                addNotification(errorBundle2, i);
                i2++;
                c = 0;
            }
            return !z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.f27379n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.f27379n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0268  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void checkCRLs(java.security.cert.PKIXParameters r19, java.security.cert.X509Certificate r20, java.util.Date r21, java.security.cert.X509Certificate r22, java.security.PublicKey r23, java.util.Vector r24, int r25) throws org.bouncycastle.x509.CertPathReviewerException {
        /*
            Method dump skipped, instructions count: 1088
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.f27379n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i2] = new ArrayList();
            this.errors[i2] = new ArrayList();
            i2++;
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((ASN1IA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.f27379n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((ASN1IA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                if (authorityKeyIdentifier.getAuthorityCertSerialNumber() != null) {
                    x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                } else {
                    byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                    if (keyIdentifier != null) {
                        x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                    }
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        this.certPath = certPath;
        this.certs = certPath.getCertificates();
        this.f27379n = this.certs.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle("org.bouncycastle.x509.CertPathReviewerMessages", "CertPathReviewer.emptyCertPath"));
        }
        this.pkixParams = (PKIXParameters) pKIXParameters.clone();
        this.currentDate = new Date();
        this.validDate = getValidityDate(this.pkixParams, this.currentDate);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
