package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorUnsubscribeOn */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorUnsubscribeOn<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorUnsubscribeOn(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorUnsubscribeOn.1
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
                subscriber.onNext(t);
            }
        };
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorUnsubscribeOn.2
            @Override // p475rx.functions.Action0
            public void call() {
                final Scheduler.Worker createWorker = OperatorUnsubscribeOn.this.scheduler.createWorker();
                createWorker.schedule(new Action0() { // from class: rx.internal.operators.OperatorUnsubscribeOn.2.1
                    @Override // p475rx.functions.Action0
                    public void call() {
                        subscriber2.unsubscribe();
                        createWorker.unsubscribe();
                    }
                });
            }
        }));
        return subscriber2;
    }
}
