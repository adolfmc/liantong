package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.observers.SerializedSubscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTakeUntil */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTakeUntil<T, E> implements Observable.Operator<T, T> {
    private final Observable<? extends E> other;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeUntil(Observable<? extends E> observable) {
        this.other = observable;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(serializedSubscriber, false) { // from class: rx.internal.operators.OperatorTakeUntil.1
            @Override // p475rx.Observer
            public void onNext(T t) {
                serializedSubscriber.onNext(t);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                try {
                    serializedSubscriber.onError(th);
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                try {
                    serializedSubscriber.onCompleted();
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        Subscriber<E> subscriber3 = new Subscriber<E>() { // from class: rx.internal.operators.OperatorTakeUntil.2
            @Override // p475rx.Subscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber2.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber2.onError(th);
            }

            @Override // p475rx.Observer
            public void onNext(E e) {
                onCompleted();
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        subscriber.add(serializedSubscriber);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
