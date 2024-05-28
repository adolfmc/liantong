package p475rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.subscriptions.BooleanSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.ImmediateScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ImmediateScheduler extends Scheduler {
    public static final ImmediateScheduler INSTANCE = new ImmediateScheduler();

    private ImmediateScheduler() {
    }

    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new InnerImmediateScheduler();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.ImmediateScheduler$InnerImmediateScheduler */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class InnerImmediateScheduler extends Scheduler.Worker implements Subscription {
        final BooleanSubscription innerSubscription = new BooleanSubscription();

        InnerImmediateScheduler() {
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            return schedule(new SleepingAction(action0, this, ImmediateScheduler.this.now() + timeUnit.toMillis(j)));
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            action0.call();
            return Subscriptions.unsubscribed();
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
    }
}
