package com.baidu.cloud.videocache.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ThreadPool {
    private static final boolean DEBUG = false;
    public static final String TAG = "ThreadPool";
    private static volatile ThreadExecutor sCacheThreadPool;
    private static volatile ThreadExecutor sComputationThreadPool;
    private static volatile ThreadExecutor sIOThreadPool;
    private static volatile ThreadExecutor sSingleThreadPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class HttpThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final String mNamePrefix;
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);
        private final int mThreadPriority;

        /* renamed from: s */
        private SecurityManager f4945s;

        public HttpThreadFactory(String str, int i) {
            this.mThreadPriority = i;
            System.getSecurityManager();
            this.mNamePrefix = str + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            SecurityManager securityManager = this.f4945s;
            ThreadGroup threadGroup = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
            Thread thread = new Thread(threadGroup, runnable, this.mNamePrefix + this.mThreadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.mThreadPriority);
            return thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LIFOLinkedBlockingDeque extends LinkedBlockingDeque {
        private static final long serialVersionUID = -4114786347960826192L;

        private LIFOLinkedBlockingDeque() {
        }

        @Override // java.util.concurrent.LinkedBlockingDeque, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Deque
        public boolean offer(Object obj) {
            return super.offerFirst(obj);
        }

        @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractQueue, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.Deque
        public Object remove() {
            return super.removeFirst();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum QueueProcessingType {
        FIFO,
        LIFO
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ThreadExecutor {
        private static final int KEEP_ALIVE_SECONDS = 3;
        private int mCoreSize;
        private String mName;
        private ExecutorService mPostExecutor;
        private int mPriority;
        private QueueProcessingType mTasksProcessingType;
        private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
        private static final int MAXIMUM_POOL_SIZE = CPU_COUNT;

        public ThreadExecutor(String str, int i) {
            this(str, i, 9, QueueProcessingType.FIFO);
        }

        public ThreadExecutor(String str, int i, int i2, QueueProcessingType queueProcessingType) {
            this.mCoreSize = CORE_POOL_SIZE;
            this.mPriority = 4;
            this.mTasksProcessingType = QueueProcessingType.FIFO;
            this.mName = str;
            this.mCoreSize = i;
            this.mPriority = i2;
            this.mTasksProcessingType = queueProcessingType;
            if (this.mPostExecutor == null) {
                this.mPostExecutor = createExecutor(this.mName, this.mCoreSize, this.mPriority, this.mTasksProcessingType);
            }
        }

        private ExecutorService createExecutor(String str, int i, int i2, QueueProcessingType queueProcessingType) {
            return new ThreadPoolExecutor(i, i, 3L, TimeUnit.SECONDS, queueProcessingType == QueueProcessingType.FIFO ? new LinkedBlockingQueue() : new LIFOLinkedBlockingDeque(), new HttpThreadFactory(str, i2));
        }

        protected void allowsCoreThreadTimeOut() {
            ExecutorService executorService = this.mPostExecutor;
            if (!(executorService instanceof ThreadPoolExecutor) || executorService == null || executorService.isShutdown()) {
                return;
            }
            ((ThreadPoolExecutor) this.mPostExecutor).allowsCoreThreadTimeOut();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void destroyPool() {
            ExecutorService executorService = this.mPostExecutor;
            if (executorService == null || executorService.isShutdown()) {
                return;
            }
            this.mPostExecutor.shutdownNow();
        }

        public void execute(Runnable runnable) {
            this.mPostExecutor.execute(runnable);
        }

        public int getActiveCount() {
            return ((ThreadPoolExecutor) this.mPostExecutor).getActiveCount();
        }

        public Executor getExecutor() {
            return this.mPostExecutor;
        }

        public int getWaitingQueueSize() {
            return ((ThreadPoolExecutor) this.mPostExecutor).getQueue().size();
        }

        public Future submit(Callable callable) {
            return this.mPostExecutor.submit(callable);
        }
    }

    public static ThreadExecutor cache() {
        if (sCacheThreadPool == null) {
            synchronized (ThreadPool.class) {
                if (sCacheThreadPool == null) {
                    ThreadExecutor threadExecutor = new ThreadExecutor("CacheThreadPool", ThreadExecutor.MAXIMUM_POOL_SIZE);
                    threadExecutor.allowsCoreThreadTimeOut();
                    sCacheThreadPool = threadExecutor;
                }
            }
        }
        return sCacheThreadPool;
    }

    public static ThreadExecutor computation() {
        if (sComputationThreadPool == null) {
            synchronized (ThreadPool.class) {
                if (sComputationThreadPool == null) {
                    ThreadExecutor threadExecutor = new ThreadExecutor("ComputationThreadPool", ThreadExecutor.CORE_POOL_SIZE);
                    threadExecutor.allowsCoreThreadTimeOut();
                    sComputationThreadPool = threadExecutor;
                }
            }
        }
        return sComputationThreadPool;
    }

    public static void destroy() {
        if (sIOThreadPool != null) {
            sIOThreadPool.destroyPool();
            sIOThreadPool = null;
        }
        if (sComputationThreadPool != null) {
            sComputationThreadPool.destroyPool();
            sComputationThreadPool = null;
        }
        if (sSingleThreadPool != null) {
            sSingleThreadPool.destroyPool();
            sSingleThreadPool = null;
        }
        if (sCacheThreadPool != null) {
            sCacheThreadPool.destroyPool();
            sCacheThreadPool = null;
        }
    }

    /* renamed from: io */
    public static ThreadExecutor m19721io() {
        if (sIOThreadPool == null) {
            synchronized (ThreadPool.class) {
                if (sIOThreadPool == null) {
                    ThreadExecutor threadExecutor = new ThreadExecutor("IOThreadPool", ThreadExecutor.MAXIMUM_POOL_SIZE);
                    threadExecutor.allowsCoreThreadTimeOut();
                    sIOThreadPool = threadExecutor;
                }
            }
        }
        return sIOThreadPool;
    }

    public static ThreadExecutor single() {
        if (sSingleThreadPool == null) {
            synchronized (ThreadPool.class) {
                if (sSingleThreadPool == null) {
                    sSingleThreadPool = new ThreadExecutor("SingleThreadPool", 1);
                }
            }
        }
        return sSingleThreadPool;
    }

    public ThreadExecutor createNewThreadPool(String str, int i) {
        return new ThreadExecutor(str, i);
    }
}
