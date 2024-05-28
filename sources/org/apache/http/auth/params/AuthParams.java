package org.apache.http.auth.params;

import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class AuthParams {
    private AuthParams() {
    }

    public static String getCredentialCharset(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        String str = (String) httpParams.getParameter("http.auth.credential-charset");
        if (str == null) {
            return "US-ASCII";
        }
        return str;
    }

    public static void setCredentialCharset(HttpParams httpParams, String str) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setParameter("http.auth.credential-charset", str);
    }
}
