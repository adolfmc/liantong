package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultRedirectHandler implements RedirectHandler {
    private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());

    @Override // org.apache.http.client.RedirectHandler
    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        switch (httpResponse.getStatusLine().getStatusCode()) {
            case 301:
            case 302:
            case 303:
            case 307:
                return true;
            case 304:
            case 305:
            case 306:
            default:
                return false;
        }
    }

    @Override // org.apache.http.client.RedirectHandler
    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
        URI rewriteURI;
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        Header firstHeader = httpResponse.getFirstHeader("location");
        if (firstHeader == null) {
            throw new ProtocolException("Received redirect response " + httpResponse.getStatusLine() + " but no location header");
        }
        String value = firstHeader.getValue();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Redirect requested to location '" + value + "'");
        }
        try {
            URI uri = new URI(value);
            HttpParams params = httpResponse.getParams();
            if (!uri.isAbsolute()) {
                if (params.isParameterTrue("http.protocol.reject-relative-redirect")) {
                    throw new ProtocolException("Relative redirect location '" + uri + "' not allowed");
                }
                HttpHost httpHost = (HttpHost) httpContext.getAttribute("http.target_host");
                if (httpHost == null) {
                    throw new IllegalStateException("Target host not available in the HTTP context");
                }
                try {
                    uri = URIUtils.resolve(URIUtils.rewriteURI(new URI(((HttpRequest) httpContext.getAttribute("http.request")).getRequestLine().getUri()), httpHost, true), uri);
                } catch (URISyntaxException e) {
                    throw new ProtocolException(e.getMessage(), e);
                }
            }
            if (params.isParameterFalse("http.protocol.allow-circular-redirects")) {
                RedirectLocations redirectLocations = (RedirectLocations) httpContext.getAttribute("http.protocol.redirect-locations");
                if (redirectLocations == null) {
                    redirectLocations = new RedirectLocations();
                    httpContext.setAttribute("http.protocol.redirect-locations", redirectLocations);
                }
                if (uri.getFragment() != null) {
                    try {
                        rewriteURI = URIUtils.rewriteURI(uri, new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), true);
                    } catch (URISyntaxException e2) {
                        throw new ProtocolException(e2.getMessage(), e2);
                    }
                } else {
                    rewriteURI = uri;
                }
                if (redirectLocations.contains(rewriteURI)) {
                    throw new CircularRedirectException("Circular redirect to '" + rewriteURI + "'");
                }
                redirectLocations.add(rewriteURI);
            }
            return uri;
        } catch (URISyntaxException e3) {
            throw new ProtocolException("Invalid redirect URI: " + value, e3);
        }
    }
}
