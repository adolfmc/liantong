package com.uber.autodispose;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class FlowableScoper<T> extends BaseAutoDisposeConverter implements Function<Flowable<? extends T>, FlowableSubscribeProxy<T>> {
    @Override // io.reactivex.functions.Function
    public /* bridge */ /* synthetic */ Object apply(Object obj) throws Exception {
        return apply((Flowable) ((Flowable) obj));
    }

    public FlowableScoper(ScopeProvider scopeProvider) {
        super(scopeProvider);
    }

    public FlowableScoper(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        super(lifecycleScopeProvider);
    }

    public FlowableScoper(Maybe<?> maybe) {
        super(maybe);
    }

    public FlowableSubscribeProxy<T> apply(Flowable<? extends T> flowable) throws Exception {
        return (FlowableSubscribeProxy) flowable.map(BaseAutoDisposeConverter.identityFunctionForGenerics()).m1944as(AutoDispose.autoDisposable(scope()));
    }
}
