package org.apache.http.impl.client;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class RedirectLocations {
    private final Set<URI> uris = new HashSet();

    public boolean contains(URI uri) {
        return this.uris.contains(uri);
    }

    public void add(URI uri) {
        this.uris.add(uri);
    }

    public boolean remove(URI uri) {
        return this.uris.remove(uri);
    }
}
