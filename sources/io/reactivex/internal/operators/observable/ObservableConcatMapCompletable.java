package io.reactivex.internal.operators.observable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ObservableConcatMapCompletable<T> extends Completable {
    final int bufferSize;
    final Function<? super T, ? extends CompletableSource> mapper;
    final ObservableSource<T> source;

    public ObservableConcatMapCompletable(ObservableSource<T> observableSource, Function<? super T, ? extends CompletableSource> function, int i) {
        this.source = observableSource;
        this.mapper = function;
        this.bufferSize = Math.max(8, i);
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new SourceObserver(completableObserver, this.mapper, this.bufferSize));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class SourceObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 6893587405571511048L;
        volatile boolean active;
        final CompletableObserver actual;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        final InnerObserver inner;
        final Function<? super T, ? extends CompletableSource> mapper;
        SimpleQueue<T> queue;

        /* renamed from: s */
        Disposable f24623s;
        int sourceMode;

        SourceObserver(CompletableObserver completableObserver, Function<? super T, ? extends CompletableSource> function, int i) {
            this.actual = completableObserver;
            this.mapper = function;
            this.bufferSize = i;
            this.inner = new InnerObserver(completableObserver, this);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24623s, disposable)) {
                this.f24623s = disposable;
                if (disposable instanceof QueueDisposable) {
                    QueueDisposable queueDisposable = (QueueDisposable) disposable;
                    int requestFusion = queueDisposable.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueDisposable;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueDisposable;
                        this.actual.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode == 0) {
                this.queue.offer(t);
            }
            drain();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            dispose();
            this.actual.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.disposed = true;
            this.inner.dispose();
            this.f24623s.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            while (!this.disposed) {
                if (!this.active) {
                    boolean z = this.done;
                    try {
                        T poll = this.queue.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            this.disposed = true;
                            this.actual.onComplete();
                            return;
                        } else if (!z2) {
                            try {
                                CompletableSource completableSource = (CompletableSource) ObjectHelper.requireNonNull(this.mapper.apply(poll), "The mapper returned a null CompletableSource");
                                this.active = true;
                                completableSource.subscribe(this.inner);
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                dispose();
                                this.queue.clear();
                                this.actual.onError(th);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        dispose();
                        this.queue.clear();
                        this.actual.onError(th2);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            this.queue.clear();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static final class InnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -5987419458390772447L;
            final CompletableObserver actual;
            final SourceObserver<?> parent;

            InnerObserver(CompletableObserver completableObserver, SourceObserver<?> sourceObserver) {
                this.actual = completableObserver;
                this.parent = sourceObserver;
            }

            @Override // io.reactivex.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.set(this, disposable);
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                this.parent.dispose();
                this.actual.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onComplete() {
                this.parent.innerComplete();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
