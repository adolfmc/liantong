package io.reactivex.internal.schedulers;

import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class SchedulerPoolFactory {
    public static final boolean PURGE_ENABLED;
    static final String PURGE_ENABLED_KEY = "rx2.purge-enabled";
    public static final int PURGE_PERIOD_SECONDS;
    static final String PURGE_PERIOD_SECONDS_KEY = "rx2.purge-period-seconds";
    static final AtomicReference<ScheduledExecutorService> PURGE_THREAD = new AtomicReference<>();
    static final Map<ScheduledThreadPoolExecutor, Object> POOLS = new ConcurrentHashMap();

    private SchedulerPoolFactory() {
        throw new IllegalStateException("No instances!");
    }

    static {
        Properties properties = System.getProperties();
        int i = 1;
        boolean z = properties.containsKey("rx2.purge-enabled") ? Boolean.getBoolean("rx2.purge-enabled") : true;
        if (z && properties.containsKey("rx2.purge-period-seconds")) {
            i = Integer.getInteger("rx2.purge-period-seconds", 1).intValue();
        }
        PURGE_ENABLED = z;
        PURGE_PERIOD_SECONDS = i;
        start();
    }

    public static void start() {
        if (!PURGE_ENABLED) {
            return;
        }
        while (true) {
            ScheduledExecutorService scheduledExecutorService = PURGE_THREAD.get();
            if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
                return;
            }
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
            if (PURGE_THREAD.compareAndSet(scheduledExecutorService, newScheduledThreadPool)) {
                ScheduledTask scheduledTask = new ScheduledTask();
                int i = PURGE_PERIOD_SECONDS;
                newScheduledThreadPool.scheduleAtFixedRate(scheduledTask, i, i, TimeUnit.SECONDS);
                return;
            }
            newScheduledThreadPool.shutdownNow();
        }
    }

    public static void shutdown() {
        ScheduledExecutorService scheduledExecutorService = PURGE_THREAD.get();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        POOLS.clear();
    }

    public static ScheduledExecutorService create(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (PURGE_ENABLED && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            POOLS.put((ScheduledThreadPoolExecutor) newScheduledThreadPool, newScheduledThreadPool);
        }
        return newScheduledThreadPool;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class ScheduledTask implements Runnable {
        ScheduledTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Iterator it = new ArrayList(SchedulerPoolFactory.POOLS.keySet()).iterator();
                while (it.hasNext()) {
                    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                    if (scheduledThreadPoolExecutor.isShutdown()) {
                        SchedulerPoolFactory.POOLS.remove(scheduledThreadPoolExecutor);
                    } else {
                        scheduledThreadPoolExecutor.purge();
                    }
                }
            } catch (Throwable th) {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
