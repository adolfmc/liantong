package org.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.BasicRouteDirector;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultRequestDirector implements RequestDirector {
    private static Method cleartextTrafficPermittedMethod;
    private static Object networkSecurityPolicy;
    protected final ClientConnectionManager connManager;
    protected final HttpProcessor httpProcessor;
    protected final ConnectionKeepAliveStrategy keepAliveStrategy;
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());
    protected ManagedClientConnection managedConn;
    private int maxRedirects;
    protected final HttpParams params;
    private final AuthenticationHandler proxyAuthHandler;
    private final AuthState proxyAuthState;
    private int redirectCount;
    protected final RedirectHandler redirectHandler;
    protected final HttpRequestExecutor requestExec;
    protected final HttpRequestRetryHandler retryHandler;
    protected final ConnectionReuseStrategy reuseStrategy;
    protected final HttpRoutePlanner routePlanner;
    private final AuthenticationHandler targetAuthHandler;
    private final AuthState targetAuthState;
    private final UserTokenHandler userTokenHandler;

    public DefaultRequestDirector(HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectHandler redirectHandler, AuthenticationHandler authenticationHandler, AuthenticationHandler authenticationHandler2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        if (httpRequestExecutor == null) {
            throw new IllegalArgumentException("Request executor may not be null.");
        }
        if (clientConnectionManager == null) {
            throw new IllegalArgumentException("Client connection manager may not be null.");
        }
        if (connectionReuseStrategy == null) {
            throw new IllegalArgumentException("Connection reuse strategy may not be null.");
        }
        if (connectionKeepAliveStrategy == null) {
            throw new IllegalArgumentException("Connection keep alive strategy may not be null.");
        }
        if (httpRoutePlanner == null) {
            throw new IllegalArgumentException("Route planner may not be null.");
        }
        if (httpProcessor == null) {
            throw new IllegalArgumentException("HTTP protocol processor may not be null.");
        }
        if (httpRequestRetryHandler == null) {
            throw new IllegalArgumentException("HTTP request retry handler may not be null.");
        }
        if (redirectHandler == null) {
            throw new IllegalArgumentException("Redirect handler may not be null.");
        }
        if (authenticationHandler == null) {
            throw new IllegalArgumentException("Target authentication handler may not be null.");
        }
        if (authenticationHandler2 == null) {
            throw new IllegalArgumentException("Proxy authentication handler may not be null.");
        }
        if (userTokenHandler == null) {
            throw new IllegalArgumentException("User token handler may not be null.");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.requestExec = httpRequestExecutor;
        this.connManager = clientConnectionManager;
        this.reuseStrategy = connectionReuseStrategy;
        this.keepAliveStrategy = connectionKeepAliveStrategy;
        this.routePlanner = httpRoutePlanner;
        this.httpProcessor = httpProcessor;
        this.retryHandler = httpRequestRetryHandler;
        this.redirectHandler = redirectHandler;
        this.targetAuthHandler = authenticationHandler;
        this.proxyAuthHandler = authenticationHandler2;
        this.userTokenHandler = userTokenHandler;
        this.params = httpParams;
        this.managedConn = null;
        this.redirectCount = 0;
        this.maxRedirects = httpParams.getIntParameter("http.protocol.max-redirects", 100);
        this.targetAuthState = new AuthState();
        this.proxyAuthState = new AuthState();
    }

    private RequestWrapper wrapRequest(HttpRequest httpRequest) throws ProtocolException {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) httpRequest);
        }
        return new RequestWrapper(httpRequest);
    }

    protected void rewriteRequestURI(RequestWrapper requestWrapper, HttpRoute httpRoute) throws ProtocolException {
        try {
            URI uri = requestWrapper.getURI();
            if (httpRoute.getProxyHost() != null && !httpRoute.isTunnelled()) {
                if (!uri.isAbsolute()) {
                    requestWrapper.setURI(URIUtils.rewriteURI(uri, httpRoute.getTargetHost()));
                }
            } else if (uri.isAbsolute()) {
                requestWrapper.setURI(URIUtils.rewriteURI(uri, null));
            }
        } catch (URISyntaxException e) {
            throw new ProtocolException("Invalid URI: " + requestWrapper.getRequestLine().getUri(), e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x02b9, code lost:
        r18.managedConn.markReusable();
     */
    @Override // org.apache.http.client.RequestDirector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.http.HttpResponse execute(org.apache.http.HttpHost r19, org.apache.http.HttpRequest r20, org.apache.http.protocol.HttpContext r21) throws org.apache.http.HttpException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.client.DefaultRequestDirector.execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.protocol.HttpContext):org.apache.http.HttpResponse");
    }

    protected void releaseConnection() {
        try {
            this.managedConn.releaseConnection();
        } catch (IOException e) {
            this.log.debug("IOException releasing connection", e);
        }
        this.managedConn = null;
    }

    protected HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        String str;
        String str2;
        URI uri;
        if (httpHost == null) {
            httpHost = (HttpHost) httpRequest.getParams().getParameter("http.default-host");
        }
        if (httpHost == null) {
            String str3 = null;
            if ((httpRequest instanceof HttpUriRequest) && (uri = ((HttpUriRequest) httpRequest).getURI()) != null) {
                str3 = uri.getScheme();
                str2 = uri.getHost();
                str = uri.getPath();
            } else {
                str = null;
                str2 = null;
            }
            throw new IllegalStateException("Target host must not be null, or set in parameters. scheme=" + str3 + ", host=" + str2 + ", path=" + str);
        }
        return this.routePlanner.determineRoute(httpHost, httpRequest, httpContext);
    }

    protected void establishRoute(HttpRoute httpRoute, HttpContext httpContext) throws HttpException, IOException {
        int nextStep;
        BasicRouteDirector basicRouteDirector = new BasicRouteDirector();
        do {
            HttpRoute route = this.managedConn.getRoute();
            nextStep = basicRouteDirector.nextStep(httpRoute, route);
            switch (nextStep) {
                case -1:
                    throw new IllegalStateException("Unable to establish route.\nplanned = " + httpRoute + "\ncurrent = " + route);
                case 0:
                    break;
                case 1:
                case 2:
                    this.managedConn.open(httpRoute, httpContext, this.params);
                    continue;
                case 3:
                    boolean createTunnelToTarget = createTunnelToTarget(httpRoute, httpContext);
                    this.log.debug("Tunnel to target created.");
                    this.managedConn.tunnelTarget(createTunnelToTarget, this.params);
                    continue;
                case 4:
                    int hopCount = route.getHopCount() - 1;
                    boolean createTunnelToProxy = createTunnelToProxy(httpRoute, hopCount, httpContext);
                    this.log.debug("Tunnel to proxy created.");
                    this.managedConn.tunnelProxy(httpRoute.getHopTarget(hopCount), createTunnelToProxy, this.params);
                    continue;
                case 5:
                    this.managedConn.layerProtocol(httpContext, this.params);
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + nextStep + " from RouteDirector.");
            }
        } while (nextStep > 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0193  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean createTunnelToTarget(org.apache.http.conn.routing.HttpRoute r17, org.apache.http.protocol.HttpContext r18) throws org.apache.http.HttpException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.client.DefaultRequestDirector.createTunnelToTarget(org.apache.http.conn.routing.HttpRoute, org.apache.http.protocol.HttpContext):boolean");
    }

    protected boolean createTunnelToProxy(HttpRoute httpRoute, int i, HttpContext httpContext) throws HttpException, IOException {
        throw new UnsupportedOperationException("Proxy chains are not supported.");
    }

    protected HttpRequest createConnectRequest(HttpRoute httpRoute, HttpContext httpContext) {
        HttpHost targetHost = httpRoute.getTargetHost();
        String hostName = targetHost.getHostName();
        int port = targetHost.getPort();
        if (port < 0) {
            port = this.connManager.getSchemeRegistry().getScheme(targetHost.getSchemeName()).getDefaultPort();
        }
        StringBuilder sb = new StringBuilder(hostName.length() + 6);
        sb.append(hostName);
        sb.append(':');
        sb.append(Integer.toString(port));
        return new BasicHttpRequest("CONNECT", sb.toString(), HttpProtocolParams.getVersion(this.params));
    }

    protected RoutedRequest handleResponse(RoutedRequest routedRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        HttpRoute route = routedRequest.getRoute();
        HttpHost proxyHost = route.getProxyHost();
        RequestWrapper request = routedRequest.getRequest();
        HttpParams params = request.getParams();
        if (HttpClientParams.isRedirecting(params) && this.redirectHandler.isRedirectRequested(httpResponse, httpContext)) {
            int i = this.redirectCount;
            if (i >= this.maxRedirects) {
                throw new RedirectException("Maximum redirects (" + this.maxRedirects + ") exceeded");
            }
            this.redirectCount = i + 1;
            URI locationURI = this.redirectHandler.getLocationURI(httpResponse, httpContext);
            HttpHost httpHost = new HttpHost(locationURI.getHost(), locationURI.getPort(), locationURI.getScheme());
            HttpGet httpGet = new HttpGet(locationURI);
            httpGet.setHeaders(request.getOriginal().getAllHeaders());
            RequestWrapper requestWrapper = new RequestWrapper(httpGet);
            requestWrapper.setParams(params);
            HttpRoute determineRoute = determineRoute(httpHost, requestWrapper, httpContext);
            RoutedRequest routedRequest2 = new RoutedRequest(requestWrapper, determineRoute);
            if (this.log.isDebugEnabled()) {
                this.log.debug("Redirecting to '" + locationURI + "' via " + determineRoute);
            }
            return routedRequest2;
        }
        CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.getAttribute("http.auth.credentials-provider");
        if (credentialsProvider != null && HttpClientParams.isAuthenticating(params)) {
            if (!this.targetAuthHandler.isAuthenticationRequested(httpResponse, httpContext)) {
                this.targetAuthState.setAuthScope(null);
                if (!this.proxyAuthHandler.isAuthenticationRequested(httpResponse, httpContext)) {
                    this.proxyAuthState.setAuthScope(null);
                } else {
                    this.log.debug("Proxy requested authentication");
                    try {
                        processChallenges(this.proxyAuthHandler.getChallenges(httpResponse, httpContext), this.proxyAuthState, this.proxyAuthHandler, httpResponse, httpContext);
                    } catch (AuthenticationException e) {
                        if (this.log.isWarnEnabled()) {
                            this.log.warn("Authentication error: " + e.getMessage());
                            return null;
                        }
                    }
                    updateAuthState(this.proxyAuthState, proxyHost, credentialsProvider);
                    if (this.proxyAuthState.getCredentials() == null) {
                        return null;
                    }
                    return routedRequest;
                }
            } else {
                HttpHost httpHost2 = (HttpHost) httpContext.getAttribute("http.target_host");
                if (httpHost2 == null) {
                    httpHost2 = route.getTargetHost();
                }
                this.log.debug("Target requested authentication");
                try {
                    processChallenges(this.targetAuthHandler.getChallenges(httpResponse, httpContext), this.targetAuthState, this.targetAuthHandler, httpResponse, httpContext);
                } catch (AuthenticationException e2) {
                    if (this.log.isWarnEnabled()) {
                        this.log.warn("Authentication error: " + e2.getMessage());
                        return null;
                    }
                }
                updateAuthState(this.targetAuthState, httpHost2, credentialsProvider);
                if (this.targetAuthState.getCredentials() == null) {
                    return null;
                }
                return routedRequest;
            }
        }
        return null;
    }

    private void abortConnection() {
        ManagedClientConnection managedClientConnection = this.managedConn;
        if (managedClientConnection != null) {
            this.managedConn = null;
            try {
                managedClientConnection.abortConnection();
            } catch (IOException e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(e.getMessage(), e);
                }
            }
            try {
                managedClientConnection.releaseConnection();
            } catch (IOException e2) {
                this.log.debug("Error releasing connection", e2);
            }
        }
    }

    private void processChallenges(Map<String, Header> map, AuthState authState, AuthenticationHandler authenticationHandler, HttpResponse httpResponse, HttpContext httpContext) throws MalformedChallengeException, AuthenticationException {
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme == null) {
            authScheme = authenticationHandler.selectScheme(map, httpResponse, httpContext);
            authState.setAuthScheme(authScheme);
        }
        String schemeName = authScheme.getSchemeName();
        Header header = map.get(schemeName.toLowerCase(Locale.ENGLISH));
        if (header == null) {
            throw new AuthenticationException(schemeName + " authorization challenge expected, but not found");
        }
        authScheme.processChallenge(header);
        this.log.debug("Authorization challenge processed");
    }

    private void updateAuthState(AuthState authState, HttpHost httpHost, CredentialsProvider credentialsProvider) {
        if (!authState.isValid()) {
            return;
        }
        String hostName = httpHost.getHostName();
        int port = httpHost.getPort();
        if (port < 0) {
            port = this.connManager.getSchemeRegistry().getScheme(httpHost).getDefaultPort();
        }
        AuthScheme authScheme = authState.getAuthScheme();
        AuthScope authScope = new AuthScope(hostName, port, authScheme.getRealm(), authScheme.getSchemeName());
        if (this.log.isDebugEnabled()) {
            this.log.debug("Authentication scope: " + authScope);
        }
        Credentials credentials = authState.getCredentials();
        if (credentials == null) {
            credentials = credentialsProvider.getCredentials(authScope);
            if (this.log.isDebugEnabled()) {
                if (credentials != null) {
                    this.log.debug("Found credentials");
                } else {
                    this.log.debug("Credentials not found");
                }
            }
        } else if (authScheme.isComplete()) {
            this.log.debug("Authentication failed");
            credentials = null;
        }
        authState.setAuthScope(authScope);
        authState.setCredentials(credentials);
    }

    private static boolean isCleartextTrafficPermitted(String str) {
        Object obj;
        Method method;
        try {
            synchronized (DefaultRequestDirector.class) {
                if (cleartextTrafficPermittedMethod == null) {
                    Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
                    networkSecurityPolicy = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                    cleartextTrafficPermittedMethod = cls.getMethod("isCleartextTrafficPermitted", String.class);
                }
                obj = networkSecurityPolicy;
                method = cleartextTrafficPermittedMethod;
            }
            return ((Boolean) method.invoke(obj, str)).booleanValue();
        } catch (ReflectiveOperationException e) {
            return true;
        }
    }
}
