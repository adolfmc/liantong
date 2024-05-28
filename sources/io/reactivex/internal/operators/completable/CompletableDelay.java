package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableDelay extends Completable {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final CompletableSource source;
    final TimeUnit unit;

    public CompletableDelay(CompletableSource completableSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.source = completableSource;
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new Delay(new CompositeDisposable(), completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class Delay implements CompletableObserver {

        /* renamed from: s */
        final CompletableObserver f24429s;
        private final CompositeDisposable set;

        Delay(CompositeDisposable compositeDisposable, CompletableObserver completableObserver) {
            this.set = compositeDisposable;
            this.f24429s = completableObserver;
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            this.set.add(CompletableDelay.this.scheduler.scheduleDirect(new OnComplete(), CompletableDelay.this.delay, CompletableDelay.this.unit));
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.set.add(CompletableDelay.this.scheduler.scheduleDirect(new OnError(th), CompletableDelay.this.delayError ? CompletableDelay.this.delay : 0L, CompletableDelay.this.unit));
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
            this.f24429s.onSubscribe(this.set);
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        final class OnComplete implements Runnable {
            OnComplete() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Delay.this.f24429s.onComplete();
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnError implements Runnable {

            /* renamed from: e */
            private final Throwable f24430e;

            OnError(Throwable th) {
                this.f24430e = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                Delay.this.f24429s.onError(this.f24430e);
            }
        }
    }
}
