package com.networkbench.com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
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
public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {

    /* renamed from: a */
    Comparator<? super K> f17314a;

    /* renamed from: b */
    C6696f<K, V>[] f17315b;

    /* renamed from: c */
    final C6696f<K, V> f17316c;

    /* renamed from: d */
    int f17317d;

    /* renamed from: e */
    int f17318e;
    private LinkedHashTreeMap<K, V>.C6691c entrySet;

    /* renamed from: f */
    int f17319f;
    private LinkedHashTreeMap<K, V>.C6693d keySet;

    /* renamed from: g */
    static final /* synthetic */ boolean f17313g = !LinkedHashTreeMap.class.desiredAssertionStatus();
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: com.networkbench.com.google.gson.internal.LinkedHashTreeMap.1
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    private static int secondaryHash(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.f17317d = 0;
        this.f17318e = 0;
        this.f17314a = comparator == null ? NATURAL_ORDER : comparator;
        this.f17316c = new C6696f<>();
        this.f17315b = new C6696f[16];
        C6696f<K, V>[] c6696fArr = this.f17315b;
        this.f17319f = (c6696fArr.length / 2) + (c6696fArr.length / 4);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f17317d;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        C6696f<K, V> m8643a = m8643a(obj);
        if (m8643a != null) {
            return m8643a.f17340h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return m8643a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        C6696f<K, V> m8642a = m8642a((LinkedHashTreeMap<K, V>) k, true);
        V v2 = m8642a.f17340h;
        m8642a.f17340h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.f17315b, (Object) null);
        this.f17317d = 0;
        this.f17318e++;
        C6696f<K, V> c6696f = this.f17316c;
        C6696f<K, V> c6696f2 = c6696f.f17336d;
        while (c6696f2 != c6696f) {
            C6696f<K, V> c6696f3 = c6696f2.f17336d;
            c6696f2.f17337e = null;
            c6696f2.f17336d = null;
            c6696f2 = c6696f3;
        }
        c6696f.f17337e = c6696f;
        c6696f.f17336d = c6696f;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        C6696f<K, V> m8639b = m8639b(obj);
        if (m8639b != null) {
            return m8639b.f17340h;
        }
        return null;
    }

    /* renamed from: a */
    C6696f<K, V> m8642a(K k, boolean z) {
        C6696f<K, V> c6696f;
        int i;
        C6696f<K, V> c6696f2;
        int compare;
        Comparator<? super K> comparator = this.f17314a;
        C6696f<K, V>[] c6696fArr = this.f17315b;
        int secondaryHash = secondaryHash(k.hashCode());
        int length = (c6696fArr.length - 1) & secondaryHash;
        C6696f<K, V> c6696f3 = c6696fArr[length];
        if (c6696f3 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    compare = comparable.compareTo(c6696f3.f17338f);
                } else {
                    compare = comparator.compare(k, (K) c6696f3.f17338f);
                }
                if (compare == 0) {
                    return c6696f3;
                }
                C6696f<K, V> c6696f4 = compare < 0 ? c6696f3.f17334b : c6696f3.f17335c;
                if (c6696f4 == null) {
                    c6696f = c6696f3;
                    i = compare;
                    break;
                }
                c6696f3 = c6696f4;
            }
        } else {
            c6696f = c6696f3;
            i = 0;
        }
        if (z) {
            C6696f<K, V> c6696f5 = this.f17316c;
            if (c6696f == null) {
                if (comparator == NATURAL_ORDER && !(k instanceof Comparable)) {
                    throw new ClassCastException(k.getClass().getName() + " is not Comparable");
                }
                c6696f2 = new C6696f<>(c6696f, k, secondaryHash, c6696f5, c6696f5.f17337e);
                c6696fArr[length] = c6696f2;
            } else {
                c6696f2 = new C6696f<>(c6696f, k, secondaryHash, c6696f5, c6696f5.f17337e);
                if (i < 0) {
                    c6696f.f17334b = c6696f2;
                } else {
                    c6696f.f17335c = c6696f2;
                }
                rebalance(c6696f, true);
            }
            int i2 = this.f17317d;
            this.f17317d = i2 + 1;
            if (i2 > this.f17319f) {
                doubleCapacity();
            }
            this.f17318e++;
            return c6696f2;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    C6696f<K, V> m8643a(Object obj) {
        if (obj != 0) {
            try {
                return m8642a((LinkedHashTreeMap<K, V>) obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    C6696f<K, V> m8641a(Map.Entry<?, ?> entry) {
        C6696f<K, V> m8643a = m8643a(entry.getKey());
        if (m8643a != null && equal(m8643a.f17340h, entry.getValue())) {
            return m8643a;
        }
        return null;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    void m8644a(C6696f<K, V> c6696f, boolean z) {
        int i;
        if (z) {
            c6696f.f17337e.f17336d = c6696f.f17336d;
            c6696f.f17336d.f17337e = c6696f.f17337e;
            c6696f.f17337e = null;
            c6696f.f17336d = null;
        }
        C6696f<K, V> c6696f2 = c6696f.f17334b;
        C6696f<K, V> c6696f3 = c6696f.f17335c;
        C6696f<K, V> c6696f4 = c6696f.f17333a;
        int i2 = 0;
        if (c6696f2 != null && c6696f3 != null) {
            C6696f<K, V> m8629b = c6696f2.f17341i > c6696f3.f17341i ? c6696f2.m8629b() : c6696f3.m8630a();
            m8644a((C6696f) m8629b, false);
            C6696f<K, V> c6696f5 = c6696f.f17334b;
            if (c6696f5 != null) {
                i = c6696f5.f17341i;
                m8629b.f17334b = c6696f5;
                c6696f5.f17333a = m8629b;
                c6696f.f17334b = null;
            } else {
                i = 0;
            }
            C6696f<K, V> c6696f6 = c6696f.f17335c;
            if (c6696f6 != null) {
                i2 = c6696f6.f17341i;
                m8629b.f17335c = c6696f6;
                c6696f6.f17333a = m8629b;
                c6696f.f17335c = null;
            }
            m8629b.f17341i = Math.max(i, i2) + 1;
            replaceInParent(c6696f, m8629b);
            return;
        }
        if (c6696f2 != null) {
            replaceInParent(c6696f, c6696f2);
            c6696f.f17334b = null;
        } else if (c6696f3 != null) {
            replaceInParent(c6696f, c6696f3);
            c6696f.f17335c = null;
        } else {
            replaceInParent(c6696f, null);
        }
        rebalance(c6696f4, false);
        this.f17317d--;
        this.f17318e++;
    }

    /* renamed from: b */
    C6696f<K, V> m8639b(Object obj) {
        C6696f<K, V> m8643a = m8643a(obj);
        if (m8643a != null) {
            m8644a((C6696f) m8643a, true);
        }
        return m8643a;
    }

    private void replaceInParent(C6696f<K, V> c6696f, C6696f<K, V> c6696f2) {
        C6696f<K, V> c6696f3 = c6696f.f17333a;
        c6696f.f17333a = null;
        if (c6696f2 != null) {
            c6696f2.f17333a = c6696f3;
        }
        if (c6696f3 != null) {
            if (c6696f3.f17334b == c6696f) {
                c6696f3.f17334b = c6696f2;
                return;
            } else if (!f17313g && c6696f3.f17335c != c6696f) {
                throw new AssertionError();
            } else {
                c6696f3.f17335c = c6696f2;
                return;
            }
        }
        int i = c6696f.f17339g;
        C6696f<K, V>[] c6696fArr = this.f17315b;
        c6696fArr[i & (c6696fArr.length - 1)] = c6696f2;
    }

    private void rebalance(C6696f<K, V> c6696f, boolean z) {
        while (c6696f != null) {
            C6696f<K, V> c6696f2 = c6696f.f17334b;
            C6696f<K, V> c6696f3 = c6696f.f17335c;
            int i = c6696f2 != null ? c6696f2.f17341i : 0;
            int i2 = c6696f3 != null ? c6696f3.f17341i : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                C6696f<K, V> c6696f4 = c6696f3.f17334b;
                C6696f<K, V> c6696f5 = c6696f3.f17335c;
                int i4 = (c6696f4 != null ? c6696f4.f17341i : 0) - (c6696f5 != null ? c6696f5.f17341i : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    rotateLeft(c6696f);
                } else if (!f17313g && i4 != 1) {
                    throw new AssertionError();
                } else {
                    rotateRight(c6696f3);
                    rotateLeft(c6696f);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                C6696f<K, V> c6696f6 = c6696f2.f17334b;
                C6696f<K, V> c6696f7 = c6696f2.f17335c;
                int i5 = (c6696f6 != null ? c6696f6.f17341i : 0) - (c6696f7 != null ? c6696f7.f17341i : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    rotateRight(c6696f);
                } else if (!f17313g && i5 != -1) {
                    throw new AssertionError();
                } else {
                    rotateLeft(c6696f2);
                    rotateRight(c6696f);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                c6696f.f17341i = i + 1;
                if (z) {
                    return;
                }
            } else if (!f17313g && i3 != -1 && i3 != 1) {
                throw new AssertionError();
            } else {
                c6696f.f17341i = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            c6696f = c6696f.f17333a;
        }
    }

    private void rotateLeft(C6696f<K, V> c6696f) {
        C6696f<K, V> c6696f2 = c6696f.f17334b;
        C6696f<K, V> c6696f3 = c6696f.f17335c;
        C6696f<K, V> c6696f4 = c6696f3.f17334b;
        C6696f<K, V> c6696f5 = c6696f3.f17335c;
        c6696f.f17335c = c6696f4;
        if (c6696f4 != null) {
            c6696f4.f17333a = c6696f;
        }
        replaceInParent(c6696f, c6696f3);
        c6696f3.f17334b = c6696f;
        c6696f.f17333a = c6696f3;
        c6696f.f17341i = Math.max(c6696f2 != null ? c6696f2.f17341i : 0, c6696f4 != null ? c6696f4.f17341i : 0) + 1;
        c6696f3.f17341i = Math.max(c6696f.f17341i, c6696f5 != null ? c6696f5.f17341i : 0) + 1;
    }

    private void rotateRight(C6696f<K, V> c6696f) {
        C6696f<K, V> c6696f2 = c6696f.f17334b;
        C6696f<K, V> c6696f3 = c6696f.f17335c;
        C6696f<K, V> c6696f4 = c6696f2.f17334b;
        C6696f<K, V> c6696f5 = c6696f2.f17335c;
        c6696f.f17334b = c6696f5;
        if (c6696f5 != null) {
            c6696f5.f17333a = c6696f;
        }
        replaceInParent(c6696f, c6696f2);
        c6696f2.f17335c = c6696f;
        c6696f.f17333a = c6696f2;
        c6696f.f17341i = Math.max(c6696f3 != null ? c6696f3.f17341i : 0, c6696f5 != null ? c6696f5.f17341i : 0) + 1;
        c6696f2.f17341i = Math.max(c6696f.f17341i, c6696f4 != null ? c6696f4.f17341i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.C6691c c6691c = this.entrySet;
        if (c6691c != null) {
            return c6691c;
        }
        LinkedHashTreeMap<K, V>.C6691c c6691c2 = new C6691c();
        this.entrySet = c6691c2;
        return c6691c2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.C6693d c6693d = this.keySet;
        if (c6693d != null) {
            return c6693d;
        }
        LinkedHashTreeMap<K, V>.C6693d c6693d2 = new C6693d();
        this.keySet = c6693d2;
        return c6693d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedHashTreeMap$f */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6696f<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        C6696f<K, V> f17333a;

        /* renamed from: b */
        C6696f<K, V> f17334b;

        /* renamed from: c */
        C6696f<K, V> f17335c;

        /* renamed from: d */
        C6696f<K, V> f17336d;

        /* renamed from: e */
        C6696f<K, V> f17337e;

        /* renamed from: f */
        final K f17338f;

        /* renamed from: g */
        final int f17339g;

        /* renamed from: h */
        V f17340h;

        /* renamed from: i */
        int f17341i;

        C6696f() {
            this.f17338f = null;
            this.f17339g = -1;
            this.f17337e = this;
            this.f17336d = this;
        }

        C6696f(C6696f<K, V> c6696f, K k, int i, C6696f<K, V> c6696f2, C6696f<K, V> c6696f3) {
            this.f17333a = c6696f;
            this.f17338f = k;
            this.f17339g = i;
            this.f17341i = 1;
            this.f17336d = c6696f2;
            this.f17337e = c6696f3;
            c6696f3.f17336d = this;
            c6696f2.f17337e = this;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f17338f;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f17340h;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f17340h;
            this.f17340h = v;
            return v2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                K k = this.f17338f;
                if (k == null) {
                    if (entry.getKey() != null) {
                        return false;
                    }
                } else if (!k.equals(entry.getKey())) {
                    return false;
                }
                V v = this.f17340h;
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
            K k = this.f17338f;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.f17340h;
            return hashCode ^ (v != null ? v.hashCode() : 0);
        }

        public String toString() {
            return this.f17338f + "=" + this.f17340h;
        }

        /* renamed from: a */
        public C6696f<K, V> m8630a() {
            C6696f<K, V> c6696f = this;
            for (C6696f<K, V> c6696f2 = this.f17334b; c6696f2 != null; c6696f2 = c6696f2.f17334b) {
                c6696f = c6696f2;
            }
            return c6696f;
        }

        /* renamed from: b */
        public C6696f<K, V> m8629b() {
            C6696f<K, V> c6696f = this;
            for (C6696f<K, V> c6696f2 = this.f17335c; c6696f2 != null; c6696f2 = c6696f2.f17335c) {
                c6696f = c6696f2;
            }
            return c6696f;
        }
    }

    private void doubleCapacity() {
        this.f17315b = m8640a((C6696f[]) this.f17315b);
        C6696f<K, V>[] c6696fArr = this.f17315b;
        this.f17319f = (c6696fArr.length / 2) + (c6696fArr.length / 4);
    }

    /* renamed from: a */
    static <K, V> C6696f<K, V>[] m8640a(C6696f<K, V>[] c6696fArr) {
        int length = c6696fArr.length;
        C6696f<K, V>[] c6696fArr2 = new C6696f[length * 2];
        C6690b c6690b = new C6690b();
        C6689a c6689a = new C6689a();
        C6689a c6689a2 = new C6689a();
        for (int i = 0; i < length; i++) {
            C6696f<K, V> c6696f = c6696fArr[i];
            if (c6696f != null) {
                c6690b.m8633a(c6696f);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    C6696f<K, V> m8634a = c6690b.m8634a();
                    if (m8634a == null) {
                        break;
                    } else if ((m8634a.f17339g & length) == 0) {
                        i2++;
                    } else {
                        i3++;
                    }
                }
                c6689a.m8636a(i2);
                c6689a2.m8636a(i3);
                c6690b.m8633a(c6696f);
                while (true) {
                    C6696f<K, V> m8634a2 = c6690b.m8634a();
                    if (m8634a2 == null) {
                        break;
                    } else if ((m8634a2.f17339g & length) == 0) {
                        c6689a.m8635a(m8634a2);
                    } else {
                        c6689a2.m8635a(m8634a2);
                    }
                }
                c6696fArr2[i] = i2 > 0 ? c6689a.m8637a() : null;
                c6696fArr2[i + length] = i3 > 0 ? c6689a2.m8637a() : null;
            }
        }
        return c6696fArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedHashTreeMap$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6690b<K, V> {

        /* renamed from: a */
        private C6696f<K, V> f17324a;

        C6690b() {
        }

        /* renamed from: a */
        void m8633a(C6696f<K, V> c6696f) {
            C6696f<K, V> c6696f2 = null;
            while (true) {
                C6696f<K, V> c6696f3 = c6696f2;
                c6696f2 = c6696f;
                if (c6696f2 != null) {
                    c6696f2.f17333a = c6696f3;
                    c6696f = c6696f2.f17334b;
                } else {
                    this.f17324a = c6696f3;
                    return;
                }
            }
        }

        /* renamed from: a */
        public C6696f<K, V> m8634a() {
            C6696f<K, V> c6696f = this.f17324a;
            if (c6696f == null) {
                return null;
            }
            C6696f<K, V> c6696f2 = c6696f.f17333a;
            c6696f.f17333a = null;
            C6696f<K, V> c6696f3 = c6696f.f17335c;
            while (true) {
                C6696f<K, V> c6696f4 = c6696f2;
                c6696f2 = c6696f3;
                if (c6696f2 != null) {
                    c6696f2.f17333a = c6696f4;
                    c6696f3 = c6696f2.f17334b;
                } else {
                    this.f17324a = c6696f4;
                    return c6696f;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedHashTreeMap$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6689a<K, V> {

        /* renamed from: a */
        private C6696f<K, V> f17320a;

        /* renamed from: b */
        private int f17321b;

        /* renamed from: c */
        private int f17322c;

        /* renamed from: d */
        private int f17323d;

        C6689a() {
        }

        /* renamed from: a */
        void m8636a(int i) {
            this.f17321b = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.f17323d = 0;
            this.f17322c = 0;
            this.f17320a = null;
        }

        /* renamed from: a */
        void m8635a(C6696f<K, V> c6696f) {
            c6696f.f17335c = null;
            c6696f.f17333a = null;
            c6696f.f17334b = null;
            c6696f.f17341i = 1;
            int i = this.f17321b;
            if (i > 0) {
                int i2 = this.f17323d;
                if ((i2 & 1) == 0) {
                    this.f17323d = i2 + 1;
                    this.f17321b = i - 1;
                    this.f17322c++;
                }
            }
            c6696f.f17333a = this.f17320a;
            this.f17320a = c6696f;
            this.f17323d++;
            int i3 = this.f17321b;
            if (i3 > 0) {
                int i4 = this.f17323d;
                if ((i4 & 1) == 0) {
                    this.f17323d = i4 + 1;
                    this.f17321b = i3 - 1;
                    this.f17322c++;
                }
            }
            int i5 = 4;
            while (true) {
                int i6 = i5 - 1;
                if ((this.f17323d & i6) != i6) {
                    return;
                }
                int i7 = this.f17322c;
                if (i7 == 0) {
                    C6696f<K, V> c6696f2 = this.f17320a;
                    C6696f<K, V> c6696f3 = c6696f2.f17333a;
                    C6696f<K, V> c6696f4 = c6696f3.f17333a;
                    c6696f3.f17333a = c6696f4.f17333a;
                    this.f17320a = c6696f3;
                    c6696f3.f17334b = c6696f4;
                    c6696f3.f17335c = c6696f2;
                    c6696f3.f17341i = c6696f2.f17341i + 1;
                    c6696f4.f17333a = c6696f3;
                    c6696f2.f17333a = c6696f3;
                } else if (i7 == 1) {
                    C6696f<K, V> c6696f5 = this.f17320a;
                    C6696f<K, V> c6696f6 = c6696f5.f17333a;
                    this.f17320a = c6696f6;
                    c6696f6.f17335c = c6696f5;
                    c6696f6.f17341i = c6696f5.f17341i + 1;
                    c6696f5.f17333a = c6696f6;
                    this.f17322c = 0;
                } else if (i7 == 2) {
                    this.f17322c = 0;
                }
                i5 *= 2;
            }
        }

        /* renamed from: a */
        C6696f<K, V> m8637a() {
            C6696f<K, V> c6696f = this.f17320a;
            if (c6696f.f17333a == null) {
                return c6696f;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedHashTreeMap$e */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public abstract class AbstractC6695e<T> implements Iterator<T> {

        /* renamed from: b */
        C6696f<K, V> f17329b;

        /* renamed from: c */
        C6696f<K, V> f17330c;

        /* renamed from: d */
        int f17331d;

        private AbstractC6695e() {
            this.f17329b = LinkedHashTreeMap.this.f17316c.f17336d;
            this.f17330c = null;
            this.f17331d = LinkedHashTreeMap.this.f17318e;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f17329b != LinkedHashTreeMap.this.f17316c;
        }

        /* renamed from: b */
        final C6696f<K, V> m8631b() {
            C6696f<K, V> c6696f = this.f17329b;
            if (c6696f == LinkedHashTreeMap.this.f17316c) {
                throw new NoSuchElementException();
            }
            if (LinkedHashTreeMap.this.f17318e != this.f17331d) {
                throw new ConcurrentModificationException();
            }
            this.f17329b = c6696f.f17336d;
            this.f17330c = c6696f;
            return c6696f;
        }

        @Override // java.util.Iterator
        public final void remove() {
            C6696f<K, V> c6696f = this.f17330c;
            if (c6696f == null) {
                throw new IllegalStateException();
            }
            LinkedHashTreeMap.this.m8644a((C6696f) c6696f, true);
            this.f17330c = null;
            this.f17331d = LinkedHashTreeMap.this.f17318e;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedHashTreeMap$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C6691c extends AbstractSet<Map.Entry<K, V>> {
        C6691c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.f17317d;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashTreeMap<K, V>.AbstractC6695e<Map.Entry<K, V>>() { // from class: com.networkbench.com.google.gson.internal.LinkedHashTreeMap.c.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                /* renamed from: a */
                public Map.Entry<K, V> next() {
                    return m8631b();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.m8641a((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            C6696f<K, V> m8641a;
            if ((obj instanceof Map.Entry) && (m8641a = LinkedHashTreeMap.this.m8641a((Map.Entry) obj)) != null) {
                LinkedHashTreeMap.this.m8644a((C6696f) m8641a, true);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.LinkedHashTreeMap$d */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C6693d extends AbstractSet<K> {
        C6693d() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.f17317d;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedHashTreeMap<K, V>.AbstractC6695e<K>() { // from class: com.networkbench.com.google.gson.internal.LinkedHashTreeMap.d.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return m8631b().f17338f;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.m8639b(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
