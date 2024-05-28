package com.huawei.hmf.tasks;

import com.huawei.hmf.tasks.p212a.C4814c;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CancellationTokenSource {
    private C4814c impl = new C4814c();

    public void cancel() {
        C4814c c4814c = this.impl;
        if (c4814c.f10828c) {
            return;
        }
        synchronized (c4814c.f10827b) {
            c4814c.f10828c = true;
            for (Runnable runnable : c4814c.f10826a) {
                runnable.run();
            }
        }
    }

    public CancellationToken getToken() {
        return this.impl;
    }
}
