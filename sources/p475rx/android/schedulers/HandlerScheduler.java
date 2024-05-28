package p475rx.android.schedulers;

import android.os.Handler;
import java.util.concurrent.TimeUnit;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.android.plugins.RxAndroidPlugins;
import p475rx.functions.Action0;
import p475rx.internal.schedulers.ScheduledAction;
import p475rx.subscriptions.CompositeSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.android.schedulers.HandlerScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class HandlerScheduler extends Scheduler {
    private final Handler handler;

    public static HandlerScheduler from(Handler handler) {
        if (handler == null) {
            throw new NullPointerException("handler == null");
        }
        return new HandlerScheduler(handler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new HandlerWorker(this.handler);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.android.schedulers.HandlerScheduler$HandlerWorker */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class HandlerWorker extends Scheduler.Worker {
        private final CompositeSubscription compositeSubscription = new CompositeSubscription();
        private final Handler handler;

        HandlerWorker(Handler handler) {
            this.handler = handler;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            this.compositeSubscription.unsubscribe();
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.compositeSubscription.isUnsubscribed();
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            if (this.compositeSubscription.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final ScheduledAction scheduledAction = new ScheduledAction(RxAndroidPlugins.getInstance().getSchedulersHook().onSchedule(action0));
            scheduledAction.addParent(this.compositeSubscription);
            this.compositeSubscription.add(scheduledAction);
            this.handler.postDelayed(scheduledAction, timeUnit.toMillis(j));
            scheduledAction.add(Subscriptions.create(new Action0() { // from class: rx.android.schedulers.HandlerScheduler.HandlerWorker.1
                @Override // p475rx.functions.Action0
                public void call() {
                    HandlerWorker.this.handler.removeCallbacks(scheduledAction);
                }
            }));
            return scheduledAction;
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            return schedule(action0, 0L, TimeUnit.MILLISECONDS);
        }
    }
}
