package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.functions.Func2;
import p475rx.internal.producers.ProducerArbiter;
import p475rx.schedulers.Schedulers;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorRetryWithPredicate */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorRetryWithPredicate<T> implements Observable.Operator<T, Observable<T>> {
    final Func2<Integer, Throwable, Boolean> predicate;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorRetryWithPredicate(Func2<Integer, Throwable, Boolean> func2) {
        this.predicate = func2;
    }

    public Subscriber<? super Observable<T>> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = Schedulers.trampoline().createWorker();
        subscriber.add(createWorker);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        ProducerArbiter producerArbiter = new ProducerArbiter();
        subscriber.setProducer(producerArbiter);
        return new SourceSubscriber(subscriber, this.predicate, createWorker, serialSubscription, producerArbiter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorRetryWithPredicate$SourceSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SourceSubscriber<T> extends Subscriber<Observable<T>> {
        final AtomicInteger attempts = new AtomicInteger();
        final Subscriber<? super T> child;
        final Scheduler.Worker inner;

        /* renamed from: pa */
        final ProducerArbiter f27602pa;
        final Func2<Integer, Throwable, Boolean> predicate;
        final SerialSubscription serialSubscription;

        @Override // p475rx.Observer
        public void onCompleted() {
        }

        @Override // p475rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        public SourceSubscriber(Subscriber<? super T> subscriber, Func2<Integer, Throwable, Boolean> func2, Scheduler.Worker worker, SerialSubscription serialSubscription, ProducerArbiter producerArbiter) {
            this.child = subscriber;
            this.predicate = func2;
            this.inner = worker;
            this.serialSubscription = serialSubscription;
            this.f27602pa = producerArbiter;
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        public void onNext(final Observable<T> observable) {
            this.inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorRetryWithPredicate.SourceSubscriber.1
                @Override // p475rx.functions.Action0
                public void call() {
                    SourceSubscriber.this.attempts.incrementAndGet();
                    Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.OperatorRetryWithPredicate.SourceSubscriber.1.1
                        boolean done;

                        @Override // p475rx.Observer
                        public void onCompleted() {
                            if (this.done) {
                                return;
                            }
                            this.done = true;
                            SourceSubscriber.this.child.onCompleted();
                        }

                        @Override // p475rx.Observer
                        public void onError(Throwable th) {
                            if (this.done) {
                                return;
                            }
                            this.done = true;
                            if (SourceSubscriber.this.predicate.call(Integer.valueOf(SourceSubscriber.this.attempts.get()), th).booleanValue() && !SourceSubscriber.this.inner.isUnsubscribed()) {
                                SourceSubscriber.this.inner.schedule(this);
                            } else {
                                SourceSubscriber.this.child.onError(th);
                            }
                        }

                        @Override // p475rx.Observer
                        public void onNext(T t) {
                            if (this.done) {
                                return;
                            }
                            SourceSubscriber.this.child.onNext(t);
                            SourceSubscriber.this.f27602pa.produced(1L);
                        }

                        @Override // p475rx.Subscriber
                        public void setProducer(Producer producer) {
                            SourceSubscriber.this.f27602pa.setProducer(producer);
                        }
                    };
                    SourceSubscriber.this.serialSubscription.set(subscriber);
                    observable.unsafeSubscribe(subscriber);
                }
            });
        }
    }
}
