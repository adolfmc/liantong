package org.greenrobot.essentials.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Multimap<K, V> extends AbstractMultimap<K, V, List<V>> {
    private final ListType listType;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum ListType {
        REGULAR,
        THREAD_SAFE,
        LINKED
    }

    public static <K, V> Multimap<K, V> create() {
        return create(ListType.REGULAR);
    }

    public static <K, V> Multimap<K, V> create(ListType listType) {
        return new Multimap<>(new HashMap(), listType);
    }

    protected Multimap(Map<K, List<V>> map, ListType listType) {
        super(map);
        this.listType = listType;
        if (listType == null) {
            throw new IllegalArgumentException("List type may not be null");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.essentials.collections.AbstractMultimap
    public List<V> createNewCollection() {
        switch (this.listType) {
            case REGULAR:
                return new ArrayList();
            case THREAD_SAFE:
                return new CopyOnWriteArrayList();
            case LINKED:
                return new LinkedList();
            default:
                throw new IllegalStateException("Unknown list type: " + this.listType);
        }
    }
}
