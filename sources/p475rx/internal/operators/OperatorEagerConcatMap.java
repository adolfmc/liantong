package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.functions.Func1;
import p475rx.internal.util.atomic.SpscAtomicArrayQueue;
import p475rx.internal.util.unsafe.SpscArrayQueue;
import p475rx.internal.util.unsafe.UnsafeAccess;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorEagerConcatMap */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorEagerConcatMap<T, R> implements Observable.Operator<R, T> {
    final int bufferSize;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    private final int maxConcurrent;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorEagerConcatMap(Func1<? super T, ? extends Observable<? extends R>> func1, int i, int i2) {
        this.mapper = func1;
        this.bufferSize = i;
        this.maxConcurrent = i2;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        EagerOuterSubscriber eagerOuterSubscriber = new EagerOuterSubscriber(this.mapper, this.bufferSize, this.maxConcurrent, subscriber);
        eagerOuterSubscriber.init();
        return eagerOuterSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorEagerConcatMap$EagerOuterProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class EagerOuterProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = -657299606803478389L;
        final EagerOuterSubscriber<?, ?> parent;

        public EagerOuterProducer(EagerOuterSubscriber<?, ?> eagerOuterSubscriber) {
            this.parent = eagerOuterSubscriber;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalStateException("n >= 0 required but it was " + j);
            } else if (i > 0) {
                BackpressureUtils.getAndAddRequest(this, j);
                this.parent.drain();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorEagerConcatMap$EagerOuterSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class EagerOuterSubscriber<T, R> extends Subscriber<T> {
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        Throwable error;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        private EagerOuterProducer sharedProducer;
        final LinkedList<EagerInnerSubscriber<R>> subscribers = new LinkedList<>();
        final AtomicInteger wip = new AtomicInteger();

        public EagerOuterSubscriber(Func1<? super T, ? extends Observable<? extends R>> func1, int i, int i2, Subscriber<? super R> subscriber) {
            this.mapper = func1;
            this.bufferSize = i;
            this.actual = subscriber;
            request(i2 == Integer.MAX_VALUE ? Long.MAX_VALUE : i2);
        }

        void init() {
            this.sharedProducer = new EagerOuterProducer(this);
            add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorEagerConcatMap.EagerOuterSubscriber.1
                @Override // p475rx.functions.Action0
                public void call() {
                    EagerOuterSubscriber eagerOuterSubscriber = EagerOuterSubscriber.this;
                    eagerOuterSubscriber.cancelled = true;
                    if (eagerOuterSubscriber.wip.getAndIncrement() == 0) {
                        EagerOuterSubscriber.this.cleanup();
                    }
                }
            }));
            this.actual.add(this);
            this.actual.setProducer(this.sharedProducer);
        }

        void cleanup() {
            ArrayList<Subscription> arrayList;
            synchronized (this.subscribers) {
                arrayList = new ArrayList(this.subscribers);
                this.subscribers.clear();
            }
            for (Subscription subscription : arrayList) {
                subscription.unsubscribe();
            }
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            try {
                Observable<? extends R> call = this.mapper.call(t);
                EagerInnerSubscriber<R> eagerInnerSubscriber = new EagerInnerSubscriber<>(this, this.bufferSize);
                if (this.cancelled) {
                    return;
                }
                synchronized (this.subscribers) {
                    if (this.cancelled) {
                        return;
                    }
                    this.subscribers.add(eagerInnerSubscriber);
                    if (this.cancelled) {
                        return;
                    }
                    call.unsafeSubscribe(eagerInnerSubscriber);
                    drain();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.actual, t);
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void drain() {
            EagerInnerSubscriber<R> peek;
            long j;
            boolean z;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            EagerOuterProducer eagerOuterProducer = this.sharedProducer;
            Subscriber<? super R> subscriber = this.actual;
            NotificationLite instance = NotificationLite.instance();
            int i = 1;
            while (!this.cancelled) {
                boolean z2 = this.done;
                synchronized (this.subscribers) {
                    peek = this.subscribers.peek();
                }
                boolean z3 = peek == null;
                if (z2) {
                    Throwable th = this.error;
                    if (th != null) {
                        cleanup();
                        subscriber.onError(th);
                        return;
                    } else if (z3) {
                        subscriber.onCompleted();
                        return;
                    }
                }
                if (!z3) {
                    long j2 = eagerOuterProducer.get();
                    boolean z4 = j2 == Long.MAX_VALUE;
                    Queue<Object> queue = peek.queue;
                    long j3 = 0;
                    while (true) {
                        boolean z5 = peek.done;
                        Object peek2 = queue.peek();
                        boolean z6 = peek2 == null;
                        if (z5) {
                            Throwable th2 = peek.error;
                            if (th2 == null) {
                                if (z6) {
                                    synchronized (this.subscribers) {
                                        this.subscribers.poll();
                                    }
                                    peek.unsubscribe();
                                    request(1L);
                                    z = true;
                                    j = 0;
                                    break;
                                }
                            } else {
                                cleanup();
                                subscriber.onError(th2);
                                return;
                            }
                        }
                        if (z6) {
                            j = 0;
                            break;
                        }
                        j = 0;
                        if (j2 == 0) {
                            break;
                        }
                        queue.poll();
                        try {
                            subscriber.onNext((Object) instance.getValue(peek2));
                            j2--;
                            j3--;
                        } catch (Throwable th3) {
                            Exceptions.throwOrReport(th3, subscriber, peek2);
                            return;
                        }
                    }
                    z = false;
                    if (j3 != j) {
                        if (!z4) {
                            eagerOuterProducer.addAndGet(j3);
                        }
                        if (!z) {
                            peek.requestMore(-j3);
                        }
                    }
                    if (z) {
                        continue;
                    }
                }
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            cleanup();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorEagerConcatMap$EagerInnerSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class EagerInnerSubscriber<T> extends Subscriber<T> {
        volatile boolean done;
        Throwable error;

        /* renamed from: nl */
        final NotificationLite<T> f27592nl;
        final EagerOuterSubscriber<?, T> parent;
        final Queue<Object> queue;

        public EagerInnerSubscriber(EagerOuterSubscriber<?, T> eagerOuterSubscriber, int i) {
            Queue<Object> spscAtomicArrayQueue;
            this.parent = eagerOuterSubscriber;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscAtomicArrayQueue = new SpscArrayQueue<>(i);
            } else {
                spscAtomicArrayQueue = new SpscAtomicArrayQueue<>(i);
            }
            this.queue = spscAtomicArrayQueue;
            this.f27592nl = NotificationLite.instance();
            request(i);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.queue.offer(this.f27592nl.next(t));
            this.parent.drain();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            this.parent.drain();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long j) {
            request(j);
        }
    }
}
