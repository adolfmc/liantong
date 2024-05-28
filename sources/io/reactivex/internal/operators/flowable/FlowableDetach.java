package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EmptyComponent;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableDetach<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableDetach(Flowable<T> flowable) {
        super(flowable);
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new DetachSubscriber(subscriber));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DetachSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        Subscriber<? super T> actual;

        /* renamed from: s */
        Subscription f24478s;

        DetachSubscriber(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.f24478s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            Subscription subscription = this.f24478s;
            this.f24478s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asSubscriber();
            subscription.cancel();
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f24478s, subscription)) {
                this.f24478s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            Subscriber<? super T> subscriber = this.actual;
            this.f24478s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asSubscriber();
            subscriber.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            Subscriber<? super T> subscriber = this.actual;
            this.f24478s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asSubscriber();
            subscriber.onComplete();
        }
    }
}
