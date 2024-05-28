package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class CookieIdentityComparator implements Serializable, Comparator<Cookie> {
    private static final long serialVersionUID = 4466565437490631532L;

    @Override // java.util.Comparator
    public int compare(Cookie cookie, Cookie cookie2) {
        int compareTo = cookie.getName().compareTo(cookie2.getName());
        if (compareTo == 0) {
            String domain = cookie.getDomain();
            String str = "";
            if (domain == null) {
                domain = "";
            }
            String domain2 = cookie2.getDomain();
            if (domain2 != null) {
                str = domain2;
            }
            return domain.compareToIgnoreCase(str);
        }
        return compareTo;
    }
}
