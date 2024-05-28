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
/* renamed from: rx.internal.operators.OperatorFilter */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorFilter<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> predicate;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorFilter(Func1<? super T, Boolean> func1) {
        this.predicate = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        FilterSubscriber filterSubscriber = new FilterSubscriber(subscriber, this.predicate);
        subscriber.add(filterSubscriber);
        return filterSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorFilter$FilterSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FilterSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        boolean done;
        final Func1<? super T, Boolean> predicate;

        public FilterSubscriber(Subscriber<? super T> subscriber, Func1<? super T, Boolean> func1) {
            this.actual = subscriber;
            this.predicate = func1;
            request(0L);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            try {
                if (this.predicate.call(t).booleanValue()) {
                    this.actual.onNext(t);
                } else {
                    request(1L);
                }
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
            super.setProducer(producer);
            this.actual.setProducer(producer);
        }
    }
}
