package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func0;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeDefer */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeDefer<T> implements Observable.OnSubscribe<T> {
    final Func0<? extends Observable<? extends T>> observableFactory;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeDefer(Func0<? extends Observable<? extends T>> func0) {
        this.observableFactory = func0;
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            this.observableFactory.call().unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
