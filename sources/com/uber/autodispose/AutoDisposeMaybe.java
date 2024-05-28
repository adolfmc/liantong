package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposeMaybe<T> extends Maybe<T> {
    private final Maybe<?> scope;
    private final MaybeSource<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposeMaybe(MaybeSource<T> maybeSource, Maybe<?> maybe) {
        this.source = maybeSource;
        this.scope = maybe;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new AutoDisposingMaybeObserverImpl(this.scope, maybeObserver));
    }
}
