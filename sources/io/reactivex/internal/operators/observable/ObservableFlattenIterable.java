package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableFlattenIterable<T, R> extends AbstractObservableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;

    public ObservableFlattenIterable(ObservableSource<T> observableSource, Function<? super T, ? extends Iterable<? extends R>> function) {
        super(observableSource);
        this.mapper = function;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new FlattenIterableObserver(observer, this.mapper));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class FlattenIterableObserver<T, R> implements Observer<T>, Disposable {
        final Observer<? super R> actual;

        /* renamed from: d */
        Disposable f24646d;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;

        FlattenIterableObserver(Observer<? super R> observer, Function<? super T, ? extends Iterable<? extends R>> function) {
            this.actual = observer;
            this.mapper = function;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24646d, disposable)) {
                this.f24646d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.f24646d == DisposableHelper.DISPOSED) {
                return;
            }
            try {
                Observer<? super R> observer = this.actual;
                for (R r : this.mapper.apply(t)) {
                    try {
                        try {
                            observer.onNext((Object) ObjectHelper.requireNonNull(r, "The iterator returned a null value"));
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.f24646d.dispose();
                            onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.f24646d.dispose();
                        onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                this.f24646d.dispose();
                onError(th3);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.f24646d == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.f24646d = DisposableHelper.DISPOSED;
            this.actual.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.f24646d == DisposableHelper.DISPOSED) {
                return;
            }
            this.f24646d = DisposableHelper.DISPOSED;
            this.actual.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24646d.isDisposed();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24646d.dispose();
            this.f24646d = DisposableHelper.DISPOSED;
        }
    }
}
