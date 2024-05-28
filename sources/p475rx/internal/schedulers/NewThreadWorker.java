package p475rx.internal.schedulers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.internal.util.PlatformDependent;
import p475rx.internal.util.RxThreadFactory;
import p475rx.internal.util.SubscriptionList;
import p475rx.plugins.RxJavaPlugins;
import p475rx.plugins.RxJavaSchedulersHook;
import p475rx.subscriptions.CompositeSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.NewThreadWorker */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NewThreadWorker extends Scheduler.Worker implements Subscription {
    private static final String FREQUENCY_KEY = "rx.scheduler.jdk6.purge-frequency-millis";
    private static final String PURGE_FORCE_KEY = "rx.scheduler.jdk6.purge-force";
    private static final String PURGE_THREAD_PREFIX = "RxSchedulerPurge-";
    private static final Object SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
    private static final boolean SHOULD_TRY_ENABLE_CANCEL_POLICY;
    private static volatile Object cachedSetRemoveOnCancelPolicyMethod;
    private final ScheduledExecutorService executor;
    volatile boolean isUnsubscribed;
    private final RxJavaSchedulersHook schedulersHook;
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap<>();
    private static final AtomicReference<ScheduledExecutorService> PURGE = new AtomicReference<>();
    public static final int PURGE_FREQUENCY = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int androidApiVersion = PlatformDependent.getAndroidApiVersion();
        SHOULD_TRY_ENABLE_CANCEL_POLICY = !z && (androidApiVersion == 0 || androidApiVersion >= 21);
        SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED = new Object();
    }

    public static void registerExecutor(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (true) {
            if (PURGE.get() != null) {
                break;
            }
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (PURGE.compareAndSet(null, newScheduledThreadPool)) {
                Runnable runnable = new Runnable() { // from class: rx.internal.schedulers.NewThreadWorker.1
                    @Override // java.lang.Runnable
                    public void run() {
                        NewThreadWorker.purgeExecutors();
                    }
                };
                int i = PURGE_FREQUENCY;
                newScheduledThreadPool.scheduleAtFixedRate(runnable, i, i, TimeUnit.MILLISECONDS);
                break;
            }
            newScheduledThreadPool.shutdownNow();
        }
        EXECUTORS.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static void deregisterExecutor(ScheduledExecutorService scheduledExecutorService) {
        EXECUTORS.remove(scheduledExecutorService);
    }

    static void purgeExecutors() {
        try {
            Iterator<ScheduledThreadPoolExecutor> it = EXECUTORS.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor next = it.next();
                if (!next.isShutdown()) {
                    next.purge();
                } else {
                    it.remove();
                }
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        }
    }

    public static boolean tryEnableCancelPolicy(ScheduledExecutorService scheduledExecutorService) {
        Method findSetRemoveOnCancelPolicyMethod;
        if (SHOULD_TRY_ENABLE_CANCEL_POLICY) {
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = cachedSetRemoveOnCancelPolicyMethod;
                if (obj == SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED) {
                    return false;
                }
                if (obj == null) {
                    findSetRemoveOnCancelPolicyMethod = findSetRemoveOnCancelPolicyMethod(scheduledExecutorService);
                    cachedSetRemoveOnCancelPolicyMethod = findSetRemoveOnCancelPolicyMethod != null ? findSetRemoveOnCancelPolicyMethod : SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
                } else {
                    findSetRemoveOnCancelPolicyMethod = (Method) obj;
                }
            } else {
                findSetRemoveOnCancelPolicyMethod = findSetRemoveOnCancelPolicyMethod(scheduledExecutorService);
            }
            if (findSetRemoveOnCancelPolicyMethod != null) {
                try {
                    findSetRemoveOnCancelPolicyMethod.invoke(scheduledExecutorService, true);
                    return true;
                } catch (Exception e) {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                }
            }
        }
        return false;
    }

    static Method findSetRemoveOnCancelPolicyMethod(ScheduledExecutorService scheduledExecutorService) {
        Method[] methods;
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public NewThreadWorker(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!tryEnableCancelPolicy(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            registerExecutor((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.schedulersHook = RxJavaPlugins.getInstance().getSchedulersHook();
        this.executor = newScheduledThreadPool;
    }

    @Override // p475rx.Scheduler.Worker
    public Subscription schedule(Action0 action0) {
        return schedule(action0, 0L, null);
    }

    @Override // p475rx.Scheduler.Worker
    public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
        if (this.isUnsubscribed) {
            return Subscriptions.unsubscribed();
        }
        return scheduleActual(action0, j, timeUnit);
    }

    public ScheduledAction scheduleActual(Action0 action0, long j, TimeUnit timeUnit) {
        Future<?> schedule;
        ScheduledAction scheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(action0));
        if (j <= 0) {
            schedule = this.executor.submit(scheduledAction);
        } else {
            schedule = this.executor.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(schedule);
        return scheduledAction;
    }

    public ScheduledAction scheduleActual(Action0 action0, long j, TimeUnit timeUnit, CompositeSubscription compositeSubscription) {
        Future<?> schedule;
        ScheduledAction scheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(action0), compositeSubscription);
        compositeSubscription.add(scheduledAction);
        if (j <= 0) {
            schedule = this.executor.submit(scheduledAction);
        } else {
            schedule = this.executor.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(schedule);
        return scheduledAction;
    }

    public ScheduledAction scheduleActual(Action0 action0, long j, TimeUnit timeUnit, SubscriptionList subscriptionList) {
        Future<?> schedule;
        ScheduledAction scheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(action0), subscriptionList);
        subscriptionList.add(scheduledAction);
        if (j <= 0) {
            schedule = this.executor.submit(scheduledAction);
        } else {
            schedule = this.executor.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(schedule);
        return scheduledAction;
    }

    @Override // p475rx.Subscription
    public void unsubscribe() {
        this.isUnsubscribed = true;
        this.executor.shutdownNow();
        deregisterExecutor(this.executor);
    }

    @Override // p475rx.Subscription
    public boolean isUnsubscribed() {
        return this.isUnsubscribed;
    }
}
