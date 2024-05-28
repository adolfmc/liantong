package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SingleScoper<T> extends BaseAutoDisposeConverter implements Function<Single<? extends T>, SingleSubscribeProxy<T>> {
    @Override // io.reactivex.functions.Function
    public /* bridge */ /* synthetic */ Object apply(Object obj) throws Exception {
        return apply((Single) ((Single) obj));
    }

    public SingleScoper(ScopeProvider scopeProvider) {
        super(scopeProvider);
    }

    public SingleScoper(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        super(lifecycleScopeProvider);
    }

    public SingleScoper(Maybe<?> maybe) {
        super(maybe);
    }

    public SingleSubscribeProxy<T> apply(Single<? extends T> single) throws Exception {
        return (SingleSubscribeProxy) single.map(BaseAutoDisposeConverter.identityFunctionForGenerics()).m1938as(AutoDispose.autoDisposable(scope()));
    }
}
