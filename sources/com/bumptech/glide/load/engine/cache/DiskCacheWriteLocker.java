package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.util.Preconditions;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class DiskCacheWriteLocker {
    private final Map<String, WriteLock> locks = new HashMap();
    private final WriteLockPool writeLockPool = new WriteLockPool();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acquire(String str) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = this.locks.get(str);
            if (writeLock == null) {
                writeLock = this.writeLockPool.obtain();
                this.locks.put(str, writeLock);
            }
            writeLock.interestedThreads++;
        }
        writeLock.lock.lock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release(String str) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = (WriteLock) Preconditions.checkNotNull(this.locks.get(str));
            if (writeLock.interestedThreads < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + writeLock.interestedThreads);
            }
            writeLock.interestedThreads--;
            if (writeLock.interestedThreads == 0) {
                WriteLock remove = this.locks.remove(str);
                if (!remove.equals(writeLock)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + writeLock + ", but actually removed: " + remove + ", safeKey: " + str);
                }
                this.writeLockPool.offer(remove);
            }
        }
        writeLock.lock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class WriteLock {
        int interestedThreads;
        final Lock lock = new ReentrantLock();

        WriteLock() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class WriteLockPool {
        private static final int MAX_POOL_SIZE = 10;
        private final Queue<WriteLock> pool = new ArrayDeque();

        WriteLockPool() {
        }

        WriteLock obtain() {
            WriteLock poll;
            synchronized (this.pool) {
                poll = this.pool.poll();
            }
            return poll == null ? new WriteLock() : poll;
        }

        void offer(WriteLock writeLock) {
            synchronized (this.pool) {
                if (this.pool.size() < 10) {
                    this.pool.offer(writeLock);
                }
            }
        }
    }
}
