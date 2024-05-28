package com.huawei.hmf.tasks.p212a;

import android.os.Looper;
import com.huawei.hmf.tasks.Continuation;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* renamed from: com.huawei.hmf.tasks.a.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4831j {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hmf.tasks.a.j$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4835a<TResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TResult> {

        /* renamed from: a */
        public final CountDownLatch f10878a = new CountDownLatch(1);

        @Override // com.huawei.hmf.tasks.OnCanceledListener
        public final void onCanceled() {
            this.f10878a.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public final void onFailure(Exception exc) {
            this.f10878a.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public final void onSuccess(TResult tresult) {
            this.f10878a.countDown();
        }
    }

    /* renamed from: a */
    public static <TResult> Task<TResult> m15354a(TResult tresult) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    /* renamed from: a */
    public static Task<List<Task<?>>> m15352a(final Collection<? extends Task<?>> collection) {
        return m15349c(collection).continueWith(new Continuation<Void, List<Task<?>>>() { // from class: com.huawei.hmf.tasks.a.j.2
            @Override // com.huawei.hmf.tasks.Continuation
            public final /* synthetic */ List<Task<?>> then(Task<Void> task) throws Exception {
                ArrayList arrayList = new ArrayList(collection.size());
                arrayList.addAll(collection);
                return arrayList;
            }
        });
    }

    /* renamed from: a */
    public static <TResult> TResult m15355a(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    /* renamed from: a */
    public static void m15353a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(str);
        }
    }

    /* renamed from: b */
    public static <TResult> Task<List<TResult>> m15350b(final Collection<? extends Task<?>> collection) {
        return (Task<List<TResult>>) m15349c(collection).continueWith((Continuation<Void, List<TResult>>) new Continuation<Void, List<TResult>>() { // from class: com.huawei.hmf.tasks.a.j.3
            @Override // com.huawei.hmf.tasks.Continuation
            public final /* synthetic */ Object then(Task<Void> task) throws Exception {
                ArrayList arrayList = new ArrayList();
                for (Task task2 : collection) {
                    arrayList.add(task2.getResult());
                }
                return arrayList;
            }
        });
    }

    /* renamed from: c */
    public static Task<Void> m15349c(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return m15354a((Object) null);
        }
        for (Task<?> task : collection) {
            if (task == null) {
                throw new NullPointerException("task can not is null");
            }
        }
        C4823i c4823i = new C4823i();
        C4817e c4817e = new C4817e(collection.size(), c4823i);
        for (Task<?> task2 : collection) {
            task2.addOnSuccessListener(TaskExecutors.immediate(), c4817e);
            task2.addOnFailureListener(TaskExecutors.immediate(), c4817e);
            task2.addOnCanceledListener(TaskExecutors.immediate(), c4817e);
        }
        return c4823i;
    }

    /* renamed from: a */
    public final <TResult> Task<TResult> m15351a(Executor executor, final Callable<TResult> callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.j.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        taskCompletionSource.setResult(callable.call());
                    } catch (Exception e) {
                        taskCompletionSource.setException(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
        return taskCompletionSource.getTask();
    }
}
