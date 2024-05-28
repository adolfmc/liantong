package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableFromPublisher<T> extends Completable {
    final Publisher<T> flowable;

    public CompletableFromPublisher(Publisher<T> publisher) {
        this.flowable = publisher;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.flowable.subscribe(new FromPublisherSubscriber(completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class FromPublisherSubscriber<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: cs */
        final CompletableObserver f24436cs;

        /* renamed from: s */
        Subscription f24437s;

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
        }

        FromPublisherSubscriber(CompletableObserver completableObserver) {
            this.f24436cs = completableObserver;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f24437s, subscription)) {
                this.f24437s = subscription;
                this.f24436cs.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.f24436cs.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.f24436cs.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24437s.cancel();
            this.f24437s = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24437s == SubscriptionHelper.CANCELLED;
        }
    }
}
