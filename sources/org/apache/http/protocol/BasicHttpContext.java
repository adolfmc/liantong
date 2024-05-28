package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicHttpContext implements HttpContext {
    private Map map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext httpContext) {
        this.map = null;
        this.parentContext = httpContext;
    }

    @Override // org.apache.http.protocol.HttpContext
    public Object getAttribute(String str) {
        HttpContext httpContext;
        if (str == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        Object obj = null;
        Map map = this.map;
        if (map != null) {
            obj = map.get(str);
        }
        if (obj == null && (httpContext = this.parentContext) != null) {
            return httpContext.getAttribute(str);
        }
        return obj;
    }

    @Override // org.apache.http.protocol.HttpContext
    public void setAttribute(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(str, obj);
    }

    @Override // org.apache.http.protocol.HttpContext
    public Object removeAttribute(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        Map map = this.map;
        if (map != null) {
            return map.remove(str);
        }
        return null;
    }
}
