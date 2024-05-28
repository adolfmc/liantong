package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableDelay<T> extends AbstractObservableWithUpstream<T, T> {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final TimeUnit unit;

    public ObservableDelay(ObservableSource<T> observableSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(observableSource);
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DelayObserver(this.delayError ? observer : new SerializedObserver(observer), this.delay, this.unit, this.scheduler.createWorker(), this.delayError));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DelayObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        final long delay;
        final boolean delayError;

        /* renamed from: s */
        Disposable f24629s;
        final TimeUnit unit;

        /* renamed from: w */
        final Scheduler.Worker f24630w;

        DelayObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
            this.actual = observer;
            this.delay = j;
            this.unit = timeUnit;
            this.f24630w = worker;
            this.delayError = z;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24629s, disposable)) {
                this.f24629s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.f24630w.schedule(new OnNext(t), this.delay, this.unit);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.f24630w.schedule(new OnError(th), this.delayError ? this.delay : 0L, this.unit);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.f24630w.schedule(new OnComplete(), this.delay, this.unit);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24629s.dispose();
            this.f24630w.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24630w.isDisposed();
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnNext implements Runnable {

            /* renamed from: t */
            private final T f24631t;

            OnNext(T t) {
                this.f24631t = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                DelayObserver.this.actual.onNext((T) this.f24631t);
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnError implements Runnable {
            private final Throwable throwable;

            OnError(Throwable th) {
                this.throwable = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DelayObserver.this.actual.onError(this.throwable);
                } finally {
                    DelayObserver.this.f24630w.dispose();
                }
            }
        }

        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        final class OnComplete implements Runnable {
            OnComplete() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DelayObserver.this.actual.onComplete();
                } finally {
                    DelayObserver.this.f24630w.dispose();
                }
            }
        }
    }
}
