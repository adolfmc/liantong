package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ObserverFullArbiter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FullArbiterObserver<T> implements Observer<T> {
    final ObserverFullArbiter<T> arbiter;

    /* renamed from: s */
    Disposable f24412s;

    public FullArbiterObserver(ObserverFullArbiter<T> observerFullArbiter) {
        this.arbiter = observerFullArbiter;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f24412s, disposable)) {
            this.f24412s = disposable;
            this.arbiter.setDisposable(disposable);
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.arbiter.onNext(t, this.f24412s);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.arbiter.onError(th, this.f24412s);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        this.arbiter.onComplete(this.f24412s);
    }
}
