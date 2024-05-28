package p475rx.internal.operators;

import java.util.concurrent.TimeoutException;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.functions.Func3;
import p475rx.functions.Func4;
import p475rx.internal.producers.ProducerArbiter;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.SerialSubscription;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTimeoutBase */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class OperatorTimeoutBase<T> implements Observable.Operator<T, T> {
    final FirstTimeoutStub<T> firstTimeoutStub;
    final Observable<? extends T> other;
    final Scheduler scheduler;
    final TimeoutStub<T> timeoutStub;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTimeoutBase$FirstTimeoutStub */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface FirstTimeoutStub<T> extends Func3<TimeoutSubscriber<T>, Long, Scheduler.Worker, Subscription> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTimeoutBase$TimeoutStub */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface TimeoutStub<T> extends Func4<TimeoutSubscriber<T>, Long, T, Scheduler.Worker, Subscription> {
    }

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OperatorTimeoutBase(FirstTimeoutStub<T> firstTimeoutStub, TimeoutStub<T> timeoutStub, Observable<? extends T> observable, Scheduler scheduler) {
        this.firstTimeoutStub = firstTimeoutStub;
        this.timeoutStub = timeoutStub;
        this.other = observable;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        serializedSubscriber.add(serialSubscription);
        TimeoutSubscriber timeoutSubscriber = new TimeoutSubscriber(serializedSubscriber, this.timeoutStub, serialSubscription, this.other, createWorker);
        serializedSubscriber.add(timeoutSubscriber);
        serializedSubscriber.setProducer(timeoutSubscriber.arbiter);
        serialSubscription.set(this.firstTimeoutStub.call(timeoutSubscriber, 0L, createWorker));
        return timeoutSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTimeoutBase$TimeoutSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class TimeoutSubscriber<T> extends Subscriber<T> {
        long actual;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final Scheduler.Worker inner;
        final Observable<? extends T> other;
        final SerialSubscription serial;
        final SerializedSubscriber<T> serializedSubscriber;
        boolean terminated;
        final TimeoutStub<T> timeoutStub;

        TimeoutSubscriber(SerializedSubscriber<T> serializedSubscriber, TimeoutStub<T> timeoutStub, SerialSubscription serialSubscription, Observable<? extends T> observable, Scheduler.Worker worker) {
            this.serializedSubscriber = serializedSubscriber;
            this.timeoutStub = timeoutStub;
            this.serial = serialSubscription;
            this.other = observable;
            this.inner = worker;
        }

        @Override // p475rx.Subscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            long j;
            boolean z;
            synchronized (this) {
                if (!this.terminated) {
                    j = this.actual + 1;
                    this.actual = j;
                    z = true;
                } else {
                    j = this.actual;
                    z = false;
                }
            }
            if (z) {
                this.serializedSubscriber.onNext(t);
                this.serial.set(this.timeoutStub.call(this, Long.valueOf(j), t, this.inner));
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.terminated) {
                    z = false;
                } else {
                    this.terminated = true;
                }
            }
            if (z) {
                this.serial.unsubscribe();
                this.serializedSubscriber.onError(th);
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.terminated) {
                    z = false;
                } else {
                    this.terminated = true;
                }
            }
            if (z) {
                this.serial.unsubscribe();
                this.serializedSubscriber.onCompleted();
            }
        }

        public void onTimeout(long j) {
            boolean z;
            synchronized (this) {
                z = true;
                if (j != this.actual || this.terminated) {
                    z = false;
                } else {
                    this.terminated = true;
                }
            }
            if (z) {
                if (this.other == null) {
                    this.serializedSubscriber.onError(new TimeoutException());
                    return;
                }
                Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.OperatorTimeoutBase.TimeoutSubscriber.1
                    @Override // p475rx.Observer
                    public void onNext(T t) {
                        TimeoutSubscriber.this.serializedSubscriber.onNext(t);
                    }

                    @Override // p475rx.Observer
                    public void onError(Throwable th) {
                        TimeoutSubscriber.this.serializedSubscriber.onError(th);
                    }

                    @Override // p475rx.Observer
                    public void onCompleted() {
                        TimeoutSubscriber.this.serializedSubscriber.onCompleted();
                    }

                    @Override // p475rx.Subscriber
                    public void setProducer(Producer producer) {
                        TimeoutSubscriber.this.arbiter.setProducer(producer);
                    }
                };
                this.other.unsafeSubscribe(subscriber);
                this.serial.set(subscriber);
            }
        }
    }
}
