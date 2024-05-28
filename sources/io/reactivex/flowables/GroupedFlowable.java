package io.reactivex.flowables;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;

    /* JADX INFO: Access modifiers changed from: protected */
    public GroupedFlowable(@Nullable K k) {
        this.key = k;
    }

    @Nullable
    public K getKey() {
        return this.key;
    }
}
