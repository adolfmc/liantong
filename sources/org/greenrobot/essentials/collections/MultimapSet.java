package org.greenrobot.essentials.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MultimapSet<K, V> extends AbstractMultimap<K, V, Set<V>> {
    private final SetType setType;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum SetType {
        REGULAR,
        THREAD_SAFE
    }

    public static <K, V> MultimapSet<K, V> create() {
        return create(SetType.REGULAR);
    }

    public static <K, V> MultimapSet<K, V> create(SetType setType) {
        return new MultimapSet<>(new HashMap(), setType);
    }

    protected MultimapSet(Map<K, Set<V>> map, SetType setType) {
        super(map);
        this.setType = setType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.essentials.collections.AbstractMultimap
    public Set<V> createNewCollection() {
        switch (this.setType) {
            case REGULAR:
                return new HashSet();
            case THREAD_SAFE:
                return new CopyOnWriteArraySet();
            default:
                throw new IllegalStateException("Unknown set type: " + this.setType);
        }
    }
}
