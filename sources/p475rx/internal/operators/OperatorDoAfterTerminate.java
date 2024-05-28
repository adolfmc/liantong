package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDoAfterTerminate */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDoAfterTerminate<T> implements Observable.Operator<T, T> {
    final Action0 action;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDoAfterTerminate(Action0 action0) {
        if (action0 == null) {
            throw new NullPointerException("Action can not be null");
        }
        this.action = action0;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDoAfterTerminate.1
            @Override // p475rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                try {
                    subscriber.onError(th);
                } finally {
                    callAction();
                }
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                try {
                    subscriber.onCompleted();
                } finally {
                    callAction();
                }
            }

            void callAction() {
                try {
                    OperatorDoAfterTerminate.this.action.call();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                }
            }
        };
    }
}
