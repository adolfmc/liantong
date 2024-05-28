package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDoOnSubscribe */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorDoOnSubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 subscribe;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnSubscribe(Action0 action0) {
        this.subscribe = action0;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        this.subscribe.call();
        return Subscribers.wrap(subscriber);
    }
}
