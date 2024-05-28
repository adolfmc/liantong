package p475rx.schedulers;

import java.util.concurrent.Executor;
import p475rx.Scheduler;
import p475rx.internal.schedulers.ExecutorScheduler;
import p475rx.internal.schedulers.GenericScheduledExecutorService;
import p475rx.internal.schedulers.ImmediateScheduler;
import p475rx.internal.schedulers.SchedulerLifecycle;
import p475rx.internal.schedulers.TrampolineScheduler;
import p475rx.internal.util.RxRingBuffer;
import p475rx.plugins.RxJavaPlugins;
import p475rx.plugins.RxJavaSchedulersHook;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.schedulers.Schedulers */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Schedulers {
    private static final Schedulers INSTANCE = new Schedulers();
    private final Scheduler computationScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler newThreadScheduler;

    private Schedulers() {
        RxJavaSchedulersHook schedulersHook = RxJavaPlugins.getInstance().getSchedulersHook();
        Scheduler computationScheduler = schedulersHook.getComputationScheduler();
        if (computationScheduler != null) {
            this.computationScheduler = computationScheduler;
        } else {
            this.computationScheduler = RxJavaSchedulersHook.createComputationScheduler();
        }
        Scheduler iOScheduler = schedulersHook.getIOScheduler();
        if (iOScheduler != null) {
            this.ioScheduler = iOScheduler;
        } else {
            this.ioScheduler = RxJavaSchedulersHook.createIoScheduler();
        }
        Scheduler newThreadScheduler = schedulersHook.getNewThreadScheduler();
        if (newThreadScheduler != null) {
            this.newThreadScheduler = newThreadScheduler;
        } else {
            this.newThreadScheduler = RxJavaSchedulersHook.createNewThreadScheduler();
        }
    }

    public static Scheduler immediate() {
        return ImmediateScheduler.INSTANCE;
    }

    public static Scheduler trampoline() {
        return TrampolineScheduler.INSTANCE;
    }

    public static Scheduler newThread() {
        return INSTANCE.newThreadScheduler;
    }

    public static Scheduler computation() {
        return INSTANCE.computationScheduler;
    }

    /* renamed from: io */
    public static Scheduler m117io() {
        return INSTANCE.ioScheduler;
    }

    public static TestScheduler test() {
        return new TestScheduler();
    }

    public static Scheduler from(Executor executor) {
        return new ExecutorScheduler(executor);
    }

    static void start() {
        Schedulers schedulers = INSTANCE;
        synchronized (schedulers) {
            if (schedulers.computationScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.computationScheduler).start();
            }
            if (schedulers.ioScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.ioScheduler).start();
            }
            if (schedulers.newThreadScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.newThreadScheduler).start();
            }
            GenericScheduledExecutorService.INSTANCE.start();
            RxRingBuffer.SPSC_POOL.start();
            RxRingBuffer.SPMC_POOL.start();
        }
    }

    public static void shutdown() {
        Schedulers schedulers = INSTANCE;
        synchronized (schedulers) {
            if (schedulers.computationScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.computationScheduler).shutdown();
            }
            if (schedulers.ioScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.ioScheduler).shutdown();
            }
            if (schedulers.newThreadScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle) schedulers.newThreadScheduler).shutdown();
            }
            GenericScheduledExecutorService.INSTANCE.shutdown();
            RxRingBuffer.SPSC_POOL.shutdown();
            RxRingBuffer.SPMC_POOL.shutdown();
        }
    }
}
