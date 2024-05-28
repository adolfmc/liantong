package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSkipTimed */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSkipTimed<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkipTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        createWorker.schedule(new Action0() { // from class: rx.internal.operators.OperatorSkipTimed.1
            @Override // p475rx.functions.Action0
            public void call() {
                atomicBoolean.set(true);
            }
        }, this.time, this.unit);
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipTimed.2
            @Override // p475rx.Observer
            public void onNext(T t) {
                if (atomicBoolean.get()) {
                    subscriber.onNext(t);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                try {
                    subscriber.onError(th);
                } finally {
                    unsubscribe();
                }
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                try {
                    subscriber.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        };
    }
}
