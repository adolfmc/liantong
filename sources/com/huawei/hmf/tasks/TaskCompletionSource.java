package com.huawei.hmf.tasks;

import com.huawei.hmf.tasks.p212a.C4823i;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TaskCompletionSource<TResult> {
    private final C4823i<TResult> task = new C4823i<>();

    public TaskCompletionSource() {
    }

    public TaskCompletionSource(CancellationToken cancellationToken) {
        cancellationToken.register(new Runnable() { // from class: com.huawei.hmf.tasks.TaskCompletionSource.1
            @Override // java.lang.Runnable
            public void run() {
                TaskCompletionSource.this.task.m15360a();
            }
        });
    }

    public Task<TResult> getTask() {
        return this.task;
    }

    public void setException(Exception exc) {
        this.task.m15358a(exc);
    }

    public void setResult(TResult tresult) {
        this.task.m15357a((C4823i<TResult>) tresult);
    }
}
