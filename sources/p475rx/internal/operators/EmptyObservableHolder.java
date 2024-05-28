package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.EmptyObservableHolder */
/* loaded from: E:\9227576_dexfile_execute.dex */
public enum EmptyObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;
    
    static final Observable<Object> EMPTY = Observable.create(INSTANCE);

    public static <T> Observable<T> instance() {
        return (Observable<T>) EMPTY;
    }

    @Override // p475rx.functions.Action1
    public void call(Subscriber<? super Object> subscriber) {
        subscriber.onCompleted();
    }
}
