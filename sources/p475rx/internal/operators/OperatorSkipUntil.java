package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.observers.SerializedSubscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSkipUntil */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSkipUntil<T, U> implements Observable.Operator<T, T> {
    final Observable<U> other;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkipUntil(Observable<U> observable) {
        this.other = observable;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorSkipUntil.1
            @Override // p475rx.Observer
            public void onNext(U u) {
                atomicBoolean.set(true);
                unsubscribe();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                serializedSubscriber.unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                unsubscribe();
            }
        };
        subscriber.add(subscriber2);
        this.other.unsafeSubscribe(subscriber2);
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipUntil.2
            @Override // p475rx.Observer
            public void onNext(T t) {
                if (atomicBoolean.get()) {
                    serializedSubscriber.onNext(t);
                } else {
                    request(1L);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                serializedSubscriber.onCompleted();
                unsubscribe();
            }
        };
    }
}
