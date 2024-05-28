package org.apache.http.conn.params;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ConnRouteParams implements ConnRoutePNames {
    public static final HttpHost NO_HOST;
    public static final HttpRoute NO_ROUTE;

    static {
        HttpHost httpHost = new HttpHost("127.0.0.255", 0, "no-host");
        NO_HOST = httpHost;
        NO_ROUTE = new HttpRoute(httpHost);
    }

    private ConnRouteParams() {
    }

    public static HttpHost getDefaultProxy(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        HttpHost httpHost = (HttpHost) httpParams.getParameter("http.route.default-proxy");
        if (httpHost != null && NO_HOST.equals(httpHost)) {
            return null;
        }
        return httpHost;
    }

    public static void setDefaultProxy(HttpParams httpParams, HttpHost httpHost) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        httpParams.setParameter("http.route.default-proxy", httpHost);
    }

    public static HttpRoute getForcedRoute(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        HttpRoute httpRoute = (HttpRoute) httpParams.getParameter("http.route.forced-route");
        if (httpRoute != null && NO_ROUTE.equals(httpRoute)) {
            return null;
        }
        return httpRoute;
    }

    public static void setForcedRoute(HttpParams httpParams, HttpRoute httpRoute) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        httpParams.setParameter("http.route.forced-route", httpRoute);
    }

    public static InetAddress getLocalAddress(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        return (InetAddress) httpParams.getParameter("http.route.local-address");
    }

    public static void setLocalAddress(HttpParams httpParams, InetAddress inetAddress) {
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        httpParams.setParameter("http.route.local-address", inetAddress);
    }
}
