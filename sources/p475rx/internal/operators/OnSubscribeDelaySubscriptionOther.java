package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.observers.Subscribers;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.SerialSubscription;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeDelaySubscriptionOther */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeDelaySubscriptionOther<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> main;
    final Observable<U> other;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeDelaySubscriptionOther(Observable<? extends T> observable, Observable<U> observable2) {
        this.main = observable;
        this.other = observable2;
    }

    public void call(Subscriber<? super T> subscriber) {
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        final Subscriber wrap = Subscribers.wrap(subscriber);
        Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionOther.1
            boolean done;

            @Override // p475rx.Observer
            public void onNext(U u) {
                onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                    return;
                }
                this.done = true;
                wrap.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                serialSubscription.set(Subscriptions.unsubscribed());
                OnSubscribeDelaySubscriptionOther.this.main.unsafeSubscribe(wrap);
            }
        };
        serialSubscription.set(subscriber2);
        this.other.unsafeSubscribe(subscriber2);
    }
}
