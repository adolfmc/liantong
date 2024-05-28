package org.apache.http.conn.params;

import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class ConnManagerParams implements ConnManagerPNames {
    private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute() { // from class: org.apache.http.conn.params.ConnManagerParams.1
        @Override // org.apache.http.conn.params.ConnPerRoute
        public int getMaxForRoute(HttpRoute httpRoute) {
            return 2;
        }
    };
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

    public static long getTimeout(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return httpParams.getLongParameter("http.conn-manager.timeout", 0L);
    }

    public static void setTimeout(HttpParams httpParams, long j) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setLongParameter("http.conn-manager.timeout", j);
    }

    public static void setMaxConnectionsPerRoute(HttpParams httpParams, ConnPerRoute connPerRoute) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters must not be null.");
        }
        httpParams.setParameter("http.conn-manager.max-per-route", connPerRoute);
    }

    public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters must not be null.");
        }
        ConnPerRoute connPerRoute = (ConnPerRoute) httpParams.getParameter("http.conn-manager.max-per-route");
        if (connPerRoute == null) {
            return DEFAULT_CONN_PER_ROUTE;
        }
        return connPerRoute;
    }

    public static void setMaxTotalConnections(HttpParams httpParams, int i) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters must not be null.");
        }
        httpParams.setIntParameter("http.conn-manager.max-total", i);
    }

    public static int getMaxTotalConnections(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters must not be null.");
        }
        return httpParams.getIntParameter("http.conn-manager.max-total", 20);
    }
}
