package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorIgnoreElements */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorIgnoreElements<T> implements Observable.Operator<T, T> {
    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorIgnoreElements$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Holder {
        static final OperatorIgnoreElements<?> INSTANCE = new OperatorIgnoreElements<>();

        private Holder() {
        }
    }

    public static <T> OperatorIgnoreElements<T> instance() {
        return (OperatorIgnoreElements<T>) Holder.INSTANCE;
    }

    OperatorIgnoreElements() {
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorIgnoreElements.1
            @Override // p475rx.Observer
            public void onNext(T t) {
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
        subscriber.add(subscriber2);
        return subscriber2;
    }
}
