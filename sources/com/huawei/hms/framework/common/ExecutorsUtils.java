package com.huawei.hms.framework.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ExecutorsUtils {
    private static final String THREADNAME_HEADER = "NetworkKit_";

    public static ExecutorService newCachedThreadPool(String str) {
        ThreadPoolExcutorEnhance threadPoolExcutorEnhance = new ThreadPoolExcutorEnhance(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), createThreadFactory(str));
        threadPoolExcutorEnhance.allowCoreThreadTimeOut(true);
        return threadPoolExcutorEnhance;
    }

    public static ExecutorService newSingleThreadExecutor(String str) {
        return ExecutorsEnhance.newSingleThreadExecutor(createThreadFactory(str));
    }

    public static ScheduledExecutorService newScheduledThreadPool(int i, String str) {
        return new ScheduledThreadPoolExecutorEnhance(i, createThreadFactory(str));
    }

    public static ExecutorService newFixedThreadPool(int i, String str) {
        ThreadPoolExcutorEnhance threadPoolExcutorEnhance = new ThreadPoolExcutorEnhance(i, i, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), createThreadFactory(str));
        threadPoolExcutorEnhance.allowCoreThreadTimeOut(true);
        return threadPoolExcutorEnhance;
    }

    public static ThreadFactory createThreadFactory(final String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new NullPointerException("ThreadName is empty");
        }
        return new ThreadFactory() { // from class: com.huawei.hms.framework.common.ExecutorsUtils.1
            private final AtomicInteger threadNumbers = new AtomicInteger(0);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "NetworkKit_" + str + "_" + this.threadNumbers.getAndIncrement());
            }
        };
    }
}
