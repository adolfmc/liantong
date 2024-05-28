package com.huawei.hmf.tasks.p212a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* renamed from: com.huawei.hmf.tasks.a.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4815d<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a */
    Executor f10829a;

    /* renamed from: b */
    private OnCompleteListener<TResult> f10830b;

    /* renamed from: c */
    private final Object f10831c = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4815d(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.f10830b = onCompleteListener;
        this.f10829a = executor;
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void cancel() {
        synchronized (this.f10831c) {
            this.f10830b = null;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(final Task<TResult> task) {
        this.f10829a.execute(new Runnable() { // from class: com.huawei.hmf.tasks.a.d.1
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (C4815d.this.f10831c) {
                    if (C4815d.this.f10830b != null) {
                        C4815d.this.f10830b.onComplete(task);
                    }
                }
            }
        });
    }
}
