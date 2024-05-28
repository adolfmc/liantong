package org.apache.http.impl.cookie;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class NetscapeDraftSpecFactory implements CookieSpecFactory {
    @Override // org.apache.http.cookie.CookieSpecFactory
    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams != null) {
            return new NetscapeDraftSpec((String[]) httpParams.getParameter("http.protocol.cookie-datepatterns"));
        }
        return new NetscapeDraftSpec();
    }
}
