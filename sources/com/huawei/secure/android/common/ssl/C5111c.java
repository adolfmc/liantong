package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.util.C5115b;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.C5121h;
import com.huawei.secure.android.common.ssl.util.ContextUtil;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.huawei.secure.android.common.ssl.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5111c implements X509TrustManager {

    /* renamed from: c */
    private static final String f12075c = "WebViewX509TrustManger";

    /* renamed from: a */
    private X509Certificate f12076a;

    /* renamed from: b */
    private List<X509TrustManager> f12077b = new ArrayList();

    public C5111c(Context context) {
        if (context != null) {
            ContextUtil.setContext(context);
            this.f12076a = new C5121h(context).m13837b();
            if (this.f12076a == null) {
                throw new NullPointerException("WebViewX509TrustManger cannot get cbg root ca");
            }
            return;
        }
        throw new NullPointerException("WebViewX509TrustManger context is null");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        C5118e.m13853c(f12075c, "checkClientTrusted");
        if (!this.f12077b.isEmpty()) {
            this.f12077b.get(0).checkClientTrusted(x509CertificateArr, str);
            return;
        }
        throw new CertificateException("checkClientTrusted CertificateException");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        C5118e.m13853c(f12075c, "checkServerTrusted");
        boolean z = false;
        for (int i = 0; i < x509CertificateArr.length; i++) {
            C5118e.m13856a(f12075c, "checkServerTrusted " + i + " : " + x509CertificateArr[i].getIssuerDN().getName());
        }
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        for (int i2 = 0; i2 < x509CertificateArr.length; i2++) {
            x509CertificateArr2[i2] = x509CertificateArr[(x509CertificateArr.length - 1) - i2];
        }
        CertificateException e = new CertificateException("CBG root CA CertificateException");
        try {
            z = C5115b.m13879a(this.f12076a, x509CertificateArr2);
        } catch (InvalidKeyException e2) {
            C5118e.m13854b(f12075c, "checkServerTrusted InvalidKeyException: " + e2.getMessage());
        } catch (NoSuchAlgorithmException e3) {
            C5118e.m13854b(f12075c, "checkServerTrusted NoSuchAlgorithmException: " + e3.getMessage());
        } catch (NoSuchProviderException e4) {
            C5118e.m13854b(f12075c, "checkServerTrusted NoSuchProviderException: " + e4.getMessage());
        } catch (SignatureException e5) {
            C5118e.m13854b(f12075c, "checkServerTrusted SignatureException: " + e5.getMessage());
        } catch (CertificateException e6) {
            e = e6;
            C5118e.m13854b(f12075c, "checkServerTrusted CertificateException: " + e.getMessage());
        }
        if (!z) {
            throw e;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            for (X509TrustManager x509TrustManager : this.f12077b) {
                arrayList.addAll(Arrays.asList(x509TrustManager.getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e) {
            C5118e.m13854b(f12075c, "getAcceptedIssuers exception : " + e.getMessage());
            return new X509Certificate[0];
        }
    }
}
