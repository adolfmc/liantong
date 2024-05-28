package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableHide<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableHide(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new HideDisposable(observer));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class HideDisposable<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;

        /* renamed from: d */
        Disposable f24650d;

        HideDisposable(Observer<? super T> observer) {
            this.actual = observer;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24650d.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24650d.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24650d, disposable)) {
                this.f24650d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.actual.onComplete();
        }
    }
}
