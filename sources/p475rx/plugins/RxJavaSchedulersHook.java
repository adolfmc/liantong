package p475rx.plugins;

import java.util.concurrent.ThreadFactory;
import p475rx.Scheduler;
import p475rx.annotations.Experimental;
import p475rx.functions.Action0;
import p475rx.internal.schedulers.CachedThreadScheduler;
import p475rx.internal.schedulers.EventLoopsScheduler;
import p475rx.internal.schedulers.NewThreadScheduler;
import p475rx.internal.util.RxThreadFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.plugins.RxJavaSchedulersHook */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RxJavaSchedulersHook {
    private static final RxJavaSchedulersHook DEFAULT_INSTANCE = new RxJavaSchedulersHook();

    public Scheduler getComputationScheduler() {
        return null;
    }

    public Scheduler getIOScheduler() {
        return null;
    }

    public Scheduler getNewThreadScheduler() {
        return null;
    }

    public Action0 onSchedule(Action0 action0) {
        return action0;
    }

    @Experimental
    public static Scheduler createComputationScheduler() {
        return createComputationScheduler(new RxThreadFactory("RxComputationScheduler-"));
    }

    @Experimental
    public static Scheduler createComputationScheduler(ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory == null");
        }
        return new EventLoopsScheduler(threadFactory);
    }

    @Experimental
    public static Scheduler createIoScheduler() {
        return createIoScheduler(new RxThreadFactory("RxIoScheduler-"));
    }

    @Experimental
    public static Scheduler createIoScheduler(ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory == null");
        }
        return new CachedThreadScheduler(threadFactory);
    }

    @Experimental
    public static Scheduler createNewThreadScheduler() {
        return createNewThreadScheduler(new RxThreadFactory("RxNewThreadScheduler-"));
    }

    @Experimental
    public static Scheduler createNewThreadScheduler(ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory == null");
        }
        return new NewThreadScheduler(threadFactory);
    }

    protected RxJavaSchedulersHook() {
    }

    public static RxJavaSchedulersHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
