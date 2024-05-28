package org.apache.http.conn.scheme;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class SchemeRegistry {
    private final Map<String, Scheme> registeredSchemes = new LinkedHashMap();

    public final synchronized Scheme getScheme(String str) {
        Scheme scheme;
        scheme = get(str);
        if (scheme == null) {
            throw new IllegalStateException("Scheme '" + str + "' not registered.");
        }
        return scheme;
    }

    public final synchronized Scheme getScheme(HttpHost httpHost) {
        if (httpHost == null) {
            throw new IllegalArgumentException("Host must not be null.");
        }
        return getScheme(httpHost.getSchemeName());
    }

    public final synchronized Scheme get(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        return this.registeredSchemes.get(str);
    }

    public final synchronized Scheme register(Scheme scheme) {
        if (scheme == null) {
            throw new IllegalArgumentException("Scheme must not be null.");
        }
        return this.registeredSchemes.put(scheme.getName(), scheme);
    }

    public final synchronized Scheme unregister(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        return this.registeredSchemes.remove(str);
    }

    public final synchronized List<String> getSchemeNames() {
        return new ArrayList(this.registeredSchemes.keySet());
    }

    public synchronized void setItems(Map<String, Scheme> map) {
        if (map == null) {
            return;
        }
        this.registeredSchemes.clear();
        this.registeredSchemes.putAll(map);
    }
}
