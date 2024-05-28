package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingCompletableObserver;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposingCompletableObserverImpl implements AutoDisposingCompletableObserver {
    private final CompletableObserver delegate;
    private final Maybe<?> lifecycle;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final AtomicReference<Disposable> lifecycleDisposable = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposingCompletableObserverImpl(Maybe<?> maybe, CompletableObserver completableObserver) {
        this.lifecycle = maybe;
        this.delegate = completableObserver;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingCompletableObserver
    public CompletableObserver delegateObserver() {
        return this.delegate;
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        DisposableMaybeObserver<Object> disposableMaybeObserver = new DisposableMaybeObserver<Object>() { // from class: com.uber.autodispose.AutoDisposingCompletableObserverImpl.1
            @Override // io.reactivex.MaybeObserver
            public void onSuccess(Object obj) {
                AutoDisposingCompletableObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposableHelper.dispose(AutoDisposingCompletableObserverImpl.this.mainDisposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                AutoDisposingCompletableObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingCompletableObserverImpl.this.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                AutoDisposingCompletableObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
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

    @Override // io.reactivex.CompletableObserver
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onComplete();
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onError(th);
    }
}
