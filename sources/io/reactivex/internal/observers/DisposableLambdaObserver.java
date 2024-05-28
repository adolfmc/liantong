package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DisposableLambdaObserver<T> implements Observer<T>, Disposable {
    final Observer<? super T> actual;
    final Action onDispose;
    final Consumer<? super Disposable> onSubscribe;

    /* renamed from: s */
    Disposable f24411s;

    public DisposableLambdaObserver(Observer<? super T> observer, Consumer<? super Disposable> consumer, Action action) {
        this.actual = observer;
        this.onSubscribe = consumer;
        this.onDispose = action;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        try {
            this.onSubscribe.accept(disposable);
            if (DisposableHelper.validate(this.f24411s, disposable)) {
                this.f24411s = disposable;
                this.actual.onSubscribe(this);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            disposable.dispose();
            this.f24411s = DisposableHelper.DISPOSED;
            EmptyDisposable.error(th, this.actual);
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.actual.onNext(t);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        if (this.f24411s != DisposableHelper.DISPOSED) {
            this.actual.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (this.f24411s != DisposableHelper.DISPOSED) {
            this.actual.onComplete();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        try {
            this.onDispose.run();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
        this.f24411s.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.f24411s.isDisposed();
    }
}
