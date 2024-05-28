package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.NeverObservableHolder */
/* loaded from: E:\9227576_dexfile_execute.dex */
public enum NeverObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;
    
    static final Observable<Object> NEVER = Observable.create(INSTANCE);

    @Override // p475rx.functions.Action1
    public void call(Subscriber<? super Object> subscriber) {
    }

    public static <T> Observable<T> instance() {
        return (Observable<T>) NEVER;
    }
}
