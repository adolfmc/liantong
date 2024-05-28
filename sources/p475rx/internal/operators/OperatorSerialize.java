package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.observers.SerializedSubscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSerialize */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSerialize<T> implements Observable.Operator<T, T> {
    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorSerialize$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Holder {
        static final OperatorSerialize<Object> INSTANCE = new OperatorSerialize<>();

        private Holder() {
        }
    }

    public static <T> OperatorSerialize<T> instance() {
        return (OperatorSerialize<T>) Holder.INSTANCE;
    }

    OperatorSerialize() {
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new SerializedSubscriber(new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSerialize.1
            @Override // p475rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }
        });
    }
}
