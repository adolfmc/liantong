package io.reactivex.internal.operators.flowable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableIgnoreElementsCompletable<T> extends Completable implements FuseToFlowable<T> {
    final Flowable<T> source;

    public FlowableIgnoreElementsCompletable(Flowable<T> flowable) {
        this.source = flowable;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe((FlowableSubscriber) new IgnoreElementsSubscriber(completableObserver));
    }

    @Override // io.reactivex.internal.fuseable.FuseToFlowable
    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableIgnoreElements(this.source));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class IgnoreElementsSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final CompletableObserver actual;

        /* renamed from: s */
        Subscription f24499s;

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
        }

        IgnoreElementsSubscriber(CompletableObserver completableObserver) {
            this.actual = completableObserver;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f24499s, subscription)) {
                this.f24499s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.f24499s = SubscriptionHelper.CANCELLED;
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.f24499s = SubscriptionHelper.CANCELLED;
            this.actual.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24499s.cancel();
            this.f24499s = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24499s == SubscriptionHelper.CANCELLED;
        }
    }
}
