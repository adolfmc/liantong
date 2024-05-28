package p475rx.internal.operators;

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
/* renamed from: rx.internal.operators.OperatorToMap */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorToMap<T, K, V> implements Observable.Operator<Map<K, V>, T> {
    final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, V>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorToMap$DefaultToMapFactory */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DefaultToMapFactory<K, V> implements Func0<Map<K, V>> {
        @Override // p475rx.functions.Func0, java.util.concurrent.Callable
        public Map<K, V> call() {
            return new HashMap();
        }
    }

    public OperatorToMap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, new DefaultToMapFactory());
    }

    public OperatorToMap(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, V>> func0) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.mapFactory = func0;
    }

    public Subscriber<? super T> call(final Subscriber<? super Map<K, V>> subscriber) {
        try {
            final Map<K, V> call = this.mapFactory.call();
            return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorToMap.1
                private Map<K, V> map;

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
                        this.map.put(OperatorToMap.this.keySelector.call(t), OperatorToMap.this.valueSelector.call(t));
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                    }
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    this.map = null;
                    subscriber.onError(th);
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    Map<K, V> map = this.map;
                    this.map = null;
                    subscriber.onNext(map);
                    subscriber.onCompleted();
                }
            };
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
            Subscriber<? super T> empty = Subscribers.empty();
            empty.unsubscribe();
            return empty;
        }
    }
}
