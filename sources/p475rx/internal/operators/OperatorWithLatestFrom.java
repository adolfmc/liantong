package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func2;
import p475rx.observers.SerializedSubscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorWithLatestFrom */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorWithLatestFrom<T, U, R> implements Observable.Operator<R, T> {
    static final Object EMPTY = new Object();
    final Observable<? extends U> other;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorWithLatestFrom(Observable<? extends U> observable, Func2<? super T, ? super U, ? extends R> func2) {
        this.other = observable;
        this.resultSelector = func2;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        subscriber.add(serializedSubscriber);
        final AtomicReference atomicReference = new AtomicReference(EMPTY);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>(serializedSubscriber, true) { // from class: rx.internal.operators.OperatorWithLatestFrom.1
            @Override // p475rx.Observer
            public void onNext(T t) {
                Object obj = atomicReference.get();
                if (obj != OperatorWithLatestFrom.EMPTY) {
                    try {
                        serializedSubscriber.onNext(OperatorWithLatestFrom.this.resultSelector.call(t, obj));
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, this);
                    }
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                serializedSubscriber.unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                serializedSubscriber.onCompleted();
                serializedSubscriber.unsubscribe();
            }
        };
        Subscriber<U> subscriber3 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorWithLatestFrom.2
            @Override // p475rx.Observer
            public void onNext(U u) {
                atomicReference.set(u);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                serializedSubscriber.unsubscribe();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (atomicReference.get() == OperatorWithLatestFrom.EMPTY) {
                    serializedSubscriber.onCompleted();
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        serializedSubscriber.add(subscriber2);
        serializedSubscriber.add(subscriber3);
        this.other.unsafeSubscribe(subscriber3);
        return subscriber2;
    }
}
