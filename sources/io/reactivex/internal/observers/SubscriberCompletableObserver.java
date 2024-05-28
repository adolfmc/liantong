package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SubscriberCompletableObserver<T> implements CompletableObserver, Subscription {

    /* renamed from: d */
    Disposable f24424d;
    final Subscriber<? super T> subscriber;

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
    }

    public SubscriberCompletableObserver(Subscriber<? super T> subscriber) {
        this.subscriber = subscriber;
    }

    @Override // io.reactivex.CompletableObserver
    public void onComplete() {
        this.subscriber.onComplete();
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        this.subscriber.onError(th);
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f24424d, disposable)) {
            this.f24424d = disposable;
            this.subscriber.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.f24424d.dispose();
    }
}
