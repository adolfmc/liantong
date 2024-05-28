package p475rx.internal.operators;

import java.util.HashSet;
import java.util.Set;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.functions.Func1;
import p475rx.internal.util.UtilityFunctions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDistinct */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDistinct<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends U> keySelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDistinct$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Holder {
        static final OperatorDistinct<?, ?> INSTANCE = new OperatorDistinct<>(UtilityFunctions.identity());

        private Holder() {
        }
    }

    public static <T> OperatorDistinct<T, T> instance() {
        return (OperatorDistinct<T, T>) Holder.INSTANCE;
    }

    public OperatorDistinct(Func1<? super T, ? extends U> func1) {
        this.keySelector = func1;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorDistinct.1
            Set<U> keyMemory = new HashSet();

            @Override // p475rx.Observer
            public void onNext(T t) {
                if (this.keyMemory.add(OperatorDistinct.this.keySelector.call(t))) {
                    subscriber.onNext(t);
                } else {
                    request(1L);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                this.keyMemory = null;
                subscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                this.keyMemory = null;
                subscriber.onCompleted();
            }
        };
    }
}
