package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.HasUpstreamSingleSource;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class MaybeFromSingle<T> extends Maybe<T> implements HasUpstreamSingleSource<T> {
    final SingleSource<T> source;

    public MaybeFromSingle(SingleSource<T> singleSource) {
        this.source = singleSource;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamSingleSource
    public SingleSource<T> source() {
        return this.source;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new FromSingleObserver(maybeObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class FromSingleObserver<T> implements SingleObserver<T>, Disposable {
        final MaybeObserver<? super T> actual;

        /* renamed from: d */
        Disposable f24587d;

        FromSingleObserver(MaybeObserver<? super T> maybeObserver) {
            this.actual = maybeObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.f24587d.dispose();
            this.f24587d = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24587d.isDisposed();
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24587d, disposable)) {
                this.f24587d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.f24587d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(t);
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.f24587d = DisposableHelper.DISPOSED;
            this.actual.onError(th);
        }
    }
}
