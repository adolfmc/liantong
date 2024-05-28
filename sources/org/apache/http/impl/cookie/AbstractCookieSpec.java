package org.apache.http.impl.cookie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractCookieSpec implements CookieSpec {
    private final Map<String, CookieAttributeHandler> attribHandlerMap = new HashMap(10);

    public void registerAttribHandler(String str, CookieAttributeHandler cookieAttributeHandler) {
        if (str == null) {
            throw new IllegalArgumentException("Attribute name may not be null");
        }
        if (cookieAttributeHandler == null) {
            throw new IllegalArgumentException("Attribute handler may not be null");
        }
        this.attribHandlerMap.put(str, cookieAttributeHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CookieAttributeHandler findAttribHandler(String str) {
        return this.attribHandlerMap.get(str);
    }

    protected CookieAttributeHandler getAttribHandler(String str) {
        CookieAttributeHandler findAttribHandler = findAttribHandler(str);
        if (findAttribHandler == null) {
            throw new IllegalStateException("Handler not registered for " + str + " attribute.");
        }
        return findAttribHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<CookieAttributeHandler> getAttribHandlers() {
        return this.attribHandlerMap.values();
    }
}
