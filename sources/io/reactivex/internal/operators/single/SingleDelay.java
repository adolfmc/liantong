package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SingleDelay<T> extends Single<T> {
    final boolean delayError;
    final Scheduler scheduler;
    final SingleSource<? extends T> source;
    final long time;
    final TimeUnit unit;

    public SingleDelay(SingleSource<? extends T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.source = singleSource;
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        singleObserver.onSubscribe(sequentialDisposable);
        this.source.subscribe(new Delay(sequentialDisposable, singleObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class Delay implements SingleObserver<T> {

        /* renamed from: s */
        final SingleObserver<? super T> f24738s;

        /* renamed from: sd */
        private final SequentialDisposable f24739sd;

        Delay(SequentialDisposable sequentialDisposable, SingleObserver<? super T> singleObserver) {
            this.f24739sd = sequentialDisposable;
            this.f24738s = singleObserver;
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.f24739sd.replace(disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.f24739sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnSuccess(t), SingleDelay.this.time, SingleDelay.this.unit));
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.f24739sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnError(th), SingleDelay.this.delayError ? SingleDelay.this.time : 0L, SingleDelay.this.unit));
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnSuccess implements Runnable {
            private final T value;

            OnSuccess(T t) {
                this.value = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                Delay.this.f24738s.onSuccess((T) this.value);
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnError implements Runnable {

            /* renamed from: e */
            private final Throwable f24740e;

            OnError(Throwable th) {
                this.f24740e = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                Delay.this.f24738s.onError(this.f24740e);
            }
        }
    }
}
