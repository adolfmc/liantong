package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.plugins.RxJavaPlugins;

@Experimental
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableDoFinally<T> extends AbstractObservableWithUpstream<T, T> {
    final Action onFinally;

    public ObservableDoFinally(ObservableSource<T> observableSource, Action action) {
        super(observableSource);
        this.onFinally = action;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DoFinallyObserver(observer, this.onFinally));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final Observer<? super T> actual;

        /* renamed from: d */
        Disposable f24634d;
        final Action onFinally;

        /* renamed from: qd */
        QueueDisposable<T> f24635qd;
        boolean syncFused;

        DoFinallyObserver(Observer<? super T> observer, Action action) {
            this.actual = observer;
            this.onFinally = action;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24634d, disposable)) {
                this.f24634d = disposable;
                if (disposable instanceof QueueDisposable) {
                    this.f24635qd = (QueueDisposable) disposable;
                }
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.actual.onError(th);
            runFinally();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24634d.dispose();
            runFinally();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24634d.isDisposed();
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            QueueDisposable<T> queueDisposable = this.f24635qd;
            if (queueDisposable == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = queueDisposable.requestFusion(i);
            if (requestFusion != 0) {
                this.syncFused = requestFusion == 1;
            }
            return requestFusion;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.f24635qd.clear();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.f24635qd.isEmpty();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            T poll = this.f24635qd.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
