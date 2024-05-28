package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.exceptions.OnErrorThrowable;
import p475rx.functions.Func1;
import p475rx.internal.util.RxJavaPluginUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorMap */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorMap<T, R> implements Observable.Operator<R, T> {
    final Func1<? super T, ? extends R> transformer;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorMap(Func1<? super T, ? extends R> func1) {
        this.transformer = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        MapSubscriber mapSubscriber = new MapSubscriber(subscriber, this.transformer);
        subscriber.add(mapSubscriber);
        return mapSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorMap$MapSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class MapSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        boolean done;
        final Func1<? super T, ? extends R> mapper;

        public MapSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
            this.actual = subscriber;
            this.mapper = func1;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            try {
                this.actual.onNext(this.mapper.call(t));
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
}
