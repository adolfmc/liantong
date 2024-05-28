package com.huawei.hms.hatool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.huawei.hms.hatool.b0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4968b0 {

    /* renamed from: b */
    private static C4968b0 f11353b;

    /* renamed from: c */
    private static C4968b0 f11354c;

    /* renamed from: d */
    private static C4968b0 f11355d;

    /* renamed from: a */
    private ThreadPoolExecutor f11356a = new ThreadPoolExecutor(0, 1, 60000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(5000), new ThreadFactoryC4970b());

    /* renamed from: com.huawei.hms.hatool.b0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class RunnableC4969a implements Runnable {

        /* renamed from: a */
        private Runnable f11357a;

        public RunnableC4969a(Runnable runnable) {
            this.f11357a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = this.f11357a;
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Exception unused) {
                    C5029v.m14452e("hmsSdk", "InnerTask : Exception has happened,From internal operations!");
                }
            }
        }
    }

    /* renamed from: com.huawei.hms.hatool.b0$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class ThreadFactoryC4970b implements ThreadFactory {

        /* renamed from: d */
        private static final AtomicInteger f11358d = new AtomicInteger(1);

        /* renamed from: a */
        private final ThreadGroup f11359a;

        /* renamed from: b */
        private final AtomicInteger f11360b = new AtomicInteger(1);

        /* renamed from: c */
        private final String f11361c;

        ThreadFactoryC4970b() {
            SecurityManager securityManager = System.getSecurityManager();
            this.f11359a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.f11361c = "FormalHASDK-base-" + f11358d.getAndIncrement();
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.f11359a;
            return new Thread(threadGroup, runnable, this.f11361c + this.f11360b.getAndIncrement(), 0L);
        }
    }

    static {
        new C4968b0();
        new C4968b0();
        f11353b = new C4968b0();
        f11354c = new C4968b0();
        f11355d = new C4968b0();
    }

    private C4968b0() {
    }

    /* renamed from: a */
    public static C4968b0 m14796a() {
        return f11355d;
    }

    /* renamed from: b */
    public static C4968b0 m14794b() {
        return f11354c;
    }

    /* renamed from: c */
    public static C4968b0 m14793c() {
        return f11353b;
    }

    /* renamed from: a */
    public void m14795a(InterfaceRunnableC4984g interfaceRunnableC4984g) {
        try {
            this.f11356a.execute(new RunnableC4969a(interfaceRunnableC4984g));
        } catch (RejectedExecutionException unused) {
            C5029v.m14452e("hmsSdk", "addToQueue() Exception has happened!Form rejected execution");
        }
    }
}
