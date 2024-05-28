package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableSubscribeOn<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean nonScheduledRequests;
    final Scheduler scheduler;

    public FlowableSubscribeOn(Flowable<T> flowable, Scheduler scheduler, boolean z) {
        super(flowable);
        this.scheduler = scheduler;
        this.nonScheduledRequests = z;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        SubscribeOnSubscriber subscribeOnSubscriber = new SubscribeOnSubscriber(subscriber, createWorker, this.source, this.nonScheduledRequests);
        subscriber.onSubscribe(subscribeOnSubscriber);
        createWorker.schedule(subscribeOnSubscriber);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class SubscribeOnSubscriber<T> extends AtomicReference<Thread> implements FlowableSubscriber<T>, Runnable, Subscription {
        private static final long serialVersionUID = 8094547886072529208L;
        final Subscriber<? super T> actual;
        final boolean nonScheduledRequests;
        Publisher<T> source;
        final Scheduler.Worker worker;

        /* renamed from: s */
        final AtomicReference<Subscription> f24538s = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        SubscribeOnSubscriber(Subscriber<? super T> subscriber, Scheduler.Worker worker, Publisher<T> publisher, boolean z) {
            this.actual = subscriber;
            this.worker = worker;
            this.source = publisher;
            this.nonScheduledRequests = !z;
        }

        @Override // java.lang.Runnable
        public void run() {
            lazySet(Thread.currentThread());
            Publisher<T> publisher = this.source;
            this.source = null;
            publisher.subscribe(this);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.f24538s, subscription)) {
                long andSet = this.requested.getAndSet(0L);
                if (andSet != 0) {
                    requestUpstream(andSet, subscription);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
            this.worker.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.actual.onComplete();
            this.worker.dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                Subscription subscription = this.f24538s.get();
                if (subscription != null) {
                    requestUpstream(j, subscription);
                    return;
                }
                BackpressureHelper.add(this.requested, j);
                Subscription subscription2 = this.f24538s.get();
                if (subscription2 != null) {
                    long andSet = this.requested.getAndSet(0L);
                    if (andSet != 0) {
                        requestUpstream(andSet, subscription2);
                    }
                }
            }
        }

        void requestUpstream(long j, Subscription subscription) {
            if (this.nonScheduledRequests || Thread.currentThread() == get()) {
                subscription.request(j);
            } else {
                this.worker.schedule(new Request(subscription, j));
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this.f24538s);
            this.worker.dispose();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public static final class Request implements Runnable {

            /* renamed from: n */
            private final long f24539n;

            /* renamed from: s */
            private final Subscription f24540s;

            Request(Subscription subscription, long j) {
                this.f24540s = subscription;
                this.f24539n = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f24540s.request(this.f24539n);
            }
        }
    }
}
