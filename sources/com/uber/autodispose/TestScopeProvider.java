package com.uber.autodispose;

import com.uber.autodispose.ScopeUtil;
import io.reactivex.Maybe;
import io.reactivex.subjects.MaybeSubject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class TestScopeProvider implements ScopeProvider {
    private final MaybeSubject<Object> innerMaybe = MaybeSubject.create();

    public static TestScopeProvider create() {
        return create(MaybeSubject.create());
    }

    public static TestScopeProvider create(Maybe<?> maybe) {
        return new TestScopeProvider(maybe);
    }

    @Deprecated
    public static TestScopeProvider unbound() {
        return create(Maybe.empty());
    }

    private TestScopeProvider(Maybe<?> maybe) {
        maybe.subscribe(this.innerMaybe);
    }

    @Override // com.uber.autodispose.ScopeProvider
    public Maybe<?> requestScope() {
        return this.innerMaybe;
    }

    public void emit() {
        this.innerMaybe.onSuccess(ScopeUtil.LifecycleEndNotification.INSTANCE);
    }
}
