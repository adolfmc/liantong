package com.baidu.cloud.frameprocessor.p133ar;

import android.support.annotation.NonNull;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.frameprocessor.ar.SafeConcurrentHashMap */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SafeConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public V put(@NonNull K k, @NonNull V v) {
        if (k == null) {
            return null;
        }
        if (v == null) {
            return remove(k);
        }
        return (V) super.put(k, v);
    }
}
