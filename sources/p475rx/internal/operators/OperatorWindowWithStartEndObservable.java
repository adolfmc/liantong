package p475rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Subscriber;
import p475rx.functions.Func1;
import p475rx.observers.SerializedObserver;
import p475rx.observers.SerializedSubscriber;
import p475rx.subscriptions.CompositeSubscription;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorWindowWithStartEndObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorWindowWithStartEndObservable<T, U, V> implements Observable.Operator<Observable<T>, T> {
    final Func1<? super U, ? extends Observable<? extends V>> windowClosingSelector;
    final Observable<? extends U> windowOpenings;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorWindowWithStartEndObservable(Observable<? extends U> observable, Func1<? super U, ? extends Observable<? extends V>> func1) {
        this.windowOpenings = observable;
        this.windowClosingSelector = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        subscriber.add(compositeSubscription);
        final SourceSubscriber sourceSubscriber = new SourceSubscriber(subscriber, compositeSubscription);
        Subscriber<U> subscriber2 = new Subscriber<U>() { // from class: rx.internal.operators.OperatorWindowWithStartEndObservable.1
            @Override // p475rx.Subscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // p475rx.Observer
            public void onNext(U u) {
                sourceSubscriber.beginWindow(u);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                sourceSubscriber.onError(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                sourceSubscriber.onCompleted();
            }
        };
        compositeSubscription.add(sourceSubscriber);
        compositeSubscription.add(subscriber2);
        this.windowOpenings.unsafeSubscribe(subscriber2);
        return sourceSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithStartEndObservable$SerializedSubject */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class SerializedSubject<T> {
        final Observer<T> consumer;
        final Observable<T> producer;

        public SerializedSubject(Observer<T> observer, Observable<T> observable) {
            this.consumer = new SerializedObserver(observer);
            this.producer = observable;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorWindowWithStartEndObservable$SourceSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final class SourceSubscriber extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        final CompositeSubscription csub;
        boolean done;
        final Object guard = new Object();
        final List<SerializedSubject<T>> chunks = new LinkedList();

        public SourceSubscriber(Subscriber<? super Observable<T>> subscriber, CompositeSubscription compositeSubscription) {
            this.child = new SerializedSubscriber(subscriber);
            this.csub = compositeSubscription;
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
                for (SerializedSubject serializedSubject : new ArrayList(this.chunks)) {
                    serializedSubject.consumer.onNext(t);
                }
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            try {
                synchronized (this.guard) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    ArrayList<SerializedSubject> arrayList = new ArrayList(this.chunks);
                    this.chunks.clear();
                    for (SerializedSubject serializedSubject : arrayList) {
                        serializedSubject.consumer.onError(th);
                    }
                    this.child.onError(th);
                }
            } finally {
                this.csub.unsubscribe();
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            try {
                synchronized (this.guard) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    ArrayList<SerializedSubject> arrayList = new ArrayList(this.chunks);
                    this.chunks.clear();
                    for (SerializedSubject serializedSubject : arrayList) {
                        serializedSubject.consumer.onCompleted();
                    }
                    this.child.onCompleted();
                }
            } finally {
                this.csub.unsubscribe();
            }
        }

        void beginWindow(U u) {
            final SerializedSubject<T> createSerializedSubject = createSerializedSubject();
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                this.chunks.add(createSerializedSubject);
                this.child.onNext(createSerializedSubject.producer);
                try {
                    Observable<? extends V> call = OperatorWindowWithStartEndObservable.this.windowClosingSelector.call(u);
                    Subscriber<V> subscriber = new Subscriber<V>() { // from class: rx.internal.operators.OperatorWindowWithStartEndObservable.SourceSubscriber.1
                        boolean once = true;

                        @Override // p475rx.Observer
                        public void onError(Throwable th) {
                        }

                        @Override // p475rx.Observer
                        public void onNext(V v) {
                            onCompleted();
                        }

                        @Override // p475rx.Observer
                        public void onCompleted() {
                            if (this.once) {
                                this.once = false;
                                SourceSubscriber.this.endWindow(createSerializedSubject);
                                SourceSubscriber.this.csub.remove(this);
                            }
                        }
                    };
                    this.csub.add(subscriber);
                    call.unsafeSubscribe(subscriber);
                } catch (Throwable th) {
                    onError(th);
                }
            }
        }

        void endWindow(SerializedSubject<T> serializedSubject) {
            boolean z;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                Iterator<SerializedSubject<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == serializedSubject) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    serializedSubject.consumer.onCompleted();
                }
            }
        }

        SerializedSubject<T> createSerializedSubject() {
            UnicastSubject create = UnicastSubject.create();
            return new SerializedSubject<>(create, create);
        }
    }
}
