package org.apache.http.impl.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicScheme extends RFC2617Scheme {
    private boolean complete = false;

    @Override // org.apache.http.auth.AuthScheme
    public String getSchemeName() {
        return "basic";
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase, org.apache.http.auth.AuthScheme
    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        this.complete = true;
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isComplete() {
        return this.complete;
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isConnectionBased() {
        return false;
    }

    @Override // org.apache.http.auth.AuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        return authenticate(credentials, AuthParams.getCredentialCharset(httpRequest.getParams()), isProxy());
    }

    public static Header authenticate(Credentials credentials, String str, boolean z) {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        if (str == null) {
            throw new IllegalArgumentException("charset may not be null");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(credentials.getUserPrincipal().getName());
        sb.append(":");
        sb.append(credentials.getPassword() == null ? "null" : credentials.getPassword());
        byte[] encodeBase64 = Base64.encodeBase64(EncodingUtils.getBytes(sb.toString(), str));
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        if (z) {
            charArrayBuffer.append("Proxy-Authorization");
        } else {
            charArrayBuffer.append("Authorization");
        }
        charArrayBuffer.append(": Basic ");
        charArrayBuffer.append(encodeBase64, 0, encodeBase64.length);
        return new BufferedHeader(charArrayBuffer);
    }
}
