package p475rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.Subscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorOnBackpressureLatest */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorOnBackpressureLatest<T> implements Observable.Operator<T, T> {
    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureLatest$Holder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Holder {
        static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest<>();

        Holder() {
        }
    }

    public static <T> OperatorOnBackpressureLatest<T> instance() {
        return (OperatorOnBackpressureLatest<T>) Holder.INSTANCE;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        LatestEmitter latestEmitter = new LatestEmitter(subscriber);
        LatestSubscriber<? super T> latestSubscriber = new LatestSubscriber<>(latestEmitter);
        latestEmitter.parent = latestSubscriber;
        subscriber.add(latestSubscriber);
        subscriber.add(latestEmitter);
        subscriber.setProducer(latestEmitter);
        return latestSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureLatest$LatestEmitter */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class LatestEmitter<T> extends AtomicLong implements Observer<T>, Producer, Subscription {
        static final Object EMPTY = new Object();
        static final long NOT_REQUESTED = -4611686018427387904L;
        private static final long serialVersionUID = -1364393685005146274L;
        final Subscriber<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        LatestSubscriber<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value = new AtomicReference<>(EMPTY);

        public LatestEmitter(Subscriber<? super T> subscriber) {
            this.child = subscriber;
            lazySet(-4611686018427387904L);
        }

        @Override // p475rx.Producer
        public void request(long j) {
            long j2;
            int i;
            long j3;
            if (j >= 0) {
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    i = (j2 > (-4611686018427387904L) ? 1 : (j2 == (-4611686018427387904L) ? 0 : -1));
                    if (i == 0) {
                        j3 = j;
                    } else {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = Long.MAX_VALUE;
                        }
                    }
                } while (!compareAndSet(j2, j3));
                if (i == 0) {
                    this.parent.requestMore(Long.MAX_VALUE);
                }
                emit();
            }
        }

        long produced(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 < 0) {
                    return j2;
                }
                j3 = j2 - j;
            } while (!compareAndSet(j2, j3));
            return j3;
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.value.lazySet(t);
            emit();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.terminal = th;
            this.done = true;
            emit();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        void emit() {
            boolean z;
            synchronized (this) {
                boolean z2 = true;
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                this.missed = false;
                while (true) {
                    try {
                        long j = get();
                        if (j == Long.MIN_VALUE) {
                            return;
                        }
                        Object obj = this.value.get();
                        if (j > 0 && obj != EMPTY) {
                            this.child.onNext(obj);
                            this.value.compareAndSet(obj, EMPTY);
                            produced(1L);
                            obj = EMPTY;
                        }
                        if (obj == EMPTY && this.done) {
                            Throwable th = this.terminal;
                            if (th != null) {
                                this.child.onError(th);
                            } else {
                                this.child.onCompleted();
                            }
                        }
                        try {
                            synchronized (this) {
                                try {
                                    if (!this.missed) {
                                        this.emitting = false;
                                        return;
                                    }
                                    this.missed = false;
                                } catch (Throwable th2) {
                                    th = th2;
                                    z2 = false;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            z = z2;
                            th = th4;
                            if (!z) {
                                synchronized (this) {
                                    this.emitting = false;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        z = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorOnBackpressureLatest$LatestSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class LatestSubscriber<T> extends Subscriber<T> {
        private final LatestEmitter<T> producer;

        LatestSubscriber(LatestEmitter<T> latestEmitter) {
            this.producer = latestEmitter;
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(0L);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.producer.onNext(t);
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.producer.onError(th);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.producer.onCompleted();
        }

        void requestMore(long j) {
            request(j);
        }
    }
}
