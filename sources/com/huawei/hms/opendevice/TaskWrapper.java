package com.huawei.hms.opendevice;

import android.util.Log;

/* renamed from: com.huawei.hms.opendevice.q */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TaskWrapper implements Runnable {

    /* renamed from: a */
    private final Runnable f11560a;

    public TaskWrapper(Runnable runnable) {
        this.f11560a = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.f11560a;
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable unused) {
                Log.e("HmsPushThreads", "exception in task run");
            }
        }
    }
}
