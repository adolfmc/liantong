package p475rx.internal.util.unsafe;

import p475rx.internal.util.atomic.LinkedQueueNode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* compiled from: BaseLinkedQueue.java */
/* renamed from: rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class BaseLinkedQueueConsumerNodeRef<E> extends BaseLinkedQueuePad1<E> {
    protected static final long C_NODE_OFFSET = UnsafeAccess.addressOf(BaseLinkedQueueConsumerNodeRef.class, "consumerNode");
    protected LinkedQueueNode<E> consumerNode;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void spConsumerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.consumerNode = linkedQueueNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LinkedQueueNode<E> lvConsumerNode() {
        return (LinkedQueueNode) UnsafeAccess.UNSAFE.getObjectVolatile(this, C_NODE_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LinkedQueueNode<E> lpConsumerNode() {
        return this.consumerNode;
    }
}
