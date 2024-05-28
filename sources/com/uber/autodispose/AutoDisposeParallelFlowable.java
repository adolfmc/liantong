package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.parallel.ParallelFlowable;
import org.reactivestreams.Subscriber;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class AutoDisposeParallelFlowable<T> extends ParallelFlowable<T> {
    private final Maybe<?> scope;
    private final ParallelFlowable<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoDisposeParallelFlowable(ParallelFlowable<T> parallelFlowable, Maybe<?> maybe) {
        this.source = parallelFlowable;
        this.scope = maybe;
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[subscriberArr.length];
            for (int i = 0; i < subscriberArr.length; i++) {
                subscriberArr2[i] = new AutoDisposingSubscriberImpl(this.scope, subscriberArr[i]);
            }
            this.source.subscribe(subscriberArr2);
        }
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public int parallelism() {
        return this.source.parallelism();
    }
}
