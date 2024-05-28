package p475rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Func0;
import p475rx.functions.Func2;
import p475rx.internal.util.atomic.SpscLinkedAtomicQueue;
import p475rx.internal.util.unsafe.SpscLinkedQueue;
import p475rx.internal.util.unsafe.UnsafeAccess;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorScan */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorScan<R, T> implements Observable.Operator<R, T> {
    private static final Object NO_INITIAL_VALUE = new Object();
    final Func2<R, ? super T, R> accumulator;
    private final Func0<R> initialValueFactory;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorScan(final R r, Func2<R, ? super T, R> func2) {
        this((Func0) new Func0<R>() { // from class: rx.internal.operators.OperatorScan.1
            @Override // p475rx.functions.Func0, java.util.concurrent.Callable
            public R call() {
                return (R) r;
            }
        }, (Func2) func2);
    }

    public OperatorScan(Func0<R> func0, Func2<R, ? super T, R> func2) {
        this.initialValueFactory = func0;
        this.accumulator = func2;
    }

    public OperatorScan(Func2<R, ? super T, R> func2) {
        this(NO_INITIAL_VALUE, func2);
    }

    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final R call = this.initialValueFactory.call();
        if (call == NO_INITIAL_VALUE) {
            return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorScan.2
                boolean once;
                R value;

                @Override // p475rx.Observer
                public void onNext(T t) {
                    R r;
                    if (!this.once) {
                        this.once = true;
                        r = t;
                    } else {
                        try {
                            r = OperatorScan.this.accumulator.call(this.value, t);
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber, t);
                            return;
                        }
                    }
                    this.value = r;
                    subscriber.onNext(r);
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    subscriber.onCompleted();
                }
            };
        }
        final InitialProducer initialProducer = new InitialProducer(call, subscriber);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorScan.3
            private R value;

            {
                this.value = (R) call;
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                try {
                    R call2 = OperatorScan.this.accumulator.call(this.value, t);
                    this.value = call2;
                    initialProducer.onNext(call2);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                initialProducer.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                initialProducer.onCompleted();
            }

            @Override // p475rx.Subscriber
            public void setProducer(Producer producer) {
                initialProducer.setProducer(producer);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(initialProducer);
        return subscriber2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorScan$InitialProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class InitialProducer<R> implements Observer<R>, Producer {
        final Subscriber<? super R> child;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        long missedRequested;
        volatile Producer producer;
        final Queue<Object> queue;
        final AtomicLong requested;

        public InitialProducer(R r, Subscriber<? super R> subscriber) {
            Queue<Object> spscLinkedAtomicQueue;
            this.child = subscriber;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscLinkedAtomicQueue = new SpscLinkedQueue<>();
            } else {
                spscLinkedAtomicQueue = new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedAtomicQueue;
            spscLinkedAtomicQueue.offer(NotificationLite.instance().next(r));
            this.requested = new AtomicLong();
        }

        @Override // p475rx.Observer
        public void onNext(R r) {
            this.queue.offer(NotificationLite.instance().next(r));
            emit();
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super R> subscriber) {
            if (subscriber.isUnsubscribed()) {
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    subscriber.onError(th);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            emit();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // p475rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (i != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                Producer producer = this.producer;
                if (producer == null) {
                    synchronized (this.requested) {
                        producer = this.producer;
                        if (producer == null) {
                            this.missedRequested = BackpressureUtils.addCap(this.missedRequested, j);
                        }
                    }
                }
                if (producer != null) {
                    producer.request(j);
                }
                emit();
            }
        }

        public void setProducer(Producer producer) {
            long j;
            if (producer == null) {
                throw new NullPointerException();
            }
            synchronized (this.requested) {
                if (this.producer != null) {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
                j = this.missedRequested;
                if (j != Long.MAX_VALUE) {
                    j--;
                }
                this.missedRequested = 0L;
                this.producer = producer;
            }
            if (j > 0) {
                producer.request(j);
            }
            emit();
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        void emitLoop() {
            Subscriber<? super R> subscriber = this.child;
            Queue<Object> queue = this.queue;
            NotificationLite instance = NotificationLite.instance();
            AtomicLong atomicLong = this.requested;
            long j = atomicLong.get();
            while (true) {
                boolean z = j == Long.MAX_VALUE;
                if (checkTerminated(this.done, queue.isEmpty(), subscriber)) {
                    return;
                }
                long j2 = 0;
                while (j != 0) {
                    boolean z2 = this.done;
                    Object poll = queue.poll();
                    boolean z3 = poll == null;
                    if (checkTerminated(z2, z3, subscriber)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    Object obj = (Object) instance.getValue(poll);
                    try {
                        subscriber.onNext(obj);
                        j--;
                        j2--;
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, obj);
                        return;
                    }
                }
                if (j2 != 0 && !z) {
                    j = atomicLong.addAndGet(j2);
                }
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
                        return;
                    }
                    this.missed = false;
                }
            }
        }
    }
}
