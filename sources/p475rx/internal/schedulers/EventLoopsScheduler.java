package p475rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.internal.util.RxThreadFactory;
import p475rx.internal.util.SubscriptionList;
import p475rx.subscriptions.CompositeSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.EventLoopsScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class EventLoopsScheduler extends Scheduler implements SchedulerLifecycle {
    static final String KEY_MAX_THREADS = "rx.scheduler.max-computation-threads";
    static final int MAX_THREADS;
    static final FixedSchedulerPool NONE;
    static final PoolWorker SHUTDOWN_WORKER;
    final AtomicReference<FixedSchedulerPool> pool = new AtomicReference<>(NONE);
    final ThreadFactory threadFactory;

    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        MAX_THREADS = intValue;
        SHUTDOWN_WORKER = new PoolWorker(RxThreadFactory.NONE);
        SHUTDOWN_WORKER.unsubscribe();
        NONE = new FixedSchedulerPool(null, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.EventLoopsScheduler$FixedSchedulerPool */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FixedSchedulerPool {
        final int cores;
        final PoolWorker[] eventLoops;

        /* renamed from: n */
        long f27612n;

        FixedSchedulerPool(ThreadFactory threadFactory, int i) {
            this.cores = i;
            this.eventLoops = new PoolWorker[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.eventLoops[i2] = new PoolWorker(threadFactory);
            }
        }

        public PoolWorker getEventLoop() {
            int i = this.cores;
            if (i == 0) {
                return EventLoopsScheduler.SHUTDOWN_WORKER;
            }
            PoolWorker[] poolWorkerArr = this.eventLoops;
            long j = this.f27612n;
            this.f27612n = 1 + j;
            return poolWorkerArr[(int) (j % i)];
        }

        public void shutdown() {
            for (PoolWorker poolWorker : this.eventLoops) {
                poolWorker.unsubscribe();
            }
        }
    }

    public EventLoopsScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        start();
    }

    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new EventLoopWorker(this.pool.get().getEventLoop());
    }

    @Override // p475rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        FixedSchedulerPool fixedSchedulerPool = new FixedSchedulerPool(this.threadFactory, MAX_THREADS);
        if (this.pool.compareAndSet(NONE, fixedSchedulerPool)) {
            return;
        }
        fixedSchedulerPool.shutdown();
    }

    @Override // p475rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        FixedSchedulerPool fixedSchedulerPool;
        FixedSchedulerPool fixedSchedulerPool2;
        do {
            fixedSchedulerPool = this.pool.get();
            fixedSchedulerPool2 = NONE;
            if (fixedSchedulerPool == fixedSchedulerPool2) {
                return;
            }
        } while (!this.pool.compareAndSet(fixedSchedulerPool, fixedSchedulerPool2));
        fixedSchedulerPool.shutdown();
    }

    public Subscription scheduleDirect(Action0 action0) {
        return this.pool.get().getEventLoop().scheduleActual(action0, -1L, TimeUnit.NANOSECONDS);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.EventLoopsScheduler$EventLoopWorker */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class EventLoopWorker extends Scheduler.Worker {
        private final PoolWorker poolWorker;
        private final SubscriptionList serial = new SubscriptionList();
        private final CompositeSubscription timed = new CompositeSubscription();
        private final SubscriptionList both = new SubscriptionList(this.serial, this.timed);

        EventLoopWorker(PoolWorker poolWorker) {
            this.poolWorker = poolWorker;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            this.both.unsubscribe();
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.both.isUnsubscribed();
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(final Action0 action0) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() { // from class: rx.internal.schedulers.EventLoopsScheduler.EventLoopWorker.1
                @Override // p475rx.functions.Action0
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, 0L, (TimeUnit) null, this.serial);
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(final Action0 action0, long j, TimeUnit timeUnit) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() { // from class: rx.internal.schedulers.EventLoopsScheduler.EventLoopWorker.2
                @Override // p475rx.functions.Action0
                public void call() {
                    if (EventLoopWorker.this.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                }
            }, j, timeUnit, this.timed);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.EventLoopsScheduler$PoolWorker */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class PoolWorker extends NewThreadWorker {
        PoolWorker(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}
