package p475rx.observables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.annotations.Experimental;
import p475rx.functions.Action0;
import p475rx.functions.Action1;
import p475rx.functions.Action2;
import p475rx.functions.Action3;
import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.functions.Func3;
import p475rx.internal.operators.BufferUntilSubscriber;
import p475rx.observers.SerializedObserver;
import p475rx.plugins.RxJavaPlugins;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* renamed from: rx.observables.AsyncOnSubscribe */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class AsyncOnSubscribe<S, T> implements Observable.OnSubscribe<T> {
    protected abstract S generateState();

    protected abstract S next(S s, long j, Observer<Observable<? extends T>> observer);

    protected void onUnsubscribe(S s) {
    }

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, final Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3) {
        return new AsyncOnSubscribeImpl(func0, new Func3<S, Long, Observer<Observable<? extends T>>, S>() { // from class: rx.observables.AsyncOnSubscribe.1
            @Override // p475rx.functions.Func3
            public /* bridge */ /* synthetic */ Object call(Object obj, Long l, Object obj2) {
                return call((C140581) obj, l, (Observer) ((Observer) obj2));
            }

            public S call(S s, Long l, Observer<Observable<? extends T>> observer) {
                Action3.this.call(s, l, observer);
                return s;
            }
        });
    }

    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, final Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3, Action1<? super S> action1) {
        return new AsyncOnSubscribeImpl(func0, new Func3<S, Long, Observer<Observable<? extends T>>, S>() { // from class: rx.observables.AsyncOnSubscribe.2
            @Override // p475rx.functions.Func3
            public /* bridge */ /* synthetic */ Object call(Object obj, Long l, Object obj2) {
                return call((C140592) obj, l, (Observer) ((Observer) obj2));
            }

            public S call(S s, Long l, Observer<Observable<? extends T>> observer) {
                Action3.this.call(s, l, observer);
                return s;
            }
        }, action1);
    }

    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3, Action1<? super S> action1) {
        return new AsyncOnSubscribeImpl(func0, func3, action1);
    }

    @Experimental
    public static <S, T> AsyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3) {
        return new AsyncOnSubscribeImpl(func0, func3);
    }

    @Experimental
    public static <T> AsyncOnSubscribe<Void, T> createStateless(final Action2<Long, ? super Observer<Observable<? extends T>>> action2) {
        return new AsyncOnSubscribeImpl(new Func3<Void, Long, Observer<Observable<? extends T>>, Void>() { // from class: rx.observables.AsyncOnSubscribe.3
            @Override // p475rx.functions.Func3
            public /* bridge */ /* synthetic */ Void call(Void r1, Long l, Object obj) {
                return call(r1, l, (Observer) ((Observer) obj));
            }

            public Void call(Void r2, Long l, Observer<Observable<? extends T>> observer) {
                Action2.this.call(l, observer);
                return r2;
            }
        });
    }

    @Experimental
    public static <T> AsyncOnSubscribe<Void, T> createStateless(final Action2<Long, ? super Observer<Observable<? extends T>>> action2, final Action0 action0) {
        return new AsyncOnSubscribeImpl(new Func3<Void, Long, Observer<Observable<? extends T>>, Void>() { // from class: rx.observables.AsyncOnSubscribe.4
            @Override // p475rx.functions.Func3
            public /* bridge */ /* synthetic */ Void call(Void r1, Long l, Object obj) {
                return call(r1, l, (Observer) ((Observer) obj));
            }

            public Void call(Void r1, Long l, Observer<Observable<? extends T>> observer) {
                Action2.this.call(l, observer);
                return null;
            }
        }, new Action1<Void>() { // from class: rx.observables.AsyncOnSubscribe.5
            @Override // p475rx.functions.Action1
            public void call(Void r1) {
                Action0.this.call();
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.observables.AsyncOnSubscribe$AsyncOnSubscribeImpl */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class AsyncOnSubscribeImpl<S, T> extends AsyncOnSubscribe<S, T> {
        private final Func0<? extends S> generator;
        private final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> next;
        private final Action1<? super S> onUnsubscribe;

        @Override // p475rx.observables.AsyncOnSubscribe, p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            super.call((Subscriber) ((Subscriber) obj));
        }

        AsyncOnSubscribeImpl(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3, Action1<? super S> action1) {
            this.generator = func0;
            this.next = func3;
            this.onUnsubscribe = action1;
        }

        public AsyncOnSubscribeImpl(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3) {
            this(func0, func3, null);
        }

        public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> func3, Action1<? super S> action1) {
            this(null, func3, action1);
        }

        public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> func3) {
            this(null, func3, null);
        }

        @Override // p475rx.observables.AsyncOnSubscribe
        protected S generateState() {
            Func0<? extends S> func0 = this.generator;
            if (func0 == null) {
                return null;
            }
            return func0.call();
        }

        @Override // p475rx.observables.AsyncOnSubscribe
        protected S next(S s, long j, Observer<Observable<? extends T>> observer) {
            return this.next.call(s, Long.valueOf(j), observer);
        }

        @Override // p475rx.observables.AsyncOnSubscribe
        protected void onUnsubscribe(S s) {
            Action1<? super S> action1 = this.onUnsubscribe;
            if (action1 != null) {
                action1.call(s);
            }
        }
    }

    public final void call(final Subscriber<? super T> subscriber) {
        try {
            S generateState = generateState();
            UnicastSubject create = UnicastSubject.create();
            final AsyncOuterManager asyncOuterManager = new AsyncOuterManager(this, generateState, create);
            Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.observables.AsyncOnSubscribe.6
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

                @Override // p475rx.Subscriber
                public void setProducer(Producer producer) {
                    asyncOuterManager.setConcatProducer(producer);
                }
            };
            create.onBackpressureBuffer().concatMap(new Func1<Observable<T>, Observable<T>>() { // from class: rx.observables.AsyncOnSubscribe.7
                @Override // p475rx.functions.Func1
                public /* bridge */ /* synthetic */ Object call(Object obj) {
                    return call((Observable) ((Observable) obj));
                }

                public Observable<T> call(Observable<T> observable) {
                    return observable.onBackpressureBuffer();
                }
            }).unsafeSubscribe(subscriber2);
            subscriber.add(subscriber2);
            subscriber.add(asyncOuterManager);
            subscriber.setProducer(asyncOuterManager);
        } catch (Throwable th) {
            subscriber.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.observables.AsyncOnSubscribe$AsyncOuterManager */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class AsyncOuterManager<S, T> implements Observer<Observable<? extends T>>, Producer, Subscription {
        private static final AtomicIntegerFieldUpdater<AsyncOuterManager> IS_UNSUBSCRIBED = AtomicIntegerFieldUpdater.newUpdater(AsyncOuterManager.class, "isUnsubscribed");
        Producer concatProducer;
        boolean emitting;
        long expectedDelivery;
        private boolean hasTerminated;
        private volatile int isUnsubscribed;
        private final UnicastSubject<Observable<T>> merger;
        private boolean onNextCalled;
        private final AsyncOnSubscribe<S, T> parent;
        List<Long> requests;
        private S state;
        final CompositeSubscription subscriptions = new CompositeSubscription();
        private final SerializedObserver<Observable<? extends T>> serializedSubscriber = new SerializedObserver<>(this);

        @Override // p475rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        public AsyncOuterManager(AsyncOnSubscribe<S, T> asyncOnSubscribe, S s, UnicastSubject<Observable<T>> unicastSubject) {
            this.parent = asyncOnSubscribe;
            this.state = s;
            this.merger = unicastSubject;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            if (IS_UNSUBSCRIBED.compareAndSet(this, 0, 1)) {
                synchronized (this) {
                    if (this.emitting) {
                        this.requests = new ArrayList();
                        this.requests.add(0L);
                        return;
                    }
                    this.emitting = true;
                    cleanup();
                }
            }
        }

        void setConcatProducer(Producer producer) {
            if (this.concatProducer != null) {
                throw new IllegalStateException("setConcatProducer may be called at most once!");
            }
            this.concatProducer = producer;
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return this.isUnsubscribed != 0;
        }

        public void nextIteration(long j) {
            this.state = this.parent.next(this.state, j, this.serializedSubscriber);
        }

        void cleanup() {
            this.subscriptions.unsubscribe();
            try {
                this.parent.onUnsubscribe(this.state);
            } catch (Throwable th) {
                handleThrownError(th);
            }
        }

        @Override // p475rx.Producer
        public void request(long j) {
            boolean z;
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i == 0) {
                return;
            }
            if (i < 0) {
                throw new IllegalStateException("Request can't be negative! " + j);
            }
            synchronized (this) {
                z = true;
                if (this.emitting) {
                    List list = this.requests;
                    if (list == null) {
                        list = new ArrayList();
                        this.requests = list;
                    }
                    list.add(Long.valueOf(j));
                } else {
                    this.emitting = true;
                    z = false;
                }
            }
            this.concatProducer.request(j);
            if (z || tryEmit(j)) {
                return;
            }
            while (true) {
                synchronized (this) {
                    List<Long> list2 = this.requests;
                    if (list2 == null) {
                        this.emitting = false;
                        return;
                    }
                    this.requests = null;
                    for (Long l : list2) {
                        if (tryEmit(l.longValue())) {
                            return;
                        }
                    }
                }
            }
        }

        public void requestRemaining(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i == 0) {
                return;
            }
            if (i < 0) {
                throw new IllegalStateException("Request can't be negative! " + j);
            }
            synchronized (this) {
                if (this.emitting) {
                    List list = this.requests;
                    if (list == null) {
                        list = new ArrayList();
                        this.requests = list;
                    }
                    list.add(Long.valueOf(j));
                    return;
                }
                this.emitting = true;
                if (tryEmit(j)) {
                    return;
                }
                while (true) {
                    synchronized (this) {
                        List<Long> list2 = this.requests;
                        if (list2 == null) {
                            this.emitting = false;
                            return;
                        }
                        this.requests = null;
                        for (Long l : list2) {
                            if (tryEmit(l.longValue())) {
                                return;
                            }
                        }
                    }
                }
            }
        }

        boolean tryEmit(long j) {
            if (isUnsubscribed()) {
                cleanup();
                return true;
            }
            try {
                this.onNextCalled = false;
                this.expectedDelivery = j;
                nextIteration(j);
                if (!this.hasTerminated && !isUnsubscribed()) {
                    if (this.onNextCalled) {
                        return false;
                    }
                    handleThrownError(new IllegalStateException("No events emitted!"));
                    return true;
                }
                cleanup();
                return true;
            } catch (Throwable th) {
                handleThrownError(th);
                return true;
            }
        }

        private void handleThrownError(Throwable th) {
            if (this.hasTerminated) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.hasTerminated = true;
            this.merger.onError(th);
            cleanup();
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            this.merger.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            this.merger.onError(th);
        }

        public void onNext(Observable<? extends T> observable) {
            if (this.onNextCalled) {
                throw new IllegalStateException("onNext called multiple times!");
            }
            this.onNextCalled = true;
            if (this.hasTerminated) {
                return;
            }
            subscribeBufferToObservable(observable);
        }

        private void subscribeBufferToObservable(Observable<? extends T> observable) {
            final BufferUntilSubscriber create = BufferUntilSubscriber.create();
            final long j = this.expectedDelivery;
            final Subscriber subscriber = (Subscriber<T>) new Subscriber<T>() { // from class: rx.observables.AsyncOnSubscribe.AsyncOuterManager.1
                long remaining;

                {
                    this.remaining = j;
                }

                @Override // p475rx.Observer
                public void onNext(T t) {
                    this.remaining--;
                    create.onNext(t);
                }

                @Override // p475rx.Observer
                public void onError(Throwable th) {
                    create.onError(th);
                }

                @Override // p475rx.Observer
                public void onCompleted() {
                    create.onCompleted();
                    long j2 = this.remaining;
                    if (j2 > 0) {
                        AsyncOuterManager.this.requestRemaining(j2);
                    }
                }
            };
            this.subscriptions.add(subscriber);
            observable.doOnTerminate(new Action0() { // from class: rx.observables.AsyncOnSubscribe.AsyncOuterManager.2
                @Override // p475rx.functions.Action0
                public void call() {
                    AsyncOuterManager.this.subscriptions.remove(subscriber);
                }
            }).subscribe((Subscriber<? super Object>) subscriber);
            this.merger.onNext(create);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.observables.AsyncOnSubscribe$UnicastSubject */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class UnicastSubject<T> extends Observable<T> implements Observer<T> {
        private State<T> state;

        public static <T> UnicastSubject<T> create() {
            return new UnicastSubject<>(new State());
        }

        protected UnicastSubject(State<T> state) {
            super(state);
            this.state = state;
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.state.subscriber.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.state.subscriber.onError(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            this.state.subscriber.onNext(t);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: rx.observables.AsyncOnSubscribe$UnicastSubject$State */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static final class State<T> implements Observable.OnSubscribe<T> {
            Subscriber<? super T> subscriber;

            State() {
            }

            @Override // p475rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super T> subscriber) {
                synchronized (this) {
                    if (this.subscriber == null) {
                        this.subscriber = subscriber;
                    } else {
                        subscriber.onError(new IllegalStateException("There can be only one subscriber"));
                    }
                }
            }
        }
    }
}
