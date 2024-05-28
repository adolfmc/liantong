package org.greenrobot.essentials.collections;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LongHashSet {
    protected static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private volatile float loadFactor;
    private volatile int size;
    private Entry[] table;
    private int threshold;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Entry {
        final long key;
        Entry next;

        Entry(long j, Entry entry) {
            this.key = j;
            this.next = entry;
        }
    }

    public static LongHashSet createSynchronized() {
        return new Synchronized(16);
    }

    public static LongHashSet createSynchronized(int i) {
        return new Synchronized(i);
    }

    public LongHashSet() {
        this(16);
    }

    public LongHashSet(int i) {
        this.loadFactor = 1.3f;
        this.capacity = i;
        this.threshold = (int) ((i * this.loadFactor) + 0.5f);
        this.table = new Entry[i];
    }

    public boolean contains(long j) {
        for (Entry entry = this.table[((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.capacity]; entry != null; entry = entry.next) {
            if (entry.key == j) {
                return true;
            }
        }
        return false;
    }

    public boolean add(long j) {
        int i = ((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.capacity;
        Entry entry = this.table[i];
        for (Entry entry2 = entry; entry2 != null; entry2 = entry2.next) {
            if (entry2.key == j) {
                return false;
            }
        }
        this.table[i] = new Entry(j, entry);
        this.size++;
        if (this.size > this.threshold) {
            setCapacity(this.capacity * 2);
        }
        return true;
    }

    public boolean remove(long j) {
        int i = ((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.capacity;
        Entry entry = this.table[i];
        Entry entry2 = null;
        while (entry != null) {
            Entry entry3 = entry.next;
            if (entry.key == j) {
                if (entry2 == null) {
                    this.table[i] = entry3;
                } else {
                    entry2.next = entry3;
                }
                this.size--;
                return true;
            }
            entry2 = entry;
            entry = entry3;
        }
        return false;
    }

    public long[] keys() {
        Entry[] entryArr;
        long[] jArr = new long[this.size];
        int i = 0;
        for (Entry entry : this.table) {
            while (entry != null) {
                jArr[i] = entry.key;
                entry = entry.next;
                i++;
            }
        }
        return jArr;
    }

    public void clear() {
        this.size = 0;
        Arrays.fill(this.table, (Object) null);
    }

    public int size() {
        return this.size;
    }

    public void setCapacity(int i) {
        Entry[] entryArr = new Entry[i];
        int length = this.table.length;
        for (int i2 = 0; i2 < length; i2++) {
            Entry entry = this.table[i2];
            while (entry != null) {
                long j = entry.key;
                int i3 = ((((int) j) ^ ((int) (j >>> 32))) & Integer.MAX_VALUE) % i;
                Entry entry2 = entry.next;
                entry.next = entryArr[i3];
                entryArr[i3] = entry;
                entry = entry2;
            }
        }
        this.table = entryArr;
        this.capacity = i;
        this.threshold = (int) ((i * this.loadFactor) + 0.5f);
    }

    public void setLoadFactor(float f) {
        this.loadFactor = f;
    }

    public void reserveRoom(int i) {
        setCapacity((int) ((i * this.loadFactor * 1.3f) + 0.5f));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Synchronized extends LongHashSet {
        public Synchronized(int i) {
            super(i);
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized boolean contains(long j) {
            return super.contains(j);
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized boolean add(long j) {
            return super.add(j);
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized boolean remove(long j) {
            return super.remove(j);
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized long[] keys() {
            return super.keys();
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized void clear() {
            super.clear();
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized void setCapacity(int i) {
            super.setCapacity(i);
        }

        @Override // org.greenrobot.essentials.collections.LongHashSet
        public synchronized void reserveRoom(int i) {
            super.reserveRoom(i);
        }
    }
}
