package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.functions.Action1;
import p475rx.observables.ConnectableObservable;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeAutoConnect */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeAutoConnect<T> implements Observable.OnSubscribe<T> {
    final AtomicInteger clients;
    final Action1<? super Subscription> connection;
    final int numberOfSubscribers;
    final ConnectableObservable<? extends T> source;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeAutoConnect(ConnectableObservable<? extends T> connectableObservable, int i, Action1<? super Subscription> action1) {
        if (i <= 0) {
            throw new IllegalArgumentException("numberOfSubscribers > 0 required");
        }
        this.source = connectableObservable;
        this.numberOfSubscribers = i;
        this.connection = action1;
        this.clients = new AtomicInteger();
    }

    public void call(Subscriber<? super T> subscriber) {
        this.source.unsafeSubscribe(Subscribers.wrap(subscriber));
        if (this.clients.incrementAndGet() == this.numberOfSubscribers) {
            this.source.connect(this.connection);
        }
    }
}
