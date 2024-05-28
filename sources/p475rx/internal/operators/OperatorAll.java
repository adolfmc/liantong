package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func1;
import p475rx.internal.producers.SingleDelayedProducer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorAll */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorAll<T> implements Observable.Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;

    public OperatorAll(Func1<? super T, Boolean> func1) {
        this.predicate = func1;
    }

    @Override // p475rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorAll.1
            boolean done;

            @Override // p475rx.Observer
            public void onNext(T t) {
                try {
                    if (OperatorAll.this.predicate.call(t).booleanValue() || this.done) {
                        return;
                    }
                    this.done = true;
                    singleDelayedProducer.setValue(false);
                    unsubscribe();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                singleDelayedProducer.setValue(true);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }
}
