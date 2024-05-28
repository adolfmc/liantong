package io.reactivex.schedulers;

import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface SchedulerRunnableIntrospection {
    @NonNull
    Runnable getWrappedRunnable();
}
