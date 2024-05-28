package com.baidu.minivideo.arface.utils;

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
    private static volatile ThreadExecutor sComputationThreadPool;
    private static volatile ThreadExecutor sIOThreadPool;
    private static volatile ThreadExecutor sSingleThreadPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum QueueProcessingType {
        FIFO,
        LIFO
    }

    /* renamed from: io */
    public static ThreadExecutor m18107io() {
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

    public ThreadExecutor createNewThreadPool(String str, int i) {
        return new ThreadExecutor(str, i);
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

        protected void destroyPool() {
            ExecutorService executorService = this.mPostExecutor;
            if (executorService == null || executorService.isShutdown()) {
                return;
            }
            this.mPostExecutor.shutdownNow();
        }

        protected void allowsCoreThreadTimeOut() {
            ExecutorService executorService = this.mPostExecutor;
            if (!(executorService instanceof ThreadPoolExecutor) || executorService == null || executorService.isShutdown()) {
                return;
            }
            ((ThreadPoolExecutor) this.mPostExecutor).allowsCoreThreadTimeOut();
        }

        private ExecutorService createExecutor(String str, int i, int i2, QueueProcessingType queueProcessingType) {
            return new ThreadPoolExecutor(i, i, 3L, TimeUnit.SECONDS, queueProcessingType == QueueProcessingType.FIFO ? new LinkedBlockingQueue() : new LIFOLinkedBlockingDeque(), new HttpThreadFactory(str, i2));
        }

        public void execute(Runnable runnable) {
            this.mPostExecutor.execute(runnable);
        }

        public <T> Future<T> submit(Callable<T> callable) {
            return this.mPostExecutor.submit(callable);
        }

        public Executor getExecutor() {
            return this.mPostExecutor;
        }

        public int getActiveCount() {
            return ((ThreadPoolExecutor) this.mPostExecutor).getActiveCount();
        }

        public int getWaitingQueueSize() {
            return ((ThreadPoolExecutor) this.mPostExecutor).getQueue().size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LIFOLinkedBlockingDeque<T> extends LinkedBlockingDeque<T> {
        private static final long serialVersionUID = -4114786347960826192L;

        private LIFOLinkedBlockingDeque() {
        }

        @Override // java.util.concurrent.LinkedBlockingDeque, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Deque
        public boolean offer(T t) {
            return super.offerFirst(t);
        }

        @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractQueue, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.Deque
        public T remove() {
            return (T) super.removeFirst();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class HttpThreadFactory implements ThreadFactory {
        private static final AtomicInteger mPoolNumber = new AtomicInteger(1);
        private final String mNamePrefix;
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);
        private final int mThreadPriority;

        /* renamed from: s */
        private SecurityManager f7470s;

        public HttpThreadFactory(String str, int i) {
            this.mThreadPriority = i;
            System.getSecurityManager();
            this.mNamePrefix = str + "-" + mPoolNumber.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            SecurityManager securityManager = this.f7470s;
            ThreadGroup threadGroup = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
            Thread thread = new Thread(threadGroup, runnable, this.mNamePrefix + this.mThreadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.mThreadPriority);
            return thread;
        }
    }
}
