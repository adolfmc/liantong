package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingSubscriber;
import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class AutoDisposingSubscriberImpl<T> extends AtomicInteger implements AutoDisposingSubscriber<T> {
    private final Subscriber<? super T> delegate;
    private final Maybe<?> lifecycle;
    final AtomicReference<Subscription> mainSubscription = new AtomicReference<>();
    final AtomicReference<Disposable> lifecycleDisposable = new AtomicReference<>();
    private final AtomicThrowable error = new AtomicThrowable();
    private final AtomicReference<Subscription> ref = new AtomicReference<>();
    private final AtomicLong requested = new AtomicLong();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposingSubscriberImpl(Maybe<?> maybe, Subscriber<? super T> subscriber) {
        this.lifecycle = maybe;
        this.delegate = subscriber;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingSubscriber
    public Subscriber<? super T> delegateSubscriber() {
        return this.delegate;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        DisposableMaybeObserver<Object> disposableMaybeObserver = new DisposableMaybeObserver<Object>() { // from class: com.uber.autodispose.AutoDisposingSubscriberImpl.1
            @Override // io.reactivex.MaybeObserver
            public void onSuccess(Object obj) {
                AutoDisposingSubscriberImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoSubscriptionHelper.cancel(AutoDisposingSubscriberImpl.this.mainSubscription);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                AutoDisposingSubscriberImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingSubscriberImpl.this.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                AutoDisposingSubscriberImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
            }
        };
        if (AutoDisposeEndConsumerHelper.setOnce(this.lifecycleDisposable, disposableMaybeObserver, getClass())) {
            this.delegate.onSubscribe(this);
            this.lifecycle.subscribe(disposableMaybeObserver);
            if (AutoDisposeEndConsumerHelper.setOnce(this.mainSubscription, subscription, getClass())) {
                AutoSubscriptionHelper.deferredSetOnce(this.ref, this.requested, subscription);
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
        AutoSubscriptionHelper.deferredRequest(this.ref, this.requested, j);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        AutoSubscriptionHelper.cancel(this.mainSubscription);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.mainSubscription.get() == AutoSubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        cancel();
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (isDisposed() || !HalfSerializer.onNext(this.delegate, t, this, this.error)) {
            return;
        }
        this.mainSubscription.lazySet(AutoSubscriptionHelper.CANCELLED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainSubscription.lazySet(AutoSubscriptionHelper.CANCELLED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        HalfSerializer.onError(this.delegate, th, this, this.error);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        this.mainSubscription.lazySet(AutoSubscriptionHelper.CANCELLED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        HalfSerializer.onComplete(this.delegate, this, this.error);
    }
}
