package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.observable = observableSource;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.observable.subscribe(new CompletableFromObservableObserver(completableObserver));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class CompletableFromObservableObserver<T> implements Observer<T> {

        /* renamed from: co */
        final CompletableObserver f24435co;

        @Override // io.reactivex.Observer
        public void onNext(T t) {
        }

        CompletableFromObservableObserver(CompletableObserver completableObserver) {
            this.f24435co = completableObserver;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.f24435co.onSubscribe(disposable);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.f24435co.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.f24435co.onComplete();
        }
    }
}
