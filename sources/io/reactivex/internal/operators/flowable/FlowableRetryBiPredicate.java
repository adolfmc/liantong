package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableRetryBiPredicate<T> extends AbstractFlowableWithUpstream<T, T> {
    final BiPredicate<? super Integer, ? super Throwable> predicate;

    public FlowableRetryBiPredicate(Flowable<T> flowable, BiPredicate<? super Integer, ? super Throwable> biPredicate) {
        super(flowable);
        this.predicate = biPredicate;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
        subscriber.onSubscribe(subscriptionArbiter);
        new RetryBiSubscriber(subscriber, this.predicate, subscriptionArbiter, this.source).subscribeNext();
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class RetryBiSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Subscriber<? super T> actual;
        final BiPredicate<? super Integer, ? super Throwable> predicate;
        long produced;
        int retries;

        /* renamed from: sa */
        final SubscriptionArbiter f24519sa;
        final Publisher<? extends T> source;

        RetryBiSubscriber(Subscriber<? super T> subscriber, BiPredicate<? super Integer, ? super Throwable> biPredicate, SubscriptionArbiter subscriptionArbiter, Publisher<? extends T> publisher) {
            this.actual = subscriber;
            this.f24519sa = subscriptionArbiter;
            this.source = publisher;
            this.predicate = biPredicate;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.f24519sa.setSubscription(subscription);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.actual.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            try {
                BiPredicate<? super Integer, ? super Throwable> biPredicate = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (!biPredicate.test(Integer.valueOf(i), th)) {
                    this.actual.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.actual.onError(new CompositeException(th, th2));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.actual.onComplete();
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.f24519sa.isCancelled()) {
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0L;
                        this.f24519sa.produced(j);
                    }
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }
}
