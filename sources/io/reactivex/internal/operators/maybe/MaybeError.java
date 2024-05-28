package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposables;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class MaybeError<T> extends Maybe<T> {
    final Throwable error;

    public MaybeError(Throwable th) {
        this.error = th;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        maybeObserver.onSubscribe(Disposables.disposed());
        maybeObserver.onError(this.error);
    }
}
