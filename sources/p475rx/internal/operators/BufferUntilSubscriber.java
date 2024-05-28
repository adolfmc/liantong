package p475rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Subscriber;
import p475rx.functions.Action0;
import p475rx.subjects.Subject;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.BufferUntilSubscriber */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BufferUntilSubscriber<T> extends Subject<T, T> {
    static final Observer EMPTY_OBSERVER = new Observer() { // from class: rx.internal.operators.BufferUntilSubscriber.1
        @Override // p475rx.Observer
        public void onCompleted() {
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
        }

        @Override // p475rx.Observer
        public void onNext(Object obj) {
        }
    };
    private boolean forward;
    final State<T> state;

    public static <T> BufferUntilSubscriber<T> create() {
        return new BufferUntilSubscriber<>(new State());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.BufferUntilSubscriber$State */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class State<T> extends AtomicReference<Observer<? super T>> {
        final Object guard = new Object();
        boolean emitting = false;
        final ConcurrentLinkedQueue<Object> buffer = new ConcurrentLinkedQueue<>();

        /* renamed from: nl */
        final NotificationLite<T> f27576nl = NotificationLite.instance();

        State() {
        }

        boolean casObserverRef(Observer<? super T> observer, Observer<? super T> observer2) {
            return compareAndSet(observer, observer2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.BufferUntilSubscriber$OnSubscribeAction */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class OnSubscribeAction<T> implements Observable.OnSubscribe<T> {
        final State<T> state;

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public OnSubscribeAction(State<T> state) {
            this.state = state;
        }

        public void call(Subscriber<? super T> subscriber) {
            boolean z;
            if (this.state.casObserverRef(null, subscriber)) {
                subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.BufferUntilSubscriber.OnSubscribeAction.1
                    @Override // p475rx.functions.Action0
                    public void call() {
                        OnSubscribeAction.this.state.set(BufferUntilSubscriber.EMPTY_OBSERVER);
                    }
                }));
                synchronized (this.state.guard) {
                    z = true;
                    if (this.state.emitting) {
                        z = false;
                    } else {
                        this.state.emitting = true;
                    }
                }
                if (!z) {
                    return;
                }
                NotificationLite instance = NotificationLite.instance();
                while (true) {
                    Object poll = this.state.buffer.poll();
                    if (poll != null) {
                        instance.accept(this.state.get(), poll);
                    } else {
                        synchronized (this.state.guard) {
                            if (this.state.buffer.isEmpty()) {
                                this.state.emitting = false;
                                return;
                            }
                        }
                    }
                }
            } else {
                subscriber.onError(new IllegalStateException("Only one subscriber allowed!"));
            }
        }
    }

    private BufferUntilSubscriber(State<T> state) {
        super(new OnSubscribeAction(state));
        this.forward = false;
        this.state = state;
    }

    private void emit(Object obj) {
        synchronized (this.state.guard) {
            this.state.buffer.add(obj);
            if (this.state.get() != null && !this.state.emitting) {
                this.forward = true;
                this.state.emitting = true;
            }
        }
        if (!this.forward) {
            return;
        }
        while (true) {
            Object poll = this.state.buffer.poll();
            if (poll == null) {
                return;
            }
            this.state.f27576nl.accept(this.state.get(), poll);
        }
    }

    @Override // p475rx.Observer
    public void onCompleted() {
        if (this.forward) {
            this.state.get().onCompleted();
        } else {
            emit(this.state.f27576nl.completed());
        }
    }

    @Override // p475rx.Observer
    public void onError(Throwable th) {
        if (this.forward) {
            this.state.get().onError(th);
        } else {
            emit(this.state.f27576nl.error(th));
        }
    }

    @Override // p475rx.Observer
    public void onNext(T t) {
        if (this.forward) {
            this.state.get().onNext(t);
        } else {
            emit(this.state.f27576nl.next(t));
        }
    }

    @Override // p475rx.subjects.Subject
    public boolean hasObservers() {
        boolean z;
        synchronized (this.state.guard) {
            z = this.state.get() != null;
        }
        return z;
    }
}
