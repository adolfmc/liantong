package com.huawei.secure.android.common.ssl;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import com.huawei.secure.android.common.ssl.WebViewSSLCheckThread;
import com.huawei.secure.android.common.ssl.util.C5115b;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.huawei.secure.android.common.ssl.util.C5121h;
import java.security.cert.X509Certificate;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class WebViewSSLCheck {

    /* renamed from: a */
    private static final String f12053a = "WebViewSSLCheck";

    public static void checkServerCertificateNew(SslErrorHandler sslErrorHandler, SslError sslError, Context context) {
        checkServerCertificateNew(sslErrorHandler, sslError, null, context, null);
    }

    public static void checkServerCertificateNew(SslErrorHandler sslErrorHandler, SslError sslError, String str, Context context, WebViewSSLCheckThread.Callback callback) {
        String str2 = f12053a;
        C5118e.m13853c(str2, " error type : " + sslError.getPrimaryError() + " , cn is : " + sslError.getCertificate().getIssuedTo().getCName());
        X509Certificate m13884a = C5115b.m13884a(sslError.getCertificate());
        X509Certificate m13839a = new C5121h(context).m13839a();
        String str3 = f12053a;
        C5118e.m13856a(str3, "checkServerCertificateNew: error certificate is : " + m13884a);
        if (C5115b.m13880a(m13839a, m13884a)) {
            C5118e.m13853c(f12053a, "checkServerCertificateNew: proceed");
            if (callback != null) {
                callback.onProceed(context, str);
                return;
            } else {
                sslErrorHandler.proceed();
                return;
            }
        }
        C5118e.m13854b(f12053a, "checkServerCertificateNew: cancel");
        if (callback != null) {
            callback.onCancel(context, str);
        } else {
            sslErrorHandler.cancel();
        }
    }

    public static boolean checkServerCertificateNew(X509Certificate x509Certificate, SslError sslError) {
        return C5115b.m13880a(x509Certificate, C5115b.m13884a(sslError.getCertificate()));
    }

    public static boolean checkServerCertificateNew(String str, SslError sslError) {
        return checkServerCertificateNew(C5115b.m13883a(str), sslError);
    }
}
