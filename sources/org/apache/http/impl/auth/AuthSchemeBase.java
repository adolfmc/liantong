package org.apache.http.impl.auth;

import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AuthSchemeBase implements AuthScheme {
    private boolean proxy;

    protected abstract void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException;

    @Override // org.apache.http.auth.AuthScheme
    public void processChallenge(Header header) throws MalformedChallengeException {
        CharArrayBuffer charArrayBuffer;
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null");
        }
        String name = header.getName();
        int i = 0;
        if (name.equalsIgnoreCase("WWW-Authenticate")) {
            this.proxy = false;
        } else if (name.equalsIgnoreCase("Proxy-Authenticate")) {
            this.proxy = true;
        } else {
            throw new MalformedChallengeException("Unexpected header name: " + name);
        }
        if (header instanceof FormattedHeader) {
            FormattedHeader formattedHeader = (FormattedHeader) header;
            charArrayBuffer = formattedHeader.getBuffer();
            i = formattedHeader.getValuePos();
        } else {
            String value = header.getValue();
            if (value == null) {
                throw new MalformedChallengeException("Header value is null");
            }
            charArrayBuffer = new CharArrayBuffer(value.length());
            charArrayBuffer.append(value);
        }
        while (i < charArrayBuffer.length() && HTTP.isWhitespace(charArrayBuffer.charAt(i))) {
            i++;
        }
        int i2 = i;
        while (i2 < charArrayBuffer.length() && !HTTP.isWhitespace(charArrayBuffer.charAt(i2))) {
            i2++;
        }
        String substring = charArrayBuffer.substring(i, i2);
        if (!substring.equalsIgnoreCase(getSchemeName())) {
            throw new MalformedChallengeException("Invalid scheme identifier: " + substring);
        }
        parseChallenge(charArrayBuffer, i2, charArrayBuffer.length());
    }

    public boolean isProxy() {
        return this.proxy;
    }
}
