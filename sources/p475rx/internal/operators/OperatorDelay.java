package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDelay */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDelay<T> implements Observable.Operator<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDelay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        return new C139141(subscriber, createWorker, subscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDelay$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C139141 extends Subscriber<T> {
        boolean done;
        final /* synthetic */ Subscriber val$child;
        final /* synthetic */ Scheduler.Worker val$worker;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C139141(Subscriber subscriber, Scheduler.Worker worker, Subscriber subscriber2) {
            super(subscriber);
            this.val$worker = worker;
            this.val$child = subscriber2;
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.1
                @Override // p475rx.functions.Action0
                public void call() {
                    if (C139141.this.done) {
                        return;
                    }
                    C139141 c139141 = C139141.this;
                    c139141.done = true;
                    c139141.val$child.onCompleted();
                }
            }, OperatorDelay.this.delay, OperatorDelay.this.unit);
        }

        @Override // p475rx.Observer
        public void onError(final Throwable th) {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.2
                @Override // p475rx.functions.Action0
                public void call() {
                    if (C139141.this.done) {
                        return;
                    }
                    C139141 c139141 = C139141.this;
                    c139141.done = true;
                    c139141.val$child.onError(th);
                    C139141.this.val$worker.unsubscribe();
                }
            });
        }

        @Override // p475rx.Observer
        public void onNext(final T t) {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // p475rx.functions.Action0
                public void call() {
                    if (C139141.this.done) {
                        return;
                    }
                    C139141.this.val$child.onNext(t);
                }
            }, OperatorDelay.this.delay, OperatorDelay.this.unit);
        }
    }
}
