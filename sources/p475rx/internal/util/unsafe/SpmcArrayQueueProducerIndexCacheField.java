package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SpmcArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpmcArrayQueueProducerIndexCacheField */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E> {
    private volatile long producerIndexCache;

    public SpmcArrayQueueProducerIndexCacheField(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void svProducerIndexCache(long j) {
        this.producerIndexCache = j;
    }
}
