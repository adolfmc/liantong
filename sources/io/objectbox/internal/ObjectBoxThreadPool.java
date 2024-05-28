package io.objectbox.internal;

import io.objectbox.BoxStore;
import io.objectbox.annotation.apihint.Internal;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ObjectBoxThreadPool extends ThreadPoolExecutor {
    private final BoxStore boxStore;

    public ObjectBoxThreadPool(BoxStore boxStore) {
        super(0, Integer.MAX_VALUE, 20L, TimeUnit.SECONDS, new SynchronousQueue(), new ObjectBoxThreadFactory());
        this.boxStore = boxStore;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        this.boxStore.closeThreadResources();
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class ObjectBoxThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_COUNT = new AtomicInteger();

        /* renamed from: group  reason: collision with root package name */
        private final ThreadGroup f27866group;
        private final String namePrefix = "ObjectBox-" + POOL_COUNT.incrementAndGet() + "-Thread-";
        private final AtomicInteger threadCount = new AtomicInteger();

        ObjectBoxThreadFactory() {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.f27866group = threadGroup;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f27866group, runnable, this.namePrefix + this.threadCount.incrementAndGet());
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }
}
