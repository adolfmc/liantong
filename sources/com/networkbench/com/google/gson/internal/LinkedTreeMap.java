package com.networkbench.com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {

    /* renamed from: a */
    Comparator<? super K> f17343a;

    /* renamed from: b */
    C6703d<K, V> f17344b;

    /* renamed from: c */
    int f17345c;

    /* renamed from: d */
    int f17346d;

    /* renamed from: e */
    final C6703d<K, V> f17347e;
    private LinkedTreeMap<K, V>.C6698a entrySet;
    private LinkedTreeMap<K, V>.C6700b keySet;

    /* renamed from: f */
    static final /* synthetic */ boolean f17342f = !LinkedTreeMap.class.desiredAssertionStatus();
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: com.networkbench.com.google.gson.internal.LinkedTreeMap.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.f17345c = 0;
        this.f17346d = 0;
        this.f17347e = new C6703d<>();
        this.f17343a = comparator == null ? NATURAL_ORDER : comparator;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f17345c;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        C6703d<K, V> m8627a = m8627a(obj);
        if (m8627a != null) {
            return m8627a.f17362g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return m8627a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        C6703d<K, V> m8626a = m8626a((LinkedTreeMap<K, V>) k, true);
        V v2 = m8626a.f17362g;
        m8626a.f17362g = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f17344b = null;
        this.f17345c = 0;
        this.f17346d++;
        C6703d<K, V> c6703d = this.f17347e;
        c6703d.f17360e = c6703d;
        c6703d.f17359d = c6703d;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        C6703d<K, V> m8624b = m8624b(obj);
        if (m8624b != null) {
            return m8624b.f17362g;
        }
        return null;
    }

    /* renamed from: a */
    C6703d<K, V> m8626a(K k, boolean z) {
        int i;
        C6703d<K, V> c6703d;
        Comparator<? super K> comparator = this.f17343a;
        C6703d<K, V> c6703d2 = this.f17344b;
        if (c6703d2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(c6703d2.f17361f);
                } else {
                    i = comparator.compare(k, (K) c6703d2.f17361f);
                }
                if (i == 0) {
                    return c6703d2;
                }
                C6703d<K, V> c6703d3 = i < 0 ? c6703d2.f17357b : c6703d2.f17358c;
                if (c6703d3 == null) {
                    break;
                }
                c6703d2 = c6703d3;
            }
        } else {
            i = 0;
        }
        if (z) {
            C6703d<K, V> c6703d4 = this.f17347e;
            if (c6703d2 == null) {
                if (comparator == NATURAL_ORDER && !(k instanceof Comparable)) {
                    throw new ClassCastException(k.getClass().getName() + " is not Comparable");
                }
                c6703d = new C6703d<>(c6703d2, k, c6703d4, c6703d4.f17360e);
                this.f17344b = c6703d;
            } else {
                c6703d = new C6703d<>(c6703d2, k, c6703d4, c6703d4.f17360e);
                if (i < 0) {
                    c6703d2.f17357b = c6703d;
                } else {
                    c6703d2.f17358c = c6703d;
                }
                rebalance(c6703d2, true);
            }
            this.f17345c++;
            this.f17346d++;
            return c6703d;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    C6703d<K, V> m8627a(Object obj) {
        if (obj != 0) {
            try {
                return m8626a((LinkedTreeMap<K, V>) obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    C6703d<K, V> m8625a(Map.Entry<?, ?> entry) {
        C6703d<K, V> m8627a = m8627a(entry.getKey());
        if (m8627a != null && equal(m8627a.f17362g, entry.getValue())) {
            return m8627a;
        }
        return null;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    void m8628a(C6703d<K, V> c6703d, boolean z) {
        int i;
        if (z) {
            c6703d.f17360e.f17359d = c6703d.f17359d;
            c6703d.f17359d.f17360e = c6703d.f17360e;
        }
        C6703d<K, V> c6703d2 = c6703d.f17357b;
        C6703d<K, V> c6703d3 = c6703d.f17358c;
        C6703d<K, V> c6703d4 = c6703d.f17356a;
        int i2 = 0;
        if (c6703d2 != null && c6703d3 != null) {
            C6703d<K, V> m8619b = c6703d2.f17363h > c6703d3.f17363h ? c6703d2.m8619b() : c6703d3.m8620a();
            m8628a((C6703d) m8619b, false);
            C6703d<K, V> c6703d5 = c6703d.f17357b;
            if (c6703d5 != null) {
                i = c6703d5.f17363h;
                m8619b.f17357b = c6703d5;
                c6703d5.f17356a = m8619b;
                c6703d.f17357b = null;
            } else {
                i = 0;
            }
            C6703d<K, V> c6703d6 = c6703d.f17358c;
            if (c6703d6 != null) {
                i2 = c6703d6.f17363h;
                m8619b.f17358c = c6703d6;
                c6703d6.f17356a = m8619b;
                c6703d.f17358c = null;
            }
            m8619b.f17363h = Math.max(i, i2) + 1;
            replaceInParent(c6703d, m8619b);
            return;
        }
        if (c6703d2 != null) {
            replaceInParent(c6703d, c6703d2);
            c6703d.f17357b = null;
        } else if (c6703d3 != null) {
            replaceInParent(c6703d, c6703d3);
            c6703d.f17358c = null;
        } else {
            replaceInParent(c6703d, null);
        }
        rebalance(c6703d4, false);
        this.f17345c--;
        this.f17346d++;
    }

    /* renamed from: b */
    C6703d<K, V> m8624b(Object obj) {
        C6703d<K, V> m8627a = m8627a(obj);
        if (m8627a != null) {
            m8628a((C6703d) m8627a, true);
        }
        return m8627a;
    }

    private void replaceInParent(C6703d<K, V> c6703d, C6703d<K, V> c6703d2) {
        C6703d<K, V> c6703d3 = c6703d.f17356a;
        c6703d.f17356a = null;
        if (c6703d2 != null) {
            c6703d2.f17356a = c6703d3;
        }
        if (c6703d3 != null) {
            if (c6703d3.f17357b == c6703d) {
                c6703d3.f17357b = c6703d2;
                return;
            } else if (!f17342f && c6703d3.f17358c != c6703d) {
                throw new AssertionError();
            } else {
                c6703d3.f17358c = c6703d2;
                return;
            }
        }
        this.f17344b = c6703d2;
    }

    private void rebalance(C6703d<K, V> c6703d, boolean z) {
        while (c6703d != null) {
            C6703d<K, V> c6703d2 = c6703d.f17357b;
            C6703d<K, V> c6703d3 = c6703d.f17358c;
            int i = c6703d2 != null ? c6703d2.f17363h : 0;
            int i2 = c6703d3 != null ? c6703d3.f17363h : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                C6703d<K, V> c6703d4 = c6703d3.f17357b;
                C6703d<K, V> c6703d5 = c6703d3.f17358c;
                int i4 = (c6703d4 != null ? c6703d4.f17363h : 0) - (c6703d5 != null ? c6703d5.f17363h : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    rotateLeft(c6703d);
                } else if (!f17342f && i4 != 1) {
                    throw new AssertionError();
                } else {
                    rotateRight(c6703d3);
                    rotateLeft(c6703d);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                C6703d<K, V> c6703d6 = c6703d2.f17357b;
                C6703d<K, V> c6703d7 = c6703d2.f17358c;
                int i5 = (c6703d6 != null ? c6703d6.f17363h : 0) - (c6703d7 != null ? c6703d7.f17363h : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    rotateRight(c6703d);
                } else if (!f17342f && i5 != -1) {
                    throw new AssertionError();
                } else {
                    rotateLeft(c6703d2);
                    rotateRight(c6703d);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                c6703d.f17363h = i + 1;
                if (z) {
                    return;
                }
            } else if (!f17342f && i3 != -1 && i3 != 1) {
                throw new AssertionError();
            } else {
                c6703d.f17363h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            c6703d = c6703d.f17356a;
        }
    }

    private void rotateLeft(C6703d<K, V> c6703d) {
        C6703d<K, V> c6703d2 = c6703d.f17357b;
        C6703d<K, V> c6703d3 = c6703d.f17358c;
        C6703d<K, V> c6703d4 = c6703d3.f17357b;
        C6703d<K, V> c6703d5 = c6703d3.f17358c;
        c6703d.f17358c = c6703d4;
        if (c6703d4 != null) {
            c6703d4.f17356a = c6703d;
        }
        replaceInParent(c6703d, c6703d3);
        c6703d3.f17357b = c6703d;
        c6703d.f17356a = c6703d3;
        c6703d.f17363h = Math.max(c6703d2 != null ? c6703d2.f17363h : 0, c6703d4 != null ? c6703d4.f17363h : 0) + 1;
        c6703d3.f17363h = Math.max(c6703d.f17363h, c6703d5 != null ? c6703d5.f17363h : 0) + 1;
    }

    private void rotateRight(C6703d<K, V> c6703d) {
        C6703d<K, V> c6703d2 = c6703d.f17357b;
        C6703d<K, V> c6703d3 = c6703d.f17358c;
        C6703d<K, V> c6703d4 = c6703d2.f17357b;
        C6703d<K, V> c6703d5 = c6703d2.f17358c;
        c6703d.f17357b = c6703d5;
        if (c6703d5 != null) {
            c6703d5.f17356a = c6703d;
        }
        replaceInParent(c6703d, c6703d2);
        c6703d2.f17358c = c6703d;
        c6703d.f17356a = c6703d2;
        c6703d.f17363h = Math.max(c6703d3 != null ? c6703d3.f17363h : 0, c6703d5 != null ? c6703d5.f17363h : 0) + 1;
        c6703d2.f17363h = Math.max(c6703d.f17363h, c6703d4 != null ? c6703d4.f17363h : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.C6698a c6698a = this.entrySet;
        if (c6698a != null) {
            return c6698a;
        }
        LinkedTreeMap<K, V>.C6698a c6698a2 = new C6698a();
        this.entrySet = c6698a2;
        return c6698a2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedTreeMap<K, V>.C6700b c6700b = this.keySet;
        if (c6700b != null) {
            return c6700b;
        }
        LinkedTreeMap<K, V>.C6700b c6700b2 = new C6700b();
        this.keySet = c6700b2;
        return c6700b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedTreeMap$d */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6703d<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        C6703d<K, V> f17356a;

        /* renamed from: b */
        C6703d<K, V> f17357b;

        /* renamed from: c */
        C6703d<K, V> f17358c;

        /* renamed from: d */
        C6703d<K, V> f17359d;

        /* renamed from: e */
        C6703d<K, V> f17360e;

        /* renamed from: f */
        final K f17361f;

        /* renamed from: g */
        V f17362g;

        /* renamed from: h */
        int f17363h;

        C6703d() {
            this.f17361f = null;
            this.f17360e = this;
            this.f17359d = this;
        }

        C6703d(C6703d<K, V> c6703d, K k, C6703d<K, V> c6703d2, C6703d<K, V> c6703d3) {
            this.f17356a = c6703d;
            this.f17361f = k;
            this.f17363h = 1;
            this.f17359d = c6703d2;
            this.f17360e = c6703d3;
            c6703d3.f17359d = this;
            c6703d2.f17360e = this;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f17361f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f17362g;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f17362g;
            this.f17362g = v;
            return v2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                K k = this.f17361f;
                if (k == null) {
                    if (entry.getKey() != null) {
                        return false;
                    }
                } else if (!k.equals(entry.getKey())) {
                    return false;
                }
                V v = this.f17362g;
                if (v == null) {
                    if (entry.getValue() != null) {
                        return false;
                    }
                } else if (!v.equals(entry.getValue())) {
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f17361f;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f17362g;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        public String toString() {
            return this.f17361f + "=" + this.f17362g;
        }

        /* renamed from: a */
        public C6703d<K, V> m8620a() {
            C6703d<K, V> c6703d = this;
            for (C6703d<K, V> c6703d2 = this.f17357b; c6703d2 != null; c6703d2 = c6703d2.f17357b) {
                c6703d = c6703d2;
            }
            return c6703d;
        }

        /* renamed from: b */
        public C6703d<K, V> m8619b() {
            C6703d<K, V> c6703d = this;
            for (C6703d<K, V> c6703d2 = this.f17358c; c6703d2 != null; c6703d2 = c6703d2.f17358c) {
                c6703d = c6703d2;
            }
            return c6703d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedTreeMap$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public abstract class AbstractC6702c<T> implements Iterator<T> {

        /* renamed from: b */
        C6703d<K, V> f17352b;

        /* renamed from: c */
        C6703d<K, V> f17353c;

        /* renamed from: d */
        int f17354d;

        private AbstractC6702c() {
            this.f17352b = LinkedTreeMap.this.f17347e.f17359d;
            this.f17353c = null;
            this.f17354d = LinkedTreeMap.this.f17346d;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f17352b != LinkedTreeMap.this.f17347e;
        }

        /* renamed from: b */
        final C6703d<K, V> m8621b() {
            C6703d<K, V> c6703d = this.f17352b;
            if (c6703d == LinkedTreeMap.this.f17347e) {
                throw new NoSuchElementException();
            }
            if (LinkedTreeMap.this.f17346d != this.f17354d) {
                throw new ConcurrentModificationException();
            }
            this.f17352b = c6703d.f17359d;
            this.f17353c = c6703d;
            return c6703d;
        }

        @Override // java.util.Iterator
        public final void remove() {
            C6703d<K, V> c6703d = this.f17353c;
            if (c6703d == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.m8628a((C6703d) c6703d, true);
            this.f17353c = null;
            this.f17354d = LinkedTreeMap.this.f17346d;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedTreeMap$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C6698a extends AbstractSet<Map.Entry<K, V>> {
        C6698a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.f17345c;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedTreeMap<K, V>.AbstractC6702c<Map.Entry<K, V>>() { // from class: com.networkbench.com.google.gson.internal.LinkedTreeMap.a.1
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                @Override // java.util.Iterator
                /* renamed from: a */
                public Map.Entry<K, V> next() {
                    return m8621b();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedTreeMap.this.m8625a((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            C6703d<K, V> m8625a;
            if ((obj instanceof Map.Entry) && (m8625a = LinkedTreeMap.this.m8625a((Map.Entry) obj)) != null) {
                LinkedTreeMap.this.m8628a((C6703d) m8625a, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedTreeMap$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C6700b extends AbstractSet<K> {
        C6700b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.f17345c;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedTreeMap<K, V>.AbstractC6702c<K>() { // from class: com.networkbench.com.google.gson.internal.LinkedTreeMap.b.1
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return m8621b().f17361f;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedTreeMap.this.m8624b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
