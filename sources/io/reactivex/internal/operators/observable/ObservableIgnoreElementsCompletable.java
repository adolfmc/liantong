package io.reactivex.internal.operators.observable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableIgnoreElementsCompletable<T> extends Completable implements FuseToObservable<T> {
    final ObservableSource<T> source;

    public ObservableIgnoreElementsCompletable(ObservableSource<T> observableSource) {
        this.source = observableSource;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new IgnoreObservable(completableObserver));
    }

    @Override // io.reactivex.internal.fuseable.FuseToObservable
    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableIgnoreElements(this.source));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class IgnoreObservable<T> implements Observer<T>, Disposable {
        final CompletableObserver actual;

        /* renamed from: d */
        Disposable f24652d;

        @Override // io.reactivex.Observer
        public void onNext(T t) {
        }

        IgnoreObservable(CompletableObserver completableObserver) {
            this.actual = completableObserver;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.f24652d = disposable;
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
            this.f24652d.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24652d.isDisposed();
        }
    }
}
