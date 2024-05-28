package com.uber.autodispose;

import com.uber.autodispose.observers.AutoDisposingObserver;
import io.reactivex.Maybe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposingObserverImpl<T> extends AtomicInteger implements AutoDisposingObserver<T> {
    private final Observer<? super T> delegate;
    private final Maybe<?> lifecycle;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final AtomicReference<Disposable> lifecycleDisposable = new AtomicReference<>();
    private final AtomicThrowable error = new AtomicThrowable();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposingObserverImpl(Maybe<?> maybe, Observer<? super T> observer) {
        this.lifecycle = maybe;
        this.delegate = observer;
    }

    @Override // com.uber.autodispose.observers.AutoDisposingObserver
    public Observer<? super T> delegateObserver() {
        return this.delegate;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        DisposableMaybeObserver<Object> disposableMaybeObserver = new DisposableMaybeObserver<Object>() { // from class: com.uber.autodispose.AutoDisposingObserverImpl.1
            @Override // io.reactivex.MaybeObserver
            public void onSuccess(Object obj) {
                AutoDisposingObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposableHelper.dispose(AutoDisposingObserverImpl.this.mainDisposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                AutoDisposingObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
                AutoDisposingObserverImpl.this.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                AutoDisposingObserverImpl.this.lifecycleDisposable.lazySet(AutoDisposableHelper.DISPOSED);
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

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        if (isDisposed() || !HalfSerializer.onNext(this.delegate, t, this, this.error)) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        HalfSerializer.onError(this.delegate, th, this, this.error);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        this.mainDisposable.lazySet(AutoDisposableHelper.DISPOSED);
        AutoDisposableHelper.dispose(this.lifecycleDisposable);
        HalfSerializer.onComplete(this.delegate, this, this.error);
    }
}
