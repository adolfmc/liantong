package com.bytedance.sdk.openadsdk.p187ox;

import com.bytedance.sdk.openadsdk.api.C3972mb;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.bytedance.sdk.openadsdk.ox.mb */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4074mb {

    /* renamed from: ox */
    private static volatile C4074mb f9695ox;

    /* renamed from: mb */
    private volatile ThreadPoolExecutor f9696mb = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC4076mb(), new RejectedExecutionHandler() { // from class: com.bytedance.sdk.openadsdk.ox.mb.1
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            C3972mb.m16552hj("TTThreadManager", "TTThreadManager rejectedExecution:  ");
        }
    });

    /* renamed from: mb */
    public static C4074mb m16346mb() {
        if (f9695ox == null) {
            synchronized (C4074mb.class) {
                f9695ox = new C4074mb();
            }
        }
        return f9695ox;
    }

    public C4074mb() {
        this.f9696mb.allowCoreThreadTimeOut(true);
    }

    /* renamed from: mb */
    public void m16345mb(Runnable runnable) {
        if (runnable != null) {
            try {
                this.f9696mb.execute(runnable);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.bytedance.sdk.openadsdk.ox.mb$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ThreadFactoryC4076mb implements ThreadFactory {

        /* renamed from: b */
        private final String f9698b;

        /* renamed from: mb */
        private final ThreadGroup f9699mb;

        /* renamed from: ox */
        private final AtomicInteger f9700ox;

        ThreadFactoryC4076mb() {
            this("csj_g_pl_mgr");
        }

        ThreadFactoryC4076mb(String str) {
            this.f9700ox = new AtomicInteger(1);
            this.f9699mb = new ThreadGroup("csj_g_pl_mgr");
            this.f9698b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.f9699mb;
            Thread thread = new Thread(threadGroup, runnable, this.f9698b + this.f9700ox.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }
}
