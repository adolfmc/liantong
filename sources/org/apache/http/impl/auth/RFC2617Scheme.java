package org.apache.http.impl.auth;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HeaderElement;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class RFC2617Scheme extends AuthSchemeBase {
    private Map<String, String> params;

    @Override // org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException {
        HeaderElement[] parseElements = BasicHeaderValueParser.DEFAULT.parseElements(charArrayBuffer, new ParserCursor(i, charArrayBuffer.length()));
        if (parseElements.length == 0) {
            throw new MalformedChallengeException("Authentication challenge is empty");
        }
        this.params = new HashMap(parseElements.length);
        for (HeaderElement headerElement : parseElements) {
            this.params.put(headerElement.getName(), headerElement.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<String, String> getParameters() {
        if (this.params == null) {
            this.params = new HashMap();
        }
        return this.params;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getParameter(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter name may not be null");
        }
        Map<String, String> map = this.params;
        if (map == null) {
            return null;
        }
        return map.get(str.toLowerCase(Locale.ENGLISH));
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getRealm() {
        return getParameter("realm");
    }
}
