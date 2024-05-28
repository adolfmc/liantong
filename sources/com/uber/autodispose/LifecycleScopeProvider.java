package com.uber.autodispose;

import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.functions.Function;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@DoNotMock("Use TestLifecycleScopeProvider instead")
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface LifecycleScopeProvider<E> {
    @CheckReturnValue
    Function<E, E> correspondingEvents();

    @CheckReturnValue
    Observable<E> lifecycle();

    @Nullable
    E peekLifecycle();
}
