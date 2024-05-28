package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorToMultimap */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorToMultimap<T, K, V> implements Observable.Operator<Map<K, Collection<V>>, T> {
    final Func1<? super K, ? extends Collection<V>> collectionFactory;
    final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, Collection<V>>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorToMultimap$DefaultToMultimapFactory */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DefaultToMultimapFactory<K, V> implements Func0<Map<K, Collection<V>>> {
        @Override // p475rx.functions.Func0, java.util.concurrent.Callable
        public Map<K, Collection<V>> call() {
            return new HashMap();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorToMultimap$DefaultMultimapCollectionFactory */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DefaultMultimapCollectionFactory<K, V> implements Func1<K, Collection<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // p475rx.functions.Func1
        public /* bridge */ /* synthetic */ Object call(Object obj) {
            return call((DefaultMultimapCollectionFactory<K, V>) obj);
        }

        @Override // p475rx.functions.Func1
        public Collection<V> call(K k) {
            return new ArrayList();
        }
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, new DefaultToMultimapFactory(), new DefaultMultimapCollectionFactory());
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0) {
        this(func1, func12, func0, new DefaultMultimapCollectionFactory());
    }

    public OperatorToMultimap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0, Func1<? super K, ? extends Collection<V>> func13) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.mapFactory = func0;
        this.collectionFactory = func13;
    }

    public Subscriber<? super T> call(final Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            final Map<K, Collection<V>> call = this.mapFactory.call();
            return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorToMultimap.1
                private Map<K, Collection<V>> map;

                {
                    this.map = call;
                }

                @Override // p475rx.Subscriber
                public void onStart() {
                    request(Long.MAX_VALUE);
                }

                @Override // p475rx.Observer
                public void onNext(T t) {
                    try {
                        K call2 = OperatorToMultimap.this.keySelector.call(t);
                        V call3 = OperatorToMultimap.this.valueSelector.call(t);
                        Collection<V> collection = this.map.get(call2);
                        if (collection == null) {
                            try {
                                collection = OperatorToMultimap.this.collectionFactory.call(call2);
                                this.map.put(call2, collection);
                            } catch (Throwable th) {
                                Exceptions.throwOrReport(th, subscriber);
                                return;
                            }
                        }
                        collection.add(call3);
                    } catch (Throwable th2) {
                        Exceptions.throwOrReport(th2, subscriber);
                    }
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    this.map = null;
                    subscriber.onError(th);
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    Map<K, Collection<V>> map = this.map;
                    this.map = null;
                    subscriber.onNext(map);
                    subscriber.onCompleted();
                }
            };
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
            Subscriber<? super T> empty = Subscribers.empty();
            empty.unsubscribe();
            return empty;
        }
    }
}
