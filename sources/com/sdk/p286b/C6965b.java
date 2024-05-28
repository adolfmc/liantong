package com.sdk.p286b;

import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.sdk.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6965b<K, V> extends ConcurrentHashMap<K, Long> {
    private static final long serialVersionUID = 5514969596535320724L;

    public C6965b(int i, float f) {
        super(i, f, 16);
    }

    /* renamed from: a */
    public synchronized Long m8191a(Object obj) {
        return (Long) super.remove(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    /* renamed from: a */
    public synchronized Long put(K k, Long l) {
        if (containsKey(k)) {
            synchronized (this) {
                Long l2 = (Long) super.remove(k);
            }
        }
        return (Long) super.put(k, l);
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized void clear() {
        super.clear();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized boolean containsKey(Object obj) {
        boolean z;
        z = false;
        Long l = (Long) super.get(obj);
        if (l == null || System.currentTimeMillis() >= l.longValue()) {
            synchronized (this) {
                Long l2 = (Long) super.remove(obj);
            }
        } else {
            z = true;
        }
        return z;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Long l;
        synchronized (this) {
            l = containsKey(obj) ? (Long) super.get(obj) : null;
        }
        return l;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Long l;
        synchronized (this) {
            l = (Long) super.remove(obj);
        }
        return l;
    }
}
