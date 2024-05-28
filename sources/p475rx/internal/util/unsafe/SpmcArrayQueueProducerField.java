package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SpmcArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpmcArrayQueueProducerField */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class SpmcArrayQueueProducerField<E> extends SpmcArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpmcArrayQueueProducerField.class, "producerIndex");
    private volatile long producerIndex;

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerIndex() {
        return this.producerIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soTail(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, j);
    }

    public SpmcArrayQueueProducerField(int i) {
        super(i);
    }
}
