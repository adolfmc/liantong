package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ObservableScoper<T> extends BaseAutoDisposeConverter implements Function<Observable<? extends T>, ObservableSubscribeProxy<T>> {
    @Override // io.reactivex.functions.Function
    public /* bridge */ /* synthetic */ Object apply(Object obj) throws Exception {
        return apply((Observable) ((Observable) obj));
    }

    public ObservableScoper(ScopeProvider scopeProvider) {
        super(scopeProvider);
    }

    public ObservableScoper(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        super(lifecycleScopeProvider);
    }

    public ObservableScoper(Maybe<?> maybe) {
        super(maybe);
    }

    public ObservableSubscribeProxy<T> apply(Observable<? extends T> observable) {
        return (ObservableSubscribeProxy) observable.map(BaseAutoDisposeConverter.identityFunctionForGenerics()).m1940as(AutoDispose.autoDisposable(scope()));
    }
}
