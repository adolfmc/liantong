package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableSkip<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: n */
    final long f24532n;

    public FlowableSkip(Flowable<T> flowable, long j) {
        super(flowable);
        this.f24532n = j;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new SkipSubscriber(subscriber, this.f24532n));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class SkipSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        long remaining;

        /* renamed from: s */
        Subscription f24533s;

        SkipSubscriber(Subscriber<? super T> subscriber, long j) {
            this.actual = subscriber;
            this.remaining = j;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f24533s, subscription)) {
                long j = this.remaining;
                this.f24533s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(j);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long j = this.remaining;
            if (j != 0) {
                this.remaining = j - 1;
            } else {
                this.actual.onNext(t);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.actual.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f24533s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f24533s.cancel();
        }
    }
}
