package p475rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p475rx.internal.util.unsafe.Pow2;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.atomic.AtomicReferenceArrayQueue */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class AtomicReferenceArrayQueue<E> extends AbstractQueue<E> {
    protected final AtomicReferenceArray<E> buffer;
    protected final int mask;

    /* JADX INFO: Access modifiers changed from: protected */
    public final int calcElementOffset(long j, int i) {
        return ((int) j) & i;
    }

    public AtomicReferenceArrayQueue(int i) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        this.mask = roundToPowerOfTwo - 1;
        this.buffer = new AtomicReferenceArray<>(roundToPowerOfTwo);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int calcElementOffset(long j) {
        return ((int) j) & this.mask;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final E lvElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    protected final E lpElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    protected final E lpElement(int i) {
        return this.buffer.get(i);
    }

    protected final void spElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.lazySet(i, e);
    }

    protected final void spElement(int i, E e) {
        this.buffer.lazySet(i, e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.lazySet(i, e);
    }

    protected final void soElement(int i, E e) {
        this.buffer.lazySet(i, e);
    }

    protected final void svElement(AtomicReferenceArray<E> atomicReferenceArray, int i, E e) {
        atomicReferenceArray.set(i, e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final E lvElement(int i) {
        return lvElement(this.buffer, i);
    }
}
