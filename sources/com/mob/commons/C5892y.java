package com.mob.commons;

import android.text.TextUtils;
import com.mob.commons.p229a.C5731l;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.y */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5892y {

    /* renamed from: a */
    public static final String f14523a = "M-" + C5731l.m12674a("002+gkil");

    /* renamed from: b */
    public static final String f14524b = "M-" + C5731l.m12674a("003;hlgdil");

    /* renamed from: c */
    public static final ThreadPoolExecutor f14525c = new ThreadPoolExecutor(2, Math.max(2, 5), 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC5894b(0), new RejectedExecutionHandlerC5893a());

    /* renamed from: d */
    public static final ThreadPoolExecutor f14526d = new ThreadPoolExecutor(1, 1, 120, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC5894b(1));

    /* renamed from: e */
    public static final ExecutorService f14527e = Executors.newCachedThreadPool(new ThreadFactoryC5894b(2));

    /* renamed from: f */
    public static final ExecutorService f14528f = Executors.newCachedThreadPool(new ThreadFactoryC5894b(3));

    /* renamed from: g */
    public static final ExecutorService f14529g = Executors.newCachedThreadPool(new ThreadFactoryC5894b(4));

    /* renamed from: com.mob.commons.y$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class ThreadFactoryC5894b implements ThreadFactory {

        /* renamed from: a */
        private static final AtomicInteger f14530a = new AtomicInteger(1);

        /* renamed from: b */
        private final ThreadGroup f14531b;

        /* renamed from: c */
        private final AtomicInteger f14532c = new AtomicInteger(1);

        /* renamed from: d */
        private final String f14533d;

        ThreadFactoryC5894b(int i) {
            SecurityManager securityManager = System.getSecurityManager();
            this.f14531b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            if (TextUtils.isEmpty("M-")) {
                this.f14533d = C5731l.m12674a("005kBfefeSh1il") + f14530a.getAndIncrement() + C5731l.m12674a("0082ilZji4ek%ge>edil");
                return;
            }
            this.f14533d = C5892y.f14524b + i + "-" + f14530a.getAndIncrement() + "-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.f14531b;
            Thread thread = new Thread(threadGroup, runnable, this.f14533d + this.f14532c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    /* renamed from: com.mob.commons.y$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class RejectedExecutionHandlerC5893a implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                C5731l.m12681a().m12666e(500L, runnable);
            } catch (Throwable unused) {
            }
        }
    }
}
