package p475rx.observables;

import p475rx.Observable;
import p475rx.Subscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observables.GroupedObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GroupedObservable<K, T> extends Observable<T> {
    private final K key;

    public static <K, T> GroupedObservable<K, T> from(K k, final Observable<T> observable) {
        return new GroupedObservable<>(k, new Observable.OnSubscribe<T>() { // from class: rx.observables.GroupedObservable.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super T> subscriber) {
                Observable.this.unsafeSubscribe(subscriber);
            }
        });
    }

    public static <K, T> GroupedObservable<K, T> create(K k, Observable.OnSubscribe<T> onSubscribe) {
        return new GroupedObservable<>(k, onSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GroupedObservable(K k, Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
        this.key = k;
    }

    public K getKey() {
        return this.key;
    }
}
