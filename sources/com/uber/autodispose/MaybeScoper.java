package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;

@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MaybeScoper<T> extends BaseAutoDisposeConverter implements Function<Maybe<? extends T>, MaybeSubscribeProxy<T>> {
    @Override // io.reactivex.functions.Function
    public /* bridge */ /* synthetic */ Object apply(Object obj) throws Exception {
        return apply((Maybe) ((Maybe) obj));
    }

    public MaybeScoper(ScopeProvider scopeProvider) {
        super(scopeProvider);
    }

    public MaybeScoper(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        super(lifecycleScopeProvider);
    }

    public MaybeScoper(Maybe<?> maybe) {
        super(maybe);
    }

    public MaybeSubscribeProxy<T> apply(Maybe<? extends T> maybe) throws Exception {
        return (MaybeSubscribeProxy) maybe.map(BaseAutoDisposeConverter.identityFunctionForGenerics()).m1942as(AutoDispose.autoDisposable(scope()));
    }
}
