package com.huawei.hms.opendevice;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.huawei.hms.opendevice.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AsyncThreadFactory implements ThreadFactory {

    /* renamed from: a */
    private final ThreadGroup f11536a;

    /* renamed from: b */
    private int f11537b = 1;

    /* renamed from: c */
    private final String f11538c;

    public AsyncThreadFactory(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        this.f11536a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.f11538c = str + "-pool-";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        synchronized (this) {
            this.f11537b++;
        }
        Thread thread = new Thread(this.f11536a, runnable, this.f11538c + this.f11537b, 0L);
        thread.setUncaughtExceptionHandler(null);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}
