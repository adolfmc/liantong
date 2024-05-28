package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(@NonNull Runnable runnable);

    public abstract boolean isMainThread();

    public abstract void postToMainThread(@NonNull Runnable runnable);

    public void executeOnMainThread(@NonNull Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            postToMainThread(runnable);
        }
    }
}
