package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.exceptions.Exceptions;
import p475rx.exceptions.OnErrorThrowable;
import p475rx.functions.Action0;
import p475rx.functions.Action1;
import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.observables.ConnectableObservable;
import p475rx.schedulers.Timestamped;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorReplay */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorReplay<T> extends ConnectableObservable<T> {
    static final Func0 DEFAULT_UNBOUNDED_FACTORY = new Func0() { // from class: rx.internal.operators.OperatorReplay.1
        @Override // p475rx.functions.Func0, java.util.concurrent.Callable
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    };
    final Func0<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Observable<? extends T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$ReplayBuffer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    public static <T, U, R> Observable<R> multicastSelector(final Func0<? extends ConnectableObservable<U>> func0, final Func1<? super Observable<U>, ? extends Observable<R>> func1) {
        return Observable.create(new Observable.OnSubscribe<R>() { // from class: rx.internal.operators.OperatorReplay.2
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(final Subscriber<? super R> subscriber) {
                try {
                    ConnectableObservable connectableObservable = (ConnectableObservable) Func0.this.call();
                    ((Observable) func1.call(connectableObservable)).subscribe((Subscriber) subscriber);
                    connectableObservable.connect(new Action1<Subscription>() { // from class: rx.internal.operators.OperatorReplay.2.1
                        @Override // p475rx.functions.Action1
                        public void call(Subscription subscription) {
                            subscriber.add(subscription);
                        }
                    });
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber);
                }
            }
        });
    }

    public static <T> ConnectableObservable<T> observeOn(final ConnectableObservable<T> connectableObservable, Scheduler scheduler) {
        final Observable<T> observeOn = connectableObservable.observeOn(scheduler);
        return new ConnectableObservable<T>(new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.3
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(final Subscriber<? super T> subscriber) {
                Observable.this.unsafeSubscribe(new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorReplay.3.1
                    @Override // p475rx.Observer
                    public void onNext(T t) {
                        subscriber.onNext(t);
                    }

                    @Override // p475rx.Observer
                    public void onError(Throwable th) {
                        subscriber.onError(th);
                    }

                    @Override // p475rx.Observer
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }
                });
            }
        }) { // from class: rx.internal.operators.OperatorReplay.4
            @Override // p475rx.observables.ConnectableObservable
            public void connect(Action1<? super Subscription> action1) {
                connectableObservable.connect(action1);
            }
        };
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable) {
        return create(observable, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, final int i) {
        if (i == Integer.MAX_VALUE) {
            return create(observable);
        }
        return create(observable, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.5
            @Override // p475rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeBoundReplayBuffer(i);
            }
        });
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        return create(observable, j, timeUnit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, long j, TimeUnit timeUnit, final Scheduler scheduler, final int i) {
        final long millis = timeUnit.toMillis(j);
        return create(observable, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.6
            @Override // p475rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeAndTimeBoundReplayBuffer(i, millis, scheduler);
            }
        });
    }

    static <T> ConnectableObservable<T> create(Observable<? extends T> observable, final Func0<? extends ReplayBuffer<T>> func0) {
        final AtomicReference atomicReference = new AtomicReference();
        return new OperatorReplay(new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.7
            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super T> subscriber) {
                ReplaySubscriber replaySubscriber;
                while (true) {
                    replaySubscriber = (ReplaySubscriber) atomicReference.get();
                    if (replaySubscriber != null) {
                        break;
                    }
                    ReplaySubscriber replaySubscriber2 = new ReplaySubscriber(atomicReference, (ReplayBuffer) func0.call());
                    replaySubscriber2.init();
                    if (atomicReference.compareAndSet(replaySubscriber, replaySubscriber2)) {
                        replaySubscriber = replaySubscriber2;
                        break;
                    }
                }
                InnerProducer<T> innerProducer = new InnerProducer<>(replaySubscriber, subscriber);
                replaySubscriber.add((InnerProducer) innerProducer);
                subscriber.add(innerProducer);
                replaySubscriber.buffer.replay(innerProducer);
                subscriber.setProducer(innerProducer);
            }
        }, observable, atomicReference, func0);
    }

    private OperatorReplay(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> observable, AtomicReference<ReplaySubscriber<T>> atomicReference, Func0<? extends ReplayBuffer<T>> func0) {
        super(onSubscribe);
        this.source = observable;
        this.current = atomicReference;
        this.bufferFactory = func0;
    }

    @Override // p475rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> action1) {
        ReplaySubscriber<T> replaySubscriber;
        while (true) {
            replaySubscriber = this.current.get();
            if (replaySubscriber != null && !replaySubscriber.isUnsubscribed()) {
                break;
            }
            ReplaySubscriber<T> replaySubscriber2 = new ReplaySubscriber<>(this.current, this.bufferFactory.call());
            replaySubscriber2.init();
            if (this.current.compareAndSet(replaySubscriber, replaySubscriber2)) {
                replaySubscriber = replaySubscriber2;
                break;
            }
        }
        boolean z = true;
        if (replaySubscriber.shouldConnect.get() || !replaySubscriber.shouldConnect.compareAndSet(false, true)) {
            z = false;
        }
        action1.call(replaySubscriber);
        if (z) {
            this.source.unsafeSubscribe(replaySubscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$ReplaySubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ReplaySubscriber<T> extends Subscriber<T> implements Subscription {
        static final InnerProducer[] EMPTY = new InnerProducer[0];
        static final InnerProducer[] TERMINATED = new InnerProducer[0];
        final ReplayBuffer<T> buffer;
        boolean done;
        boolean emitting;
        long maxChildRequested;
        long maxUpstreamRequested;
        boolean missed;
        volatile Producer producer;

        /* renamed from: nl */
        final NotificationLite<T> f27600nl = NotificationLite.instance();
        final AtomicReference<InnerProducer[]> producers = new AtomicReference<>(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        public ReplaySubscriber(AtomicReference<ReplaySubscriber<T>> atomicReference, ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
            request(0L);
        }

        void init() {
            add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorReplay.ReplaySubscriber.1
                @Override // p475rx.functions.Action0
                public void call() {
                    ReplaySubscriber.this.producers.getAndSet(ReplaySubscriber.TERMINATED);
                }
            }));
        }

        boolean add(InnerProducer<T> innerProducer) {
            InnerProducer[] innerProducerArr;
            InnerProducer[] innerProducerArr2;
            if (innerProducer == null) {
                throw new NullPointerException();
            }
            do {
                innerProducerArr = this.producers.get();
                if (innerProducerArr == TERMINATED) {
                    return false;
                }
                int length = innerProducerArr.length;
                innerProducerArr2 = new InnerProducer[length + 1];
                System.arraycopy(innerProducerArr, 0, innerProducerArr2, 0, length);
                innerProducerArr2[length] = innerProducer;
            } while (!this.producers.compareAndSet(innerProducerArr, innerProducerArr2));
            return true;
        }

        void remove(InnerProducer<T> innerProducer) {
            InnerProducer[] innerProducerArr;
            InnerProducer[] innerProducerArr2;
            do {
                innerProducerArr = this.producers.get();
                if (innerProducerArr == EMPTY || innerProducerArr == TERMINATED) {
                    return;
                }
                int i = -1;
                int length = innerProducerArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (innerProducerArr[i2].equals(innerProducer)) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    innerProducerArr2 = EMPTY;
                } else {
                    InnerProducer[] innerProducerArr3 = new InnerProducer[length - 1];
                    System.arraycopy(innerProducerArr, 0, innerProducerArr3, 0, i);
                    System.arraycopy(innerProducerArr, i + 1, innerProducerArr3, i, (length - i) - 1);
                    innerProducerArr2 = innerProducerArr3;
                }
            } while (!this.producers.compareAndSet(innerProducerArr, innerProducerArr2));
        }

        @Override // p475rx.Subscriber
        public void setProducer(Producer producer) {
            if (this.producer != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.producer = producer;
            manageRequests();
            replay();
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.buffer.next(t);
            replay();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            this.done = true;
            try {
                this.buffer.error(th);
                replay();
            } finally {
                unsubscribe();
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            try {
                this.buffer.complete();
                replay();
            } finally {
                unsubscribe();
            }
        }

        void manageRequests() {
            if (isUnsubscribed()) {
                return;
            }
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                while (!isUnsubscribed()) {
                    InnerProducer[] innerProducerArr = this.producers.get();
                    long j = this.maxChildRequested;
                    long j2 = j;
                    for (InnerProducer innerProducer : innerProducerArr) {
                        j2 = Math.max(j2, innerProducer.totalRequested.get());
                    }
                    long j3 = this.maxUpstreamRequested;
                    Producer producer = this.producer;
                    long j4 = j2 - j;
                    if (j4 != 0) {
                        this.maxChildRequested = j2;
                        if (producer == null) {
                            long j5 = j3 + j4;
                            if (j5 < 0) {
                                j5 = Long.MAX_VALUE;
                            }
                            this.maxUpstreamRequested = j5;
                        } else if (j3 != 0) {
                            this.maxUpstreamRequested = 0L;
                            producer.request(j3 + j4);
                        } else {
                            producer.request(j4);
                        }
                    } else if (j3 != 0 && producer != null) {
                        this.maxUpstreamRequested = 0L;
                        producer.request(j3);
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

        void replay() {
            for (InnerProducer<T> innerProducer : this.producers.get()) {
                this.buffer.replay(innerProducer);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$InnerProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(ReplaySubscriber<T> replaySubscriber, Subscriber<? super T> subscriber) {
            this.parent = replaySubscriber;
            this.child = subscriber;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            long j2;
            long j3;
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                return;
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return;
                }
                if (j2 >= 0 && i == 0) {
                    return;
                }
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!compareAndSet(j2, j3));
            addTotalRequested(j);
            this.parent.manageRequests();
            this.parent.buffer.replay(this);
        }

        void addTotalRequested(long j) {
            long j2;
            long j3;
            do {
                j2 = this.totalRequested.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(j2, j3));
        }

        public long produced(long j) {
            long j2;
            long j3;
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                j3 = j2 - j;
                if (j3 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j2 + ")");
                }
            } while (!compareAndSet(j2, j3));
            return j3;
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (get() == Long.MIN_VALUE || getAndSet(Long.MIN_VALUE) == Long.MIN_VALUE) {
                return;
            }
            this.parent.remove(this);
            this.parent.manageRequests();
        }

        <U> U index() {
            return (U) this.index;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$UnboundedReplayBuffer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;

        /* renamed from: nl */
        final NotificationLite<T> f27601nl;
        volatile int size;

        public UnboundedReplayBuffer(int i) {
            super(i);
            this.f27601nl = NotificationLite.instance();
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public void next(T t) {
            add(this.f27601nl.next(t));
            this.size++;
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public void error(Throwable th) {
            add(this.f27601nl.error(th));
            this.size++;
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public void complete() {
            add(this.f27601nl.completed());
            this.size++;
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public void replay(InnerProducer<T> innerProducer) {
            synchronized (innerProducer) {
                if (innerProducer.emitting) {
                    innerProducer.missed = true;
                    return;
                }
                innerProducer.emitting = true;
                while (!innerProducer.isUnsubscribed()) {
                    int i = this.size;
                    Integer num = (Integer) innerProducer.index();
                    int intValue = num != null ? num.intValue() : 0;
                    long j = innerProducer.get();
                    long j2 = j;
                    long j3 = 0;
                    while (j2 != 0 && intValue < i) {
                        Object obj = get(intValue);
                        try {
                            if (this.f27601nl.accept(innerProducer.child, obj) || innerProducer.isUnsubscribed()) {
                                return;
                            }
                            intValue++;
                            j2--;
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            innerProducer.unsubscribe();
                            if (this.f27601nl.isError(obj) || this.f27601nl.isCompleted(obj)) {
                                return;
                            }
                            innerProducer.child.onError(OnErrorThrowable.addValueAsLastCause(th, this.f27601nl.getValue(obj)));
                            return;
                        }
                    }
                    if (j3 != 0) {
                        innerProducer.index = Integer.valueOf(intValue);
                        if (j != Long.MAX_VALUE) {
                            innerProducer.produced(j3);
                        }
                    }
                    synchronized (innerProducer) {
                        if (!innerProducer.missed) {
                            innerProducer.emitting = false;
                            return;
                        }
                        innerProducer.missed = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$Node */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        public Node(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$BoundedReplayBuffer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;

        /* renamed from: nl */
        final NotificationLite<T> f27599nl = NotificationLite.instance();
        int size;
        Node tail;

        Object enterTransform(Object obj) {
            return obj;
        }

        Object leaveTransform(Object obj) {
            return obj;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        public BoundedReplayBuffer() {
            Node node = new Node(null, 0L);
            this.tail = node;
            set(node);
        }

        final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        final void removeFirst() {
            Node node = get().get();
            if (node == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(node);
        }

        final void removeSome(int i) {
            Node node = get();
            while (i > 0) {
                node = node.get();
                i--;
                this.size--;
            }
            setFirst(node);
        }

        final void setFirst(Node node) {
            set(node);
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void next(T t) {
            Object enterTransform = enterTransform(this.f27599nl.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncate();
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void error(Throwable th) {
            Object enterTransform = enterTransform(this.f27599nl.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void complete() {
            Object enterTransform = enterTransform(this.f27599nl.completed());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        @Override // p475rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void replay(InnerProducer<T> innerProducer) {
            Node node;
            synchronized (innerProducer) {
                if (innerProducer.emitting) {
                    innerProducer.missed = true;
                    return;
                }
                innerProducer.emitting = true;
                while (!innerProducer.isUnsubscribed()) {
                    long j = innerProducer.get();
                    boolean z = j == Long.MAX_VALUE;
                    Node node2 = (Node) innerProducer.index();
                    if (node2 == null) {
                        node2 = get();
                        innerProducer.index = node2;
                        innerProducer.addTotalRequested(node2.index);
                    }
                    if (innerProducer.isUnsubscribed()) {
                        return;
                    }
                    long j2 = 0;
                    while (j != 0 && (node = node2.get()) != null) {
                        Object leaveTransform = leaveTransform(node.value);
                        try {
                            if (this.f27599nl.accept(innerProducer.child, leaveTransform)) {
                                innerProducer.index = null;
                                return;
                            }
                            j2++;
                            j--;
                            if (innerProducer.isUnsubscribed()) {
                                return;
                            }
                            node2 = node;
                        } catch (Throwable th) {
                            innerProducer.index = null;
                            Exceptions.throwIfFatal(th);
                            innerProducer.unsubscribe();
                            if (this.f27599nl.isError(leaveTransform) || this.f27599nl.isCompleted(leaveTransform)) {
                                return;
                            }
                            innerProducer.child.onError(OnErrorThrowable.addValueAsLastCause(th, this.f27599nl.getValue(leaveTransform)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        innerProducer.index = node2;
                        if (!z) {
                            innerProducer.produced(j2);
                        }
                    }
                    synchronized (innerProducer) {
                        if (!innerProducer.missed) {
                            innerProducer.emitting = false;
                            return;
                        }
                        innerProducer.missed = false;
                    }
                }
            }
        }

        final void collect(Collection<? super T> collection) {
            Node node = get();
            while (true) {
                node = node.get();
                if (node == null) {
                    return;
                }
                Object leaveTransform = leaveTransform(node.value);
                if (this.f27599nl.isCompleted(leaveTransform) || this.f27599nl.isError(leaveTransform)) {
                    return;
                }
                collection.add((T) this.f27599nl.getValue(leaveTransform));
            }
        }

        boolean hasError() {
            return this.tail.value != null && this.f27599nl.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && this.f27599nl.isCompleted(leaveTransform(this.tail.value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$SizeBoundReplayBuffer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        public SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        @Override // p475rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorReplay$SizeAndTimeBoundReplayBuffer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final Scheduler scheduler;

        public SizeAndTimeBoundReplayBuffer(int i, long j, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAgeInMillis = j;
        }

        @Override // p475rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object enterTransform(Object obj) {
            return new Timestamped(this.scheduler.now(), obj);
        }

        @Override // p475rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object leaveTransform(Object obj) {
            return ((Timestamped) obj).getValue();
        }

        @Override // p475rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            long now = this.scheduler.now() - this.maxAgeInMillis;
            Node node = (Node) get();
            int i = 0;
            Node node2 = node;
            Node node3 = node.get();
            while (node3 != null) {
                if (this.size > this.limit) {
                    i++;
                    this.size--;
                    node2 = node3;
                    node3 = node3.get();
                } else if (((Timestamped) node3.value).getTimestampMillis() > now) {
                    break;
                } else {
                    i++;
                    this.size--;
                    node2 = node3;
                    node3 = node3.get();
                }
            }
            if (i != 0) {
                setFirst(node2);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003f, code lost:
            setFirst(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0042, code lost:
            return;
         */
        @Override // p475rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void truncateFinal() {
            /*
                r10 = this;
                rx.Scheduler r0 = r10.scheduler
                long r0 = r0.now()
                long r2 = r10.maxAgeInMillis
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                rx.internal.operators.OperatorReplay$Node r2 = (p475rx.internal.operators.OperatorReplay.Node) r2
                java.lang.Object r3 = r2.get()
                rx.internal.operators.OperatorReplay$Node r3 = (p475rx.internal.operators.OperatorReplay.Node) r3
                r4 = 0
                r9 = r3
                r3 = r2
                r2 = r9
            L19:
                if (r2 == 0) goto L3d
                int r5 = r10.size
                r6 = 1
                if (r5 <= r6) goto L3d
                java.lang.Object r5 = r2.value
                rx.schedulers.Timestamped r5 = (p475rx.schedulers.Timestamped) r5
                long r7 = r5.getTimestampMillis()
                int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r5 > 0) goto L3d
                int r4 = r4 + 1
                int r3 = r10.size
                int r3 = r3 - r6
                r10.size = r3
                java.lang.Object r3 = r2.get()
                rx.internal.operators.OperatorReplay$Node r3 = (p475rx.internal.operators.OperatorReplay.Node) r3
                r9 = r3
                r3 = r2
                r2 = r9
                goto L19
            L3d:
                if (r4 == 0) goto L42
                r10.setFirst(r3)
            L42:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p475rx.internal.operators.OperatorReplay.SizeAndTimeBoundReplayBuffer.truncateFinal():void");
        }
    }
}
