package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.SequentialDisposable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableResumeNext extends Completable {
    final Function<? super Throwable, ? extends CompletableSource> errorMapper;
    final CompletableSource source;

    public CompletableResumeNext(CompletableSource completableSource, Function<? super Throwable, ? extends CompletableSource> function) {
        this.source = completableSource;
        this.errorMapper = function;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        completableObserver.onSubscribe(sequentialDisposable);
        this.source.subscribe(new ResumeNext(completableObserver, sequentialDisposable));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class ResumeNext implements CompletableObserver {

        /* renamed from: s */
        final CompletableObserver f24443s;

        /* renamed from: sd */
        final SequentialDisposable f24444sd;

        ResumeNext(CompletableObserver completableObserver, SequentialDisposable sequentialDisposable) {
            this.f24443s = completableObserver;
            this.f24444sd = sequentialDisposable;
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            this.f24443s.onComplete();
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            try {
                CompletableSource apply = CompletableResumeNext.this.errorMapper.apply(th);
                if (apply == null) {
                    NullPointerException nullPointerException = new NullPointerException("The CompletableConsumable returned is null");
                    nullPointerException.initCause(th);
                    this.f24443s.onError(nullPointerException);
                    return;
                }
                apply.subscribe(new OnErrorObserver());
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.f24443s.onError(new CompositeException(th2, th));
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.f24444sd.update(disposable);
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        final class OnErrorObserver implements CompletableObserver {
            OnErrorObserver() {
            }

            @Override // io.reactivex.CompletableObserver
            public void onComplete() {
                ResumeNext.this.f24443s.onComplete();
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                ResumeNext.this.f24443s.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                ResumeNext.this.f24444sd.update(disposable);
            }
        }
    }
}
