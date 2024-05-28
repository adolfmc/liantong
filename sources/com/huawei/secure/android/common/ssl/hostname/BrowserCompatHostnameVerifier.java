package com.huawei.secure.android.common.ssl.hostname;

import com.huawei.secure.android.common.ssl.util.AsyncTaskC5116c;
import com.huawei.secure.android.common.ssl.util.C5118e;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BrowserCompatHostnameVerifier implements HostnameVerifier {
    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        try {
            X509Certificate x509Certificate = (X509Certificate) sSLSession.getPeerCertificates()[0];
            C5118e.m13853c("", "verify: certificate is : " + x509Certificate.getSubjectDN().getName());
            C5113b.m13904a(str, x509Certificate, false);
            AsyncTaskC5116c.m13874a();
            return true;
        } catch (SSLException e) {
            C5118e.m13854b("", "SSLException : " + e.getMessage());
            return false;
        }
    }
}
