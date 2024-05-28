package com.baidu.cloud.videocache.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CacheThreadPoolHelper {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class Holder {
        private static final CacheThreadPoolHelper INSTANCE = new CacheThreadPoolHelper();

        private Holder() {
        }
    }

    private CacheThreadPoolHelper() {
    }

    public static final CacheThreadPoolHelper getInstance() {
        return Holder.INSTANCE;
    }

    public void destroy() {
        ThreadPool.cache().destroyPool();
    }

    public void execute(Runnable runnable) {
        ThreadPool.cache().execute(runnable);
    }

    public int getActiveCount() {
        return ThreadPool.cache().getActiveCount();
    }

    public int getWaitingQueueSize() {
        return ThreadPool.cache().getWaitingQueueSize();
    }

    public Future singleSubmit(Callable callable) {
        return ThreadPool.single().submit(callable);
    }
}
