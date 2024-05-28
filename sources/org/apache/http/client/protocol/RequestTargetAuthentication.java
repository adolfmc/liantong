package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class RequestTargetAuthentication implements HttpRequestInterceptor {
    private final InterfaceC13042Log log = LogFactory.getLog(getClass());

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        AuthState authState;
        AuthScheme authScheme;
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        if (httpRequest.containsHeader("Authorization") || (authState = (AuthState) httpContext.getAttribute("http.auth.target-scope")) == null || (authScheme = authState.getAuthScheme()) == null) {
            return;
        }
        Credentials credentials = authState.getCredentials();
        if (credentials == null) {
            this.log.debug("User credentials not available");
        } else if (authState.getAuthScope() != null || !authScheme.isConnectionBased()) {
            try {
                httpRequest.addHeader(authScheme.authenticate(credentials, httpRequest));
            } catch (AuthenticationException e) {
                if (this.log.isErrorEnabled()) {
                    this.log.error("Authentication error: " + e.getMessage());
                }
            }
        }
    }
}
