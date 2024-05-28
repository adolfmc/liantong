package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMarkers;

/* JADX INFO: Add missing generic type declarations: [V] */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: AbstractMap.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1890d2 = {"kotlin/collections/AbstractMap$values$1$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class AbstractMap$values$1$iterator$1<V> implements Iterator<V>, KMarkers {
    final /* synthetic */ Iterator $entryIterator;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMap$values$1$iterator$1(Iterator it) {
        this.$entryIterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.$entryIterator.hasNext();
    }

    @Override // java.util.Iterator
    public V next() {
        return (V) ((Map.Entry) this.$entryIterator.next()).getValue();
    }
}
