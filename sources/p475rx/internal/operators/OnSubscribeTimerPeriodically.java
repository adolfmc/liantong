package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeTimerPeriodically */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeTimerPeriodically implements Observable.OnSubscribe<Long> {
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;

    public OnSubscribeTimerPeriodically(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.initialDelay = j;
        this.period = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // p475rx.functions.Action1
    public void call(final Subscriber<? super Long> subscriber) {
        final Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        createWorker.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OnSubscribeTimerPeriodically.1
            long counter;

            @Override // p475rx.functions.Action0
            public void call() {
                try {
                    Subscriber subscriber2 = subscriber;
                    long j = this.counter;
                    this.counter = 1 + j;
                    subscriber2.onNext(Long.valueOf(j));
                } catch (Throwable th) {
                    try {
                        createWorker.unsubscribe();
                    } finally {
                        Exceptions.throwOrReport(th, subscriber);
                    }
                }
            }
        }, this.initialDelay, this.period, this.unit);
    }
}
