package com.huawei.hmf.tasks.p212a;

import com.huawei.hmf.tasks.CancellationToken;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.huawei.hmf.tasks.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4814c extends CancellationToken {

    /* renamed from: a */
    public final List<Runnable> f10826a = new ArrayList();

    /* renamed from: b */
    public final Object f10827b = new Object();

    /* renamed from: c */
    public boolean f10828c = false;

    @Override // com.huawei.hmf.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.f10828c;
    }

    @Override // com.huawei.hmf.tasks.CancellationToken
    public final CancellationToken register(Runnable runnable) {
        synchronized (this.f10827b) {
            if (this.f10828c) {
                runnable.run();
            } else {
                this.f10826a.add(runnable);
            }
        }
        return this;
    }
}
