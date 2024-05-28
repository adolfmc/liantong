package com.huawei.hmf.tasks.p212a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* renamed from: com.huawei.hmf.tasks.a.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4818f<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a */
    private OnFailureListener f10840a;

    /* renamed from: b */
    private Executor f10841b;

    /* renamed from: c */
    private final Object f10842c = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4818f(Executor executor, OnFailureListener onFailureListener) {
        this.f10840a = onFailureListener;
        this.f10841b = executor;
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void cancel() {
        synchronized (this.f10842c) {
            this.f10840a = null;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(final Task<TResult> task) {
        if (task.isSuccessful() || task.isCanceled()) {
            return;
        }
        this.f10841b.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.f.1
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (C4818f.this.f10842c) {
                    if (C4818f.this.f10840a != null) {
                        C4818f.this.f10840a.onFailure(task.getException());
                    }
                }
            }
        });
    }
}
