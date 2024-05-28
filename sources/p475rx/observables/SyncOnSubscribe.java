package p475rx.observables;

import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.annotations.Beta;
import p475rx.exceptions.Exceptions;
import p475rx.functions.Action0;
import p475rx.functions.Action1;
import p475rx.functions.Action2;
import p475rx.functions.Func0;
import p475rx.functions.Func2;
import p475rx.internal.operators.BackpressureUtils;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Beta
/* renamed from: rx.observables.SyncOnSubscribe */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SyncOnSubscribe<S, T> implements Observable.OnSubscribe<T> {
    protected abstract S generateState();

    protected abstract S next(S s, Observer<? super T> observer);

    protected void onUnsubscribe(S s) {
    }

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public final void call(Subscriber<? super T> subscriber) {
        try {
            SubscriptionProducer subscriptionProducer = new SubscriptionProducer(subscriber, this, generateState());
            subscriber.add(subscriptionProducer);
            subscriber.setProducer(subscriptionProducer);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, final Action2<? super S, ? super Observer<? super T>> action2) {
        return new SyncOnSubscribeImpl(func0, new Func2<S, Observer<? super T>, S>() { // from class: rx.observables.SyncOnSubscribe.1
            @Override // p475rx.functions.Func2
            public /* bridge */ /* synthetic */ Object call(Object obj, Object obj2) {
                return call((C140781) obj, (Observer) ((Observer) obj2));
            }

            public S call(S s, Observer<? super T> observer) {
                Action2.this.call(s, observer);
                return s;
            }
        });
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, final Action2<? super S, ? super Observer<? super T>> action2, Action1<? super S> action1) {
        return new SyncOnSubscribeImpl(func0, new Func2<S, Observer<? super T>, S>() { // from class: rx.observables.SyncOnSubscribe.2
            @Override // p475rx.functions.Func2
            public /* bridge */ /* synthetic */ Object call(Object obj, Object obj2) {
                return call((C140792) obj, (Observer) ((Observer) obj2));
            }

            public S call(S s, Observer<? super T> observer) {
                Action2.this.call(s, observer);
                return s;
            }
        }, action1);
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2, Action1<? super S> action1) {
        return new SyncOnSubscribeImpl(func0, func2, action1);
    }

    @Beta
    public static <S, T> SyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
        return new SyncOnSubscribeImpl(func0, func2);
    }

    @Beta
    public static <T> SyncOnSubscribe<Void, T> createStateless(final Action1<? super Observer<? super T>> action1) {
        return new SyncOnSubscribeImpl(new Func2<Void, Observer<? super T>, Void>() { // from class: rx.observables.SyncOnSubscribe.3
            @Override // p475rx.functions.Func2
            public /* bridge */ /* synthetic */ Void call(Void r1, Object obj) {
                return call(r1, (Observer) ((Observer) obj));
            }

            public Void call(Void r2, Observer<? super T> observer) {
                Action1.this.call(observer);
                return r2;
            }
        });
    }

    @Beta
    public static <T> SyncOnSubscribe<Void, T> createStateless(final Action1<? super Observer<? super T>> action1, final Action0 action0) {
        return new SyncOnSubscribeImpl(new Func2<Void, Observer<? super T>, Void>() { // from class: rx.observables.SyncOnSubscribe.4
            @Override // p475rx.functions.Func2
            public /* bridge */ /* synthetic */ Void call(Void r1, Object obj) {
                return call(r1, (Observer) ((Observer) obj));
            }

            public Void call(Void r1, Observer<? super T> observer) {
                Action1.this.call(observer);
                return null;
            }
        }, new Action1<Void>() { // from class: rx.observables.SyncOnSubscribe.5
            @Override // p475rx.functions.Action1
            public void call(Void r1) {
                Action0.this.call();
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.observables.SyncOnSubscribe$SyncOnSubscribeImpl */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class SyncOnSubscribeImpl<S, T> extends SyncOnSubscribe<S, T> {
        private final Func0<? extends S> generator;
        private final Func2<? super S, ? super Observer<? super T>, ? extends S> next;
        private final Action1<? super S> onUnsubscribe;

        @Override // p475rx.observables.SyncOnSubscribe, p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            super.call((Subscriber) ((Subscriber) obj));
        }

        SyncOnSubscribeImpl(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2, Action1<? super S> action1) {
            this.generator = func0;
            this.next = func2;
            this.onUnsubscribe = action1;
        }

        public SyncOnSubscribeImpl(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
            this(func0, func2, null);
        }

        public SyncOnSubscribeImpl(Func2<S, Observer<? super T>, S> func2, Action1<? super S> action1) {
            this(null, func2, action1);
        }

        public SyncOnSubscribeImpl(Func2<S, Observer<? super T>, S> func2) {
            this(null, func2, null);
        }

        @Override // p475rx.observables.SyncOnSubscribe
        protected S generateState() {
            Func0<? extends S> func0 = this.generator;
            if (func0 == null) {
                return null;
            }
            return func0.call();
        }

        @Override // p475rx.observables.SyncOnSubscribe
        protected S next(S s, Observer<? super T> observer) {
            return this.next.call(s, observer);
        }

        @Override // p475rx.observables.SyncOnSubscribe
        protected void onUnsubscribe(S s) {
            Action1<? super S> action1 = this.onUnsubscribe;
            if (action1 != null) {
                action1.call(s);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.observables.SyncOnSubscribe$SubscriptionProducer */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class SubscriptionProducer<S, T> extends AtomicLong implements Observer<T>, Producer, Subscription {
        private static final long serialVersionUID = -3736864024352728072L;
        private final Subscriber<? super T> actualSubscriber;
        private boolean hasTerminated;
        private boolean onNextCalled;
        private final SyncOnSubscribe<S, T> parent;
        private S state;

        SubscriptionProducer(Subscriber<? super T> subscriber, SyncOnSubscribe<S, T> syncOnSubscribe, S s) {
            this.actualSubscriber = subscriber;
            this.parent = syncOnSubscribe;
            this.state = s;
        }

        @Override // p475rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        @Override // p475rx.Subscription
        public void unsubscribe() {
            long j;
            do {
                j = get();
                if (compareAndSet(0L, -1L)) {
                    doUnsubscribe();
                    return;
                }
            } while (!compareAndSet(j, -2L));
        }

        private boolean tryUnsubscribe() {
            if (this.hasTerminated || get() < -1) {
                set(-1L);
                doUnsubscribe();
                return true;
            }
            return false;
        }

        private void doUnsubscribe() {
            try {
                this.parent.onUnsubscribe(this.state);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        }

        @Override // p475rx.Producer
        public void request(long j) {
            if (j <= 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
                return;
            }
            if (j == Long.MAX_VALUE) {
                fastpath();
            } else {
                slowPath(j);
            }
        }

        private void fastpath() {
            SyncOnSubscribe<S, T> syncOnSubscribe = this.parent;
            Subscriber<? super T> subscriber = this.actualSubscriber;
            do {
                try {
                    this.onNextCalled = false;
                    nextIteration(syncOnSubscribe);
                } catch (Throwable th) {
                    handleThrownError(subscriber, th);
                    return;
                }
            } while (!tryUnsubscribe());
        }

        private void handleThrownError(Subscriber<? super T> subscriber, Throwable th) {
            if (this.hasTerminated) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.hasTerminated = true;
            subscriber.onError(th);
            unsubscribe();
        }

        private void slowPath(long j) {
            SyncOnSubscribe<S, T> syncOnSubscribe = this.parent;
            Subscriber<? super T> subscriber = this.actualSubscriber;
            do {
                long j2 = j;
                do {
                    try {
                        this.onNextCalled = false;
                        nextIteration(syncOnSubscribe);
                        if (tryUnsubscribe()) {
                            return;
                        }
                        if (this.onNextCalled) {
                            j2--;
                        }
                    } catch (Throwable th) {
                        handleThrownError(subscriber, th);
                        return;
                    }
                } while (j2 != 0);
                j = addAndGet(-j);
            } while (j > 0);
            tryUnsubscribe();
        }

        private void nextIteration(SyncOnSubscribe<S, T> syncOnSubscribe) {
            this.state = syncOnSubscribe.next(this.state, this);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            if (this.actualSubscriber.isUnsubscribed()) {
                return;
            }
            this.actualSubscriber.onCompleted();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (this.hasTerminated) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.hasTerminated = true;
            if (this.actualSubscriber.isUnsubscribed()) {
                return;
            }
            this.actualSubscriber.onError(th);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (this.onNextCalled) {
                throw new IllegalStateException("onNext called multiple times!");
            }
            this.onNextCalled = true;
            this.actualSubscriber.onNext(t);
        }
    }
}
