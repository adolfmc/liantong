package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableFromObservable<T> extends Flowable<T> {
    private final Observable<T> upstream;

    public FlowableFromObservable(Observable<T> observable) {
        this.upstream = observable;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.upstream.subscribe(new SubscriberObserver(subscriber));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class SubscriberObserver<T> implements Observer<T>, Subscription {

        /* renamed from: d */
        private Disposable f24494d;

        /* renamed from: s */
        private final Subscriber<? super T> f24495s;

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
        }

        SubscriberObserver(Subscriber<? super T> subscriber) {
            this.f24495s = subscriber;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.f24495s.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.f24495s.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.f24495s.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.f24494d = disposable;
            this.f24495s.onSubscribe(this);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.f24494d.dispose();
        }
    }
}
