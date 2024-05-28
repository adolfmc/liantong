package p475rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorDebounceWithTime */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorDebounceWithTime<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDebounceWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        serializedSubscriber.add(createWorker);
        serializedSubscriber.add(serialSubscription);
        return new C139121(subscriber, serialSubscription, createWorker, serializedSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDebounceWithTime$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C139121 extends Subscriber<T> {
        final Subscriber<?> self;
        final DebounceState<T> state;
        final /* synthetic */ SerializedSubscriber val$s;
        final /* synthetic */ SerialSubscription val$ssub;
        final /* synthetic */ Scheduler.Worker val$worker;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C139121(Subscriber subscriber, SerialSubscription serialSubscription, Scheduler.Worker worker, SerializedSubscriber serializedSubscriber) {
            super(subscriber);
            this.val$ssub = serialSubscription;
            this.val$worker = worker;
            this.val$s = serializedSubscriber;
            this.state = new DebounceState<>();
            this.self = this;
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            final int next = this.state.next(t);
            this.val$ssub.set(this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDebounceWithTime.1.1
                @Override // p475rx.functions.Action0
                public void call() {
                    C139121.this.state.emit(next, C139121.this.val$s, C139121.this.self);
                }
            }, OperatorDebounceWithTime.this.timeout, OperatorDebounceWithTime.this.unit));
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.val$s.onError(th);
            unsubscribe();
            this.state.clear();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.state.emitAndComplete(this.val$s, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorDebounceWithTime$DebounceState */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class DebounceState<T> {
        boolean emitting;
        boolean hasValue;
        int index;
        boolean terminate;
        T value;

        public synchronized int next(T t) {
            int i;
            this.value = t;
            this.hasValue = true;
            i = this.index + 1;
            this.index = i;
            return i;
        }

        public void emit(int i, Subscriber<T> subscriber, Subscriber<?> subscriber2) {
            synchronized (this) {
                if (!this.emitting && this.hasValue && i == this.index) {
                    T t = this.value;
                    this.value = null;
                    this.hasValue = false;
                    this.emitting = true;
                    try {
                        subscriber.onNext(t);
                        synchronized (this) {
                            if (!this.terminate) {
                                this.emitting = false;
                            } else {
                                subscriber.onCompleted();
                            }
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber2, t);
                    }
                }
            }
        }

        public void emitAndComplete(Subscriber<T> subscriber, Subscriber<?> subscriber2) {
            synchronized (this) {
                if (this.emitting) {
                    this.terminate = true;
                    return;
                }
                T t = this.value;
                boolean z = this.hasValue;
                this.value = null;
                this.hasValue = false;
                this.emitting = true;
                if (z) {
                    try {
                        subscriber.onNext(t);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber2, t);
                        return;
                    }
                }
                subscriber.onCompleted();
            }
        }

        public synchronized void clear() {
            this.index++;
            this.value = null;
            this.hasValue = false;
        }
    }
}
