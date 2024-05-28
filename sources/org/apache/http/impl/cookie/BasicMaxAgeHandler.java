package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler {
    @Override // org.apache.http.cookie.CookieAttributeHandler
    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        if (setCookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        }
        if (str == null) {
            throw new MalformedCookieException("Missing value for max-age attribute");
        }
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 0) {
                throw new MalformedCookieException("Negative max-age attribute: " + str);
            }
            setCookie.setExpiryDate(new Date(System.currentTimeMillis() + (parseInt * 1000)));
        } catch (NumberFormatException e) {
            throw new MalformedCookieException("Invalid max-age attribute: " + str);
        }
    }
}
