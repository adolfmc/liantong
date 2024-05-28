package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableDisposeOn extends Completable {
    final Scheduler scheduler;
    final CompletableSource source;

    public CompletableDisposeOn(CompletableSource completableSource, Scheduler scheduler) {
        this.source = completableSource;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new CompletableObserverImplementation(completableObserver, this.scheduler));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class CompletableObserverImplementation implements CompletableObserver, Disposable, Runnable {

        /* renamed from: d */
        Disposable f24432d;
        volatile boolean disposed;

        /* renamed from: s */
        final CompletableObserver f24433s;
        final Scheduler scheduler;

        CompletableObserverImplementation(CompletableObserver completableObserver, Scheduler scheduler) {
            this.f24433s = completableObserver;
            this.scheduler = scheduler;
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            if (this.disposed) {
                return;
            }
            this.f24433s.onComplete();
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            if (this.disposed) {
                RxJavaPlugins.onError(th);
            } else {
                this.f24433s.onError(th);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24432d, disposable)) {
                this.f24432d = disposable;
                this.f24433s.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.disposed = true;
            this.scheduler.scheduleDirect(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f24432d.dispose();
            this.f24432d = DisposableHelper.DISPOSED;
        }
    }
}
