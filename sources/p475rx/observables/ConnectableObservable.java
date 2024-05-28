package p475rx.observables;

import p475rx.Observable;
import p475rx.Subscription;
import p475rx.annotations.Beta;
import p475rx.functions.Action1;
import p475rx.functions.Actions;
import p475rx.internal.operators.OnSubscribeAutoConnect;
import p475rx.internal.operators.OnSubscribeRefCount;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observables.ConnectableObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {
    public abstract void connect(Action1<? super Subscription> action1);

    /* JADX INFO: Access modifiers changed from: protected */
    public ConnectableObservable(Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public final Subscription connect() {
        final Subscription[] subscriptionArr = new Subscription[1];
        connect(new Action1<Subscription>() { // from class: rx.observables.ConnectableObservable.1
            @Override // p475rx.functions.Action1
            public void call(Subscription subscription) {
                subscriptionArr[0] = subscription;
            }
        });
        return subscriptionArr[0];
    }

    public Observable<T> refCount() {
        return create(new OnSubscribeRefCount(this));
    }

    @Beta
    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    @Beta
    public Observable<T> autoConnect(int i) {
        return autoConnect(i, Actions.empty());
    }

    @Beta
    public Observable<T> autoConnect(int i, Action1<? super Subscription> action1) {
        if (i <= 0) {
            connect(action1);
            return this;
        }
        return create(new OnSubscribeAutoConnect(this, i, action1));
    }
}
