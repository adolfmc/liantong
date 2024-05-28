package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingSingleObserver;
import io.reactivex.Maybe;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposingSingleObserverImpl<T> implements AutoDisposingSingleObserver<T> {
    private final SingleObserver<? super T> delegate;
    private final Maybe<?> lifecycle;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final AtomicReference<Disposable> lifecycleDisposable = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposingSingleObserverImpl(Maybe<?> maybe, SingleObserver<? super T> singleObserver) {
        this.lifecycle = maybe;
        this.delegate = singleObserver;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingSingleObserver
    public SingleObserver<? super T> delegateObserver() {
        return this.delegate;
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        DisposableMaybeObserver<Object> disposableMaybeObserver = new DisposableMaybeObserver<Object>() { // from class: com.uber.autodispose.AutoDisposingSingleObserverImpl.1
            @Override // io.reactivex.MaybeObserver
            public void onSuccess(Object obj) {
                AutoDisposingSingleObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposableHelper.dispose(AutoDisposingSingleObserverImpl.this.mainDisposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                AutoDisposingSingleObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingSingleObserverImpl.this.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                AutoDisposingSingleObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
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

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onSuccess(t);
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        this.delegate.onError(th);
    }
}
