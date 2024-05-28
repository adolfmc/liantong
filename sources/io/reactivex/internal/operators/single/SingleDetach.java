package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

@Experimental
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SingleDetach<T> extends Single<T> {
    final SingleSource<T> source;

    public SingleDetach(SingleSource<T> singleSource) {
        this.source = singleSource;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DetachSingleObserver(singleObserver));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class DetachSingleObserver<T> implements SingleObserver<T>, Disposable {
        SingleObserver<? super T> actual;

        /* renamed from: d */
        Disposable f24742d;

        DetachSingleObserver(SingleObserver<? super T> singleObserver) {
            this.actual = singleObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.actual = null;
            this.f24742d.dispose();
            this.f24742d = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.f24742d.isDisposed();
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f24742d, disposable)) {
                this.f24742d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.f24742d = DisposableHelper.DISPOSED;
            SingleObserver<? super T> singleObserver = this.actual;
            if (singleObserver != null) {
                this.actual = null;
                singleObserver.onSuccess(t);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.f24742d = DisposableHelper.DISPOSED;
            SingleObserver<? super T> singleObserver = this.actual;
            if (singleObserver != null) {
                this.actual = null;
                singleObserver.onError(th);
            }
        }
    }
}
