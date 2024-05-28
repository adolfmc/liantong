package com.alipay.android.phone.mrpc.core;

import android.net.SSLCertificateSocketFactory;
import android.util.Base64;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentationHttpClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.android.phone.mrpc.core.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1931b implements HttpClient {

    /* renamed from: a */
    public static long f3397a = 160;

    /* renamed from: b */
    private static String[] f3398b = {"text/", "application/xml", "application/json"};

    /* renamed from: c */
    private static final HttpRequestInterceptor f3399c = new C1934c();

    /* renamed from: d */
    private final HttpClient f3400d;

    /* renamed from: e */
    private RuntimeException f3401e = new IllegalStateException("AndroidHttpClient created and never closed");

    /* renamed from: f */
    private volatile C1933b f3402f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.android.phone.mrpc.core.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1932a implements HttpRequestInterceptor {
        private C1932a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ C1932a(C1931b c1931b, byte b) {
            this();
        }

        @Override // org.apache.http.HttpRequestInterceptor
        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            C1933b c1933b = C1931b.this.f3402f;
            if (c1933b != null && C1933b.m21120a(c1933b) && (httpRequest instanceof HttpUriRequest)) {
                C1933b.m21119a(c1933b, C1931b.m21125a((HttpUriRequest) httpRequest));
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.android.phone.mrpc.core.b$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C1933b {

        /* renamed from: a */
        private final String f3404a;

        /* renamed from: b */
        private final int f3405b;

        /* renamed from: a */
        static /* synthetic */ void m21119a(C1933b c1933b, String str) {
            Log.println(c1933b.f3405b, c1933b.f3404a, str);
        }

        /* renamed from: a */
        static /* synthetic */ boolean m21120a(C1933b c1933b) {
            return Log.isLoggable(c1933b.f3404a, c1933b.f3405b);
        }
    }

    private C1931b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f3400d = new C1935d(this, clientConnectionManager, httpParams);
    }

    /* renamed from: a */
    public static C1931b m21129a(String str) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(30000, null), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, 60000L);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new C1931b(threadSafeClientConnManager, basicHttpParams);
    }

    /* renamed from: a */
    public static InputStream m21128a(HttpEntity httpEntity) {
        Header contentEncoding;
        String value;
        InputStream content = httpEntity.getContent();
        if (content != null && (contentEncoding = httpEntity.getContentEncoding()) != null && (value = contentEncoding.getValue()) != null && value.contains("gzip")) {
            return new GZIPInputStream(content);
        }
        return content;
    }

    /* renamed from: a */
    static /* synthetic */ String m21125a(HttpUriRequest httpUriRequest) {
        Header[] allHeaders;
        HttpEntity entity;
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("curl ");
        for (Header header : httpUriRequest.getAllHeaders()) {
            if (!header.getName().equals("Authorization") && !header.getName().equals("Cookie")) {
                sb.append("--header \"");
                sb.append(header.toString().trim());
                sb.append("\" ");
            }
        }
        URI uri = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper) {
            HttpRequest original = ((RequestWrapper) httpUriRequest).getOriginal();
            if (original instanceof HttpUriRequest) {
                uri = ((HttpUriRequest) original).getURI();
            }
        }
        sb.append("\"");
        sb.append(uri);
        sb.append("\"");
        if ((httpUriRequest instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity()) != null && entity.isRepeatable()) {
            if (entity.getContentLength() < 1024) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                entity.writeTo(byteArrayOutputStream);
                if (m21121b(httpUriRequest)) {
                    sb.insert(0, "echo '" + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2) + "' | base64 -d > /tmp/$$.bin; ");
                    str = " --data-binary @/tmp/$$.bin";
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    sb.append(" --data-ascii \"");
                    sb.append(byteArrayOutputStream2);
                    str = "\"";
                }
            } else {
                str = " [TOO MUCH DATA TO INCLUDE]";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static AbstractHttpEntity m21124a(byte[] bArr) {
        if (bArr.length < f3397a) {
            return new ByteArrayEntity(bArr);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding("gzip");
        StringBuilder sb = new StringBuilder("gzip size:");
        sb.append(bArr.length);
        sb.append("->");
        sb.append(byteArrayEntity.getContentLength());
        return byteArrayEntity;
    }

    /* renamed from: a */
    public static void m21127a(HttpRequest httpRequest) {
        httpRequest.addHeader("Accept-Encoding", "gzip");
    }

    /* renamed from: b */
    public static long m21123b(String str) {
        return C1942k.m21113a(str);
    }

    /* renamed from: b */
    public static void m21122b(HttpRequest httpRequest) {
        httpRequest.addHeader("Connection", "Keep-Alive");
    }

    /* renamed from: b */
    private static boolean m21121b(HttpUriRequest httpUriRequest) {
        Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            for (Header header : headers) {
                if ("gzip".equalsIgnoreCase(header.getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders("content-type");
        if (headers2 != null) {
            for (Header header2 : headers2) {
                for (String str : f3398b) {
                    if (header2.getValue().startsWith(str)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public final void m21126a(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.f3400d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpHost, httpRequest, responseHandler) : (T) NBSInstrumentationHttpClient.execute(httpClient, httpHost, httpRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpHost, httpRequest, responseHandler, httpContext) : (T) NBSInstrumentationHttpClient.execute(httpClient, httpHost, httpRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpUriRequest, responseHandler) : (T) NBSInstrumentationHttpClient.execute(httpClient, httpUriRequest, responseHandler);
    }

    @Override // org.apache.http.client.HttpClient
    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? (T) httpClient.execute(httpUriRequest, responseHandler, httpContext) : (T) NBSInstrumentationHttpClient.execute(httpClient, httpUriRequest, responseHandler, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest) : NBSInstrumentationHttpClient.execute(httpClient, httpHost, httpRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest, httpContext) : NBSInstrumentationHttpClient.execute(httpClient, httpHost, httpRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest) : NBSInstrumentationHttpClient.execute(httpClient, httpUriRequest);
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        HttpClient httpClient = this.f3400d;
        return !(httpClient instanceof HttpClient) ? httpClient.execute(httpUriRequest, httpContext) : NBSInstrumentationHttpClient.execute(httpClient, httpUriRequest, httpContext);
    }

    @Override // org.apache.http.client.HttpClient
    public final ClientConnectionManager getConnectionManager() {
        return this.f3400d.getConnectionManager();
    }

    @Override // org.apache.http.client.HttpClient
    public final HttpParams getParams() {
        return this.f3400d.getParams();
    }
}
