package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import p475rx.Completable;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.MissingBackpressureException;
import p475rx.internal.util.unsafe.SpscArrayQueue;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.CompletableOnSubscribeConcat */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CompletableOnSubscribeConcat implements Completable.CompletableOnSubscribe {
    final int prefetch;
    final Observable<Completable> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeConcat(Observable<? extends Completable> observable, int i) {
        this.sources = observable;
        this.prefetch = i;
    }

    @Override // p475rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        CompletableConcatSubscriber completableConcatSubscriber = new CompletableConcatSubscriber(completableSubscriber, this.prefetch);
        completableSubscriber.onSubscribe(completableConcatSubscriber);
        this.sources.subscribe((Subscriber<? super Completable>) completableConcatSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.CompletableOnSubscribeConcat$CompletableConcatSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CompletableConcatSubscriber extends Subscriber<Completable> {
        static final AtomicIntegerFieldUpdater<CompletableConcatSubscriber> ONCE = AtomicIntegerFieldUpdater.newUpdater(CompletableConcatSubscriber.class, "once");
        final Completable.CompletableSubscriber actual;
        volatile boolean done;
        volatile int once;
        final int prefetch;
        final SpscArrayQueue<Completable> queue;

        /* renamed from: sr */
        final SerialSubscription f27578sr = new SerialSubscription();
        final ConcatInnerSubscriber inner = new ConcatInnerSubscriber();
        final AtomicInteger wip = new AtomicInteger();

        public CompletableConcatSubscriber(Completable.CompletableSubscriber completableSubscriber, int i) {
            this.actual = completableSubscriber;
            this.prefetch = i;
            this.queue = new SpscArrayQueue<>(i);
            add(this.f27578sr);
            request(i);
        }

        @Override // p475rx.Observer
        public void onNext(Completable completable) {
            if (!this.queue.offer(completable)) {
                onError(new MissingBackpressureException());
            } else if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (ONCE.compareAndSet(this, 0, 1)) {
                this.actual.onError(th);
            } else {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }

        void innerError(Throwable th) {
            unsubscribe();
            onError(th);
        }

        void innerComplete() {
            if (this.wip.decrementAndGet() != 0) {
                next();
            }
            if (this.done) {
                return;
            }
            request(1L);
        }

        void next() {
            boolean z = this.done;
            Completable poll = this.queue.poll();
            if (poll != null) {
                poll.subscribe(this.inner);
            } else if (z) {
                if (ONCE.compareAndSet(this, 0, 1)) {
                    this.actual.onCompleted();
                }
            } else {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new IllegalStateException("Queue is empty?!"));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.internal.operators.CompletableOnSubscribeConcat$CompletableConcatSubscriber$ConcatInnerSubscriber */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public final class ConcatInnerSubscriber implements Completable.CompletableSubscriber {
            ConcatInnerSubscriber() {
            }

            @Override // p475rx.Completable.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                CompletableConcatSubscriber.this.f27578sr.set(subscription);
            }

            @Override // p475rx.Completable.CompletableSubscriber
            public void onError(Throwable th) {
                CompletableConcatSubscriber.this.innerError(th);
            }

            @Override // p475rx.Completable.CompletableSubscriber
            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }
        }
    }
}
