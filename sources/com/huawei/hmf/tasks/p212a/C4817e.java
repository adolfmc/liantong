package com.huawei.hmf.tasks.p212a;

import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import java.util.concurrent.ExecutionException;

/* renamed from: com.huawei.hmf.tasks.a.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C4817e<TResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TResult> {

    /* renamed from: a */
    private final Object f10834a = new Object();

    /* renamed from: b */
    private final int f10835b;

    /* renamed from: c */
    private final C4823i<Void> f10836c;

    /* renamed from: d */
    private int f10837d;

    /* renamed from: e */
    private Exception f10838e;

    /* renamed from: f */
    private boolean f10839f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4817e(int i, C4823i<Void> c4823i) {
        this.f10835b = i;
        this.f10836c = c4823i;
    }

    /* renamed from: a */
    private void m15368a() {
        if (this.f10837d >= this.f10835b) {
            Exception exc = this.f10838e;
            if (exc != null) {
                this.f10836c.m15358a(new ExecutionException("a task failed", exc));
            } else if (this.f10839f) {
                this.f10836c.m15360a();
            } else {
                this.f10836c.m15357a((C4823i<Void>) null);
            }
        }
    }

    @Override // com.huawei.hmf.tasks.OnCanceledListener
    public final void onCanceled() {
        synchronized (this.f10834a) {
            this.f10837d++;
            this.f10839f = true;
            m15368a();
        }
    }

    @Override // com.huawei.hmf.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        synchronized (this.f10834a) {
            this.f10837d++;
            this.f10838e = exc;
            m15368a();
        }
    }

    @Override // com.huawei.hmf.tasks.OnSuccessListener
    public final void onSuccess(TResult tresult) {
        synchronized (this.f10834a) {
            this.f10837d++;
            m15368a();
        }
    }
}
