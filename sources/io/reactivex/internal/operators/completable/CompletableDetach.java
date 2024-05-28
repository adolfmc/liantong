package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

@Experimental
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableDetach extends Completable {
    final CompletableSource source;

    public CompletableDetach(CompletableSource completableSource) {
        this.source = completableSource;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new DetachCompletableObserver(completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DetachCompletableObserver implements CompletableObserver, Disposable {
        CompletableObserver actual;

        /* renamed from: d */
        Disposable f24431d;

        DetachCompletableObserver(CompletableObserver completableObserver) {
            this.actual = completableObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.actual = null;
            this.f24431d.dispose();
            this.f24431d = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24431d.isDisposed();
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24431d, disposable)) {
                this.f24431d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.f24431d = DisposableHelper.DISPOSED;
            CompletableObserver completableObserver = this.actual;
            if (completableObserver != null) {
                this.actual = null;
                completableObserver.onError(th);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            this.f24431d = DisposableHelper.DISPOSED;
            CompletableObserver completableObserver = this.actual;
            if (completableObserver != null) {
                this.actual = null;
                completableObserver.onComplete();
            }
        }
    }
}
