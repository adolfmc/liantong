package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.NoSuchElementException;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableLastSingle<T> extends Single<T> {
    final T defaultItem;
    final ObservableSource<T> source;

    public ObservableLastSingle(ObservableSource<T> observableSource, T t) {
        this.source = observableSource;
        this.defaultItem = t;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new LastObserver(singleObserver, this.defaultItem));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class LastObserver<T> implements Observer<T>, Disposable {
        final SingleObserver<? super T> actual;
        final T defaultItem;
        T item;

        /* renamed from: s */
        Disposable f24655s;

        LastObserver(SingleObserver<? super T> singleObserver, T t) {
            this.actual = singleObserver;
            this.defaultItem = t;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24655s.dispose();
            this.f24655s = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24655s == DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24655s, disposable)) {
                this.f24655s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.item = t;
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.f24655s = DisposableHelper.DISPOSED;
            this.item = null;
            this.actual.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.f24655s = DisposableHelper.DISPOSED;
            T t = this.item;
            if (t != null) {
                this.item = null;
                this.actual.onSuccess(t);
                return;
            }
            T t2 = this.defaultItem;
            if (t2 != null) {
                this.actual.onSuccess(t2);
            } else {
                this.actual.onError(new NoSuchElementException());
            }
        }
    }
}
