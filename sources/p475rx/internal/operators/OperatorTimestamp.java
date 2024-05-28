package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.schedulers.Timestamped;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTimestamp */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTimestamp<T> implements Observable.Operator<Timestamped<T>, T> {
    final Scheduler scheduler;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTimestamp(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super Timestamped<T>> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorTimestamp.1
            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                subscriber.onNext(new Timestamped(OperatorTimestamp.this.scheduler.now(), t));
            }
        };
    }
}
