package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.functions.Action0;
import p475rx.functions.Func1;
import p475rx.internal.producers.ProducerArbiter;
import p475rx.internal.util.RxRingBuffer;
import p475rx.internal.util.UtilityFunctions;
import p475rx.observables.GroupedObservable;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorGroupBy */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorGroupBy<T, K, V> implements Observable.Operator<GroupedObservable<K, V>, T> {
    final int bufferSize;
    final boolean delayError;
    final Func1<? super T, ? extends K> keySelector;
    final Func1<? super T, ? extends V> valueSelector;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1) {
        this(func1, UtilityFunctions.identity(), RxRingBuffer.SIZE, false);
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, RxRingBuffer.SIZE, false);
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int i, boolean z) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.bufferSize = i;
        this.delayError = z;
    }

    public Subscriber<? super T> call(Subscriber<? super GroupedObservable<K, V>> subscriber) {
        final GroupBySubscriber groupBySubscriber = new GroupBySubscriber(subscriber, this.keySelector, this.valueSelector, this.bufferSize, this.delayError);
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorGroupBy.1
            @Override // p475rx.functions.Action0
            public void call() {
                groupBySubscriber.cancel();
            }
        }));
        subscriber.setProducer(groupBySubscriber.producer);
        return groupBySubscriber;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorGroupBy$GroupByProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class GroupByProducer implements Producer {
        final GroupBySubscriber<?, ?, ?> parent;

        public GroupByProducer(GroupBySubscriber<?, ?, ?> groupBySubscriber) {
            this.parent = groupBySubscriber;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            this.parent.requestMore(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorGroupBy$GroupBySubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class GroupBySubscriber<T, K, V> extends Subscriber<T> {
        final Subscriber<? super GroupedObservable<K, V>> actual;
        final int bufferSize;
        volatile int cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        volatile int groupCount;
        final Func1<? super T, ? extends K> keySelector;
        final GroupByProducer producer;
        volatile long requested;

        /* renamed from: s */
        final ProducerArbiter f27593s;
        final Func1<? super T, ? extends V> valueSelector;
        volatile int wip;
        static final Object NULL_KEY = new Object();
        static final AtomicIntegerFieldUpdater<GroupBySubscriber> CANCELLED = AtomicIntegerFieldUpdater.newUpdater(GroupBySubscriber.class, "cancelled");
        static final AtomicLongFieldUpdater<GroupBySubscriber> REQUESTED = AtomicLongFieldUpdater.newUpdater(GroupBySubscriber.class, "requested");
        static final AtomicIntegerFieldUpdater<GroupBySubscriber> GROUP_COUNT = AtomicIntegerFieldUpdater.newUpdater(GroupBySubscriber.class, "groupCount");
        static final AtomicIntegerFieldUpdater<GroupBySubscriber> WIP = AtomicIntegerFieldUpdater.newUpdater(GroupBySubscriber.class, "wip");
        final Map<Object, GroupedUnicast<K, V>> groups = new ConcurrentHashMap();
        final Queue<GroupedObservable<K, V>> queue = new ConcurrentLinkedQueue();

        public GroupBySubscriber(Subscriber<? super GroupedObservable<K, V>> subscriber, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int i, boolean z) {
            this.actual = subscriber;
            this.keySelector = func1;
            this.valueSelector = func12;
            this.bufferSize = i;
            this.delayError = z;
            GROUP_COUNT.lazySet(this, 1);
            this.f27593s = new ProducerArbiter();
            this.f27593s.request(i);
            this.producer = new GroupByProducer(this);
        }

        @Override // p475rx.Subscriber
        public void setProducer(Producer producer) {
            this.f27593s.setProducer(producer);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            Queue<?> queue = this.queue;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
            try {
                K call = this.keySelector.call(t);
                boolean z = true;
                Object obj = call != null ? call : NULL_KEY;
                GroupedUnicast groupedUnicast = this.groups.get(obj);
                if (groupedUnicast == null) {
                    if (this.cancelled != 0) {
                        return;
                    }
                    groupedUnicast = GroupedUnicast.createWith(call, this.bufferSize, this, this.delayError);
                    this.groups.put(obj, groupedUnicast);
                    GROUP_COUNT.getAndIncrement(this);
                    z = false;
                    queue.offer(groupedUnicast);
                    drain();
                }
                try {
                    groupedUnicast.onNext(this.valueSelector.call(t));
                    if (z) {
                        this.f27593s.request(1L);
                    }
                } catch (Throwable th) {
                    unsubscribe();
                    errorAll(subscriber, queue, th);
                }
            } catch (Throwable th2) {
                unsubscribe();
                errorAll(subscriber, queue, th2);
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.error = th;
            this.done = true;
            GROUP_COUNT.decrementAndGet(this);
            drain();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            for (GroupedUnicast<K, V> groupedUnicast : this.groups.values()) {
                groupedUnicast.onComplete();
            }
            this.groups.clear();
            this.done = true;
            GROUP_COUNT.decrementAndGet(this);
            drain();
        }

        public void requestMore(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            BackpressureUtils.getAndAddRequest(REQUESTED, this, j);
            drain();
        }

        public void cancel() {
            if (CANCELLED.compareAndSet(this, 0, 1) && GROUP_COUNT.decrementAndGet(this) == 0) {
                unsubscribe();
            }
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            if (this.groups.remove(k) == null || GROUP_COUNT.decrementAndGet(this) != 0) {
                return;
            }
            unsubscribe();
        }

        void drain() {
            if (WIP.getAndIncrement(this) != 0) {
                return;
            }
            Queue<GroupedObservable<K, V>> queue = this.queue;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
            int i = 1;
            while (!checkTerminated(this.done, queue.isEmpty(), subscriber, queue)) {
                long j = this.requested;
                boolean z = j == Long.MAX_VALUE;
                long j2 = 0;
                while (j != 0) {
                    boolean z2 = this.done;
                    GroupedObservable<K, V> poll = queue.poll();
                    boolean z3 = poll == null;
                    if (checkTerminated(z2, z3, subscriber, queue)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    subscriber.onNext(poll);
                    j--;
                    j2--;
                }
                if (j2 != 0) {
                    if (!z) {
                        REQUESTED.addAndGet(this, j2);
                    }
                    this.f27593s.request(-j2);
                }
                i = WIP.addAndGet(this, -i);
                if (i == 0) {
                    return;
                }
            }
        }

        void errorAll(Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue, Throwable th) {
            queue.clear();
            ArrayList<GroupedUnicast> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (GroupedUnicast groupedUnicast : arrayList) {
                groupedUnicast.onError(th);
            }
            subscriber.onError(th);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue) {
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    errorAll(subscriber, queue, th);
                    return true;
                } else if (z2) {
                    this.actual.onCompleted();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorGroupBy$GroupedUnicast */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class GroupedUnicast<K, T> extends GroupedObservable<K, T> {
        final State<T, K> state;

        public static <T, K> GroupedUnicast<K, T> createWith(K k, int i, GroupBySubscriber<?, K, T> groupBySubscriber, boolean z) {
            return new GroupedUnicast<>(k, new State(i, groupBySubscriber, k, z));
        }

        protected GroupedUnicast(K k, State<T, K> state) {
            super(k, state);
            this.state = state;
        }

        public void onNext(T t) {
            this.state.onNext(t);
        }

        public void onError(Throwable th) {
            this.state.onError(th);
        }

        public void onComplete() {
            this.state.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorGroupBy$State */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class State<T, K> extends AtomicInteger implements Observable.OnSubscribe<T>, Producer, Subscription {
        private static final long serialVersionUID = -3852313036005250360L;
        volatile Subscriber<? super T> actual;
        volatile int cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        volatile int once;
        final GroupBySubscriber<?, K, T> parent;
        final Queue<Object> queue = new ConcurrentLinkedQueue();
        volatile long requested;
        static final AtomicLongFieldUpdater<State> REQUESTED = AtomicLongFieldUpdater.newUpdater(State.class, "requested");
        static final AtomicIntegerFieldUpdater<State> CANCELLED = AtomicIntegerFieldUpdater.newUpdater(State.class, "cancelled");
        static final AtomicReferenceFieldUpdater<State, Subscriber> ACTUAL = AtomicReferenceFieldUpdater.newUpdater(State.class, Subscriber.class, "actual");
        static final AtomicIntegerFieldUpdater<State> ONCE = AtomicIntegerFieldUpdater.newUpdater(State.class, "once");

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public State(int i, GroupBySubscriber<?, K, T> groupBySubscriber, K k, boolean z) {
            this.parent = groupBySubscriber;
            this.key = k;
            this.delayError = z;
        }

        @Override // p475rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (i != 0) {
                BackpressureUtils.getAndAddRequest(REQUESTED, this, j);
                drain();
            }
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled != 0;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (CANCELLED.compareAndSet(this, 0, 1) && getAndIncrement() == 0) {
                this.parent.cancel(this.key);
            }
        }

        public void call(Subscriber<? super T> subscriber) {
            if (ONCE.compareAndSet(this, 0, 1)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                ACTUAL.lazySet(this, subscriber);
                drain();
                return;
            }
            subscriber.onError(new IllegalStateException("Only one Subscriber allowed!"));
        }

        public void onNext(T t) {
            if (t == null) {
                this.error = new NullPointerException();
                this.done = true;
            } else {
                this.queue.offer(NotificationLite.instance().next(t));
            }
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Queue<Object> queue = this.queue;
            boolean z = this.delayError;
            Subscriber<? super T> subscriber = this.actual;
            NotificationLite instance = NotificationLite.instance();
            int i = 1;
            while (true) {
                if (subscriber != null) {
                    if (checkTerminated(this.done, queue.isEmpty(), subscriber, z)) {
                        return;
                    }
                    long j = this.requested;
                    boolean z2 = j == Long.MAX_VALUE;
                    long j2 = 0;
                    while (j != 0) {
                        boolean z3 = this.done;
                        Object poll = queue.poll();
                        boolean z4 = poll == null;
                        if (checkTerminated(z3, z4, subscriber, z)) {
                            return;
                        }
                        if (z4) {
                            break;
                        }
                        subscriber.onNext((Object) instance.getValue(poll));
                        j--;
                        j2--;
                    }
                    if (j2 != 0) {
                        if (!z2) {
                            REQUESTED.addAndGet(this, j2);
                        }
                        this.parent.f27593s.request(-j2);
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
                if (subscriber == null) {
                    subscriber = this.actual;
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
            if (this.cancelled != 0) {
                this.queue.clear();
                this.parent.cancel(this.key);
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = this.error;
                if (th2 != null) {
                    this.queue.clear();
                    subscriber.onError(th2);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
