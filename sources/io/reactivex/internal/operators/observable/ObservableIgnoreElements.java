package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableIgnoreElements<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableIgnoreElements(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new IgnoreObservable(observer));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class IgnoreObservable<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;

        /* renamed from: d */
        Disposable f24651d;

        @Override // io.reactivex.Observer
        public void onNext(T t) {
        }

        IgnoreObservable(Observer<? super T> observer) {
            this.actual = observer;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.f24651d = disposable;
            this.actual.onSubscribe(this);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.actual.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24651d.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24651d.isDisposed();
        }
    }
}
