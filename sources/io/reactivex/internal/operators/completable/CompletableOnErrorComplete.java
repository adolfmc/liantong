package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CompletableOnErrorComplete extends Completable {
    final Predicate<? super Throwable> predicate;
    final CompletableSource source;

    public CompletableOnErrorComplete(CompletableSource completableSource, Predicate<? super Throwable> predicate) {
        this.source = completableSource;
        this.predicate = predicate;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new OnError(completableObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    final class OnError implements CompletableObserver {

        /* renamed from: s */
        private final CompletableObserver f24441s;

        OnError(CompletableObserver completableObserver) {
            this.f24441s = completableObserver;
        }

        @Override // io.reactivex.CompletableObserver
        public void onComplete() {
            this.f24441s.onComplete();
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            try {
                if (CompletableOnErrorComplete.this.predicate.test(th)) {
                    this.f24441s.onComplete();
                } else {
                    this.f24441s.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.f24441s.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.f24441s.onSubscribe(disposable);
        }
    }
}
