package org.apache.http.client.protocol;

import java.util.List;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ClientContextConfigurer implements ClientContext {
    private final HttpContext context;

    public ClientContextConfigurer(HttpContext httpContext) {
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        this.context = httpContext;
    }

    public void setCookieSpecRegistry(CookieSpecRegistry cookieSpecRegistry) {
        this.context.setAttribute("http.cookiespec-registry", cookieSpecRegistry);
    }

    public void setAuthSchemeRegistry(AuthSchemeRegistry authSchemeRegistry) {
        this.context.setAttribute("http.authscheme-registry", authSchemeRegistry);
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.context.setAttribute("http.cookie-store", cookieStore);
    }

    public void setCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.context.setAttribute("http.auth.credentials-provider", credentialsProvider);
    }

    public void setAuthSchemePref(List<String> list) {
        this.context.setAttribute("http.auth.scheme-pref", list);
    }
}
