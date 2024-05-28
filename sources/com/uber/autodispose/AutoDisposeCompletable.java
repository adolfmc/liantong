package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposeCompletable extends Completable {
    private final Maybe<?> scope;
    private final Completable source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposeCompletable(Completable completable, Maybe<?> maybe) {
        this.source = completable;
        this.scope = maybe;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new AutoDisposingCompletableObserverImpl(this.scope, completableObserver));
    }
}
