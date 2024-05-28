package com.alibaba.android.arouter.thread;

import android.support.annotation.NonNull;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    /* renamed from: group  reason: collision with root package name */
    private final ThreadGroup f27851group;
    private final String namePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public DefaultThreadFactory() {
        ThreadGroup threadGroup;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            threadGroup = securityManager.getThreadGroup();
        } else {
            threadGroup = Thread.currentThread().getThreadGroup();
        }
        this.f27851group = threadGroup;
        this.namePrefix = "ARouter task pool No." + poolNumber.getAndIncrement() + ", thread No.";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(@NonNull Runnable runnable) {
        String str = this.namePrefix + this.threadNumber.getAndIncrement();
        ARouter.logger.info("ARouter::", "Thread production, name is [" + str + "]");
        Thread thread = new Thread(this.f27851group, runnable, str, 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.alibaba.android.arouter.thread.DefaultThreadFactory.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread2, Throwable th) {
                ILogger iLogger = ARouter.logger;
                iLogger.info("ARouter::", "Running task appeared exception! Thread [" + thread2.getName() + "], because [" + th.getMessage() + "]");
            }
        });
        return thread;
    }
}
