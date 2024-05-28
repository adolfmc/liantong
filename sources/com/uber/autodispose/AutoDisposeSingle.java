package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposeSingle<T> extends Single<T> {
    private final Maybe<?> scope;
    private final SingleSource<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposeSingle(SingleSource<T> singleSource, Maybe<?> maybe) {
        this.source = singleSource;
        this.scope = maybe;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new AutoDisposingSingleObserverImpl(this.scope, singleObserver));
    }
}
