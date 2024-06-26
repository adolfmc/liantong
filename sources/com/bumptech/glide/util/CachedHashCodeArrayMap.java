package com.bumptech.glide.util;

import android.support.p083v4.util.ArrayMap;
import android.support.p083v4.util.SimpleArrayMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CachedHashCodeArrayMap<K, V> extends ArrayMap<K, V> {
    private int hashCode;

    @Override // android.support.p083v4.util.SimpleArrayMap, java.util.Map
    public void clear() {
        this.hashCode = 0;
        super.clear();
    }

    @Override // android.support.p083v4.util.SimpleArrayMap
    public V setValueAt(int i, V v) {
        this.hashCode = 0;
        return (V) super.setValueAt(i, v);
    }

    @Override // android.support.p083v4.util.SimpleArrayMap, java.util.Map
    public V put(K k, V v) {
        this.hashCode = 0;
        return (V) super.put(k, v);
    }

    @Override // android.support.p083v4.util.SimpleArrayMap
    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this.hashCode = 0;
        super.putAll(simpleArrayMap);
    }

    @Override // android.support.p083v4.util.SimpleArrayMap
    public V removeAt(int i) {
        this.hashCode = 0;
        return (V) super.removeAt(i);
    }

    @Override // android.support.p083v4.util.SimpleArrayMap, java.util.Map
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = super.hashCode();
        }
        return this.hashCode;
    }
}
