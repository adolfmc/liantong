package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class FlowableSkipLastTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    final int bufferSize;
    final boolean delayError;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public FlowableSkipLastTimed(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, int i, boolean z) {
        super(flowable);
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.bufferSize = i;
        this.delayError = z;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new SkipLastTimedSubscriber(subscriber, this.time, this.unit, this.scheduler, this.bufferSize, this.delayError));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static final class SkipLastTimedSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -5677354903406201275L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested = new AtomicLong();

        /* renamed from: s */
        Subscription f24535s;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        SkipLastTimedSubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler, int i, boolean z) {
            this.actual = subscriber;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.delayError = z;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.f24535s, subscription)) {
                this.f24535s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.queue.offer(Long.valueOf(this.scheduler.now(this.unit)), t);
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.f24535s.cancel();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            long j;
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = this.actual;
            SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
            boolean z = this.delayError;
            TimeUnit timeUnit = this.unit;
            Scheduler scheduler = this.scheduler;
            long j2 = this.time;
            int i = 1;
            do {
                long j3 = this.requested.get();
                long j4 = 0;
                while (true) {
                    if (j4 == j3) {
                        j = 0;
                        break;
                    }
                    boolean z2 = this.done;
                    Long l = (Long) spscLinkedArrayQueue.peek();
                    boolean z3 = l == null;
                    boolean z4 = (z3 || l.longValue() <= scheduler.now(timeUnit) - j2) ? z3 : true;
                    if (checkTerminated(z2, z4, subscriber, z)) {
                        return;
                    }
                    if (z4) {
                        j = 0;
                        break;
                    }
                    spscLinkedArrayQueue.poll();
                    subscriber.onNext(spscLinkedArrayQueue.poll());
                    j4++;
                }
                if (j4 != j) {
                    BackpressureHelper.produced(this.requested, j4);
                }
                i = addAndGet(-i);
            } while (i != 0);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onComplete();
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
                    subscriber.onComplete();
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
