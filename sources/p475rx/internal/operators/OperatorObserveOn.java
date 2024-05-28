package p475rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.exceptions.MissingBackpressureException;
import p475rx.functions.Action0;
import p475rx.internal.schedulers.ImmediateScheduler;
import p475rx.internal.schedulers.TrampolineScheduler;
import p475rx.internal.util.RxRingBuffer;
import p475rx.internal.util.atomic.SpscAtomicArrayQueue;
import p475rx.internal.util.unsafe.SpscArrayQueue;
import p475rx.internal.util.unsafe.UnsafeAccess;
import p475rx.plugins.RxJavaPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorObserveOn */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z) {
        this(scheduler, z, RxRingBuffer.SIZE);
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z, int i) {
        this.scheduler = scheduler;
        this.delayError = z;
        this.bufferSize = i <= 0 ? RxRingBuffer.SIZE : i;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler scheduler = this.scheduler;
        if ((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler)) {
            return subscriber;
        }
        ObserveOnSubscriber observeOnSubscriber = new ObserveOnSubscriber(scheduler, subscriber, this.delayError, this.bufferSize);
        observeOnSubscriber.init();
        return observeOnSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorObserveOn$ObserveOnSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong counter = new AtomicLong();

        /* renamed from: on */
        final NotificationLite<T> f27596on = NotificationLite.instance();

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> subscriber, boolean z, int i) {
            this.child = subscriber;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = z;
            i = i <= 0 ? RxRingBuffer.SIZE : i;
            this.limit = i - (i >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(i);
            } else {
                this.queue = new SpscAtomicArrayQueue(i);
            }
            request(i);
        }

        void init() {
            Subscriber<? super T> subscriber = this.child;
            subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorObserveOn.ObserveOnSubscriber.1
                @Override // p475rx.Producer
                public void request(long j) {
                    if (j > 0) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, j);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            subscriber.add(this.recursiveScheduler);
            subscriber.add(this);
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            if (!this.queue.offer(this.f27596on.next(t))) {
                onError(new MissingBackpressureException());
            } else {
                schedule();
            }
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            if (isUnsubscribed() || this.finished) {
                return;
            }
            this.finished = true;
            schedule();
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            if (isUnsubscribed() || this.finished) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.error = th;
            this.finished = true;
            schedule();
        }

        protected void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this);
            }
        }

        @Override // p475rx.functions.Action0
        public void call() {
            int i;
            long j = this.emitted;
            Queue<Object> queue = this.queue;
            Subscriber<? super T> subscriber = this.child;
            NotificationLite<T> notificationLite = this.f27596on;
            long j2 = j;
            long j3 = 1;
            do {
                long j4 = this.requested.get();
                while (true) {
                    i = (j4 > j2 ? 1 : (j4 == j2 ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.finished;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (checkTerminated(z, z2, subscriber, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext((T) notificationLite.getValue(poll));
                    j2++;
                    if (j2 == this.limit) {
                        j4 = BackpressureUtils.produced(this.requested, j2);
                        request(j2);
                        j2 = 0;
                    }
                }
                if (i == 0 && checkTerminated(this.finished, queue.isEmpty(), subscriber, queue)) {
                    return;
                }
                this.emitted = j2;
                j3 = this.counter.addAndGet(-j3);
            } while (j3 != 0);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, Queue<Object> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            } else if (z) {
                if (this.delayError) {
                    if (z2) {
                        Throwable th = this.error;
                        try {
                            if (th != null) {
                                subscriber.onError(th);
                            } else {
                                subscriber.onCompleted();
                            }
                            return false;
                        } finally {
                        }
                    }
                    return false;
                }
                Throwable th2 = this.error;
                if (th2 != null) {
                    queue.clear();
                    try {
                        subscriber.onError(th2);
                        return true;
                    } finally {
                    }
                } else if (z2) {
                    try {
                        subscriber.onCompleted();
                        return true;
                    } finally {
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
