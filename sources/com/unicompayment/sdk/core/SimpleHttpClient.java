package com.unicompayment.sdk.core;

import cn.ltzf.passguard.C1730a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p482w.C14261e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SimpleHttpClient {
    private static String CERT = null;
    private static final String TAG = "unicompay_SimpleHttpClient";
    private static CertificateFactory cerFactory;
    public static boolean isDevinfoSentToAuthServer;
    public static boolean isDevinfoSentToRiskServer;
    private static KeyStore keyStore;
    private static SSLContext sslcontext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class MyLog {
        /* renamed from: d */
        public static void m6019d(String str, String str2) {
        }

        /* renamed from: i */
        public static void m6018i(String str, String str2) {
        }

        /* renamed from: w */
        public static void m6017w(String str, String str2) {
        }

        /* renamed from: w */
        public static void m6016w(String str, String str2, Exception exc) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class MyX509TrustManager implements X509TrustManager {
        public X509TrustManager defaultX509TrustManager;

        public MyX509TrustManager() {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(SimpleHttpClient.CERT.getBytes());
            int i = 0;
            while (byteArrayInputStream.available() > 0) {
                Certificate generateCertificate = SimpleHttpClient.cerFactory.generateCertificate(byteArrayInputStream);
                KeyStore keyStore = SimpleHttpClient.keyStore;
                StringBuilder m22016a = C1730a.m22016a("trust");
                m22016a.append(i);
                keyStore.setCertificateEntry(m22016a.toString(), generateCertificate);
                i++;
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(SimpleHttpClient.keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            for (int i2 = 0; i2 < trustManagers.length; i2++) {
                if (trustManagers[i2] instanceof X509TrustManager) {
                    this.defaultX509TrustManager = (X509TrustManager) trustManagers[i2];
                    return;
                }
            }
            throw new Exception("Couldn't initialize");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.defaultX509TrustManager.checkClientTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.defaultX509TrustManager.checkServerTrusted(x509CertificateArr, str);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return this.defaultX509TrustManager.getAcceptedIssuers();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class NullHostNameVerifier implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            MyLog.m6018i("RestUtilImpl", C14261e.m22a("Approving certificate for ", str));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class NullX509TrustManager implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    static {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            sslcontext = SSLContext.getInstance("TLS");
            KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore = keyStore2;
            keyStore2.load(null, null);
            cerFactory = CertificateFactory.getInstance("X.509");
        } catch (Exception e) {
            MyLog.m6016w("unicompay_SimpleHttpClient", "init https client", e);
        }
        CERT = "";
    }

    private static HttpURLConnection getConnection(String str) {
        HttpsURLConnection httpsURLConnection = null;
        if (str == null || !(str.startsWith("http://") || str.startsWith("https://"))) {
            return null;
        }
        try {
            URL url = new URL(str);
            if (str.startsWith("http://")) {
                return (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            }
            sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, null);
            HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            try {
                httpsURLConnection2.setSSLSocketFactory(sslcontext.getSocketFactory());
                return httpsURLConnection2;
            } catch (Exception e) {
                httpsURLConnection = httpsURLConnection2;
                e = e;
                MyLog.m6016w("unicompay_SimpleHttpClient", "getconn", e);
                return httpsURLConnection;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static HttpURLConnection getDefaultConnection(String str) {
        if (str == null || !(str.startsWith("http://") || str.startsWith("https://"))) {
            return null;
        }
        try {
            URL url = new URL(str);
            return str.startsWith("http://") ? (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection()) : (HttpsURLConnection) NBSInstrumentation.openConnection(url.openConnection());
        } catch (Exception e) {
            MyLog.m6016w("unicompay_SimpleHttpClient", "getconn", e);
            return null;
        }
    }

    private static HttpURLConnection getInsecureConnection(String str) {
        HttpsURLConnection httpsURLConnection = null;
        if (str == null || !(str.startsWith("http://") || str.startsWith("https://"))) {
            return null;
        }
        try {
            URL url = new URL(str);
            if (str.startsWith("http://")) {
                return (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            }
            sslcontext.init(null, new TrustManager[]{new NullX509TrustManager()}, null);
            HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            try {
                httpsURLConnection2.setSSLSocketFactory(sslcontext.getSocketFactory());
                return httpsURLConnection2;
            } catch (Exception e) {
                httpsURLConnection = httpsURLConnection2;
                e = e;
                MyLog.m6016w("unicompay_SimpleHttpClient", "getconn", e);
                return httpsURLConnection;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e1, code lost:
        if (r9 == null) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00aa A[Catch: all -> 0x00d1, Exception -> 0x00d3, TryCatch #7 {Exception -> 0x00d3, blocks: (B:32:0x00a2, B:34:0x00aa, B:35:0x00bd, B:37:0x00c3, B:38:0x00c7), top: B:80:0x00a2, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f2 A[Catch: all -> 0x012e, TryCatch #8 {, blocks: (B:7:0x0008, B:9:0x0012, B:12:0x001e, B:16:0x0058, B:31:0x009f, B:40:0x00cd, B:49:0x00e3, B:50:0x00e6, B:54:0x00f6, B:53:0x00f2, B:47:0x00de, B:32:0x00a2, B:34:0x00aa, B:35:0x00bd, B:37:0x00c3, B:38:0x00c7, B:45:0x00d4, B:14:0x004a, B:19:0x005d, B:20:0x0084, B:30:0x0097), top: B:82:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.String postBodyJson(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicompayment.sdk.core.SimpleHttpClient.postBodyJson(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String postFormData(String str, HashMap<String, String> hashMap) {
        return postFormData(str, hashMap, null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:93:0x02ee, code lost:
        if (r15 != null) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x018d A[Catch: all -> 0x02dc, Exception -> 0x02de, TryCatch #3 {Exception -> 0x02de, blocks: (B:49:0x0187, B:51:0x018d, B:52:0x0191, B:54:0x0197, B:57:0x01a9, B:58:0x01e7, B:60:0x01ee, B:61:0x01f3, B:62:0x022b, B:64:0x0286, B:65:0x0299, B:67:0x029f, B:68:0x02a3), top: B:120:0x0187 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0286 A[Catch: all -> 0x02dc, Exception -> 0x02de, TryCatch #3 {Exception -> 0x02de, blocks: (B:49:0x0187, B:51:0x018d, B:52:0x0191, B:54:0x0197, B:57:0x01a9, B:58:0x01e7, B:60:0x01ee, B:61:0x01f3, B:62:0x022b, B:64:0x0286, B:65:0x0299, B:67:0x029f, B:68:0x02a3), top: B:120:0x0187 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.String postFormData(java.lang.String r17, java.util.HashMap<java.lang.String, java.lang.String> r18, java.lang.String r19, java.util.ArrayList<java.lang.String> r20) {
        /*
            Method dump skipped, instructions count: 849
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicompayment.sdk.core.SimpleHttpClient.postFormData(java.lang.String, java.util.HashMap, java.lang.String, java.util.ArrayList):java.lang.String");
    }
}
