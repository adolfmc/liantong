package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.exceptions.OnErrorThrowable;
import p475rx.internal.util.RxJavaPluginUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorCast */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorCast<T, R> implements Observable.Operator<R, T> {
    final Class<R> castClass;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorCast(Class<R> cls) {
        this.castClass = cls;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        CastSubscriber castSubscriber = new CastSubscriber(subscriber, this.castClass);
        subscriber.add(castSubscriber);
        return castSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorCast$CastSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CastSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        final Class<R> castClass;
        boolean done;

        public CastSubscriber(Subscriber<? super R> subscriber, Class<R> cls) {
            this.actual = subscriber;
            this.castClass = cls;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            try {
                this.actual.onNext(this.castClass.cast(t));
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
