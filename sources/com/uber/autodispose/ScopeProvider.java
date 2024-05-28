package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.annotations.CheckReturnValue;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@DoNotMock("Use TestScopeProvider instead")
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ScopeProvider {
    public static final ScopeProvider UNBOUND = new ScopeProvider() { // from class: com.uber.autodispose.ScopeProvider.1
        @Override // com.uber.autodispose.ScopeProvider
        public Maybe<?> requestScope() {
            return Maybe.empty();
        }
    };

    @CheckReturnValue
    Maybe<?> requestScope();
}
