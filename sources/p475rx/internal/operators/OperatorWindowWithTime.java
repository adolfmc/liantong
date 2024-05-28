package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.observers.SerializedObserver;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorWindowWithTime */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorWindowWithTime<T> implements Observable.Operator<Observable<T>, T> {
    static final Object NEXT_SUBJECT = new Object();

    /* renamed from: nl */
    static final NotificationLite<Object> f27610nl = NotificationLite.instance();
    final Scheduler scheduler;
    final int size;
    final long timeshift;
    final long timespan;
    final TimeUnit unit;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorWindowWithTime(long j, long j2, TimeUnit timeUnit, int i, Scheduler scheduler) {
        this.timespan = j;
        this.timeshift = j2;
        this.unit = timeUnit;
        this.size = i;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        if (this.timespan == this.timeshift) {
            ExactSubscriber exactSubscriber = new ExactSubscriber(subscriber, createWorker);
            exactSubscriber.add(createWorker);
            exactSubscriber.scheduleExact();
            return exactSubscriber;
        }
        InexactSubscriber inexactSubscriber = new InexactSubscriber(subscriber, createWorker);
        inexactSubscriber.add(createWorker);
        inexactSubscriber.startNewChunk();
        inexactSubscriber.scheduleChunk();
        return inexactSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithTime$State */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class State<T> {
        static final State<Object> EMPTY = new State<>(null, null, 0);
        final Observer<T> consumer;
        final int count;
        final Observable<T> producer;

        public State(Observer<T> observer, Observable<T> observable, int i) {
            this.consumer = observer;
            this.producer = observable;
            this.count = i;
        }

        public State<T> next() {
            return new State<>(this.consumer, this.producer, this.count + 1);
        }

        public State<T> create(Observer<T> observer, Observable<T> observable) {
            return new State<>(observer, observable, 0);
        }

        public State<T> clear() {
            return empty();
        }

        public static <T> State<T> empty() {
            return (State<T>) EMPTY;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithTime$ExactSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class ExactSubscriber extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        boolean emitting;
        List<Object> queue;
        final Scheduler.Worker worker;
        final Object guard = new Object();
        volatile State<T> state = State.empty();

        public ExactSubscriber(Subscriber<? super Observable<T>> subscriber, Scheduler.Worker worker) {
            this.child = new SerializedSubscriber(subscriber);
            this.worker = worker;
            subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.1
                @Override // p475rx.functions.Action0
                public void call() {
                    if (ExactSubscriber.this.state.consumer == null) {
                        ExactSubscriber.this.unsubscribe();
                    }
                }
            }));
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            List<Object> list;
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(t);
                    return;
                }
                boolean z = true;
                this.emitting = true;
                try {
                    if (emitValue(t)) {
                        do {
                            try {
                                synchronized (this.guard) {
                                    try {
                                        list = this.queue;
                                        if (list == null) {
                                            this.emitting = false;
                                            return;
                                        }
                                        this.queue = null;
                                    } catch (Throwable th) {
                                        th = th;
                                        z = false;
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            if (!z) {
                                                synchronized (this.guard) {
                                                    this.emitting = false;
                                                }
                                            }
                                            throw th;
                                        }
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } while (drain(list));
                        synchronized (this.guard) {
                            this.emitting = false;
                        }
                        return;
                    }
                    synchronized (this.guard) {
                        this.emitting = false;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    z = false;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
            return true;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean drain(java.util.List<java.lang.Object> r5) {
            /*
                r4 = this;
                r0 = 1
                if (r5 != 0) goto L4
                return r0
            L4:
                java.util.Iterator r5 = r5.iterator()
            L8:
                boolean r1 = r5.hasNext()
                if (r1 == 0) goto L43
                java.lang.Object r1 = r5.next()
                java.lang.Object r2 = p475rx.internal.operators.OperatorWindowWithTime.NEXT_SUBJECT
                r3 = 0
                if (r1 != r2) goto L1e
                boolean r1 = r4.replaceSubject()
                if (r1 != 0) goto L8
                return r3
            L1e:
                rx.internal.operators.NotificationLite<java.lang.Object> r2 = p475rx.internal.operators.OperatorWindowWithTime.f27610nl
                boolean r2 = r2.isError(r1)
                if (r2 == 0) goto L30
                rx.internal.operators.NotificationLite<java.lang.Object> r5 = p475rx.internal.operators.OperatorWindowWithTime.f27610nl
                java.lang.Throwable r5 = r5.getError(r1)
                r4.error(r5)
                goto L43
            L30:
                rx.internal.operators.NotificationLite<java.lang.Object> r2 = p475rx.internal.operators.OperatorWindowWithTime.f27610nl
                boolean r2 = r2.isCompleted(r1)
                if (r2 == 0) goto L3c
                r4.complete()
                goto L43
            L3c:
                boolean r1 = r4.emitValue(r1)
                if (r1 != 0) goto L8
                return r3
            L43:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p475rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.drain(java.util.List):boolean");
        }

        boolean replaceSubject() {
            Observer<T> observer = this.state.consumer;
            if (observer != null) {
                observer.onCompleted();
            }
            if (this.child.isUnsubscribed()) {
                this.state = this.state.clear();
                unsubscribe();
                return false;
            }
            UnicastSubject create = UnicastSubject.create();
            this.state = this.state.create(create, create);
            this.child.onNext(create);
            return true;
        }

        boolean emitValue(T t) {
            State<T> next;
            State<T> state = this.state;
            if (state.consumer == null) {
                if (!replaceSubject()) {
                    return false;
                }
                state = this.state;
            }
            state.consumer.onNext(t);
            if (state.count == OperatorWindowWithTime.this.size - 1) {
                state.consumer.onCompleted();
                next = state.clear();
            } else {
                next = state.next();
            }
            this.state = next;
            return true;
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            synchronized (this.guard) {
                if (this.emitting) {
                    this.queue = Collections.singletonList(OperatorWindowWithTime.f27610nl.error(th));
                    return;
                }
                this.queue = null;
                this.emitting = true;
                error(th);
            }
        }

        void error(Throwable th) {
            Observer<T> observer = this.state.consumer;
            this.state = this.state.clear();
            if (observer != null) {
                observer.onError(th);
            }
            this.child.onError(th);
            unsubscribe();
        }

        void complete() {
            Observer<T> observer = this.state.consumer;
            this.state = this.state.clear();
            if (observer != null) {
                observer.onCompleted();
            }
            this.child.onCompleted();
            unsubscribe();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(OperatorWindowWithTime.f27610nl.completed());
                    return;
                }
                List<Object> list = this.queue;
                this.queue = null;
                this.emitting = true;
                try {
                    drain(list);
                    complete();
                } catch (Throwable th) {
                    error(th);
                }
            }
        }

        void scheduleExact() {
            this.worker.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.ExactSubscriber.2
                @Override // p475rx.functions.Action0
                public void call() {
                    ExactSubscriber.this.nextWindow();
                }
            }, 0L, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
        }

        void nextWindow() {
            boolean z;
            List<Object> list;
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(OperatorWindowWithTime.NEXT_SUBJECT);
                    return;
                }
                boolean z2 = true;
                this.emitting = true;
                try {
                    if (replaceSubject()) {
                        do {
                            try {
                                synchronized (this.guard) {
                                    try {
                                        list = this.queue;
                                        if (list == null) {
                                            this.emitting = false;
                                            return;
                                        }
                                        this.queue = null;
                                    } catch (Throwable th) {
                                        th = th;
                                        z2 = false;
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            z = z2;
                                            th = th2;
                                            if (!z) {
                                                synchronized (this.guard) {
                                                    this.emitting = false;
                                                }
                                            }
                                            throw th;
                                        }
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } while (drain(list));
                        synchronized (this.guard) {
                            this.emitting = false;
                        }
                        return;
                    }
                    synchronized (this.guard) {
                        this.emitting = false;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    z = false;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithTime$CountedSerializedSubject */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class CountedSerializedSubject<T> {
        final Observer<T> consumer;
        int count;
        final Observable<T> producer;

        public CountedSerializedSubject(Observer<T> observer, Observable<T> observable) {
            this.consumer = new SerializedObserver(observer);
            this.producer = observable;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithTime$InexactSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class InexactSubscriber extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        final List<CountedSerializedSubject<T>> chunks;
        boolean done;
        final Object guard;
        final Scheduler.Worker worker;

        public InexactSubscriber(Subscriber<? super Observable<T>> subscriber, Scheduler.Worker worker) {
            super(subscriber);
            this.child = subscriber;
            this.worker = worker;
            this.guard = new Object();
            this.chunks = new LinkedList();
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                ArrayList<CountedSerializedSubject> arrayList = new ArrayList(this.chunks);
                Iterator<CountedSerializedSubject<T>> it = this.chunks.iterator();
                while (it.hasNext()) {
                    CountedSerializedSubject<T> next = it.next();
                    int i = next.count + 1;
                    next.count = i;
                    if (i == OperatorWindowWithTime.this.size) {
                        it.remove();
                    }
                }
                for (CountedSerializedSubject countedSerializedSubject : arrayList) {
                    countedSerializedSubject.consumer.onNext(t);
                    if (countedSerializedSubject.count == OperatorWindowWithTime.this.size) {
                        countedSerializedSubject.consumer.onCompleted();
                    }
                }
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.done = true;
                ArrayList<CountedSerializedSubject> arrayList = new ArrayList(this.chunks);
                this.chunks.clear();
                for (CountedSerializedSubject countedSerializedSubject : arrayList) {
                    countedSerializedSubject.consumer.onError(th);
                }
                this.child.onError(th);
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.done = true;
                ArrayList<CountedSerializedSubject> arrayList = new ArrayList(this.chunks);
                this.chunks.clear();
                for (CountedSerializedSubject countedSerializedSubject : arrayList) {
                    countedSerializedSubject.consumer.onCompleted();
                }
                this.child.onCompleted();
            }
        }

        void scheduleChunk() {
            this.worker.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.InexactSubscriber.1
                @Override // p475rx.functions.Action0
                public void call() {
                    InexactSubscriber.this.startNewChunk();
                }
            }, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.unit);
        }

        void startNewChunk() {
            final CountedSerializedSubject<T> createCountedSerializedSubject = createCountedSerializedSubject();
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.chunks.add(createCountedSerializedSubject);
                try {
                    this.child.onNext(createCountedSerializedSubject.producer);
                    this.worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorWindowWithTime.InexactSubscriber.2
                        @Override // p475rx.functions.Action0
                        public void call() {
                            InexactSubscriber.this.terminateChunk(createCountedSerializedSubject);
                        }
                    }, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
                } catch (Throwable th) {
                    onError(th);
                }
            }
        }

        void terminateChunk(CountedSerializedSubject<T> countedSerializedSubject) {
            boolean z;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                Iterator<CountedSerializedSubject<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == countedSerializedSubject) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    countedSerializedSubject.consumer.onCompleted();
                }
            }
        }

        CountedSerializedSubject<T> createCountedSerializedSubject() {
            UnicastSubject create = UnicastSubject.create();
            return new CountedSerializedSubject<>(create, create);
        }
    }
}
