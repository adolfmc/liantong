package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SingleDoOnError<T> extends Single<T> {
    final Consumer<? super Throwable> onError;
    final SingleSource<T> source;

    public SingleDoOnError(SingleSource<T> singleSource, Consumer<? super Throwable> consumer) {
        this.source = singleSource;
        this.onError = consumer;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DoOnError(singleObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class DoOnError implements SingleObserver<T> {

        /* renamed from: s */
        private final SingleObserver<? super T> f24747s;

        DoOnError(SingleObserver<? super T> singleObserver) {
            this.f24747s = singleObserver;
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.f24747s.onSubscribe(disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.f24747s.onSuccess(t);
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            try {
                SingleDoOnError.this.onError.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.f24747s.onError(th);
        }
    }
}
