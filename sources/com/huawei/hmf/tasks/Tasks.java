package com.huawei.hmf.tasks;

import com.huawei.hmf.tasks.p212a.C4823i;
import com.huawei.hmf.tasks.p212a.C4831j;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Tasks {
    private static C4831j IMPL = new C4831j();

    public static Task<List<Task<?>>> allOf(Collection<? extends Task<?>> collection) {
        return C4831j.m15352a(collection);
    }

    public static Task<List<Task<?>>> allOf(Task<?>... taskArr) {
        return C4831j.m15352a((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    public static <TResult> TResult await(Task<TResult> task) throws ExecutionException, InterruptedException {
        C4831j.m15353a("await must not be called on the UI thread");
        if (task.isComplete()) {
            return (TResult) C4831j.m15355a((Task<Object>) task);
        }
        C4831j.C4835a c4835a = new C4831j.C4835a();
        task.addOnSuccessListener(c4835a).addOnFailureListener(c4835a);
        c4835a.f10878a.await();
        return (TResult) C4831j.m15355a((Task<Object>) task);
    }

    public static <TResult> TResult await(Task<TResult> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        C4831j.m15353a("await must not be called on the UI thread");
        if (!task.isComplete()) {
            C4831j.C4835a c4835a = new C4831j.C4835a();
            task.addOnSuccessListener(c4835a).addOnFailureListener(c4835a);
            if (!c4835a.f10878a.await(j, timeUnit)) {
                throw new TimeoutException("Timed out waiting for Task");
            }
        }
        return (TResult) C4831j.m15355a((Task<Object>) task);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return IMPL.m15351a(TaskExecutors.immediate(), callable);
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable) {
        return IMPL.m15351a(TaskExecutors.background(), callable);
    }

    public static <TResult> Task<TResult> callInBackground(Executor executor, Callable<TResult> callable) {
        return IMPL.m15351a(executor, callable);
    }

    public static <TResult> Task<TResult> fromCanceled() {
        C4823i c4823i = new C4823i();
        c4823i.m15360a();
        return c4823i;
    }

    public static <TResult> Task<TResult> fromException(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(exc);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> fromResult(TResult tresult) {
        return C4831j.m15354a(tresult);
    }

    public static Task<Void> join(Collection<? extends Task<?>> collection) {
        return C4831j.m15349c(collection);
    }

    public static Task<Void> join(Task<?>... taskArr) {
        return C4831j.m15349c(Arrays.asList(taskArr));
    }

    public static <TResult> Task<List<TResult>> successOf(Collection<? extends Task<TResult>> collection) {
        return C4831j.m15350b(collection);
    }

    public static <TResult> Task<List<TResult>> successOf(Task<?>... taskArr) {
        return C4831j.m15350b(Arrays.asList(taskArr));
    }
}
