package com.billy.android.swipe.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SwipeUtil {
    private static final int KEEP_ALIVE = 10;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, CPU_COUNT - 1);
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT + 1;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() { // from class: com.billy.android.swipe.internal.SwipeUtil.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "SmartSwipe #" + this.mCount.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue(128);
    private static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 10, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

    public static int getReverseDirection(int i) {
        return (i & 3) != 0 ? (i ^ 3) & 3 : (i ^ 12) & 12;
    }

    public static void runInThreadPool(Runnable runnable) {
        THREAD_POOL_EXECUTOR.execute(runnable);
    }
}
