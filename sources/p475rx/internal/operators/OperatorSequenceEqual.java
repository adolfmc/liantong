package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.functions.Func1;
import p475rx.functions.Func2;
import p475rx.internal.util.UtilityFunctions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSequenceEqual */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSequenceEqual {
    static final Object LOCAL_ONCOMPLETED = new Object();

    private OperatorSequenceEqual() {
        throw new IllegalStateException("No instances!");
    }

    static <T> Observable<Object> materializeLite(Observable<T> observable) {
        return Observable.concat(observable.map((Func1<T, Object>) new Func1<T, Object>() { // from class: rx.internal.operators.OperatorSequenceEqual.1
            @Override // p475rx.functions.Func1
            public Object call(T t) {
                return t;
            }
        }), Observable.just(LOCAL_ONCOMPLETED));
    }

    public static <T> Observable<Boolean> sequenceEqual(Observable<? extends T> observable, Observable<? extends T> observable2, final Func2<? super T, ? super T, Boolean> func2) {
        return Observable.zip(materializeLite(observable), materializeLite(observable2), new Func2<Object, Object, Boolean>() { // from class: rx.internal.operators.OperatorSequenceEqual.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // p475rx.functions.Func2
            public Boolean call(Object obj, Object obj2) {
                boolean z = obj == OperatorSequenceEqual.LOCAL_ONCOMPLETED;
                boolean z2 = obj2 == OperatorSequenceEqual.LOCAL_ONCOMPLETED;
                if (z && z2) {
                    return true;
                }
                if (z || z2) {
                    return false;
                }
                return (Boolean) Func2.this.call(obj, obj2);
            }
        }).all(UtilityFunctions.identity());
    }
}
