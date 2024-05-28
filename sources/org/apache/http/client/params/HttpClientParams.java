package org.apache.http.client.params;

import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpClientParams {
    private HttpClientParams() {
    }

    public static boolean isRedirecting(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return httpParams.getBooleanParameter("http.protocol.handle-redirects", true);
    }

    public static void setRedirecting(HttpParams httpParams, boolean z) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setBooleanParameter("http.protocol.handle-redirects", z);
    }

    public static boolean isAuthenticating(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return httpParams.getBooleanParameter("http.protocol.handle-authentication", true);
    }

    public static void setAuthenticating(HttpParams httpParams, boolean z) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setBooleanParameter("http.protocol.handle-authentication", z);
    }

    public static String getCookiePolicy(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        String str = (String) httpParams.getParameter("http.protocol.cookie-policy");
        if (str == null) {
            return "best-match";
        }
        return str;
    }

    public static void setCookiePolicy(HttpParams httpParams, String str) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setParameter("http.protocol.cookie-policy", str);
    }
}
