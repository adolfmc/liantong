package io.objectbox.reactive;

import io.objectbox.annotation.apihint.Internal;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface DelegatingObserver<T> {
    DataObserver<T> getObserverDelegate();
}
