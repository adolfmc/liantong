package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Subscriber;
import p475rx.functions.Func0;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.SerialSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorWindowWithObservableFactory */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorWindowWithObservableFactory<T, U> implements Observable.Operator<Observable<T>, T> {
    static final Object NEXT_SUBJECT = new Object();

    /* renamed from: nl */
    static final NotificationLite<Object> f27609nl = NotificationLite.instance();
    final Func0<? extends Observable<? extends U>> otherFactory;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorWindowWithObservableFactory(Func0<? extends Observable<? extends U>> func0) {
        this.otherFactory = func0;
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        SourceSubscriber sourceSubscriber = new SourceSubscriber(subscriber, this.otherFactory);
        subscriber.add(sourceSubscriber);
        sourceSubscriber.replaceWindow();
        return sourceSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithObservableFactory$SourceSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SourceSubscriber<T, U> extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        Observer<T> consumer;
        boolean emitting;
        final Func0<? extends Observable<? extends U>> otherFactory;
        Observable<T> producer;
        List<Object> queue;
        final Object guard = new Object();
        final SerialSubscription ssub = new SerialSubscription();

        public SourceSubscriber(Subscriber<? super Observable<T>> subscriber, Func0<? extends Observable<? extends U>> func0) {
            this.child = new SerializedSubscriber(subscriber);
            this.otherFactory = func0;
            add(this.ssub);
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(t);
                    return;
                }
                List<Object> list = this.queue;
                this.queue = null;
                boolean z = true;
                this.emitting = true;
                boolean z2 = true;
                while (true) {
                    try {
                        drain(list);
                        if (z2) {
                            emitValue(t);
                            z2 = false;
                        }
                        try {
                            synchronized (this.guard) {
                                try {
                                    List<Object> list2 = this.queue;
                                    this.queue = null;
                                    if (list2 == null) {
                                        this.emitting = false;
                                        return;
                                    } else if (this.child.isUnsubscribed()) {
                                        synchronized (this.guard) {
                                            this.emitting = false;
                                        }
                                        return;
                                    } else {
                                        list = list2;
                                    }
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
                    } catch (Throwable th4) {
                        th = th4;
                        z = false;
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain(List<Object> list) {
            if (list == null) {
                return;
            }
            for (Object obj : list) {
                if (obj == OperatorWindowWithObservableFactory.NEXT_SUBJECT) {
                    replaceSubject();
                } else if (OperatorWindowWithObservableFactory.f27609nl.isError(obj)) {
                    error(OperatorWindowWithObservableFactory.f27609nl.getError(obj));
                    return;
                } else if (OperatorWindowWithObservableFactory.f27609nl.isCompleted(obj)) {
                    complete();
                    return;
                } else {
                    emitValue(obj);
                }
            }
        }

        void replaceSubject() {
            Observer<T> observer = this.consumer;
            if (observer != null) {
                observer.onCompleted();
            }
            createNewWindow();
            this.child.onNext(this.producer);
        }

        void createNewWindow() {
            UnicastSubject create = UnicastSubject.create();
            this.consumer = create;
            this.producer = create;
            try {
                Observable<? extends U> call = this.otherFactory.call();
                BoundarySubscriber boundarySubscriber = new BoundarySubscriber(this.child, this);
                this.ssub.set(boundarySubscriber);
                call.unsafeSubscribe(boundarySubscriber);
            } catch (Throwable th) {
                this.child.onError(th);
                unsubscribe();
            }
        }

        void emitValue(T t) {
            Observer<T> observer = this.consumer;
            if (observer != null) {
                observer.onNext(t);
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            synchronized (this.guard) {
                if (this.emitting) {
                    this.queue = Collections.singletonList(OperatorWindowWithObservableFactory.f27609nl.error(th));
                    return;
                }
                this.queue = null;
                this.emitting = true;
                error(th);
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(OperatorWindowWithObservableFactory.f27609nl.completed());
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

        void replaceWindow() {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(OperatorWindowWithObservableFactory.NEXT_SUBJECT);
                    return;
                }
                List<Object> list = this.queue;
                this.queue = null;
                boolean z = true;
                this.emitting = true;
                boolean z2 = true;
                while (true) {
                    try {
                        drain(list);
                        if (z2) {
                            replaceSubject();
                            z2 = false;
                        }
                        try {
                            synchronized (this.guard) {
                                try {
                                    List<Object> list2 = this.queue;
                                    this.queue = null;
                                    if (list2 == null) {
                                        this.emitting = false;
                                        return;
                                    } else if (this.child.isUnsubscribed()) {
                                        synchronized (this.guard) {
                                            this.emitting = false;
                                        }
                                        return;
                                    } else {
                                        list = list2;
                                    }
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
                    } catch (Throwable th4) {
                        th = th4;
                        z = false;
                    }
                }
            }
        }

        void complete() {
            Observer<T> observer = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (observer != null) {
                observer.onCompleted();
            }
            this.child.onCompleted();
            unsubscribe();
        }

        void error(Throwable th) {
            Observer<T> observer = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (observer != null) {
                observer.onError(th);
            }
            this.child.onError(th);
            unsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithObservableFactory$BoundarySubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class BoundarySubscriber<T, U> extends Subscriber<U> {
        boolean done;
        final SourceSubscriber<T, U> sub;

        public BoundarySubscriber(Subscriber<?> subscriber, SourceSubscriber<T, U> sourceSubscriber) {
            this.sub = sourceSubscriber;
        }

        @Override // p475rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // p475rx.Observer
        public void onNext(U u) {
            if (this.done) {
                return;
            }
            this.done = true;
            this.sub.replaceWindow();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.sub.onError(th);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.sub.onCompleted();
        }
    }
}
