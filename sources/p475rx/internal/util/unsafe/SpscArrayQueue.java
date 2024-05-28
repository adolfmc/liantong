package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.unsafe.SpscArrayQueue */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SpscArrayQueue<E> extends SpscArrayQueueL3Pad<E> {
    public SpscArrayQueue(int i) {
        super(i);
    }

    @Override // java.util.Queue, p475rx.internal.util.unsafe.MessagePassingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        E[] eArr = this.buffer;
        long j = this.producerIndex;
        long calcElementOffset = calcElementOffset(j);
        if (lvElement(eArr, calcElementOffset) != null) {
            return false;
        }
        soProducerIndex(j + 1);
        soElement(eArr, calcElementOffset, e);
        return true;
    }

    @Override // java.util.Queue, p475rx.internal.util.unsafe.MessagePassingQueue
    public E poll() {
        long j = this.consumerIndex;
        long calcElementOffset = calcElementOffset(j);
        E[] eArr = this.buffer;
        E lvElement = lvElement(eArr, calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        soConsumerIndex(j + 1);
        soElement(eArr, calcElementOffset, null);
        return lvElement;
    }

    @Override // java.util.Queue, p475rx.internal.util.unsafe.MessagePassingQueue
    public E peek() {
        return lvElement(calcElementOffset(this.consumerIndex));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, p475rx.internal.util.unsafe.MessagePassingQueue
    public int size() {
        long lvConsumerIndex = lvConsumerIndex();
        while (true) {
            long lvProducerIndex = lvProducerIndex();
            long lvConsumerIndex2 = lvConsumerIndex();
            if (lvConsumerIndex == lvConsumerIndex2) {
                return (int) (lvProducerIndex - lvConsumerIndex2);
            }
            lvConsumerIndex = lvConsumerIndex2;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, p475rx.internal.util.unsafe.MessagePassingQueue
    public boolean isEmpty() {
        return lvProducerIndex() == lvConsumerIndex();
    }

    private void soProducerIndex(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, j);
    }

    private void soConsumerIndex(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, j);
    }

    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
    }

    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
    }
}
