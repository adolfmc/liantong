package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorAsObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorAsObservable<T> implements Observable.Operator<T, T> {
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return subscriber;
    }

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorAsObservable$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Holder {
        static final OperatorAsObservable<Object> INSTANCE = new OperatorAsObservable<>();

        private Holder() {
        }
    }

    public static <T> OperatorAsObservable<T> instance() {
        return (OperatorAsObservable<T>) Holder.INSTANCE;
    }

    OperatorAsObservable() {
    }
}
