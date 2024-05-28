package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.internal.operators.OperatorTimeoutBase;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTimeout */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTimeout<T> extends OperatorTimeoutBase<T> {
    @Override // p475rx.internal.operators.OperatorTimeoutBase
    public /* bridge */ /* synthetic */ Subscriber call(Subscriber subscriber) {
        return super.call(subscriber);
    }

    public OperatorTimeout(final long j, final TimeUnit timeUnit, Observable<? extends T> observable, Scheduler scheduler) {
        super(new OperatorTimeoutBase.FirstTimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeout.1
            @Override // p475rx.functions.Func3
            public /* bridge */ /* synthetic */ Subscription call(Object obj, Long l, Scheduler.Worker worker) {
                return call((OperatorTimeoutBase.TimeoutSubscriber) ((OperatorTimeoutBase.TimeoutSubscriber) obj), l, worker);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, Scheduler.Worker worker) {
                return worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorTimeout.1.1
                    @Override // p475rx.functions.Action0
                    public void call() {
                        timeoutSubscriber.onTimeout(l.longValue());
                    }
                }, j, timeUnit);
            }
        }, new OperatorTimeoutBase.TimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeout.2
            @Override // p475rx.functions.Func4
            public /* bridge */ /* synthetic */ Subscription call(Object obj, Long l, Object obj2, Scheduler.Worker worker) {
                return call((OperatorTimeoutBase.TimeoutSubscriber<Long>) obj, l, (Long) obj2, worker);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, T t, Scheduler.Worker worker) {
                return worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorTimeout.2.1
                    @Override // p475rx.functions.Action0
                    public void call() {
                        timeoutSubscriber.onTimeout(l.longValue());
                    }
                }, j, timeUnit);
            }
        }, observable, scheduler);
    }
}
