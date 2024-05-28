package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SpscArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpscArrayQueueProducerFields */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
    protected long producerIndex;
    protected long producerLookAhead;

    public SpscArrayQueueProducerFields(int i) {
        super(i);
    }
}
