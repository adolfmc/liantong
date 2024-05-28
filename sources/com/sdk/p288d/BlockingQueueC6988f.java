package com.sdk.p288d;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.sdk.d.f */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BlockingQueueC6988f<E> extends AbstractQueue<E> implements Serializable, BlockingQueue<E> {
    private static final long serialVersionUID = -6903933977591709194L;

    /* renamed from: a */
    public final int f18116a;

    /* renamed from: b */
    public final AtomicInteger f18117b;

    /* renamed from: c */
    public transient C6977a<E> f18118c;

    /* renamed from: d */
    public transient C6977a<E> f18119d;

    /* renamed from: e */
    public final ReentrantLock f18120e;

    /* renamed from: f */
    public final Condition f18121f;

    /* renamed from: g */
    public final ReentrantLock f18122g;

    /* renamed from: h */
    public final Condition f18123h;

    /* renamed from: com.sdk.d.f$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C6989a implements Iterator<E> {

        /* renamed from: a */
        public C6977a<E> f18124a;

        /* renamed from: b */
        public C6977a<E> f18125b;

        /* renamed from: c */
        public E f18126c;

        public C6989a() {
            BlockingQueueC6988f.this.m8177a();
            try {
                C6977a<E> c6977a = BlockingQueueC6988f.this.f18118c.f18088c;
                this.f18124a = c6977a;
                if (c6977a != null) {
                    this.f18126c = c6977a.m8181a();
                }
            } finally {
                BlockingQueueC6988f.this.m8174b();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18124a != null;
        }

        @Override // java.util.Iterator
        public E next() {
            C6977a<E> c6977a;
            BlockingQueueC6988f.this.m8177a();
            try {
                C6977a<E> c6977a2 = this.f18124a;
                if (c6977a2 != null) {
                    E e = this.f18126c;
                    this.f18125b = c6977a2;
                    while (true) {
                        c6977a = c6977a2.f18088c;
                        if (c6977a != c6977a2) {
                            if (c6977a == null || c6977a.m8181a() != null) {
                                break;
                            }
                            c6977a2 = c6977a;
                        } else {
                            c6977a = BlockingQueueC6988f.this.f18118c.f18088c;
                            break;
                        }
                    }
                    this.f18124a = c6977a;
                    this.f18126c = c6977a == null ? null : c6977a.m8181a();
                    return e;
                }
                throw new NoSuchElementException();
            } finally {
                BlockingQueueC6988f.this.m8174b();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
            r4.f18127d.m8175a(r1, r2);
         */
        @Override // java.util.Iterator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void remove() {
            /*
                r4 = this;
                com.sdk.d.a<E> r0 = r4.f18125b
                if (r0 == 0) goto L2d
                com.sdk.d.f r0 = com.sdk.p288d.BlockingQueueC6988f.this
                r0.m8177a()
                com.sdk.d.a<E> r0 = r4.f18125b     // Catch: java.lang.Throwable -> L26
                r1 = 0
                r4.f18125b = r1     // Catch: java.lang.Throwable -> L26
                com.sdk.d.f r1 = com.sdk.p288d.BlockingQueueC6988f.this     // Catch: java.lang.Throwable -> L26
                com.sdk.d.a<E> r1 = r1.f18118c     // Catch: java.lang.Throwable -> L26
            L12:
                com.sdk.d.a<T> r2 = r1.f18088c     // Catch: java.lang.Throwable -> L26
                r3 = r2
                r2 = r1
                r1 = r3
                if (r1 == 0) goto L20
                if (r1 != r0) goto L12
                com.sdk.d.f r0 = com.sdk.p288d.BlockingQueueC6988f.this     // Catch: java.lang.Throwable -> L26
                r0.m8175a(r1, r2)     // Catch: java.lang.Throwable -> L26
            L20:
                com.sdk.d.f r0 = com.sdk.p288d.BlockingQueueC6988f.this
                r0.m8174b()
                return
            L26:
                r0 = move-exception
                com.sdk.d.f r1 = com.sdk.p288d.BlockingQueueC6988f.this
                r1.m8174b()
                throw r0
            L2d:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sdk.p288d.BlockingQueueC6988f.C6989a.remove():void");
        }
    }

    public BlockingQueueC6988f() {
        this(Integer.MAX_VALUE);
    }

    public BlockingQueueC6988f(int i) {
        this.f18117b = new AtomicInteger();
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f18120e = reentrantLock;
        this.f18121f = reentrantLock.newCondition();
        ReentrantLock reentrantLock2 = new ReentrantLock();
        this.f18122g = reentrantLock2;
        this.f18123h = reentrantLock2.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.f18116a = i;
        C6977a<E> c6977a = new C6977a<>(null);
        this.f18118c = c6977a;
        this.f18119d = c6977a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f18117b.set(0);
        C6977a<E> c6977a = new C6977a<>(null);
        this.f18118c = c6977a;
        this.f18119d = c6977a;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject == null) {
                return;
            }
            add(readObject);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        m8177a();
        try {
            objectOutputStream.defaultWriteObject();
            C6977a c6977a = this.f18118c;
            while (true) {
                c6977a = c6977a.f18088c;
                if (c6977a == null) {
                    objectOutputStream.writeObject(null);
                    return;
                }
                objectOutputStream.writeObject(c6977a.m8181a());
            }
        } finally {
            m8174b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public final synchronized E m8176a(C6977a<E> c6977a) {
        if (c6977a == 0) {
            C6977a c6977a2 = (C6977a<E>) this.f18118c;
            C6977a<E> c6977a3 = (C6977a<E>) c6977a2.f18088c;
            c6977a2.f18088c = c6977a2;
            this.f18118c = c6977a3;
            E m8181a = c6977a3.m8181a();
            c6977a3.m8180a(null);
            return m8181a;
        }
        boolean z = false;
        C6977a c6977a4 = this.f18118c;
        while (true) {
            C6977a<T> c6977a5 = c6977a4.f18088c;
            if (c6977a5 == 0) {
                break;
            } else if (c6977a5.f18087b.f18114a.ordinal() > c6977a.f18087b.f18114a.ordinal()) {
                c6977a4.f18088c = c6977a;
                c6977a.f18088c = c6977a5;
                z = true;
                break;
            } else {
                c6977a4 = c6977a4.f18088c;
            }
        }
        if (!z) {
            this.f18119d.f18088c = c6977a;
            this.f18119d = c6977a;
        }
        return null;
    }

    /* renamed from: a */
    public void m8177a() {
        this.f18122g.lock();
        this.f18120e.lock();
    }

    /* renamed from: a */
    public void m8175a(C6977a<E> c6977a, C6977a<E> c6977a2) {
        c6977a.m8180a(null);
        c6977a2.f18088c = (C6977a<E>) c6977a.f18088c;
        if (this.f18119d == c6977a) {
            this.f18119d = c6977a2;
        }
        if (this.f18117b.getAndDecrement() == this.f18116a) {
            this.f18123h.signal();
        }
    }

    /* renamed from: b */
    public void m8174b() {
        this.f18120e.unlock();
        this.f18122g.unlock();
    }

    /* renamed from: c */
    public final void m8173c() {
        ReentrantLock reentrantLock = this.f18120e;
        reentrantLock.lock();
        try {
            this.f18121f.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        m8177a();
        try {
            C6977a c6977a = (C6977a<E>) this.f18118c;
            while (true) {
                C6977a c6977a2 = c6977a.f18088c;
                if (c6977a2 == null) {
                    break;
                }
                c6977a.f18088c = c6977a;
                c6977a2.m8180a(null);
                c6977a = (C6977a<E>) c6977a2;
            }
            this.f18118c = this.f18119d;
            if (this.f18117b.getAndSet(0) == this.f18116a) {
                this.f18123h.signal();
            }
        } finally {
            m8174b();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        m8177a();
        try {
            C6977a c6977a = this.f18118c;
            do {
                c6977a = c6977a.f18088c;
                if (c6977a == null) {
                    return false;
                }
            } while (!obj.equals(c6977a.m8181a()));
            m8174b();
            return true;
        } finally {
            m8174b();
        }
    }

    /* renamed from: d */
    public final void m8172d() {
        ReentrantLock reentrantLock = this.f18122g;
        reentrantLock.lock();
        try {
            this.f18123h.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        collection.getClass();
        if (collection != this) {
            boolean z = false;
            if (i <= 0) {
                return 0;
            }
            ReentrantLock reentrantLock = this.f18120e;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.f18117b.get());
                C6977a<E> c6977a = this.f18118c;
                int i2 = 0;
                while (i2 < min) {
                    C6977a<E> c6977a2 = c6977a.f18088c;
                    collection.add((Object) c6977a2.m8181a());
                    c6977a2.m8180a(null);
                    c6977a.f18088c = (C6977a<T>) c6977a;
                    i2++;
                    c6977a = c6977a2;
                }
                if (i2 > 0) {
                    this.f18118c = c6977a;
                    if (this.f18117b.getAndAdd(-i2) == this.f18116a) {
                        z = true;
                    }
                }
                return min;
            } finally {
                reentrantLock.unlock();
                if (0 != 0) {
                    m8172d();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new C6989a();
    }

    @Override // java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e) {
        e.getClass();
        AtomicInteger atomicInteger = this.f18117b;
        if (atomicInteger.get() == this.f18116a) {
            return false;
        }
        int i = -1;
        C6977a<E> c6977a = new C6977a<>(e);
        ReentrantLock reentrantLock = this.f18122g;
        reentrantLock.lock();
        try {
            if (atomicInteger.get() < this.f18116a) {
                m8176a(c6977a);
                i = atomicInteger.getAndIncrement();
                if (i + 1 < this.f18116a) {
                    this.f18123h.signal();
                }
            }
            if (i == 0) {
                m8173c();
            }
            return i >= 0;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long j, TimeUnit timeUnit) {
        e.getClass();
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f18122g;
        AtomicInteger atomicInteger = this.f18117b;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == this.f18116a) {
            try {
                if (nanos <= 0) {
                    reentrantLock.unlock();
                    return false;
                }
                nanos = this.f18123h.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        m8176a(new C6977a<>(e));
        int andIncrement = atomicInteger.getAndIncrement();
        if (andIncrement + 1 < this.f18116a) {
            this.f18123h.signal();
        }
        if (andIncrement == 0) {
            m8173c();
            return true;
        }
        return true;
    }

    @Override // java.util.Queue
    public E peek() {
        if (this.f18117b.get() == 0) {
            return null;
        }
        ReentrantLock reentrantLock = this.f18120e;
        reentrantLock.lock();
        try {
            C6977a<E> c6977a = this.f18118c.f18088c;
            if (c6977a == null) {
                return null;
            }
            return c6977a.m8181a();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.Queue
    public E poll() {
        AtomicInteger atomicInteger = this.f18117b;
        E e = null;
        if (atomicInteger.get() == 0) {
            return null;
        }
        int i = -1;
        ReentrantLock reentrantLock = this.f18120e;
        reentrantLock.lock();
        try {
            if (atomicInteger.get() > 0) {
                e = m8176a(null);
                i = atomicInteger.getAndDecrement();
                if (i > 1) {
                    this.f18121f.signal();
                }
            }
            reentrantLock.unlock();
            if (i == this.f18116a) {
                m8172d();
            }
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        AtomicInteger atomicInteger = this.f18117b;
        ReentrantLock reentrantLock = this.f18120e;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.f18121f.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        E m8176a = m8176a(null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.f18121f.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.f18116a) {
            m8172d();
        }
        return m8176a;
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) {
        e.getClass();
        C6977a<E> c6977a = new C6977a<>(e);
        ReentrantLock reentrantLock = this.f18122g;
        AtomicInteger atomicInteger = this.f18117b;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == this.f18116a) {
            try {
                this.f18123h.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        m8176a(c6977a);
        int andIncrement = atomicInteger.getAndIncrement();
        if (andIncrement + 1 < this.f18116a) {
            this.f18123h.signal();
        }
        if (andIncrement == 0) {
            m8173c();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return this.f18116a - this.f18117b.get();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        C6977a<E> c6977a;
        if (obj == null) {
            return false;
        }
        m8177a();
        try {
            C6977a<E> c6977a2 = this.f18118c;
            do {
                c6977a = c6977a2;
                c6977a2 = c6977a2.f18088c;
                if (c6977a2 == null) {
                    return false;
                }
            } while (!obj.equals(c6977a2.m8181a()));
            m8175a(c6977a2, c6977a);
            m8174b();
            return true;
        } finally {
            m8174b();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.f18117b.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() {
        AtomicInteger atomicInteger = this.f18117b;
        ReentrantLock reentrantLock = this.f18120e;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                this.f18121f.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        E m8176a = m8176a(null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.f18121f.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.f18116a) {
            m8172d();
        }
        return m8176a;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        m8177a();
        try {
            Object[] objArr = new Object[this.f18117b.get()];
            int i = 0;
            C6977a c6977a = this.f18118c;
            while (true) {
                c6977a = c6977a.f18088c;
                if (c6977a == null) {
                    return objArr;
                }
                int i2 = i + 1;
                objArr[i] = c6977a.m8181a();
                i = i2;
            }
        } finally {
            m8174b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        m8177a();
        try {
            int i = this.f18117b.get();
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            int i2 = 0;
            C6977a c6977a = this.f18118c;
            while (true) {
                c6977a = c6977a.f18088c;
                if (c6977a == null) {
                    break;
                }
                tArr[i2] = c6977a.m8181a();
                i2++;
            }
            if (tArr.length > i2) {
                tArr[i2] = null;
            }
            return tArr;
        } finally {
            m8174b();
        }
    }
}
