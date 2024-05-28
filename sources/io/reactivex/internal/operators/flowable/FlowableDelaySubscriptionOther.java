package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableDelaySubscriptionOther<T, U> extends Flowable<T> {
    final Publisher<? extends T> main;
    final Publisher<U> other;

    public FlowableDelaySubscriptionOther(Publisher<? extends T> publisher, Publisher<U> publisher2) {
        this.main = publisher;
        this.other = publisher2;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
        subscriber.onSubscribe(subscriptionArbiter);
        this.other.subscribe(new DelaySubscriber(subscriptionArbiter, subscriber));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class DelaySubscriber implements FlowableSubscriber<U> {
        final Subscriber<? super T> child;
        boolean done;
        final SubscriptionArbiter serial;

        DelaySubscriber(SubscriptionArbiter subscriptionArbiter, Subscriber<? super T> subscriber) {
            this.serial = subscriptionArbiter;
            this.child = subscriber;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.serial.setSubscription(new DelaySubscription(subscription));
            subscription.request(Long.MAX_VALUE);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(U u) {
            onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.child.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            FlowableDelaySubscriptionOther.this.main.subscribe(new OnCompleteSubscriber());
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        final class DelaySubscription implements Subscription {

            /* renamed from: s */
            private final Subscription f24476s;

            @Override // org.reactivestreams.Subscription
            public void request(long j) {
            }

            DelaySubscription(Subscription subscription) {
                this.f24476s = subscription;
            }

            @Override // org.reactivestreams.Subscription
            public void cancel() {
                this.f24476s.cancel();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public final class OnCompleteSubscriber implements FlowableSubscriber<T> {
            OnCompleteSubscriber() {
            }

            @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                DelaySubscriber.this.serial.setSubscription(subscription);
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(T t) {
                DelaySubscriber.this.child.onNext(t);
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                DelaySubscriber.this.child.onError(th);
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                DelaySubscriber.this.child.onComplete();
            }
        }
    }
}
