package org.apache.http.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.ProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class RequestAddCookies implements HttpRequestInterceptor {
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        URI uri;
        Header versionHeader;
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        CookieStore cookieStore = (CookieStore) httpContext.getAttribute("http.cookie-store");
        if (cookieStore == null) {
            this.log.info("Cookie store not available in HTTP context");
            return;
        }
        CookieSpecRegistry cookieSpecRegistry = (CookieSpecRegistry) httpContext.getAttribute("http.cookiespec-registry");
        if (cookieSpecRegistry == null) {
            this.log.info("CookieSpec registry not available in HTTP context");
            return;
        }
        HttpHost httpHost = (HttpHost) httpContext.getAttribute("http.target_host");
        if (httpHost == null) {
            throw new IllegalStateException("Target host not specified in HTTP context");
        }
        ManagedClientConnection managedClientConnection = (ManagedClientConnection) httpContext.getAttribute("http.connection");
        if (managedClientConnection == null) {
            throw new IllegalStateException("Client connection not specified in HTTP context");
        }
        String cookiePolicy = HttpClientParams.getCookiePolicy(httpRequest.getParams());
        if (this.log.isDebugEnabled()) {
            this.log.debug("CookieSpec selected: " + cookiePolicy);
        }
        if (httpRequest instanceof HttpUriRequest) {
            uri = ((HttpUriRequest) httpRequest).getURI();
        } else {
            try {
                uri = new URI(httpRequest.getRequestLine().getUri());
            } catch (URISyntaxException e) {
                throw new ProtocolException("Invalid request URI: " + httpRequest.getRequestLine().getUri(), e);
            }
        }
        String hostName = httpHost.getHostName();
        int port = httpHost.getPort();
        if (port < 0) {
            port = managedClientConnection.getRemotePort();
        }
        CookieOrigin cookieOrigin = new CookieOrigin(hostName, port, uri.getPath(), managedClientConnection.isSecure());
        CookieSpec cookieSpec = cookieSpecRegistry.getCookieSpec(cookiePolicy, httpRequest.getParams());
        ArrayList<Cookie> arrayList = new ArrayList(cookieStore.getCookies());
        ArrayList<Cookie> arrayList2 = new ArrayList();
        for (Cookie cookie : arrayList) {
            if (cookieSpec.match(cookie, cookieOrigin)) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Cookie " + cookie + " match " + cookieOrigin);
                }
                arrayList2.add(cookie);
            }
        }
        if (!arrayList2.isEmpty()) {
            for (Header header : cookieSpec.formatCookies(arrayList2)) {
                httpRequest.addHeader(header);
            }
        }
        int version = cookieSpec.getVersion();
        if (version > 0) {
            boolean z = false;
            for (Cookie cookie2 : arrayList2) {
                if (version != cookie2.getVersion()) {
                    z = true;
                }
            }
            if (z && (versionHeader = cookieSpec.getVersionHeader()) != null) {
                httpRequest.addHeader(versionHeader);
            }
        }
        httpContext.setAttribute("http.cookie-spec", cookieSpec);
        httpContext.setAttribute("http.cookie-origin", cookieOrigin);
    }
}
