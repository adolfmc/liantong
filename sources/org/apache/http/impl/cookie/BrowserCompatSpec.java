package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BrowserCompatSpec extends CookieSpecBase {
    protected static final String[] DATE_PATTERNS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};
    private final String[] datepatterns;

    public BrowserCompatSpec(String[] strArr) {
        if (strArr != null) {
            this.datepatterns = (String[]) strArr.clone();
        } else {
            this.datepatterns = DATE_PATTERNS;
        }
        registerAttribHandler("path", new BasicPathHandler());
        registerAttribHandler("domain", new BasicDomainHandler());
        registerAttribHandler("max-age", new BasicMaxAgeHandler());
        registerAttribHandler("secure", new BasicSecureHandler());
        registerAttribHandler("comment", new BasicCommentHandler());
        registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
    }

    public BrowserCompatSpec() {
        this(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0082  */
    @Override // org.apache.http.cookie.CookieSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<org.apache.http.cookie.Cookie> parse(org.apache.http.Header r7, org.apache.http.cookie.CookieOrigin r8) throws org.apache.http.cookie.MalformedCookieException {
        /*
            r6 = this;
            if (r7 == 0) goto L93
            if (r8 == 0) goto L8b
            java.lang.String r0 = r7.getValue()
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r0.toLowerCase(r1)
            java.lang.String r2 = "expires="
            int r1 = r1.indexOf(r2)
            r3 = 1
            r4 = -1
            r5 = 0
            if (r1 == r4) goto L38
            int r2 = r2.length()
            int r1 = r1 + r2
            r2 = 59
            int r2 = r0.indexOf(r2, r1)
            if (r2 != r4) goto L2b
            int r2 = r0.length()
        L2b:
            java.lang.String r0 = r0.substring(r1, r2)     // Catch: org.apache.http.impl.cookie.DateParseException -> L37
            java.lang.String[] r1 = r6.datepatterns     // Catch: org.apache.http.impl.cookie.DateParseException -> L37
            org.apache.http.impl.cookie.DateUtils.parseDate(r0, r1)     // Catch: org.apache.http.impl.cookie.DateParseException -> L37
            r0 = r3
            goto L39
        L37:
            r0 = move-exception
        L38:
            r0 = r5
        L39:
            if (r0 == 0) goto L82
            org.apache.http.impl.cookie.NetscapeDraftHeaderParser r0 = org.apache.http.impl.cookie.NetscapeDraftHeaderParser.DEFAULT
            boolean r1 = r7 instanceof org.apache.http.FormattedHeader
            if (r1 == 0) goto L56
            org.apache.http.FormattedHeader r7 = (org.apache.http.FormattedHeader) r7
            org.apache.http.util.CharArrayBuffer r1 = r7.getBuffer()
            org.apache.http.message.ParserCursor r2 = new org.apache.http.message.ParserCursor
            int r7 = r7.getValuePos()
            int r4 = r1.length()
            r2.<init>(r7, r4)
            goto L71
        L56:
            java.lang.String r7 = r7.getValue()
            if (r7 == 0) goto L7a
            org.apache.http.util.CharArrayBuffer r1 = new org.apache.http.util.CharArrayBuffer
            int r2 = r7.length()
            r1.<init>(r2)
            r1.append(r7)
            org.apache.http.message.ParserCursor r2 = new org.apache.http.message.ParserCursor
            int r7 = r1.length()
            r2.<init>(r5, r7)
        L71:
            org.apache.http.HeaderElement[] r7 = new org.apache.http.HeaderElement[r3]
            org.apache.http.HeaderElement r0 = r0.parseHeader(r1, r2)
            r7[r5] = r0
            goto L86
        L7a:
            org.apache.http.cookie.MalformedCookieException r7 = new org.apache.http.cookie.MalformedCookieException
            java.lang.String r8 = "Header value is null"
            r7.<init>(r8)
            throw r7
        L82:
            org.apache.http.HeaderElement[] r7 = r7.getElements()
        L86:
            java.util.List r7 = r6.parse(r7, r8)
            return r7
        L8b:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Cookie origin may not be null"
            r7.<init>(r8)
            throw r7
        L93:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Header may not be null"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.cookie.BrowserCompatSpec.parse(org.apache.http.Header, org.apache.http.cookie.CookieOrigin):java.util.List");
    }

    @Override // org.apache.http.cookie.CookieSpec
    public List<Header> formatCookies(List<Cookie> list) {
        if (list == null) {
            throw new IllegalArgumentException("List of cookies may not be null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List of cookies may not be empty");
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 20);
        charArrayBuffer.append("Cookie");
        charArrayBuffer.append(": ");
        for (int i = 0; i < list.size(); i++) {
            Cookie cookie = list.get(i);
            if (i > 0) {
                charArrayBuffer.append("; ");
            }
            charArrayBuffer.append(cookie.getName());
            charArrayBuffer.append("=");
            String value = cookie.getValue();
            if (value != null) {
                charArrayBuffer.append(value);
            }
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new BufferedHeader(charArrayBuffer));
        return arrayList;
    }

    @Override // org.apache.http.cookie.CookieSpec
    public int getVersion() {
        return 0;
    }

    @Override // org.apache.http.cookie.CookieSpec
    public Header getVersionHeader() {
        return null;
    }
}
