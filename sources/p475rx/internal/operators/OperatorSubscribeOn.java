package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSubscribeOn */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<T> source;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSubscribeOn(Observable<T> observable, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = observable;
    }

    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        createWorker.schedule(new C139781(subscriber, createWorker));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorSubscribeOn$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C139781 implements Action0 {
        final /* synthetic */ Scheduler.Worker val$inner;
        final /* synthetic */ Subscriber val$subscriber;

        C139781(Subscriber subscriber, Scheduler.Worker worker) {
            this.val$subscriber = subscriber;
            this.val$inner = worker;
        }

        @Override // p475rx.functions.Action0
        public void call() {
            final Thread currentThread = Thread.currentThread();
            OperatorSubscribeOn.this.source.unsafeSubscribe(new Subscriber<T>(this.val$subscriber) { // from class: rx.internal.operators.OperatorSubscribeOn.1.1
                @Override // p475rx.Observer
                public void onNext(T t) {
                    C139781.this.val$subscriber.onNext(t);
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    try {
                        C139781.this.val$subscriber.onError(th);
                    } finally {
                        C139781.this.val$inner.unsubscribe();
                    }
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    try {
                        C139781.this.val$subscriber.onCompleted();
                    } finally {
                        C139781.this.val$inner.unsubscribe();
                    }
                }

                @Override // p475rx.Subscriber
                public void setProducer(final Producer producer) {
                    C139781.this.val$subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSubscribeOn.1.1.1
                        @Override // p475rx.Producer
                        public void request(final long j) {
                            if (currentThread == Thread.currentThread()) {
                                producer.request(j);
                            } else {
                                C139781.this.val$inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorSubscribeOn.1.1.1.1
                                    @Override // p475rx.functions.Action0
                                    public void call() {
                                        producer.request(j);
                                    }
                                });
                            }
                        }
                    });
                }
            });
        }
    }
}
