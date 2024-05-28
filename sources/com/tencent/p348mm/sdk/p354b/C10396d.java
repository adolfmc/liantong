package com.tencent.p348mm.sdk.p354b;

import java.util.LinkedHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10396d<K, V> {

    /* renamed from: l */
    private final LinkedHashMap<K, V> f20005l;

    /* renamed from: m */
    private int f20006m;

    /* renamed from: n */
    private int f20007n;

    /* renamed from: o */
    private int f20008o;

    /* renamed from: p */
    private int f20009p;

    /* renamed from: q */
    private int f20010q;
    private int size;

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void trimToSize(int r3) {
        /*
            r2 = this;
        L0:
            monitor-enter(r2)
            int r0 = r2.size     // Catch: java.lang.Throwable -> L69
            if (r0 < 0) goto L4a
            java.util.LinkedHashMap<K, V> r0 = r2.f20005l     // Catch: java.lang.Throwable -> L69
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L69
            if (r0 == 0) goto L11
            int r0 = r2.size     // Catch: java.lang.Throwable -> L69
            if (r0 != 0) goto L4a
        L11:
            int r0 = r2.size     // Catch: java.lang.Throwable -> L69
            if (r0 <= r3) goto L48
            java.util.LinkedHashMap<K, V> r0 = r2.f20005l     // Catch: java.lang.Throwable -> L69
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L69
            if (r0 == 0) goto L1e
            goto L48
        L1e:
            java.util.LinkedHashMap<K, V> r0 = r2.f20005l     // Catch: java.lang.Throwable -> L69
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L69
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L69
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L69
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L69
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L69
            r0.getValue()     // Catch: java.lang.Throwable -> L69
            java.util.LinkedHashMap<K, V> r0 = r2.f20005l     // Catch: java.lang.Throwable -> L69
            r0.remove(r1)     // Catch: java.lang.Throwable -> L69
            int r0 = r2.size     // Catch: java.lang.Throwable -> L69
            int r0 = r0 + (-1)
            r2.size = r0     // Catch: java.lang.Throwable -> L69
            int r0 = r2.f20008o     // Catch: java.lang.Throwable -> L69
            int r0 = r0 + 1
            r2.f20008o = r0     // Catch: java.lang.Throwable -> L69
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L69
            goto L0
        L48:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L69
            return
        L4a:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L69
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69
            r0.<init>()     // Catch: java.lang.Throwable -> L69
            java.lang.Class r1 = r2.getClass()     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L69
            r0.append(r1)     // Catch: java.lang.Throwable -> L69
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L69
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L69
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L69
            throw r3     // Catch: java.lang.Throwable -> L69
        L69:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.p348mm.sdk.p354b.C10396d.trimToSize(int):void");
    }

    /* renamed from: a */
    public final synchronized boolean m6186a(K k) {
        return this.f20005l.containsKey(k);
    }

    public final V get(K k) {
        if (k != null) {
            synchronized (this) {
                V v = this.f20005l.get(k);
                if (v != null) {
                    this.f20009p++;
                    return v;
                }
                this.f20010q++;
                return null;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final V put(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f20007n++;
            this.size++;
            put = this.f20005l.put(k, v);
            if (put != null) {
                this.size--;
            }
        }
        trimToSize(this.f20006m);
        return put;
    }

    public final synchronized String toString() {
        int i;
        i = this.f20009p + this.f20010q;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f20006m), Integer.valueOf(this.f20009p), Integer.valueOf(this.f20010q), Integer.valueOf(i != 0 ? (this.f20009p * 100) / i : 0));
    }
}
