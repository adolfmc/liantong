package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SingleTimeout<T> extends Single<T> {
    final SingleSource<? extends T> other;
    final Scheduler scheduler;
    final SingleSource<T> source;
    final long timeout;
    final TimeUnit unit;

    public SingleTimeout(SingleSource<T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, SingleSource<? extends T> singleSource2) {
        this.source = singleSource;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = singleSource2;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        TimeoutMainObserver timeoutMainObserver = new TimeoutMainObserver(singleObserver, this.other);
        singleObserver.onSubscribe(timeoutMainObserver);
        DisposableHelper.replace(timeoutMainObserver.task, this.scheduler.scheduleDirect(timeoutMainObserver, this.timeout, this.unit));
        this.source.subscribe(timeoutMainObserver);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class TimeoutMainObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable, Runnable {
        private static final long serialVersionUID = 37497744973048446L;
        final SingleObserver<? super T> actual;
        final TimeoutFallbackObserver<T> fallback;
        SingleSource<? extends T> other;
        final AtomicReference<Disposable> task = new AtomicReference<>();

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        static final class TimeoutFallbackObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
            private static final long serialVersionUID = 2071387740092105509L;
            final SingleObserver<? super T> actual;

            TimeoutFallbackObserver(SingleObserver<? super T> singleObserver) {
                this.actual = singleObserver;
            }

            @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(T t) {
                this.actual.onSuccess(t);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable th) {
                this.actual.onError(th);
            }
        }

        TimeoutMainObserver(SingleObserver<? super T> singleObserver, SingleSource<? extends T> singleSource) {
            this.actual = singleObserver;
            this.other = singleSource;
            if (singleSource != null) {
                this.fallback = new TimeoutFallbackObserver<>(singleObserver);
            } else {
                this.fallback = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Disposable disposable = get();
            if (disposable == DisposableHelper.DISPOSED || !compareAndSet(disposable, DisposableHelper.DISPOSED)) {
                return;
            }
            if (disposable != null) {
                disposable.dispose();
            }
            SingleSource<? extends T> singleSource = this.other;
            if (singleSource == null) {
                this.actual.onError(new TimeoutException());
                return;
            }
            this.other = null;
            singleSource.subscribe(this.fallback);
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            Disposable disposable = get();
            if (disposable == DisposableHelper.DISPOSED || !compareAndSet(disposable, DisposableHelper.DISPOSED)) {
                return;
            }
            DisposableHelper.dispose(this.task);
            this.actual.onSuccess(t);
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            Disposable disposable = get();
            if (disposable != DisposableHelper.DISPOSED && compareAndSet(disposable, DisposableHelper.DISPOSED)) {
                DisposableHelper.dispose(this.task);
                this.actual.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.task);
            TimeoutFallbackObserver<T> timeoutFallbackObserver = this.fallback;
            if (timeoutFallbackObserver != null) {
                DisposableHelper.dispose(timeoutFallbackObserver);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
