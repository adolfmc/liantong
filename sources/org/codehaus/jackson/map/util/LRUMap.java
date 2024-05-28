package org.codehaus.jackson.map.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LRUMap<K, V> extends LinkedHashMap<K, V> {
    protected final int _maxEntries;

    public LRUMap(int i, int i2) {
        super(i, 0.8f, true);
        this._maxEntries = i2;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > this._maxEntries;
    }
}
