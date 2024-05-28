package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.observers.SerializedSubscriber;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTakeTimed */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTakeTimed<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        TakeSubscriber takeSubscriber = new TakeSubscriber(new SerializedSubscriber(subscriber));
        createWorker.schedule(takeSubscriber, this.time, this.unit);
        return takeSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTakeTimed$TakeSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class TakeSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;

        public TakeSubscriber(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.child = subscriber;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
            unsubscribe();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
            unsubscribe();
        }

        @Override // p475rx.functions.Action0
        public void call() {
            onCompleted();
        }
    }
}
