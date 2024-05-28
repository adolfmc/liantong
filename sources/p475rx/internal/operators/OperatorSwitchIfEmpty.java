package p475rx.internal.operators;

import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.internal.producers.ProducerArbiter;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorSwitchIfEmpty */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorSwitchIfEmpty<T> implements Observable.Operator<T, T> {
    private final Observable<? extends T> alternate;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSwitchIfEmpty(Observable<? extends T> observable) {
        this.alternate = observable;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerialSubscription serialSubscription = new SerialSubscription();
        ProducerArbiter producerArbiter = new ProducerArbiter();
        ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber, serialSubscription, producerArbiter, this.alternate);
        serialSubscription.set(parentSubscriber);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        return parentSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorSwitchIfEmpty$ParentSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Observable<? extends T> alternate;
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;
        private boolean empty = true;
        private final SerialSubscription ssub;

        ParentSubscriber(Subscriber<? super T> subscriber, SerialSubscription serialSubscription, ProducerArbiter producerArbiter, Observable<? extends T> observable) {
            this.child = subscriber;
            this.ssub = serialSubscription;
            this.arbiter = producerArbiter;
            this.alternate = observable;
        }

        @Override // p475rx.Subscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (!this.empty) {
                this.child.onCompleted();
            } else if (this.child.isUnsubscribed()) {
            } else {
                subscribeToAlternate();
            }
        }

        private void subscribeToAlternate() {
            AlternateSubscriber alternateSubscriber = new AlternateSubscriber(this.child, this.arbiter);
            this.ssub.set(alternateSubscriber);
            this.alternate.unsafeSubscribe(alternateSubscriber);
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.empty = false;
            this.child.onNext(t);
            this.arbiter.produced(1L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorSwitchIfEmpty$AlternateSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class AlternateSubscriber<T> extends Subscriber<T> {
        private final ProducerArbiter arbiter;
        private final Subscriber<? super T> child;

        AlternateSubscriber(Subscriber<? super T> subscriber, ProducerArbiter producerArbiter) {
            this.child = subscriber;
            this.arbiter = producerArbiter;
        }

        @Override // p475rx.Subscriber
        public void setProducer(Producer producer) {
            this.arbiter.setProducer(producer);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.child.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.child.onError(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            this.arbiter.produced(1L);
        }
    }
}
