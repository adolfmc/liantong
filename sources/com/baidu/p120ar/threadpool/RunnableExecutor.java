package com.baidu.p120ar.threadpool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.threadpool.RunnableExecutor */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableExecutor {
    private static final String DROPPABLE_FLAG = "droppable";
    private static final String MULTI_FLAG = "multi";
    private static final String MUST_RUN_FLAG = "must_run";
    private static final String SINGLE_FLAG = "single";
    private Map<String, ExecutorService> mExecutorMap = Collections.synchronizedMap(new HashMap());
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = (CPU_COUNT * 2) + 1;

    public boolean execute(AbstractRunnable abstractRunnable) {
        String executorKey = getExecutorKey(abstractRunnable.mFlag, abstractRunnable.mSingleThread, abstractRunnable.mDroppable);
        ExecutorService executorService = this.mExecutorMap.get(executorKey);
        if (executorService == null) {
            executorService = newThreadExecutor(abstractRunnable.mSingleThread, abstractRunnable.mDroppable);
            this.mExecutorMap.put(executorKey, executorService);
        }
        try {
            executorService.execute(abstractRunnable);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void release() {
        for (Map.Entry<String, ExecutorService> entry : this.mExecutorMap.entrySet()) {
            entry.getValue().shutdownNow();
        }
        this.mExecutorMap.clear();
        this.mExecutorMap = null;
    }

    private String getExecutorKey(String str, boolean z, boolean z2) {
        String str2 = z ? "single" : "multi";
        String str3 = z2 ? "droppable" : "must_run";
        return str + "_" + str2 + "_" + str3;
    }

    private ExecutorService newThreadExecutor(boolean z, boolean z2) {
        int i;
        int i2;
        long j;
        BlockingQueue linkedBlockingQueue;
        if (z) {
            i = 1;
            i2 = 1;
            j = 0;
        } else {
            i = CORE_POOL_SIZE;
            i2 = MAXIMUM_POOL_SIZE;
            j = 10;
        }
        if (z2) {
            linkedBlockingQueue = new ArrayBlockingQueue(1);
        } else {
            linkedBlockingQueue = new LinkedBlockingQueue();
        }
        return new ThreadPoolExecutor(i, i2, j, TimeUnit.SECONDS, linkedBlockingQueue);
    }
}
