package com.huawei.hmf.tasks.p212a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* renamed from: com.huawei.hmf.tasks.a.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4821h<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a */
    private OnSuccessListener<TResult> f10847a;

    /* renamed from: b */
    private Executor f10848b;

    /* renamed from: c */
    private final Object f10849c = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4821h(Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        this.f10847a = onSuccessListener;
        this.f10848b = executor;
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void cancel() {
        synchronized (this.f10849c) {
            this.f10847a = null;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(final Task<TResult> task) {
        if (!task.isSuccessful() || task.isCanceled()) {
            return;
        }
        this.f10848b.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.h.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (C4821h.this.f10849c) {
                    if (C4821h.this.f10847a != null) {
                        C4821h.this.f10847a.onSuccess(task.getResult());
                    }
                }
            }
        });
    }
}
