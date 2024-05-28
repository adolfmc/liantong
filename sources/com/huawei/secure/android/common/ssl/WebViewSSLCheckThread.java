package com.huawei.secure.android.common.ssl;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import com.huawei.secure.android.common.ssl.util.AbstractC5117d;
import com.huawei.secure.android.common.ssl.util.C5118e;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentationHttpClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class WebViewSSLCheckThread extends Thread {

    /* renamed from: i */
    private static final String f12054i = "WebViewSSLCheckThread";

    /* renamed from: a */
    private SSLSocketFactory f12055a;

    /* renamed from: b */
    private HostnameVerifier f12056b;

    /* renamed from: c */
    private org.apache.http.conn.ssl.SSLSocketFactory f12057c;

    /* renamed from: d */
    private X509HostnameVerifier f12058d;

    /* renamed from: e */
    private SslErrorHandler f12059e;

    /* renamed from: f */
    private String f12060f;

    /* renamed from: g */
    private Callback f12061g;

    /* renamed from: h */
    private Context f12062h;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Callback {
        void onCancel(Context context, String str);

        void onProceed(Context context, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.secure.android.common.ssl.WebViewSSLCheckThread$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5108a implements okhttp3.Callback {

        /* renamed from: a */
        final /* synthetic */ Callback f12063a;

        /* renamed from: b */
        final /* synthetic */ Context f12064b;

        /* renamed from: c */
        final /* synthetic */ String f12065c;

        /* renamed from: d */
        final /* synthetic */ SslErrorHandler f12066d;

        C5108a(Callback callback, Context context, String str, SslErrorHandler sslErrorHandler) {
            this.f12063a = callback;
            this.f12064b = context;
            this.f12065c = str;
            this.f12066d = sslErrorHandler;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            String str = WebViewSSLCheckThread.f12054i;
            C5118e.m13854b(str, "onFailure , IO Exception : " + iOException.getMessage());
            Callback callback = this.f12063a;
            if (callback != null) {
                callback.onCancel(this.f12064b, this.f12065c);
            } else {
                this.f12066d.cancel();
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            C5118e.m13854b(WebViewSSLCheckThread.f12054i, "onResponse . proceed");
            Callback callback = this.f12063a;
            if (callback != null) {
                callback.onProceed(this.f12064b, this.f12065c);
            } else {
                this.f12066d.proceed();
            }
        }
    }

    public WebViewSSLCheckThread() {
    }

    /* renamed from: b */
    private void m13916b() {
        C5118e.m13853c(f12054i, "callbackCancel: ");
        Callback callback = this.f12061g;
        if (callback != null) {
            callback.onCancel(this.f12062h, this.f12060f);
        } else if (this.f12059e != null) {
            C5118e.m13853c(f12054i, "callbackCancel 2: ");
            this.f12059e.cancel();
        }
    }

    /* renamed from: c */
    private void m13915c() {
        C5118e.m13853c(f12054i, "callbackProceed: ");
        Callback callback = this.f12061g;
        if (callback != null) {
            callback.onProceed(this.f12062h, this.f12060f);
            return;
        }
        SslErrorHandler sslErrorHandler = this.f12059e;
        if (sslErrorHandler != null) {
            sslErrorHandler.proceed();
        }
    }

    public static void checkServerCertificateWithOK(SslErrorHandler sslErrorHandler, String str, Context context) {
        checkServerCertificateWithOK(sslErrorHandler, str, context, null);
    }

    public X509HostnameVerifier getApacheHostnameVerifier() {
        return this.f12058d;
    }

    public org.apache.http.conn.ssl.SSLSocketFactory getApacheSSLSocketFactory() {
        return this.f12057c;
    }

    public Callback getCallback() {
        return this.f12061g;
    }

    public Context getContext() {
        return this.f12062h;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.f12056b;
    }

    public SslErrorHandler getSslErrorHandler() {
        return this.f12059e;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.f12055a;
    }

    public String getUrl() {
        return this.f12060f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [javax.net.ssl.HostnameVerifier] */
    /* JADX WARN: Type inference failed for: r0v3, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r0v5 */
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Exception e;
        HttpsURLConnection httpsURLConnection;
        super.run();
        if (this.f12057c != null && this.f12058d != null) {
            if (this.f12059e != null && !TextUtils.isEmpty(this.f12060f)) {
                try {
                    try {
                        this.f12057c.setHostnameVerifier(this.f12058d);
                        if (this.f12057c instanceof SecureApacheSSLSocketFactory) {
                            ((SecureApacheSSLSocketFactory) this.f12057c).setContext(this.f12062h);
                        }
                        BasicHttpParams basicHttpParams = new BasicHttpParams();
                        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 30000);
                        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
                        SchemeRegistry schemeRegistry = new SchemeRegistry();
                        schemeRegistry.register(new Scheme("https", this.f12057c, 443));
                        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                        HttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
                        HttpGet httpGet = new HttpGet();
                        httpGet.setURI(new URI(this.f12060f));
                        HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpGet) : NBSInstrumentationHttpClient.execute(defaultHttpClient, httpGet);
                        C5118e.m13853c(f12054i, "status code is : " + execute.getStatusLine().getStatusCode());
                        AbstractC5117d.m13862a((Reader) null);
                        m13915c();
                        return;
                    } catch (Throwable th) {
                        AbstractC5117d.m13862a((Reader) null);
                        throw th;
                    }
                } catch (Exception e2) {
                    C5118e.m13854b(f12054i, "run: exception : " + e2.getMessage());
                    m13916b();
                    AbstractC5117d.m13862a((Reader) null);
                    return;
                }
            }
            C5118e.m13854b(f12054i, "sslErrorHandler or url is null");
            m13916b();
            return;
        }
        if (this.f12055a != null) {
            ?? r0 = this.f12056b;
            try {
                if (r0 != 0) {
                    try {
                        URLConnection openConnection = NBSInstrumentation.openConnection(new URL(this.f12060f).openConnection());
                        if (openConnection instanceof HttpsURLConnection) {
                            httpsURLConnection = (HttpsURLConnection) openConnection;
                            try {
                                httpsURLConnection.setSSLSocketFactory(this.f12055a);
                                httpsURLConnection.setHostnameVerifier(this.f12056b);
                                httpsURLConnection.setRequestMethod("GET");
                                httpsURLConnection.setConnectTimeout(10000);
                                httpsURLConnection.setReadTimeout(20000);
                                httpsURLConnection.connect();
                            } catch (Exception e3) {
                                e = e3;
                                C5118e.m13854b(f12054i, "exception : " + e.getMessage());
                                m13916b();
                                if (httpsURLConnection != null) {
                                    httpsURLConnection.disconnect();
                                    return;
                                }
                                return;
                            }
                        } else {
                            httpsURLConnection = null;
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        m13915c();
                        return;
                    } catch (Exception e4) {
                        e = e4;
                        httpsURLConnection = null;
                    } catch (Throwable th2) {
                        th = th2;
                        r0 = 0;
                        if (r0 != 0) {
                            r0.disconnect();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        m13916b();
    }

    public void setApacheHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        this.f12058d = x509HostnameVerifier;
    }

    public void setApacheSSLSocketFactory(org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory) {
        this.f12057c = sSLSocketFactory;
    }

    public void setCallback(Callback callback) {
        this.f12061g = callback;
    }

    public void setContext(Context context) {
        this.f12062h = context;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.f12056b = hostnameVerifier;
    }

    public void setSslErrorHandler(SslErrorHandler sslErrorHandler) {
        this.f12059e = sslErrorHandler;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f12055a = sSLSocketFactory;
    }

    public void setUrl(String str) {
        this.f12060f = str;
    }

    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, Context context) throws CertificateException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException, IllegalAccessException {
        setSslErrorHandler(sslErrorHandler);
        setUrl(str);
        setContext(context);
        setSslSocketFactory(new SecureSSLSocketFactoryNew(new C5111c(context)));
        setHostnameVerifier(new StrictHostnameVerifier());
        try {
            setApacheSSLSocketFactory(new SecureApacheSSLSocketFactory(null, new C5111c(context)));
        } catch (UnrecoverableKeyException e) {
            String str2 = f12054i;
            C5118e.m13854b(str2, "WebViewSSLCheckThread: UnrecoverableKeyException : " + e.getMessage());
        }
        setApacheHostnameVerifier(SecureApacheSSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
    }

    public static void checkServerCertificateWithOK(SslErrorHandler sslErrorHandler, String str, Context context, Callback callback) {
        if (sslErrorHandler != null && !TextUtils.isEmpty(str) && context != null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            try {
                SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = new SecureSSLSocketFactoryNew(new C5111c(context));
                secureSSLSocketFactoryNew.setContext(context);
                builder.sslSocketFactory(secureSSLSocketFactoryNew, new C5111c(context));
                builder.hostnameVerifier(new StrictHostnameVerifier());
                (!(builder instanceof OkHttpClient.Builder) ? builder.build() : NBSOkHttp3Instrumentation.builderInit(builder)).newCall(new Request.Builder().url(str).build()).enqueue(new C5108a(callback, context, str, sslErrorHandler));
                return;
            } catch (Exception e) {
                String str2 = f12054i;
                C5118e.m13854b(str2, "checkServerCertificateWithOK: exception : " + e.getMessage());
                sslErrorHandler.cancel();
                return;
            }
        }
        C5118e.m13854b(f12054i, "checkServerCertificateWithOK: handler or url or context is null");
    }

    @Deprecated
    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        setSslErrorHandler(sslErrorHandler);
        setUrl(str);
        setSslSocketFactory(sSLSocketFactory);
        setHostnameVerifier(hostnameVerifier);
    }

    @Deprecated
    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier) {
        setSslErrorHandler(sslErrorHandler);
        setUrl(str);
        setApacheSSLSocketFactory(sSLSocketFactory);
        setApacheHostnameVerifier(x509HostnameVerifier);
    }

    @Deprecated
    public WebViewSSLCheckThread(SslErrorHandler sslErrorHandler, String str, org.apache.http.conn.ssl.SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier, Callback callback, Context context) {
        this.f12059e = sslErrorHandler;
        this.f12060f = str;
        this.f12057c = sSLSocketFactory;
        this.f12058d = x509HostnameVerifier;
        this.f12061g = callback;
        this.f12062h = context;
    }
}
