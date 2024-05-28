package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeDelaySubscription */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeDelaySubscription<T> implements Observable.OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<? extends T> source;
    final long time;
    final TimeUnit unit;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeDelaySubscription(Observable<? extends T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.source = observable;
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public void call(final Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        createWorker.schedule(new Action0() { // from class: rx.internal.operators.OnSubscribeDelaySubscription.1
            @Override // p475rx.functions.Action0
            public void call() {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                OnSubscribeDelaySubscription.this.source.unsafeSubscribe(Subscribers.wrap(subscriber));
            }
        }, this.time, this.unit);
    }
}
