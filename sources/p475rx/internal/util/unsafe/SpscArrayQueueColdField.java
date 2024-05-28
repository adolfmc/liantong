package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SpscArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpscArrayQueueColdField */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class SpscArrayQueueColdField<E> extends ConcurrentCircularArrayQueue<E> {
    private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    protected final int lookAheadStep;

    public SpscArrayQueueColdField(int i) {
        super(i);
        this.lookAheadStep = Math.min(i / 4, MAX_LOOK_AHEAD_STEP.intValue());
    }
}
