package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.observers.Subscribers;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDoOnUnsubscribe */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorDoOnUnsubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 unsubscribe;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnUnsubscribe(Action0 action0) {
        this.unsubscribe = action0;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        subscriber.add(Subscriptions.create(this.unsubscribe));
        return Subscribers.wrap(subscriber);
    }
}
