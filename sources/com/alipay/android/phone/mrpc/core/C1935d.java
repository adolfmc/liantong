package com.alipay.android.phone.mrpc.core;

import com.alipay.android.phone.mrpc.core.C1931b;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.RedirectHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1935d extends DefaultHttpClient {

    /* renamed from: a */
    final /* synthetic */ C1931b f3406a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1935d(C1931b c1931b, ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        super(clientConnectionManager, httpParams);
        this.f3406a = c1931b;
    }

    @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
    protected final ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return new C1937f(this);
    }

    @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
    protected final HttpContext createHttpContext() {
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        basicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        basicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return basicHttpContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
    public final BasicHttpProcessor createHttpProcessor() {
        HttpRequestInterceptor httpRequestInterceptor;
        BasicHttpProcessor createHttpProcessor = super.createHttpProcessor();
        httpRequestInterceptor = C1931b.f3399c;
        createHttpProcessor.addRequestInterceptor(httpRequestInterceptor);
        createHttpProcessor.addRequestInterceptor(new C1931b.C1932a(this.f3406a, (byte) 0));
        return createHttpProcessor;
    }

    @Override // org.apache.http.impl.client.DefaultHttpClient, org.apache.http.impl.client.AbstractHttpClient
    protected final RedirectHandler createRedirectHandler() {
        return new C1936e(this);
    }
}
