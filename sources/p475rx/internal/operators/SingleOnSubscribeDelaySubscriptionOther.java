package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Single;
import p475rx.SingleSubscriber;
import p475rx.Subscriber;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SingleOnSubscribeDelaySubscriptionOther<T> implements Single.OnSubscribe<T> {
    final Single<? extends T> main;
    final Observable<?> other;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleOnSubscribeDelaySubscriptionOther(Single<? extends T> single, Observable<?> observable) {
        this.main = single;
        this.other = observable;
    }

    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.1
            @Override // p475rx.SingleSubscriber
            public void onSuccess(T t) {
                singleSubscriber.onSuccess(t);
            }

            @Override // p475rx.SingleSubscriber
            public void onError(Throwable th) {
                singleSubscriber.onError(th);
            }
        };
        final SerialSubscription serialSubscription = new SerialSubscription();
        singleSubscriber.add(serialSubscription);
        Subscriber<? super Object> subscriber = new Subscriber<Object>() { // from class: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.2
            boolean done;

            @Override // p475rx.Observer
            public void onNext(Object obj) {
                onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                    return;
                }
                this.done = true;
                singleSubscriber2.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                serialSubscription.set(singleSubscriber2);
                SingleOnSubscribeDelaySubscriptionOther.this.main.subscribe(singleSubscriber2);
            }
        };
        serialSubscription.set(subscriber);
        this.other.subscribe(subscriber);
    }
}
