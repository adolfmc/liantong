package p475rx.internal.schedulers;

import p475rx.Scheduler;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.schedulers.SleepingAction */
/* loaded from: E:\9227576_dexfile_execute.dex */
class SleepingAction implements Action0 {
    private final long execTime;
    private final Scheduler.Worker innerScheduler;
    private final Action0 underlying;

    public SleepingAction(Action0 action0, Scheduler.Worker worker, long j) {
        this.underlying = action0;
        this.innerScheduler = worker;
        this.execTime = j;
    }

    @Override // p475rx.functions.Action0
    public void call() {
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        long now = this.execTime - this.innerScheduler.now();
        if (now > 0) {
            try {
                Thread.sleep(now);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        if (this.innerScheduler.isUnsubscribed()) {
            return;
        }
        this.underlying.call();
    }
}
