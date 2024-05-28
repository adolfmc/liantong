package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.schedulers.TimeInterval;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTimeInterval */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTimeInterval<T> implements Observable.Operator<TimeInterval<T>, T> {
    final Scheduler scheduler;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTimeInterval(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super TimeInterval<T>> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorTimeInterval.1
            private long lastTimestamp;

            {
                this.lastTimestamp = OperatorTimeInterval.this.scheduler.now();
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                long now = OperatorTimeInterval.this.scheduler.now();
                subscriber.onNext(new TimeInterval(now - this.lastTimestamp, t));
                this.lastTimestamp = now;
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
    }
}
