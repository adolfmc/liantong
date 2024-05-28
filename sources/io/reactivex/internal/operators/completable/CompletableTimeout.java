package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableTimeout extends Completable {
    final CompletableSource other;
    final Scheduler scheduler;
    final CompletableSource source;
    final long timeout;
    final TimeUnit unit;

    public CompletableTimeout(CompletableSource completableSource, long j, TimeUnit timeUnit, Scheduler scheduler, CompletableSource completableSource2) {
        this.source = completableSource;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = completableSource2;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        completableObserver.onSubscribe(compositeDisposable);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        compositeDisposable.add(this.scheduler.scheduleDirect(new DisposeTask(atomicBoolean, compositeDisposable, completableObserver), this.timeout, this.unit));
        this.source.subscribe(new TimeOutObserver(compositeDisposable, atomicBoolean, completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class TimeOutObserver implements CompletableObserver {
        private final AtomicBoolean once;

        /* renamed from: s */
        private final CompletableObserver f24446s;
        private final CompositeDisposable set;

        TimeOutObserver(CompositeDisposable compositeDisposable, AtomicBoolean atomicBoolean, CompletableObserver completableObserver) {
            this.set = compositeDisposable;
            this.once = atomicBoolean;
            this.f24446s = completableObserver;
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f24446s.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f24446s.onComplete();
            }
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class DisposeTask implements Runnable {
        private final AtomicBoolean once;

        /* renamed from: s */
        final CompletableObserver f24445s;
        final CompositeDisposable set;

        DisposeTask(AtomicBoolean atomicBoolean, CompositeDisposable compositeDisposable, CompletableObserver completableObserver) {
            this.once = atomicBoolean;
            this.set = compositeDisposable;
            this.f24445s = completableObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.set.clear();
                if (CompletableTimeout.this.other == null) {
                    this.f24445s.onError(new TimeoutException());
                } else {
                    CompletableTimeout.this.other.subscribe(new DisposeObserver());
                }
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class DisposeObserver implements CompletableObserver {
            DisposeObserver() {
            }

            @Override // io.reactivex.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposeTask.this.set.add(disposable);
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                DisposeTask.this.set.dispose();
                DisposeTask.this.f24445s.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onComplete() {
                DisposeTask.this.set.dispose();
                DisposeTask.this.f24445s.onComplete();
            }
        }
    }
}
