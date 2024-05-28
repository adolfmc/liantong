package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class BaseAutoDisposeConverter {
    private static final Function<?, ?> IDENTITY_FUNCTION = new Function<Object, Object>() { // from class: com.uber.autodispose.BaseAutoDisposeConverter.1
        @Override // io.reactivex.functions.Function
        public Object apply(Object obj) {
            return obj;
        }
    };
    private final Maybe<?> scope;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static <T> Function<T, T> identityFunctionForGenerics() {
        return (Function<T, T>) IDENTITY_FUNCTION;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseAutoDisposeConverter(final ScopeProvider scopeProvider) {
        this(Maybe.defer(new Callable<MaybeSource<?>>() { // from class: com.uber.autodispose.BaseAutoDisposeConverter.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MaybeSource<?> call() throws Exception {
                return ScopeProvider.this.requestScope();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseAutoDisposeConverter(LifecycleScopeProvider<?> lifecycleScopeProvider) {
        this(ScopeUtil.deferredResolvedLifecycle((LifecycleScopeProvider) AutoDisposeUtil.checkNotNull(lifecycleScopeProvider, "provider == null")));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseAutoDisposeConverter(Maybe<?> maybe) {
        this.scope = (Maybe) AutoDisposeUtil.checkNotNull(maybe, "scope == null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Maybe<?> scope() {
        return this.scope;
    }
}
