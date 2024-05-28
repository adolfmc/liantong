package org.bouncycastle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CollectionStore<T> implements Iterable<T>, Store<T> {
    private Collection<T> _local;

    public CollectionStore(Collection<T> collection) {
        this._local = new ArrayList(collection);
    }

    @Override // org.bouncycastle.util.Store
    public Collection<T> getMatches(Selector<T> selector) {
        if (selector == null) {
            return new ArrayList(this._local);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : this._local) {
            if (selector.match(t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<T> iterator() {
        return getMatches(null).iterator();
    }
}
