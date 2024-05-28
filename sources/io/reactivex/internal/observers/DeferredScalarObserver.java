package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -266195175408988651L;

    /* renamed from: s */
    protected Disposable f24410s;

    public DeferredScalarObserver(Observer<? super R> observer) {
        super(observer);
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f24410s, disposable)) {
            this.f24410s = disposable;
            this.actual.onSubscribe(this);
        }
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.value = null;
        error(th);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        T t = this.value;
        if (t != null) {
            this.value = null;
            complete(t);
            return;
        }
        complete();
    }

    @Override // io.reactivex.internal.observers.DeferredScalarDisposable, io.reactivex.disposables.Disposable
    public void dispose() {
        super.dispose();
        this.f24410s.dispose();
    }
}
