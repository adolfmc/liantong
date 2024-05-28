package szcom.googlecode.mp4parser.util;

import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class RangeStartMap<K extends Comparable, V> implements Map<K, V> {
    TreeMap<K, V> base = new TreeMap<>((Comparator<? super K>) new Comparator<K>() { // from class: szcom.googlecode.mp4parser.util.RangeStartMap.1
        @Override // java.util.Comparator
        public int compare(K k, K k2) {
            return -k.compareTo(k2);
        }
    });

    public RangeStartMap() {
    }

    public RangeStartMap(K k, V v) {
        put((RangeStartMap<K, V>) k, (K) v);
    }

    @Override // java.util.Map
    public void clear() {
        this.base.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.base.get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return this.base.entrySet();
    }

    @Override // java.util.Map
    public V get(Object obj) {
        K next;
        if (obj instanceof Comparable) {
            Comparable comparable = (Comparable) obj;
            if (isEmpty()) {
                return null;
            }
            Iterator<K> it = this.base.keySet().iterator();
            do {
                next = it.next();
                if (!it.hasNext()) {
                    break;
                }
            } while (comparable.compareTo(next) < 0);
            return this.base.get(next);
        }
        return null;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.base.isEmpty();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.base.keySet();
    }

    public V put(K k, V v) {
        return this.base.put(k, v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((RangeStartMap<K, V>) ((Comparable) obj), (Comparable) obj2);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        this.base.putAll(map);
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        K next;
        if (obj instanceof Comparable) {
            Comparable comparable = (Comparable) obj;
            if (isEmpty()) {
                return null;
            }
            Iterator<K> it = this.base.keySet().iterator();
            do {
                next = it.next();
                if (!it.hasNext()) {
                    break;
                }
            } while (comparable.compareTo(next) < 0);
            return this.base.remove(next);
        }
        return null;
    }

    @Override // java.util.Map
    public int size() {
        return this.base.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.base.values();
    }
}
