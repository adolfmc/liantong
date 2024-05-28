package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CompletableScoper extends BaseAutoDisposeConverter implements Function<Completable, CompletableSubscribeProxy> {
    public CompletableScoper(ScopeProvider scopeProvider) {
        super(scopeProvider);
    }

    public CompletableScoper(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        super(lifecycleScopeProvider);
    }

    public CompletableScoper(Maybe<?> maybe) {
        super(maybe);
    }

    @Override // io.reactivex.functions.Function
    public CompletableSubscribeProxy apply(Completable completable) {
        return (CompletableSubscribeProxy) completable.m1946as(AutoDispose.autoDisposable(scope()));
    }
}
