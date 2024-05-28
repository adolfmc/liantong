package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class MaybeDetach<T> extends AbstractMaybeWithUpstream<T, T> {
    public MaybeDetach(MaybeSource<T> maybeSource) {
        super(maybeSource);
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DetachMaybeObserver(maybeObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DetachMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        MaybeObserver<? super T> actual;

        /* renamed from: d */
        Disposable f24574d;

        DetachMaybeObserver(MaybeObserver<? super T> maybeObserver) {
            this.actual = maybeObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.actual = null;
            this.f24574d.dispose();
            this.f24574d = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24574d.isDisposed();
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24574d, disposable)) {
                this.f24574d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            this.f24574d = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.actual;
            if (maybeObserver != null) {
                this.actual = null;
                maybeObserver.onSuccess(t);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.f24574d = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.actual;
            if (maybeObserver != null) {
                this.actual = null;
                maybeObserver.onError(th);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.f24574d = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.actual;
            if (maybeObserver != null) {
                this.actual = null;
                maybeObserver.onComplete();
            }
        }
    }
}
