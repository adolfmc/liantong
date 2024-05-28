package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposeObservable<T> extends Observable<T> {
    private final Maybe<?> scope;
    private final ObservableSource<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposeObservable(ObservableSource<T> observableSource, Maybe<?> maybe) {
        this.source = observableSource;
        this.scope = maybe;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new AutoDisposingObserverImpl(this.scope, observer));
    }
}
