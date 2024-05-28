package com.alibaba.android.arouter.base;

import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class UniqueKeyTreeMap<K, V> extends TreeMap<K, V> {
    private String tipText;

    public UniqueKeyTreeMap(String str) {
        this.tipText = str;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (containsKey(k)) {
            throw new RuntimeException(String.format(this.tipText, k));
        }
        return (V) super.put(k, v);
    }
}
