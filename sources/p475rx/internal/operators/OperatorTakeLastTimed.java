package p475rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p475rx.Observable;
import p475rx.Producer;
import p475rx.Scheduler;
import p475rx.Subscriber;
import p475rx.functions.Func1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.operators.OperatorTakeLastTimed */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class OperatorTakeLastTimed<T> implements Observable.Operator<T, T> {
    final long ageMillis;
    final int count;
    final Scheduler scheduler;

    @Override // p475rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeLastTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.ageMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
        this.count = -1;
    }

    public OperatorTakeLastTimed(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.ageMillis = timeUnit.toMillis(j);
        this.scheduler = scheduler;
        this.count = i;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final TakeLastTimedSubscriber takeLastTimedSubscriber = new TakeLastTimedSubscriber(subscriber, this.count, this.ageMillis, this.scheduler);
        subscriber.add(takeLastTimedSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeLastTimed.1
            @Override // p475rx.Producer
            public void request(long j) {
                takeLastTimedSubscriber.requestMore(j);
            }
        });
        return takeLastTimedSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.operators.OperatorTakeLastTimed$TakeLastTimedSubscriber */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class TakeLastTimedSubscriber<T> extends Subscriber<T> implements Func1<Object, T> {
        final Subscriber<? super T> actual;
        final long ageMillis;
        final int count;
        final Scheduler scheduler;
        final AtomicLong requested = new AtomicLong();
        final ArrayDeque<Object> queue = new ArrayDeque<>();
        final ArrayDeque<Long> queueTimes = new ArrayDeque<>();

        /* renamed from: nl */
        final NotificationLite<T> f27607nl = NotificationLite.instance();

        public TakeLastTimedSubscriber(Subscriber<? super T> subscriber, int i, long j, Scheduler scheduler) {
            this.actual = subscriber;
            this.count = i;
            this.ageMillis = j;
            this.scheduler = scheduler;
        }

        @Override // p475rx.Observer
        public void onNext(T t) {
            if (this.count != 0) {
                long now = this.scheduler.now();
                if (this.queue.size() == this.count) {
                    this.queue.poll();
                    this.queueTimes.poll();
                }
                evictOld(now);
                this.queue.offer(this.f27607nl.next(t));
                this.queueTimes.offer(Long.valueOf(now));
            }
        }

        protected void evictOld(long j) {
            long j2 = j - this.ageMillis;
            while (true) {
                Long peek = this.queueTimes.peek();
                if (peek == null || peek.longValue() >= j2) {
                    return;
                }
                this.queue.poll();
                this.queueTimes.poll();
            }
        }

        @Override // p475rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.queueTimes.clear();
            this.actual.onError(th);
        }

        @Override // p475rx.Observer
        public void onCompleted() {
            evictOld(this.scheduler.now());
            this.queueTimes.clear();
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual, this);
        }

        @Override // p475rx.functions.Func1
        public T call(Object obj) {
            return this.f27607nl.getValue(obj);
        }

        void requestMore(long j) {
            BackpressureUtils.postCompleteRequest(this.requested, j, this.queue, this.actual, this);
        }
    }
}
