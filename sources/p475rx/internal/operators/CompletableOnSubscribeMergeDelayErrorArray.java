package p475rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Completable;
import p475rx.Subscription;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeMergeDelayErrorArray implements Completable.CompletableOnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeMergeDelayErrorArray(Completable[] completableArr) {
        this.sources = completableArr;
    }

    @Override // p475rx.functions.Action1
    public void call(final Completable.CompletableSubscriber completableSubscriber) {
        Completable[] completableArr;
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        final AtomicInteger atomicInteger = new AtomicInteger(this.sources.length + 1);
        final ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        completableSubscriber.onSubscribe(compositeSubscription);
        for (Completable completable : this.sources) {
            if (compositeSubscription.isUnsubscribed()) {
                return;
            }
            if (completable == null) {
                concurrentLinkedQueue.offer(new NullPointerException("A completable source is null"));
                atomicInteger.decrementAndGet();
            } else {
                completable.subscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray.1
                    @Override // p475rx.Completable.CompletableSubscriber
                    public void onSubscribe(Subscription subscription) {
                        compositeSubscription.add(subscription);
                    }

                    @Override // p475rx.Completable.CompletableSubscriber
                    public void onError(Throwable th) {
                        concurrentLinkedQueue.offer(th);
                        tryTerminate();
                    }

                    @Override // p475rx.Completable.CompletableSubscriber
                    public void onCompleted() {
                        tryTerminate();
                    }

                    void tryTerminate() {
                        if (atomicInteger.decrementAndGet() == 0) {
                            if (concurrentLinkedQueue.isEmpty()) {
                                completableSubscriber.onCompleted();
                            } else {
                                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(concurrentLinkedQueue));
                            }
                        }
                    }
                });
            }
        }
        if (atomicInteger.decrementAndGet() == 0) {
            if (concurrentLinkedQueue.isEmpty()) {
                completableSubscriber.onCompleted();
            } else {
                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(concurrentLinkedQueue));
            }
        }
    }
}
