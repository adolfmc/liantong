package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletablePeek extends Completable {
    final Action onAfterTerminate;
    final Action onComplete;
    final Action onDispose;
    final Consumer<? super Throwable> onError;
    final Consumer<? super Disposable> onSubscribe;
    final Action onTerminate;
    final CompletableSource source;

    public CompletablePeek(CompletableSource completableSource, Consumer<? super Disposable> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2, Action action3, Action action4) {
        this.source = completableSource;
        this.onSubscribe = consumer;
        this.onError = consumer2;
        this.onComplete = action;
        this.onTerminate = action2;
        this.onAfterTerminate = action3;
        this.onDispose = action4;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new CompletableObserverImplementation(completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class CompletableObserverImplementation implements CompletableObserver, Disposable {
        final CompletableObserver actual;

        /* renamed from: d */
        Disposable f24442d;

        CompletableObserverImplementation(CompletableObserver completableObserver) {
            this.actual = completableObserver;
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            try {
                CompletablePeek.this.onSubscribe.accept(disposable);
                if (DisposableHelper.validate(this.f24442d, disposable)) {
                    this.f24442d = disposable;
                    this.actual.onSubscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                disposable.dispose();
                this.f24442d = DisposableHelper.DISPOSED;
                EmptyDisposable.error(th, this.actual);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            if (this.f24442d == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(th);
                return;
            }
            try {
                CompletablePeek.this.onError.accept(th);
                CompletablePeek.this.onTerminate.run();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.actual.onError(th);
            doAfter();
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            if (this.f24442d == DisposableHelper.DISPOSED) {
                return;
            }
            try {
                CompletablePeek.this.onComplete.run();
                CompletablePeek.this.onTerminate.run();
                this.actual.onComplete();
                doAfter();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.actual.onError(th);
            }
        }

        void doAfter() {
            try {
                CompletablePeek.this.onAfterTerminate.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            try {
                CompletablePeek.this.onDispose.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.f24442d.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24442d.isDisposed();
        }
    }
}
