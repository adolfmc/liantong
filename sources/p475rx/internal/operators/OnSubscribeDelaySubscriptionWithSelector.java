package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func0;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeDelaySubscriptionWithSelector<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> source;
    final Func0<? extends Observable<U>> subscriptionDelay;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> observable, Func0<? extends Observable<U>> func0) {
        this.source = observable;
        this.subscriptionDelay = func0;
    }

    public void call(final Subscriber<? super T> subscriber) {
        try {
            this.subscriptionDelay.call().take(1).unsafeSubscribe((Subscriber<U>) new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector.1
                @Override // p475rx.Observer
                public void onNext(U u) {
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap(subscriber));
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }
            });
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
