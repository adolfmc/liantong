package p475rx.schedulers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import p475rx.Scheduler;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.subscriptions.BooleanSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.schedulers.TestScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TestScheduler extends Scheduler {
    static long counter;
    final Queue<TimedAction> queue = new PriorityQueue(11, new CompareActionsByTime());
    long time;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.schedulers.TestScheduler$TimedAction */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class TimedAction {
        final Action0 action;
        private final long count;
        final Scheduler.Worker scheduler;
        final long time;

        TimedAction(Scheduler.Worker worker, long j, Action0 action0) {
            long j2 = TestScheduler.counter;
            TestScheduler.counter = 1 + j2;
            this.count = j2;
            this.time = j;
            this.action = action0;
            this.scheduler = worker;
        }

        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", Long.valueOf(this.time), this.action.toString());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.schedulers.TestScheduler$CompareActionsByTime */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class CompareActionsByTime implements Comparator<TimedAction> {
        CompareActionsByTime() {
        }

        @Override // java.util.Comparator
        public int compare(TimedAction timedAction, TimedAction timedAction2) {
            if (timedAction.time == timedAction2.time) {
                if (timedAction.count < timedAction2.count) {
                    return -1;
                }
                return timedAction.count > timedAction2.count ? 1 : 0;
            } else if (timedAction.time < timedAction2.time) {
                return -1;
            } else {
                return timedAction.time > timedAction2.time ? 1 : 0;
            }
        }
    }

    @Override // p475rx.Scheduler
    public long now() {
        return TimeUnit.NANOSECONDS.toMillis(this.time);
    }

    public void advanceTimeBy(long j, TimeUnit timeUnit) {
        advanceTimeTo(this.time + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long j, TimeUnit timeUnit) {
        triggerActions(timeUnit.toNanos(j));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long j) {
        while (!this.queue.isEmpty()) {
            TimedAction peek = this.queue.peek();
            if (peek.time > j) {
                break;
            }
            this.time = peek.time == 0 ? this.time : peek.time;
            this.queue.remove();
            if (!peek.scheduler.isUnsubscribed()) {
                peek.action.call();
            }
        }
        this.time = j;
    }

    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new InnerTestScheduler();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.schedulers.TestScheduler$InnerTestScheduler */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    final class InnerTestScheduler extends Scheduler.Worker {

        /* renamed from: s */
        private final BooleanSubscription f27641s = new BooleanSubscription();

        InnerTestScheduler() {
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            this.f27641s.unsubscribe();
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.f27641s.isUnsubscribed();
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            final TimedAction timedAction = new TimedAction(this, TestScheduler.this.time + timeUnit.toNanos(j), action0);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() { // from class: rx.schedulers.TestScheduler.InnerTestScheduler.1
                @Override // p475rx.functions.Action0
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }

        @Override // p475rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            final TimedAction timedAction = new TimedAction(this, 0L, action0);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() { // from class: rx.schedulers.TestScheduler.InnerTestScheduler.2
                @Override // p475rx.functions.Action0
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }

        @Override // p475rx.Scheduler.Worker
        public long now() {
            return TestScheduler.this.now();
        }
    }
}
