package com.huawei.secure.android.common.ssl.util;

import android.net.http.SslCertificate;
import java.io.ByteArrayInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.ssl.util.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5115b {

    /* renamed from: a */
    private static final String f12107a = "b";

    /* renamed from: b */
    private static final int f12108b = 5;

    /* renamed from: a */
    public static X509Certificate m13883a(String str) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(str.getBytes()));
        } catch (CertificateException e) {
            String str2 = f12107a;
            C5118e.m13854b(str2, "generateX509FromStr: CertificateException" + e.getMessage());
            return null;
        }
    }

    /* renamed from: b */
    public static boolean m13875b(X509Certificate[] x509CertificateArr) {
        Date date = new Date();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            try {
                x509Certificate.checkValidity(date);
            } catch (CertificateExpiredException e) {
                e = e;
                C5118e.m13854b(f12107a, "verifyCertificateDate: exception : " + e.getMessage());
                return false;
            } catch (CertificateNotYetValidException e2) {
                e = e2;
                C5118e.m13854b(f12107a, "verifyCertificateDate: exception : " + e.getMessage());
                return false;
            } catch (Exception e3) {
                C5118e.m13854b(f12107a, "verifyCertificateDate : exception : " + e3.getMessage());
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m13878a(X509Certificate x509Certificate, X509Certificate[] x509CertificateArr, X509CRL x509crl, String str) throws NoSuchAlgorithmException, CertificateException, NoSuchProviderException, InvalidKeyException, SignatureException {
        return !m13879a(x509Certificate, x509CertificateArr) && !m13876a(x509CertificateArr, x509crl) && m13881a(x509CertificateArr[x509CertificateArr.length - 1], str) && m13875b(x509CertificateArr);
    }

    /* renamed from: a */
    public static boolean m13876a(X509Certificate[] x509CertificateArr, X509CRL x509crl) {
        ArrayList arrayList = new ArrayList();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            arrayList.add(x509Certificate.getSerialNumber());
        }
        if (x509crl != null) {
            try {
                Set<? extends X509CRLEntry> revokedCertificates = x509crl.getRevokedCertificates();
                if (revokedCertificates == null || revokedCertificates.isEmpty()) {
                    return true;
                }
                for (X509CRLEntry x509CRLEntry : revokedCertificates) {
                    if (arrayList.contains(x509CRLEntry.getSerialNumber())) {
                        C5118e.m13854b(f12107a, "verify: certificate revoked");
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                C5118e.m13854b(f12107a, "verify: revoked verify exception : " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m13881a(X509Certificate x509Certificate, String str) {
        if (str.equals(x509Certificate.getSubjectDN().getName())) {
            return true;
        }
        C5118e.m13854b(f12107a, "verify: subject name is error");
        return false;
    }

    /* renamed from: a */
    public static boolean m13880a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            if (m13875b(new X509Certificate[]{x509Certificate, x509Certificate2})) {
                return true;
            }
            C5118e.m13854b(f12107a, "verify: date not right");
            return false;
        } catch (InvalidKeyException e) {
            String str = f12107a;
            C5118e.m13854b(str, "verify: publickey InvalidKeyException " + e.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e2) {
            String str2 = f12107a;
            C5118e.m13854b(str2, "verify: publickey NoSuchAlgorithmException " + e2.getMessage());
            return false;
        } catch (NoSuchProviderException e3) {
            String str3 = f12107a;
            C5118e.m13854b(str3, "verify: publickey NoSuchProviderException " + e3.getMessage());
            return false;
        } catch (SignatureException e4) {
            String str4 = f12107a;
            C5118e.m13854b(str4, "verify: publickey SignatureException " + e4.getMessage());
            return false;
        } catch (CertificateException e5) {
            String str5 = f12107a;
            C5118e.m13854b(str5, "verify: publickey CertificateException " + e5.getMessage());
            return false;
        } catch (Exception e6) {
            String str6 = f12107a;
            C5118e.m13854b(str6, "verify: Exception " + e6.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m13879a(X509Certificate x509Certificate, X509Certificate[] x509CertificateArr) throws NoSuchProviderException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Principal principal = null;
        int i = 0;
        while (i < x509CertificateArr.length) {
            X509Certificate x509Certificate2 = x509CertificateArr[i];
            Principal issuerDN = x509Certificate2.getIssuerDN();
            Principal subjectDN = x509Certificate2.getSubjectDN();
            if (principal != null) {
                if (issuerDN.equals(principal)) {
                    x509CertificateArr[i].verify(x509CertificateArr[i - 1].getPublicKey());
                } else {
                    C5118e.m13854b(f12107a, "verify: principalIssuer not match");
                    return false;
                }
            }
            i++;
            principal = subjectDN;
        }
        return m13880a(x509Certificate, x509CertificateArr[0]) && m13875b(x509CertificateArr) && m13882a(x509Certificate) && m13877a(x509CertificateArr);
    }

    /* renamed from: a */
    public static X509Certificate m13884a(SslCertificate sslCertificate) {
        byte[] byteArray = SslCertificate.saveState(sslCertificate).getByteArray("x509-certificate");
        if (byteArray != null) {
            try {
                return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(byteArray));
            } catch (CertificateException e) {
                C5118e.m13855a(f12107a, "exception", e);
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m13877a(X509Certificate[] x509CertificateArr) {
        for (int i = 0; i < x509CertificateArr.length - 1; i++) {
            if (!m13882a(x509CertificateArr[i])) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m13882a(X509Certificate x509Certificate) {
        if (x509Certificate == null || x509Certificate.getBasicConstraints() == -1) {
            return false;
        }
        return x509Certificate.getKeyUsage()[5];
    }
}
