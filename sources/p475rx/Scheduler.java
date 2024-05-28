package p475rx;

import java.util.concurrent.TimeUnit;
import p475rx.functions.Action0;
import p475rx.subscriptions.MultipleAssignmentSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.Scheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Scheduler {
    static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public abstract Worker createWorker();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.Scheduler$Worker */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(final Action0 action0, long j, long j2, TimeUnit timeUnit) {
            final long nanos = timeUnit.toNanos(j2);
            final long nanos2 = TimeUnit.MILLISECONDS.toNanos(now());
            final long nanos3 = nanos2 + timeUnit.toNanos(j);
            final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            Action0 action02 = new Action0() { // from class: rx.Scheduler.Worker.1
                long count;
                long lastNowNanos;
                long startInNanos;

                {
                    this.lastNowNanos = nanos2;
                    this.startInNanos = nanos3;
                }

                @Override // p475rx.functions.Action0
                public void call() {
                    long j3;
                    if (multipleAssignmentSubscription.isUnsubscribed()) {
                        return;
                    }
                    action0.call();
                    long nanos4 = TimeUnit.MILLISECONDS.toNanos(Worker.this.now());
                    long j4 = this.lastNowNanos;
                    if (Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS + nanos4 < j4 || nanos4 >= j4 + nanos + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOS) {
                        long j5 = nanos;
                        long j6 = nanos4 + j5;
                        long j7 = this.count + 1;
                        this.count = j7;
                        this.startInNanos = j6 - (j5 * j7);
                        j3 = j6;
                    } else {
                        long j8 = this.startInNanos;
                        long j9 = this.count + 1;
                        this.count = j9;
                        j3 = j8 + (j9 * nanos);
                    }
                    this.lastNowNanos = nanos4;
                    multipleAssignmentSubscription.set(Worker.this.schedule(this, j3 - nanos4, TimeUnit.NANOSECONDS));
                }
            };
            MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
            multipleAssignmentSubscription.set(multipleAssignmentSubscription2);
            multipleAssignmentSubscription2.set(schedule(action02, j, timeUnit));
            return multipleAssignmentSubscription;
        }

        public long now() {
            return System.currentTimeMillis();
        }
    }

    public long now() {
        return System.currentTimeMillis();
    }
}
