package p475rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.functions.Func1;
import p475rx.internal.producers.SingleProducer;
import p475rx.internal.schedulers.EventLoopsScheduler;
import p475rx.observers.Subscribers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.ScalarSynchronousObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class ScalarSynchronousObservable<T> extends Observable<T> {
    static final boolean STRONG_MODE = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();

    /* renamed from: t */
    final T f27625t;

    static <T> Producer createProducer(Subscriber<? super T> subscriber, T t) {
        if (STRONG_MODE) {
            return new SingleProducer(subscriber, t);
        }
        return new WeakSingleProducer(subscriber, t);
    }

    public static <T> ScalarSynchronousObservable<T> create(T t) {
        return new ScalarSynchronousObservable<>(t);
    }

    protected ScalarSynchronousObservable(final T t) {
        super(new Observable.OnSubscribe<T>() { // from class: rx.internal.util.ScalarSynchronousObservable.1
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super T> subscriber) {
                subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, t));
            }
        });
        this.f27625t = t;
    }

    public T get() {
        return this.f27625t;
    }

    public Observable<T> scalarScheduleOn(final Scheduler scheduler) {
        Func1<Action0, Subscription> func1;
        if (scheduler instanceof EventLoopsScheduler) {
            final EventLoopsScheduler eventLoopsScheduler = (EventLoopsScheduler) scheduler;
            func1 = new Func1<Action0, Subscription>() { // from class: rx.internal.util.ScalarSynchronousObservable.2
                @Override // p475rx.functions.Func1
                public Subscription call(Action0 action0) {
                    return eventLoopsScheduler.scheduleDirect(action0);
                }
            };
        } else {
            func1 = new Func1<Action0, Subscription>() { // from class: rx.internal.util.ScalarSynchronousObservable.3
                @Override // p475rx.functions.Func1
                public Subscription call(final Action0 action0) {
                    final Scheduler.Worker createWorker = scheduler.createWorker();
                    createWorker.schedule(new Action0() { // from class: rx.internal.util.ScalarSynchronousObservable.3.1
                        @Override // p475rx.functions.Action0
                        public void call() {
                            try {
                                action0.call();
                            } finally {
                                createWorker.unsubscribe();
                            }
                        }
                    });
                    return createWorker;
                }
            };
        }
        return create((Observable.OnSubscribe) new ScalarAsyncOnSubscribe(this.f27625t, func1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.ScalarSynchronousObservable$ScalarAsyncOnSubscribe */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ScalarAsyncOnSubscribe<T> implements Observable.OnSubscribe<T> {
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        ScalarAsyncOnSubscribe(T t, Func1<Action0, Subscription> func1) {
            this.value = t;
            this.onSchedule = func1;
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(new ScalarAsyncProducer(subscriber, this.value, this.onSchedule));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.ScalarSynchronousObservable$ScalarAsyncProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0 {
        private static final long serialVersionUID = -2466317989629281651L;
        final Subscriber<? super T> actual;
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        public ScalarAsyncProducer(Subscriber<? super T> subscriber, T t, Func1<Action0, Subscription> func1) {
            this.actual = subscriber;
            this.value = t;
            this.onSchedule = func1;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (i == 0 || !compareAndSet(false, true)) {
            } else {
                this.actual.add(this.onSchedule.call(this));
            }
        }

        @Override // p475rx.functions.Action0
        public void call() {
            Subscriber<? super T> subscriber = this.actual;
            if (subscriber.isUnsubscribed()) {
                return;
            }
            Object obj = (T) this.value;
            try {
                subscriber.onNext(obj);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, obj);
            }
        }

        @Override // java.util.concurrent.atomic.AtomicBoolean
        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    public <R> Observable<R> scalarFlatMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return create((Observable.OnSubscribe) new Observable.OnSubscribe<R>() { // from class: rx.internal.util.ScalarSynchronousObservable.4
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super R> subscriber) {
                Observable observable = (Observable) func1.call(ScalarSynchronousObservable.this.f27625t);
                if (observable instanceof ScalarSynchronousObservable) {
                    subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, ((ScalarSynchronousObservable) observable).f27625t));
                } else {
                    observable.unsafeSubscribe(Subscribers.wrap(subscriber));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.ScalarSynchronousObservable$WeakSingleProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class WeakSingleProducer<T> implements Producer {
        final Subscriber<? super T> actual;
        boolean once;
        final T value;

        public WeakSingleProducer(Subscriber<? super T> subscriber, T t) {
            this.actual = subscriber;
            this.value = t;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            if (this.once) {
                return;
            }
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalStateException("n >= required but it was " + j);
            } else if (i == 0) {
            } else {
                this.once = true;
                Subscriber<? super T> subscriber = this.actual;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Object obj = (T) this.value;
                try {
                    subscriber.onNext(obj);
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, obj);
                }
            }
        }
    }
}
