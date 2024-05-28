package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.android.phone.mrpc.core.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class CallableC1949q implements Callable<C1953u> {

    /* renamed from: e */
    private static final HttpRequestRetryHandler f3443e = new C1930ad();

    /* renamed from: a */
    protected C1944l f3444a;

    /* renamed from: b */
    protected Context f3445b;

    /* renamed from: c */
    protected C1947o f3446c;

    /* renamed from: d */
    String f3447d;

    /* renamed from: f */
    private HttpUriRequest f3448f;

    /* renamed from: i */
    private CookieManager f3451i;

    /* renamed from: j */
    private AbstractHttpEntity f3452j;

    /* renamed from: k */
    private HttpHost f3453k;

    /* renamed from: l */
    private URL f3454l;

    /* renamed from: q */
    private String f3459q;

    /* renamed from: g */
    private HttpContext f3449g = new BasicHttpContext();

    /* renamed from: h */
    private CookieStore f3450h = new BasicCookieStore();

    /* renamed from: m */
    private int f3455m = 0;

    /* renamed from: n */
    private boolean f3456n = false;

    /* renamed from: o */
    private boolean f3457o = false;

    /* renamed from: p */
    private String f3458p = null;

    public CallableC1949q(C1944l c1944l, C1947o c1947o) {
        this.f3444a = c1944l;
        this.f3445b = this.f3444a.f3421a;
        this.f3446c = c1947o;
    }

    /* renamed from: a */
    private static long m21081a(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if ("max-age".equalsIgnoreCase(strArr[i])) {
                int i2 = i + 1;
                if (strArr[i2] != null) {
                    try {
                        return Long.parseLong(strArr[i2]);
                    } catch (Exception unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return 0L;
    }

    /* renamed from: a */
    private static HttpUrlHeader m21083a(HttpResponse httpResponse) {
        Header[] allHeaders;
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    /* renamed from: a */
    private C1953u m21082a(HttpResponse httpResponse, int i, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        String str2;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        String str3 = null;
        if (entity == null || httpResponse.getStatusLine().getStatusCode() != 200) {
            if (entity == null) {
                httpResponse.getStatusLine().getStatusCode();
                return null;
            }
            return null;
        }
        new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            m21084a(entity, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.f3457o = false;
            this.f3444a.m21102c(System.currentTimeMillis() - currentTimeMillis);
            this.f3444a.m21107a(byteArray.length);
            new StringBuilder("res:").append(byteArray.length);
            C1948p c1948p = new C1948p(m21083a(httpResponse), i, str, byteArray);
            long m21079b = m21079b(httpResponse);
            Header contentType = httpResponse.getEntity().getContentType();
            if (contentType != null) {
                HashMap<String, String> m21085a = m21085a(contentType.getValue());
                str3 = m21085a.get("charset");
                str2 = m21085a.get("Content-Type");
            } else {
                str2 = null;
            }
            c1948p.m21065b(str2);
            c1948p.m21088a(str3);
            c1948p.m21089a(System.currentTimeMillis());
            c1948p.m21087b(m21079b);
            try {
                byteArrayOutputStream.close();
                return c1948p;
            } catch (IOException e) {
                throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
            }
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static HashMap<String, String> m21085a(String str) {
        String[] split;
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(";")) {
            String[] split2 = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }

    /* renamed from: a */
    private void m21084a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream m21128a = C1931b.m21128a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = m21128a.read(bArr);
                    if (read == -1 || this.f3446c.m21067h()) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    if (this.f3446c.m21069f() != null) {
                        int i = (contentLength > 0L ? 1 : (contentLength == 0L ? 0 : -1));
                    }
                }
                outputStream.flush();
            } catch (Exception e) {
                e.getCause();
                throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
            }
        } finally {
            C1950r.m21071a(m21128a);
        }
    }

    /* renamed from: b */
    private static long m21079b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return m21081a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Expires");
        if (firstHeader2 != null) {
            return C1931b.m21123b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0L;
    }

    /* renamed from: b */
    private URI m21080b() {
        String m21101a = this.f3446c.m21101a();
        String str = this.f3447d;
        if (str != null) {
            m21101a = str;
        }
        if (m21101a != null) {
            return new URI(m21101a);
        }
        throw new RuntimeException("url should not be null");
    }

    /* renamed from: c */
    private HttpUriRequest m21078c() {
        HttpUriRequest httpUriRequest = this.f3448f;
        if (httpUriRequest != null) {
            return httpUriRequest;
        }
        if (this.f3452j == null) {
            byte[] m21095b = this.f3446c.m21095b();
            String m21094b = this.f3446c.m21094b("gzip");
            if (m21095b != null) {
                if (TextUtils.equals(m21094b, "true")) {
                    this.f3452j = C1931b.m21124a(m21095b);
                } else {
                    this.f3452j = new ByteArrayEntity(m21095b);
                }
                this.f3452j.setContentType(this.f3446c.m21093c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.f3452j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(m21080b());
            httpPost.setEntity(abstractHttpEntity);
            this.f3448f = httpPost;
        } else {
            this.f3448f = new HttpGet(m21080b());
        }
        return this.f3448f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010b A[Catch: Exception -> 0x026f, NullPointerException -> 0x0291, IOException -> 0x02b5, UnknownHostException -> 0x02df, HttpHostConnectException -> 0x030b, NoHttpResponseException -> 0x032f, SocketTimeoutException -> 0x035a, ConnectTimeoutException -> 0x0385, ConnectionPoolTimeoutException -> 0x03af, SSLException -> 0x03d9, SSLPeerUnverifiedException -> 0x0403, SSLHandshakeException -> 0x042d, URISyntaxException -> 0x0457, HttpException -> 0x0464, TryCatch #3 {HttpException -> 0x0464, NullPointerException -> 0x0291, SocketTimeoutException -> 0x035a, URISyntaxException -> 0x0457, UnknownHostException -> 0x02df, SSLHandshakeException -> 0x042d, SSLPeerUnverifiedException -> 0x0403, SSLException -> 0x03d9, NoHttpResponseException -> 0x032f, ConnectionPoolTimeoutException -> 0x03af, ConnectTimeoutException -> 0x0385, HttpHostConnectException -> 0x030b, IOException -> 0x02b5, Exception -> 0x026f, blocks: (B:3:0x0004, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x0146, B:51:0x0151, B:53:0x0169, B:54:0x0170, B:56:0x0176, B:57:0x017a, B:59:0x0180, B:61:0x018c, B:65:0x01bb, B:66:0x01d7, B:74:0x01f4, B:75:0x020d, B:76:0x020e, B:78:0x0216, B:80:0x021c, B:84:0x0228, B:86:0x022c, B:88:0x023c, B:90:0x0244, B:92:0x024e, B:50:0x014b, B:43:0x010b, B:94:0x0263, B:95:0x026e, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:180:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012b A[Catch: Exception -> 0x026f, NullPointerException -> 0x0291, IOException -> 0x02b5, UnknownHostException -> 0x02df, HttpHostConnectException -> 0x030b, NoHttpResponseException -> 0x032f, SocketTimeoutException -> 0x035a, ConnectTimeoutException -> 0x0385, ConnectionPoolTimeoutException -> 0x03af, SSLException -> 0x03d9, SSLPeerUnverifiedException -> 0x0403, SSLHandshakeException -> 0x042d, URISyntaxException -> 0x0457, HttpException -> 0x0464, TryCatch #3 {HttpException -> 0x0464, NullPointerException -> 0x0291, SocketTimeoutException -> 0x035a, URISyntaxException -> 0x0457, UnknownHostException -> 0x02df, SSLHandshakeException -> 0x042d, SSLPeerUnverifiedException -> 0x0403, SSLException -> 0x03d9, NoHttpResponseException -> 0x032f, ConnectionPoolTimeoutException -> 0x03af, ConnectTimeoutException -> 0x0385, HttpHostConnectException -> 0x030b, IOException -> 0x02b5, Exception -> 0x026f, blocks: (B:3:0x0004, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x0146, B:51:0x0151, B:53:0x0169, B:54:0x0170, B:56:0x0176, B:57:0x017a, B:59:0x0180, B:61:0x018c, B:65:0x01bb, B:66:0x01d7, B:74:0x01f4, B:75:0x020d, B:76:0x020e, B:78:0x0216, B:80:0x021c, B:84:0x0228, B:86:0x022c, B:88:0x023c, B:90:0x0244, B:92:0x024e, B:50:0x014b, B:43:0x010b, B:94:0x0263, B:95:0x026e, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:180:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0146 A[Catch: Exception -> 0x026f, NullPointerException -> 0x0291, IOException -> 0x02b5, UnknownHostException -> 0x02df, HttpHostConnectException -> 0x030b, NoHttpResponseException -> 0x032f, SocketTimeoutException -> 0x035a, ConnectTimeoutException -> 0x0385, ConnectionPoolTimeoutException -> 0x03af, SSLException -> 0x03d9, SSLPeerUnverifiedException -> 0x0403, SSLHandshakeException -> 0x042d, URISyntaxException -> 0x0457, HttpException -> 0x0464, TryCatch #3 {HttpException -> 0x0464, NullPointerException -> 0x0291, SocketTimeoutException -> 0x035a, URISyntaxException -> 0x0457, UnknownHostException -> 0x02df, SSLHandshakeException -> 0x042d, SSLPeerUnverifiedException -> 0x0403, SSLException -> 0x03d9, NoHttpResponseException -> 0x032f, ConnectionPoolTimeoutException -> 0x03af, ConnectTimeoutException -> 0x0385, HttpHostConnectException -> 0x030b, IOException -> 0x02b5, Exception -> 0x026f, blocks: (B:3:0x0004, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x0146, B:51:0x0151, B:53:0x0169, B:54:0x0170, B:56:0x0176, B:57:0x017a, B:59:0x0180, B:61:0x018c, B:65:0x01bb, B:66:0x01d7, B:74:0x01f4, B:75:0x020d, B:76:0x020e, B:78:0x0216, B:80:0x021c, B:84:0x0228, B:86:0x022c, B:88:0x023c, B:90:0x0244, B:92:0x024e, B:50:0x014b, B:43:0x010b, B:94:0x0263, B:95:0x026e, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:180:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014b A[Catch: Exception -> 0x026f, NullPointerException -> 0x0291, IOException -> 0x02b5, UnknownHostException -> 0x02df, HttpHostConnectException -> 0x030b, NoHttpResponseException -> 0x032f, SocketTimeoutException -> 0x035a, ConnectTimeoutException -> 0x0385, ConnectionPoolTimeoutException -> 0x03af, SSLException -> 0x03d9, SSLPeerUnverifiedException -> 0x0403, SSLHandshakeException -> 0x042d, URISyntaxException -> 0x0457, HttpException -> 0x0464, TryCatch #3 {HttpException -> 0x0464, NullPointerException -> 0x0291, SocketTimeoutException -> 0x035a, URISyntaxException -> 0x0457, UnknownHostException -> 0x02df, SSLHandshakeException -> 0x042d, SSLPeerUnverifiedException -> 0x0403, SSLException -> 0x03d9, NoHttpResponseException -> 0x032f, ConnectionPoolTimeoutException -> 0x03af, ConnectTimeoutException -> 0x0385, HttpHostConnectException -> 0x030b, IOException -> 0x02b5, Exception -> 0x026f, blocks: (B:3:0x0004, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x0146, B:51:0x0151, B:53:0x0169, B:54:0x0170, B:56:0x0176, B:57:0x017a, B:59:0x0180, B:61:0x018c, B:65:0x01bb, B:66:0x01d7, B:74:0x01f4, B:75:0x020d, B:76:0x020e, B:78:0x0216, B:80:0x021c, B:84:0x0228, B:86:0x022c, B:88:0x023c, B:90:0x0244, B:92:0x024e, B:50:0x014b, B:43:0x010b, B:94:0x0263, B:95:0x026e, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:180:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0169 A[Catch: Exception -> 0x026f, NullPointerException -> 0x0291, IOException -> 0x02b5, UnknownHostException -> 0x02df, HttpHostConnectException -> 0x030b, NoHttpResponseException -> 0x032f, SocketTimeoutException -> 0x035a, ConnectTimeoutException -> 0x0385, ConnectionPoolTimeoutException -> 0x03af, SSLException -> 0x03d9, SSLPeerUnverifiedException -> 0x0403, SSLHandshakeException -> 0x042d, URISyntaxException -> 0x0457, HttpException -> 0x0464, TryCatch #3 {HttpException -> 0x0464, NullPointerException -> 0x0291, SocketTimeoutException -> 0x035a, URISyntaxException -> 0x0457, UnknownHostException -> 0x02df, SSLHandshakeException -> 0x042d, SSLPeerUnverifiedException -> 0x0403, SSLException -> 0x03d9, NoHttpResponseException -> 0x032f, ConnectionPoolTimeoutException -> 0x03af, ConnectTimeoutException -> 0x0385, HttpHostConnectException -> 0x030b, IOException -> 0x02b5, Exception -> 0x026f, blocks: (B:3:0x0004, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x0146, B:51:0x0151, B:53:0x0169, B:54:0x0170, B:56:0x0176, B:57:0x017a, B:59:0x0180, B:61:0x018c, B:65:0x01bb, B:66:0x01d7, B:74:0x01f4, B:75:0x020d, B:76:0x020e, B:78:0x0216, B:80:0x021c, B:84:0x0228, B:86:0x022c, B:88:0x023c, B:90:0x0244, B:92:0x024e, B:50:0x014b, B:43:0x010b, B:94:0x0263, B:95:0x026e, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:180:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0176 A[Catch: Exception -> 0x026f, NullPointerException -> 0x0291, IOException -> 0x02b5, UnknownHostException -> 0x02df, HttpHostConnectException -> 0x030b, NoHttpResponseException -> 0x032f, SocketTimeoutException -> 0x035a, ConnectTimeoutException -> 0x0385, ConnectionPoolTimeoutException -> 0x03af, SSLException -> 0x03d9, SSLPeerUnverifiedException -> 0x0403, SSLHandshakeException -> 0x042d, URISyntaxException -> 0x0457, HttpException -> 0x0464, TryCatch #3 {HttpException -> 0x0464, NullPointerException -> 0x0291, SocketTimeoutException -> 0x035a, URISyntaxException -> 0x0457, UnknownHostException -> 0x02df, SSLHandshakeException -> 0x042d, SSLPeerUnverifiedException -> 0x0403, SSLException -> 0x03d9, NoHttpResponseException -> 0x032f, ConnectionPoolTimeoutException -> 0x03af, ConnectTimeoutException -> 0x0385, HttpHostConnectException -> 0x030b, IOException -> 0x02b5, Exception -> 0x026f, blocks: (B:3:0x0004, B:17:0x0032, B:19:0x003a, B:21:0x0040, B:22:0x0044, B:24:0x004a, B:25:0x0058, B:27:0x00d2, B:29:0x00d8, B:31:0x00e2, B:34:0x00eb, B:36:0x00f7, B:40:0x0101, B:42:0x0108, B:44:0x0123, B:46:0x012b, B:47:0x0138, B:49:0x0146, B:51:0x0151, B:53:0x0169, B:54:0x0170, B:56:0x0176, B:57:0x017a, B:59:0x0180, B:61:0x018c, B:65:0x01bb, B:66:0x01d7, B:74:0x01f4, B:75:0x020d, B:76:0x020e, B:78:0x0216, B:80:0x021c, B:84:0x0228, B:86:0x022c, B:88:0x023c, B:90:0x0244, B:92:0x024e, B:50:0x014b, B:43:0x010b, B:94:0x0263, B:95:0x026e, B:6:0x0017, B:8:0x001b, B:10:0x001f, B:12:0x0025, B:15:0x002d), top: B:180:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01eb  */
    @Override // java.util.concurrent.Callable
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alipay.android.phone.mrpc.core.C1953u call() {
        /*
            Method dump skipped, instructions count: 1151
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.CallableC1949q.call():com.alipay.android.phone.mrpc.core.u");
    }

    /* renamed from: e */
    private void m21076e() {
        HttpUriRequest httpUriRequest = this.f3448f;
        if (httpUriRequest != null) {
            httpUriRequest.abort();
        }
    }

    /* renamed from: f */
    private String m21075f() {
        if (TextUtils.isEmpty(this.f3459q)) {
            this.f3459q = this.f3446c.m21094b("operationType");
            return this.f3459q;
        }
        return this.f3459q;
    }

    /* renamed from: g */
    private int m21074g() {
        URL m21073h = m21073h();
        return m21073h.getPort() == -1 ? m21073h.getDefaultPort() : m21073h.getPort();
    }

    /* renamed from: h */
    private URL m21073h() {
        URL url = this.f3454l;
        if (url != null) {
            return url;
        }
        this.f3454l = new URL(this.f3446c.m21101a());
        return this.f3454l;
    }

    /* renamed from: i */
    private CookieManager m21072i() {
        CookieManager cookieManager = this.f3451i;
        if (cookieManager != null) {
            return cookieManager;
        }
        this.f3451i = CookieManager.getInstance();
        return this.f3451i;
    }

    /* renamed from: a */
    public final C1947o m21086a() {
        return this.f3446c;
    }
}
