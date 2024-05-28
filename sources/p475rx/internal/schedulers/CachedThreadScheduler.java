package p475rx.internal.schedulers;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.internal.util.RxThreadFactory;
import p475rx.subscriptions.CompositeSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.CachedThreadScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class CachedThreadScheduler extends Scheduler implements SchedulerLifecycle {
    private static final long KEEP_ALIVE_TIME = 60;
    static final CachedWorkerPool NONE;
    final AtomicReference<CachedWorkerPool> pool = new AtomicReference<>(NONE);
    final ThreadFactory threadFactory;
    private static final TimeUnit KEEP_ALIVE_UNIT = TimeUnit.SECONDS;
    static final ThreadWorker SHUTDOWN_THREADWORKER = new ThreadWorker(RxThreadFactory.NONE);

    static {
        SHUTDOWN_THREADWORKER.unsubscribe();
        NONE = new CachedWorkerPool(null, 0L, null);
        NONE.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.CachedThreadScheduler$CachedWorkerPool */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CachedWorkerPool {
        private final CompositeSubscription allWorkers;
        private final ScheduledExecutorService evictorService;
        private final Future<?> evictorTask;
        private final ConcurrentLinkedQueue<ThreadWorker> expiringWorkerQueue;
        private final long keepAliveTime;
        private final ThreadFactory threadFactory;

        CachedWorkerPool(final ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
            ScheduledExecutorService scheduledExecutorService;
            this.threadFactory = threadFactory;
            this.keepAliveTime = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.expiringWorkerQueue = new ConcurrentLinkedQueue<>();
            this.allWorkers = new CompositeSubscription();
            ScheduledFuture<?> scheduledFuture = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactory() { // from class: rx.internal.schedulers.CachedThreadScheduler.CachedWorkerPool.1
                    @Override // java.util.concurrent.ThreadFactory
                    public Thread newThread(Runnable runnable) {
                        Thread newThread = threadFactory.newThread(runnable);
                        newThread.setName(newThread.getName() + " (Evictor)");
                        return newThread;
                    }
                });
                NewThreadWorker.tryEnableCancelPolicy(scheduledExecutorService);
                Runnable runnable = new Runnable() { // from class: rx.internal.schedulers.CachedThreadScheduler.CachedWorkerPool.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CachedWorkerPool.this.evictExpiredWorkers();
                    }
                };
                long j2 = this.keepAliveTime;
                scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(runnable, j2, j2, TimeUnit.NANOSECONDS);
            } else {
                scheduledExecutorService = null;
            }
            this.evictorService = scheduledExecutorService;
            this.evictorTask = scheduledFuture;
        }

        ThreadWorker get() {
            if (this.allWorkers.isUnsubscribed()) {
                return CachedThreadScheduler.SHUTDOWN_THREADWORKER;
            }
            while (!this.expiringWorkerQueue.isEmpty()) {
                ThreadWorker poll = this.expiringWorkerQueue.poll();
                if (poll != null) {
                    return poll;
                }
            }
            ThreadWorker threadWorker = new ThreadWorker(this.threadFactory);
            this.allWorkers.add(threadWorker);
            return threadWorker;
        }

        void release(ThreadWorker threadWorker) {
            threadWorker.setExpirationTime(now() + this.keepAliveTime);
            this.expiringWorkerQueue.offer(threadWorker);
        }

        void evictExpiredWorkers() {
            if (this.expiringWorkerQueue.isEmpty()) {
                return;
            }
            long now = now();
            Iterator<ThreadWorker> it = this.expiringWorkerQueue.iterator();
            while (it.hasNext()) {
                ThreadWorker next = it.next();
                if (next.getExpirationTime() > now) {
                    return;
                }
                if (this.expiringWorkerQueue.remove(next)) {
                    this.allWorkers.remove(next);
                }
            }
        }

        long now() {
            return System.nanoTime();
        }

        void shutdown() {
            try {
                if (this.evictorTask != null) {
                    this.evictorTask.cancel(true);
                }
                if (this.evictorService != null) {
                    this.evictorService.shutdownNow();
                }
            } finally {
                this.allWorkers.unsubscribe();
            }
        }
    }

    public CachedThreadScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        start();
    }

    @Override // p475rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        CachedWorkerPool cachedWorkerPool = new CachedWorkerPool(this.threadFactory, 60L, KEEP_ALIVE_UNIT);
        if (this.pool.compareAndSet(NONE, cachedWorkerPool)) {
            return;
        }
        cachedWorkerPool.shutdown();
    }

    @Override // p475rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        CachedWorkerPool cachedWorkerPool;
        CachedWorkerPool cachedWorkerPool2;
        do {
            cachedWorkerPool = this.pool.get();
            cachedWorkerPool2 = NONE;
            if (cachedWorkerPool == cachedWorkerPool2) {
                return;
            }
        } while (!this.pool.compareAndSet(cachedWorkerPool, cachedWorkerPool2));
        cachedWorkerPool.shutdown();
    }

    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new EventLoopWorker(this.pool.get());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.CachedThreadScheduler$EventLoopWorker */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class EventLoopWorker extends Scheduler.Worker {
        static final AtomicIntegerFieldUpdater<EventLoopWorker> ONCE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(EventLoopWorker.class, "once");
        private final CompositeSubscription innerSubscription = new CompositeSubscription();
        volatile int once;
        private final CachedWorkerPool pool;
        private final ThreadWorker threadWorker;

        EventLoopWorker(CachedWorkerPool cachedWorkerPool) {
            this.pool = cachedWorkerPool;
            this.threadWorker = cachedWorkerPool.get();
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (ONCE_UPDATER.compareAndSet(this, 0, 1)) {
                this.pool.release(this.threadWorker);
            }
            this.innerSubscription.unsubscribe();
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            return schedule(action0, 0L, null);
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(final Action0 action0, long j, TimeUnit timeUnit) {
            if (this.innerSubscription.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            ScheduledAction scheduleActual = this.threadWorker.scheduleActual(new Action0() { // from class: rx.internal.schedulers.CachedThreadScheduler.EventLoopWorker.1
                @Override // p475rx.functions.Action0
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, j, timeUnit);
            this.innerSubscription.add(scheduleActual);
            scheduleActual.addParent(this.innerSubscription);
            return scheduleActual;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.CachedThreadScheduler$ThreadWorker */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ThreadWorker extends NewThreadWorker {
        private long expirationTime;

        ThreadWorker(ThreadFactory threadFactory) {
            super(threadFactory);
            this.expirationTime = 0L;
        }

        public long getExpirationTime() {
            return this.expirationTime;
        }

        public void setExpirationTime(long j) {
            this.expirationTime = j;
        }
    }
}
