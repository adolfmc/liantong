package p475rx.internal.schedulers;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.subscriptions.BooleanSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.TrampolineScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class TrampolineScheduler extends Scheduler {
    public static final TrampolineScheduler INSTANCE = new TrampolineScheduler();

    static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new InnerCurrentThreadScheduler();
    }

    private TrampolineScheduler() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.TrampolineScheduler$InnerCurrentThreadScheduler */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class InnerCurrentThreadScheduler extends Scheduler.Worker implements Subscription {
        final AtomicInteger counter = new AtomicInteger();
        final PriorityBlockingQueue<TimedAction> queue = new PriorityBlockingQueue<>();
        private final BooleanSubscription innerSubscription = new BooleanSubscription();
        private final AtomicInteger wip = new AtomicInteger();

        InnerCurrentThreadScheduler() {
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            return enqueue(action0, now());
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            long now = now() + timeUnit.toMillis(j);
            return enqueue(new SleepingAction(action0, this, now), now);
        }

        private Subscription enqueue(Action0 action0, long j) {
            if (this.innerSubscription.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            final TimedAction timedAction = new TimedAction(action0, Long.valueOf(j), this.counter.incrementAndGet());
            this.queue.add(timedAction);
            if (this.wip.getAndIncrement() == 0) {
                do {
                    TimedAction poll = this.queue.poll();
                    if (poll != null) {
                        poll.action.call();
                    }
                } while (this.wip.decrementAndGet() > 0);
                return Subscriptions.unsubscribed();
            }
            return Subscriptions.create(new Action0() { // from class: rx.internal.schedulers.TrampolineScheduler.InnerCurrentThreadScheduler.1
                @Override // p475rx.functions.Action0
                public void call() {
                    InnerCurrentThreadScheduler.this.queue.remove(timedAction);
                }
            });
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.schedulers.TrampolineScheduler$TimedAction */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class TimedAction implements Comparable<TimedAction> {
        final Action0 action;
        final int count;
        final Long execTime;

        TimedAction(Action0 action0, Long l, int i) {
            this.action = action0;
            this.execTime = l;
            this.count = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedAction timedAction) {
            int compareTo = this.execTime.compareTo(timedAction.execTime);
            return compareTo == 0 ? TrampolineScheduler.compare(this.count, timedAction.count) : compareTo;
        }
    }
}
