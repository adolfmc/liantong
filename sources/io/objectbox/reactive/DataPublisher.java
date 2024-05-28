package io.objectbox.reactive;

import io.objectbox.annotation.apihint.Internal;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface DataPublisher<T> {
    void publishSingle(DataObserver<T> dataObserver, @Nullable Object obj);

    void subscribe(DataObserver<T> dataObserver, @Nullable Object obj);

    void unsubscribe(DataObserver<T> dataObserver, @Nullable Object obj);
}
