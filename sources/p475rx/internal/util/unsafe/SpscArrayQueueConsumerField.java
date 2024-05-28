package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SpscArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpscArrayQueueConsumerField */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class SpscArrayQueueConsumerField<E> extends SpscArrayQueueL2Pad<E> {
    protected static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueConsumerField.class, "consumerIndex");
    protected long consumerIndex;

    public SpscArrayQueueConsumerField(int i) {
        super(i);
    }
}
