package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class BasicFuseableObserver<T, R> implements Observer<T>, QueueDisposable<R> {
    protected final Observer<? super R> actual;
    protected boolean done;

    /* renamed from: qs */
    protected QueueDisposable<T> f24406qs;

    /* renamed from: s */
    protected Disposable f24407s;
    protected int sourceMode;

    protected void afterDownstream() {
    }

    protected boolean beforeDownstream() {
        return true;
    }

    public BasicFuseableObserver(Observer<? super R> observer) {
        this.actual = observer;
    }

    @Override // io.reactivex.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.f24407s, disposable)) {
            this.f24407s = disposable;
            if (disposable instanceof QueueDisposable) {
                this.f24406qs = (QueueDisposable) disposable;
            }
            if (beforeDownstream()) {
                this.actual.onSubscribe(this);
                afterDownstream();
            }
        }
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        this.actual.onError(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void fail(Throwable th) {
        Exceptions.throwIfFatal(th);
        this.f24407s.dispose();
        onError(th);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.actual.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int transitiveBoundaryFusion(int i) {
        QueueDisposable<T> queueDisposable = this.f24406qs;
        if (queueDisposable == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = queueDisposable.requestFusion(i);
        if (requestFusion != 0) {
            this.sourceMode = requestFusion;
        }
        return requestFusion;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.f24407s.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.f24407s.isDisposed();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return this.f24406qs.isEmpty();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        this.f24406qs.clear();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public final boolean offer(R r, R r2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
