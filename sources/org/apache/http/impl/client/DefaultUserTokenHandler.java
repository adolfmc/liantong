package org.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultUserTokenHandler implements UserTokenHandler {
    @Override // org.apache.http.client.UserTokenHandler
    public Object getUserToken(HttpContext httpContext) {
        Principal principal;
        SSLSession sSLSession;
        AuthState authState = (AuthState) httpContext.getAttribute("http.auth.target-scope");
        if (authState == null) {
            principal = null;
        } else {
            principal = getAuthPrincipal(authState);
            if (principal == null) {
                principal = getAuthPrincipal((AuthState) httpContext.getAttribute("http.auth.proxy-scope"));
            }
        }
        if (principal == null) {
            ManagedClientConnection managedClientConnection = (ManagedClientConnection) httpContext.getAttribute("http.connection");
            if (managedClientConnection.isOpen() && (sSLSession = managedClientConnection.getSSLSession()) != null) {
                return sSLSession.getLocalPrincipal();
            }
            return principal;
        }
        return principal;
    }

    private static Principal getAuthPrincipal(AuthState authState) {
        Credentials credentials;
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme != null && authScheme.isComplete() && authScheme.isConnectionBased() && (credentials = authState.getCredentials()) != null) {
            return credentials.getUserPrincipal();
        }
        return null;
    }
}
