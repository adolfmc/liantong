package p475rx.schedulers;

import p475rx.Scheduler;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: rx.schedulers.NewThreadScheduler */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class NewThreadScheduler extends Scheduler {
    @Override // p475rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }

    private NewThreadScheduler() {
        throw new AssertionError();
    }
}
