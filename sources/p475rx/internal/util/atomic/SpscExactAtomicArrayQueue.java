package p475rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p475rx.internal.util.unsafe.Pow2;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.atomic.SpscExactAtomicArrayQueue */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SpscExactAtomicArrayQueue<T> extends AtomicReferenceArray<T> implements Queue<T> {
    private static final long serialVersionUID = 6210984603741293445L;
    final int capacitySkip;
    volatile long consumerIndex;
    final int mask;
    volatile long producerIndex;
    static final AtomicLongFieldUpdater<SpscExactAtomicArrayQueue> PRODUCER_INDEX = AtomicLongFieldUpdater.newUpdater(SpscExactAtomicArrayQueue.class, "producerIndex");
    static final AtomicLongFieldUpdater<SpscExactAtomicArrayQueue> CONSUMER_INDEX = AtomicLongFieldUpdater.newUpdater(SpscExactAtomicArrayQueue.class, "consumerIndex");

    public SpscExactAtomicArrayQueue(int i) {
        super(Pow2.roundToPowerOfTwo(i));
        int length = length();
        this.mask = length - 1;
        this.capacitySkip = length - i;
    }

    @Override // java.util.Queue
    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        long j = this.producerIndex;
        int i = this.mask;
        if (get(((int) (this.capacitySkip + j)) & i) != null) {
            return false;
        }
        PRODUCER_INDEX.lazySet(this, j + 1);
        lazySet(i & ((int) j), t);
        return true;
    }

    @Override // java.util.Queue
    public T poll() {
        long j = this.consumerIndex;
        int i = ((int) j) & this.mask;
        T t = get(i);
        if (t == null) {
            return null;
        }
        CONSUMER_INDEX.lazySet(this, j + 1);
        lazySet(i, null);
        return t;
    }

    @Override // java.util.Queue
    public T peek() {
        return get(((int) this.consumerIndex) & this.mask);
    }

    @Override // java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.producerIndex == this.consumerIndex;
    }

    @Override // java.util.Collection
    public int size() {
        long j = this.consumerIndex;
        while (true) {
            long j2 = this.producerIndex;
            long j3 = this.consumerIndex;
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue, java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public T remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public T element() {
        throw new UnsupportedOperationException();
    }
}
