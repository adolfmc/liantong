package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.exceptions.OnErrorThrowable;
import p475rx.functions.Func1;
import p475rx.functions.Func2;
import p475rx.internal.util.RxJavaPluginUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorMapPair */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorMapPair<T, U, R> implements Observable.Operator<Observable<? extends R>, T> {
    final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public static <T, U> Func1<T, Observable<U>> convertSelector(final Func1<? super T, ? extends Iterable<? extends U>> func1) {
        return new Func1<T, Observable<U>>() { // from class: rx.internal.operators.OperatorMapPair.1
            @Override // p475rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object obj) {
                return call((C139321) obj);
            }

            @Override // p475rx.functions.Func1
            public Observable<U> call(T t) {
                return Observable.from((Iterable) Func1.this.call(t));
            }
        };
    }

    public OperatorMapPair(Func1<? super T, ? extends Observable<? extends U>> func1, Func2<? super T, ? super U, ? extends R> func2) {
        this.collectionSelector = func1;
        this.resultSelector = func2;
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<? extends R>> subscriber) {
        MapPairSubscriber mapPairSubscriber = new MapPairSubscriber(subscriber, this.collectionSelector, this.resultSelector);
        subscriber.add(mapPairSubscriber);
        return mapPairSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorMapPair$MapPairSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class MapPairSubscriber<T, U, R> extends Subscriber<T> {
        final Subscriber<? super Observable<? extends R>> actual;
        final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
        boolean done;
        final Func2<? super T, ? super U, ? extends R> resultSelector;

        public MapPairSubscriber(Subscriber<? super Observable<? extends R>> subscriber, Func1<? super T, ? extends Observable<? extends U>> func1, Func2<? super T, ? super U, ? extends R> func2) {
            this.actual = subscriber;
            this.collectionSelector = func1;
            this.resultSelector = func2;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            try {
                this.actual.onNext(this.collectionSelector.call(t).map(new OuterInnerMapper(t, this.resultSelector)));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPluginUtils.handleException(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.actual.onCompleted();
        }

        @Override // p475rx.Subscriber
        public void setProducer(Producer producer) {
            this.actual.setProducer(producer);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorMapPair$OuterInnerMapper */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class OuterInnerMapper<T, U, R> implements Func1<U, R> {
        final T outer;
        final Func2<? super T, ? super U, ? extends R> resultSelector;

        public OuterInnerMapper(T t, Func2<? super T, ? super U, ? extends R> func2) {
            this.outer = t;
            this.resultSelector = func2;
        }

        @Override // p475rx.functions.Func1
        public R call(U u) {
            return this.resultSelector.call((T) this.outer, u);
        }
    }
}
