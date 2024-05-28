package com.uber.autodispose;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposeFlowable<T> extends Flowable<T> {
    private final Maybe<?> scope;
    private final Publisher<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposeFlowable(Publisher<T> publisher, Maybe<?> maybe) {
        this.source = publisher;
        this.scope = maybe;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new AutoDisposingSubscriberImpl(this.scope, subscriber));
    }
}
