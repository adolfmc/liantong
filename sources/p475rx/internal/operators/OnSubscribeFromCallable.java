package p475rx.internal.operators;

import java.util.concurrent.Callable;
import p475rx.Observable;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.internal.producers.SingleDelayedProducer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeFromCallable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeFromCallable<T> implements Observable.OnSubscribe<T> {
    private final Callable<? extends T> resultFactory;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeFromCallable(Callable<? extends T> callable) {
        this.resultFactory = callable;
    }

    public void call(Subscriber<? super T> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        subscriber.setProducer(singleDelayedProducer);
        try {
            singleDelayedProducer.setValue(this.resultFactory.call());
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
