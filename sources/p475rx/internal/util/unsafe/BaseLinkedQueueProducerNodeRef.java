package p475rx.internal.util.unsafe;

import p475rx.internal.util.atomic.LinkedQueueNode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BaseLinkedQueue.java */
/* renamed from: rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class BaseLinkedQueueProducerNodeRef<E> extends BaseLinkedQueuePad0<E> {
    protected static final long P_NODE_OFFSET = UnsafeAccess.addressOf(BaseLinkedQueueProducerNodeRef.class, "producerNode");
    protected LinkedQueueNode<E> producerNode;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void spProducerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.producerNode = linkedQueueNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LinkedQueueNode<E> lvProducerNode() {
        return (LinkedQueueNode) UnsafeAccess.UNSAFE.getObjectVolatile(this, P_NODE_OFFSET);
    }

    protected final LinkedQueueNode<E> lpProducerNode() {
        return this.producerNode;
    }
}
