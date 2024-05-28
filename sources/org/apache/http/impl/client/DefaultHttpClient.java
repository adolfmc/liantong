package org.apache.http.impl.client;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.RequestProxyAuthentication;
import org.apache.http.client.protocol.RequestTargetAuthentication;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.NetscapeDraftSpecFactory;
import org.apache.http.impl.cookie.RFC2109SpecFactory;
import org.apache.http.impl.cookie.RFC2965SpecFactory;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.VersionInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultHttpClient extends AbstractHttpClient {
    public DefaultHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        super(clientConnectionManager, httpParams);
    }

    public DefaultHttpClient(HttpParams httpParams) {
        super(null, httpParams);
    }

    public DefaultHttpClient() {
        super(null, null);
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected HttpParams createHttpParams() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "ISO-8859-1");
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        VersionInfo loadVersionInfo = VersionInfo.loadVersionInfo("org.apache.http.client", getClass().getClassLoader());
        HttpProtocolParams.setUserAgent(basicHttpParams, "Apache-HttpClient/" + (loadVersionInfo != null ? loadVersionInfo.getRelease() : "UNAVAILABLE") + " (java 1.4)");
        return basicHttpParams;
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected HttpRequestExecutor createRequestExecutor() {
        return new HttpRequestExecutor();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected ClientConnectionManager createClientConnectionManager() {
        String str;
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        HttpParams params = getParams();
        ClientConnectionManagerFactory clientConnectionManagerFactory = (ClientConnectionManagerFactory) params.getParameter("http.connection-manager.factory-object");
        if (clientConnectionManagerFactory == null && (str = (String) params.getParameter("http.connection-manager.factory-class-name")) != null) {
            try {
                clientConnectionManagerFactory = (ClientConnectionManagerFactory) Class.forName(str).newInstance();
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Invalid class name: " + str);
            } catch (IllegalAccessException e2) {
                throw new IllegalAccessError(e2.getMessage());
            } catch (InstantiationException e3) {
                throw new InstantiationError(e3.getMessage());
            }
        }
        if (clientConnectionManagerFactory != null) {
            return clientConnectionManagerFactory.newInstance(params, schemeRegistry);
        }
        return new SingleClientConnManager(getParams(), schemeRegistry);
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected HttpContext createHttpContext() {
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        basicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        basicHttpContext.setAttribute("http.cookie-store", getCookieStore());
        basicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return basicHttpContext;
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected ConnectionReuseStrategy createConnectionReuseStrategy() {
        return new DefaultConnectionReuseStrategy();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return new DefaultConnectionKeepAliveStrategy();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected AuthSchemeRegistry createAuthSchemeRegistry() {
        AuthSchemeRegistry authSchemeRegistry = new AuthSchemeRegistry();
        authSchemeRegistry.register("Basic", new BasicSchemeFactory());
        authSchemeRegistry.register("Digest", new DigestSchemeFactory());
        return authSchemeRegistry;
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected CookieSpecRegistry createCookieSpecRegistry() {
        CookieSpecRegistry cookieSpecRegistry = new CookieSpecRegistry();
        cookieSpecRegistry.register("best-match", new BestMatchSpecFactory());
        cookieSpecRegistry.register("compatibility", new BrowserCompatSpecFactory());
        cookieSpecRegistry.register("netscape", new NetscapeDraftSpecFactory());
        cookieSpecRegistry.register("rfc2109", new RFC2109SpecFactory());
        cookieSpecRegistry.register("rfc2965", new RFC2965SpecFactory());
        return cookieSpecRegistry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.client.AbstractHttpClient
    public BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor basicHttpProcessor = new BasicHttpProcessor();
        basicHttpProcessor.addInterceptor(new RequestDefaultHeaders());
        basicHttpProcessor.addInterceptor(new RequestContent());
        basicHttpProcessor.addInterceptor(new RequestTargetHost());
        basicHttpProcessor.addInterceptor(new RequestConnControl());
        basicHttpProcessor.addInterceptor(new RequestUserAgent());
        basicHttpProcessor.addInterceptor(new RequestExpectContinue());
        basicHttpProcessor.addInterceptor(new RequestAddCookies());
        basicHttpProcessor.addInterceptor(new ResponseProcessCookies());
        basicHttpProcessor.addInterceptor(new RequestTargetAuthentication());
        basicHttpProcessor.addInterceptor(new RequestProxyAuthentication());
        return basicHttpProcessor;
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected HttpRequestRetryHandler createHttpRequestRetryHandler() {
        return new DefaultHttpRequestRetryHandler();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected RedirectHandler createRedirectHandler() {
        return new DefaultRedirectHandler();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected AuthenticationHandler createTargetAuthenticationHandler() {
        return new DefaultTargetAuthenticationHandler();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected AuthenticationHandler createProxyAuthenticationHandler() {
        return new DefaultProxyAuthenticationHandler();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected CookieStore createCookieStore() {
        return new BasicCookieStore();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected CredentialsProvider createCredentialsProvider() {
        return new BasicCredentialsProvider();
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected HttpRoutePlanner createHttpRoutePlanner() {
        return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry(), null);
    }

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected UserTokenHandler createUserTokenHandler() {
        return new DefaultUserTokenHandler();
    }
}
