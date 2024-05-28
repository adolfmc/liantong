package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorThrottleFirst */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorThrottleFirst<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long timeInMilliseconds;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorThrottleFirst(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.timeInMilliseconds = timeUnit.toMillis(j);
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorThrottleFirst.1
            private long lastOnNext = 0;

            @Override // p475rx.Subscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                long now = OperatorThrottleFirst.this.scheduler.now();
                long j = this.lastOnNext;
                if (j == 0 || now - j >= OperatorThrottleFirst.this.timeInMilliseconds) {
                    this.lastOnNext = now;
                    subscriber.onNext(t);
                }
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
