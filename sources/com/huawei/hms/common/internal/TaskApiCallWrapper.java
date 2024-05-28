package com.huawei.hms.common.internal;

import com.huawei.hmf.tasks.TaskCompletionSource;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TaskApiCallWrapper<TResult> extends BaseContentWrapper {

    /* renamed from: a */
    private final TaskApiCall<? extends AnyClient, TResult> f11175a;

    /* renamed from: b */
    private final TaskCompletionSource<TResult> f11176b;

    public TaskApiCallWrapper(TaskApiCall<? extends AnyClient, TResult> taskApiCall, TaskCompletionSource<TResult> taskCompletionSource) {
        super(1);
        this.f11175a = taskApiCall;
        this.f11176b = taskCompletionSource;
    }

    public TaskApiCall<? extends AnyClient, TResult> getTaskApiCall() {
        return this.f11175a;
    }

    public TaskCompletionSource<TResult> getTaskCompletionSource() {
        return this.f11176b;
    }
}
