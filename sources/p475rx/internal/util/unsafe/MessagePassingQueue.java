package p475rx.internal.util.unsafe;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.unsafe.MessagePassingQueue */
/* loaded from: E:\9227576_dexfile_execute.dex */
interface MessagePassingQueue<M> {
    boolean isEmpty();

    boolean offer(M m);

    M peek();

    M poll();

    int size();
}
