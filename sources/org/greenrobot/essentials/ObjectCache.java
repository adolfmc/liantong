package org.greenrobot.essentials;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ObjectCache<KEY, VALUE> {
    private volatile int countEvicted;
    private volatile int countExpired;
    private volatile int countHit;
    private volatile int countMiss;
    private volatile int countPut;
    private volatile int countPutCountSinceEviction;
    private volatile int countRefCleared;
    private final long expirationMillis;
    private final boolean isExpiring;
    private final boolean isStrongReference;
    private final int maxSize;
    private volatile long nextCleanUpTimestamp;
    private final ReferenceType referenceType;
    private final Map<KEY, CacheEntry<VALUE>> values;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum ReferenceType {
        SOFT,
        WEAK,
        STRONG
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class CacheEntry<V> {
        final Reference<V> reference;
        final V referenceStrong;
        final long timeCreated = System.currentTimeMillis();

        CacheEntry(Reference<V> reference, V v) {
            this.reference = reference;
            this.referenceStrong = v;
        }
    }

    public ObjectCache(ReferenceType referenceType, int i, long j) {
        this.referenceType = referenceType;
        this.isStrongReference = referenceType == ReferenceType.STRONG;
        this.maxSize = i;
        this.expirationMillis = j;
        this.isExpiring = j > 0;
        this.values = new LinkedHashMap();
    }

    public VALUE put(KEY key, VALUE value) {
        CacheEntry<VALUE> cacheEntry;
        CacheEntry<VALUE> put;
        if (this.referenceType == ReferenceType.WEAK) {
            cacheEntry = new CacheEntry<>(new WeakReference(value), null);
        } else if (this.referenceType == ReferenceType.SOFT) {
            cacheEntry = new CacheEntry<>(new SoftReference(value), null);
        } else {
            cacheEntry = new CacheEntry<>(null, value);
        }
        this.countPutCountSinceEviction++;
        this.countPut++;
        if (this.isExpiring && this.nextCleanUpTimestamp == 0) {
            this.nextCleanUpTimestamp = System.currentTimeMillis() + this.expirationMillis + 1;
        }
        synchronized (this) {
            if (this.values.size() >= this.maxSize) {
                evictToTargetSize(this.maxSize - 1);
            }
            put = this.values.put(key, cacheEntry);
        }
        return getValueForRemoved(put);
    }

    private VALUE getValueForRemoved(CacheEntry<VALUE> cacheEntry) {
        if (cacheEntry != null) {
            return this.isStrongReference ? cacheEntry.referenceStrong : cacheEntry.reference.get();
        }
        return null;
    }

    private VALUE getValue(KEY key, CacheEntry<VALUE> cacheEntry) {
        if (cacheEntry != null) {
            if (this.isStrongReference) {
                return cacheEntry.referenceStrong;
            }
            VALUE value = cacheEntry.reference.get();
            if (value == null) {
                this.countRefCleared++;
                if (key != null) {
                    synchronized (this) {
                        this.values.remove(key);
                    }
                }
            }
            return value;
        }
        return null;
    }

    public void putAll(Map<KEY, VALUE> map) {
        int size = this.maxSize - map.size();
        if (this.maxSize > 0 && this.values.size() > size) {
            evictToTargetSize(size);
        }
        for (Map.Entry<KEY, VALUE> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public VALUE get(KEY key) {
        CacheEntry<VALUE> cacheEntry;
        synchronized (this) {
            cacheEntry = this.values.get(key);
        }
        VALUE value = null;
        if (cacheEntry != null) {
            if (this.isExpiring) {
                if (System.currentTimeMillis() - cacheEntry.timeCreated < this.expirationMillis) {
                    value = getValue(key, cacheEntry);
                } else {
                    this.countExpired++;
                    synchronized (this) {
                        this.values.remove(key);
                    }
                }
            } else {
                value = getValue(key, cacheEntry);
            }
        }
        if (value != null) {
            this.countHit++;
        } else {
            this.countMiss++;
        }
        return value;
    }

    public synchronized void clear() {
        this.values.clear();
    }

    public VALUE remove(KEY key) {
        return getValueForRemoved(this.values.remove(key));
    }

    public synchronized void evictToTargetSize(int i) {
        if (i <= 0) {
            this.values.clear();
        } else {
            checkCleanUpObsoleteEntries();
            Iterator<KEY> it = this.values.keySet().iterator();
            while (it.hasNext() && this.values.size() > i) {
                this.countEvicted++;
                it.next();
                it.remove();
            }
        }
    }

    void checkCleanUpObsoleteEntries() {
        if (!this.isStrongReference || this.isExpiring) {
            if ((!this.isExpiring || this.nextCleanUpTimestamp == 0 || System.currentTimeMillis() <= this.nextCleanUpTimestamp) && this.countPutCountSinceEviction <= this.maxSize / 2) {
                return;
            }
            cleanUpObsoleteEntries();
        }
    }

    public synchronized int cleanUpObsoleteEntries() {
        int i;
        i = 0;
        this.countPutCountSinceEviction = 0;
        this.nextCleanUpTimestamp = 0L;
        long currentTimeMillis = this.isExpiring ? System.currentTimeMillis() - this.expirationMillis : 0L;
        Iterator<CacheEntry<VALUE>> it = this.values.values().iterator();
        while (it.hasNext()) {
            CacheEntry<VALUE> next = it.next();
            if (!this.isStrongReference && next.reference == null) {
                this.countRefCleared++;
                i++;
                it.remove();
            } else if (next.timeCreated < currentTimeMillis) {
                this.countExpired++;
                i++;
                it.remove();
            }
        }
        return i;
    }

    public synchronized boolean containsKey(KEY key) {
        return this.values.containsKey(key);
    }

    public boolean containsKeyWithValue(KEY key) {
        return get(key) != null;
    }

    public synchronized Set<KEY> keySet() {
        return this.values.keySet();
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public synchronized int size() {
        return this.values.size();
    }

    public int getCountPut() {
        return this.countPut;
    }

    public int getCountHit() {
        return this.countHit;
    }

    public int getCountMiss() {
        return this.countMiss;
    }

    public int getCountExpired() {
        return this.countExpired;
    }

    public int getCountRefCleared() {
        return this.countRefCleared;
    }

    public int getCountEvicted() {
        return this.countEvicted;
    }

    public String toString() {
        return "ObjectCache[maxSize=" + this.maxSize + ", hits=" + this.countHit + ", misses=" + this.countMiss + "]";
    }

    public String getStatsStringRemoved() {
        return "ObjectCache-Removed[expired=" + this.countExpired + ", refCleared=" + this.countRefCleared + ", evicted=" + this.countEvicted;
    }
}
