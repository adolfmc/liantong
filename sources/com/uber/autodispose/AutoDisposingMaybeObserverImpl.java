package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingMaybeObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposingMaybeObserverImpl<T> implements AutoDisposingMaybeObserver<T> {
    private final MaybeObserver<? super T> delegate;
    private final Maybe<?> lifecycle;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final AtomicReference<Disposable> lifecycleDisposable = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposingMaybeObserverImpl(Maybe<?> maybe, MaybeObserver<? super T> maybeObserver) {
        this.lifecycle = maybe;
        this.delegate = maybeObserver;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingMaybeObserver
    public MaybeObserver<? super T> delegateObserver() {
        return this.delegate;
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
        DisposableMaybeObserver<Object> disposableMaybeObserver = new DisposableMaybeObserver<Object>() { // from class: com.uber.autodispose.AutoDisposingMaybeObserverImpl.1
            @Override // io.reactivex.MaybeObserver
            public void onSuccess(Object obj) {
                AutoDisposingMaybeObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposableHelper.dispose(AutoDisposingMaybeObserverImpl.this.mainDisposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                AutoDisposingMaybeObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingMaybeObserverImpl.this.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                AutoDisposingMaybeObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
            }
        };
        if (AutoDisposeEndConsumerHelper.setOnce(this.lifecycleDisposable, disposableMaybeObserver, getClass())) {
            this.delegate.onSubscribe(this);
            this.lifecycle.subscribe(disposableMaybeObserver);
            AutoDisposeEndConsumerHelper.setOnce(this.mainDisposable, disposable, getClass());
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.mainDisposable.get() == AutoDisposableHelper.DISPOSED;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        AutoDisposableHelper.dispose(this.mainDisposable);
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onSuccess(t);
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onError(th);
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onComplete();
    }
}
