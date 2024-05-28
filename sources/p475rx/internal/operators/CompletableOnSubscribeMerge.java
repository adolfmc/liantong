package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p475rx.Completable;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.CompositeException;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeMerge */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeMerge implements Completable.CompletableOnSubscribe {
    final boolean delayErrors;
    final int maxConcurrency;
    final Observable<Completable> source;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeMerge(Observable<? extends Completable> observable, int i, boolean z) {
        this.source = observable;
        this.maxConcurrency = i;
        this.delayErrors = z;
    }

    @Override // p475rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        CompletableMergeSubscriber completableMergeSubscriber = new CompletableMergeSubscriber(completableSubscriber, this.maxConcurrency, this.delayErrors);
        completableSubscriber.onSubscribe(completableMergeSubscriber);
        this.source.subscribe((Subscriber<? super Completable>) completableMergeSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.CompletableOnSubscribeMerge$CompletableMergeSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CompletableMergeSubscriber extends Subscriber<Completable> {
        static final AtomicReferenceFieldUpdater<CompletableMergeSubscriber, Queue> ERRORS = AtomicReferenceFieldUpdater.newUpdater(CompletableMergeSubscriber.class, Queue.class, "errors");
        static final AtomicIntegerFieldUpdater<CompletableMergeSubscriber> ONCE = AtomicIntegerFieldUpdater.newUpdater(CompletableMergeSubscriber.class, "once");
        final Completable.CompletableSubscriber actual;
        final boolean delayErrors;
        volatile boolean done;
        volatile Queue<Throwable> errors;
        final int maxConcurrency;
        volatile int once;
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(1);

        public CompletableMergeSubscriber(Completable.CompletableSubscriber completableSubscriber, int i, boolean z) {
            this.actual = completableSubscriber;
            this.maxConcurrency = i;
            this.delayErrors = z;
            if (i == Integer.MAX_VALUE) {
                request(Long.MAX_VALUE);
            } else {
                request(i);
            }
        }

        Queue<Throwable> getOrCreateErrors() {
            Queue<Throwable> queue = this.errors;
            if (queue != null) {
                return queue;
            }
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            return ERRORS.compareAndSet(this, null, concurrentLinkedQueue) ? concurrentLinkedQueue : this.errors;
        }

        @Override // p475rx.Observer
        public void onNext(Completable completable) {
            if (this.done) {
                return;
            }
            this.wip.getAndIncrement();
            completable.subscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMerge.CompletableMergeSubscriber.1

                /* renamed from: d */
                Subscription f27581d;
                boolean innerDone;

                @Override // p475rx.Completable.CompletableSubscriber
                public void onSubscribe(Subscription subscription) {
                    this.f27581d = subscription;
                    CompletableMergeSubscriber.this.set.add(subscription);
                }

                @Override // p475rx.Completable.CompletableSubscriber
                public void onError(Throwable th) {
                    if (this.innerDone) {
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                        return;
                    }
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.f27581d);
                    CompletableMergeSubscriber.this.getOrCreateErrors().offer(th);
                    CompletableMergeSubscriber.this.terminate();
                    if (!CompletableMergeSubscriber.this.delayErrors || CompletableMergeSubscriber.this.done) {
                        return;
                    }
                    CompletableMergeSubscriber.this.request(1L);
                }

                @Override // p475rx.Completable.CompletableSubscriber
                public void onCompleted() {
                    if (this.innerDone) {
                        return;
                    }
                    this.innerDone = true;
                    CompletableMergeSubscriber.this.set.remove(this.f27581d);
                    CompletableMergeSubscriber.this.terminate();
                    if (CompletableMergeSubscriber.this.done) {
                        return;
                    }
                    CompletableMergeSubscriber.this.request(1L);
                }
            });
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            getOrCreateErrors().offer(th);
            this.done = true;
            terminate();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            terminate();
        }

        void terminate() {
            Queue<Throwable> queue;
            if (this.wip.decrementAndGet() == 0) {
                Queue<Throwable> queue2 = this.errors;
                if (queue2 == null || queue2.isEmpty()) {
                    this.actual.onCompleted();
                    return;
                }
                Throwable collectErrors = CompletableOnSubscribeMerge.collectErrors(queue2);
                if (ONCE.compareAndSet(this, 0, 1)) {
                    this.actual.onError(collectErrors);
                } else {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(collectErrors);
                }
            } else if (this.delayErrors || (queue = this.errors) == null || queue.isEmpty()) {
            } else {
                Throwable collectErrors2 = CompletableOnSubscribeMerge.collectErrors(queue);
                if (ONCE.compareAndSet(this, 0, 1)) {
                    this.actual.onError(collectErrors2);
                } else {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(collectErrors2);
                }
            }
        }
    }

    public static Throwable collectErrors(Queue<Throwable> queue) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            Throwable poll = queue.poll();
            if (poll == null) {
                break;
            }
            arrayList.add(poll);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (Throwable) arrayList.get(0);
        }
        return new CompositeException(arrayList);
    }
}
