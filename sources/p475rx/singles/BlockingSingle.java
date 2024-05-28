package p475rx.singles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.annotations.Experimental;
import p475rx.internal.operators.BlockingOperatorToFuture;
import p475rx.internal.util.BlockingUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* renamed from: rx.singles.BlockingSingle */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BlockingSingle<T> {
    private final Single<? extends T> single;

    private BlockingSingle(Single<? extends T> single) {
        this.single = single;
    }

    @Experimental
    public static <T> BlockingSingle<T> from(Single<? extends T> single) {
        return new BlockingSingle<>(single);
    }

    @Experimental
    public T value() {
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, this.single.subscribe((SingleSubscriber<? super Object>) new SingleSubscriber<T>() { // from class: rx.singles.BlockingSingle.1
            @Override // p475rx.SingleSubscriber
            public void onSuccess(T t) {
                atomicReference.set(t);
                countDownLatch.countDown();
            }

            @Override // p475rx.SingleSubscriber
            public void onError(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }
        }));
        Throwable th = (Throwable) atomicReference2.get();
        if (th != null) {
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw new RuntimeException(th);
        }
        return (T) atomicReference.get();
    }

    @Experimental
    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.single.toObservable());
    }
}
