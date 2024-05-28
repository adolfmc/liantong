package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface SchedulerMultiWorkerSupport {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface WorkerCallback {
        void onWorker(int i, @NonNull Scheduler.Worker worker);
    }

    void createWorkers(int i, @NonNull WorkerCallback workerCallback);
}
