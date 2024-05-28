package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableHide extends Completable {
    final CompletableSource source;

    public CompletableHide(CompletableSource completableSource) {
        this.source = completableSource;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new HideCompletableObserver(completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class HideCompletableObserver implements CompletableObserver, Disposable {
        final CompletableObserver actual;

        /* renamed from: d */
        Disposable f24439d;

        HideCompletableObserver(CompletableObserver completableObserver) {
            this.actual = completableObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24439d.dispose();
            this.f24439d = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24439d.isDisposed();
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24439d, disposable)) {
                this.f24439d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            this.actual.onComplete();
        }
    }
}
