package io.objectbox.reactive;

import io.objectbox.annotation.apihint.Internal;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DataPublisherUtils {
    public static <T> void removeObserverFromCopyOnWriteSet(Set<DataObserver<T>> set, DataObserver<T> dataObserver) {
        if (set != null) {
            for (DataObserver<T> dataObserver2 : set) {
                if (dataObserver2.equals(dataObserver)) {
                    set.remove(dataObserver2);
                } else if (dataObserver2 instanceof DelegatingObserver) {
                    DataObserver<T> dataObserver3 = dataObserver2;
                    while (dataObserver3 instanceof DelegatingObserver) {
                        dataObserver3 = ((DelegatingObserver) dataObserver3).getObserverDelegate();
                    }
                    if (dataObserver3 == null || dataObserver3.equals(dataObserver)) {
                        set.remove(dataObserver2);
                    }
                }
            }
        }
    }
}
