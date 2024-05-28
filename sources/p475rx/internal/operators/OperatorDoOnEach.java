package p475rx.internal.operators;

import java.util.Arrays;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Subscriber;
import p475rx.exceptions.CompositeException;
import p475rx.exceptions.Exceptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDoOnEach */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorDoOnEach<T> implements Observable.Operator<T, T> {
    final Observer<? super T> doOnEachObserver;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoOnEach(Observer<? super T> observer) {
        this.doOnEachObserver = observer;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDoOnEach.1
            private boolean done = false;

            @Override // p475rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                try {
                    OperatorDoOnEach.this.doOnEachObserver.onCompleted();
                    this.done = true;
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                Exceptions.throwIfFatal(th);
                if (this.done) {
                    return;
                }
                this.done = true;
                try {
                    OperatorDoOnEach.this.doOnEachObserver.onError(th);
                    subscriber.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    subscriber.onError(new CompositeException(Arrays.asList(th, th2)));
                }
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                try {
                    OperatorDoOnEach.this.doOnEachObserver.onNext(t);
                    subscriber.onNext(t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }
        };
    }
}
