package p475rx.observables;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Observable;
import p475rx.Observer;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.Subscription;
import p475rx.annotations.Experimental;
import p475rx.exceptions.OnErrorNotImplementedException;
import p475rx.functions.Action0;
import p475rx.functions.Action1;
import p475rx.functions.Actions;
import p475rx.functions.Func1;
import p475rx.internal.operators.BlockingOperatorLatest;
import p475rx.internal.operators.BlockingOperatorMostRecent;
import p475rx.internal.operators.BlockingOperatorNext;
import p475rx.internal.operators.BlockingOperatorToFuture;
import p475rx.internal.operators.BlockingOperatorToIterator;
import p475rx.internal.operators.NotificationLite;
import p475rx.internal.util.BlockingUtils;
import p475rx.internal.util.UtilityFunctions;
import p475rx.subscriptions.Subscriptions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.observables.BlockingObservable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class BlockingObservable<T> {
    static final Object ON_START = new Object();
    static final Object SET_PRODUCER = new Object();
    static final Object UNSUBSCRIBE = new Object();

    /* renamed from: o */
    private final Observable<? extends T> f27638o;

    private BlockingObservable(Observable<? extends T> observable) {
        this.f27638o = observable;
    }

    public static <T> BlockingObservable<T> from(Observable<? extends T> observable) {
        return new BlockingObservable<>(observable);
    }

    public void forEach(final Action1<? super T> action1) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        BlockingUtils.awaitForComplete(countDownLatch, this.f27638o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.1
            @Override // p475rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                atomicReference.set(th);
                countDownLatch.countDown();
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                action1.call(t);
            }
        }));
        if (atomicReference.get() != null) {
            if (atomicReference.get() instanceof RuntimeException) {
                throw ((RuntimeException) atomicReference.get());
            }
            throw new RuntimeException((Throwable) atomicReference.get());
        }
    }

    public Iterator<T> getIterator() {
        return BlockingOperatorToIterator.toIterator(this.f27638o);
    }

    public T first() {
        return blockForSingle(this.f27638o.first());
    }

    public T first(Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f27638o.first(func1));
    }

    public T firstOrDefault(T t) {
        return blockForSingle(this.f27638o.map(UtilityFunctions.identity()).firstOrDefault(t));
    }

    public T firstOrDefault(T t, Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f27638o.filter(func1).map(UtilityFunctions.identity()).firstOrDefault(t));
    }

    public T last() {
        return blockForSingle(this.f27638o.last());
    }

    public T last(Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f27638o.last(func1));
    }

    public T lastOrDefault(T t) {
        return blockForSingle(this.f27638o.map(UtilityFunctions.identity()).lastOrDefault(t));
    }

    public T lastOrDefault(T t, Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f27638o.filter(func1).map(UtilityFunctions.identity()).lastOrDefault(t));
    }

    public Iterable<T> mostRecent(T t) {
        return BlockingOperatorMostRecent.mostRecent(this.f27638o, t);
    }

    public Iterable<T> next() {
        return BlockingOperatorNext.next(this.f27638o);
    }

    public Iterable<T> latest() {
        return BlockingOperatorLatest.latest(this.f27638o);
    }

    public T single() {
        return blockForSingle(this.f27638o.single());
    }

    public T single(Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f27638o.single(func1));
    }

    public T singleOrDefault(T t) {
        return blockForSingle(this.f27638o.map(UtilityFunctions.identity()).singleOrDefault(t));
    }

    public T singleOrDefault(T t, Func1<? super T, Boolean> func1) {
        return blockForSingle(this.f27638o.filter(func1).map(UtilityFunctions.identity()).singleOrDefault(t));
    }

    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.f27638o);
    }

    public Iterable<T> toIterable() {
        return new Iterable<T>() { // from class: rx.observables.BlockingObservable.2
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return BlockingObservable.this.getIterator();
            }
        };
    }

    private T blockForSingle(Observable<? extends T> observable) {
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, observable.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.3
            @Override // p475rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }

            @Override // p475rx.Observer
            public void onNext(T t) {
                atomicReference.set(t);
            }
        }));
        if (atomicReference2.get() != null) {
            if (atomicReference2.get() instanceof RuntimeException) {
                throw ((RuntimeException) atomicReference2.get());
            }
            throw new RuntimeException((Throwable) atomicReference2.get());
        }
        return (T) atomicReference.get();
    }

    @Experimental
    public void subscribe() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] thArr = {null};
        BlockingUtils.awaitForComplete(countDownLatch, this.f27638o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.4
            @Override // p475rx.Observer
            public void onNext(T t) {
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                thArr[0] = th;
                countDownLatch.countDown();
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }
        }));
        Throwable th = thArr[0];
        if (th != null) {
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw new RuntimeException(th);
        }
    }

    @Experimental
    public void subscribe(Observer<? super T> observer) {
        Object poll;
        final NotificationLite instance = NotificationLite.instance();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Subscription subscribe = this.f27638o.subscribe((Subscriber<? super Object>) new Subscriber<T>() { // from class: rx.observables.BlockingObservable.5
            @Override // p475rx.Observer
            public void onNext(T t) {
                linkedBlockingQueue.offer(instance.next(t));
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                linkedBlockingQueue.offer(instance.error(th));
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                linkedBlockingQueue.offer(instance.completed());
            }
        });
        do {
            try {
                poll = linkedBlockingQueue.poll();
                if (poll == null) {
                    poll = linkedBlockingQueue.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                observer.onError(e);
                return;
            } finally {
                subscribe.unsubscribe();
            }
        } while (!instance.accept(observer, poll));
    }

    @Experimental
    public void subscribe(Subscriber<? super T> subscriber) {
        final NotificationLite instance = NotificationLite.instance();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final Producer[] producerArr = {null};
        Subscriber<T> subscriber2 = new Subscriber<T>() { // from class: rx.observables.BlockingObservable.6
            @Override // p475rx.Observer
            public void onNext(T t) {
                linkedBlockingQueue.offer(instance.next(t));
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                linkedBlockingQueue.offer(instance.error(th));
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                linkedBlockingQueue.offer(instance.completed());
            }

            @Override // p475rx.Subscriber
            public void setProducer(Producer producer) {
                producerArr[0] = producer;
                linkedBlockingQueue.offer(BlockingObservable.SET_PRODUCER);
            }

            @Override // p475rx.Subscriber
            public void onStart() {
                linkedBlockingQueue.offer(BlockingObservable.ON_START);
            }
        };
        subscriber.add(subscriber2);
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.observables.BlockingObservable.7
            @Override // p475rx.functions.Action0
            public void call() {
                linkedBlockingQueue.offer(BlockingObservable.UNSUBSCRIBE);
            }
        }));
        this.f27638o.subscribe((Subscriber<? super Object>) subscriber2);
        while (!subscriber.isUnsubscribed()) {
            try {
                try {
                    Object poll = linkedBlockingQueue.poll();
                    if (poll == null) {
                        poll = linkedBlockingQueue.take();
                    }
                    if (subscriber.isUnsubscribed() || poll == UNSUBSCRIBE) {
                        break;
                    } else if (poll == ON_START) {
                        subscriber.onStart();
                    } else if (poll == SET_PRODUCER) {
                        subscriber.setProducer(producerArr[0]);
                    } else if (instance.accept(subscriber, poll)) {
                        return;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    subscriber.onError(e);
                }
            } finally {
                subscriber2.unsubscribe();
            }
        }
    }

    @Experimental
    public void subscribe(Action1<? super T> action1) {
        subscribe(action1, new Action1<Throwable>() { // from class: rx.observables.BlockingObservable.8
            @Override // p475rx.functions.Action1
            public void call(Throwable th) {
                throw new OnErrorNotImplementedException(th);
            }
        }, Actions.empty());
    }

    @Experimental
    public void subscribe(Action1<? super T> action1, Action1<? super Throwable> action12) {
        subscribe(action1, action12, Actions.empty());
    }

    @Experimental
    public void subscribe(final Action1<? super T> action1, final Action1<? super Throwable> action12, final Action0 action0) {
        subscribe(new Observer<T>() { // from class: rx.observables.BlockingObservable.9
            @Override // p475rx.Observer
            public void onNext(T t) {
                action1.call(t);
            }

            @Override // p475rx.Observer
            public void onError(Throwable th) {
                action12.call(th);
            }

            @Override // p475rx.Observer
            public void onCompleted() {
                action0.call();
            }
        });
    }
}
