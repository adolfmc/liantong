package com.huawei.hmf.tasks.p212a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* renamed from: com.huawei.hmf.tasks.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4812b<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a */
    private OnCanceledListener f10822a;

    /* renamed from: b */
    private Executor f10823b;

    /* renamed from: c */
    private final Object f10824c = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4812b(Executor executor, OnCanceledListener onCanceledListener) {
        this.f10822a = onCanceledListener;
        this.f10823b = executor;
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void cancel() {
        synchronized (this.f10824c) {
            this.f10822a = null;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(Task<TResult> task) {
        if (task.isCanceled()) {
            this.f10823b.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    synchronized (C4812b.this.f10824c) {
                        if (C4812b.this.f10822a != null) {
                            C4812b.this.f10822a.onCanceled();
                        }
                    }
                }
            });
        }
    }
}
