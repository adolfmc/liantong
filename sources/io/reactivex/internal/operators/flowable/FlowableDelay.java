package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableDelay<T> extends AbstractFlowableWithUpstream<T, T> {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final TimeUnit unit;

    public FlowableDelay(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(flowable);
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new DelaySubscriber(this.delayError ? subscriber : new SerializedSubscriber(subscriber), this.delay, this.unit, this.scheduler.createWorker(), this.delayError));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DelaySubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        final long delay;
        final boolean delayError;

        /* renamed from: s */
        Subscription f24472s;
        final TimeUnit unit;

        /* renamed from: w */
        final Scheduler.Worker f24473w;

        DelaySubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
            this.actual = subscriber;
            this.delay = j;
            this.unit = timeUnit;
            this.f24473w = worker;
            this.delayError = z;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f24472s, subscription)) {
                this.f24472s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.f24473w.schedule(new OnNext(t), this.delay, this.unit);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.f24473w.schedule(new OnError(th), this.delayError ? this.delay : 0L, this.unit);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.f24473w.schedule(new OnComplete(), this.delay, this.unit);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f24472s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f24472s.cancel();
            this.f24473w.dispose();
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnNext implements Runnable {

            /* renamed from: t */
            private final T f24475t;

            OnNext(T t) {
                this.f24475t = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                DelaySubscriber.this.actual.onNext((T) this.f24475t);
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnError implements Runnable {

            /* renamed from: t */
            private final Throwable f24474t;

            OnError(Throwable th) {
                this.f24474t = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DelaySubscriber.this.actual.onError(this.f24474t);
                } finally {
                    DelaySubscriber.this.f24473w.dispose();
                }
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnComplete implements Runnable {
            OnComplete() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DelaySubscriber.this.actual.onComplete();
                } finally {
                    DelaySubscriber.this.f24473w.dispose();
                }
            }
        }
    }
}
