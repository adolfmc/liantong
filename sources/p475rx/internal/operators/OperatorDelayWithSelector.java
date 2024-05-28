package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.observers.SerializedSubscriber;
import p475rx.observers.Subscribers;
import p475rx.subjects.PublishSubject;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDelayWithSelector */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDelayWithSelector<T, V> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends Observable<V>> itemDelay;
    final Observable<? extends T> source;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDelayWithSelector(Observable<? extends T> observable, Func1<? super T, ? extends Observable<V>> func1) {
        this.source = observable;
        this.itemDelay = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        final PublishSubject create = PublishSubject.create();
        subscriber.add(Observable.merge(create).unsafeSubscribe(Subscribers.from(serializedSubscriber)));
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDelayWithSelector.1
            @Override // p475rx.Observer
            public void onCompleted() {
                create.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p475rx.Observer
            public void onNext(final T t) {
                try {
                    create.onNext(OperatorDelayWithSelector.this.itemDelay.call(t).take(1).defaultIfEmpty(null).map((Func1<V, T>) new Func1<V, T>() { // from class: rx.internal.operators.OperatorDelayWithSelector.1.1
                        @Override // p475rx.functions.Func1
                        public T call(V v) {
                            return (T) t;
                        }
                    }));
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        };
    }
}
