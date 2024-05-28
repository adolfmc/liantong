package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.internal.operators.OperatorTimeoutBase;
import p475rx.schedulers.Schedulers;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTimeoutWithSelector */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorTimeoutWithSelector<T, U, V> extends OperatorTimeoutBase<T> {
    @Override // p475rx.internal.operators.OperatorTimeoutBase
    public /* bridge */ /* synthetic */ Subscriber call(Subscriber subscriber) {
        return super.call(subscriber);
    }

    public OperatorTimeoutWithSelector(final Func0<? extends Observable<U>> func0, final Func1<? super T, ? extends Observable<V>> func1, Observable<? extends T> observable) {
        super(new OperatorTimeoutBase.FirstTimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.1
            @Override // p475rx.functions.Func3
            public /* bridge */ /* synthetic */ Subscription call(Object obj, Long l, Scheduler.Worker worker) {
                return call((OperatorTimeoutBase.TimeoutSubscriber) ((OperatorTimeoutBase.TimeoutSubscriber) obj), l, worker);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, Scheduler.Worker worker) {
                Func0 func02 = Func0.this;
                if (func02 != null) {
                    try {
                        return ((Observable) func02.call()).unsafeSubscribe(new Subscriber<U>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.1.1
                            @Override // p475rx.Observer
                            public void onCompleted() {
                                timeoutSubscriber.onTimeout(l.longValue());
                            }

                            @Override // p475rx.Observer
                            public void onError(Throwable th) {
                                timeoutSubscriber.onError(th);
                            }

                            @Override // p475rx.Observer
                            public void onNext(U u) {
                                timeoutSubscriber.onTimeout(l.longValue());
                            }
                        });
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, timeoutSubscriber);
                        return Subscriptions.unsubscribed();
                    }
                }
                return Subscriptions.unsubscribed();
            }
        }, new OperatorTimeoutBase.TimeoutStub<T>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.2
            @Override // p475rx.functions.Func4
            public /* bridge */ /* synthetic */ Subscription call(Object obj, Long l, Object obj2, Scheduler.Worker worker) {
                return call((OperatorTimeoutBase.TimeoutSubscriber<Long>) obj, l, (Long) obj2, worker);
            }

            public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> timeoutSubscriber, final Long l, T t, Scheduler.Worker worker) {
                try {
                    return ((Observable) Func1.this.call(t)).unsafeSubscribe(new Subscriber<V>() { // from class: rx.internal.operators.OperatorTimeoutWithSelector.2.1
                        @Override // p475rx.Observer
                        public void onCompleted() {
                            timeoutSubscriber.onTimeout(l.longValue());
                        }

                        @Override // p475rx.Observer
                        public void onError(Throwable th) {
                            timeoutSubscriber.onError(th);
                        }

                        @Override // p475rx.Observer
                        public void onNext(V v) {
                            timeoutSubscriber.onTimeout(l.longValue());
                        }
                    });
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, timeoutSubscriber);
                    return Subscriptions.unsubscribed();
                }
            }
        }, observable, Schedulers.immediate());
    }
}
