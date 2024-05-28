package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.plugins.RxJavaObservableExecutionHook;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeLift */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeLift<T, R> implements Observable.OnSubscribe<R> {
    static final RxJavaObservableExecutionHook hook = RxJavaPlugins.getInstance().getObservableExecutionHook();
    final Observable.Operator<? extends R, ? super T> operator;
    final Observable.OnSubscribe<T> parent;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeLift(Observable.OnSubscribe<T> onSubscribe, Observable.Operator<? extends R, ? super T> operator) {
        this.parent = onSubscribe;
        this.operator = operator;
    }

    public void call(Subscriber<? super R> subscriber) {
        try {
            Subscriber subscriber2 = (Subscriber) hook.onLift(this.operator).call(subscriber);
            subscriber2.onStart();
            this.parent.call(subscriber2);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }
}
