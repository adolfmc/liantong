package p475rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Subscriber;
import p475rx.exceptions.Exceptions;
import p475rx.exceptions.MissingBackpressureException;
import p475rx.functions.Func1;
import p475rx.internal.operators.OnSubscribeFromIterable;
import p475rx.internal.util.ExceptionsUtils;
import p475rx.internal.util.RxJavaPluginUtils;
import p475rx.internal.util.RxRingBuffer;
import p475rx.internal.util.ScalarSynchronousObservable;
import p475rx.internal.util.atomic.SpscAtomicArrayQueue;
import p475rx.internal.util.atomic.SpscLinkedArrayQueue;
import p475rx.internal.util.unsafe.SpscArrayQueue;
import p475rx.internal.util.unsafe.UnsafeAccess;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OnSubscribeFlattenIterable */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OnSubscribeFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    @Override // p475rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    protected OnSubscribeFlattenIterable(Observable<? extends T> observable, Func1<? super T, ? extends Iterable<? extends R>> func1, int i) {
        this.source = observable;
        this.mapper = func1;
        this.prefetch = i;
    }

    public void call(Subscriber<? super R> subscriber) {
        final FlattenIterableSubscriber flattenIterableSubscriber = new FlattenIterableSubscriber(subscriber, this.mapper, this.prefetch);
        subscriber.add(flattenIterableSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeFlattenIterable.1
            @Override // p475rx.Producer
            public void request(long j) {
                flattenIterableSubscriber.requestMore(j);
            }
        });
        this.source.unsafeSubscribe(flattenIterableSubscriber);
    }

    public static <T, R> Observable<R> createFrom(Observable<? extends T> observable, Func1<? super T, ? extends Iterable<? extends R>> func1, int i) {
        if (observable instanceof ScalarSynchronousObservable) {
            return Observable.create(new OnSubscribeScalarFlattenIterable(((ScalarSynchronousObservable) observable).get(), func1));
        }
        return Observable.create(new OnSubscribeFlattenIterable(observable, func1, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeFlattenIterable$FlattenIterableSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class FlattenIterableSubscriber<T, R> extends Subscriber<T> {
        Iterator<? extends R> active;
        final Subscriber<? super R> actual;
        volatile boolean done;
        final long limit;
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        long produced;
        final Queue<Object> queue;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();

        /* renamed from: nl */
        final NotificationLite<T> f27584nl = NotificationLite.instance();

        public FlattenIterableSubscriber(Subscriber<? super R> subscriber, Func1<? super T, ? extends Iterable<? extends R>> func1, int i) {
            this.actual = subscriber;
            this.mapper = func1;
            if (i == Integer.MAX_VALUE) {
                this.limit = Long.MAX_VALUE;
                this.queue = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
            } else {
                this.limit = i - (i >> 2);
                if (UnsafeAccess.isUnsafeAvailable()) {
                    this.queue = new SpscArrayQueue(i);
                } else {
                    this.queue = new SpscAtomicArrayQueue(i);
                }
            }
            request(i);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(this.f27584nl.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (ExceptionsUtils.addThrowable(this.error, th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPluginUtils.handleException(th);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void requestMore(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                drain();
            } else if (i >= 0) {
            } else {
                throw new IllegalStateException("n >= 0 required but it was " + j);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x00dd A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0010 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 222
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p475rx.internal.operators.OnSubscribeFlattenIterable.FlattenIterableSubscriber.drain():void");
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                this.active = null;
                return true;
            } else if (z) {
                if (this.error.get() == null) {
                    if (z2) {
                        subscriber.onCompleted();
                        return true;
                    }
                    return false;
                }
                Throwable terminate = ExceptionsUtils.terminate(this.error);
                unsubscribe();
                queue.clear();
                this.active = null;
                subscriber.onError(terminate);
                return true;
            } else {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OnSubscribeFlattenIterable$OnSubscribeScalarFlattenIterable */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class OnSubscribeScalarFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final T value;

        @Override // p475rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public OnSubscribeScalarFlattenIterable(T t, Func1<? super T, ? extends Iterable<? extends R>> func1) {
            this.value = t;
            this.mapper = func1;
        }

        public void call(Subscriber<? super R> subscriber) {
            try {
                Iterator<? extends R> it = this.mapper.call((T) this.value).iterator();
                if (!it.hasNext()) {
                    subscriber.onCompleted();
                } else {
                    subscriber.setProducer(new OnSubscribeFromIterable.IterableProducer(subscriber, it));
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, this.value);
            }
        }
    }
}
